/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sangokushi.model;

/**
 *
 * @author RuN
 */
public class Ability
{
    private String name;
    private String description;
    private int damageBonus;
    private int defenceBonus;
    private int unitsKillCount;
    private int leadershipBonus;
    private int combatPowerBonus;
    private int politicsBonus;
    private int intelligenceBonus;
    private boolean criticalStrike;
    private boolean doubleStrike;
    private boolean stun;
    
    
    public Ability(String name)
    {
        this.name = name;
        
        damageBonus = 0;
        defenceBonus = 0;
        unitsKillCount = 0;
        leadershipBonus = 0;
        combatPowerBonus = 0;
        politicsBonus = 0;
        intelligenceBonus = 0;
        criticalStrike = false;
        doubleStrike = false;
        stun = false;
        description = " ";
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getDamageBonus()
    {
        return damageBonus;
    }

    public void setDamageBonus(int damageBonus)
    {
        this.damageBonus = damageBonus;
    }

    public int getDefenceBonus()
    {
        return defenceBonus;
    }

    public void setDefenceBonus(int defenceBonus)
    {
        this.defenceBonus = defenceBonus;
    }

    public int getUnitsKillCount()
    {
        return unitsKillCount;
    }

    public void setUnitsKillCount(int unitsKillCount)
    {
        this.unitsKillCount = unitsKillCount;
    }

    public int getLeadershipBonus()
    {
        return leadershipBonus;
    }

    public void setLeadershipBonus(int leadershipBonus)
    {
        this.leadershipBonus = leadershipBonus;
    }

    public int getCombatPowerBonus()
    {
        return combatPowerBonus;
    }

    public void setCombatPowerBonus(int combatPowerBonus)
    {
        this.combatPowerBonus = combatPowerBonus;
    }

    public int getPoliticsBonus()
    {
        return politicsBonus;
    }

    public void setPoliticsBonus(int politicsBonus)
    {
        this.politicsBonus = politicsBonus;
    }

    public int getIntelligenceBonus()
    {
        return intelligenceBonus;
    }

    public void setIntelligenceBonus(int intelligenceBonus)
    {
        this.intelligenceBonus = intelligenceBonus;
    }

    public boolean isCriticalStrike()
    {
        return criticalStrike;
    }

    public void setCriticalStrike(boolean criticalStrike)
    {
        this.criticalStrike = criticalStrike;
    }

    public boolean isDoubleStrike()
    {
        return doubleStrike;
    }

    public void setDoubleStrike(boolean doubleStrike)
    {
        this.doubleStrike = doubleStrike;
    }

    public boolean isStun()
    {
        return stun;
    }

    public void setStun(boolean stun)
    {
        this.stun = stun;
    }
}
