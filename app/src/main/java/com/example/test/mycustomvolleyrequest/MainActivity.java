package com.example.test.mycustomvolleyrequest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.mycustomvolleyrequest.model.CommentContent;
import com.example.test.mycustomvolleyrequest.model.Student;
import com.example.test.mycustomvolleyrequest.net.MyRequest;

/**
 * 作者：Chris
 * 邮箱：395932265@qq.com
 * 描述:
 *      TODO
 */
public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<Student> {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = "http://10.0.3.2:8080/json/student.json";
    }

    /**
     * 自定义Volley请求（返回student）
     */
    public void customRequest(View v){
        /**
         * 自定义volley步骤：
         * 1.继承Request，建议参考StringRequest
         */

        //1.
        MyRequest request = new MyRequest(url, Student.class,this, this);
        //2.
        RequestQueue queue = Volley.newRequestQueue(this);
        //3.
        queue.add(request);
    }

    /**
     * 自定义Volley请求（复杂数据类型）
     */
    public void customRequest2(View v) {
        //1.
        MyRequest request = new MyRequest("http://10.0.3.2:8080/VolleyTest/Comments",
                CommentContent.class, // 直接转化为 复杂数据类型 bean 带集合
                new Response.Listener<CommentContent>() {

            @Override
            public void onResponse(CommentContent commentContent) {
                Log.d("result", commentContent.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("result", "error");
            }
        });
        //2.
        RequestQueue queue = Volley.newRequestQueue(this);
        //3.
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Log.d("result", "error");
    }


    @Override
    public void onResponse(Student student) {
        Log.d("result", student.toString());
    }

    /**
     * 自定义Volley请求（封装FastJson）
     * 这个，好像没有写
     */
    public void customRequest3(View v){
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
//                Student student = NetworkResponse.parseData(s, Student.class);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
