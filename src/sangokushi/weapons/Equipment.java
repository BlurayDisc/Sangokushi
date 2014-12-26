/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sangokushi.weapons;

/**
 *
 * @author RuN
 */
public abstract class Equipment {
    
    protected int cost;
    protected int rate;
    
    public Equipment() {
        cost = 0;
        rate = 0;
    }
    
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
    
}
