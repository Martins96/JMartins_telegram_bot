package com.lucamartinelli.telegram.bot.comands.random;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.stream.Stream;

import com.lucamartinelli.telegram.bot.comands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class JokeCommand extends BotCommand {
	
	private final Random random;

	public JokeCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
		this.random = new Random();
	}

	@Override
	protected int run() {
		final String joke = readLine();
		if (joke == null) {
			log.error("Something going wrong in the file load, cannot send any message");
			return -1;
		}
		log.debug("Content loaded, sending message to " + Long.toString(chatID));
		sendMessage(chatID, joke);
		return 0;
	}

	private File loadBattuteFile() {
		log.debug("Loading Joke row from: commands/battute/battute.txt");
		final ClassLoader classLoader = this.getClass().getClassLoader();
		return new File(classLoader.getResource("commands/battute/battute.txt").getFile());
	}
	
	private String readLine() {
		try (Stream<String> lines = Files.lines(loadBattuteFile().toPath())) {
			final long totalRows = lines.count();
			final long previousSelected = random.nextLong(totalRows);
			if (previousSelected == 0)
				return lines.findFirst().get();
		    return lines.skip(previousSelected).findFirst().get();
		} catch (IOException e) {
			log.error("Reading battute file error: ", e);
			return null;
		}
	}

}
