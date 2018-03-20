package com.dcv.spdesigns.dokkancards.ui;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.dcv.spdesigns.dokkancards.model.filter_dialog.glb.GlobalLrImageAdapter;
import com.dcv.spdesigns.dokkancards.model.filter_dialog.glb.GlobalUrImageAdapter;
import com.dcv.spdesigns.dokkancards.presenter.CardViewActivity;
import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;
import com.dcv.spdesigns.dokkancards.model.glb.UserBoxGlbImageAdapter;
import com.dcv.spdesigns.dokkancards.presenter.MainActivity;

import java.util.ArrayList;

/**
 * The Fragment that displays the user's cards that were added
 * to the Global User Box and handles various actions like card removal, card details etc.
 */
public class UserBoxGLBFragment extends Fragment {

    private static GridView globalGridView;

    private static int filterOptionSelected = 0;

    public UserBoxGLBFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_box_glb, container, false); // returns the view
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserBoxGlbImageAdapter mGlbImageAdapter = new UserBoxGlbImageAdapter(this.getContext());

        globalGridView = view.findViewById(R.id.userBoxGlbGridView);
        setGLBFragmentAdapter(mGlbImageAdapter);
        registerForContextMenu(globalGridView);

        // When an item from the GridView gets clicked
        globalGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent cardViewIntent = new Intent(getContext(), CardViewActivity.class);
                cardViewIntent.putExtra("Card Index",position);
                cardViewIntent.putExtra("Identifier", 1);
                cardViewIntent.putExtra("filterOptionGLB",getFilterOptionSelected());
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
            removeCardFromGLBBox(position);
            callRefreshFragmentView(globalGridView.getAdapter());
        } else {
            return false;
        }
        return false;
    }

    /**
     * Creates a snackbar message, telling the user which card was removed from GLB
     */
    private void removeCardMessage() {
        final Snackbar snackbar = Snackbar.make(globalGridView, "Card removed",Snackbar.LENGTH_LONG);

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
     * Removes the selected card from the grid's adapter.
     * @param position The selected card's position in the database.
     */
    private void removeCardFromGLBBox(int position) {
        if(GlobalDataHolder.cards.get(position).getRarity().equals("LR")) {
            for(int i = 0; i < GlobalDataHolder.LRCards.size();i++) {
                if(GlobalDataHolder.cards.get(position).getName().equals(GlobalDataHolder.LRCards.get(i).getName())) {
                    GlobalDataHolder.LRCards.remove(i);
                }
            }

        } else if(GlobalDataHolder.cards.get(position).getRarity().equals("UR")) {
            for(int i = 0; i < GlobalDataHolder.URCards.size();i++) {
                if(GlobalDataHolder.cards.get(position).getName().equals(GlobalDataHolder.URCards.get(i).getName())) {
                    GlobalDataHolder.URCards.remove(i);
                }
            }
        }
        GlobalDataHolder.cards.remove(position);
    }

    /**
     * Refreshes the grid's image adapter in order to make any changes
     * viewable immediately.
     * @param adapter The adapter whose data is refreshed.
     */
    private void callRefreshFragmentView(ListAdapter adapter) {
        if(adapter instanceof UserBoxGlbImageAdapter) {
            UserBoxGlbImageAdapter.refreshFragmentView((UserBoxGlbImageAdapter) adapter);
        } else if(adapter instanceof GlobalLrImageAdapter) {
            GlobalLrImageAdapter.refreshFragmentView((GlobalLrImageAdapter) adapter);
        } else if(adapter instanceof GlobalUrImageAdapter) {
            GlobalUrImageAdapter.refreshFragmentView((GlobalUrImageAdapter) adapter);
        } else {
            Log.v("ERROR_USERBOXGLOBAL","Error refreshing the adapter");
        }
    }

    /**
     * Sets this fragment's grid image adapter.
     * This function is used by the SortingDialog class in order
     * to change the grid's image adapter based on the filter option
     * that the user has selected.
     * @param adapter The adapter which is used by the grid.
     */
    public static void setGLBFragmentAdapter(ListAdapter adapter) {
        globalGridView.setAdapter(adapter);
    }

    public static int getFilterOptionSelected() {
        return filterOptionSelected;
    }

    public static void setFilterOptionSelected(int optionSelected) {
        filterOptionSelected = optionSelected;
    }
}