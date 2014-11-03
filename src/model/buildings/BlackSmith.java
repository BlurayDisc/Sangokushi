/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.buildings;

import model.weapons.Armour;
import model.weapons.Chariot;
import model.weapons.Equipment;
import model.weapons.Weapon;

/**
 *
 * @author RuN
 */
public class BlackSmith extends Building{

    private Equipment equip;
    
    public BlackSmith() {
        setName("锻造所");
        setCost(1500);
        setLevel(1);
    }
    
    public void setProduceWeapon() {
        equip = new Weapon();
    }
    
    public void setProduceArmour() {
        equip = new Armour();
    }
    
    public void setProduceChariot() {
        equip = new Chariot();
    }
    
    public int applyCost(int value) {
        if (value > equip.getRate()) {
            return equip.getCost() * equip.getRate();
        }
        return equip.getCost() * value;
    }
    
    @Override
    public void getInfo() {
        //
    }
    
}
