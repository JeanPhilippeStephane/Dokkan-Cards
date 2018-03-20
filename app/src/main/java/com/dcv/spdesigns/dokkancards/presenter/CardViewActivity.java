package com.dcv.spdesigns.dokkancards.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.main.CardInfoDatabase;
import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;
import com.dcv.spdesigns.dokkancards.model.jp.JPDataHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Displays the selected card's details(sort version)
 */

public class CardViewActivity extends AppCompatActivity {

    private static final String TAG = CardViewActivity.class.getSimpleName();

    @BindView(R.id.cardArtImageView) ImageView cardArtImageView;
    @BindView(R.id.leaderSkillDesc) TextView leaderSkillDescText;
    @BindView(R.id.superAttackTitle) TextView superAttackTitleText;
    @BindView(R.id.superAttackDesc) TextView superAttackDescText;
//    @BindView(R.id.passiveSkillTitle) TextView passiveSkillTitleText;
//    @BindView(R.id.passiveSkillDesc) TextView passiveSkillDescText;
    @BindView(R.id.HP) TextView hpText;
    @BindView(R.id.ATT) TextView attText;
    @BindView(R.id.DEF) TextView defText;
    @BindView(R.id.COST) TextView costText;
    @BindView(R.id.arrowButton) Button arrowButton;

