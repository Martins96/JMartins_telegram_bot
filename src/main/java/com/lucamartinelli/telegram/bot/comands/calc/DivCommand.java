package com.lucamartinelli.telegram.bot.comands.calc;

import java.math.BigDecimal;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class DivCommand extends BotCommand {

	public DivCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("Diff numbers");
		if (update == null || update.message() == null || update.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, termino il comando di divisione");
			log.debug("Input not manageable for Div command, exiting from command");

			chatSession.resetIncompleteCommand();
			return 1;
		}
		
		final String input = update.message().text();
		log.debug("Input for Div command: " + input);
		final String[] splitted = input.split(" ");
		if (splitted.length > 1) {
			int res = divNum(input);
			if (res == 0) {
				chatSession.resetIncompleteCommand();
			} else {
				chatSession.setCommandFlowIncomplete(true);
				chatSession.setProcessingCommand(this);
			}
			return res;
		} else {
			sendMessage(chatID, "Inviami 2 numeri usando lo spazio come separatore es: (3,5 1)");
			chatSession.setCommandFlowIncomplete(true);
			chatSession.setProcessingCommand(this);
		}
		
		
		return 0;
	}
	
	
	@Override
	public int resumeExecution(Update newUpdate) {
		log.debug("Resuming diff command");
		if (newUpdate == null || newUpdate.message() == null || newUpdate.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, termino il comando di divisione");
			log.debug("Input not manageable for Div command, exiting from command");

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
		log.debug("Input for resuming Div command: " + input);
		int res = divNum(input);
		if (res == 0) {
			chatSession.resetIncompleteCommand();
		} else {
			chatSession.setCommandFlowIncomplete(true);
			chatSession.setProcessingCommand(this);
		}
	
		return res;
	}
	
	
	private int divNum(String input) {
		if ("A E".equalsIgnoreCase(input)) {
			log.debug("Easter egg triggered");
			sendMessage(chatID, "Caiiina!");
			return 0;
		}
		
		
		
		log.debug("Diff for input: " + input);
		String[] iSplitted = input.split(" ");
		if (iSplitted.length != 2) {
			log.debugf("Input [%s] not contains only 2 numbers", input);
			sendMessage(chatID, "L'input non è gestibile, manda solo 2 numeri separati da uno spazio");
			return 1;
		}
		iSplitted[0] = iSplitted[0].replace(',', '.');
		iSplitted[1] = iSplitted[1].replace(',', '.');
		try {
			Float.parseFloat(iSplitted[0]);
			Float.parseFloat(iSplitted[1]);
		} catch (NumberFormatException e) {
			log.debug(input + " not contains only numbers");
			sendMessage(chatID, "L'input non è gestibile, manda 2 numeri separati da uno spazio");
			return 1;
		}
		
		
		BigDecimal diff = new BigDecimal(iSplitted[0]).divide(new BigDecimal(iSplitted[1]));
		log.debugf("Div for %s is %s ", input, iSplitted.toString());
		
		log.debugf("Final div for %s is %s", input, diff.toString());
		sendMessage(chatID, "La divisione è: " + diff.toString() + "\n Divisione conclusa, termino il comando");
		return 0;
		
	}

}
