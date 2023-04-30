package com.lucamartinelli.telegram.bot.comands.calc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lucamartinelli.telegram.bot.comands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class SumCommand extends BotCommand {

	public SumCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("Sum numbers");
		if (update == null || update.message() == null || update.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, termino il comando di somma");
			log.debug("Input not manageable for Sum command, exiting from command");

			chatSession.resetIncompleteCommand();
			return 1;
		}
		
		final String input = update.message().text();
		log.debug("Input for Sum command: " + input);
		final String[] splitted = input.split(" ");
		if (splitted.length > 1) {
			sumNum(input.split(" ", 2)[1]);
			chatSession.resetIncompleteCommand();
		} else {
			sendMessage(chatID, "Inviami 2 o più numeri usando lo spazio come separatore es: (1 2 3,5 4)");
			chatSession.setCommandFlowIncomplete(true);
			chatSession.setProcessingCommand(this);
		}
		
		
		return 0;
	}
	
	
	@Override
	public int resumeExecution(Update newUpdate) {
		log.debug("Resuming sum command");
		if (newUpdate == null || newUpdate.message() == null || newUpdate.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, termino il comando Calcolatrice");
			log.debug("Input not manageable for Calc command, exiting from command");

			chatSession.resetIncompleteCommand();
			return 1;
		}
		
		final String input = newUpdate.message().text();
		log.debug("Input for resuming Sum command: " + input);
		sumNum(input);
		chatSession.resetIncompleteCommand();
	
		return 0;
	}
	
	
	private void sumNum(String input) {
		if ("A E".equals(input)) {
			log.debug("Easter egg triggered");
			sendMessage(chatID, "Caiiina!");
			return;
		}
		
		
		BigDecimal[] sum = { new BigDecimal(0 ) };
		List<String> errStrings = new ArrayList<>();
		log.debug("Sum for input: " + input);
		List<String> iSplitted = Arrays.asList(input.split(" "));
		log.debugf("Sum for %d numbers", iSplitted.size());
		iSplitted.forEach(s -> {
			try {
				if (s != null) {
					s = s.replace(',', '.');
					Float.parseFloat(s);
					log.debug("Adding " + s);
					sum[0] = sum[0].add(new BigDecimal(s));
				}
			} catch (NumberFormatException e) {
				log.debug(s + " is not a number");
				errStrings.add(s);
			}
		});
		if (errStrings.size() > 0) {
			sendMessage(chatID, "Non sono riuscita a comprendere i numeri: " 
					+ Arrays.toString(errStrings.toArray(new String[0])));
		}
		log.debug("Final sum is: " + sum[0].toString());
		sendMessage(chatID, "La somma è: " + sum[0].toString() + "\n Somma conclusa, termino il comando");
		
	}

}
