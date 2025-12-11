package com.nfc.secondhandshop.Acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nfc.secondhandshop.Data.Store;
import com.nfc.secondhandshop.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StoreRegisterActivity extends AppCompatActivity {
    String TAG = "StoreRegisterActivity";
    private LinearLayout likeLinear;
    private EditText editText_storeName;
    private EditText editText_storeIntro;
    private Button button_confirm;
    private Store store = new Store();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_register);
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
        editText_storeName = findViewById(R.id.editText_storeName);
        editText_storeIntro = findViewById(R.id.editText_storeIntro);
        store.setStoreName(editText_storeName.getText().toString());
        store.setStoreIntro(editText_storeIntro.getText().toString());
        //复选框
        likeLinear= (LinearLayout) findViewById(R.id.likeLiner_MainBusiness);
        int cNum = likeLinear.getChildCount();//拿到所有的子类长度
        String mainBusiness = "";
        for (int i = 1; i < cNum; i++) {
            //根据i拿到每一个CheckBox
            CheckBox cb= (CheckBox) likeLinear.getChildAt(i);
            // 判断CheckBox是否被选中
            if(cb.isChecked()){
                //把被选中的文字添加到StringBuffer中
                mainBusiness.concat("["+cb.getText().toString()+"]");
            }
        }
        store.setMainBusiness(mainBusiness);
    }

    public void update(){
        //更新店铺表
        Map<String,String> map=new HashMap<String,String>();
        map.put("storeName", store.getStoreName());
        map.put("storeIntro", store.getStoreIntro());
        map.put("mainBusiness", store.getMainBusiness());
        JSONObject params=new JSONObject(map);

        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://49.234.89.231:8080/main/saveStore";
        String url = "http://10.101.149.127:8080/main/saveStore";
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
