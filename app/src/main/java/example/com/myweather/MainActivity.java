package example.com.myweather;
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

import example.com.myweather.Activity.CityActivity;
import example.com.myweather.Adapter.MyDecoration;
import example.com.myweather.Adapter.MyRecyclerAdapter;
import example.com.myweather.Bean.HttpResult;
import example.com.myweather.Bean.JsonResuly;
import example.com.myweather.InterfaceUtil.OnItemClickListener;
import example.com.myweather.Utils.HttpMethods;
import example.com.myweather.Utils.UrlUtils;
import rx.Subscriber;



public class MainActivity extends AppCompatActivity {
    private TextView resultTV;
    private Subscriber<HttpResult<ArrayList<JsonResuly>>> subscriber;
    private Boolean IsFirstStart=false;
    private RecyclerView  ry_province;
    private ArrayList<String> mProvince;
    private MyRecyclerAdapter recycleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV = (TextView) findViewById(R.id.tv_content);
        ry_province=(RecyclerView)findViewById(R.id.ry_province);
        getMovie();
        resultTV.setText("中国");
        mProvince= HttpMethods.singleton(getApplicationContext()).getData();
        recycleAdapter= new MyRecyclerAdapter(MainActivity.this ,mProvince);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        ry_province.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        ry_province.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        //设置Adapter
        recycleAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String province=mProvince.get(position);
                Intent intent=new Intent(MainActivity.this, CityActivity.class);
                intent.putExtra("province",province);
                startActivity(intent);
            }
        });

        ry_province.setAdapter( recycleAdapter);
    }
    private void getMovie() {
        if (IsFirstStart) {
            subscriber = new Subscriber<HttpResult<ArrayList<JsonResuly>>>() {
                @Override
                public void onCompleted() {
                }
                @Override
                public void onError(Throwable e) {
                    Toast.makeText(getApplicationContext(), "获取数据失败", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNext(HttpResult<ArrayList<JsonResuly>> arrayListHttpResult) {
                    resultTV.setText(arrayListHttpResult.result.get(0).getCity());
                }
            };
            HttpMethods.singleton(this).getTopMovie(subscriber, UrlUtils.Key);
        }
    }
}




