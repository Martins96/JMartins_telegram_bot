package com.lucamartinelli.telegram.bot.commands.admin.getlog;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.eclipse.microprofile.config.ConfigProvider;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.lucamartinelli.telegram.bot.vo.LoggedRolesEnum;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendDocument;

public class GetLogCommand extends BotCommand {
	
	private final String logPath;

	public GetLogCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
		this.logPath = ConfigProvider.getConfig()
				.getOptionalValue("quarkus.log.file.path", String.class)
				.orElseGet(() -> {
					log.warn("Log path not set, getLog command will be not available");
					return null;
				});
	}

	@Override
	protected int run() {
		if (chatSession.getLoggedinRole() != LoggedRolesEnum.ADMIN) {
			log.debugf("User with ID [%s] invoked GetLog command, but is not an ADMIN", 
					Long.toString(chatID));
			sendMessage(chatID, "Perdonami, ma questo comando è disponibile solo per ADMIN\n"
					+ "Loggati usando il comando /admin");
			return 10;
		}
		
		log.debug("Starting GetLog command, reading log file from: " + logPath);
		
		if (logPath == null) {
			log.warn("Log path not available, blocking command");
			sendMessage(chatID, "Non è stato settato alcun path per i log"
					+ ", non posso fornirli");
			return 1;
		}
		
		final File logFile = new File(logPath);
		if (logFile == null || !logFile.exists()) {
			log.info("The log file not exists, blocking command");
			sendMessage(chatID, "Non esiste alcun file al path " + logPath);
			return 2;
		} else if (!logFile.canRead()) {
			log.info("The log file is not readable, blocking command");
			sendMessage(chatID, "Non ho i permessi per leggere il file di log");
			return 3;
		}
		
		if (args != null && args.length > 0 && args[0].equals("file")) {
			sendFullFile(logFile);
		} else {
			try {
				readTailAndSend(logFile);
			} catch (IOException e) {
				log.error("Error reading Log File", e);
				sendMessage(chatID, "Non sono riuscita a leggere il file di log: " + e.getMessage());
				return 4;
			}
		}
		
		
		
		return 0;
	}
	
	
	
	private void readTailAndSend(File logFile) throws IOException {
		int n_lines = 20;
		int counter = 0; 
		
		try(final ReversedLinesFileReader fileReaderReverse = 
				new ReversedLinesFileReader(logFile, Charset.defaultCharset())) {
			final StringBuffer sb = new StringBuffer();
			
			while(counter < n_lines) {
			    sb.append(fileReaderReverse.readLine()).append('\n');
			    counter++;
			}
			
			sendMessage(chatID, sb.toString());
		}
	}
	
	private void sendFullFile(File logFile) {
		ellie.execute(new SendDocument(chatID, logFile));
	}

}
