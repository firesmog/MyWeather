package example.com.myweather.Bean.weatherDataBean;

import java.io.Serializable;

/**
 * Created by Lzy
 * on 2017/3/10.
 */

public class Today_Weather implements Serializable{
    public Today_Weather(String city, String date_y, String week, String temperature, String weather, Weather_id weather_id) {
        this.city = city;
        this.date_y = date_y;
        this.week = week;
        this.temperature = temperature;
        this.weather = weather;
        this.weather_id = weather_id;
    }

    public Today_Weather() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate_y() {
        return date_y;
    }

    public void setDate_y(String date_y) {
        this.date_y = date_y;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Weather_id getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(Weather_id weather_id) {
        this.weather_id = weather_id;
    }

    private String city;
    private String date_y;
    private String week;
    private String temperature;/*今日温度*/
    private String weather;	/*今日天气*/
    private Weather_id weather_id;





}
