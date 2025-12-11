package com.nfc.secondhandshop.RecyclerView;

import android.app.Activity;
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

import com.nfc.secondhandshop.Acticity.GoodsListActivity;
import com.nfc.secondhandshop.Acticity.StoresListActivity;
import com.nfc.secondhandshop.Data.Store;
import com.nfc.secondhandshop.R;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.myViewHolder> {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 数据集合
     */
    private List<Store> data;

    public StoreAdapter(List<Store> data, Context context) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_store_item, parent, false);
        final myViewHolder viewHolder = new myViewHolder(view);
        //点击事件
        viewHolder.button_enterStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mContext, GoodsListActivity.class);

                SharedPreferences sp = mContext.getSharedPreferences("good_data", 0);//获取SharedPreferences对象
                SharedPreferences.Editor edit = sp.edit();//获取Editor对象
                edit.putString("storeName", viewHolder.textView_storeName.getText().toString());//以键值对形式添加新值。
                edit.commit();//提交新值。必须执行，否则前面的操作都无效

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        //将数据设置到item上
        Store store = data.get(position);
        holder.textView_storeName.setText(store.getStoreName());
        holder.textView_mainBusiness.setText(store.getMainBusiness());
        holder.textView_storeIntro.setText(store.getStoreIntro());
        Log.d("onBindViewHolder",  store.toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
        TextView textView_storeName;
        TextView textView_mainBusiness;
        TextView textView_storeIntro;
        Button button_enterStore;

        public myViewHolder(View itemView) {
            super(itemView);
            textView_storeName = itemView.findViewById(R.id.textView_storeName);
            textView_mainBusiness = itemView.findViewById(R.id.textView_mainBusiness);
            textView_storeIntro = itemView.findViewById(R.id.textView_storeIntro);
            button_enterStore = itemView.findViewById(R.id.button_enterStore);
        }
    }
}