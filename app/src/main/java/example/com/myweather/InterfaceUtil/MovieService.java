package example.com.myweather.InterfaceUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import example.com.myweather.Bean.HttpResult;
import example.com.myweather.Bean.JsonResuly;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by 20256473 on 2017/3/9.
 */

public interface MovieService {
    @GET("weather/citys")
    Call<HttpResult<ArrayList<JsonResuly>>> getTopMovie(@Query("key") String key);
}