    private int selectedItemPosition;
    private int identifier;
    private int filterOptionSelected;
    private int filterOptionSelectedGLB;
    private int getFilterOptionSelectedJP;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview_refined);
        ButterKnife.bind(this);

        // Retrieving the data sent over from MainActivity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            selectedItemPosition = bundle.getInt("Card Index");
            selectedItemPosition = getIntent().getIntExtra("Card Index",-1);
            identifier = bundle.getInt("Identifier");
            filterOptionSelected = bundle.getInt("filterOption",-1);
            filterOptionSelectedGLB = bundle.getInt("filterOptionGLB",-1);
            getFilterOptionSelectedJP = bundle.getInt("filterOptionJP",-1);
        }

        setSelectedViewsInit();
        callInitDataMethods();

        arrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fullDetailsIntent = new Intent(CardViewActivity.this,CardFullDetailsActivity.class);
                fullDetailsIntent.putExtra("selectedPos",selectedItemPosition);
                fullDetailsIntent.putExtra("id",identifier);
                fullDetailsIntent.putExtra("filterOption",filterOptionSelected);
                fullDetailsIntent.putExtra("filterOptionGLB",filterOptionSelectedGLB);
                fullDetailsIntent.putExtra("filterOptionJP",getFilterOptionSelectedJP);
                startActivity(fullDetailsIntent);
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
    }

    /**
     * Calls the appropriate initCardView() method based on the identifier field's value
     */
    private void callInitDataMethods() {
        if(identifier == 0) {
            initCardViewMainScreen(selectedItemPosition,filterOptionSelected);
        } else if(identifier == 1) {
            initCardViewGLB(selectedItemPosition,filterOptionSelectedGLB);
        } else if(identifier == 2) {
            initCardViewJP(selectedItemPosition,getFilterOptionSelectedJP);
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
            cardArtImageView.setImageResource(CardInfoDatabase.cardDatabase[selectedItemPosition].getCardArt());
            leaderSkillDescText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getLeaderSkill());
            superAttackTitleText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getSuperAttackName());
            superAttackDescText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getSuperAttackDesc());
            hpText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getHp());
            attText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getAtt());
            defText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getDef());
            costText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getCost());
        } else if(filterOptionSelected == 1) {
            cardArtImageView.setImageResource(CardInfoDatabase.LRCards[selectedItemPosition].getCardArt());
            leaderSkillDescText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getLeaderSkill());
            superAttackTitleText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getSuperAttackName());
            superAttackDescText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getSuperAttackDesc());
            hpText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getHp());
            attText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getAtt());
            defText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getDef());
            costText.setText(CardInfoDatabase.LRCards[selectedItemPosition].getCost());
        } else if(filterOptionSelected == 2) {
            cardArtImageView.setImageResource(CardInfoDatabase.URCards[selectedItemPosition].getCardArt());
            leaderSkillDescText.setText(CardInfoDatabase.URCards[selectedItemPosition].getLeaderSkill());
            superAttackTitleText.setText(CardInfoDatabase.URCards[selectedItemPosition].getSuperAttackName());
            superAttackDescText.setText(CardInfoDatabase.URCards[selectedItemPosition].getSuperAttackDesc());
            hpText.setText(CardInfoDatabase.URCards[selectedItemPosition].getHp());
            attText.setText(CardInfoDatabase.URCards[selectedItemPosition].getAtt());
            defText.setText(CardInfoDatabase.URCards[selectedItemPosition].getDef());
            costText.setText(CardInfoDatabase.URCards[selectedItemPosition].getCost());
        } else {
            Log.i("CardViewActivity","Error loading the card info in the card view activity");
        }
    }

    /**
     * Initialize the cardViewActivity's views with the data from the GlobalDataHolder.java class
     * @param selectedItemPosition Used to initialize this activity's views if the intent was called from the Global Fragment
     */
    private void initCardViewGLB(int selectedItemPosition,int filterOptionSelectedGLB) {
        if(filterOptionSelectedGLB == 0) {
            cardArtImageView.setImageResource(GlobalDataHolder.cards.get(selectedItemPosition).getCardArt());
            leaderSkillDescText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getLeaderSkill());
            superAttackTitleText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getSuperAttackName());
            superAttackDescText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getSuperAttackDesc());
            hpText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getHp());
            attText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getAtt());
            defText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getDef());
            costText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getCost());
        } else if(filterOptionSelectedGLB == 1) {
            cardArtImageView.setImageResource(GlobalDataHolder.LRCards.get(selectedItemPosition).getCardArt());
            leaderSkillDescText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getLeaderSkill());
            superAttackTitleText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getSuperAttackName());
            superAttackDescText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getSuperAttackDesc());
            hpText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getHp());
            attText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getAtt());
            defText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getDef());
            costText.setText(GlobalDataHolder.LRCards.get(selectedItemPosition).getCost());
        } else if(filterOptionSelectedGLB == 2) {
            cardArtImageView.setImageResource(GlobalDataHolder.URCards.get(selectedItemPosition).getCardArt());
            leaderSkillDescText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getLeaderSkill());
            superAttackTitleText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getSuperAttackName());
            superAttackDescText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getSuperAttackDesc());
            hpText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getHp());
            attText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getAtt());
            defText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getDef());
            costText.setText(GlobalDataHolder.URCards.get(selectedItemPosition).getCost());
        } else {
            Log.i("CardViewActivity","Error loading the card info in the card view activity from GLOBAL!");
        }
    }

    /**
     * Initialize the cardViewActivity's views with the data from the JPDataHolder.java class
     * @param selectedItemPosition Used to initialize this activity's views if the intent was called from the JP Fragment
     */
    private void initCardViewJP(int selectedItemPosition,int filterOptionSelectedJP) {
        if(filterOptionSelectedJP == 0) {
            cardArtImageView.setImageResource(JPDataHolder.cards.get(selectedItemPosition).getCardArt());
            leaderSkillDescText.setText(JPDataHolder.cards.get(selectedItemPosition).getLeaderSkill());
            superAttackTitleText.setText(JPDataHolder.cards.get(selectedItemPosition).getSuperAttackName());
            superAttackDescText.setText(JPDataHolder.cards.get(selectedItemPosition).getSuperAttackDesc());
            hpText.setText(JPDataHolder.cards.get(selectedItemPosition).getHp());
            attText.setText(JPDataHolder.cards.get(selectedItemPosition).getAtt());
            defText.setText(JPDataHolder.cards.get(selectedItemPosition).getDef());
            costText.setText(JPDataHolder.cards.get(selectedItemPosition).getCost());
        } else if(filterOptionSelectedJP == 1) {
            cardArtImageView.setImageResource(JPDataHolder.LRCards.get(selectedItemPosition).getCardArt());
            leaderSkillDescText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getLeaderSkill());
            superAttackTitleText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getSuperAttackName());
            superAttackDescText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getSuperAttackDesc());
            hpText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getHp());
            attText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getAtt());
            defText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getDef());
            costText.setText(JPDataHolder.LRCards.get(selectedItemPosition).getCost());
        } else if(filterOptionSelectedJP == 2) {
            cardArtImageView.setImageResource(JPDataHolder.URCards.get(selectedItemPosition).getCardArt());
            leaderSkillDescText.setText(JPDataHolder.URCards.get(selectedItemPosition).getLeaderSkill());
            superAttackTitleText.setText(JPDataHolder.URCards.get(selectedItemPosition).getSuperAttackName());
            superAttackDescText.setText(JPDataHolder.URCards.get(selectedItemPosition).getSuperAttackDesc());
            hpText.setText(JPDataHolder.URCards.get(selectedItemPosition).getHp());
            attText.setText(JPDataHolder.URCards.get(selectedItemPosition).getAtt());
            defText.setText(JPDataHolder.URCards.get(selectedItemPosition).getDef());
            costText.setText(JPDataHolder.URCards.get(selectedItemPosition).getCost());
        } else {
            Log.i("CardViewActivity","Error loading the card info in the card view activity from JP!");
        }
    }
}
