package com.dcv.spdesigns.dokkancards.ui;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.dcv.spdesigns.dokkancards.presenter.CardViewActivity;
import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.jp.JPDataHolder;
import com.dcv.spdesigns.dokkancards.model.jp.UserBoxJpImageAdapter;

import java.util.ArrayList;

/**
 * The Fragment that displays the user's cards that were added
 * to the JP User Box and handles various actions like card removal, card details etc.
 */
public class UserBoxJPFragment extends Fragment {

    private static GridView jpGridView;
    private static int filterOptionSelectedJP = 0;

    public UserBoxJPFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_box_jp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserBoxJpImageAdapter adapter = new UserBoxJpImageAdapter(this.getContext());

        jpGridView = view.findViewById(R.id.userBoxJPGridView);
        setJPFragmentAdapter(adapter);
        registerForContextMenu(jpGridView);

        // When an item from the GridView gets clicked
        jpGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent cardViewIntent = new Intent(getContext(), CardViewActivity.class);
                cardViewIntent.putExtra("Card Index",position);
                cardViewIntent.putExtra("Identifier", 2);
                cardViewIntent.putExtra("filterOptionJP",getFilterOptionSelectedJP());
                startActivity(cardViewIntent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Card Options");
        menu.add(Menu.NONE,v.getId(),1,"Remove card");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // Get some extra info about the contextMenu
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int position = info.position; // clicked view's position

        if(item.getTitle().equals("Remove card")) {
            removeCardMessage();
            removeCardFromJPBox(position);
            callRefreshFragmentView(jpGridView.getAdapter());
        } else {
            return false;
        }
        return false;
    }

    /**
     * Creates a snackbar message, telling the user which card was removed from JP Box
     */
    private void removeCardMessage() {
        final Snackbar snackbar = Snackbar.make(jpGridView, "Card removed",Snackbar.LENGTH_LONG);

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
     * Refreshes the grid's image adapter in order to make any changes
     * viewable immediately.
     * @param adapter The adapter whose data is refreshed.
     */
    private void callRefreshFragmentView(ListAdapter adapter) {
        UserBoxJpImageAdapter.refreshFragmentView((UserBoxJpImageAdapter) adapter);
    }

    private void removeCardFromJPBox(int position) {
        if(JPDataHolder.cards.get(position).getRarity().equals("LR")) {
            for(int i = 0; i < JPDataHolder.LRCards.size();i++) {
                if(JPDataHolder.cards.get(position).getName().equals(JPDataHolder.LRCards.get(i).getName())) {
                    JPDataHolder.LRCards.remove(i);
                }
            }
        } else if(JPDataHolder.cards.get(position).getRarity().equals("UR")) {
            for(int i = 0; i < JPDataHolder.URCards.size();i++) {
                if(JPDataHolder.cards.get(position).getName().equals(JPDataHolder.URCards.get(i).getName())) {
                    JPDataHolder.URCards.remove(i);
                }
            }
        }
        JPDataHolder.cards.remove(position);
    }

    /**
     * Sets this fragment's grid image adapter.
     * This function is used by the SortingDialog class in order
     * to change the grid's image adapter based on the filter option
     * that the user has selected.
     * @param adapter The adapter which is used by the grid.
     */
    public static void setJPFragmentAdapter(ListAdapter adapter) {
        jpGridView.setAdapter(adapter);
    }

    public static int getFilterOptionSelectedJP() {
        return filterOptionSelectedJP;
    }

    public static void setFilterOptionSelectedJP(int filterOptionJP) {
        filterOptionSelectedJP = filterOptionJP;
    }
}
