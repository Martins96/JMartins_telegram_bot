package com.lucamartinelli.telegram.bot.comands.random;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.stream.Stream;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class AphorismCommand extends BotCommand {
	
	private final Random random;
	private long totalRows;

	public AphorismCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
		this.random = new Random();
		init();
	}

	@Override
	protected int run() {
		final String aphorism = readLine();
		if (aphorism == null) {
			log.error("Something going wrong in the file load, cannot send any message");
			return -1;
		}
		log.debug("Content loaded, sending message to " + Long.toString(chatID));
		sendMessage(chatID, aphorism);
		return 0;
	}

	private File loadAphorismFile() {
		log.debug("Loading aphorism row from: commands/perla/perle.txt");
		final ClassLoader classLoader = this.getClass().getClassLoader();
		return new File(classLoader.getResource("commands/perla/perle.txt").getFile());
	}
	
	private String readLine() {
		try (Stream<String> lines = Files.lines(loadAphorismFile().toPath())) {
			final long previousSelected = random.nextLong(totalRows);
			if (previousSelected == 0)
				return lines.findFirst().get();
		    return lines.skip(previousSelected).findFirst().get();
		} catch (IOException e) {
			log.error("Reading battute file error: ", e);
			return null;
		}
	}
	
	private void init() {
		try (Stream<String> lines = Files.lines(loadAphorismFile().toPath())) {
			this.totalRows = lines.count();
		} catch (IOException e) {
			log.error("Reading battute file error: ", e);
			this.totalRows = -1L;
		}
		log.debug("Total lines in files: " + this.totalRows);
	}

}
