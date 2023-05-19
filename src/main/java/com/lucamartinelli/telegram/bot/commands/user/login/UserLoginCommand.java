package com.lucamartinelli.telegram.bot.commands.user.login;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.lucamartinelli.telegram.bot.vo.LoggedRolesEnum;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteMessage;

public class UserLoginCommand extends BotCommand {
	
	private final Config config;
	
	public UserLoginCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
		this.config = ConfigProvider.getConfig();
	}

	@Override
	protected int run() {
		log.debug("Checking if user is already logged");
		final LoggedRolesEnum role = chatSession.getLoggedinRole();
		if (role != null) {
			log.debugf("User is already logged with [%s] role", role.toString());
			sendMessage(chatID, "A quanto vedo sei già stato autenticato come '" + role.toString() 
					+ "', prima di effettuare un nuovo login devi scollegarti da quello precedente."
					+ "\nDigita il comando /logout per pulire la sessione e poter effettuare una "
					+ "nuova autenticazione");
			return 1;
			
		} else {
			log.debug("User is not logged in, asking password");
			sendMessage(chatID, "Ehilà! Per autenticarti scrivimi la tua parola segreta");
			chatSession.setCommandFlowIncomplete(true);
			chatSession.setProcessingCommand(this);
		}
		
		
		return 0;
	}

	
	@Override
	public int resumeExecution(Update newUpdate) {
		if (newUpdate == null || newUpdate.message() == null || newUpdate.message().text() == null) {
			log.debug("Input not valid for access to User mode, exiting...");
			sendMessage(chatID, "Ciò che hai mandato non può essere considerato una password, "
					+ "termino il comando");
			chatSession.resetIncompleteCommand();
			return 10;
		}
		text = newUpdate.message().text();
		if ("/exit".equalsIgnoreCase(text)) {
			log.debug("Exit requested, ending command");
			sendMessage(chatID, "Richiesta di uscita, termino il comando");
			chatSession.resetIncompleteCommand();
			return 1;
		}
		
		final String keyProperty = newUpdate.message().text().concat(".user.secret");
		final String usernameProperty = this.config.getOptionalValue(keyProperty, String.class)
				.orElse(null);
		
		
		
		if (usernameProperty == null) {
			log.debug("Password NOT match, exiting comand");
			log.infof("User %s with username %s used a wrong password for user login", 
					chatID, newUpdate.message().from().username());
			chatSession.resetIncompleteCommand();
			sendMessage(chatID, "Mi dispiace, ma la parola segreta che hai inserito non "
					+ "la conosco.");
			return 2;
		}
		

		log.debug("Password match, setting session for user role");
		log.infof("User %s with username %s gain the USER role. Welcome %s", 
				chatID, newUpdate.message().from().username(), usernameProperty);
		chatSession.resetIncompleteCommand();
		chatSession.setLoggedinRole(LoggedRolesEnum.USER);
		sendMessage(chatID, "Benvenuto " + usernameProperty + ", è un piacere sentirti. \n"
				+ "Se ti serve aiuto con la lista dei comandi disponibili esclusivi da utente "
				+ "loggato digita /userhelp");
		ellie.execute(new DeleteMessage(chatID, newUpdate.message().messageId()));
		return 0;
		
		
	}

}
