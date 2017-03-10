package example.com.myweather.InterfaceUtil;

import java.util.ArrayList;

import example.com.myweather.Bean.HttpResult;
import example.com.myweather.Bean.JsonResuly;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 20256473 on 2017/3/9.
 */

public interface WeatherService {
    @GET("weather/citys")
    Observable<HttpResult<ArrayList<JsonResuly>>> getTopMovie(@Query("key") String key);
}
