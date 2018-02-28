package com.dcv.spdesigns.dokkancards.model.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.dcv.spdesigns.dokkancards.R;

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
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
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
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // References to our images
    public static final Integer[] mThumbIds = {
            R.mipmap.darkintentionsmaskedsaiyan_icon,
            R.mipmap.grimreaperofdeathsrampagesupersaiyan3gotenks_icon,
            R.mipmap.welcometohellperfectcell_icon,
            R.mipmap.bladeofhopeanddreamssupersaiyantrunksfuture_icon,
            R.mipmap.fullpowersalutationaralenorimaki_icon,
            R.mipmap.passionofthewarriorracesupersaiyangodssvegeta_icon,
            R.mipmap.hideoussupersaiyansupersaiyanbroly_icon,
            R.mipmap.countdowntodespairmajinbuuultimategohan_icon,
            R.mipmap.eternalhorrorlegendarysupersaiyanbroly_icon,
            R.mipmap.wrathoftheabsolutegodfusionzamasu_icon,
            R.mipmap.startling_super_warrior_super_saiyan_trunks_teen_icon,
            R.mipmap.thehopeoftheuniversegoku_icon,
            R.mipmap.agentofdestructionsynshenron_icon,
            R.mipmap.fireofvengeancegoldenfrieza_icon
    };
}
