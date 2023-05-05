package com.lucamartinelli.telegram.bot.comands.admin.login;

import org.eclipse.microprofile.config.ConfigProvider;

import com.lucamartinelli.telegram.bot.comands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.lucamartinelli.telegram.bot.vo.LoggedRolesEnum;
import com.pengrad.telegrambot.model.Update;

public class LoginAdmin extends BotCommand {
	
	private final String adminPassword;

	public LoginAdmin(ChatSession chatSession, Update update) {
		super(chatSession, update);
		this.adminPassword = ConfigProvider.getConfig()
				.getOptionalValue("admin.password", String.class)
				.orElseGet(() -> {
					log.warn("No password set on the properties file for admin role, "
							+ "using default one");
					return "Password123";
				});
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
			sendMessage(chatID, "Papà? Sei tu?");
			chatSession.setCommandFlowIncomplete(true);
			chatSession.setProcessingCommand(this);
		}
		
		
		return 0;
	}

	
	@Override
	public int resumeExecution(Update newUpdate) {
		if (newUpdate == null || newUpdate.message() == null || newUpdate.message().text() == null) {
			log.debug("Input not valid for access to Admin mode, exiting...");
			sendMessage(chatID, "Ciò che hai mandato non può essere considerato una password, "
					+ "termino il comando");
			chatSession.resetIncompleteCommand();
			return 10;
		}
		text = newUpdate.message().text();
		if ("/exit".equalsIgnoreCase(text)) {
			
		}
		
		if (this.adminPassword.equals(this.text)) {
			log.debug("Password match, setting session for Admin user");
			log.infof("User %s with username %s gain the ADMIN role", 
					chatID, newUpdate.message().from().username());
			chatSession.resetIncompleteCommand();
			chatSession.setLoggedinRole(LoggedRolesEnum.ADMIN);
			sendMessage(chatID, "Papà! ❤😍😘❤");
			return 0;
		} else {
			log.debug("Password NOT match, exiting comand");
			log.infof("User %s with username %s used a wrong password for admin login", 
					chatID, newUpdate.message().from().username());
			chatSession.resetIncompleteCommand();
			sendMessage(chatID, "Mmm... non sono d'accordo, la password che hai inserito non "
					+ "è quella che mi aspettavo, termino il comando");
			return 2;
		}
	}
}
