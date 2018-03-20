package com.dcv.spdesigns.dokkancards.presenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dcv.spdesigns.dokkancards.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;

/**
 * An Activity class that uses a Third Party Library to create
 * the app's "About Page".
 */

public class AboutPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element Facebook = new Element();
        Facebook.setTitle("Find me on Facebook");
        Facebook.setIconDrawable(R.drawable.facebook_icon_96dp);
        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fbUrl = "https://www.facebook.com/SteliosPapamichailspd";
                Intent fbIntent = new Intent(Intent.ACTION_VIEW);
                fbIntent.setData(Uri.parse(fbUrl));
                startActivity(fbIntent);
            }
        });

        Element Twitter = new Element();
        Twitter.setTitle("Find me on Twitter");
        Twitter.setIconDrawable(R.drawable.twitter_icon_96dp);
        Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String twitterUrl = "https://twitter.com/MikePapamichail";
                Intent twitterIntent = new Intent(Intent.ACTION_VIEW);
                twitterIntent.setData(Uri.parse(twitterUrl));
                startActivity(twitterIntent);
            }
        });

        Element Contributors = new Element();
        Contributors.setTitle("Contributors");
        Contributors.setIconDrawable(R.mipmap.ic_people_black_24dp);
        Intent contributorsIntent = new Intent(this,ContributorsActivity.class);
        Contributors.setIntent(contributorsIntent);

        Element version = new Element();
        version.setTitle("Version 0.8");

        Element Licenses = new Element();
        Licenses.setTitle("Open Source Licenses");
        Licenses.setIconDrawable(R.mipmap.ic_assignment_late_black_24dp);
        Intent licensesIntent = new Intent(this, OssLicensesMenuActivity.class);
        String title = "Open Source Licenses";
        licensesIntent.putExtra("title", title);
        Licenses.setIntent(licensesIntent);

        Element disclaimer = new Element();
        disclaimer.setTitle("Copyright Disclaimer");
        disclaimer.setIconDrawable(R.mipmap.ic_content_paste_black_24dp);
        Intent disclaimerIntent = new Intent(this,DisclaimerActivity.class);
        disclaimer.setIntent(disclaimerIntent);

        Element ThirdPartyLibs = new Element();
        ThirdPartyLibs.setTitle("Third Party Libraries");
        ThirdPartyLibs.setIconDrawable(R.mipmap.ic_library_books_black_24dp);
        Intent thirdPartyLibsIntent = new Intent(this,ThirdPartyLibsActivity.class);
        ThirdPartyLibs.setIntent(thirdPartyLibsIntent);


        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("Dokkan Cards is an application developed by SP Designs for the community of \n Dragon Ball Z Dokkan Battle.\n\n This app was made in Greece")
                .setImage(R.mipmap.ic_launcher)
                .addItem(version)
                .addGroup("Connect with me")
                .addItem(Facebook)
                .addItem(Twitter)
                .addEmail("support@spdesignsofficial.com","Support Email")
                .addWebsite("https://spdesignsofficial.wixsite.com/spds","Visit the website")
                .addItem(Contributors)
                .addItem(Licenses)
                .addItem(disclaimer)
                .addItem(ThirdPartyLibs)
                .create();
        setContentView(aboutPage);
    }
}
