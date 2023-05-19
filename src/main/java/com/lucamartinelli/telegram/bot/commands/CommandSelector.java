package com.lucamartinelli.telegram.bot.commands;

import com.lucamartinelli.telegram.bot.commands.admin.help.AdminHelpCommand;
import com.lucamartinelli.telegram.bot.commands.admin.login.LoginAdmin;
import com.lucamartinelli.telegram.bot.commands.calc.CalcCommand;
import com.lucamartinelli.telegram.bot.commands.calc.DiffCommand;
import com.lucamartinelli.telegram.bot.commands.calc.DivCommand;
import com.lucamartinelli.telegram.bot.commands.calc.MidCommand;
import com.lucamartinelli.telegram.bot.commands.calc.MultipCommand;
import com.lucamartinelli.telegram.bot.commands.calc.RandCommand;
import com.lucamartinelli.telegram.bot.commands.calc.SqrtCommand;
import com.lucamartinelli.telegram.bot.commands.calc.SumCommand;
import com.lucamartinelli.telegram.bot.commands.media.asciiart.AsciiArtCommand;
import com.lucamartinelli.telegram.bot.commands.mylady.help.MyLadyHelpCommand;
import com.lucamartinelli.telegram.bot.commands.mylady.login.LoginMyLady;
import com.lucamartinelli.telegram.bot.commands.mylady.love.LoveCommand;
import com.lucamartinelli.telegram.bot.commands.mylady.media.MyLadyImageCommand;
import com.lucamartinelli.telegram.bot.commands.simple.def.DefaultCommand;
import com.lucamartinelli.telegram.bot.commands.simple.help.HelpCommand;
import com.lucamartinelli.telegram.bot.commands.simple.info.InfoCommand;
import com.lucamartinelli.telegram.bot.commands.simple.pinfo.PersonalInfoCommand;
import com.lucamartinelli.telegram.bot.commands.simple.start.StartCommand;
import com.lucamartinelli.telegram.bot.commands.simple.sticker.StickerCommand;
import com.lucamartinelli.telegram.bot.commands.simple.test.TestCommand;
import com.lucamartinelli.telegram.bot.commands.simple.time.TimeCommand;
import com.lucamartinelli.telegram.bot.commands.random.AphorismCommand;
import com.lucamartinelli.telegram.bot.commands.random.CoinCommand;
import com.lucamartinelli.telegram.bot.commands.random.DiceCommand;
import com.lucamartinelli.telegram.bot.commands.random.JokeCommand;
import com.lucamartinelli.telegram.bot.commands.random.RandCharCommand;
import com.lucamartinelli.telegram.bot.commands.user.login.LogoutCommand;
import com.lucamartinelli.telegram.bot.commands.user.login.UserLoginCommand;
import com.lucamartinelli.telegram.bot.commands.weather.WeatherCommand;
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
        		command = new StickerCommand(session, update);
        		break;

        	case ("/ora"):
        		command = new TimeCommand(session, update);
        		break;

        	case ("/calc"):
        		command = new CalcCommand(session, update);
    			break;

        	case ("/+"):
        		command = new SumCommand(session, update);
				break;

        	case ("/-"):
        		command = new DiffCommand(session, update);
				break;
        
        	case ("/x"):
        	case ("/*"):
        		command = new MultipCommand(session, update);
				break;
        
        	case ("/:"):
        	case ("//"):
        		command = new DivCommand(session, update);
				break;
        
        	case ("/sqrt"):
        		command = new SqrtCommand(session, update);
				break;
        
        	case ("/rand"):
        		command = new RandCommand(session, update);
				break;
        
        	case ("/media"):
        		command = new MidCommand(session, update);
				break;
        
        	case ("/letterarand"):
        		command = new RandCharCommand(session, update);
        		break;
        
        	case ("/moneta"):
        		command = new CoinCommand(session, update);
    			break;
    			
        	case ("/dado"):
        		command = new DiceCommand(session, update);
        		break;
        		
        	case ("/logout"):
        		command = new LogoutCommand(session, update);
        		break;

        	case ("/user"): 
        		command = new UserLoginCommand(session, update);
        		break;

        	case ("/userhelp"):
        		
        
        	case ("/mylady"): 
        		command = new LoginMyLady(session, update);
        		break;

        	case ("/myladyhelp"):
        		command = new MyLadyHelpCommand(session, update);
    			break;
        
        	case ("/myladyimage"):
        		command = new MyLadyImageCommand(session, update);
				break;

        	case ("/love"):
        		command = new LoveCommand(session, update);
        		break;
        
        	case ("/admin"): 
        		command = new LoginAdmin(session, update);
        		break;

        	case ("/adminhelp"):
        		command = new AdminHelpCommand(session, update);
				break;

        	case ("/adminimage"): 

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
        		command = new WeatherCommand(session, update);
        		break;
        
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
        		command = new DefaultCommand(session, update);
        	}
        
        return command;
	}

}
