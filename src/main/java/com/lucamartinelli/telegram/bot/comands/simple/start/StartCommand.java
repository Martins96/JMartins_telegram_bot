package com.lucamartinelli.telegram.bot.comands.simple.start;

import com.lucamartinelli.telegram.bot.comands.BotCommand;
import com.lucamartinelli.telegram.bot.constants.Messages;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class StartCommand extends BotCommand {

	public StartCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		sendMessage(chatID, Messages.START);
		return 0;
	}

}
