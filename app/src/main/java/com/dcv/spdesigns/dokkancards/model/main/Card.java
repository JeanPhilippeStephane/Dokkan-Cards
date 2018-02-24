package com.dcv.spdesigns.dokkancards.model.main;

import java.util.List;

/**
 * A card object
 */

public class Card {

    private Integer mCardArt;
    private Integer mCardIcon;
    private String mNameDescription;
    private String mLeaderSkill;
    private String mSuperAttackName;
    private String mSuperAttackDesc;
    private String mPassiveSkillName;
    private String mPassiveSkillDesc;
    private int hp;
    private int att;
    private int def;
    private int cost;
    private List<String> mLinkSkills;

    public Card(Integer mCardArt, Integer mCardIcon, String mNameDescription, String mLeaderSkill, String mSuperAttackName, String mSuperAttackDesc, String mPassiveSkillName, String mPassiveSkillDesc, int hp, int att, int def, int cost) {
        this.mCardArt = mCardArt;
        this.mCardIcon = mCardIcon;
        this.mNameDescription = mNameDescription;
        this.mLeaderSkill = mLeaderSkill;
        this.mSuperAttackName = mSuperAttackName;
        this.mSuperAttackDesc = mSuperAttackDesc;
        this.mPassiveSkillName = mPassiveSkillName;
        this.mPassiveSkillDesc = mPassiveSkillDesc;
        this.hp = hp;
        this.att = att;
        this.def = def;
        this.cost = cost;
        this.mLinkSkills = mLinkSkills;
    }

    public Integer getmCardArt() {
        return mCardArt;
    }

    public Integer getmCardIcon() {
        return mCardIcon;
    }

    public String getmName() {
        return mNameDescription;
    }

    public String getmLeaderSkill() {
        return mLeaderSkill;
    }

    public String getmSuperAttackName() {
        return mSuperAttackName;
    }

    public String getmSuperAttackDesc() {
        return mSuperAttackDesc;
    }

    public String getmPassiveSkillName() {
        return mPassiveSkillName;
    }

    public String getmPassiveSkillDesc() {
        return mPassiveSkillDesc;
    }

    public int getHp() {
        return hp;
    }

    public int getAtt() {
        return att;
    }

    public int getDef() {
        return def;
    }

    public int getCost() {
        return cost;
    }

    public List<String> getmLinkSkills() {
        return mLinkSkills;
    }
}
