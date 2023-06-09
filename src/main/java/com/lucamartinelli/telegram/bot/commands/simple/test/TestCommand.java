package com.lucamartinelli.telegram.bot.commands.simple.test;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class TestCommand extends BotCommand {

	public TestCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		sendMessage(chatID, "Nessun test in corso");
		return 0;
	}

}
