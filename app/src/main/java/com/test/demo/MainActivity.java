package com.test.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String url;
    private Context context;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        initView();
        /**
         * Volley三部曲：
         * 1.创建对应请求对象
         * 2.创建请求队列
         * 3.发起请求：queue.add(request)
         */

        context = this;
//        url = "http://10.0.3.2:8080/json/student.json";// genymotion 的地址
        url = "http://192.168.1.100:8080/json/student.json";// genymotion 的地址
    }

    /**
     * 返回字符串
     *
     * @param v
     */
    public void stringRequest(View v) {
        //1.创建对应请求对象:参数2-成功回调接口、参数3-失败回调接口
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {

            //成功
            @Override
            public void onResponse(String response) {
                try {
                    showLog(response);
                    //解析json
                    JSONObject json = new JSONObject(response);
//                    String name = json.getString("name");//当key不存在，空指针，开发中不建议使用这个方式解析json
                    String name = json.optString("name");//开发建议使用这种方式，当key不存在，返回默认值："",0,0.0

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            //失败
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                showLog("error");
            }
        });

        //2.创建请求队列
        RequestQueue queue = Volley.newRequestQueue(context);
        //3.发起请求：queue.add(request)
        queue.add(request);
    }

    //返回json对象
    public void jsonObjectRequest(View v) {
        JSONObject params = null;//提交的参数
        JsonObjectRequest request = new JsonObjectRequest(url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                showLog(jsonObject.toString());
                String name = jsonObject.optString("name");
                int age = jsonObject.optInt("age");

                showLog("age=" + age + ",name=" + name);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                showLog("error" + volleyError.getMessage());
            }
        });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void postParams(View v) {
        //正式开发，不建议这么提交参数
        JSONObject params = null;//提交的参数
//            params.put("username", "xqgdmg");
        JsonObjectRequest request = new JsonObjectRequest("http://httpbin.org/get", params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                showLog(jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                showLog("error" + volleyError.getMessage());
            }
        }) {
            //提交参数，正式开发建议在这里处理
            @Override // 重写了
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", "xqgdmg");
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

    }

    //返回json数组
    public void jsonArrayRequest(View v) {
        String arrayUrl = "http://192.168.1.100:8080/json/computers.json";
        JsonArrayRequest request = new JsonArrayRequest(arrayUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Toast.makeText(MainActivity.this, jsonArray.toString(), Toast.LENGTH_SHORT).show();
                showLog(jsonArray.toString());
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject json = jsonArray.optJSONObject(i);
                    String name = json.optString("name");
                    double price = json.optDouble("price");
                    showLog("name=" + name + ",price=" + price);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                showLog("erro");
            }
        });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void imageRequest(View v) {
        //volley三部曲
        //1.创建请求对象
        String url = "http://192.168.1.100:8080/imgs/messi.png";
        int maxWidth = 120;//代码中单位：px，设置最大宽度
        int maxHeight = 120;//设置最大高度
        //大图缩放
        Bitmap.Config decodeConfig = Bitmap.Config.RGB_565;//16位，2byte：一个像素点占2个字节

//        Bitmap.Config decodeConfig = Bitmap.Config.ARGB_4444;//16位，2个字节，一个像素点占2个字节
//        Bitmap.Config decodeConfig = Bitmap.Config.ALPHA_8;//8位，1个字节，一个像素点占1个字节
//        Bitmap.Config decodeConfig = Bitmap.Config.ARGB_8888;//32位，4个字节，一个像素点占4个字节
        /**
         * 联系：有一张图片：480 * 800，请计算占用内存大小
         * RGB_565：480 * 800 * 2 byte = /1024 =kb
         */
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                image.setImageBitmap(bitmap);
            }
        }, maxWidth, maxHeight, decodeConfig, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                showLog("error");
            }
        });
        //2.创建请求队列
        RequestQueue queue = Volley.newRequestQueue(context);
        //3.发起网络请求：add
        queue.add(request);
    }

    private void showLog(String msg) {
        Log.e("result", "" + msg);
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);
    }
}
