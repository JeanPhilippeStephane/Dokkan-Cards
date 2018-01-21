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

import com.dcv.spdesigns.dokkancards.presenter.CardViewJPGLBActivity;
import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;
import com.dcv.spdesigns.dokkancards.model.glb.UserBoxGlbImageAdapter;

import java.util.ArrayList;

public class UserBoxGLBFragment extends Fragment {

    private GridView globalGridView;
    private UserBoxGlbImageAdapter adapter;

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

        // Get the data to be used to initialize the UserBoxGLBFragment's grid
        ArrayList<Integer> data = getArguments().getIntegerArrayList("DATA_LIST");
        adapter = new UserBoxGlbImageAdapter(this.getContext(), data);

        globalGridView = view.findViewById(R.id.userBoxGlbGridView);
        globalGridView.setAdapter(adapter);
        registerForContextMenu(globalGridView);

        // When an item from the GridView gets clicked
        globalGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent cardViewIntent = new Intent(getContext(), CardViewJPGLBActivity.class);
                cardViewIntent.putExtra("INDEX",position);
                cardViewIntent.putExtra("CALLED_FROM", true); // if true = from GLB
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

    private void removeCardFromGLBBox(int position) {
        GlobalDataHolder.dataHolder.remove(position);
        GlobalDataHolder.cardArts.remove(position);
        GlobalDataHolder.cardNameAndDescription.remove(position);
        GlobalDataHolder.leaderSkills.remove(position);
        GlobalDataHolder.superAttacksName.remove(position);
        GlobalDataHolder.superAttacksDesc.remove(position);
        GlobalDataHolder.passiveSkillsName.remove(position);
        GlobalDataHolder.passiveSkillsDesc.remove(position);
        GlobalDataHolder.hp.remove(position);
        GlobalDataHolder.att.remove(position);
        GlobalDataHolder.def.remove(position);
        GlobalDataHolder.cost.remove(position);
        UserBoxGlbImageAdapter.refreshFragmentView(adapter);
    }
}