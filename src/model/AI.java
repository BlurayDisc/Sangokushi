/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;

/**
 *
 * @author RuN
 */
public class AI
{
    private Force force;
    private Force player;
    private LinkedList<Force> neighbourList;
    private int networth;
    private int income;
    private int militaryPower;
    private int[] neighbourNetworth;
    
    public AI(Force force)
    {
        this.force = force;
        update();
    }
    
    public AI() {
        
    }
    
    public Army generateDummyArmy() {
        return new Army(new Character("RuN", 70, 70, 80, 50), 10000, 0);
    }
    
    public void update()
    {        
        calcOwnNetworth();
        calcOwnIncome();
        calcOwnMilitaryPower();
        
        getNeighbourForces();
        
        calcNeighbourNetworth();
        calcNeighbourIncome();
        calcNeighbourMilitaryPower();
    }
    
    // force data has to be read evey turn.
    public void readForceData(Force force)
    {
        this.force = force;
        update();
    }
    
    private void calcOwnNetworth()
    {
        networth = force.getGold();
    }
    
    private void calcOwnIncome()
    {
        
    }
    
    private void calcNeighbourNetworth()
    {
        for (int i = 0; i < neighbourList.size(); i++)
        {
            networth = 0;
        }
    }
    
    private void calcNeighbourIncome()
    {
        // getNeighbours(force.getCityList().getFirst());
    }
    
    private void calcOwnMilitaryPower()
    {
        force.calcSoldier();
        militaryPower = force.getSoldier();
    }
    
    private void calcNeighbourMilitaryPower()
    {
        
    }
    
    // imports a targeting force to be attacked
    private void attack(Force targetForce)
    {
        
    }
    
    private void getNeighbourForces()
    {
        neighbourList = new LinkedList<>();
        
        
    }
    
    // checks if the import force is the player.
    private boolean isPlayer(Force force)
    {
        boolean isPlayer = false;
        
        if (force.getForceName().equals(player.getForceName()))
            isPlayer = true;
        
        return isPlayer;
    }
}
