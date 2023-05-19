package com.lucamartinelli.telegram.bot.commands.weather;

import org.apache.commons.lang3.math.NumberUtils;
import org.jboss.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lucamartinelli.telegram.bot.vo.weather.WeatherDayVO;
import com.lucamartinelli.telegram.bot.vo.weather.WeatherForecastVO;

public class WeatherEngine {
	
	private final Logger log;
	private final static String URL_TEMPLATE = "https://www.ilmeteo.it/box/previsioni.php?citta=%d&type=day1&width=400&ico=1&lang=ita&days=6";
	private final String url;
	private final String citta;
	private final int cittaCode;
	
	public WeatherEngine(String citta, int cittaCode) {
		log = Logger.getLogger(this.getClass());
		this.citta = citta;
		this.cittaCode = cittaCode;
		this.url = String.format(URL_TEMPLATE, this.cittaCode);
	}
	
	public WeatherEngine(String citta) {
		log = Logger.getLogger(this.getClass());
		this.citta = citta;
		this.cittaCode = CitySelector.getCityId(citta);
		this.url = String.format(URL_TEMPLATE, this.cittaCode);
	}

	public WeatherForecastVO getForecast() {
		
		log.debug("Connecting for weather info to URL: " + url);
		Connection connection = Jsoup.connect(url)
				.header("Accept", "text/html")
				.header("Pragma", "no-cache")
				.header("Accept-Encoding", "gzip, deflate, br")
				.userAgent("Mozilla/5.0")
				.timeout(5000);
		log.debug("Ready to execute call");
		try {
			final WeatherForecastVO forecastVO = new WeatherForecastVO();
			final Document html = connection.get();
			log.infof("Receinved document for city %s", this.citta);
			
			log.debug("Managing document with charset " + html.charset());
			final Elements rows = html.getElementsByTag("tbody").get(0).getElementsByTag("tr");
			log.debug("Managing today");
			WeatherDayVO today = generateDay(rows.get(1));
			log.debug("Managing tomorrow");
			WeatherDayVO tomorrow = generateDay(rows.get(3));
			log.debug("Managing after tomorrow");
			WeatherDayVO afterTomorrow = generateDay(rows.get(5));
			forecastVO.setToday(today);
			forecastVO.setTomorrow(tomorrow);
			forecastVO.setAfterTomorrow(afterTomorrow);
			forecastVO.setLocation(citta);

			log.debug("Forecast ready: " + forecastVO);
			return forecastVO;
			
		} catch (Exception e) {
			log.error("Error managing HTML document", e);
			throw new RuntimeException(e);
		}
	}
	
	private WeatherDayVO generateDay(Element tableRow) {
		if (tableRow == null)
			return null;
		
		final WeatherDayVO dayVO = new WeatherDayVO();
		
		log.debug("Managing day name");
		final String dayText = new String(tableRow.getElementsByClass("day")
				.get(0).text())
				.replace("\\&nbsp;", " ");
		dayVO.setDay(dayText);
		log.debug("Day name is " + dayText);
		
		
		log.debug("Managing temperatures");
		final String tmin = tableRow.getElementsByClass("tMin").get(0).text();
		final String tmax = tableRow.getElementsByClass("tMax").get(0).text();
		if (NumberUtils.isCreatable(tmin))
			dayVO.setMinTemp(NumberUtils.toInt(tmin));
		if (NumberUtils.isCreatable(tmax))
			dayVO.setMaxTemp(NumberUtils.toInt(tmax));
		log.debugf("Temperature min [%s] max [%s]", tmin, tmax);
		

		log.debug("Managing forecast");
		final String forecast = new String(tableRow.getElementsByClass("dati").get(1)
				.getElementsByTag("img")
				.get(0)
				.attr("alt"));
		dayVO.setForecast(forecast);
		log.debug("Forecast found: " + forecast);
		

		log.debug("Managing rain precipitations precent");
		String percRain;
		final Elements fromFont = tableRow.getElementsByTag("tbody").get(0)
				.getElementsByTag("font");
		
		if (fromFont.size() > 0) {
			percRain = fromFont.get(0)
					.text().split("\\&nbsp;", 2)[0];
		} else {
			percRain = tableRow.getElementsByTag("tbody").get(0)
					.getElementsByTag("td")
					.get(1)
					.text().split("\\&nbsp;", 2)[0];
		}
		
		
		dayVO.setPercRain(percRain);
		log.debug("Rain percent " + percRain);
		
		
		return dayVO;
	}
		

}
