package com.dcv.spdesigns.dokkancards.ui;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.dcv.spdesigns.dokkancards.presenter.CardViewActivity;
import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.main.CardInfoDatabase;
import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;
import com.dcv.spdesigns.dokkancards.model.main.ImageAdapter;
import com.dcv.spdesigns.dokkancards.model.jp.JPDataHolder;

public class MainScreenFragment extends Fragment {

    // Main Grid View
    private GridView gridView;

    public MainScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        gridView = view.findViewById(R.id.gridViewLayout);
        gridView.setAdapter(new ImageAdapter(getContext())); // used to set the contents of the GridView-in this case images-
        registerForContextMenu(gridView);

        // When an item from the GridView gets clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create a new Intent...
                Intent intent = new Intent(getContext(),CardViewActivity.class);
                intent.putExtra("Card Index",position);
                intent.putExtra("Identifier", 0);
                startActivity(intent);
            }
        });
        return view;
    }

    // Create a Context Menu when an item in the GridView is long-pressed
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Card Options");
        //AdapterView.AdapterContextMenuInfo cmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.add(1,v.getId(),0, "Add Card to GLB");
        menu.add(2,v.getId(),0,"Add Card to JP");
    }

    // When an item in the context menu gets selected, call a method
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // Get some extra info about the contextMenu
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int position = info.position; // clicked view's position

        if(item.getTitle().equals("Add Card to GLB")) {
            addCardMessage("Card added to GLB");
            addSelectedCardToGlobalUserBox(position);
        } else if (item.getTitle().equals("Add Card to JP")) {
            addCardMessage("Card added to JP");
            addSelectedCardToJPUserBox(position);
        } else
        {
            return false;
        }
        return false;
    }

    /**
     * Creates a snackbar message, telling the user which card was added to which box
     * @param text Defines into which User Box the card was added
     */
    private void addCardMessage( String text) {
          final Snackbar snackbar = Snackbar.make(gridView, text ,Snackbar.LENGTH_LONG);

          snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(Color.MAGENTA);
        snackbar.show();
    }

    /**
     * Add the selected card's icon to the Global User Box
     * @param position Holds the selected card's position.
     * This is used to define where the icon should be placed in the grid &
     * which icon from the DataBase was selected
     */
    private void addSelectedCardToGlobalUserBox(int position) {
        GlobalDataHolder.dataHolder.add(ImageAdapter.mThumbIds[position]);
        GlobalDataHolder.cardArts.add(CardInfoDatabase.cardArts[position]);
        GlobalDataHolder.cardNameAndDescription.add(CardInfoDatabase.cardNameAndDescription[position]);
        GlobalDataHolder.leaderSkills.add(CardInfoDatabase.leaderSkills[position]);
        GlobalDataHolder.superAttacksName.add(CardInfoDatabase.superAttacksName[position]);
        GlobalDataHolder.superAttacksDesc.add(CardInfoDatabase.superAttacksDesc[position]);
        GlobalDataHolder.passiveSkillsName.add(CardInfoDatabase.passiveSkillsName[position]);
        GlobalDataHolder.passiveSkillsDesc.add(CardInfoDatabase.passiveSkillsDesc[position]);
        GlobalDataHolder.hp.add(CardInfoDatabase.hp[position]);
        GlobalDataHolder.att.add(CardInfoDatabase.att[position]);
        GlobalDataHolder.def.add(CardInfoDatabase.def[position]);
        GlobalDataHolder.cost.add(CardInfoDatabase.cost[position]);
    }

    /**
     * Add the selected card's icon to the JP User Box
     * @param position Holds the selected card's position.
     * This is used to define where the icon should be placed in the grid &
     * which icon from the DataBase was selected
     */
    private void addSelectedCardToJPUserBox(int position) {
        JPDataHolder.dataHolder.add(ImageAdapter.mThumbIds[position]);
        JPDataHolder.cardArts.add(CardInfoDatabase.cardArts[position]);
        JPDataHolder.cardNameAndDescription.add(CardInfoDatabase.cardNameAndDescription[position]);
        JPDataHolder.leaderSkills.add(CardInfoDatabase.leaderSkills[position]);
        JPDataHolder.superAttacksName.add(CardInfoDatabase.superAttacksName[position]);
        JPDataHolder.superAttacksDesc.add(CardInfoDatabase.superAttacksDesc[position]);
        JPDataHolder.passiveSkillsName.add(CardInfoDatabase.passiveSkillsName[position]);
        JPDataHolder.passiveSkillsDesc.add(CardInfoDatabase.passiveSkillsDesc[position]);
        JPDataHolder.hp.add(CardInfoDatabase.hp[position]);
        JPDataHolder.att.add(CardInfoDatabase.att[position]);
        JPDataHolder.def.add(CardInfoDatabase.def[position]);
        JPDataHolder.cost.add(CardInfoDatabase.cost[position]);
    }

}
