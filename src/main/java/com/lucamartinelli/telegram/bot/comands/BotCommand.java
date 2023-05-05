package com.lucamartinelli.telegram.bot.comands;

import java.lang.reflect.InvocationTargetException;

import org.jboss.logging.Logger;

import com.github.benmanes.caffeine.cache.Cache;
import com.lucamartinelli.telegram.bot.ApplicationCore;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public abstract class BotCommand {
	
	protected final Logger log;
	protected final TelegramBot ellie;
	protected final Cache<String, ChatSession> chatCache;
	
	protected ChatSession chatSession;
	protected String text;
	protected final Update update;
	protected final Long chatID;
	
	public BotCommand(ChatSession chatSession, Update update) {
		
		this.update = update;
		this.chatSession = chatSession;
		this.log = Logger.getLogger(getClass());
		this.ellie = ApplicationCore.ellie;
		this.chatCache = ApplicationCore.chatCache;
		
		if (update == null || update.message() == null
				|| update.message().from() == null) {
			throw new RuntimeException("Update structure or message null, not manageable");
		}
		this.chatID = update.message().from().id();
		this.text = update.message().text();
	}
	
	protected abstract int run();
	
	
	
	public int executeCommand() {
		log.debug("Executing command: " + getClass().getCanonicalName());
		return run();
	}
	
	public ChatSession getSession() {
		return this.chatSession;
	}
	
	protected SendResponse sendMessage(Long chatID, String text) {
		if (chatID < 0) {
			try {
				return SendResponse.class.getDeclaredConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				log.error("Failed create dummy SendResponse", e);
				return null;
			}
		}
		log.infof("Sending message to [%s] with text: %s", Long.toString(chatID), text);
		return this.ellie.execute(new SendMessage(chatID, text));
	}
	
	public int resumeExecution(Update newUpdate) {
		log.warn("By default, this command not implement the continue execution logic,"
				+ " please override this method");
		
		return -1;
	}
	
}
