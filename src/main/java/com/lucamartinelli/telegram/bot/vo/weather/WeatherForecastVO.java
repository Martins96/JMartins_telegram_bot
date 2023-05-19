package com.lucamartinelli.telegram.bot.vo.weather;

import java.util.Objects;

public class WeatherForecastVO {
	private String location;
	private WeatherDayVO today;
	private WeatherDayVO tomorrow;
	private WeatherDayVO afterTomorrow;
	
	public WeatherForecastVO() {
		super();
	}

	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public WeatherDayVO getToday() {
		return today;
	}
	public void setToday(WeatherDayVO today) {
		this.today = today;
	}

	public WeatherDayVO getTomorrow() {
		return tomorrow;
	}
	public void setTomorrow(WeatherDayVO tomorrow) {
		this.tomorrow = tomorrow;
	}

	public WeatherDayVO getAfterTomorrow() {
		return afterTomorrow;
	}
	public void setAfterTomorrow(WeatherDayVO afterTomorrow) {
		this.afterTomorrow = afterTomorrow;
	}



	@Override
	public int hashCode() {
		return Objects.hash(afterTomorrow, location, today, tomorrow);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherForecastVO other = (WeatherForecastVO) obj;
		return Objects.equals(afterTomorrow, other.afterTomorrow) && Objects.equals(location, other.location)
				&& Objects.equals(today, other.today) && Objects.equals(tomorrow, other.tomorrow);
	}



	@Override
	public String toString() {
		return "WeatherForecastVO [location=" + location + ", today=" + today + ", tomorrow=" + tomorrow
				+ ", afterTomorrow=" + afterTomorrow + "]";
	}
	
	
	
	
	
}
