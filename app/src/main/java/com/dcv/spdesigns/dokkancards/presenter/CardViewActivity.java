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
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

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
    private Button arrowButton;
    private int selectedItemPosition;
    private boolean isBtnClicked = false;

    // Listener member field for each layout's button. This listener will be used recursively
    private View.OnClickListener arrowButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // When the arrowButton is clicked, choose the right layout based on the button's state
            int resID = isBtnClicked ? R.layout.cardview_refined : R.layout.cardview_expand_details;

            setContentView(resID);

            // If we're in the first layout, initialize the cardArtImageView field
            if(isBtnClicked) {
                cardArtImageView = findViewById(R.id.cardArtImageView);
            }

            viewDefinitions(!isBtnClicked);
            initCardViewData(selectedItemPosition);
            setSelectedViewsInit();

            // Set the arrowButton's listener to this listener (recursively)
            arrowButton.setOnClickListener(arrowButtonListener);

            // toggle our flag field
            isBtnClicked = !isBtnClicked;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview_refined);

        // Retrieving the data sent over from MainActivity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            selectedItemPosition = bundle.getInt("Card Index");
        }
        //Toast.makeText(this, "WIDTH: " + SCREEN_WIDTH, Toast.LENGTH_SHORT).show();

        // Initializing our views
        cardArtImageView = findViewById(R.id.cardArtImageView);
        viewDefinitions(false);
        setSelectedViewsInit();

        initCardViewData(selectedItemPosition);

        arrowButton.setOnClickListener(arrowButtonListener);
    }

    /**
     * Sets the required textViews as selected to allow automatic scrolling
     */
    private void setSelectedViewsInit() {
        leaderSkillDescText.setSelected(true);
        superAttackTitleText.setSelected(true);
        superAttackDescText.setSelected(true);
        if (passiveSkillTitleText != null && passiveSkillDescText != null) {
            passiveSkillTitleText.setSelected(true);
            passiveSkillDescText.setSelected(true);
        }
    }

    /**
     * Adds the views's definitions
     *
     * @param initPassiveInfo used to decide whether or not the passiveSkillDesc & ..Title != null
     *                        so that they can be defined
     */
    private void viewDefinitions(boolean initPassiveInfo) {
        leaderSkillDescText = findViewById(R.id.leaderSkillDesc);
        superAttackTitleText = findViewById(R.id.superAttackTitle);
        superAttackDescText = findViewById(R.id.superAttackDesc);
        if (initPassiveInfo) {
            passiveSkillTitleText = findViewById(R.id.passiveSkillTitle);
            passiveSkillDescText = findViewById(R.id.passiveSkillDesc);
        } else {
            Log.d("Definitions", "Passive info == null");
        }
        hpText = findViewById(R.id.HP);
        attText = findViewById(R.id.ATT);
        defText = findViewById(R.id.DEF);
        costText = findViewById(R.id.COST);
        arrowButton = findViewById(R.id.arrowButton);
    }

    /**
     * Initialize the cardViewActivity's views with the data from the CardInfoDatabase.java class
     *
     * @param selectedItemPosition Used to initialize this activity's views if the intent was called from the MainScreen Fragment
     */
    private void initCardViewData(int selectedItemPosition) {
        if (cardArtImageView != null) {
            cardArtImageView.setImageResource(CardInfoDatabase.cardArts[selectedItemPosition]);
        }
        leaderSkillDescText.setText(CardInfoDatabase.leaderSkills[selectedItemPosition]);
        superAttackTitleText.setText(CardInfoDatabase.superAttacksName[selectedItemPosition]);
        superAttackDescText.setText(CardInfoDatabase.superAttacksDesc[selectedItemPosition]);
        if (passiveSkillTitleText != null && passiveSkillDescText != null) {
            passiveSkillTitleText.setText(CardInfoDatabase.passiveSkillsName[selectedItemPosition]);
            passiveSkillDescText.setText(CardInfoDatabase.passiveSkillsDesc[selectedItemPosition]);
        }
        hpText.setText(CardInfoDatabase.hp[selectedItemPosition].toString());
        attText.setText(CardInfoDatabase.att[selectedItemPosition].toString());
        defText.setText(CardInfoDatabase.def[selectedItemPosition].toString());
        costText.setText(CardInfoDatabase.cost[selectedItemPosition].toString());
    }
}
