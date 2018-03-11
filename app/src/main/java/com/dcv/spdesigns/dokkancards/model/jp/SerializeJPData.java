package com.dcv.spdesigns.dokkancards.model.jp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.dcv.spdesigns.dokkancards.model.main.Card;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This class serializes the ArrayLists<> found in the JPDataHolder class using
 * the Gson library and deserialize them
 */
public class SerializeJPData {

    public static void Write(ArrayList<Card> cardsList, Context context) {

        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = appPrefs.edit();
        Gson gson = new Gson();
        String cardsJPJson = gson.toJson(cardsList);
        editor.putString("cardsJP",cardsJPJson);
        editor.apply();
        editor.commit();
        Log.d("WriteData[JP]","Data written successfully!");
    }


    /**
     * Reads the cards list that gets saved when the app closes
     * @param context Get the app's current context
     * @return Returns an ArrayList of Integers containing the card icons
     */
    public static ArrayList<Card> ReadCards(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String cardsJPJson = appPrefs.getString("cardsJP","");
        Type type = new TypeToken<ArrayList<Card>>(){}.getType();
        return gson.fromJson(cardsJPJson,type);
    }
}
