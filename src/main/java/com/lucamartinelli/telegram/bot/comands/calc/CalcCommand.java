package com.lucamartinelli.telegram.bot.comands.calc;

import com.lucamartinelli.telegram.bot.comands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class CalcCommand extends BotCommand {

	public CalcCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debugf("Starting calc command for user %d", chatID);
		sendMessage(chatID, "Avvio la funzione calcolatrice:\n"
				+ "/+   Somma 2 o più numeri\n"
				+ "/-   Esegue la sottrazione di 2 numeri\n"
				+ "/:   Divide 2 numeri\n"
				+ "/x   Moltiplica 2 o più numeri\n"
				+ "/sqrt   radice quadrata di un numero\n\n"
				+ "/exit   chiudi il comando Calcolatrice");
		chatSession.setCommandFlowIncomplete(true);
		chatSession.setProcessingCommand(this);
		return 0;
	}
	
	@Override
	public int resumeExecution(Update newUpdate) {
		log.debug("Resuming Calc command");
		if (newUpdate == null || newUpdate.message() == null || newUpdate.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, termino il comando Calcolatrice");
			log.debug("Input not manageable for Calc command, exiting from command");

			chatSession.resetIncompleteCommand();
			return 1;
		}
		
		final String input = newUpdate.message().text();
		log.debug("Input for resuming Calc command: " + input);
		
		switch (input.split(" ", 2)[0]) {
		case "/+":
			BotCommand cmd = new SumCommand(chatSession, newUpdate);
			cmd.executeCommand();
			chatSession = cmd.getSession();
			break;
		case "/-":

			break;
		case "/x":

			break;
		case "/:":

			break;
		case "/sqrt":

			break;
		case "/exit":
			sendMessage(chatID, "Uscita dal comando Calcolatrice");
			chatSession.resetIncompleteCommand();
			break;

		default:
			sendMessage(chatID, "Input non riconosciuto... Per favore, scrivi uno di questi comandi con lo /\n"
					+ "/+   Somma 2 o più numeri\n"
					+ "/-   Esegue la sottrazione di 2 numeri\n"
					+ "/:   Divide 2 numeri\n"
					+ "/x   Moltiplica 2 o più numeri\n"
					+ "/sqrt   radice quadrata di un numero\n\n"
					+ "/exit   chiudi il comando Calcolatrice");
			break;
		}
		
		
		return 0;
	}

}
