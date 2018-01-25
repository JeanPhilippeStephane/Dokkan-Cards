package com.dcv.spdesigns.dokkancards.model;

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
            R.mipmap.second_super_saiyan_super_saiyan_trunks_teen_icon,
            R.mipmap.beyondthe_ferocious_flash_majin_vegeta_icon,
            R.mipmap.full_tilt_kamehameha_super_saiyan2_gohan_youth_icon,
            R.mipmap.merciless_condemnation_goku_black_super_saiyan_rose_and_zamasu_icon,
            R.mipmap.everlasting_legend_super_saiyan_goku_icon,
            R.mipmap.indestructible_saiyan_evil_legendary_super_saiyan_broly_icon,
            R.mipmap.reignof_terror_frieza_1st_form_icon,
            R.mipmap.budding_heart_piccolo_icon,
            R.mipmap.extraordinary_friendship_hercule_icon,
            R.mipmap.limitless_energy_androids_no17_and_no18_icon,
            R.mipmap.the_supreme_warrior_super_gogeta_icon,
            R.mipmap.surpassing_all_perfect_cell_icon,
            R.mipmap.super_warrior_of_destruction_legendary_super_saiyan_broly_icon,
            R.mipmap.crashing_maelstrom_omega_shenron_icon,
            R.mipmap.emperors_devotion_frieza_full_power_icon,
            R.mipmap.mystery_super_technique_super_saiyan3_goku_icon,
            R.mipmap.warriors_true_value_super_saiyan3_vegeta_icon,
            R.mipmap.ultimate_power_surge_ultimate_gohan_icon,
            R.mipmap.peerless_gleam_super_saiyan4_gogeta_icon,
            R.mipmap.heartless_destruction_buu_kid_icon,
            R.mipmap.unparalleled_super_saiyan_super_saiyan4_vegeta_icon,
            R.mipmap.evilincursionsuperjanemba_icon,
            R.mipmap.devastatingpunishmentbeerus_icon,
            R.mipmap.netherworlddemonsuperjanemba_icon,
            R.mipmap.transcendentmajinbuukid_icon,
            R.mipmap.theultimateandroidsuperno17_icon,
            R.mipmap.ultimatesupersaiyansupersaiyan4goku_icon,
            R.mipmap.ultimategohan_hiddenpowerunleashed_icon,
            R.mipmap.openthegatesofhellcoorafinalform_icon,
            R.mipmap.outshiningdarknesssupergogeta_icon,
            R.mipmap.naught_but_rampage_super_saiyan_3_gotenks_icon,
            R.mipmap.astoundingtransformationsupersaiyan3gokuangel_icon,
            R.mipmap.azureomnipotencesupersaiyangodssvegito_icon,
            R.mipmap.rosestainedsupersaiyanbokublacksupersaiyanrose_icon,
            R.mipmap.unparalleledgoldenkisupervegito_icon,
            R.mipmap.hopelessminusenergyomegashenron_icon,
            R.mipmap.darkmenacegokublack_icon,
            R.mipmap.thefinalclashmajinvegeta_icon,
            R.mipmap.fatalresolvesupersaiyan2gohanyouth_icon,
            R.mipmap.distortedjusticegokublackandzamasu_icon,
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
