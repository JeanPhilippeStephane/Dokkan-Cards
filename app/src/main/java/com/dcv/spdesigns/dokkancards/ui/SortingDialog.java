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
                        //TODO:sp SORT THE CARDS
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

    private void createToast() {
        Toast.makeText(getActivity(), "Cards sorted based on AVG Stats", Toast.LENGTH_SHORT).show();
    }
}
