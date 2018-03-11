package com.dcv.spdesigns.dokkancards.model.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.dcv.spdesigns.dokkancards.R;

import java.util.ArrayList;

/**
 * DokkanCards was
 * Created by Stelios Papamichail on 11/19/2017.
 * <p>
 * This file belongs to the com.dcv.spdesigns.dokkancards.model package.
 */

public class ImageAdapter extends BaseAdapter {
    private final Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
        setImageIcons();
    }

    @Override
    public int getCount() {
        return cardIcons.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
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
        imageView.setImageResource(cardIcons.get(position));
        return imageView;
    }

    // References to our images
    public static ArrayList<Integer> cardIcons = new ArrayList<>();

    /**
     * Initializes the cardIcons array with each card's icon from the actual database
     * @return Returns an ArrayList of Integers converted to an Array
     */
    private void setImageIcons() {

        for (int i=0; i < CardInfoDatabase.cardDatabase.length;i++) {
            cardIcons.add(CardInfoDatabase.cardDatabase[i].getCardIcon());
        }
    }
}
