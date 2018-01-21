package com.dcv.spdesigns.dokkancards.model.jp;

import java.util.ArrayList;

/**
 * DokkanCards was
 * Created by Stelios Papamichail on 11/29/2017.
 * <p>
 * This file belongs to the com.dcv.spdesigns.dokkancards.model.jp package.
 */

public class JPDataHolder {

    public static ArrayList<Integer> dataHolder = new ArrayList<>();

    // Card Art References
    public static ArrayList<Integer> cardArts = new ArrayList<>();

    // Card Description And Name References
    //TODO:sp Use it for the sorting feature in a future update
    public static ArrayList<String> cardNameAndDescription = new ArrayList<>();

    // Leader Skill References
    public static ArrayList<String> leaderSkills = new ArrayList<>();

    // Super Attack Name References
    public static ArrayList<String> superAttacksName = new ArrayList<>();

    // Super Attack Description References
    public static ArrayList<String> superAttacksDesc = new ArrayList<>();

    // Passive Skill Name References
    public static ArrayList<String> passiveSkillsName = new ArrayList<>();

    // Passive Skill Description References
    public static ArrayList<String> passiveSkillsDesc = new ArrayList<>();

    // Holds each card's hp stats
    public static ArrayList<Integer> hp = new ArrayList<>();

    // Holds each card's att stats
    public static ArrayList<Integer> att = new ArrayList<>();

    // Holds each card's def stats
    public static ArrayList<Integer> def = new ArrayList<>();

    // Holds each card's cost value
    public static ArrayList<Integer> cost = new ArrayList<>();
}
