package com.lucamartinelli.telegram.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import static com.lucamartinelli.telegram.bot.constants.Constants.*;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jboss.logging.Logger;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.lucamartinelli.telegram.bot.thread.MessageManagingThread;
import com.lucamartinelli.telegram.bot.vo.ChatSession;

@QuarkusMain
public class ApplicationCore {
	
	public static ThreadPoolExecutor threadPool;
	public static TelegramBot ellie;
	public static Cache<String, ChatSession> chatCache;
	
	static Logger log = Logger.getLogger(ApplicationCore.class.getCanonicalName());

	/**
	 * Main method, this will start the world
	 */
	public static void main(String[] args) {
		Quarkus.run(EllieAppCore.class, args);
	}
	
	/**
	 * Core inner class, started by Quarkus, this will load data and start the long
	 * polling for the bot.
	 *
	 */
	public static class EllieAppCore implements QuarkusApplication {

        @Override
        public int run(String... args) throws Exception {
        	log.debug("[Ellie] Stating application, load initial information");
        	loadCache();
        	log.debug("[Ellie] Cache loaded and ready");
        	loadThreadPool();
        	log.debug("[Ellie] Thread pool loaded and ready");
        	loadBot();
        	log.debug("[Ellie] Bot connection loaded and ready");
        	loadListener();
        	log.debug("[Ellie] Listener attached to the bot, ready to receive messages");
        	
        	log.info("[Ellie] ready to action");
            Quarkus.waitForExit();
        	log.info("[Ellie] shutdown");
            return 0;
        }
        
        
        
        /**
         * Load the thread pool for async execution
         */
        private int loadThreadPool() {
        	log.debug("[Ellie] Creating Thread Pool using cached pool");
        	threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        	return 0;
		}
        
        /**
         * Load the bot connection using token
         */
        private int loadBot() {
        	log.debugf("[Ellie] Loading bot using token %s", BOT_TOKEN);
        	ellie = new TelegramBot(BOT_TOKEN);
        	return 0;
        }
        
        /**
         * Load the Caffeine cache for keep the session
         */
        private int loadCache() {
        	log.debugf("[Ellie] Creating cache pool, messages will expire in %d hours", CACHE_EXPIRATION_HOURS);
        	chatCache = Caffeine.newBuilder()
        			  .expireAfterWrite(CACHE_EXPIRATION_HOURS, TimeUnit.HOURS)
        			  .maximumSize(50)
        			  .initialCapacity(2)
        			  .build();
        	return 0;
        }
        
        /**
         * Append the listener to bot in order to receive the messages
         */
        private int loadListener() {
        	log.debugf("[Ellie] Setting up the listener to manage the incoming messages");
        	ellie.setUpdatesListener(new UpdatesListener() {
        	    @Override
        	    public int process(List<Update> updates) {
        	    	log.debugf("[Ellie] Managing %d update/s", updates.size());

        	        updates.forEach(update -> {
        	        	try {
        	        		threadPool.execute(new MessageManagingThread(update));
        	        	} catch (RuntimeException e) {
							log.error("Excaption in thread message: ", e);
						}
        	        });
        	    	log.debugf("[Ellie] Confirming the take in charge for the messages");

        	        return UpdatesListener.CONFIRMED_UPDATES_ALL;
        	    }
        	});
        	
        	return 0;
		}
    }

}
