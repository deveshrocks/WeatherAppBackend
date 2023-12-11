package com.project.weather.service;

import com.project.weather.dto.WeatherDayDetail;
import com.project.weather.dto.WeatherResponse;

import java.util.List;

public interface WeatherService {

    WeatherResponse fetchWeather(String city);
    List<WeatherDayDetail> fetchWeather2(String city);
}
