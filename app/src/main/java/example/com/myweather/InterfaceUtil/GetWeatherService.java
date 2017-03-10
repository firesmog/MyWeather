package example.com.myweather.InterfaceUtil;

import java.util.ArrayList;

import example.com.myweather.Bean.HttpResult;
import example.com.myweather.Bean.JsonResuly;
import example.com.myweather.Bean.weatherDataBean.Weather;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 20256473 on 2017/3/10.
 */

public interface GetWeatherService {
    @GET("weather/index")
    Observable<HttpResult<Weather>> getTopMovie(@Query("format") int num,@Query("cityname") String city_name, @Query("key") String key);
}
