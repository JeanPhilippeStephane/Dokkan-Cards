package com.dcv.spdesigns.dokkancards.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.dcv.spdesigns.dokkancards.R;

import agency.tango.materialintroscreen.SlideFragment;

public class TutorialDisclaimerSlide extends SlideFragment {

    private CheckBox checkBox;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tutorial_disclaimer_slide, container, false);
        checkBox = view.findViewById(R.id.checkBox);
        return view;
    }

    @Override
    public int backgroundColor() {
        return R.color.data_security;
    }

    @Override
    public int buttonsColor() {
        return R.color.colorPrimaryGLB;
    }

    @Override
    public boolean canMoveFurther() {
        return checkBox.isChecked();
    }

    @Override
    public String cantMoveFurtherErrorMessage() {
        return "You must agree to the disclaimer";
    }
}
