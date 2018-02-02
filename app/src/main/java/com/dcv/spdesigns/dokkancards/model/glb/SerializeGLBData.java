package com.dcv.spdesigns.dokkancards.model.glb;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This class serializes the ArrayLists<> found in the GlobalDataHolder class using
 * the Gson library and deserializes them
 */
public class SerializeGLBData {

    public static void Write(ArrayList<Integer> icons, ArrayList<Integer> cardArts, ArrayList<String> cardNameAndDescription,
                             ArrayList<String> leaderSkills, ArrayList<String> superAttackNames, ArrayList<String> superAttackDesc,
                             ArrayList<String> passiveSkillNames, ArrayList<String> passiveSkillDesc, ArrayList<Integer> hp, ArrayList<Integer> att, ArrayList<Integer> def, ArrayList<Integer> cost,  Context context) {

        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = appPrefs.edit();
        Gson gson = new Gson();
        String iconsJson = gson.toJson(icons);
        String cardArtsJson = gson.toJson(cardArts);
        String cardNameAndDescriptionJson = gson.toJson(cardNameAndDescription);
        String leaderSkillsJson = gson.toJson(leaderSkills);
        String superAttackNamesJson = gson.toJson(superAttackNames);
        String superAttackDescJson = gson.toJson(superAttackDesc);
        String passiveSkillNamesJson = gson.toJson(passiveSkillNames);
        String passiveSkillDescJson = gson.toJson(passiveSkillDesc);
        String HPjson = gson.toJson(hp);
        String ATTjson = gson.toJson(att);
        String DEFjson = gson.toJson(def);
        String costjson = gson.toJson(cost);
        editor.putString("Icons",iconsJson);
        editor.putString("CardArt",cardArtsJson);
        editor.putString("CardNameAndDesc",cardNameAndDescriptionJson);
        editor.putString("LeaderSkills",leaderSkillsJson);
        editor.putString("SANames",superAttackNamesJson);
        editor.putString("SADescs",superAttackDescJson);
        editor.putString("PassiveSkillNames",passiveSkillNamesJson);
        editor.putString("PassiveSkillDesc",passiveSkillDescJson);
        editor.putString("HP",HPjson);
        editor.putString("ATT", ATTjson);
        editor.putString("DEF", DEFjson);
        editor.putString("Cost", costjson);
        editor.apply();
        editor.commit();
        Log.d("WriteData","Data written successfully!");
    }

    /**
     * Reads the card icons list(name: dataHolder in GlobalDataHolder.java) that gets saved when the app closes
     * @param context Get the app's current context
     * @return Returns an ArrayList of Integers containing the card icons
     */
    public static ArrayList<Integer> ReadIcons(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String iconsJson = appPrefs.getString("Icons","");
        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
        return gson.fromJson(iconsJson,type);
    }

    /**
     * Reads the card's Art list(name: cardArts in GlobalDataHolder.java) that gets saved when the app closes
     * @param context Get the app's current context
     * @return Returns an ArrayList of Integers containing the card's arts
     */
    public static ArrayList<Integer> ReadCardArts(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String cardArtsJson = appPrefs.getString("CardArt","");
        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
        return gson.fromJson(cardArtsJson,type);
    }

    /**
     * Reads the card's name and description list(name: cardNameAndDescription in GlobalDataHolder.java) that gets saved when the app closes
     * @param context Get the app's current context
     * @return Returns an ArrayList of Strings containing the card's name and description
     */
    public static ArrayList<String> ReadcardNameAndDescription(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String cardNameAndDescriptionJson = appPrefs.getString("CardNameAndDesc","");
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        return gson.fromJson(cardNameAndDescriptionJson,type);
    }

    /**
     * Reads the card's leader skills list(name: leaderSkills in GlobalDataHolder.java) that gets saved when the app closes
     * @param context Get the app's current context
     * @return Returns an ArrayList of Strings containing the card's leader Skills
     */
    public static ArrayList<String> ReadLeaderSkills(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String leaderSkillsJson = appPrefs.getString("LeaderSkills","");
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        return gson.fromJson(leaderSkillsJson,type);
    }

    /**
     * Reads the card's super attack names list(name: superAttacksName in GlobalDataHolder.java) that gets saved when the app closes
     * @param context Get the app's current context
     * @return Returns an ArrayList of Strings containing the card's super attack names
     */
    public static ArrayList<String> ReadSuperAttacksName(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String superAttacksNameJson = appPrefs.getString("SANames","");
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        return gson.fromJson(superAttacksNameJson,type);
    }

    /**
     * Reads the card's super attack descriptions list(name: superAttacksDesc in GlobalDataHolder.java) that gets saved when the app closes
     * @param context Get the app's current context
     * @return Returns an ArrayList of Strings containing the card's super attack descriptions
     */
    public static ArrayList<String> ReadSuperAttacksDesc(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String superAttacksDescJson = appPrefs.getString("SADescs","");
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        return gson.fromJson(superAttacksDescJson,type);
    }

    /**
     * Reads the card's passive skills name list(name: passiveSkillsName in GlobalDataHolder.java) that gets saved when the app closes
     * @param context Get the app's current context
     * @return Returns an ArrayList of Strings containing the card's passive skills names
     */
    public static ArrayList<String> ReadPassiveSkillsName(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String passiveSkillsNameJson = appPrefs.getString("PassiveSkillNames","");
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        return gson.fromJson(passiveSkillsNameJson,type);
    }

    /**
     * Reads the card's passive skills description list(name: passiveSkillsDesc in GlobalDataHolder.java) that gets saved when the app closes
     * @param context Get the app's current context
     * @return Returns an ArrayList of Strings containing the card's passive skills descriptions
     */
    public static ArrayList<String> ReadPassiveSkillsDesc(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String passiveSkillsDescJson = appPrefs.getString("PassiveSkillDesc","");
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        return gson.fromJson(passiveSkillsDescJson,type);
    }

    /**
     * Reads the card's HP stat list(name: hp in GlobalDataHolder.java) that gets saved when the app closes
     * @param context Get the app's current context
     * @return Returns an ArrayList of Integers containing the card's hp
     */
    public static ArrayList<Integer> ReadHP(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String hpJson = appPrefs.getString("HP","");
        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
        return gson.fromJson(hpJson,type);
    }

    /**
     * Reads the card's ATT stat list(name: att in GlobalDataHolder.java) that gets saved when the app closes
     * @param context Get the app's current context
     * @return Returns an ArrayList of Integers containing the card's att
     */
    public static ArrayList<Integer> ReadATT(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String attJson = appPrefs.getString("ATT","");
        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
        return gson.fromJson(attJson,type);
    }

    /**
     * Reads the card's DEF stat list(name: def in GlobalDataHolder.java) that gets saved when the app closes
     * @param context Get the app's current context
     * @return Returns an ArrayList of Integers containing the card's def
     */
    public static ArrayList<Integer> ReadDEF(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String defJson = appPrefs.getString("DEF","");
        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
        return gson.fromJson(defJson,type);
    }

    /**
     * Reads the card's Cost value list
     * @param context Get the app's current context
     * @return Return an ArrayList of Integers containing the card's cost value
     */
    public static ArrayList<Integer> ReadCost(Context context) {
        SharedPreferences appPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String costJson = appPrefs.getString("Cost","");
        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
        return gson.fromJson(costJson,type);
    }

}
