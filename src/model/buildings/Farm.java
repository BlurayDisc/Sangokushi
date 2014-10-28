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
public class Farm extends Building {
    private int foodIncome;
    
    public Farm() {
        setName("农田");
        setCost(1200);
        setLevel(1);
        foodIncome = 1000;
    }
    
    @Override
    public void getInfo() {
        
    }
    
    @Override
    public int getFoodIncome() {
        return foodIncome;
    }

    public void setFoodIncome(int foodIncome) {
        this.foodIncome = foodIncome;
    }
}
