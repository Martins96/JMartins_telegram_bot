package com.lucamartinelli.telegram.bot.commands.simple.help;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.constants.Messages;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class HelpCommand extends BotCommand {

	public HelpCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		sendMessage(chatID, Messages.HELP);
		return 0;
	}

}
