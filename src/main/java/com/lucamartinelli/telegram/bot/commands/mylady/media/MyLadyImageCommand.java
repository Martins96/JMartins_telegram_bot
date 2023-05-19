package com.lucamartinelli.telegram.bot.commands.mylady.media;

import java.io.File;
import java.util.Random;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.lucamartinelli.telegram.bot.vo.LoggedRolesEnum;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class MyLadyImageCommand extends BotCommand {

	public MyLadyImageCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("Mylady image command requested, checking logged session");
		if (chatSession.getLoggedinRole() == LoggedRolesEnum.MYLADY) {
			log.debugf("User with ID [%s] invoked Mylady image command in mylady session", 
					Long.toString(chatID));
		}  else {
			log.infof("User with ID [%s] tried to invoke Mylady image command without "
					+ "be logged as MyLady", Long.toString(chatID));
			sendMessage(chatID, "Il comando richiede un'autorizzazione, autenticati "
					+ "usando il comando /mylady");
			return 1;
		}
		log.debug("User is logged and have the roles for this command, sending information");
		
		final SendResponse tempResp = sendMessage(chatID, "Invio...");
		sendPhoto(chatID, loadRandomFile());
		ellie.execute(new DeleteMessage(chatID, tempResp.message().messageId()));
		sendMessage(chatID, "Ecco qui mamma ❤");
		return 0;
	}
	
	
	private File loadFolder() {
		log.debug("Loading Mylady images folder: commands/mylady/images from resources");
		final ClassLoader classLoader = this.getClass().getClassLoader();
		return new File(classLoader.getResource("commands/mylady/images").getFile());
	}
	
	private File loadRandomFile() {
		final File[] files = loadFolder().listFiles();
		log.debugf("There are a total %d files in the folder", files.length);
		final int selectedIndex = new Random().nextInt(files.length);
		log.debugf("Selecting file with index %d in the folder", selectedIndex);
		log.debugf("Sending photo at path %s", files[selectedIndex].getAbsolutePath());
		return files[selectedIndex];
	}

}
