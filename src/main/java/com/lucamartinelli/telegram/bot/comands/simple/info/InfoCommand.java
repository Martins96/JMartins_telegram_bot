package com.lucamartinelli.telegram.bot.comands.simple.info;

import com.lucamartinelli.telegram.bot.comands.BotCommand;
import com.lucamartinelli.telegram.bot.constants.Messages;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class InfoCommand extends BotCommand {

	public InfoCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		sendMessage(chatID, Messages.BOT_INFO);
		return 0;
	}

}
