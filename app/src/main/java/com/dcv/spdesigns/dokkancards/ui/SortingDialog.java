package com.dcv.spdesigns.dokkancards.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.filter_dialog.glb.GlobalLrImageAdapter;
import com.dcv.spdesigns.dokkancards.model.filter_dialog.glb.GlobalUrImageAdapter;
import com.dcv.spdesigns.dokkancards.model.filter_dialog.jp.JpLrImageAdapter;
import com.dcv.spdesigns.dokkancards.model.filter_dialog.jp.JpUrImageAdapter;
import com.dcv.spdesigns.dokkancards.model.filter_dialog.main.LrCardsAdapter;
import com.dcv.spdesigns.dokkancards.model.filter_dialog.main.UrCardsAdapter;
import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;
import com.dcv.spdesigns.dokkancards.model.glb.UserBoxGlbImageAdapter;
import com.dcv.spdesigns.dokkancards.model.jp.UserBoxJpImageAdapter;
import com.dcv.spdesigns.dokkancards.model.main.ImageAdapter;

/**
 * This class instantiates a dialog which opens up
 * when the user clicks on the sorting icon in the toolbar
 * and provides a plethora of sorting/filter options for the current
 * fragment
 */
public class SortingDialog extends DialogFragment {

    private static String TAG = SortingDialog.class.getSimpleName();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create a builder to make the dialog building process easier
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Filter Options");
        builder.setSingleChoiceItems(R.array.sorting_options, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int itemPos) {

                        // This line is used to get the current fragment from the FrameLayout Container
                        android.support.v4.app.Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.FrameLayoutContainer);
                        SetDialogOption(itemPos, currentFragment);
                        MainScreenFragment.setFilterDialogOptionSelected(itemPos);
                    }
                });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
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

    /**
     * This method determines which sorting/filter option was selected
     * from the filter dialog and uses that option to dynamically change the
     * current fragment's image adapter so that it displays the desired Card
     * objects only.
     * @param itemPos The option that was selected in the filter dialog
     * @param currentFragment The current fragment whose image adapter will be(possibly) changed.
     */
    private void SetDialogOption(int itemPos, Fragment currentFragment) {
        if(itemPos == 0 && (currentFragment instanceof MainScreenFragment)) {
            createToast("Cards sorted based on AVG Stats");
            MainScreenFragment.setMainGridImageAdapter(new ImageAdapter(getActivity()));
            MainScreenFragment.setFilterDialogOptionSelected(itemPos);
        } else if(itemPos == 0 && (currentFragment instanceof UserBoxGLBFragment)) {
            createToast("Cards sorted based on AVG Stats");
            UserBoxGLBFragment.setGLBFragmentAdapter(new UserBoxGlbImageAdapter(getActivity()));
            UserBoxGLBFragment.setFilterOptionSelected(itemPos);
        } else if(itemPos == 0 && (currentFragment instanceof UserBoxJPFragment)) {
            createToast("Cards sorted based on AVG Stats");
            UserBoxJPFragment.setJPFragmentAdapter(new UserBoxJpImageAdapter(getContext()));
            UserBoxJPFragment.setFilterOptionSelectedJP(itemPos);
        } else if(itemPos == 1 && (currentFragment instanceof MainScreenFragment)) { // If the LR Cards item has been clicked
            createToast("Showing only LR Cards");
            MainScreenFragment.setMainGridImageAdapter(new LrCardsAdapter(getActivity()));
        } else if(itemPos == 1 && (currentFragment instanceof UserBoxGLBFragment)) {
            createToast("Showing only LR Cards");
            UserBoxGLBFragment.setGLBFragmentAdapter(new GlobalLrImageAdapter(getActivity()));
            UserBoxGLBFragment.setFilterOptionSelected(itemPos);
        } else if(itemPos == 1 && (currentFragment instanceof UserBoxJPFragment)) {
            createToast("Showing only LR Cards");
            UserBoxJPFragment.setJPFragmentAdapter(new JpLrImageAdapter(getContext()));
            UserBoxJPFragment.setFilterOptionSelectedJP(itemPos);
        } else if (itemPos == 2 && (currentFragment instanceof MainScreenFragment)) {
            createToast("Showing only UR Cards");
            MainScreenFragment.setMainGridImageAdapter(new UrCardsAdapter(getActivity()));
        } else if(itemPos == 2 && (currentFragment instanceof UserBoxGLBFragment)) {
            createToast("Showing only UR Cards");
            UserBoxGLBFragment.setGLBFragmentAdapter(new GlobalUrImageAdapter(getContext()));
            UserBoxGLBFragment.setFilterOptionSelected(itemPos);
        } else if(itemPos == 2 && (currentFragment instanceof UserBoxJPFragment)) {
            createToast("Showing only UR Cards");
            UserBoxJPFragment.setJPFragmentAdapter(new JpUrImageAdapter(getContext()));
            UserBoxJPFragment.setFilterOptionSelectedJP(itemPos);
        } else {
            Log.v(TAG,"Problem in the sorting dialog class!");
        }
    }

    private void createToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
