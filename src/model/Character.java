/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author RuN
 */
public class Character
{
    private String name, id;
    private int leadership, combatPower, intelligence, politics;
    private int damage, defence;
    private Ability ability;
    private boolean isInjured = false; 
    
    public Character(String id, String name, int leadership, int combatPower, int intelligence, int politics, Ability ability)
    {
        this.id = id;
        this.name = name;
        this.leadership = leadership;
        this.combatPower = combatPower;
        this.intelligence = intelligence;
        this.politics = politics;
        this.ability = ability;
        damage = 0;
        defence = 0;
    }
    
    public Character(String name, int leadership, int combatPower, int intelligence, int politics)
    {
        this.name = name;
        this.leadership = leadership;
        this.combatPower = combatPower;
        this.intelligence = intelligence;
        this.politics = politics;
        damage = 0;
        defence = 0;
    }
    
    public Character(String name)
    {
        this.name = name;
    }
    
    public void addLeadership(int increment)
    {
        leadership = increment + leadership;
    }
    
    public void addCombatPower(int increment)
    {
        combatPower = increment + combatPower;
    }
    
    public void addIntelligence(int increment)
    {
        intelligence = increment + intelligence;
    }
    
    public void addPolitics(int increment)
    {
        politics = increment + politics;
    }
    
    public void applyInjury()
    {
        leadership = leadership * 7 / 10;
        combatPower = combatPower * 5 / 10;
        intelligence = intelligence * 7 / 10;
        politics = politics * 8 / 10;
    }
    
    public void setInjuried(boolean isInjured)
    {
        this.isInjured = isInjured;
    }
    
    public boolean getIsInjured()
    {
        return isInjured;
    }
    
    public int getDmg()
    {        
        return damage;
    }
    
    public int getDef()
    {        
        return defence;
    }
    
    
    // Calculates the maximum number of soldiers this specific character can command.
    public int calcCommandPower()
    {
        int commandPower;
        
        commandPower = 3000 + leadership * 200;
        
        return commandPower;
    }
    
    
    // Calculates the Dmg and Def for each Character.
    // Damage -> Max: 120, Min: 0
    // Defence -> Max: 100, Min: 0
    public void calcCommanderStats()
    {        
        // For high Leadership commanders
        if (leadership > 92)
        {
            damage = Math.round(leadership + intelligence * 2 / 10);
            defence = Math.round(leadership * 8 / 10 + intelligence * 2 / 10);
        }
        // For high combatPower commanders
        else if (combatPower > 92)
        {
            damage = Math.round(combatPower * 8 / 10 + leadership * 4 / 10);     
            defence = Math.round(combatPower * 6 / 10 + leadership * 4 / 10);
        }
        // For high intelligence commanders
        else if (intelligence > 92)
        {
            damage = Math.round(leadership * 8 / 10 + intelligence * 4 / 10);
            defence = Math.round(leadership * 6 / 10 + intelligence * 4 / 10);
        }
        // For everyone else
        else
        {
            damage = Math.round(leadership * 8 / 10 + combatPower * 4 / 10);
            defence = Math.round(leadership * 6 / 10 + combatPower * 4 / 10);
        }
        
        // Apply Damage post-Adjustment (Range 10 to 154).
        
        // For Example:
        // 120 -> 154
        // 100 -> 110
        // 80 -> 74
        // 50 -> 35
        // 20 -> 14
        // damage = 10 + (int) Math.pow((damage / 10), 2);
        
        // Apply Defence post-ADjustment (Range 0 to 90)
        //defence *= 0.9;
    }
    public void increaseDamage(int increment)
    {
        damage = damage + increment;
    }
    
    public void reduceDamage(int reduction)
    {
        damage = damage - reduction;
    }
    
    public void increaseDamagePercent(int percent)
    {
        damage = damage * (100 + percent) / 100;
    }
    
    public void decreaseDamagePercent(int percent)
    {
        damage = damage * (100 - percent) /100;
    }
    
    public void increaseDefence(int increment)
    {
        defence = defence + increment;
    }
    
    public void reduceDefence(int reduction)
    {
        defence = defence - reduction;
    }
    
    public void increaseDefencePercent(int percent)
    {
        defence = defence * (100 + percent) / 100;
    }
    
    public void decreaseDefencePercent(int percent)
    {
        defence = defence * (100 - percent) /100;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the leadership
     */
    public int getLeadership()
    {
        return leadership;
    }

    /**
     * @param leadership the leadership to set
     */
    public void setLeadership(int leadership)
    {
        this.leadership = leadership;
    }

    /**
     * @return the combatPower
     */
    public int getCombatPower()
    {
        return combatPower;
    }

    /**
     * @param combatPower the combatPower to set
     */
    public void setCombatPower(int combatPower)
    {
        this.combatPower = combatPower;
    }

    /**
     * @return the intelligence
     */
    public int getIntelligence()
    {
        return intelligence;
    }

    /**
     * @param intelligence the intelligence to set
     */
    public void setIntelligence(int intelligence)
    {
        this.intelligence = intelligence;
    }

    /**
     * @return the politics
     */
    public int getPolitics()
    {
        return politics;
    }

    /**
     * @param politics the politics to set
     */
    public void setPolitics(int politics)
    {
        this.politics = politics;
    }

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }
    
    public Ability getAbility()
    {
        return ability;
    }
    
    public void setAbility(Ability ability)
    {
        this.ability = ability;
    }
}
