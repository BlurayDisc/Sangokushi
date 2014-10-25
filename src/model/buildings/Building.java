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
public abstract class Building
{
    private String name;
    private int cost;
    private int level;
    
    public Building()
    {
        name = "空地";
        cost = 0;
        level = 0;
    }
    
    public int getGoldIncome() {
        return 0;
    }
    
    public int getFoodIncome() {
        return 0;
    }
    
    public int getSoldierIncome() {
        return 0;
    }
    
    public abstract void getInfo();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getCost()
    {
        return cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }
    
    public void increaseLevel() {
        level++;
    }
    
    public String getLevelText() {
        String lvltext;
        
        if (level == 1) {
            lvltext = "";
        } else {
            lvltext = " " + level + "级";
        }
        return lvltext;
    }
}
