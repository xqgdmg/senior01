package com.example.test.mycustomvolleyrequest.net;

import com.alibaba.fastjson.JSON;
import com.android.volley.*;
import com.android.volley.NetworkResponse;
import com.android.volley.toolbox.HttpHeaderParser;
import java.io.UnsupportedEncodingException;

/**
 * 作者：Chris
 * 邮箱：395932265@qq.com
 * 描述:
 *      如果这个类报错没有办法解决，尝试复制一下重新粘贴，具体原因不清楚
 *      参照 Volley 中的 StringRequest 的写法
 */
public class MyRequest <T> extends Request<T> {
    private Class<T> clazz;
    private Response.Listener<T> mListener;

    public MyRequest(String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(url, errorListener);
        this.clazz = clazz;
        this.mListener = listener;
    }

    /*
     * 解析服务器返回数据
     * 重写 parseNetworkResponse
     * @return
     */
    @Override
    protected Response<T> parseNetworkResponse(com.android.volley.NetworkResponse response) {
        T t = null;
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            t = JSON.parseObject(parsed, clazz);
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));
    }

    // StringRequest 源码
//    protected Response<String> parseNetworkResponse(NetworkResponse response) {
//        String parsed;
//        try {
//            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//        } catch (UnsupportedEncodingException var4) {
//            parsed = new String(response.data);
//        }
//
//        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
//    }

    /*
     * 传递数据，传递给UI（activity、fragment）
     * 重写 deliverResponse，把 String 改成了 泛型 T
     * @param t
     */
    @Override
    protected void deliverResponse(T t) {
        mListener.onResponse(t);
    }

    // StringRequest 源码
//    protected void deliverResponse(String response) {
//        this.mListener.onResponse(response);
//    }
}

