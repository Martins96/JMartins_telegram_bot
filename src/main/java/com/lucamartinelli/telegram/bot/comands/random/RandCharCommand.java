package com.lucamartinelli.telegram.bot.comands.random;

import java.util.Random;

import com.lucamartinelli.telegram.bot.comands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class RandCharCommand extends BotCommand {

	public RandCharCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		Random r = new Random();
		final char c = (char)(r.nextInt(26) + 'a');
		sendMessage(chatID, new String(""+c).toUpperCase());
		return 0;
	}

}
