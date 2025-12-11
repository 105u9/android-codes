package com.nfc.secondhandshop.Acticity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nfc.secondhandshop.Data.Store;
import com.nfc.secondhandshop.R;
import com.nfc.secondhandshop.RecyclerView.StoreAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoresListActivity extends AppCompatActivity {
    private String TAG = "StoresListActivity";
    private RecyclerView recyclerView;
    StaggeredGridLayoutManager recyclerViewLayoutManager;
    private List<Store> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //使用瀑布流布局,第一个参数 spanCount 列数,第二个参数 orentation 排列方向
        recyclerViewLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        initData();
    }


    public interface VolleyCallback {
        void onSuccess(JSONArray result);
    }

    public class VolleyUtil {

        public void get(Context context, String url, final VolleyCallback callback) {
            RequestQueue queue = Volley.newRequestQueue(context);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            callback.onSuccess(response);
                            Log.d(TAG, "onResponse:"+response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e(TAG, volleyError.getMessage(), volleyError);
                }
            });
            jsonArrayRequest.setTag("obj");
            queue.add(jsonArrayRequest);
        }
    }

    /**
     * 生成一些数据添加到集合中
     */
    private void initData() {
        //更新店铺表
//        String url = "http://49.234.89.231:8080/main/getAllStores";
        String url = "http://10.101.149.127:8080/main/getAllStores";
        VolleyUtil volleyUtil = new VolleyUtil();
        volleyUtil.get(this, url, new VolleyCallback() {
            public void onSuccess(JSONArray result) {
                for (int i = 0; i < result.length(); i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = result.getJSONObject(i);
                        Log.d(TAG, "onSuccess:" + result.toString());
                        Store store = new Store(
                                jsonObject.getString("storeName"),
                                jsonObject.getString("mainBusiness"),
                                jsonObject.getString("storeIntro")
                        );
                        data.add(store);
                        StoreAdapter adapter = new StoreAdapter(data, getApplicationContext());
                        //设置adapter
                        recyclerView.setAdapter(adapter);
                        Log.d(TAG, "onSuccess:" + data.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}