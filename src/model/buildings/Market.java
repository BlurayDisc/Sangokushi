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
public class Market extends Building
{
    private int goldIncome;
    
    public Market()
    {
        super();
        setName("市场");
        setCost(800);
        setLevel(1);
        goldIncome = 100;
    }
    
    @Override
    public int getGoldIncome() {
        return goldIncome;
    }
    
    @Override
    public void getInfo() {
        
    }

    public void setGoldIncome(int goldIncome)
    {
        this.goldIncome = goldIncome;
    }


}
