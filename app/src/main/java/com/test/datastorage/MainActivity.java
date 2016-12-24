package com.test.datastorage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.test.datastorage.hotel.HotelListActivity;

/**
 * 作者：Chris
 * 邮箱：395932265@qq.com
 * 描述:
 *      MainActivity,只是实现跳转到酒店列表的功能
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 进入酒店列表页
     * @param v
     */
    public void hotelList(View v){
        Intent intent = new Intent(this, HotelListActivity.class);
        startActivity(intent);
    }

}
