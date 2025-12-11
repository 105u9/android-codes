package com.nfc.secondhandshop.Acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nfc.secondhandshop.Data.Order;
import com.nfc.secondhandshop.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OrderPlaceActivity extends AppCompatActivity {
    String TAG = "OrderPlaceActivity";
    Order order = new Order();
    TextView textView_goodName;
    TextView textView_storeName;
    TextView textView_time;
    TextView textView_goodPrice;
    EditText editText_consigneeName;
    EditText editText_phone;
    EditText editText_address;
    Button button_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_place);
        init();
        button_confirm = findViewById(R.id.button_confirm);
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
                update();
            }
        });
    }

    public void init(){
        SharedPreferences sp = getSharedPreferences("good_data", 0);//获取SharedPreferences对象
        Log.d(TAG, "onCreate:" + sp.getString("storeName",null)+"  "+sp.getString("time",null));

        order.setStoreName(sp.getString("storeName",null));
        order.setGoodName(sp.getString("goodName",null));
        order.setTime(sp.getString("time",null));
        textView_goodName = findViewById(R.id.textView_goodName);
        textView_goodPrice = findViewById(R.id.textView_goodPrice);
        textView_storeName = findViewById(R.id.textView_storeName);
        textView_time = findViewById(R.id.textView_time);
        textView_goodName.setText(order.getGoodName());
        textView_goodPrice.setText(sp.getString("goodPrice",null));
        textView_storeName.setText(order.getStoreName());
        textView_time.setText(order.getTime());
    }

    public void getInfo(){
        editText_consigneeName = findViewById(R.id.editText_consigneeName);
        editText_phone = findViewById(R.id.editText_phone);
        editText_address = findViewById(R.id.editText_address);
        order.setConsigneeName(editText_consigneeName.getText().toString());
        order.setAddress(editText_address.getText().toString());
        order.setPhone(editText_phone.getText().toString());
    }

    public void update(){
        //更新订单表
        //修改商品状态 normal->soldOut
        //更新商品表
        Map<String,String> map=new HashMap<String,String>();
        map.put("storeName", order.getStoreName());
        map.put("goodName", order.getGoodName());
        map.put("address", order.getAddress());
        map.put("phone", order.getPhone());
        map.put("time", order.getTime());
        map.put("consigneeName", order.getConsigneeName());
        JSONObject params=new JSONObject(map);

        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://49.234.89.231:8080/main/saveOrder";
        String url = "http://10.101.149.127:8080/main/saveOrder";

        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                    }
                }
        );

        objRequest.setTag("obj");
        queue.add(objRequest);
    }

}
