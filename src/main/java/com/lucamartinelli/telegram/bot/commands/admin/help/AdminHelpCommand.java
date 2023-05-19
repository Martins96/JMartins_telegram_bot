package com.lucamartinelli.telegram.bot.commands.admin.help;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.constants.Messages;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.lucamartinelli.telegram.bot.vo.LoggedRolesEnum;
import com.pengrad.telegrambot.model.Update;

public class AdminHelpCommand extends BotCommand {

	public AdminHelpCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("Checking if user is already logged");
		final LoggedRolesEnum role = chatSession.getLoggedinRole();
		if (role != null && role.equals(LoggedRolesEnum.ADMIN)) {
			log.debugf("User allowed to invoke this admin command");
			sendMessage(chatID, Messages.ADMIN_HELP);
			return 0;
			
		} else {
			log.debug("User not allowed to invoke this command");
			sendMessage(chatID, "Scusami, ma questo comando è riservato ad un Admin."
					+ "\nAutenticati usando /admin");
			return 1;
		}
	}

}
