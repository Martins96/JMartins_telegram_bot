package com.lucamartinelli.telegram.bot.comands.media.asciiart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class AsciiArtCommand extends BotCommand {

	private final Random random;

	public AsciiArtCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
		this.random = new Random();
	}

	@Override
	protected int run() {
		final File asciiArtFile = loadRandomFile();
		final String asciiart = readContent(asciiArtFile);
		if (asciiart == null) {
			log.error("Something going wrong in the file load, cannot send any message");
			return -1;
		}
		log.debug("Content loaded, sending message to " + Long.toString(chatID));
		sendMessage(chatID, asciiart);
		return 0;
	}

	private File loadFolder() {
		log.debug("Loading Asciiart folder: commands/asciiart from resources");
		final ClassLoader classLoader = this.getClass().getClassLoader();
		return new File(classLoader.getResource("commands/asciiart").getFile());
	}
	
	private File loadRandomFile() {
		final File[] files = loadFolder().listFiles();
		log.debugf("There are a total %d files in the folder", files.length);
		final int selectedIndex = random.nextInt(files.length);
		log.debugf("Selecting file with index %d in the folder", selectedIndex);
		return files[selectedIndex];
	}
	
	private String readContent(File f) {
		final StringBuffer sb = new StringBuffer();
		log.debug("Reading content from file: " + f.getAbsolutePath());
		try (final FileReader fr = new FileReader(f);
				final BufferedReader b = new BufferedReader(fr)) {
		    String t = b.readLine();
			while(t != null) {
		    	sb.append(t).append("\n");
		    	t = b.readLine();
		    }
		} catch (IOException e) {
			log.error("Error reading file " + f.getName(), e);
			return null;
		}
		log.debug("Content loaded, total characters: " + sb.length());
		return sb.toString();
	}

}
