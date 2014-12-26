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
public class HeavyCavalry extends Unit {
    public HeavyCavalry() {
        name = "重骑兵";
        
        hp = 25;
        attack = 15;
        defence = 10;
        range = 0;
        speed = 2;
        
        armourRequired = 3;
        weaponRequired = 1;
        horseRequired = 1;
    }
}
