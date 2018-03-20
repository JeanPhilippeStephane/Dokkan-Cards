package com.dcv.spdesigns.dokkancards.model.filter_dialog.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.dcv.spdesigns.dokkancards.model.main.CardInfoDatabase;

/**
 * This adapter subclass populates the mainScreenFragment's gridView with
 * only UR Cards.
 */

public class UrCardsAdapter extends BaseAdapter {
    private Context mContext;

    public UrCardsAdapter (Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return CardInfoDatabase.URCards.length;
    }

    @Override
    public Object getItem(int position) {
        return CardInfoDatabase.URCards[position];
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
        imageView.setImageResource(CardInfoDatabase.URCards[position].getCardIcon());
        return imageView;
    }
}
