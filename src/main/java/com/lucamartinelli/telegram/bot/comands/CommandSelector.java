package com.lucamartinelli.telegram.bot.comands;

import com.lucamartinelli.telegram.bot.comands.media.asciiart.AsciiArtCommand;
import com.lucamartinelli.telegram.bot.comands.random.AphorismCommand;
import com.lucamartinelli.telegram.bot.comands.random.CoinCommand;
import com.lucamartinelli.telegram.bot.comands.random.DiceCommand;
import com.lucamartinelli.telegram.bot.comands.random.JokeCommand;
import com.lucamartinelli.telegram.bot.comands.simple.def.DefaultCommand;
import com.lucamartinelli.telegram.bot.comands.simple.help.HelpCommand;
import com.lucamartinelli.telegram.bot.comands.simple.info.InfoCommand;
import com.lucamartinelli.telegram.bot.comands.simple.pinfo.PersonalInfoCommand;
import com.lucamartinelli.telegram.bot.comands.simple.start.StartCommand;
import com.lucamartinelli.telegram.bot.comands.simple.test.TestCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;


public class CommandSelector {
	
	public static BotCommand getCommand(Update update, ChatSession session) {
		if (update == null || update.message() == null) {
			throw new RuntimeException("Message null, not manageable");
		} else if (update.message().from() == null || update.message().from().id() == null) {
			throw new RuntimeException("User not present, cannot do nothing");
		} else if (update.message().text() == null || update.message().text().isBlank()) {
			new DefaultCommand(null, update);
		}
		
		final String mex = update.message().text();
		final String[] cmd = mex.split(" ", 2);

		cmd[0] = cmd[0].toLowerCase();
		final BotCommand command;
		
        switch (cmd[0]) {
        	case ("/test"):
        		command = new TestCommand(session, update);
        		break;
        
        	case ("/start"):
        		command = new StartCommand(session, update);
    			break;

        	case ("/personalinfo"):
        		command = new PersonalInfoCommand(session, update);
				break;

        	case ("/help"):
        		command = new HelpCommand(session, update);
				break;

        	case ("/botinfo"):
        		command = new InfoCommand(session, update);
    			break;

        	case ("/asciiart"):
        		command = new AsciiArtCommand(session, update);
				break;

        	case ("/battuta"):
        		command = new JokeCommand(session, update);
				break;

        	case ("/perla"):
        		command = new AphorismCommand(session, update);
        		break;

        	case ("/sticker"):

        	case ("/ora"):

        	case ("/calc"):

        	case ("/+"):

        	case ("/-"):
        
        	case ("/x"):
        	case ("/*"):
        
        	case ("/:"):
        	case ("//"):
        
        	case ("/sqrt"):
        
        	case ("/rand"):
        
        	case ("/media"):
        
        	case ("/letterarand"):
        
        	case ("/moneta"):
        		command = new CoinCommand(session, update);
    			break;
    			
        	case ("/dado"):
        		command = new DiceCommand(session, update);
        		break;

        	case ("/user"): 

        	case ("/userhelp"):
        
        	case ("/userexit"):
        
        	case ("/mylady"): 

        	case ("/myladyhelp"):
        
        	case ("/myladyimage"):

        	case ("/myladyexit"):

        	case ("/love"):
        
        	case ("/admin"): 

        	case ("/adminhelp"):

        	case ("/hddlist"):

        	case ("/adminimage"): 

        	case ("/adminexit"):

        	case ("/shutdown"):
        
        	case ("/cloud"):

        	case ("/resettimeout"): 

        	case ("/postino"): 

        	case ("/system"): 
        
        	case ("/upgrade"):
        
        	case ("/getlog"):
        
        	case ("/getellieinfo"):
        
        	case ("/threadattivi"):
        
        	case ("/startrandomevent"):
        
        	case ("/shared"):
        
        	case ("/meteo"):
        
        	case ("/storia"):
        
        	case ("/manual"):
        
        	case ("/impiccato"):
        
        	case ("/blackjack"):
        
        	case ("/battaglianavale"):
        
        	case ("/sassocartaforbice"):

        	case("/catsimulator"):

        	case ("/storygame"):
        
        	case ("/foto"):
        
        	case ("/musica"):

        	default:
        		command = new DefaultCommand(session, update);
        	}
        
        return command;
	}

}
