package example.com.myweather.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import example.com.myweather.Bean.weatherDataBean.FutureWeather;
import example.com.myweather.R;

/**
 * Created by 20256473 on 2017/3/10.
 */

public class FutureRecycleAdapter extends RecyclerView.Adapter< FutureRecycleAdapter.MyViewHolder> {

    private List<FutureWeather> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public FutureRecycleAdapter(Context context, List<FutureWeather> datas){
        this. mContext=context;
        this. mDatas=datas;
        inflater=LayoutInflater. from(mContext);
    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_week.setText(mDatas.get(position).getWeek());
        holder.tv_weather.setText(mDatas.get(position).getWeather());
        holder.tv_temp.setText(mDatas.get(position).getTemperature());
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.ry_item_futureweather,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_weather;
        TextView tv_week;
        TextView tv_temp;

        public MyViewHolder(View view) {
            super(view);
            tv_week=(TextView) view.findViewById(R.id. tv_weekday);
            tv_weather=(TextView) view.findViewById(R.id. tv_furweather);
            tv_temp=(TextView) view.findViewById(R.id. tv_temperture);
        }

    }
}