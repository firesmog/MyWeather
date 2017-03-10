package example.com.myweather.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import example.com.myweather.Adapter.FutureRecycleAdapter;
import example.com.myweather.Adapter.MyDecoration;
import example.com.myweather.Bean.weatherDataBean.Weather;
import example.com.myweather.R;

public class WeatherActivity extends AppCompatActivity {
    private TextView tv_date;
    private TextView tv_week;
    private TextView tv_temp;
    private TextView tv_weather;
    private TextView tv_wind;
    private TextView tv_windstr;
    private TextView tv_city;
    private RecyclerView ry_weather;
    private FutureRecycleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();
        Weather weatherdata=(Weather)getIntent().getSerializableExtra("weather");
        SetData(weatherdata);
        adapter=new FutureRecycleAdapter(this,weatherdata.getFuture());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ry_weather.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        ry_weather.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        ry_weather.setAdapter(adapter);

    }
    public void SetData(Weather weatherdata){
        tv_city.setText(weatherdata.getToday().getCity());
        tv_date.setText(weatherdata.getToday().getDate_y());
        tv_week.setText(weatherdata.getToday().getWeek());
        tv_temp.setText(weatherdata.getToday().getTemperature());
        tv_weather.setText(weatherdata.getToday().getWeather());
        tv_wind.setText(weatherdata.getSk().getWind_direction());
        tv_windstr.setText(weatherdata.getSk().getWind_strength());
    }
    public void initView(){
        tv_date=(TextView)findViewById(R.id.tv_date);
        tv_week=(TextView)findViewById(R.id.tv_week);
        tv_temp=(TextView)findViewById(R.id.tv_temp);
        tv_weather=(TextView)findViewById(R.id.tv_weather);
        tv_wind=(TextView)findViewById(R.id.tv_wind);
        tv_windstr=(TextView)findViewById(R.id.tv_windstr);
        tv_city=(TextView)findViewById(R.id.tv_cityTitle);
        ry_weather=(RecyclerView)findViewById(R.id.ry_weather);
    }
}
