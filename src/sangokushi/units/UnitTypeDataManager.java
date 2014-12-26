/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sangokushi.units;

import sangokushi.data.GameParameters;

/**
 *
 * @author RuN
 */
public class UnitTypeDataManager implements GameParameters   
{
    private int hp, attack, defence, range;
    
    public UnitTypeDataManager()
    {
        hp = 0;
        attack = 0;
        defence = 0;
        range = 0;
    }
    
    public void setNewType(int type)
    {
        switch(type)
        {
            case UNIT_TYPE_SWORDMAN: setSword();
                 break;
            case UNIT_TYPE_SPEARMAN: setSpear();
                 break;
            case UNIT_TYPE_ARCHER: setArcher();
                 break;
            case UNIT_TYPE_CAVALRY: setCavalry();
                 break;
            case UNIT_TYPE_LONGSWORD: setLongSword();
                 break;
            case UNIT_TYPE_HALBERD: setHalberd();
                 break;
        }
    }
    
    private void setSword()
    {
        hp = 10;
        attack = 10;
        defence = 10;
        range = 0;
    }
    private void setSpear()
    {
        hp = 8;
        attack = 14;
        defence = 8;
        range = 1;
    }
    
    private void setArcher()
    {
        hp = 8;
        attack = 8;
        defence = 8;
        range = 5;
    }
    
    private void setCavalry()
    {
        hp = 18;
        attack = 15;
        defence = 15;
        range = 0;
    }
    
    private void setLongSword()
    {
        hp = 12;
        attack = 12;
        defence = 12;
        range = 1;
    }
    
    private void setHalberd()
    {
        hp = 11;
        attack = 19;
        defence = 11;
        range = 2;
    }
    
    private void setCrossBow()
    {
        hp = 8;
        attack = 15;
        defence = 12;
        range = 5;
    }
    
    private void setHeavyCalvary()
    {
        hp = 25;
        attack = 20;
        defence = 20;
        range = 0;
    }
    
    private void setShield()
    {
        hp = 30;
        attack = 6;
        defence = 25;
        range = 0;        
    }
    
    private void setCalvaryArcher()
    {
        hp = 20;
        attack = 10;
        defence = 8;
        range = 4;       
    }
    
    private void setRattanArmour()
    {
        hp = 30;
        attack = 20;
        defence = 30;
        range = 0;        
    }
    
    private void setChariot()
    {
        hp = 20;
        attack = 20;
        defence = 20;
        range = 2;        
    }
    
    private void setTigerCalvary()
    {
        hp = 25;
        attack = 30;
        defence = 20;
        range = 0;        
    }
    
    private void setWhiteCalvary()
    {
        
    }
    
    private void setTurkCalvary()
    {
        
    }
    
    public int getAtk()
    {
        return attack;
    }
    
    public int getDef()
    {
        return defence;
    }
    
    public int getHp()
    {
        return hp;
    }
    
    public int getRange()
    {
        return range;
    }
}
