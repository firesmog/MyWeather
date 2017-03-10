package example.com.myweather.Utils;

import android.content.Context;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import example.com.myweather.Bean.City;
import example.com.myweather.Bean.HttpResult;
import example.com.myweather.Bean.JsonResuly;
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

public class HttpMethods {
    public static final String BASE_URL = UrlUtils.City_Url;
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private WeatherService movieService;
    private  Context context;
    private static HttpMethods INSTANCE;
    private Realm myRealm=null;

    //构造方法私有
    private HttpMethods(Context context) {
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
        movieService = retrofit.create(WeatherService.class);
    }

    public static HttpMethods singleton(Context context)
    {
        synchronized (HttpMethods.class)
        {
            if( INSTANCE == null)
            {
                INSTANCE = new HttpMethods(context);
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
    public void addNote(Realm realm,HttpResult<ArrayList<JsonResuly>> arrayListHttpResult){
        City city=null;
        realm.beginTransaction();//必须先开启事务
        ArrayList<JsonResuly> result=arrayListHttpResult.result;
        for(int i=0;i<result.size();i++){
            city= new City();
            city.setId(i);
            city.setCity_name(result.get(i).getCity());
            city.setProvince_name(result.get(i).getProvince());
            city.setDistrict_name(result.get(i).getDistrict());
            city.setCode(result.get(i).getId());
            realm.copyToRealmOrUpdate(city);
            city=null;
        }
        realm.commitTransaction();//提交事务
        City dog = realm.where(City.class).equalTo("id", 45).findFirst();
    }

    public ArrayList<String> getData(){
        myRealm=getRealm(context);
        RealmResults<City> dogs = myRealm.where(City.class).findAll();
        ArrayList<String> cities=new ArrayList<>();
        String name=null;
        for(City city:dogs){
            if(name==null){
                name=city.getProvince_name();
                cities.add(name);
            }else if(!name.equals(city.getProvince_name())){
                name=city.getProvince_name();
                cities.add(name);
            }

        }
        return cities;
    }
    public ArrayList<String> getCityData(String province){
        myRealm=getRealm(context);
        RealmResults<City> dogs = myRealm.where(City.class).equalTo("Province_name", province).findAll();
        ArrayList<String> cities=new ArrayList<String>();
        String name=null;
        for(City city:dogs){
            if(name==null){
                name=city.getCity_name();
                cities.add(name);
            }else if(!name.equals(city.getCity_name())){
                name=city.getCity_name();
                cities.add(name);
            }


        }
        return cities;
    }
    public ArrayList<String> getDistrcitData(String city){
        myRealm=getRealm(context);
        RealmResults<City> dogs = myRealm.where(City.class).equalTo("City_name", city).findAll();
        ArrayList<String> cities=new ArrayList<String>();
        String name=null;
        for(City data:dogs){
            if(name==null){
                name=data.getDistrict_name();
                cities.add(name);
            }else if(!name.equals(data.getDistrict_name())){
                name=data.getDistrict_name();
                cities.add(name);
            }
        }
        return cities;
    }
    public void getTopMovie(Subscriber<HttpResult<ArrayList<JsonResuly>>> subscriber, String key){
        movieService.getTopMovie(key).flatMap(new Func1<HttpResult<ArrayList<JsonResuly>>, Observable<HttpResult<ArrayList<JsonResuly>>>>() {
            public Observable<HttpResult<ArrayList<JsonResuly>>> call(HttpResult<ArrayList<JsonResuly>> arrayListHttpResult) {
                          myRealm=getRealm(context);
                        addNote(myRealm,arrayListHttpResult);
                return Observable.just(arrayListHttpResult);
            }
        })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}