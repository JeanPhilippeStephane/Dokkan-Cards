package com.dcv.spdesigns.dokkancards.model.jp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;

import java.util.ArrayList;

/**
 * This class is used to initialize the UserBoxJPFragment's grid
 * with imageViews using the data from the JPDataHolder.java class
 */

public class UserBoxJpImageAdapter extends BaseAdapter {

    private final Context mContext;

    public UserBoxJpImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return JPDataHolder.cards.size();
    }

    @Override
    public Object getItem(int i) {
        return JPDataHolder.cards.get(i);
    }

    @Override
    public long getItemId(int i) {
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
        imageView.setImageResource(JPDataHolder.cards.get(position).getCardIcon());
        return imageView;
    }

    /**
     * Refreshes the fragment's view to display data changes
     * @param adapter Used to get the current instance of the fragment's adapter
     */
    public static void refreshFragmentView(UserBoxJpImageAdapter adapter) {
        adapter.notifyDataSetChanged();
    }
}
