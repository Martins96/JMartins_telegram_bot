package com.lucamartinelli.telegram.bot.comands.weather;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class WeatherCommand extends BotCommand {

	public WeatherCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		sendMessage(chatID, "Il comando è in via di sviluppo");
		return 0;
	}
	
	
	
	@Override
	public int resumeExecution(Update newUpdate) {
		return -1;
	}

}
