package com.lucamartinelli.telegram.bot.commands.simple.pinfo;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

public class PersonalInfoCommand extends BotCommand {

	public PersonalInfoCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		final Message messaggio = update.message();
		final StringBuffer sb = new StringBuffer();
		final String loggedRole = chatSession.getLoggedinRole() == null ?
				"No" : chatSession.getLoggedinRole().toString();
		
		
		sb.append("Informazioni personali chat: \n ")
			.append("\n<b>ID Chat</b>\n").append(messaggio.chat().id())
			.append("\n\n<b>ID Utente</b>\n").append(messaggio.from().id())
			.append("\n\n<b>Nome Utente</b>\n").append(messaggio.from().firstName())
			.append("\n\n<b>Cognome Utente</b>\n").append(messaggio.from().lastName())
			.append("\n\n<b>Username Utente</b>\n").append(messaggio.from().username())
			.append("\n\n<b>Utente Premium</b>\n").append(messaggio.from().isPremium())
			.append("\n\n<b>Firma</b>\n").append(messaggio.authorSignature())
			.append("\n\n<b>Firma</b>\n").append(messaggio.authorSignature())
			.append("\n\n<b><i>INFO SESSIONE</i></b>\n")
			.append("\n\n<b>Loggato</b>\n").append(loggedRole)
			;
		sendMessageHTML(chatID, sb.toString());
		return 0;
	}

}
