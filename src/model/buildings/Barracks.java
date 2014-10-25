/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.buildings;

/**
 *
 * @author RuN
 */
public class Barracks extends Building
{
    private int soldierIncome;
    
    public Barracks()
    {
        super();
        setName("兵营");
        setCost(1500);
        setLevel(1);
        soldierIncome = 100;
    }
    
    @Override
    public int getSoldierIncome(){
        return soldierIncome;
    }
    
    @Override
    public void getInfo(){
        
    }
    

    public void setSoldierIncome(int unitIncome)
    {
        this.soldierIncome = unitIncome;
    }
}
