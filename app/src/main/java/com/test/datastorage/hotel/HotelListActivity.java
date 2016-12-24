package com.test.datastorage.hotel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.test.datastorage.R;
import com.test.datastorage.model.Hotel;
import com.test.datastorage.net.DataCenter;
import com.test.datastorage.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Chris
 * 邮箱：395932265@qq.com
 * 描述:
 *      显示酒店的页面
 */
public class HotelListActivity extends Activity implements View.OnClickListener, Response.Listener<String>, Response.ErrorListener {

    private ListView mListView;
    private List<Hotel> hotelList;
    private HotelListAdapter mAdapter;
    private int pagenum = 1;//当前页码
    private String reqFlag = this.getClass().getName();//标记volley请求，当页面finish时，通过标取消请求（volley和activity什么周期绑定）
    private RequestQueue queue;
    private View listFooterView;
    private ImageView mImageLastPage;//上一页
    private ImageView mImageNextPage;//下一页
    private TextView mTvCurPage;//当前页码
    private ProgressBar mProgressBar;
    private final static int PAGE_SIZE = 10;//每页10条数据
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        initView();
        initData();
        setListener();
    }

    private void setListener() {
        mImageLastPage.setOnClickListener(this);
        mImageNextPage.setOnClickListener(this);
    }

    private void initData() {
        queue = Volley.newRequestQueue(this);

        // 调用 DataCenter 中的方式请求酒店列表
        DataCenter.getInstance(this).getHotelList(reqFlag, queue, pagenum, this, this);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        listFooterView = View.inflate(this, R.layout.list_footer_layout, null);
        mTvCurPage = (TextView) listFooterView.findViewById(R.id.tv_cur_page);
        mImageLastPage = (ImageView) listFooterView.findViewById(R.id.iv_last_page);
        mImageNextPage = (ImageView) listFooterView.findViewById(R.id.iv_next_page);
        mTvCurPage.setText("第1页");

        hotelList = new ArrayList<Hotel>();
        mAdapter = new HotelListAdapter(this, hotelList);
        mListView.addFooterView(listFooterView);
        mListView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_last_page://上一页
                pagenum--;
                showProgressBar();
                DataCenter.getInstance(this).getHotelList(reqFlag, queue, pagenum, this, this);

                break;
            case R.id.iv_next_page://下一页
                showProgressBar();// 显示隐藏 ProgressBar
                pagenum++;// 页数加1
                DataCenter.getInstance(this).getHotelList(reqFlag, queue, pagenum, this, this);
                break;
        }
    }

    /**
     * 请求成功的回调
     */
    @Override
    public void onResponse(String result) {
        LogUtil.d(result);

        // 使用 fastJson 解析数据
        List<Hotel> hotels = JSON.parseArray(result, Hotel.class);
        if (hotels != null && hotels.size() > 0) {
            hotelList.clear();
            hotelList.addAll(hotels);
            mAdapter.notifyDataSetChanged();
            mTvCurPage.setText("第" + pagenum + "页");
        }

        hideProgressBar();// 隐藏 ProgressBar

        if (pagenum == 1) {//第一页，不显示返回上一页按钮
            mImageLastPage.setVisibility(View.INVISIBLE);
        } else {
            mImageLastPage.setVisibility(View.VISIBLE);
        }
        if (hotelList.size() < PAGE_SIZE) {
            mImageNextPage.setVisibility(View.INVISIBLE);
        } else {
            mImageNextPage.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 请求失败的回调
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        hideProgressBar();
        LogUtil.d(error.getMessage());// 打印异常信息

        // 显示弹窗
        if (dialog == null) {
            dialog = new AlertDialog.Builder(this)
                    .setTitle("网络异常!")
                    .setMessage("请检查MyEclipse服务器是否开启，或访问地址是否正确。若未下载服务器代码，请下载：https://github.com/xqgdmg/senior01.git")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
        }
        dialog.show();
    }

    /**
     * 隐藏 ProgressBar
     */
    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏 ProgressBar
     */
    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * 退出页面内
     */
    @Override
    public void finish() {
        queue.cancelAll(reqFlag);//页面消失，取消发送
        LogUtil.d("退出页面，Volley已经和Activity声明周期联动");
        Toast.makeText(HotelListActivity.this, "退出酒店模块", Toast.LENGTH_SHORT).show();
        if (dialog != null && dialog.isShowing()) dialog.dismiss();
        DataCenter.getInstance(this).deleteAllData();// 退出页面删除缓存数据
        super.finish();
    }
}
