package com.dcv.spdesigns.dokkancards.model.filter_dialog.glb;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;

/**
 * This adapter class initializes the global gridView's imageViews with
 * just UR card icons(if the user has added any UR cards to his/her box).
 */

public class GlobalUrImageAdapter extends BaseAdapter {

    private Context mContext;

    public GlobalUrImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return GlobalDataHolder.URCards.size();
    }

    @Override
    public Object getItem(int position) {
        return GlobalDataHolder.URCards.get(position);
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
        imageView.setImageResource(GlobalDataHolder.URCards.get(position).getCardIcon());
        return imageView;
    }

    /**
     * Refreshes the fragment's view to display data changes
     * @param adapter Used to get the current instance of the fragment's adapter
     */
    public static void refreshFragmentView(GlobalUrImageAdapter adapter) {
        adapter.notifyDataSetChanged();
    }
}
