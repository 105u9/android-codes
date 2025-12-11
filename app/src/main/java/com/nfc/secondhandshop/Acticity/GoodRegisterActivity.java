package com.nfc.secondhandshop.Acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nfc.secondhandshop.Data.Good;
import com.nfc.secondhandshop.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GoodRegisterActivity extends AppCompatActivity {
    String TAG = "GoodRegisterActivity";
    private EditText editText_goodName;
    private EditText editText_storeName;
    private EditText editText_goodIntro;
    private EditText editText_goodPrice;
    private Good good = new Good();
    private Button button_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_register);
        button_confirm = findViewById(R.id.button_confirm);
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
                update();
            }
        });
    }

    public void getInfo(){
        editText_goodName = findViewById(R.id.editText_goodName);
        editText_storeName = findViewById(R.id.editText_storeName);
        editText_goodIntro = findViewById(R.id.editText_goodIntro);
        editText_goodPrice = findViewById(R.id.editText_goodPrice);
        good.setGoodName(editText_goodName.getText().toString());
        good.setStoreName(editText_storeName.getText().toString());
        good.setGoodIntro(editText_goodIntro.getText().toString());
        good.setGoodPrice(editText_goodPrice.getText().toString());
    }

    public void update(){
        //更新商品表
        Map<String,String> map=new HashMap<String,String>();
        map.put("goodName", good.getGoodName());
        map.put("storeName", good.getStoreName());
        map.put("goodIntro", good.getGoodIntro());
        map.put("goodPrice", good.getGoodPrice());
        JSONObject params=new JSONObject(map);

        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://49.234.89.231:8080/main/saveGood";
        String url = "http://10.101.149.127:8080/main/saveGood";
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
