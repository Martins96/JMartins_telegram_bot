package com.lucamartinelli.telegram.bot.commands.admin.shutdown;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.lucamartinelli.telegram.bot.vo.LoggedRolesEnum;
import com.pengrad.telegrambot.model.Update;

import io.quarkus.runtime.Quarkus;

public class ShutdownCommand extends BotCommand {

	public ShutdownCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("Shutdown command requested, checking logged session");
		if (chatSession.getLoggedinRole() == LoggedRolesEnum.MYLADY) {
			log.debugf("User with ID [%s] invoked Shutdown command in mylady session", 
					Long.toString(chatID));
		} else if (chatSession.getLoggedinRole() == LoggedRolesEnum.ADMIN) {
			log.debugf("User with ID [%s] invoked Shutdown command in admin session", 
					Long.toString(chatID));
		} else {
			log.infof("User with ID [%s] tried to invoke Mylady/Admin Shutdown command without "
					+ "be logged in", Long.toString(chatID));
			sendMessage(chatID, "Il comando richiede un'autorizzazione, autenticati "
					+ "usando il comando /mylady o /admin");
			return 1;
		}
		log.debug("User is logged and have the roles for this command,sending information");
		
		
		
		sendMessage(chatID, "Hai avviato il comando di spegnimento, vuoi mandarmi a nanna?\n"
				+ "Attenzione: una volta spenta bisognerà riattivarmi dalla macchina di "
				+ "esecuzione, non si può fare qui\n\n"
				+ "/Y\n"
				+ "/N");
		chatSession.setCommandFlowIncomplete(true);
		chatSession.setProcessingCommand(this);
		return 0;
	}
	
	@Override
	public int resumeExecution(Update newUpdate) {
		if (newUpdate == null || newUpdate.message() == null || newUpdate.message().text() == null) {
			log.debug("Input not valid for access to Mylady mode, exiting...");
			sendMessage(chatID, "Ciò che hai mandato non è un input valido, "
					+ "termino il comando");
			chatSession.resetIncompleteCommand();
			return 10;
		}
		text = newUpdate.message().text();
		if (text.equals("/Y")) {
			log.debug("Confirmation is [No] starting shutdown");
			shutdown();
			return 1;
		} else if (text.equals("/N")) {
			log.debug("Confirmation is [No] keep running bot");
			sendMessage(chatID, "Ok resto attiva!");
			chatSession.resetIncompleteCommand();
			return 2;
		} else {
			log.debugf("Input not valid [%s] - re-asking confirmation", text);
			sendMessage(chatID, "L'input inserito non è valido, seleziona una delle 2 "
					+ "opzioni\n"
					+ "Vuoi spegnermi?\n"
					+ "/Y\n"
					+ "/N");
			return 3;
		}
		
	}
	
	private void shutdown() {
		log.infof("*** SHUTDOWN PROCEDURE STARTED ***");
		log.infof("*** SHUTDOWN REQUESTED BY [%s] ***", update.message().from().username());
		sendMessage(chatID, "Avvio la procedura di spegnimento, buonanotte!");
		Quarkus.asyncExit();
		System.exit(0);
	}

}
