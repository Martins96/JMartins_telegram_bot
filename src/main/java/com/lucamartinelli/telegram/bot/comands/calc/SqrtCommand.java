package com.lucamartinelli.telegram.bot.comands.calc;

import java.math.BigDecimal;
import java.math.MathContext;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class SqrtCommand extends BotCommand {

	public SqrtCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("SQRT numbers");
		if (update == null || update.message() == null || update.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, termino il comando di radice quadrata");
			log.debug("Input not manageable for Sqrt command, exiting from command");

			chatSession.resetIncompleteCommand();
			return 1;
		}
		
		final String input = update.message().text();
		log.debug("Input for Sqrt command: " + input);
		final String[] splitted = input.split(" ");
		if (splitted.length > 1) {
			int res = sqrtNum(input.split(" ", 2)[1]);
			if (res == 0) {
				chatSession.resetIncompleteCommand();
			} else {
				chatSession.setCommandFlowIncomplete(true);
				chatSession.setProcessingCommand(this);
			}
			return res;
		} else {
			sendMessage(chatID, "Inviami un numero dove applicare la radice quadrata");
			chatSession.setCommandFlowIncomplete(true);
			chatSession.setProcessingCommand(this);
		}
		
		
		return 0;
	}
	
	
	@Override
	public int resumeExecution(Update newUpdate) {
		log.debug("Resuming diff command");
		if (newUpdate == null || newUpdate.message() == null || newUpdate.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, termino il comando di radice quadrata");
			log.debug("Input not manageable for sqrt command, exiting from command");

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
		log.debug("Input for resuming SQRT command: " + input);
		int res = sqrtNum(input);
		if (res == 0) {
			chatSession.resetIncompleteCommand();
		} else {
			chatSession.setCommandFlowIncomplete(true);
			chatSession.setProcessingCommand(this);
		}
	
		return res;
	}
	
	
	private int sqrtNum(String input) {
		if ("A E".equalsIgnoreCase(input)) {
			log.debug("Easter egg triggered");
			sendMessage(chatID, "Caiiina!");
			return 0;
		}
		
		
		
		log.debug("sqrt for input: " + input);
		String[] iSplitted = input.split(" ");
		if (iSplitted.length > 1) {
			log.debugf("Input [%s] not contains a number", input);
			sendMessage(chatID, "L'input non è gestibile, manda solo un numero");
			return 1;
		}
		input = input.replace(',', '.');
		try {
			Float.parseFloat(input);
		} catch (NumberFormatException e) {
			log.debug(input + " not contains only numbers");
			sendMessage(chatID, "L'input non è gestibile, manda un numero");
			return 1;
		}
		
		
		BigDecimal sqrt = new BigDecimal(iSplitted[0]).sqrt(new MathContext(15));
		log.debugf("Square root for %s is %s ", input, sqrt.toString());
		
		sendMessage(chatID, "La radice quadrata è: " + sqrt.toString() + "\n Sqrt conclusa, termino il comando");
		return 0;
		
	}

}
