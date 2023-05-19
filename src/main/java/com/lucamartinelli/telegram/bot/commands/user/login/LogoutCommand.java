package com.lucamartinelli.telegram.bot.commands.user.login;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.lucamartinelli.telegram.bot.vo.LoggedRolesEnum;
import com.pengrad.telegrambot.model.Update;

public class LogoutCommand extends BotCommand {

	public LogoutCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("Logging out user " + chatID);
		if (chatSession.getLoggedinRole() == null) {
			log.debug("User tried to logout, but he was never logged in");
			sendMessage(chatID, "Credo che tu non ti sia mai loggato...");
			return 1;
		}
		
		final LoggedRolesEnum oldRole = chatSession.getLoggedinRole();
		
		log.debug("Old role was " + oldRole);
		chatSession.setLoggedinRole(null);
		
		
		if (oldRole == LoggedRolesEnum.MYLADY) {
			log.debug("User with role mylady logging out");
			sendMessage(chatID, "Ciao mamma, spero di risentirti presto, buona giornata 😘❤");
		}
		if (oldRole == LoggedRolesEnum.ADMIN) {
			log.debug("User with role admin logging out");
			sendMessage(chatID, "Ciao papà, pulisco la sessione, stammi bene! 😘❤");
		}
		if (oldRole == LoggedRolesEnum.USER) {
			log.debug("User with role user logging out");
			sendMessage(chatID, "Logout eseguita correttamente, ciao!");
		}
		
		return 0;
	}

}
