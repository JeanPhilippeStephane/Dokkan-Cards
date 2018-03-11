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

public class CardFullDetailsActivity extends AppCompatActivity {

    private TextView leaderSkillDescText;
    private TextView superAttackTitleText;
    private TextView superAttackDescText;
    private TextView passiveSkillTitleText;
    private TextView passiveSkillDescText;
    private TextView hpText;
    private TextView attText;
    private TextView defText;
    private TextView costText;
    private Button arrowButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview_expand_details);

        int selectedItemPos = getIntent().getIntExtra("selectedPos",0);
        int identifier = getIntent().getIntExtra("id",0);

        viewDefinitions();
        setSelectedViewsInit();
        callInitDataMethods(identifier, selectedItemPos);

        arrowButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // go back to the previous activity
            }
        });
    }

    /**
     * Define the layout's views
     */
    private void viewDefinitions() {
        leaderSkillDescText = findViewById(R.id.leaderSkillDesc);
        superAttackTitleText = findViewById(R.id.superAttackTitle);
        superAttackDescText = findViewById(R.id.superAttackDesc);
        passiveSkillTitleText = findViewById(R.id.passiveSkillTitle);
        passiveSkillDescText = findViewById(R.id.passiveSkillDesc);
        hpText = findViewById(R.id.HP);
        attText = findViewById(R.id.ATT);
        defText = findViewById(R.id.DEF);
        costText = findViewById(R.id.COST);
        arrowButton2 = findViewById(R.id.arrowButton2);
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
    private void callInitDataMethods(int identifier, int selectedItemPosition) {
        if(identifier == 0) {
            initCardViewMainScreen(selectedItemPosition);
        } else if(identifier == 1) {
            initCardViewGLB(selectedItemPosition);
        } else if(identifier == 2) {
            initCardViewJP(selectedItemPosition);
        } else {
            Log.d("DEBUG", "Error initializing card view data");
        }
    }

    /**
     * Initialize the cardViewActivity's views with the data from the CardInfoDatabase.java class
     * @param selectedItemPosition Used to initialize this activity's views if the intent was called from the MainScreen Fragment
     */
    private void initCardViewMainScreen(int selectedItemPosition) {
        leaderSkillDescText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getLeaderSkill());
        superAttackTitleText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getSuperAttackName());
        superAttackDescText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getSuperAttackDesc());
        passiveSkillTitleText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getPassiveSkillName());
        passiveSkillDescText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getPassiveSkillDesc());
        hpText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getHp());
        attText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getAtt());
        defText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getDef());
        costText.setText(CardInfoDatabase.cardDatabase[selectedItemPosition].getCost());
    }

    /**
     * Initialize the cardViewActivity's views with the data from the GlobalDataHolder.java class
     * @param selectedItemPosition Used to initialize this activity's views if the intent was called from the Global Fragment
     */
    private void initCardViewGLB(int selectedItemPosition) {
        leaderSkillDescText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getLeaderSkill());
        superAttackTitleText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getSuperAttackName());
        superAttackDescText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getSuperAttackDesc());
        passiveSkillTitleText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getPassiveSkillName());
        passiveSkillDescText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getPassiveSkillDesc());
        hpText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getHp());
        attText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getAtt());
        defText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getDef());
        costText.setText(GlobalDataHolder.cards.get(selectedItemPosition).getCost());
    }

    /**
     * Initialize the cardViewActivity's views with the data from the JPDataHolder.java class
     * @param selectedItemPosition Used to initialize this activity's views if the intent was called from the JP Fragment
     */
    private void initCardViewJP(int selectedItemPosition) {
        leaderSkillDescText.setText(JPDataHolder.cards.get(selectedItemPosition).getLeaderSkill());
        superAttackTitleText.setText(JPDataHolder.cards.get(selectedItemPosition).getSuperAttackName());
        superAttackDescText.setText(JPDataHolder.cards.get(selectedItemPosition).getSuperAttackDesc());
        passiveSkillTitleText.setText(JPDataHolder.cards.get(selectedItemPosition).getPassiveSkillName());
        passiveSkillDescText.setText(JPDataHolder.cards.get(selectedItemPosition).getPassiveSkillDesc());
        hpText.setText(JPDataHolder.cards.get(selectedItemPosition).getHp());
        attText.setText(JPDataHolder.cards.get(selectedItemPosition).getAtt());
        defText.setText(JPDataHolder.cards.get(selectedItemPosition).getDef());
        costText.setText(JPDataHolder.cards.get(selectedItemPosition).getCost());
    }
}
