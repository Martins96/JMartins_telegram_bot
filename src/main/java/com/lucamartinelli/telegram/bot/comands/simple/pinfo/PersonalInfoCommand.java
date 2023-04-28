package com.lucamartinelli.telegram.bot.comands.simple.pinfo;

import com.lucamartinelli.telegram.bot.comands.BotCommand;
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
		sb.append("Informazioni personali chat: \n ")
			.append("\n\bID Chat\b\n").append(messaggio.chat().id())
			.append("\n\n\bID Utente\b\n").append(messaggio.from().id())
			.append("\n\n\bNome Utente\b\n").append(messaggio.from().firstName())
			.append("\n\n\bCognome Utente\b\n").append(messaggio.from().lastName())
			.append("\n\n\bUsername Utente\b\n").append(messaggio.from().username())
			.append("\n\n\bUtente Premium\b\n").append(messaggio.from().isPremium())
			.append("\n\n\bFirma\b\n").append(messaggio.authorSignature())
			;
		sendMessage(chatID, sb.toString());
		return 0;
	}

}
