package com.dcv.spdesigns.dokkancards.model.glb;

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
 * This class serializes the ArrayList(s)<> found in the GlobalDataHolder class using
 * the Gson library and deserializes them
 */
public class SerializeGLBData {

    public static void Write(ArrayList<Card> cardList, Context context) {

        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = appPrefs.edit();
        Gson gson = new Gson();
        String cardsGLBJson = gson.toJson(cardList);
        editor.putString("cardsGLB",cardsGLBJson);
        editor.apply();
        editor.commit();
        Log.d("WriteData","Data written successfully!");
    }

    /**
     * Reads the cards list that gets saved when the app closes
     * @param context Get the app's current context
     * @return Returns an ArrayList of Card Objects containing the card info
     */
    public static ArrayList<Card> ReadCards(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String cardsGLBJson = appPrefs.getString("cardsGLB","");
        Type type = new TypeToken<ArrayList<Card>>(){}.getType();
        return gson.fromJson(cardsGLBJson,type);
    }

}
