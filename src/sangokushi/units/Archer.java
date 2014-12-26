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
public class Archer extends LightInfantry {
    public Archer() {
        super();
        
        name = "弓箭手";
        
        attack = 8;
        defence = 0;
        range = 5;
        
        weaponRequired = 2;
    }
}
