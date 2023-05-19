package com.lucamartinelli.telegram.bot.commands.random;

import java.util.Random;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class CoinCommand extends BotCommand {
	
	private final Random random; 

	public CoinCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
		this.random = new Random();
	}

	@Override
	protected int run() {
		log.debug("Sending a coin to user " + Long.toString(chatID));
		sendMessage(chatID, "🪙");
		final int percent = random.nextInt(101)+1;
		String result;
		if (percent < 51) {
			if (random.nextBoolean()) {
				result = "Il risultato è TESTA";
			} else {
				result = "È uscito TESTA";
			}
		} else if (percent < 101) {
			if (random.nextBoolean()) {
				result = "Il risultato è CROCE";
			} else {
				result = "È uscito CROCE";
			}
		} else {
			if (random.nextBoolean()) {
				result = "No! Mi è caduta nel tombino";
			} else {
				result = "Non è possibile, è finita di taglio!";
			}
		}
		
		sendMessage(chatID, result);
		
		return 0;
	}

}
