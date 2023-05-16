package com.lucamartinelli.telegram.bot.comands.calc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class MultipCommand extends BotCommand {

	public MultipCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("Multip numbers");
		if (update == null || update.message() == null || update.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, termino il comando di moltiplicazione");
			log.debug("Input not manageable for Multip command, exiting from command");

			chatSession.resetIncompleteCommand();
			return 1;
		}
		
		final String input = update.message().text();
		log.debug("Input for Multip command: " + input);
		final String[] splitted = input.split(" ");
		if (splitted.length > 1) {
			multipNum(input.split(" ", 2)[1]);
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
		log.debug("Resuming multip command");
		if (newUpdate == null || newUpdate.message() == null || newUpdate.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, termino il comando Moltiplicazione");
			log.debug("Input not manageable for Multiply command, exiting from command");

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
		log.debug("Input for resuming Multip command: " + input);
		multipNum(input);
		chatSession.resetIncompleteCommand();
	
		return 0;
	}
	
	
	private void multipNum(String input) {
		if ("A E".equalsIgnoreCase(input)) {
			log.debug("Easter egg triggered");
			sendMessage(chatID, "Caiiina!");
			return;
		}
		
		
		BigDecimal[] multip = { new BigDecimal(1) };
		List<String> errStrings = new ArrayList<>();
		log.debug("Multip for input: " + input);
		List<String> iSplitted = Arrays.asList(input.split(" "));
		log.debugf("Multip for %d numbers", iSplitted.size());
		AtomicBoolean isOneInputGood = new AtomicBoolean(false);
		iSplitted.forEach(s -> {
			try {
				if (s != null) {
					s = s.replace(',', '.');
					Float.parseFloat(s);
					log.debug("Adding " + s);
					multip[0] = multip[0].multiply(new BigDecimal(s));
					isOneInputGood.set(true);
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
		if (isOneInputGood.get()) {
			log.debug("Final multip is: " + multip[0].toString());
			sendMessage(chatID, "Il prodotto è: " + multip[0].toString() + "\n Moltiplicazione conclusa, termino il comando");
		} else {
			sendMessage(chatID, "Nessun input per generare il prodotto");
		}
		
	}

}
