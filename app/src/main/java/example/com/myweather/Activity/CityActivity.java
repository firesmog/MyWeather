package example.com.myweather.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.myweather.Adapter.MyDecoration;
import example.com.myweather.Adapter.MyRecyclerAdapter;
import example.com.myweather.InterfaceUtil.OnItemClickListener;
import example.com.myweather.MainActivity;
import example.com.myweather.R;
import example.com.myweather.Utils.HttpMethods;

public class CityActivity extends AppCompatActivity {
    private TextView title;
    private RecyclerView ry_city;
    private ArrayList<String> cities;
    private MyRecyclerAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        title=(TextView)findViewById(R.id.tv_title);
        ry_city=(RecyclerView)findViewById(R.id.ry_city);
        Intent intent = this.getIntent();
        String name = intent.getStringExtra("province");
        title.setText(name);
        cities= HttpMethods.singleton(this).getCityData(name);
        recycleAdapter= new MyRecyclerAdapter(CityActivity.this ,cities);
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
                String city=cities.get(position);
                Intent intent=new Intent(CityActivity.this, DistrictActivity.class);
                intent.putExtra("city",city);
                startActivity(intent);
            }
        });

        ry_city.setAdapter( recycleAdapter);

    }
}
