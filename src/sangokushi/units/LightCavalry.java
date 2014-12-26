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
public class LightCavalry  extends Unit {
    public LightCavalry() {
        name = "轻骑兵";
        
        hp = 20;
        attack = 12;
        defence = 5;
        range = 0;
        speed = 3;
        
        armourRequired = 1;
        weaponRequired = 1;
        horseRequired = 1;
    }
}
