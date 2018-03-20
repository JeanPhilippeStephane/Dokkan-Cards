package com.dcv.spdesigns.dokkancards.model.main;

import java.util.List;

/**
 * An object class containing every field necessary to create an in-game card object.
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
    private String mType;
    private String mRarity;
    private int hp;
    private int att;
    private int def;
    private int cost;
    private List<String> mLinkSkills;

    public Card(Integer mCardArt, Integer mCardIcon, String mNameDescription, String mLeaderSkill, String mSuperAttackName, String mSuperAttackDesc, String mPassiveSkillName, String mPassiveSkillDesc, int hp, int att, int def, int cost,String rarity,String type) {
        this.mCardArt = mCardArt;
        this.mCardIcon = mCardIcon;
        this.mNameDescription = mNameDescription;
        this.mLeaderSkill = mLeaderSkill;
        this.mSuperAttackName = mSuperAttackName;
        this.mSuperAttackDesc = mSuperAttackDesc;
        this.mPassiveSkillName = mPassiveSkillName;
        this.mPassiveSkillDesc = mPassiveSkillDesc;
        mType = type;
        mRarity = rarity;
        this.hp = hp;
        this.att = att;
        this.def = def;
        this.cost = cost;
        this.mLinkSkills = mLinkSkills;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getRarity() {
        return mRarity;
    }

    public void setRarity(String rarity) {
        mRarity = rarity;
    }

    public Integer getCardArt() {
        return mCardArt;
    }

    public Integer getCardIcon() {
        return mCardIcon;
    }

    public String getName() {
        return mNameDescription;
    }

    public String getLeaderSkill() {
        return mLeaderSkill;
    }

    public String getSuperAttackName() {
        return mSuperAttackName;
    }

    public String getSuperAttackDesc() {
        return mSuperAttackDesc;
    }

    public String getPassiveSkillName() {
        return mPassiveSkillName;
    }

    public String getPassiveSkillDesc() {
        return mPassiveSkillDesc;
    }

    public String getHp() {
        return String.valueOf(hp);
    }

    public String getAtt() {
        return String.valueOf(att);
    }

    public String getDef() {
        return String.valueOf(def);
    }

    public String getCost() {
        return String.valueOf(cost);
    }

    public List<String> getLinkSkills() {
        return mLinkSkills;
    }
}
