package com.nfc.secondhandshop.Acticity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nfc.secondhandshop.Data.Good;
import com.nfc.secondhandshop.R;
import com.nfc.secondhandshop.RecyclerView.GoodAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsListActivity extends AppCompatActivity {
    private String TAG = "GoodsListActivity";
    private RecyclerView recyclerView;
    StaggeredGridLayoutManager recyclerViewLayoutManager;
    private List<Good> data = new ArrayList<>();
    private String storeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);

        SharedPreferences sp = getSharedPreferences("good_data", 0);//获取SharedPreferences对象
        storeName = sp.getString("storeName",null);

        Log.d(TAG, "onCreate:" + storeName);
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
            Map<String,String> map=new HashMap<String,String>();
            map.put("storeName", storeName);
            JSONObject params = new JSONObject(map);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(params);
            Log.d(TAG, "JSONArray:" + jsonArray.toString());
            RequestQueue queue = Volley.newRequestQueue(context);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, jsonArray,
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
            jsonArrayRequest.setTag("jsonArrayRequest");
            queue.add(jsonArrayRequest);
        }
    }

    /**
     * 生成一些数据添加到集合中
     */
    private void initData() {
        //更新店铺表
//        String url = "http://49.234.89.231:8080/main/getAllGoods";
        String url = "http://10.101.149.127:8080/main/getAllGoods";
        VolleyUtil volleyUtil = new VolleyUtil();
        volleyUtil.get(this, url, new VolleyCallback() {
            public void onSuccess(JSONArray result) {
                Log.d(TAG, "onSuccess:" + result.toString());
                for (int i = 0; i < result.length(); i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = result.getJSONObject(i);
                        Good good = new Good(
                                jsonObject.getString("goodName"),
                                jsonObject.getString("goodIntro"),
                                jsonObject.getString("goodPrice")
                        );
                        data.add(good);
                        GoodAdapter adapter = new GoodAdapter(data, getApplicationContext());
                        //设置adapter
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
