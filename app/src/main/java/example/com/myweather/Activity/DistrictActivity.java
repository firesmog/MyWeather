package example.com.myweather.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import example.com.myweather.Adapter.MyDecoration;
import example.com.myweather.Adapter.MyRecyclerAdapter;
import example.com.myweather.Bean.HttpResult;
import example.com.myweather.Bean.JsonResuly;
import example.com.myweather.Bean.weatherDataBean.Weather;
import example.com.myweather.InterfaceUtil.GetWeatherService;
import example.com.myweather.InterfaceUtil.OnItemClickListener;
import example.com.myweather.R;
import example.com.myweather.Utils.GetWeatherMethods;
import example.com.myweather.Utils.HttpMethods;
import example.com.myweather.Utils.UrlUtils;
import rx.Subscriber;

public class DistrictActivity extends AppCompatActivity {
    private TextView title;
    private RecyclerView ry_city;
    private ArrayList<String> district;
    private MyRecyclerAdapter recycleAdapter;
    private ArrayList<String> distrcts;
    private Subscriber<HttpResult<Weather>> subscriber;
    private String city_name;
    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        title=(TextView)findViewById(R.id.tv_distitle);
        ry_city=(RecyclerView)findViewById(R.id.ry_distrcit);
        Intent intent = this.getIntent();
        String name = intent.getStringExtra("city");
        city_name=name;
        title.setText(name);
        distrcts= HttpMethods.singleton(this).getDistrcitData(name);
        recycleAdapter= new MyRecyclerAdapter(DistrictActivity.this ,distrcts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        ry_city.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        ry_city.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        //设置Adapter
        recycleAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                getMovie();

            }
        });

        ry_city.setAdapter( recycleAdapter);

    }

    private void getMovie() {

            subscriber = new Subscriber<HttpResult<Weather>>() {
                @Override
                public void onCompleted() {
                    Bundle bundle =new Bundle();
                    bundle.putSerializable("weather",weather);
                    Intent intent=new Intent(DistrictActivity.this,WeatherActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                @Override
                public void onError(Throwable e) {
                    Toast.makeText(getApplicationContext(), "获取数据失败", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNext(HttpResult<Weather> arrayListHttpResult) {
                    weather=arrayListHttpResult.result;
                }
            };
            GetWeatherMethods.singleton(this).getTopMovie(subscriber,city_name, UrlUtils.Key);

    }
}
