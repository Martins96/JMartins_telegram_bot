package com.lucamartinelli.telegram.bot.commands.games.morra;

import java.util.Random;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class MorraCommand extends BotCommand {

	private boolean inGame;
	public MorraCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
		this.inGame = false;
	}

	@Override
	protected int run() {
		log.debug("Starting rock paper scissors game");
		sendMessage(chatID, "Benvenuto nel gioco di \"Carta Forbici o Sasso\"\n"
				+ "Scegli una delle opzioni e vediamo se mi batti:"
				+ "\n/carta"
				+ "\n\n/sasso"
				+ "\n\n/forbici");
		this.inGame = true;
		this.chatSession.setCommandFlowIncomplete(true);
		this.chatSession.setProcessingCommand(this);
		return 0;
	}
	
	@Override
	public int resumeExecution(Update newUpdate) {
		if (newUpdate == null || newUpdate.message() == null || newUpdate.message().text() == null) {
			log.debug("Input not valid for game morra, exiting...");
			sendMessage(chatID, "Ciò che hai mandato non è valido per il gioco, termino il comando");
			chatSession.resetIncompleteCommand();
			return 10;
		}
		
		String input = newUpdate.message().text().toLowerCase();
		if (input.equals("/exit")) {
			log.debug("Exiting game by requested");
			sendMessage(chatID, "Richiesta di uscita dal gioco, termino il comando");
			this.chatSession.resetIncompleteCommand();
			return 0;
		}
		
		
		if (input.startsWith("/")) {
			input = input.substring(1);
		}
		
		if (this.inGame) {
			return startGame(input);
		} else {
			return checkForNewGame(input);
		}
		
	}
	
	
	private int startGame(String input) {
		
		int ret;
		if (input.equals("sasso")) {
			ret = checkAndSendRock(new Random().nextInt(3));
		} else if (input.equals("carta")) {
			ret = checkAndSendPaper(new Random().nextInt(3));
		} else if (input.equals("forbici")) {
			ret = checkAndSendScissors(new Random().nextInt(3));
		}
		
		/* Easter Egg */
		else if (input.equals("lucertola") || input.equals("lizard")) {
			ret = checkAndSendLizard(new Random().nextInt(5));
		} else if (input.equals("spock")) {
			ret = checkAndSendSpock(new Random().nextInt(5));
		}
		/**************/
		
		else {
			log.debug("Invalid input: " + input);
			sendMessage(chatID, "Scusa, ma l'input che hai mandato non è accettabile, seleziona:\n"
					+ "/sasso\n\n"
					+ "/carta\n\n"
					+ "/forbici\n\n"
					+ "Oppure digita /exit per terminare il gioco");
			ret = 2;
		}
		
		if (ret == 0) {
			sendMessage(chatID, "Vuoi giocare ancora?\n/Y\n\n/N");
			this.inGame = false;
		}
		return ret;
	}

	
	private int checkForNewGame(String input) {
		if (input.equals("y")) {
			sendMessage(chatID, "Altro giro!\nSeleziona:\n\n"
					+ "/sasso\n\n"
					+ "/carta\n\n"
					+ "/forbici\n\n");
			this.inGame = true;
		} else if (input.equals("n")) {
			sendMessage(chatID, "Uscita richiesta, termino il comando");
			this.chatSession.resetIncompleteCommand();
		} else {
			sendMessage(chatID, "Input non accettabile, per favore invia\n/Y\n\n/N");
			return 10;
		}
		
		return 0;
	}
	
	
	
	private int checkAndSendRock(int ellieChoice) {
		log.debug("Managing ROCK");
		if (ellieChoice == 0) {
			log.debug("Ellie choose 'ROCK'");
			sendMessage(chatID, "🪨");
			sendMessageHTML(chatID, "<b>Pareggio</b> perché ho scelto sasso anche io");
		} else if (ellieChoice == 1) {
			log.debug("Ellie choose 'PAPER'");
			sendMessage(chatID, "📄");
			sendMessageHTML(chatID, "Hai <b>perso</b> perché la carta avvolge il sasso");
		} else {
			log.debug("Ellie choose 'SCISSORS'");
			sendMessage(chatID, "✂️");
			sendMessageHTML(chatID, "Hai <b>vinto</b> perché il sasso rompe le forbici");
		}
		return 0;
	}

	private int checkAndSendPaper(int ellieChoice) {
		log.debug("Managing PAPER");
		if (ellieChoice == 0) {
			log.debug("Ellie choose 'ROCK'");
			sendMessage(chatID, "🪨");
			sendMessageHTML(chatID, "Hai <b>vinto</b> perché la carta avvolge il sasso");
		} else if (ellieChoice == 1) {
			log.debug("Ellie choose 'PAPER'");
			sendMessage(chatID, "📄");
			sendMessageHTML(chatID, "<b>Pareggio</b> perché ho scelto carta anche io");
		} else {
			log.debug("Ellie choose 'SCISSORS'");
			sendMessage(chatID, "✂️");
			sendMessageHTML(chatID, "Hai <b>perso</b> perché le forbici tagliano la carta");
		}
		return 0;
	}

	private int checkAndSendScissors(int ellieChoice) {
		log.debug("Managing SCISSORS");
		if (ellieChoice == 0) {
			log.debug("Ellie choose 'ROCK'");
			sendMessage(chatID, "🪨");
			sendMessageHTML(chatID, "Hai <b>perso</b> perché il sasso rompe le forbici");
		} else if (ellieChoice == 1) {
			log.debug("Ellie choose 'PAPER'");
			sendMessage(chatID, "📄");
			sendMessageHTML(chatID, "Hai <b>vinto</b> perché le forbici tagliano la carta");
		} else {
			log.debug("Ellie choose 'SCISSORS'");
			sendMessage(chatID, "✂️");
			sendMessageHTML(chatID, "<b>Pareggio</b> ho scelto forbici anche io");
		}
		return 0;
	}

	private int checkAndSendLizard(int ellieChoice) {
		log.debug("Easter egg triggered, managing LIZARD");
		if (ellieChoice == 0) {
			log.debug("Ellie choose 'ROCK'");
			sendMessage(chatID, "🪨");
			sendMessageHTML(chatID, "Hey, questo è un gioco diverso!\n"
					+ "Comunque hai <b>perso</b> perchè la lucertola viene schiacciata dal sasso");
		} else if (ellieChoice == 1) {
			log.debug("Ellie choose 'PAPER'");
			sendMessage(chatID, "📄");
			sendMessageHTML(chatID, "Hey, questo è un gioco diverso!\n"
					+ "Comunque hai <b>vinto</b> perchè la lucertola mangia la carta");
		} else if (ellieChoice == 2) {
			log.debug("Ellie choose 'SCISSORS'");
			sendMessage(chatID, "✂️");
			sendMessageHTML(chatID, "Hey, questo è un gioco diverso!\n"
					+ "Comunque hai <b>perso</b> perchè la lucertola viene decapitata dalle forbici");
		} else if (ellieChoice == 3) {
			log.debug("Ellie choose 'LIZARD'");
			sendMessage(chatID, "🦎");
			sendMessageHTML(chatID, "Hey, questo è un gioco diverso!\n"
					+ "Comunque è un <b>pareggio</b> perchè ho scelto lucertola anche io");
		} else {
			log.debug("Ellie choose 'SPOCK'");
			sendMessage(chatID, "🖖");
			sendMessageHTML(chatID, "Hey, questo è un gioco diverso!\n"
					+ "Comunque hai <b>vinto</b> perchè Spock è avvelenato dalla lucertola");
		}
		
		
		return 0;
	}

	private int checkAndSendSpock(int ellieChoice) {
		log.debug("Easter egg triggered, managing SPOCK");
		if (ellieChoice == 0) {
			log.debug("Ellie choose 'ROCK'");
			sendMessage(chatID, "🪨");
			sendMessageHTML(chatID, "Hey, questo è un gioco diverso!\n"
					+ "Comunque hai <b>vinto</b> perchè Spock vaporizza il sasso");
		} else if (ellieChoice == 1) {
			log.debug("Ellie choose 'PAPER'");
			sendMessage(chatID, "📄");
			sendMessageHTML(chatID, "Hey, questo è un gioco diverso!\n"
					+ "Comunque hai <b>perso</b> perchè Spock viene invalidato dalla carta");
		} else if (ellieChoice == 2) {
			log.debug("Ellie choose 'SCISSORS'");
			sendMessage(chatID, "✂️");
			sendMessageHTML(chatID, "Hey, questo è un gioco diverso!\n"
					+ "Comunque hai <b>vinto</b> perchè Spock rompe le forbici");
		} else if (ellieChoice == 3) {
			log.debug("Ellie choose 'LIZARD'");
			sendMessage(chatID, "🦎");
			sendMessageHTML(chatID, "Hey, questo è un gioco diverso!\n"
					+ "Comunque hai <b>perso</b> perchè Spock viene avvelenato dalla lucertola");
		} else {
			log.debug("Ellie choose 'SPOCK'");
			sendMessage(chatID, "🖖");
			sendMessageHTML(chatID, "Hey, questo è un gioco diverso!\n"
					+ "Comunque è un <b>pareggio</b> perchè ho scelto Spock anche io");
		}
		
		
		return 0;
	}
}
