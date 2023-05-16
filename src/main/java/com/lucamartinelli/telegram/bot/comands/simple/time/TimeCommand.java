package com.lucamartinelli.telegram.bot.comands.simple.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class TimeCommand extends BotCommand {

	public TimeCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		sendMessage(chatID, "Seleziona la città:\n"
				+ "/Roma\n"
				+ "/HongKong\n"
				+ "/Mosca\n"
				+ "/Tokyo\n"
				+ "/NewDeli\n"
				+ "/LosAngeles\n"
				+ "/NewYork\n"
				+ "/Londra\n"
				+ "/Madrid");
		chatSession.setCommandFlowIncomplete(true);
		chatSession.setProcessingCommand(this);
		return 0;
	}

	
	@Override
	public int resumeExecution(Update newUpdate) {
		Date now = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss(Z)");
		
		switch (newUpdate.message().text()) {
		case "/Roma":
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
			break;
		case "/HongKong":
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Hong_Kong"));
			break;
		case "/Mosca":
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
			break;
		case "/Tokyo":
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
			break;
		case "/NewDeli":
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			break;
		case "/LosAngeles":
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
			break;
		case "/NewYork":
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			break;
		case "/Londra":
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
			break;
		case "/Madrid":
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
			break;
		default:
			sendMessage(chatID, String.format("Scusami, non posso gestire [%s], "
					+ "posso dirti che in Italia sono le: ", text));
		}
		
		sendMessage(chatID, simpleDateFormat.format(now));
		chatSession.setProcessingCommand(null);
		chatSession.setCommandFlowIncomplete(false);
		return 0;
	}
}
