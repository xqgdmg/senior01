package com.example.test.mycustomvolleyrequest.net;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * 作者：Chris
 * 邮箱：395932265@qq.com
 * 描述:
 *      解析服务器返回数据
 */
public class NetworkResponse {
        //返回对象
    public static <T> T parseData(String reponse, Class<T> clazz){
        return JSON.parseObject(reponse, clazz);
    }
    //返回数组
    public static <T> List<T> parseList(String reponse, Class<T> clazz){
        return JSON.parseArray(reponse, clazz);
    }
}
