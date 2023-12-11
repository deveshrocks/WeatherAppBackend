package com.project.weather.service;

import com.project.weather.dto.WeatherDto;

public interface RestClientService {

    WeatherDto getOpenWeather(String city);
}
