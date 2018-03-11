package com.dcv.spdesigns.dokkancards.model.glb;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.dcv.spdesigns.dokkancards.model.main.CardInfoDatabase;

import java.util.ArrayList;

/**
 * This class is used to initialize the UserBoxGLBFragment's grid
 * with imageViews using the data from the GlobalDataHolder.java class
 */

public class UserBoxGlbImageAdapter extends BaseAdapter {

    private final Context mContext;

    private static ArrayList<Integer> mGLBIcons = new ArrayList<>();

    public UserBoxGlbImageAdapter(Context c) {
        mContext = c;
        setImageIconsGLB();
    }

    @Override
    public int getCount() {
        return mGLBIcons.size();
    }

    @Override
    public Object getItem(int i) {
        return mGLBIcons.get(i);
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
        imageView.setImageResource(mGLBIcons.get(position));
        return imageView;
    }

    /**
     * Initializes the cardIcons array with each card's icon from the global database
     * @return Returns an ArrayList of Integers
     */
    private void setImageIconsGLB() {
        for (int i = 0; i < GlobalDataHolder.cards.size(); i++) {
            mGLBIcons.add(GlobalDataHolder.cards.get(i).getCardIcon());
        }
    }

    /**
     * Refreshes the fragment's view to display data changes
     * @param adapter Used to get the current instance of the fragment's adapter
     */
    public static void refreshFragmentView(UserBoxGlbImageAdapter adapter) {
        adapter.notifyDataSetChanged();
    }
}
