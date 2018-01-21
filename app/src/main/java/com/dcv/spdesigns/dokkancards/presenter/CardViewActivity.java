package com.dcv.spdesigns.dokkancards.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcv.spdesigns.dokkancards.BuildConfig;
import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.CardInfoDatabase;

/**
 * DokkanCards was
 * Created by Stelios Papamichail on 11/19/2017.
 * <p>
 * This file belongs to the com.dcv.spdesigns.dokkancards.ui package.
 */

public class CardViewActivity extends AppCompatActivity {

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

    private int selectedItemPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Log.d("INCHES", "Size: " + screenInches + "\"");

        setContentView(R.layout.cardview_refined);

        // Retrieving the data sent over from MainActivity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null) {
            selectedItemPosition = bundle.getInt("Card Index");
        }
        //Toast.makeText(this, "WIDTH: " + SCREEN_WIDTH, Toast.LENGTH_SHORT).show();

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
        superAttackTitleText.setSelected(true);
        superAttackDescText.setSelected(true);

        // Initialize the cardView.xml layout with the right data
        initCardViewData(selectedItemPosition);
    }

    /**
     * Initialize the cardViewActivity's views with the data from the CardInfoDatabase.java class
     * @param selectedItemPosition Used to initialize this activity's views if the intent was called from the MainScreen Fragment
     */
    private void initCardViewData(int selectedItemPosition) {

            cardArtImageView.setImageResource(CardInfoDatabase.cardArts[selectedItemPosition]);
            leaderSkillDescText.setText(CardInfoDatabase.leaderSkills[selectedItemPosition]);
            superAttackTitleText.setText(CardInfoDatabase.superAttacksName[selectedItemPosition]);
            superAttackDescText.setText(CardInfoDatabase.superAttacksDesc[selectedItemPosition]);
            hpText.setText(CardInfoDatabase.hp[selectedItemPosition].toString());
            attText.setText(CardInfoDatabase.att[selectedItemPosition].toString());
            defText.setText(CardInfoDatabase.def[selectedItemPosition].toString());
            costText.setText(CardInfoDatabase.cost[selectedItemPosition].toString());
//            passiveSkillTitleText.setText(CardInfoDatabase.passiveSkillsName[selectedItemPosition]);
//            passiveSkillDescText.setText(CardInfoDatabase.passiveSkillsDesc[selectedItemPosition]);
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
