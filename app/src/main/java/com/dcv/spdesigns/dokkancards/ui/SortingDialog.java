package com.dcv.spdesigns.dokkancards.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Toast;

import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;
import com.dcv.spdesigns.dokkancards.model.glb.UserBoxGlbImageAdapter;
import com.dcv.spdesigns.dokkancards.model.main.CardInfoDatabase;
import com.dcv.spdesigns.dokkancards.model.main.ImageAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class instantiates a dialog which opens up
 * when the user clicks on the sorting icon in the toolbar
 */

public class SortingDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create a builder to make the dialog building process easier
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sorting Dialog");
        builder.setSingleChoiceItems(R.array.sorting_options, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 1) {
                            Toast.makeText(getActivity(), "2nd option Clicked", Toast.LENGTH_SHORT).show();
                            if (getActivity().getSupportFragmentManager().findFragmentByTag("GLOBAL_FRAGMENT") != null) {
                                sortGlobalListsBasedOnNameAndDesc();
                            }
                        }
                        for (int j = 0; j < GlobalDataHolder.cardNameAndDescription.size(); j++) {
                            Log.v("card_names", GlobalDataHolder.cardNameAndDescription.get(j));
                        }
                    }
                });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                createToast();
                dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        return builder.create();
    }

    private void sortGlobalListsBasedOnNameAndDesc() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            GlobalDataHolder.cardNameAndDescription.sort(new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    int id1 = GlobalDataHolder.cardNameAndDescription.indexOf(s1);
                    int id2 = GlobalDataHolder.cardNameAndDescription.indexOf(s2);

                    if (s1.equals(s2)) {
                        return 0;
                    } else if (s1.compareToIgnoreCase(s2) > 0) { //s1 is greater
                        //Collections.swap(UserBoxGlbImageAdapter.mGLBIcons,id2,id1);
                        swap(UserBoxGlbImageAdapter.mGLBIcons,id2,id1);
                        swap(GlobalDataHolder.cardNameAndDescription,id2,id1);
                        Log.d("case1","Called 1 time");
                        return 1;
                    } else if (s1.compareToIgnoreCase(s2) < 0) { //s1 is smaller
                        //Collections.swap(UserBoxGlbImageAdapter.mGLBIcons,id1,id2);
                        swap(UserBoxGlbImageAdapter.mGLBIcons,id1,id2);
                        swap(GlobalDataHolder.cardNameAndDescription,id1,id2);
                        Log.d("case2","Called 1 time");
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
        }
    }

    private void swap(List list,int objIndex1, int objIndex2) {
        for (int i=0;i < list.size(); i++) {
            Collections.swap(list,objIndex1,objIndex2);
            UserBoxGlbImageAdapter.refreshFragmentView(UserBoxGLBFragment.getUserBoxAdapter());
        }
    }

    private void createToast() {
        Toast.makeText(getActivity(), "Cards sorted based on AVG Stats", Toast.LENGTH_SHORT).show();
    }
}
