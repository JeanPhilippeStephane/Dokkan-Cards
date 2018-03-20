package com.dcv.spdesigns.dokkancards.model.filter_dialog.jp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.dcv.spdesigns.dokkancards.model.jp.JPDataHolder;

/**
 * This adapter class initializes the jp gridView's imageViews with
 * just LR card icons(if the user has added any LR cards to his/her box).
 */

public class JpUrImageAdapter extends BaseAdapter {

    private Context mContext;

    public JpUrImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return JPDataHolder.URCards.size();
    }

    @Override
    public Object getItem(int position) {
        return JPDataHolder.URCards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        // If it's not recycled, initialize some attributes
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(JPDataHolder.URCards.get(position).getCardIcon());
        return imageView;
    }
}
