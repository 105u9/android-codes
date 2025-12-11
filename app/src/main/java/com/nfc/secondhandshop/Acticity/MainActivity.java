package com.nfc.secondhandshop.Acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nfc.secondhandshop.R;

public class MainActivity extends AppCompatActivity {
    private Button button_stores;
    private Button button_storeRegister;
    private Button button_goodRegister;
    private Button button_orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_stores = (Button) findViewById(R.id.button_stores);
        button_storeRegister = (Button) findViewById(R.id.button_storeRegister);
        button_goodRegister = (Button) findViewById(R.id.button_goodRegister);
        button_orders = (Button) findViewById(R.id.button_orders);

        OnClick onClick = new OnClick();
        button_stores.setOnClickListener(onClick);
        button_storeRegister.setOnClickListener(onClick);
        button_goodRegister.setOnClickListener(onClick);
        button_orders.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.button_stores:
                    intent =  new Intent(MainActivity.this, StoresListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_storeRegister:
                    intent =  new Intent(MainActivity.this, StoreRegisterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_goodRegister:
                    intent =  new Intent(MainActivity.this, GoodRegisterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_orders:
                    intent =  new Intent(MainActivity.this, OrdersListActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }

}


