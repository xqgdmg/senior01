package com.test.datastorage.hotel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.datastorage.R;
import com.test.datastorage.model.Hotel;
import com.test.datastorage.ui.BaseViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.List;

/**
 * 作者：Chris
 * 邮箱：395932265@qq.com
 * 描述:
 *      酒店列表适配器
 *      使用的是 ArrayAdapter
 */
public class HotelListAdapter extends ArrayAdapter<Hotel>{

    private DisplayImageOptions options;

    public HotelListAdapter(Context context,  List<Hotel> data) {
        super(context, 0, data);

        // ImageLoader 设置
        setImageLoaderOptions();
    }

    /**
     * ImageLoader 设置
     */
    private void setImageLoaderOptions() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.image_default)
                .showImageForEmptyUri(R.drawable.image_default)
                .showImageOnFail(R.drawable.image_default)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())//default
                .build();
    }

    /**
     * getView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 获取 holder，通过 BaseViewHolder
        BaseViewHolder holder = BaseViewHolder.getViewHolder(getContext(), convertView, parent, R.layout.hotel_list_item_layout, position);

        Hotel hotel = getItem(position);

        // 通过 holder 找控件
        ImageView mImageView = holder.findViewById(R.id.image);
        TextView mTvName = holder.findViewById(R.id.tv_name);
        TextView mTvPrice = holder.findViewById(R.id.tv_price);

        // ImageLoader 加载图片
        ImageLoader.getInstance().displayImage(hotel.getImg(), mImageView);

        mTvName.setText(hotel.getName());
        mTvPrice.setText("价格："+hotel.getPrice());

        return holder.getConvertView();// 获取 布局的 view
    }
}
