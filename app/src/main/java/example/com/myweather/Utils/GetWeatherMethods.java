package example.com.myweather.Utils;

import android.content.Context;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import example.com.myweather.Bean.City;
import example.com.myweather.Bean.HttpResult;
import example.com.myweather.Bean.JsonResuly;
import example.com.myweather.Bean.weatherDataBean.Weather;
import example.com.myweather.InterfaceUtil.GetWeatherService;
import example.com.myweather.InterfaceUtil.WeatherService;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Lzy on 2017/3/9.
 */

public class GetWeatherMethods {
    public static final String BASE_URL = UrlUtils.Weather_Url;
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private GetWeatherService movieService;
    private  Context context;
    private static GetWeatherMethods INSTANCE;
    private Realm myRealm=null;

    //构造方法私有
    private GetWeatherMethods(Context context) {
        this.context=context;

        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        movieService = retrofit.create(GetWeatherService.class);
    }

    public static GetWeatherMethods singleton(Context context)
    {
        synchronized (GetWeatherMethods.class)
        {
            if( INSTANCE == null)
            {
                INSTANCE = new GetWeatherMethods(context);
            }
            INSTANCE.context = context;
            return INSTANCE;
        }
    }

    public Realm getRealm(Context context)
    {
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("realmdb.realm") //文件名
                .schemaVersion(0) //版本号
                .deleteRealmIfMigrationNeeded()//声明版本冲突时自动删除原数据库(当调用了该方法时，上面的方法将失效)。
                .build();//创建
        return Realm.getInstance(config);
    }

    public void getTopMovie(Subscriber<HttpResult<Weather>> subscriber, String city_name, String key){
        movieService.getTopMovie(2,city_name,key)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}