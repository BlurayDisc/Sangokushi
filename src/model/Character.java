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
    private String name;
    private int leadership, combatPower, intelligence, politics;
    private int damage, defence;
    private int commandPower;
    private Ability ability;
    private boolean isInjured = false; 
        
    public Character(String name, int leadership, int combatPower, int intelligence, int politics) {
        this.name = name;
        this.leadership = leadership;
        this.combatPower = combatPower;
        this.intelligence = intelligence;
        this.politics = politics;
        
        commandPower = calcCommandPower();
        damage = calcDmg();
        defence = calcDef();
    }
    
    public Character(String name) {
        this(name, 50, 50, 50, 50);
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
    private int calcCommandPower() {
        return 3000 + leadership * 200;
    }
    
    // Calculates the Dmg and Def for each Character.
    // Damage -> Max: 120, Min: 0
    // Defence -> Max: 100, Min: 0
    
    
            
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
    
    private int calcDmg() {
        if (leadership > 92) {                  // For high Leadership commanders
            return Math.round(leadership + intelligence * 2 / 10);
        } else if (combatPower > 92) {          // For high combatPower commanders
            return Math.round(combatPower * 8 / 10 + leadership * 4 / 10);     
        } else if (intelligence > 92) {         // For high intelligence commanders
            return Math.round(leadership * 8 / 10 + intelligence * 4 / 10);
        } else { // For everyone else
            return Math.round(leadership * 8 / 10 + combatPower * 4 / 10);
        }
    }
    
    private int calcDef() {
        if (leadership > 92) {                  // For high Leadership commanders
            return Math.round(leadership * 8 / 10 + intelligence * 2 / 10);
        } else if (combatPower > 92) {          // For high combatPower commanders
            return Math.round(combatPower * 6 / 10 + leadership * 4 / 10);    
        } else if (intelligence > 92) {         // For high intelligence commanders
            return Math.round(leadership * 6 / 10 + intelligence * 4 / 10);
        } else { // For everyone else
            return Math.round(leadership * 6 / 10 + combatPower * 4 / 10);
        }
    }
    
    
    public void reCalcStats() {        
        if (leadership > 92) {
        // For high Leadership commanders
            damage = Math.round(leadership + intelligence * 2 / 10);
            defence = Math.round(leadership * 8 / 10 + intelligence * 2 / 10);
        } else if (combatPower > 92) { 
        // For high combatPower commanders
            damage = Math.round(combatPower * 8 / 10 + leadership * 4 / 10);     
            defence = Math.round(combatPower * 6 / 10 + leadership * 4 / 10);
        } else if (intelligence > 92) {
        // For high intelligence commanders
            damage = Math.round(leadership * 8 / 10 + intelligence * 4 / 10);
            defence = Math.round(leadership * 6 / 10 + intelligence * 4 / 10);
        } else {
        // For everyone else
            damage = Math.round(leadership * 8 / 10 + combatPower * 4 / 10);
            defence = Math.round(leadership * 6 / 10 + combatPower * 4 / 10);
        }
    }
    public void increaseDamage(int increment) {
        damage = damage + increment;
    }
    
    public void reduceDamage(int reduction) {
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
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the leadership
     */
    public int getLeadership() {
        return leadership;
    }

    /**
     * Increases Leadership by 1;
     */
    public void increaseLeadership() {
        leadership++;
    }

    /**
     * @return the combatPower
     */
    public int getCombatPower() {
        return combatPower;
    }

    public void increaseCombatPower() {
        combatPower++;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void increaseIntelligence() {
        intelligence++;
    }

    public int getPolitics() {
        return politics;
    }

    public void increasePolitics() {
        politics++;
    }
    
    public Ability getAbility() {
        return ability;
    }
    
    public void setAbility(Ability ability) {
        this.ability = ability;
    }
    
    public int getCommandPower() {
        return commandPower;
    }
}
