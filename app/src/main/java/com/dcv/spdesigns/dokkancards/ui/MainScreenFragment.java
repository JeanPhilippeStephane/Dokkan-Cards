package com.dcv.spdesigns.dokkancards.ui;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.dcv.spdesigns.dokkancards.presenter.CardViewActivity;
import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.main.CardInfoDatabase;
import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;
import com.dcv.spdesigns.dokkancards.model.main.ImageAdapter;
import com.dcv.spdesigns.dokkancards.model.jp.JPDataHolder;
import com.dcv.spdesigns.dokkancards.presenter.MainActivity;

/**
 * This Fragment displays the app's Card Database to the user and allows him/her
 * to add cards to either one of his/her boxes and also view the selected card's details.
 */
public class MainScreenFragment extends Fragment {

    // Main Grid View
    private static GridView gridView;

    private static int filterDialogOptionSelected = 0; // holds the current filter option selected by the user

    public MainScreenFragment() {
        // Required empty public constructor
    }

    public static int getFilterDialogOptionSelected() {
        return filterDialogOptionSelected;
    }

    public static void setFilterDialogOptionSelected(int filterDialogOptionSelected) {
        MainScreenFragment.filterDialogOptionSelected = filterDialogOptionSelected;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        gridView = view.findViewById(R.id.gridViewLayout);
        setMainGridImageAdapter(new ImageAdapter(getContext())); // used to set the contents of the GridView-in this case images-
        registerForContextMenu(gridView);

        // When an item from the GridView gets clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create a new Intent...
                Intent intent = new Intent(getContext(),CardViewActivity.class);
                intent.putExtra("Card Index",position);
                intent.putExtra("Identifier", 0);
                intent.putExtra("filterOption",filterDialogOptionSelected);
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
        //Log.i("Position:", position +"");
        GlobalDataHolder.cards.add(CardInfoDatabase.cardDatabase[position]);
    }

    /**
     * Add the selected card's icon to the JP User Box
     * @param position Holds the selected card's position.
     * This is used to define where the icon should be placed in the grid &
     * which icon from the DataBase was selected
     */
    private void addSelectedCardToJPUserBox(int position) {
        JPDataHolder.cards.add(CardInfoDatabase.cardDatabase[position]);
    }

    /**
     * This method sets the main Grid's image adapter based on the selected
     * filter dialog option. Check the SortingDialog.java class for more info.
     * @param adapter The adapter which will be set as the main adapter for the grid.
     */
    public static void setMainGridImageAdapter(ListAdapter adapter) {
        gridView.setAdapter(adapter);
    }

}
