/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.units;

/**
 *
 * @author RuN
 */
public abstract class Unit {
    
    protected String name;
    
    protected int hp;
    protected int attack;
    protected int defence;
    protected int range;
    protected int speed;
    
    protected int weaponRequired;
    protected int armourRequired;
    protected int horseRequired;
    
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getRange() {
        return range;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWeaponRequired() {
        return weaponRequired;
    }

    public int getArmourRequired() {
        return armourRequired;
    }

    public int getHorseRequired() {
        return horseRequired;
    }
    
}
