package com.lucamartinelli.telegram.bot.commands.weather;

import com.lucamartinelli.telegram.bot.commands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.lucamartinelli.telegram.bot.vo.weather.WeatherDayVO;
import com.lucamartinelli.telegram.bot.vo.weather.WeatherForecastVO;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

public class WeatherCommand extends BotCommand {

	public WeatherCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
	}

	@Override
	protected int run() {
		log.debug("Weather command started");
		
		
		sendMessage(chatID, "Funzione di previsioni meteo\n"
				+ "Seleziona una città:\n"
				+ "/Milano\n"
				+ "/Roma\n"
				+ "/Venezia\n"
				+ "/Verona\n"
				+ "/Torino\n"
				+ "/Napoli\n"
				+ "/Bari\n"
				+ "/Cagliari\n"
				+ "/Palermo\n"
				+ "/Bergamo\n"
				+ "/Brescia");
		chatSession.setCommandFlowIncomplete(true);
		chatSession.setProcessingCommand(this);
		return 0;
	}
	
	
	
	@Override
	public int resumeExecution(Update newUpdate) {
		log.debug("Resuming weather command");
		if (newUpdate == null || newUpdate.message() == null || newUpdate.message().text() == null) {
			sendMessage(chatID, "Input non gestibile, per favore seleziona una città dell'elenco,"
					+ " se vuoi terminare il comando digita /exit\n"
					+ "/Milano\n"
					+ "/Roma\n"
					+ "/Venezia\n"
					+ "/Verona\n"
					+ "/Torino\n"
					+ "/Napoli\n"
					+ "/Bari\n"
					+ "/Cagliari\n"
					+ "/Palermo\n"
					+ "/Bergamo\n"
					+ "/Brescia");
			log.debug("Input not manageable for Weather command");
			return 10;
		}
		
		String input = newUpdate.message().text();
		log.debug("Input for resuming weather is " + input);
		if ("/exit".equalsIgnoreCase(input)) {
			log.debug("Exit requested, make end this command");
			sendMessage(chatID, "Richiesta uscita dal comando, termino l'esecuzione del meteo");
			chatSession.resetIncompleteCommand();
			return 1;
		}
		
		if (input.startsWith("/") || input.startsWith("\\")) {
			input = input.substring(1); 
		}
		
		final int cityCode = CitySelector.getCityId(input);
		if (cityCode == -1) {
			sendMessage(chatID, "Città non trovata, per favore seleziona una città dell'elenco,"
					+ " se vuoi terminare il comando digita /exit\n"
					+ "/Milano\n"
					+ "/Roma\n"
					+ "/Venezia\n"
					+ "/Verona\n"
					+ "/Torino\n"
					+ "/Napoli\n"
					+ "/Bari\n"
					+ "/Cagliari\n"
					+ "/Palermo\n"
					+ "/Bergamo\n"
					+ "/Brescia");
			log.debug("City " + input + " not found in city list");
			return 10;
		}
		
		final WeatherForecastVO forecast = new WeatherEngine(input).getForecast();
		
		StringBuffer sb = new StringBuffer();
		
		log.debug("Generating message for output");
		sb.append("Previsioni meteo per " + input + "\n");
		printDay(sb, forecast.getToday());
		sb.append('\n');
		printDay(sb, forecast.getTomorrow());
		sb.append('\n');
		printDay(sb, forecast.getAfterTomorrow());
		
		log.debug("Message ready");
		SendMessage request = new SendMessage(chatID, sb.toString())
	            .parseMode(ParseMode.HTML);
		log.debug("Sending message " + request.toString());
		this.ellie.execute(request);
		
		chatSession.resetIncompleteCommand();
		return 0;
	}

	private void printDay(StringBuffer sb, final WeatherDayVO day) {
		String minEmoji, maxEmoji;
		if (day.getMinTemp() < 18) {
			minEmoji = "❄️";
		} else {
			minEmoji = "🔥";
		}
		if (day.getMaxTemp() < 18) {
			maxEmoji = "❄️";
		} else {
			maxEmoji = "🔥";
		}
		
		
		sb.append("<u>").append(day.getDay() + "</u>\n");
		sb.append("<b>" + day.getForecast()).append("</b>\n");
		sb.append("Temperatura minima <b>").append(day.getMinTemp())
				.append(minEmoji).append("</b>\n");
		sb.append("Temperatura massima <b>").append(day.getMaxTemp())
				.append(maxEmoji).append("</b>\n");
		sb.append("Probabilità precipitazioni <b>").append(day.getPercRain())
				.append("💧").append("</b>\n");
	}

}
