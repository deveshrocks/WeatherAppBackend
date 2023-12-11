package com.project.weather.controller;

import com.project.weather.dto.WeatherDto;
import com.project.weather.dto.WeatherResponse;
import com.project.weather.service.RestClientService;
import com.project.weather.service.RestClientServiceImpl;
import com.project.weather.service.WeatherService;
import com.project.weather.service.WeatherServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@Slf4j
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Autowired
    RestClientService restClientService;

    @Cacheable("weather")
    @RequestMapping (value = "/getWeather", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getWeatherReport(@RequestParam("city") String city) {
        log.info("Input Request--> City: " + city);
        try{
//            WeatherResponse weatherResponse=weatherService.fetchWeather(city);
            
            return new ResponseEntity<>(weatherService.fetchWeather2(city), HttpStatus.OK);
        }catch(Exception e){
            log.error("Exception occurred {}", ExceptionUtils.getStackTrace(e));
        }
        return new ResponseEntity<>("Service Failed", HttpStatus.OK);
    }

    @RequestMapping (value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test() {
        try{
            WeatherDto weatherDto= restClientService.getOpenWeather("Delhi");
            return new ResponseEntity<>(weatherDto, HttpStatus.OK);
        }catch(Exception e){
            log.error("Exception occurred {}", ExceptionUtils.getStackTrace(e));
        }
        return new ResponseEntity<>("Service Failed", HttpStatus.OK);
    }




}
