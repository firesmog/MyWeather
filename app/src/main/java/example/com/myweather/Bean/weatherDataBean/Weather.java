package example.com.myweather.Bean.weatherDataBean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 20256473 on 2017/3/10.
 */

public class Weather implements Serializable {
    private Sk_Weather sk;
    private Today_Weather today;
    private ArrayList<FutureWeather> future;

    public Weather() {
    }

    public Weather(Sk_Weather sk, Today_Weather today, ArrayList<FutureWeather> future) {
        this.sk = sk;
        this.today = today;
        this.future = future;
    }

    public Sk_Weather getSk() {
        return sk;
    }

    public void setSk(Sk_Weather sk) {
        this.sk = sk;
    }

    public Today_Weather getToday() {
        return today;
    }

    public void setToday(Today_Weather today) {
        this.today = today;
    }

    public ArrayList<FutureWeather> getFuture() {
        return future;
    }

    public void setFuture(ArrayList<FutureWeather> future) {
        this.future = future;
    }
}
