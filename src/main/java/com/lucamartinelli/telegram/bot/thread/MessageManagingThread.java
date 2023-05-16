package com.lucamartinelli.telegram.bot.thread;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;

import org.jboss.logging.Logger;

import com.github.benmanes.caffeine.cache.Cache;
import com.lucamartinelli.telegram.bot.ApplicationCore;
import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.commands.CommandSelector;
import com.lucamartinelli.telegram.bot.util.Dumper;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class MessageManagingThread extends Thread {
	
	public String exitStatus;
	private final Logger log;
	private long userID;
	private ChatSession chatSession;
	private final Update update;
	
	private final Cache<String, ChatSession> chatCache = ApplicationCore.chatCache;
	private final TelegramBot ellie = ApplicationCore.ellie;
	
	
	public MessageManagingThread(final Update update) {
		log = Logger.getLogger(getClass());
		exitStatus = "In execution";
		this.update = update;
	}
	
	
	@Override
	public void run() {
		init();
		log.debug("Starting processing message");
		try {
			processMessage();
			log.debug("Flow processing message is finished successfully");
			exitStatus = "Finished successful";
		} catch (RuntimeException e) {
			log.errorf(e, "Error in processing message for User [%s - %s]", 
					""+update.message().from().id(),
					""+update.message().from().username());
			exitStatus = "Error: " + e.getMessage();
		} finally {
			log.debug("Updating cache with the new session for user: " + chatSession.getUsername());
			updateCache();
			log.debug("Ending processing message");
		}
		
		
		super.run();
	}
	
	private void init() {
		log.info("Incoming message: " + Dumper.dump(update));
		if (update == null || update.message() == null
				|| update.message().from() == null 
				|| update.message().from().id() == null) {
			exitStatus = "User ID not found, not manageable";
			throw new RuntimeException(exitStatus);
		}
		this.userID = update.message().from().id();
		
		log.debug("Loading session cache");
		chatSession = Optional
				.ofNullable(chatCache.getIfPresent(Long.toString(userID)))
				.orElse(new ChatSession());
	}
	
	/**
	 * Managing incoming message
	 */
	private void processMessage() {
		log.debug("Procesing message: " + update.message().text());
		if (chatSession.isCommandFlowIncomplete()) {
			if (continueIncompleteCommand()) {
				log.debug("Command resumed successfully, no other action required");
				return;
			}
		}
		
		
		if (update.message().text() != null && update.message().text().startsWith("/")) {
			log.debug("Message is a command, starting command flow");
			executeCommand();
			return;
		}
		
		log.debug("Message is not a command, starting simple flow");
		ellie.execute(new SendMessage(update.message().from().id(), "Hello world"));
	}
	
	
	
	/**
	 * Continue last incomplete command
	 * @return true if the command it will managed, false if an error occurs and the message will be 
	 * managed in the standard way
	 */
	private boolean continueIncompleteCommand() {
		log.debug("Found in session an incomplete command state, continue with last command");
		boolean result;
		if (chatSession.getProcessingCommand() == null) {
			log.warn("Incomplete status found, but no command found in cache, this is not a correct state, cleaning up");
			this.chatSession.resetIncompleteCommand();
			log.debug("Session command reset");
			result = false;
		} else {
			final BotCommand command = this.chatSession.getProcessingCommand();
			log.debug("Resume the command: " + command.getClass());
			final int exitCode = command.resumeExecution(update);
			if (exitCode < 0) {
				log.warn("Command finished with an error code: " + exitCode);
				log.warn("Message will be managed in the standard way");
				result = false;
			} else {
				log.debug("Command resumed correctly");
				result = true;
			}
			this.chatSession = command.getSession() != null ? command.getSession() : new ChatSession();
		}

		
		return result;
		
	}


	/**
	 * Flow for command, the command message have the prefix: /
	 */
	private int executeCommand() {
		log.debug("Checking command in the selector");
		BotCommand command = CommandSelector.getCommand(update, chatSession);
		log.debug("Command found, using: " + command.getClass().getCanonicalName());
		log.debug("Executing command");
		int result = command.executeCommand();
		if (result == 0) {
			log.debug("Command done");
		} else if (result < 0){
			log.debug("Command finished with an error code: " + result);
		} else {
			log.debug("Command finished a not-standard exitcase: " + result);
		}

		this.chatSession = command.getSession() != null ? command.getSession() : new ChatSession();
		return result;
	}
	
	
	
	private void updateCache() {
		log.debug("Updating cache");
		if (update != null &&  update.message() != null) {
			log.debugf("Setting last message: %s in date %s", 
					update.message().text(),
					Instant.ofEpochSecond(update.message().date())
							.atZone(ZoneId.of("Europe/Rome")).toString());
			chatSession.setLastMessageText(update.message().text());
			chatSession.setLastMessageTime(update.message().date());
			if (update.message().from() != null) {
				chatSession.setUsername(update.message().from().username());
			}
		}
		
		chatSession.setUserid(userID);
		
		
		ApplicationCore.chatCache.put(Long.toString(userID), chatSession);
	}
}
