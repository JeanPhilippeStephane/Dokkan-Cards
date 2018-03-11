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

import com.dcv.spdesigns.dokkancards.presenter.CardViewActivity;
import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.jp.JPDataHolder;
import com.dcv.spdesigns.dokkancards.model.jp.UserBoxJpImageAdapter;

import java.util.ArrayList;

public class UserBoxJPFragment extends Fragment {

    private GridView jpGridView;
    private UserBoxJpImageAdapter adapter;

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

        adapter = new UserBoxJpImageAdapter(this.getContext());

        jpGridView = view.findViewById(R.id.userBoxJPGridView);
        jpGridView.setAdapter(adapter);
        registerForContextMenu(jpGridView);

        // When an item from the GridView gets clicked
        jpGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent cardViewIntent = new Intent(getContext(), CardViewActivity.class);
                cardViewIntent.putExtra("Card Index",position);
                cardViewIntent.putExtra("Identifier", 2);
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

    private void removeCardFromJPBox(int position) {
        JPDataHolder.cards.remove(position);
        UserBoxJpImageAdapter.refreshFragmentView(adapter);
    }
}
