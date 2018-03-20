package com.dcv.spdesigns.dokkancards.ui;

import android.os.Bundle;

import com.dcv.spdesigns.dokkancards.R;
import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.SlideFragmentBuilder;

/**
 * This class handles the app's tutorial and extends from IntroActivity
 */
public class Tutorial extends MaterialIntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(new SlideFragmentBuilder()
                .title("Main Screen")
                .description("Search through the entire Dokkan Cards database and find any card you like.")
                .image(R.drawable.image_main)
                .backgroundColor(R.color.mainScreen)
                .buttonsColor(R.color.colorPrimaryGLB)
                .build());


        addSlide(new SlideFragmentBuilder()
                .title("Tap & Long-Press Actions")
                .description("Tap on any card icon to view it's details or Long-press on it to add it to either one of your boxes")
                .image(R.drawable.image_long_tap)
                .backgroundColor(R.color.long_tap)
                .buttonsColor(R.color.colorPrimaryGLB)
                .build());

        addSlide(new SlideFragmentBuilder()
                .title("Side Menu")
                .description("Swipe from left to right to access a handful of menu options")
                .image(R.drawable.image_menu_slide)
                .backgroundColor(R.color.side_menu)
                .buttonsColor(R.color.colorPrimaryGLB)
                .build());

        addSlide(new SlideFragmentBuilder()
                .title("Auto-Save")
                .description("This app saves your box data automatically when you close the app so that you don't have to worry about saving manually")
                .image(R.drawable.image_auto_save)
                .backgroundColor(R.color.auto_save)
                .buttonsColor(R.color.colorPrimaryGLB)
                .build());

        addSlide(new SlideFragmentBuilder()
                .title("Data Security")
                .description("Your data is 100% safe since it cannot be accessed by anyone else.All data are stored locally on your device and not on a server or cloud")
                .image(R.drawable.image_data_security)
                .backgroundColor(R.color.data_security)
                .buttonsColor(R.color.colorPrimaryGLB)
                .build());

        addSlide(new SlideFragmentBuilder()
                .title("Donations")
                .description("Although donations are not mandatory, they help a lot in the app's development process. You can submit your donation via the website tab in the app's side menu.")
                .image(R.drawable.image_donation)
                .backgroundColor(R.color.donations)
                .buttonsColor(R.color.colorPrimaryGLB)
                .build());

       addSlide(new TutorialDisclaimerSlide());

        addSlide(new SlideFragmentBuilder()
                .title("That's it")
                .description("That's the end of this tutorial.You can access it at any time from the top right corner of the app")
                .image(R.drawable.image_tutorial)
                .backgroundColor(R.color.tutorial_end)
                .buttonsColor(R.color.colorPrimaryGLB)
                .build());
    }
}
