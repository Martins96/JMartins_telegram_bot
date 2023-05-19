package com.lucamartinelli.telegram.bot.commands.mylady.help;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.constants.Messages;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.lucamartinelli.telegram.bot.vo.LoggedRolesEnum;
import com.pengrad.telegrambot.model.Update;

public class MyLadyHelpCommand extends BotCommand {

	public MyLadyHelpCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("My Lady Help requested, checking logged session");
		if (chatSession.getLoggedinRole() != LoggedRolesEnum.MYLADY) {
			log.infof("User with ID [%s] tried to invoke Mylady help command without "
					+ "be logged in", Long.toString(chatID));
			sendMessage(chatID, "Non ti ho ancora autorizzato ad usare questo comando, prima "
					+ "autenticati con il comando /mylady");
			return 1;
		}
		log.debug("User is logged as MyLady,sending information");
		
		sendMessage(chatID, Messages.MYLADY_HELP);
		return 0;
	}

}
