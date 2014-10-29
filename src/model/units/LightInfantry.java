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
public class LightInfantry  extends Unit {
    public LightInfantry() {
        name = "轻步兵";
        
        hp = 10;
        defence = 3;
        range = 0;
        speed = 1;
        
        armourRequired = 1;
        weaponRequired = 1;
        horseRequired = 0;
    }
}
