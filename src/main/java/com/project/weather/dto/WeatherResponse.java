package com.project.weather.dto;

import lombok.Data;

@Data
public class WeatherResponse {
    public WeatherDayDetail today;
    public WeatherDayDetail tomorrow;
    public WeatherDayDetail dayAfterTomorrow;

}
