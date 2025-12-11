package com.nfc.secondhandshop.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nfc.secondhandshop.Acticity.OrderPlaceActivity;
import com.nfc.secondhandshop.Data.Good;
import com.nfc.secondhandshop.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GoodAdapter extends RecyclerView.Adapter<GoodAdapter.myViewHolder> {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 数据集合
     */
    private List<Good> data;

    public GoodAdapter(List<Good> data, Context context) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_good_item, parent, false);
        final myViewHolder viewHolder = new myViewHolder(view);
        //点击事件
        viewHolder.button_buyGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onCreateViewHolder", "onClick");
                Intent intent =  new Intent(mContext, OrderPlaceActivity.class);

                SharedPreferences sp = mContext.getSharedPreferences("good_data", 0);//获取SharedPreferences对象
                SharedPreferences.Editor edit = sp.edit();//获取Editor对象
                edit.putString("goodName", viewHolder.textView_goodName.getText().toString());
                edit.putString("goodPrice", viewHolder.textView_goodPrice.getText().toString());
                edit.putString("time", new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
                edit.commit();

                Log.d("onCreateViewHolder", "onClick:" + new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        //将数据设置到item上
        Good good = data.get(position);
        holder.textView_goodName.setText(good.getGoodName());
        holder.textView_goodPrice.setText(good.getGoodPrice());
        holder.textView_goodIntro.setText(good.getGoodIntro());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
        TextView textView_goodName;
        TextView textView_goodPrice;
        TextView textView_goodIntro;
        Button button_buyGood;

        public myViewHolder(View itemView) {
            super(itemView);
            textView_goodName = itemView.findViewById(R.id.textView_goodName);
            textView_goodPrice = itemView.findViewById(R.id.textView_goodPrice);
            textView_goodIntro = itemView.findViewById(R.id.textView_goodIntro);
            button_buyGood = itemView.findViewById(R.id.button_buyGood);
        }
    }
}