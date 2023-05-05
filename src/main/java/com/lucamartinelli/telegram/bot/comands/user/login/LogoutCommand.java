package com.lucamartinelli.telegram.bot.comands.user.login;

import com.lucamartinelli.telegram.bot.comands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
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
		
		log.debug("Old role was " + chatSession.getLoggedinRole().toString());
		chatSession.setLoggedinRole(null);
		sendMessage(chatID, "Logout eseguita correttamente, ciao!");
		return 0;
	}

}
