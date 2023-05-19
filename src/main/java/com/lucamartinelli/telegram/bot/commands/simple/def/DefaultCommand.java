package com.lucamartinelli.telegram.bot.commands.simple.def;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class DefaultCommand extends BotCommand {
	
	public DefaultCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		sendMessage(chatID, "Comando non riconosciuto, "
				+ "prova /help per visualizzare cosa so fare");
		return 0;
	}

}
