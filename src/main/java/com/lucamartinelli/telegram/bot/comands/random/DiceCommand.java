package com.lucamartinelli.telegram.bot.comands.random;

import com.lucamartinelli.telegram.bot.comands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendDice;

public class DiceCommand extends BotCommand {

	public DiceCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("Sending a dice to user " + Long.toString(chatID));
		this.ellie.execute(new SendDice(chatID));
		return 0;
	}

}
