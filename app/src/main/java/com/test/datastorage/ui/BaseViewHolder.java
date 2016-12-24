package com.test.datastorage.ui;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：Chris
 * 邮箱：395932265@qq.com
 * 描述:
 *      通用ViewHolder，避免每个Adapter都需要写ViewHolder
 */
public class BaseViewHolder {

    private SparseArray<View> sparseArray;
    private int mPosition;
    private View view;

    /**
     * ViewHolder 构造方法
     */
    public BaseViewHolder(Context context, ViewGroup parent, int layoutId, int position) {

        // 保存位置
        mPosition = position;

        // 集合用来装控件
        sparseArray = new SparseArray<View>();

        // 整个 view 设置一个 Tag
        view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        view.setTag(this);
    }

    /**
     * getViewHolder 返回  viewHolder
     */
    public static BaseViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            // 省了 布局 和 findView
            return new BaseViewHolder(context, parent, layoutId, position);
        } else {
            BaseViewHolder viewHolder = (BaseViewHolder) convertView.getTag();
            viewHolder.mPosition = position;
            return viewHolder;
        }
    }

    /**
     * 获取 布局的 view
     */
    public View getConvertView() {
        return view;
    }

    /**
     * 通过id获取控件
     */
    public <T extends View> T findViewById(int viewId) {

        // 从集合获取到控件
        View view = sparseArray.get(viewId);

        // 如果找不到 findViewById 在插入集合中
        if (view == null) {
            view = this.view.findViewById(viewId);
            sparseArray.put(viewId, view);
        }

        return (T) view;
    }
}
