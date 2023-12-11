package com.project.weather.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class WeatherDto implements Serializable {

    public String cod;
    public int message;
    public int cnt;
    @JsonProperty("list")
    public ArrayList<List> list;
    public City city;


    public static class City{
        public int id;
        public String name;
        public Coord coord;
        public String country;
        public int population;
        public int timezone;
        public int sunrise;
        public int sunset;
    }

    public static class Clouds{
        public int all;
    }

    public static class Coord{
        public double lat;
        public double lon;
    }

    public static class List{
        public int dt;
        public Main main;
        public ArrayList<Weather> weather;
        public Clouds clouds;
        public Wind wind;
        public int visibility;
        public double pop;
        public Rain rain;
        public Sys sys;
        public String dt_txt;
    }

    public static class Main{
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int sea_level;
        public int grnd_level;
        public int humidity;
        public double temp_kf;
    }

    public static class Rain{
        @JsonProperty("3h")
        public double _3h;
    }

    public static class Sys{
        public String pod;
    }

    public static class Weather{
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public static class Wind{
        public double speed;
        public int deg;
        public double gust;
    }


}
