package com.dcv.spdesigns.dokkancards.model.filter_dialog.main;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;
import com.dcv.spdesigns.dokkancards.model.main.Card;
import com.dcv.spdesigns.dokkancards.model.main.CardInfoDatabase;

import java.util.ArrayList;

/**
 * This adapter subclass populates the mainScreenFragment's gridView with
 * only LR Cards.
 */

public class LrCardsAdapter extends BaseAdapter {
    private final Context mContext;

    public LrCardsAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return CardInfoDatabase.LRCards.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
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
        imageView.setImageResource(CardInfoDatabase.LRCards[position].getCardIcon());
        return imageView;
    }
}
