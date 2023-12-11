package com.project.weather.service;

import com.project.weather.dto.WeatherDto;
import com.project.weather.util.AppUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestClientServiceImpl implements RestClientService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeatherDto getOpenWeather(String city) {
        String url=AppUtil.WEATHER_URL.replace("{city}",city);
        try{
            System.out.println(url);
            WeatherDto weatherDto = restTemplate.getForObject(url,WeatherDto.class);
            return weatherDto;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
