package com.lucamartinelli.telegram.bot.commands.mylady.love;

import java.io.File;
import java.util.Random;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.lucamartinelli.telegram.bot.vo.LoggedRolesEnum;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class LoveCommand extends BotCommand {

	public LoveCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("Love command requested, checking logged session");
		if (chatSession.getLoggedinRole() == LoggedRolesEnum.MYLADY) {
			log.debugf("User with ID [%s] invoked love command in mylady session", 
					Long.toString(chatID));
		} else if (chatSession.getLoggedinRole() == LoggedRolesEnum.ADMIN) {
			log.debugf("User with ID [%s] invoked love command in admin session", 
					Long.toString(chatID));
		} else {
			log.infof("User with ID [%s] tried to invoke Mylady/Admin love command without "
					+ "be logged in", Long.toString(chatID));
			sendMessage(chatID, "Il comando richiede un'autorizzazione, autenticati "
					+ "usando il comando /mylady o /admin");
			return 1;
		}
		log.debug("User is logged and have the roles for this command,sending information");
		
		final SendResponse tempResp = sendMessage(chatID, "Invio...");
		sendPhoto(chatID, loadRandomFile());
		ellie.execute(new DeleteMessage(chatID, tempResp.message().messageId()));
		sendMessage(chatID, "❤");
		return 0;
	}
	
	
	private File loadFolder() {
		log.debug("Loading Love folder: commands/love from resources");
		final ClassLoader classLoader = this.getClass().getClassLoader();
		return new File(classLoader.getResource("commands/love").getFile());
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
