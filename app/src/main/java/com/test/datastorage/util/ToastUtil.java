package com.test.datastorage.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：Chris
 * 邮箱：395932265@qq.com
 * 描述:
 *      ToastUtil
 */
public class ToastUtil {

    public static void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
