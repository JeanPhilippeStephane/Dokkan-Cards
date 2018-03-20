package com.dcv.spdesigns.dokkancards.presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;
import com.dcv.spdesigns.dokkancards.model.jp.JPDataHolder;
import com.dcv.spdesigns.dokkancards.model.main.CardInfoDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This Activity class is used to show a layout to the user which
 * will contain the selected Card's Full Details (passive skills,stats,sa name and desc) etc.
 */
public class CardFullDetailsActivity extends AppCompatActivity {

    private static String TAG = CardFullDetailsActivity.class.getSimpleName();

    @BindView(R.id.leaderSkillDesc) TextView leaderSkillDescText;
    @BindView(R.id.superAttackTitle) TextView superAttackTitleText;
    @BindView(R.id.superAttackDesc) TextView superAttackDescText;
    @BindView(R.id.passiveSkillTitle) TextView passiveSkillTitleText;
    @BindView(R.id.passiveSkillDesc) TextView passiveSkillDescText;
    @BindView(R.id.HP) TextView hpText;
    @BindView(R.id.ATT) TextView attText;
    @BindView(R.id.DEF) TextView defText;
    @BindView(R.id.COST) TextView costText;
    @BindView(R.id.arrowButton2) Button arrowButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview_expand_details);
        ButterKnife.bind(this);

        int selectedItemPos = getIntent().getIntExtra("selectedPos",0);
        int identifier = getIntent().getIntExtra("id",0); // A variable used to identify the fragment that created the Intent
        int filterOptionSelected = getIntent().getIntExtra("filterOption",-1);
        int filterOptionSelectedGLB = getIntent().getIntExtra("filterOptionGLB",-1);
        int filterOptionSelectedJP = getIntent().getIntExtra("filterOptionJP",-1);

        setSelectedViewsInit();
        callInitDataMethods(identifier, selectedItemPos,filterOptionSelected,filterOptionSelectedGLB,filterOptionSelectedJP);

        arrowButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // go back to the previous activity
            }
        });
    }

    /**
     * Sets the required textViews as selected to allow automatic scrolling
     */
    private void setSelectedViewsInit() {
        leaderSkillDescText.setSelected(true);
        superAttackTitleText.setSelected(true);
        superAttackDescText.setSelected(true);
        passiveSkillTitleText.setSelected(true);
        passiveSkillDescText.setSelected(true);
    }

    /**
     * Calls the appropriate initCardView() method based on the identifier field's value
     */
    private void callInitDataMethods(int identifier, int selectedItemPosition,int filterOptionSelected,int filterOptionSelectedGLB,int filterOptionSelectedJP) {
        if(identifier == 0) {
            initCardViewMainScreen(selectedItemPosition,filterOptionSelected);
        } else if(identifier == 1) {
            initCardViewGLB(selectedItemPosition,filterOptionSelectedGLB);
        } else if(identifier == 2) {
            initCardViewJP(selectedItemPosition,filterOptionSelectedJP);
        } else {
            Log.d("DEBUG", "Error initializing card view data");
        }
    }

    /**
     * Initialize the cardViewActivity's views with the data from the CardInfoDatabase.java class
     * @param selectedItemPosition Used to initialize this activity's views if the intent was called from the MainScreen Fragment
     */
    private void initCardViewMainScreen(int selectedItemPosition,int filterOptionSelected) {
        if(filterOptionSelected == 0) {
            leaderSkillDescText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getLeaderSkill());
            superAttackTitleText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getSuperAttackName());
            superAttackDescText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getSuperAttackDesc());
            passiveSkillTitleText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getPassiveSkillName());
            passiveSkillDescText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getPassiveSkillDesc());
            hpText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getHp());
            attText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getAtt());
            defText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getDef());
            costText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getCost());
        } else if(filterOptionSelected == 1) {
            leaderSkillDescText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getLeaderSkill());
            superAttackTitleText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getSuperAttackName());
            superAttackDescText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getSuperAttackDesc());
            passiveSkillTitleText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getPassiveSkillName());
            passiveSkillDescText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getPassiveSkillDesc());
            hpText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getHp());
            attText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getAtt());
            defText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getDef());
            costText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getCost());
        } else if(filterOptionSelected == 2) {
            leaderSkillDescText.setText(CardInfoDatabase.URCards[selectedItemPosition].getLeaderSkill());
            superAttackTitleText.setText(CardInfoDatabase.URCards[selectedItemPosition].getSuperAttackName());
            superAttackDescText.setText(CardInfoDatabase.URCards[selectedItemPosition].getSuperAttackDesc());
            passiveSkillTitleText.setText(CardInfoDatabase.URCards[selectedItemPosition].getPassiveSkillName());
            passiveSkillDescText.setText(CardInfoDatabase.URCards[selectedItemPosition].getPassiveSkillDesc());
            hpText.setText(CardInfoDatabase.URCards[selectedItemPosition].getHp());
            attText.setText(CardInfoDatabase.URCards[selectedItemPosition].getAtt());
            defText.setText(CardInfoDatabase.URCards[selectedItemPosition].getDef());
            costText.setText(CardInfoDatabase.URCards[selectedItemPosition].getCost());
        } else {

        }
    }

    /**
     * Initialize the cardViewActivity's views with the data from the GlobalDataHolder.java class
     * @param selectedItemPosition Used to initialize this activity's views if the intent was called from the Global Fragment
     */
    private void initCardViewGLB(int selectedItemPosition,int filterOptionSelectedGLB) {
        if(filterOptionSelectedGLB == 0) {
            leaderSkillDescText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getLeaderSkill());
            superAttackTitleText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getSuperAttackName());
            superAttackDescText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getSuperAttackDesc());
            passiveSkillTitleText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getPassiveSkillName());
            passiveSkillDescText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getPassiveSkillDesc());
            hpText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getHp());
            attText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getAtt());
            defText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getDef());
            costText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getCost());
        } else if(filterOptionSelectedGLB == 1) {
            leaderSkillDescText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getLeaderSkill());
            superAttackTitleText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getSuperAttackName());
            superAttackDescText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getSuperAttackDesc());
            passiveSkillTitleText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getPassiveSkillName());
            passiveSkillDescText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getPassiveSkillDesc());
            hpText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getHp());
            attText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getAtt());
            defText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getDef());
            costText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getCost());
        } else if(filterOptionSelectedGLB == 2) {
            leaderSkillDescText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getLeaderSkill());
            superAttackTitleText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getSuperAttackName());
            superAttackDescText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getSuperAttackDesc());
            passiveSkillTitleText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getPassiveSkillName());
            passiveSkillDescText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getPassiveSkillDesc());
            hpText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getHp());
            attText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getAtt());
            defText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getDef());
            costText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getCost());
        } else {
            Log.i(TAG,"Error loading the card info in the card view activity FULL DETAILS from GLOBAL!");
        }
    }

    /**
     * Initialize the cardViewActivity's views with the data from the JPDataHolder.java class
     * @param selectedItemPosition Used to initialize this activity's views if the intent was called from the JP Fragment
     */
    private void initCardViewJP(int selectedItemPosition,int filterOptionSelectedJP) {
        if(filterOptionSelectedJP == 0) {
            leaderSkillDescText.setText(JPDataHolder.cards.get(selectedItemPosition).getLeaderSkill());
            superAttackTitleText.setText(JPDataHolder.cards.get(selectedItemPosition).getSuperAttackName());
            superAttackDescText.setText(JPDataHolder.cards.get(selectedItemPosition).getSuperAttackDesc());
            passiveSkillTitleText.setText(JPDataHolder.cards.get(selectedItemPosition).getPassiveSkillName());
            passiveSkillDescText.setText(JPDataHolder.cards.get(selectedItemPosition).getPassiveSkillDesc());
            hpText.setText(JPDataHolder.cards.get(selectedItemPosition).getHp());
            attText.setText(JPDataHolder.cards.get(selectedItemPosition).getAtt());
            defText.setText(JPDataHolder.cards.get(selectedItemPosition).getDef());
            costText.setText(JPDataHolder.cards.get(selectedItemPosition).getCost());
        } else if(filterOptionSelectedJP == 1) {
            leaderSkillDescText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getLeaderSkill());
            superAttackTitleText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getSuperAttackName());
            superAttackDescText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getSuperAttackDesc());
            passiveSkillTitleText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getPassiveSkillName());
            passiveSkillDescText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getPassiveSkillDesc());
            hpText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getHp());
            attText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getAtt());
            defText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getDef());
            costText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getCost());
        } else if(filterOptionSelectedJP == 2) {
            leaderSkillDescText.setText(JPDataHolder.URCards.get(selectedItemPosition).getLeaderSkill());
            superAttackTitleText.setText(JPDataHolder.URCards.get(selectedItemPosition).getSuperAttackName());
            superAttackDescText.setText(JPDataHolder.URCards.get(selectedItemPosition).getSuperAttackDesc());
            passiveSkillTitleText.setText(JPDataHolder.URCards.get(selectedItemPosition).getPassiveSkillName());
            passiveSkillDescText.setText(JPDataHolder.URCards.get(selectedItemPosition).getPassiveSkillDesc());
            hpText.setText(JPDataHolder.URCards.get(selectedItemPosition).getHp());
            attText.setText(JPDataHolder.URCards.get(selectedItemPosition).getAtt());
            defText.setText(JPDataHolder.URCards.get(selectedItemPosition).getDef());
            costText.setText(JPDataHolder.URCards.get(selectedItemPosition).getCost());
        } else {
            Log.i(TAG,"Error loading the card info in the card view activity FULL DETAILS from JP!");
        }
    }
}
