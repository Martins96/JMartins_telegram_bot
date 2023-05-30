package com.lucamartinelli.telegram.bot.commands.user.mail;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;

public class MailingCommand extends BotCommand {
	
	private int stepNum;
	private long destinationID;
	private String mailMessage;
	private static final String WELCOME_MSG = "<b>Postino</b>\n"
			+ "Hai avviato il comando postino, manderò un messaggio "
			+ "da parte tua a un utente.\n"
			+ "Puoi uscire da questo comando scrivendo /exit\n"
			+ "Per prima cosa dimmi a chi devo scrivere? <i>(scrivi <b>l'ID</b> dell'utente)</i>\n"
			+ "\nEcco alcuni suggerimenti:\n"
			+ "/115949778 <i>(Papà - Luca)</i>\n"
			+ "/240650708 <i>(Mamma - Ale)</i>\n"
			+ "/76891271  <i>(Matteo)</i>\n"
			+ "/104412734 <i>(Vale)</i>\n"
			+ "/148278619 <i>(Dinu)</i>\n"
			+ "/180117484 <i>(Calvi)</i>\n"
			+ "/124796388 <i>(Gaia)</i>";
	
	

	public MailingCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
		this.stepNum = 0;
	}

	@Override
	protected int run() {
		log.debug("Starting postino command");
		if (chatSession.getLoggedinRole() == null) {
			log.debugf("User [%s] is not logged in", update.message().from().username());
			sendMessage(chatID, "Per eseguire questo comando devi aver effettuato l'accesso\n"
					+ "Loggati usando /user o /admin o /mylady");
			return 10;
		}
		
		sendMessageHTML(chatID, WELCOME_MSG);
		this.stepNum = 1;
		chatSession.setCommandFlowIncomplete(true);
		chatSession.setProcessingCommand(this);
		
		return 0;
	}
	
	@Override
	public int resumeExecution(Update newUpdate) {
		if (newUpdate == null || newUpdate.message() == null 
				|| newUpdate.message().text() == null
				|| newUpdate.message().text().isEmpty()) {
			sendMessage(chatID, "Input non gestibile, termino il comando Postino");
			log.debug("Input not manageable for postino command, exiting from command");

			chatSession.resetIncompleteCommand();
			return 1;
		}
		
		String input = newUpdate.message().text();
		log.debug("Input for mailing is: " + input);
		log.debug("Continue mailing at step: " + stepNum);
		
		if (input.equalsIgnoreCase("/exit")) {
			log.debug("User asked to exit to command, turning off mailing");
			sendMessage(chatID, "Richiesta di uscita dal comando, termino 'postino'");
			this.chatSession.resetIncompleteCommand();
			return 0;
		}
		
		if (input.startsWith("/")) {
			input = input.substring(1);
		}
		int ret;
		if (stepNum == 1) {
			ret = setDestinationAndUpdate(input);
		} else if (stepNum == 2) {
			ret = setTextAndSend(input);
		} else {
			log.warn("Step num is " + stepNum + " not manageable, blocking command");
			sendMessage(chatID, "Aia, per qualche ragione ho perso il filo, termino il comando");
			chatSession.resetIncompleteCommand();
			return 5;
		}
		
		
		return ret;
	}

	private int setTextAndSend(String input) {
		this.mailMessage = input;
		log.debug("Ready to send the message");
		
		sendMessage(chatID, "Mando il messaggio a " + this.destinationID);
		sendMessage(this.destinationID, 
				new StringBuffer("Hey, ")
						.append(update.message().from().username())
						.append(" mi ha detto di dirti questo:\n\n")
						.append(this.mailMessage).toString());
		log.debug("Message sent to " + this.destinationID);
		sendMessage(chatID, "Messaggio inviato, fine del comando");
		chatSession.resetIncompleteCommand();
		return 0;
	}

	private int setDestinationAndUpdate(String input) {
		try {
			log.debug("Parsing input ID");
			final long dest = Long.parseLong(input);
			log.debug("Input ID parsed as Long");
			this.destinationID = dest;
			this.stepNum = 2;
			this.chatSession.setProcessingCommand(this);
			sendMessage(chatID, "Bene, adesso mandami il messaggio che devo recapitare "
					+ "(Aggiungerò che il mittente sei tu)\n"
					+ "Se vuoi terminare il messaggio senza mandare niente digita /exit");
			log.debug("Step complete, continue for next step");
			return 0;
		} catch (NumberFormatException e) {
			log.info("User not send an ID valid for postino command");
			sendMessage(chatID, "Mi dispiace ma l'ID che hai mandato non è valido, per favore"
					+ " manda un ID corretto (Oppure termina il comando con /exit)");
			return 2;
		}
	}

}
