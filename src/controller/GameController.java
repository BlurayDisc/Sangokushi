/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.City;
import model.Force;
import model.Character;
import model.Database;
import model.Player;
import model.buildings.Building;

/**
 *
 * @author RuN
 */
public class GameController {
    
    // Singleton declaration
    private static final GameController instance = new GameController();
    
    // Game Constants
    public static final double POPULATION_INCREASE_RATE = 0.1;
    public static final int BUTTON_DELAY_TIME = 75;
    public static final int SOLDIER_NUMBER_PER_UNIT = 100;
    public static final int NUMBER_OF_DICE = 4;
    public static final int PENALISED_NUMBER_OF_DICE = 3;
    
    
    // Class members
    private final Player player;
    private final Database db;
    private City attackedCity;
    private int month;
    private int year;
    
    // Constructor
    private GameController() {
        db = Database.getInstance();
        player = Player.getInstance();
        attackedCity = null;
        month = 1;
        year = 0;
    }
    
    public void increaseIncome(City city) {
        
    }
    
    public void increaseResources() {
        // gold is increased every turn
        player.increaseGold(100);
        
        // soldier is increased every turn
        // increase soldiers code goes here
        
        // food is increased every season
        if (month == 3 || month == 6 || month == 9 || month == 12) {
            player.increaseFood(1000);
        }
    }
    
    public void increasePopulation() {
        // population is increased every year
        if (month == 12) {
            player.fixedPopulationIncrease();
        }
    }
            
    public void increaseTime() {
        month++;
        if (month == 13) {
            year++;
            month = 1;
        }
    }
    
    public int getYear(){
        return year;
    }
    
    public int getMonth(){
        return month;
    }
    
    public int getNumForces(){
        return db.getForceList().size();
    }
        
    public String getForceName(int forceNumber){
        return getForce(forceNumber).getForceName();
    }

    public Force getForce(int forceNumber){
        return db.getForceList().get(forceNumber);
    }
    
    public ArrayList<Force> getForceList(){
        return db.getForceList();
    }
    
    public City getCity(int cityNumber) {
        return db.getCityList().get(cityNumber - 1);
    }
    
    public ArrayList<City> getCityList() {
        return db.getCityList();
    }
    
    public City getCityByName(String inCityName) {
        City tempCity = null;
        for (City city: db.getCityList()){
            if (city.getCityName().equals(inCityName)){
                return city;
            }
        }
        return tempCity;
    }

    public ArrayList<Character> getCharacterList() {
        return db.getCharacterList();
    }
    
    public void setAttackedCity(int num) {
        attackedCity = this.getCity(num);
    }
    
    public void setAttackedCity(City city) {
        attackedCity = city;
    }
    
    public City getAttackedCity() {
        return attackedCity;
    }    
        
    public void update() {
       year = db.getYear();
    }
    
    public static GameController getInstance() {
        return instance;
    }
}
