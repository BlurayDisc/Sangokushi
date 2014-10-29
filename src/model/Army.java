/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import controller.UnitType;
import controller.UnitTypeDataManager;

/**
 *
 * @author RuN
 */
public class Army
{
    private final Character commander;
    private Character Lieutenant;
    private final UnitTypeDataManager typeData;
    private final int soldiers;
    private final int range;
    private final int commanderAttack, commanderDefence;
    private final int hpCoEfficiency, attackCoEfficiency, defenceCoEfficiency;
    private int legionHP, legionAttack, legionDefence;
    private boolean isDefeated;
    private boolean isCaptured;
    
    public Army(Character commander, int soldiers, int typeNumber)
    {
        typeData = new UnitTypeDataManager();
        typeData.setNewType(typeNumber);
        hpCoEfficiency = typeData.getHp();
        attackCoEfficiency = typeData.getAtk();
        defenceCoEfficiency = typeData.getDef();
        
        this.commander = commander;
        this.commander.calcCommanderStats();
        commanderAttack = this.commander.getDmg();
        commanderDefence = this.commander.getDef();
        
        range = typeData.getRange();
        this.soldiers = soldiers;
        isDefeated = false;
        isCaptured = false;
        
        calcArmyStats();
    }
          
    // HP -> MAX: 69000 => (23000 + rank bonus) * 3.0 (number of soldiers * army unit type co-efficient).
    // Attack -> MAX: 3600 => 120 * 30 (character attack * army unit typ co-efficient).
    // Defence -> MAX: 300 => 100 * 30 (character defence * army unit typ co-efficient).
    private void calcArmyStats()
    {
        legionHP = Math.round(soldiers * hpCoEfficiency / 10);
        legionAttack = Math.round(commanderAttack * attackCoEfficiency);
        legionDefence = Math.round(commanderDefence * defenceCoEfficiency);
    }
    
    public Character getCommander()
    {
        return commander;
    }
    
    public int getSoldiers()
    {
        return soldiers;
    }
    
    public void setDefeated()
    {
        isDefeated = true;
    }
    
    public void setCaptured()
    {
        isCaptured = true;
    }
    
    public boolean getIsDefeated()
    {
        return isDefeated;
    }
    
    public boolean getIsCaptured()
    {
        return isCaptured;
    }
    
    public int getDmg()
    {
        return legionAttack;
    }
    
    public int getDef()
    {
        return legionDefence;
    }
    
    public int getHP()
    {
        return legionHP;
    }
    
    public int getRange()
    {
        return range;
    }
    
    public UnitType getUnitTypt() {
        return UnitType.SWORDSMAN;
    }
}
