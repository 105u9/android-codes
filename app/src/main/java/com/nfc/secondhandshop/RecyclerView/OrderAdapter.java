package com.nfc.secondhandshop.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.nfc.secondhandshop.Data.Order;
import com.nfc.secondhandshop.R;

import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.myViewHolder> {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 数据集合
     */
    private List<Order> data;

    public OrderAdapter(List<Order> data, Context context) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_order_item, parent, false);
        final myViewHolder viewHolder = new myViewHolder(view);
        //点击事件
        viewHolder.button_moreOrderInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext.getApplicationContext(), "此功能暂未更新，敬请期待！", Toast.LENGTH_SHORT).show();
//                Intent intent =  new Intent(mContext, GoodsListActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
//                intent.putExtra("storeName",viewHolder.textView_storeName.getText().toString());
//                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        //将数据设置到item上
        Order order = data.get(position);
        holder.textView_storeName.setText(order.getStoreName());
        holder.textView_goodName.setText(order.getGoodName());
        holder.textView_time.setText(order.getTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
        TextView textView_goodName;
        TextView textView_storeName;
        TextView textView_time;
        Button button_moreOrderInfo;

        public myViewHolder(View itemView) {
            super(itemView);
            textView_storeName = itemView.findViewById(R.id.textView_storeName);
            textView_goodName = itemView.findViewById(R.id.textView_goodName);
            textView_time = itemView.findViewById(R.id.textView_time);
            button_moreOrderInfo = itemView.findViewById(R.id.button_moreOrderInfo);
        }
    }
}