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
 * and provides a plethora of sorting options for the current
 * fragment
 */

public class SortingDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create a builder to make the dialog building process easier
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sorting Options");
        builder.setSingleChoiceItems(R.array.sorting_options, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 1) {
                            if (getActivity().getSupportFragmentManager().findFragmentByTag("GLOBAL_FRAGMENT") != null) {
                                sortGLBAlphabetically();
                            }
                        }
                        for (int j = 0; j < GlobalDataHolder.cardNameAndDescription.size() -1; j++) {
                            //Log.v("card_names", GlobalDataHolder.cardNameAndDescription.get(j));
                            UserBoxGlbImageAdapter.refreshFragmentView(UserBoxGLBFragment.getUserBoxAdapter());
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

    private void sortGLBAlphabetically() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            GlobalDataHolder.cardNameAndDescription.sort(new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    int s1Pos = GlobalDataHolder.cardNameAndDescription.indexOf(s1);
                    int s2Pos = GlobalDataHolder.cardNameAndDescription.indexOf(s2);
                    if(s1.equalsIgnoreCase(s2)) {
                        return 0;
                    } else if(s1.compareToIgnoreCase(s2) > 0)  { //greater than s2
                        Log.v("card_names","1");
                        Collections.swap(UserBoxGlbImageAdapter.mGLBIcons,s1Pos,s2Pos);
//                        UserBoxGlbImageAdapter.refreshFragmentView(UserBoxGLBFragment.getUserBoxAdapter());
                        return 1;
                    } else if(s1.compareToIgnoreCase(s2) < 0) { // less than s2
                        Log.v("card_names","-1");
                        Collections.swap(UserBoxGlbImageAdapter.mGLBIcons,s2Pos,s1Pos);
//                        UserBoxGlbImageAdapter.refreshFragmentView(UserBoxGLBFragment.getUserBoxAdapter());
                        return -1;
                    } else {
                        return 100; // error code
                    }
                }
            });
        }
    }

    private void createToast() {
        Toast.makeText(getActivity(), "Cards sorted based on AVG Stats", Toast.LENGTH_SHORT).show();
    }
}
