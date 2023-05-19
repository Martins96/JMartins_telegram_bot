package com.lucamartinelli.telegram.bot.commands.calc;

import java.util.Random;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class RandCommand extends BotCommand {

	public RandCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("rand numbers");
		if (update == null || update.message() == null || update.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, termino il comando di generazione numero casuale");
			log.debug("Input not manageable for rand command, exiting from command");

			chatSession.resetIncompleteCommand();
			return 1;
		}
		
		final String input = update.message().text();
		log.debug("Input for rand command: " + input);
		final String[] splitted = input.split(" ");
		if (splitted.length > 1) {
			int res = randNum(input.split(" ", 2)[1]);
			if (res == 0) {
				chatSession.resetIncompleteCommand();
			} else {
				chatSession.setCommandFlowIncomplete(true);
				chatSession.setProcessingCommand(this);
			}
			return res;
		} else {
			sendMessage(chatID, "Inviami un numero intero "
					+ "come limite massimo del numero casuale (inclusivo)");
			chatSession.setCommandFlowIncomplete(true);
			chatSession.setProcessingCommand(this);
		}
		
		
		return 0;
	}
	
	
	@Override
	public int resumeExecution(Update newUpdate) {
		log.debug("Resuming diff command");
		if (newUpdate == null || newUpdate.message() == null || newUpdate.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, termino il comando di random");
			log.debug("Input not manageable for Rand command, exiting from command");

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
		log.debug("Input for resuming rand command: " + input);
		int res = randNum(input);
		if (res == 0) {
			chatSession.resetIncompleteCommand();
		} else {
			chatSession.setCommandFlowIncomplete(true);
			chatSession.setProcessingCommand(this);
		}
	
		return res;
	}
	
	
	private int randNum(String input) {
		if ("A E".equalsIgnoreCase(input)) {
			log.debug("Easter egg triggered");
			sendMessage(chatID, "Caiiina!");
			return 0;
		}
		
		
		
		log.debug("rand for input: " + input);
		input = input.replace(',', '.');
		int bound;
		try {
			bound = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			log.debug(input + " is not a number");
			sendMessage(chatID, "L'input non è gestibile, manda un numero intero da usare come limite "
					+ "massimo per generare un numero intero casuale da 0 al limite (inclusivo)");
			return 1;
		}
		
		
		Random rand = new Random();
		final int num = rand.nextInt(bound+1);
		log.debugf("Square root for %s is %d ", input, num);
		
		sendMessage(chatID, "Il numero casuale è: " + num + "\n rand concluso, termino il comando");
		return 0;
		
	}

}
