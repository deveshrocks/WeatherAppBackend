package com.project.weather.dto;




import lombok.Data;

@Data
public class WeatherDayDetail {
	public String date;
    public double minTemp;
    public double maxTemp;
    public String instruction;
}
