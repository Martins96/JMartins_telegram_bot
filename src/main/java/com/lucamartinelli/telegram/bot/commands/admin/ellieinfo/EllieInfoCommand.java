package com.lucamartinelli.telegram.bot.commands.admin.ellieinfo;

import com.lucamartinelli.telegram.bot.ApplicationCore;
import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.lucamartinelli.telegram.bot.vo.LoggedRolesEnum;
import com.pengrad.telegrambot.model.Update;

public class EllieInfoCommand extends BotCommand {

	public EllieInfoCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("Checking if user is already logged");
		final LoggedRolesEnum role = chatSession.getLoggedinRole();
		if (role != null && role.equals(LoggedRolesEnum.ADMIN)) {
			log.debugf("User allowed to invoke this admin command");
			if (args.length > 0 && args[0].equalsIgnoreCase("chatcache")) {
				return getInfoCacheFlow();
			} else {
				return getInfoFlow();
			}
			
		} else {
			log.debug("User not allowed to invoke this command");
			sendMessage(chatID, "Scusami, ma questo comando è riservato ad un Admin."
					+ "\nAutenticati usando /admin");
			return 1;
		}
	}

	
	
	private int getInfoFlow() {
		final StringBuffer sb = new StringBuffer();
		sb.append("<b>ELLIE INFO</b>\n");
		sb.append("<b>Thread attivi</b>\n  ")
				.append(ApplicationCore.threadPool.getActiveCount())
				.append('\n');
		sb.append("<b>Thread massimi simultanei</b>\n  ")
				.append(ApplicationCore.threadPool.getLargestPoolSize())
				.append('\n');
		sb.append("<b>Grandezza Chat Cache</b>\n  ")
				.append(chatCache.estimatedSize())
				.append('\n');
		sb.append("<b>Letture Chat Cache riuscite</b>\n  ")
				.append(chatCache.stats().loadSuccessCount())
				.append('\n');
		sb.append("<b>Tempo lettura Chat Cache</b>\n  ")
				.append(chatCache.stats().totalLoadTime())
				.append('\n');
		sb.append("\nPer il dump della chat cache digita: /getellieinfo chatcache");

		sendMessageHTML(chatID, sb.toString());
		
		return 0;
	}
	
	private int getInfoCacheFlow() {
		final StringBuffer sb = new StringBuffer("<b>MAPPA DI SESSIONE</b>\n");
		chatCache.asMap().entrySet().forEach(e ->
			sb.append("\n<i>").append(e.getKey()).append("</i>  ")
					.append(e.getValue()).append('\n')
		);
		sendMessageHTML(chatID, sb.toString());
		return 0;
	}
}
