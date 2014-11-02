/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author RuN
 */
public class Player {
    private static final Player instance = new Player();
    private Force playerForce;
    private City selectedCity;
    
    private Player() {
        playerForce = null;
        selectedCity = null;
    }
    
    public boolean owns(City city) {
        return playerForce.owns(city);
    }
    
    public String getForcePanelCityName() {
        if (playerForce == null){
            return "";
        } else if (playerForce.getCityList().size() == 1) {
            return playerForce.getCityList().get(0).getCityName();
        } else {
            return playerForce.getCityList().size() + "";
        }
    }
    
    public int getSoldiers() {
        if (playerForce == null) {return 0;}
        playerForce.calcSoldier();
        return playerForce.getSoldier();
    }
    
    public int getPopulation() {
        if (playerForce == null) {return 0;}
        playerForce.calcPopulation();
        return playerForce.getPopulation();
    }
    
    public String getPlayerName() {
        if (playerForce == null) {return "无";}
        return playerForce.getForceName();
    }
    
    public int getGold() {
        if (playerForce == null) {return 0;}
        return playerForce.getGold();
    }
    
    public int getFood() {
        if (playerForce == null) {return 0;}
        return playerForce.getFood();
    }
    
    public void increaseGold(int value) {
        playerForce.increaseGold(value);
    }
    
    public void increaseFood(int value) {
        playerForce.increaseFood(value);
    }
    
    public void fixedPopulationIncrease() {
        playerForce.getCityList().stream().forEach((city) -> {
            city.increasePopulationByRate();
        });
    }
    
    public void setPlayerForce(Force force) {
        this.playerForce = force;
    }
    
    public Force getPlayerForce() {
        return playerForce;
    }
    
    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }
    
    public City getSelectedCity() {
        return selectedCity;
    }
    
    public static Player getInstance() {
        return instance;
    }    
}
