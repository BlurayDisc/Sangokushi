/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RuN
 */
public class Force
{
    private String forceName;
    private final List<Character> characterList;
    private final List<City> cityList;
    private Color forceColour;
    private int gold;
    private int food;
    private int goldIncome;
    private int foodIncome;
    
    private int soldier;
    private int soldierIncome;
    private int population;
    private int populationGrowth;
       
    public Force(String forceName)
    {
        this.forceName = forceName;
        forceColour = new Color(100, 100, 100);
        
        cityList = new ArrayList<>(5);
        characterList = new ArrayList<>(10);
        
    }
    
    private void joinCharList(City city)
    {
        for (int i = 0; i < city.getCharNumber(); i++)
        {
            characterList.add(city.get(i));
        }
    }
    
    public City getPrimaryCity(){
        return cityList.get(0);
    }
    
    public String getPrimaryCityName(){
        return cityList.get(0).getCityName();
    }
        
    public void increaseGold(int amount)
    {
        gold += amount;
    }
        
    public void increaseFood(int amount)
    {
        food += amount;
    }
    
    public void calcSoldier()
    {
        soldier = 0;
        int i;
        
        for (i = 0; i < cityList.size(); i++)
        {
            soldier = soldier+ cityList.get(i).getSoldiers();
        }
    }
    
    public void calcPopulation()
    {
        population = 0;
        int i;
        
        for (i = 0; i < cityList.size(); i++)
        {
            population = population + cityList.get(i).getPopulation();
        }
    }
    
    public boolean owns(City city){
        boolean ownsCity = false;

        if (cityList.contains(city)) {
            ownsCity = true;
        }
        return ownsCity;
    }
    
    public void add(City city)
    {
        cityList.add(city);
        joinCharList(city);
    }
    
    public List<Character> getCharacterList()
    {
        return characterList;
    }
    
    public List<City> getCityList()
    {
        return cityList;
    }
        
    public String getForceName()
    {
        return forceName;
    }
    
    public void setForceName(String name)
    {
        forceName = name;
    }
    
    public int getGold()
    {
        return gold;
    }
    
    public void setGold(int gold)
    {
        this.gold = gold;
    }
    
    public int getFood()
    {
        return food;
    }
    
    public void setFood(int food)
    {
        this.food = food;
    }
    
    public int getSoldier()
    {
        return soldier;
    }
    
    public int getPopulation()
    {
        return population;
    }
    
    public int getGoldIncome()
    {
        return goldIncome;
    }
    
    public void setGoldIncome(int goldIncome)
    {
        this.goldIncome = goldIncome;
    }

    public int getFoodIncome()
    {
        return foodIncome;
    }

    public void setFoodIncome(int foodIncome)
    {
        this.foodIncome = foodIncome;
    }

    public int getSoldierIncome()
    {
        return soldierIncome;
    }

    public void setSoldierIncome(int soldierIncome)
    {
        this.soldierIncome = soldierIncome;
    }

    public int getPopulationGrowth()
    {
        return populationGrowth;
    }

    public void setPopulationGrowth(int populationGrowth)
    {
        this.populationGrowth = populationGrowth;
    }
    
    public void setForceColor(int r, int g, int b)
    {
        forceColour = new Color(r, g, b);
    }
    
    public Color getForceColor()
    {
        return forceColour;
    }
    
}
