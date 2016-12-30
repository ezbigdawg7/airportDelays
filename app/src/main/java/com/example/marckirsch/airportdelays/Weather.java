package com.example.marckirsch.airportdelays;

/**
 * Created by Marc on 9/23/2015.
 */
public class Weather {
    private String wind;
    private String weather;
    private Long visibility;
    private String temp;

   private Weather () {}

    Weather (String wind, String weather, Long visibility,  String temp) {
        this.wind = wind;
        this.weather = weather;
        this.visibility = visibility;
        this.temp = temp;
     }

    public String getTemp() {
        return temp;
    }
    public Long getVisibility() {
        return visibility;
    }
    public String getWeather() {
        return weather;
    }
    public String getWind(){
        return wind;
    }


}
