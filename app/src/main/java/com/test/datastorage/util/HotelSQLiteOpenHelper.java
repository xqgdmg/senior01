package com.test.datastorage.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 作者：Chris
 * 邮箱：395932265@qq.com
 * 描述:
 *      SQLiteOpenHelper
 */
public class HotelSQLiteOpenHelper extends SQLiteOpenHelper {
    public final static String HOTEL_TABLE = "hotel_list";

    public HotelSQLiteOpenHelper(Context context) {
        super(context, "xqgdmg.db", null, 1);
    }

    /**
     * 创建表
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+HOTEL_TABLE+"(id INTEGER PRIMARY KEY AUTOINCREMENT,pagenum INTEGER,data VARCHAR(20),time VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 删除Least recently unused数据
     */
    public void deleteLruData(){

    }


}
