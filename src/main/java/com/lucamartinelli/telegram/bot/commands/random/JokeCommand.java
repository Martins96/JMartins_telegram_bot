package com.lucamartinelli.telegram.bot.commands.random;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.stream.Stream;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class JokeCommand extends BotCommand {
	
	private final Random random;
	private long totalRows;

	public JokeCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
		this.random = new Random();
		init();
	}

	

	@Override
	protected int run() {
		if (this.totalRows < 0) {
			log.debug("Cannot read total lines from joke file, file is not accessible");
			return -1;
		}
		
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
			log.debug("Generating random joke number");
			final long previousSelected = random.nextLong(totalRows);
			if (previousSelected == 0)
				return lines.findFirst().get();
			log.debugf("Sending joke number [%d]", previousSelected+1);
		    return lines.skip(previousSelected).findFirst().get();
		} catch (IOException e) {
			log.error("Reading battute file error: ", e);
			return null;
		}
	}
	
	
	private void init() {
		try (Stream<String> lines = Files.lines(loadBattuteFile().toPath())) {
			this.totalRows = lines.count();
		} catch (IOException e) {
			log.error("Reading battute file error: ", e);
			this.totalRows = -1L;
		}
		log.debug("Total lines in files: " + this.totalRows);
	}

}
