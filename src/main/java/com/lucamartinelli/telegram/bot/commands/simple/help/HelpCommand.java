package com.lucamartinelli.telegram.bot.commands.simple.help;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.constants.Messages;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

import static com.lucamartinelli.telegram.bot.commands.simple.help.HelpMessages.*;

public class HelpCommand extends BotCommand {

	public HelpCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		
		final String[] splitted = text.split(" ", 2);
		
		if (splitted.length > 1 && splitted[1] != null && !splitted[1].isEmpty()) {
			cmdDetails(splitted[1].toLowerCase());
		} else {
			sendMessage(chatID, Messages.HELP);
			sendMessage(chatID, "Puoi inoltre avere il dettaglio del comando con un esempio "
					+ "digitando /help [comando]\n"
					+ "Ad esempio /help botinfo");
		}
		return 0;
	}
	
	private void cmdDetails(String command) {
		final String msg;
		switch (command) {
		case ("/test"):
			msg = TEST;
			break;
	
		case ("/start"):
			msg = START;
			break;
	
		case ("/personalinfo"):
			msg = PERSONALINFO;
			break;
	
		case ("/help"):
			msg = HELP;
			break;
	
		case ("/botinfo"):
			msg = BOTINFO;
			break;
	
		case ("/asciiart"):
			msg = ASCIIART;
			break;
	
		case ("/battuta"):
			msg = BATTUTA;
			break;
	
		case ("/perla"):
			msg = PERLA;
			break;
	
		case ("/sticker"):
			msg = STICKER;
			break;
	
		case ("/ora"):
			msg = ORA;
			break;
	
		case ("/calc"):
			msg = CALC;
			break;
	
		case ("/+"):
			msg = MATH_SUM;
			break;
	
		case ("/-"):
			msg = MATH_DIFF;
			break;
	
		case ("/x"):
		case ("/*"):
			msg = MATH_PRODUCT;
			break;
	
		case ("/:"):
		case ("//"):
			msg = MATH_DIV;
			break;
	
		case ("/sqrt"):
			msg = SQRT;
			break;
	
		case ("/rand"):
			msg = RAND;
			break;
	
		case ("/media"):
			msg = MEDIA;
			break;
	
		case ("/letterarand"):
			msg = LETTERARAND;
			break;
	
		case ("/moneta"):
			msg = MONETA;
			break;
			
		case ("/dado"):
			msg = DADO;
			break;
			
		case ("/logout"):
			msg = LOGOUT;
			break;
	
		case ("/user"): 
			msg = USER;
			break;
	
		case ("/userhelp"):
			msg = USERHELP;
			break;
			
	
		case ("/mylady"): 
			msg = MYLADY;
			break;
	
		case ("/myladyhelp"):
			msg = MYLADYHELP;
			break;
	
		case ("/myladyimage"):
			msg = MYLADYIMAGE;
			break;
	
		case ("/love"):
			msg = LOVE;
			break;
	
		case ("/admin"): 
			msg = ADMIN;
			break;
	
		case ("/adminhelp"):
			msg = ADMINHELP;
			break;
	
		case ("/shutdown"):
			msg = SHUTDOWN;
			break;
	
		case ("/postino"): 
			msg = POSTINO;
			break;
	
		case ("/system"): 
			msg = SYSTEM;
			break;
	
		case ("/getlog"):
			msg = GETLOG;
			break;
	
		case ("/getellieinfo"):
			msg = GETELLIEINFO;
			break;
	
		case ("/startrandomevent"):
	
		case ("/shared"):
	
		case ("/meteo"):
	
		case ("/storia"):
	
		case ("/manual"):
	
		case ("/impiccato"):
	
		case ("/blackjack"):
	
		case ("/battaglianavale"):
	
		case ("/sassocartaforbice"):
	
		case ("/catsimulator"):
	
		case ("/storygame"):
	
		case ("/foto"):
	
		case ("/musica"):
	
		default:
			msg = String.format(DEFAULT, command);
		}
		
		sendMessageHTML(chatID, msg);
	}

}
