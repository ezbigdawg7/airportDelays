package com.example.marckirsch.airportdelays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Marc on 9/22/2015.
 */


public class AirportDelays {
    public String IATA;
//    private String ICAO;
    public Status status;
    public String ICAO;
    public String name;
    public String state;
    public Weather weather;
    public boolean delay;
    public String city;



    public AirportDelays() {}

    AirportDelays (String IATA, Status status, String ICAO, String name, String state, Weather weather,  Boolean delay, String city ) {


        this.IATA = IATA;
        this.status = status;
        this.ICAO = ICAO;
        this.name = name;
        this.state = state;
        this.weather = weather;
        this.delay = delay;
        this.city = city;

    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public String getIATA() {
        return IATA;}
 //   public String getICAO() {
 //       return ICAO;}
    public String getCity() {
        return city;}
    public Boolean getDelay(){
        return delay;}
    public String getName() {
        return name;}
    public String getState() {
        return state;}
    public Status getStatus() {
        return status;}
    public Weather getWeather() {
        return weather;}



}
