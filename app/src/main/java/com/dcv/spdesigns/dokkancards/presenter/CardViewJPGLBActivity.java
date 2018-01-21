package com.dcv.spdesigns.dokkancards.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;
import com.dcv.spdesigns.dokkancards.model.jp.JPDataHolder;

public class CardViewJPGLBActivity extends AppCompatActivity {

    private ImageView cardArtImageView;
    private TextView leaderSkillDescText;
    private TextView superAttackTitleText;
    private TextView superAttackDescText;
    private TextView passiveSkillTitleText;
    private TextView passiveSkillDescText;
    private TextView hpText;
    private TextView attText;
    private TextView defText;
    private TextView costText;

    private int position;
    private boolean calledFromGLB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;

        double screenInches = getScreenSizeInches(this);

        //Log.d("INCHES", "Size: " + screenInches + "\"");

        if((height == 2960 && width == 1440) && (screenInches >= 6.0 && screenInches <= 6.9)) { // S8+ WQHD
            setContentView(R.layout.activity_card_view_jpglb_s8_plus_wqhd);
        } else if((height == 2220 && width == 1080) && (screenInches >= 6.0 && screenInches <= 6.4)) { // S8+ FHD+
            setContentView(R.layout.activity_card_view_jpglb_s8_plus_fhd); //TODO:sp not chose by an s8+ fhd+
        } else if(height == 2076 && width == 1080) { // S8 FHD+
            Log.d("LAYOUT_SELECTED", "S8 FHD+");
            setContentView(R.layout.activity_card_view_jpglb_s8_fhd);
        } else { // S8 WQHD included
            Log.d("LAYOUT_SELECTED", "normal layout. Details: " + height + " " +  width);
            setContentView(R.layout.activity_card_view_jpglb);
        }

        // Retrieving the data sent over from MainActivity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null) {
            position = bundle.getInt("INDEX");
            calledFromGLB = bundle.getBoolean("CALLED_FROM");
        }

        // Initializing our views
        cardArtImageView = findViewById(R.id.cardArtImageView);
        leaderSkillDescText = findViewById(R.id.leaderSkillDesc);
        superAttackTitleText = findViewById(R.id.superAttackTitle);
        superAttackDescText = findViewById(R.id.superAttackDesc);
        hpText = findViewById(R.id.HP);
        attText = findViewById(R.id.ATT);
        defText = findViewById(R.id.DEF);
        costText = findViewById(R.id.COST);
//        passiveSkillTitleText = findViewById(R.id.passiveSkillTitle);
//        passiveSkillDescText = findViewById(R.id.passiveSkillDesc);

        leaderSkillDescText.setSelected(true);
        superAttackDescText.setSelected(true);
        superAttackTitleText.setSelected(true);
        initCardViewData(position);
    }

    /**
     * Initialize the card view's data with the right values depending on whether the intent was made
     * from the GLB Fragment(calledFromGLB = true) or from the JP Fragment(calledFromGLB = false)
     * @param position The selectedItem's position in the grid
     */
    private void initCardViewData(int position) {

            if(calledFromGLB) { //if true
                cardArtImageView.setImageResource(GlobalDataHolder.cardArts.get(position));
                leaderSkillDescText.setText(GlobalDataHolder.leaderSkills.get(position));
                superAttackTitleText.setText(GlobalDataHolder.superAttacksName.get(position));
                superAttackDescText.setText(GlobalDataHolder.superAttacksDesc.get(position));
                hpText.setText(GlobalDataHolder.hp.get(position).toString());
                attText.setText(GlobalDataHolder.att.get(position).toString());
                defText.setText(GlobalDataHolder.def.get(position).toString());
                costText.setText(GlobalDataHolder.cost.get(position).toString());
//                passiveSkillTitleText.setText(GlobalDataHolder.passiveSkillsName.get(position));
//                passiveSkillDescText.setText(GlobalDataHolder.passiveSkillsDesc.get(position));
            } else {
                cardArtImageView.setImageResource(JPDataHolder.cardArts.get(position));
                leaderSkillDescText.setText(JPDataHolder.leaderSkills.get(position));
                superAttackTitleText.setText(JPDataHolder.superAttacksName.get(position));
                superAttackDescText.setText(JPDataHolder.superAttacksDesc.get(position));
                hpText.setText(JPDataHolder.hp.get(position).toString());
                attText.setText(JPDataHolder.att.get(position).toString());
                defText.setText(JPDataHolder.def.get(position).toString());
                costText.setText(JPDataHolder.cost.get(position).toString());
//                passiveSkillTitleText.setText(JPDataHolder.passiveSkillsName.get(position));
//                passiveSkillDescText.setText(JPDataHolder.passiveSkillsDesc.get(position));
            }
    }

    /**
     * Calculates the current device's size in inches(with/without decorations)
     * @param activity Current activity
     * @return Returns the screen's size in "inches"
     */
    private static double getScreenSizeInches(Activity activity){
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        // since SDK_INT = 1;
        int mWidthPixels = displayMetrics.widthPixels;
        int mHeightPixels = displayMetrics.heightPixels;

        // includes window decorations (statusbar bar/menu bar)
        try {
            Point realSize = new Point();
            Display.class.getMethod("getRealSize", Point.class).invoke(display, realSize);
            mWidthPixels = realSize.x;
            mHeightPixels = realSize.y;
        } catch (Exception ignored) {}

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(mWidthPixels / dm.xdpi, 2);
        double y = Math.pow(mHeightPixels / dm.ydpi, 2);
        return Math.sqrt(x + y);
    }
}
