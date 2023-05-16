package com.lucamartinelli.telegram.bot.comands.calc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class MidCommand extends BotCommand {

	public MidCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("middle numbers");
		if (update == null || update.message() == null || update.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, termino il comando di media");
			log.debug("Input not manageable for middle command, exiting from command");

			chatSession.resetIncompleteCommand();
			return 1;
		}
		
		final String input = update.message().text();
		log.debug("Input for middle command: " + input);
		final String[] splitted = input.split(" ");
		if (splitted.length > 1) {
			int res = middleNum(input.split(" ", 2)[1]);
			if (res == 0) {
				chatSession.resetIncompleteCommand();
			} else {
				chatSession.setCommandFlowIncomplete(true);
				chatSession.setProcessingCommand(this);
			}
			return res;
		} else {
			sendMessage(chatID, "Inviami una serie di numeri dove calcolare la media");
			chatSession.setCommandFlowIncomplete(true);
			chatSession.setProcessingCommand(this);
		}
		
		
		return 0;
	}
	
	
	@Override
	public int resumeExecution(Update newUpdate) {
		log.debug("Resuming middle command");
		if (newUpdate == null || newUpdate.message() == null || newUpdate.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, termino il comando di media");
			log.debug("Input not manageable for middle command, exiting from command");

			chatSession.resetIncompleteCommand();
			return 1;
		}
		
		final String input = newUpdate.message().text();
		if ("/exit".equalsIgnoreCase(input)) {
			log.debug("Exiting command");
			sendMessage(chatID, "Uscita dal comando");
			chatSession.resetIncompleteCommand();
			return 0;
		}
		log.debug("Input for resuming middle command: " + input);
		int res = middleNum(input);
		if (res == 0) {
			chatSession.resetIncompleteCommand();
		} else {
			chatSession.setCommandFlowIncomplete(true);
			chatSession.setProcessingCommand(this);
		}
	
		return res;
	}
	
	
	private int middleNum(String input) {
		if ("A E".equalsIgnoreCase(input)) {
			log.debug("Easter egg triggered");
			sendMessage(chatID, "Caiiina!");
			return 0;
		}
		
		
		
		log.debug("middle for input: " + input);
		String[] iSplitted = input.split(" ");
		List<String> errStrings = new ArrayList<>();
		List<BigDecimal> numbers = new ArrayList<>();
		BigDecimal[] total = new BigDecimal[] {new BigDecimal(0)};
		Arrays.asList(iSplitted).forEach(e -> {
			e = e.replace(',', '.');
			try {
				Float.parseFloat(e);
				final BigDecimal num = new BigDecimal(e);
				numbers.add(num);
				total[0] = total[0].add(num);
			} catch (NumberFormatException ex) {
				log.debug(e + " is not a number");
				errStrings.add(e);
			}
			
		});
		
		if (errStrings.size() > 0) {
			sendMessage(chatID, "Non sono riuscita a comprendere i numeri: " 
					+ Arrays.toString(errStrings.toArray(new String[0])));
		}
		if (numbers.size() > 0) {
			log.debugf("Performing divide %s of %d", total[0].toString(), numbers.size());
			BigDecimal middle = total[0].divide(new BigDecimal(numbers.size()), new MathContext(10));
			log.debugf("Median for %s is %s ", input, middle.toString());
			
			sendMessage(chatID, "La media è: " + middle.toString() + "\n Media conclusa, termino il comando");
			return 0;
		} else {
			sendMessage(chatID, "nessun numero valido per eseguire una media, "
					+ "inserisci dei numeri separati da uno spazio");
			return 1;
		}
		
		
	}

}
