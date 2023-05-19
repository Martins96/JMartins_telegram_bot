package com.lucamartinelli.telegram.bot.commands.weather;

import org.jboss.logging.Logger;

public class CitySelector {
	private final static Logger log = Logger.getLogger(CitySelector.class);;
	
	private CitySelector(String citta) {
	}
	
	public static int getCityId(String citta) {
		if (citta == null || citta.isEmpty()) {
			log.warn("City is null, not manageable");
		}
		
		switch (citta.toLowerCase()) {
		case "milano":
			return 4074;
		case "roma":
			return 5913;
		case "venezia":
			return 7729;
		case "torino":
			return 7301;
		case "verona":
			return 7766;
		case "napoli":
			return 4579;
		case "bari":
			return 532;
		case "cagliari":
			return 1039;
		case "palermo":
			return 4937;
		case "bergamo":
			return 641;
		case "brescia":
			return 907;
		default:
			log.debug("City " + citta + " not found in list");
			return -1;
		}
		
	}
}
