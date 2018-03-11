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
 * DokkanCards was
 * Created by Stelios Papamichail on 11/29/2017.
 * <p>
 * This file belongs to the com.dcv.spdesigns.dokkancards.model.jp package.
 */

public class UserBoxJpImageAdapter extends BaseAdapter {

    private final Context mContext;
    private ArrayList<Integer> mJPIcons = new ArrayList<>();


    public UserBoxJpImageAdapter(Context c) {
        mContext = c;
        setImageIconsJP();
    }

    @Override
    public int getCount() {
        return mJPIcons.size();
    }

    @Override
    public Object getItem(int i) {
        return mJPIcons.get(i);
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
        imageView.setImageResource(mJPIcons.get(position));
        return imageView;
    }

    /**
     * Initializes the cardIcons array with each card's icon from the global database
     * @return Returns an ArrayList of Integers
     */
    private void setImageIconsJP() {
        for (int i = 0; i < JPDataHolder.cards.size(); i++) {
            mJPIcons.add(JPDataHolder.cards.get(i).getCardIcon());
        }
    }

    /**
     * Refreshes the fragment's view to display data changes
     * @param adapter Used to get the current instance of the fragment's adapter
     */
    public static void refreshFragmentView(UserBoxJpImageAdapter adapter) {
        adapter.notifyDataSetChanged();
    }
}
