package com.dcv.spdesigns.dokkancards.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.dcv.spdesigns.dokkancards.BuildConfig;
import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;
import com.dcv.spdesigns.dokkancards.model.glb.SerializeGLBData;
import com.dcv.spdesigns.dokkancards.model.jp.JPDataHolder;
import com.dcv.spdesigns.dokkancards.model.jp.SerializeJPData;
import com.dcv.spdesigns.dokkancards.model.main.Card;
import com.dcv.spdesigns.dokkancards.ui.MainScreenFragment;
import com.dcv.spdesigns.dokkancards.ui.SortingDialog;
import com.dcv.spdesigns.dokkancards.ui.Tutorial;
import com.dcv.spdesigns.dokkancards.ui.UserBoxGLBFragment;
import com.dcv.spdesigns.dokkancards.ui.UserBoxJPFragment;

import java.util.ArrayList;
import java.util.List;

import de.cketti.library.changelog.ChangeLog;

/**
 * The main Activity class that handles most of the app's code!
 * Definitely the most important .java file in this project LOL.
 */
public class MainActivity extends AppCompatActivity {

    // NavMenu member vars
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle; // Button for toggling the side menu

    // Keeps the position of the previously selected menu item(0 : Home)
    private final int position = 0;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkForFirstRun();
        callReadDataMethodsGLB(this);
        callReadDataMethodsJP(this);
        initRarityListsGLB();
        initRarityListsJP();

        // Show the changelog dialog
        ChangeLog cl = new ChangeLog(this);
        if(cl.isFirstRun()){
            cl.getLogDialog().show();
        }

        mDrawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.drawer_open,R.string.drawer_closed); // Instantiating our button

        final Toolbar mToolbar = findViewById(R.id.navActionBar);
        setSupportActionBar(mToolbar); // check quick doq
        try {
            //noinspection ConstantConditions
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        mToolbar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setOverflowIcon(getDrawable(R.mipmap.ic_more_vert_white_24dp));

        // Sets the default selected menu item, to the Home item
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        // Used to help on check and uncheck menu items when the user clicks on them
        final List<MenuItem> items = new ArrayList<>();
        Menu menu;
        menu = navigationView.getMenu();

        // Fill the list with all the menu items
        for(int i=0; i<menu.size();i++) {
            items.add(menu.getItem(i));
        }

        // Set the default starting screen to the mainScreen
        FragmentManager startingScreenManager = getSupportFragmentManager();
        FragmentTransaction startingScreenTransaction = startingScreenManager.beginTransaction();

        MainScreenFragment fragment = new MainScreenFragment();
        startingScreenTransaction.add(R.id.FrameLayoutContainer, fragment);
        startingScreenTransaction.commit();

        // When an item inside the NavView gets clicked, then handle the event...
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @SuppressLint({"ResourceAsColor", "ResourceType"})
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            // Initializing these vars again for use in this inner class
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Replace the main Fragment in this activity based on the menu item selected
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        changeActionBarColor(1);
                        MainScreenFragment mainScreenFragment = new MainScreenFragment();
                        fragmentTransaction.replace(R.id.FrameLayoutContainer,mainScreenFragment,"MainFragment");
                        fragmentTransaction.commit();
                        break;
                    case R.id.nav_UserBoxGLB:
                        changeActionBarColor(2);

                        // Initializing the globalUserBox Fragment and passing  it the data bundle
                        UserBoxGLBFragment glbFragment = new UserBoxGLBFragment();
                        fragmentTransaction.replace(R.id.FrameLayoutContainer,glbFragment,"GLOBAL_FRAGMENT");
                        fragmentTransaction.commit();
                        break;
                    case R.id.nav_UserBoxJP:
                        changeActionBarColor(3);

                        // Initializing the jpUserBox fragment and passing it the data bundle
                        UserBoxJPFragment jpFragment = new UserBoxJPFragment();
                        fragmentTransaction.replace(R.id.FrameLayoutContainer,jpFragment,"JP_FRAGMENT");
                        fragmentTransaction.commit();
                        break;
                    case R.id.nav_events:
                        Toast.makeText(MainActivity.this, "Events are not available yet!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_feedback:
                        composeEmail(emails,"Feedback");
                        break;
                    case R.id.nav_contact_us:
                        composeEmail(emails,"Contact Us");
                        break;
                    case R.id.nav_website:
                        // Open the website's URL in a browser window
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse("https://spdesignsofficial.wixsite.com/spds"));
                        startActivity(intent);
                        break;
                    case R.id.dokkangamelink:
                        // Open Dokkan Battle's official website
                        Intent dokkanIntent = new Intent();
                        dokkanIntent.setAction(Intent.ACTION_VIEW);
                        dokkanIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                        dokkanIntent.setData(Uri.parse("http://dbz-dokkan.bngames.net/en/"));
                        startActivity(dokkanIntent);
                        break;
                    case R.id.nav_about:
                        Intent aboutIntent = new Intent(MainActivity.this, AboutPageActivity.class);
                        startActivity(aboutIntent);
                        break;
                    default:
                        return onNavigationItemSelected(item);
                }
                items.get(position).setChecked(false);
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return false;
            }
        });

        mDrawerLayout.addDrawerListener(mToggle);
        // Set the hamburger icon's color
        mToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.NavActionBarTextColor));
        mToggle.syncState();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Write all the data retrieved from BOTH GlobalDataHolder & JPDataHolder to the internal memory
        SerializeGLBData.Write(GlobalDataHolder.cards,this);
        SerializeJPData.Write(JPDataHolder.cards,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    // When an item from the Action Bar gets tapped, then...
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.options_tutorial:
                Intent tutorialIntent = new Intent(this,Tutorial.class);
                startActivity(tutorialIntent);
                return true;
            case R.id.options_help:
                composeEmail(supportEmail,"Dokkan Cards : [Subject here]");
                return true;
            case R.id.options_sorting:
                showSortingDialog();
                return true;
        }
        return mToggle.onOptionsItemSelected(item) || onOptionsItemSelected(item);
    }

    /**
     * Shows the sorting dialog in the current activity
     */
    private void showSortingDialog() {
        DialogFragment sortingDialog = new SortingDialog();
        sortingDialog.show(getSupportFragmentManager(),"Sorting");
    }

    private final String[] supportEmail = {"support@spdesignsofficial.com"};
    private final String[] emails = {"SPDesignsOfficial@outlook.com"};

    /**
     * Send an email to the @address with a @subject
     * @param addresses The email address(es) to send the email to
     * @param subject The email's subject
     */
    private void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, "[Your message here]");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calls every available Read method to retrieve all available data from the GLB database
     */
    public static void callReadDataMethodsGLB(Context context) {
        GlobalDataHolder.cards = SerializeGLBData.ReadCards(context);
        Log.i("Read Methods[GLB]", "ReadMethods called!");
    }

    /**
     * Calls every available Read method to retrieve all available data from the JP database
     */
    public static void callReadDataMethodsJP(Context context) {
        JPDataHolder.cards = SerializeJPData.ReadCards(context);
        Log.i("Read Methods[JP]", "ReadMethods called!");
    }

    private void checkForFirstRun() {

        final String PREFS_NAME = "DokkanCardsPrefs";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if(currentVersionCode == savedVersionCode) {
            // This is just a normal run
            Log.d("RUN_TYPE:" , "Normal Run");
            return;
        } else if(savedVersionCode == DOESNT_EXIST) { // This is a new install(or the user cleared the shared prefs)
            CallWriteDataMethods(this);
            Log.d("RUN_TYPE:", "New Install");
            // Showing the disclaimer dialog when the app starts for the first time
            Intent tutorialIntent = new Intent(this, Tutorial.class);
            startActivity(tutorialIntent);
        } else if(currentVersionCode > savedVersionCode) { // This is an upgrade
            CallWriteDataMethods(this);
            Log.d("RUN_TYPE:","Update");
        }

        // Update the shared prefs with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY,currentVersionCode).apply();

    }

    public static void CallWriteDataMethods(Context context) {
        // Write all the -empty- data from GlobalDataHolder to the internal memory to avoid a first time read error
        SerializeGLBData.Write(GlobalDataHolder.cards,context);
        // Write all the -empty- data from JPDataHolder to the internal memory to avoid a first time read error
        SerializeJPData.Write(JPDataHolder.cards,context);
    }

    /**
     * Changes the ActionBar's color to match the currently selected fragment's pallet
     * @param menuItemPos Determines which fragment is currently being displayed
     */
    private void changeActionBarColor(int menuItemPos) {
        switch (menuItemPos) {
            case 1:
                //noinspection ConstantConditions
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
                break;
            case 2:
                //noinspection ConstantConditions
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryGLB)));
                break;
            case 3:
                //noinspection ConstantConditions
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryJP)));
                break;
        }
    }

    /**
     * Initializes the various rarity related
     * lists for the GLB Fragment dynamically.
     */
    public static void initRarityListsGLB() {
        for (int i=0;i<GlobalDataHolder.cards.size();i++) {
            if(GlobalDataHolder.cards.get(i).getRarity().equals("LR")) {
                GlobalDataHolder.LRCards.add(GlobalDataHolder.cards.get(i));
            }
        }
        for (int i=0;i<GlobalDataHolder.cards.size();i++) {
            if(GlobalDataHolder.cards.get(i).getRarity().equals("UR")) {
                GlobalDataHolder.URCards.add(GlobalDataHolder.cards.get(i));
            }
        }
    }

    /**
     * Initializes the various rarity related
     * lists for the JP Fragment dynamically.
     */
    public static void initRarityListsJP() {
        for (int i=0;i<JPDataHolder.cards.size();i++) {
            if(JPDataHolder.cards.get(i).getRarity().equals("LR")) {
                JPDataHolder.LRCards.add(JPDataHolder.cards.get(i));
            }
        }
        for (int i=0;i<JPDataHolder.cards.size();i++) {
            if(JPDataHolder.cards.get(i).getRarity().equals("UR")) {
                JPDataHolder.URCards.add(JPDataHolder.cards.get(i));
            }
        }
    }
}
