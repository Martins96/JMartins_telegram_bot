package com.lucamartinelli.telegram.bot.vo.weather;

import java.util.Objects;

public class WeatherDayVO {
	
	private String day;
	private String forecast;
	private int minTemp;
	private int maxTemp;
	private String percRain;
	
	public WeatherDayVO() {
	}

	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}

	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public int getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(int minTemp) {
		this.minTemp = minTemp;
	}

	public int getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(int maxTemp) {
		this.maxTemp = maxTemp;
	}

	public String getPercRain() {
		return percRain;
	}
	public void setPercRain(String percRain) {
		this.percRain = percRain;
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, forecast, maxTemp, minTemp, percRain);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherDayVO other = (WeatherDayVO) obj;
		return Objects.equals(day, other.day) && Objects.equals(forecast, other.forecast) && maxTemp == other.maxTemp
				&& minTemp == other.minTemp && Objects.equals(percRain, other.percRain);
	}

	@Override
	public String toString() {
		return "WeatherDayVO [day=" + day + ", forecast=" + forecast + ", minTemp=" + minTemp + ", maxTemp=" + maxTemp
				+ ", percRain=" + percRain + "]";
	}

	
	
	
}
