package com.project.weather.service;

import com.project.weather.dto.WeatherDayDetail;
import com.project.weather.dto.WeatherDto;
import com.project.weather.dto.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService{

    @Autowired
    RestClientService restClientService;

    public WeatherResponse fetchWeather(String city) {
        try {
            WeatherResponse weatherResponse = new WeatherResponse();
            List<WeatherDayDetail> WeatherDayDetailLst = new ArrayList<>();
            
            WeatherDto weatherDto= restClientService.getOpenWeather(city);

            WeatherDayDetail weatherDayDetail= new WeatherDayDetail();
            weatherDayDetail.setMaxTemp(weatherDto.getList().get(0).main.temp_max);
            weatherDayDetail.setMinTemp(weatherDto.getList().get(0).main.temp_min);
            weatherDayDetail.setInstruction(getInstruction(weatherDto,0));
            weatherResponse.setToday(weatherDayDetail);
            WeatherDayDetailLst.add(weatherDayDetail);
            

            WeatherDayDetail weatherDayDetailDay1= new WeatherDayDetail();
            weatherDayDetailDay1.setMaxTemp(weatherDto.getList().get(1).main.temp_max);
            weatherDayDetailDay1.setMinTemp(weatherDto.getList().get(1).main.temp_min);
            weatherDayDetailDay1.setInstruction(getInstruction(weatherDto,1));
            weatherResponse.setTomorrow(weatherDayDetailDay1);
            WeatherDayDetailLst.add(weatherDayDetailDay1);
            
            

            WeatherDayDetail weatherDayDetailDay2= new WeatherDayDetail();
            weatherDayDetailDay2.setMaxTemp(weatherDto.getList().get(2).main.temp_max);
            weatherDayDetailDay2.setMinTemp(weatherDto.getList().get(2).main.temp_min);
            weatherDayDetailDay2.setInstruction(getInstruction(weatherDto,2));
            weatherResponse.setDayAfterTomorrow(weatherDayDetailDay2);
            WeatherDayDetailLst.add(weatherDayDetailDay2);

            return weatherResponse;
        } catch (Exception e) {
            log.error("Exception - {}", ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }
    
    public List<WeatherDayDetail> fetchWeather2(String city) {
        try {
            List<WeatherDayDetail> WeatherDayDetailLst = new ArrayList<>();
            
            WeatherDto weatherDto= restClientService.getOpenWeather(city);

            WeatherDayDetail weatherDayDetail= new WeatherDayDetail();
            weatherDayDetail.setMaxTemp(weatherDto.getList().get(0).main.temp_max);
            weatherDayDetail.setMinTemp(weatherDto.getList().get(0).main.temp_min);
            weatherDayDetail.setInstruction(getInstruction(weatherDto,0));  
            weatherDayDetail.setDate("Today");
            WeatherDayDetailLst.add(weatherDayDetail);
            

            WeatherDayDetail weatherDayDetailDay1= new WeatherDayDetail();
            weatherDayDetailDay1.setMaxTemp(weatherDto.getList().get(1).main.temp_max);
            weatherDayDetailDay1.setMinTemp(weatherDto.getList().get(1).main.temp_min);
            weatherDayDetailDay1.setInstruction(getInstruction(weatherDto,1));
            weatherDayDetailDay1.setDate("Tommorrow");
            WeatherDayDetailLst.add(weatherDayDetailDay1);
            
            

            WeatherDayDetail weatherDayDetailDay2= new WeatherDayDetail();
            weatherDayDetailDay2.setMaxTemp(weatherDto.getList().get(2).main.temp_max);
            weatherDayDetailDay2.setMinTemp(weatherDto.getList().get(2).main.temp_min);
            weatherDayDetailDay2.setInstruction(getInstruction(weatherDto,2));
            weatherDayDetailDay2.setDate("Day After Tommorrow");
            WeatherDayDetailLst.add(weatherDayDetailDay2);

            return WeatherDayDetailLst;
        } catch (Exception e) {
            log.error("Exception - {}", ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    public String getInstruction( WeatherDto weatherDtoList, int day){
        if(weatherDtoList.getList().get(day).main.temp_max>40){
            return "Use Sunscreen lotion";
        }else if(weatherDtoList.getList().get(day).wind.speed>10){
            return "It's too windy, Watch Out!";
        }else if(weatherDtoList.getList().get(day).weather.get(0).main.contains("Rain")){
            return "Carry Umbrella";
        }else if(weatherDtoList.getList().get(day).weather.get(0).main.contains("Thunderstorm")){
            return "Dont step out! Storm is brewing!";
        }
        return weatherDtoList.getList().get(day).weather.get(0).description;
    }

}
