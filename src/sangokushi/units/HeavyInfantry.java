/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sangokushi.units;

/**
 *
 * @author RuN
 */
public class HeavyInfantry  extends Unit {
    public HeavyInfantry() {
        name = "重步兵";
        
        hp = 15;        
        defence = 8;
        range = 0;
        speed = 1;
        
        armourRequired = 2;
        weaponRequired = 1;
        horseRequired = 0;
    }
}
