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
import model.buildings.Building;

/**
 *
 * @author RuN
 */
public class GameController implements GameParameters {
    
    private static final GameController instance = new GameController();
    private final Database db;
    
    private Force player;
    private City selectedCity, attackedCity;
    private int month;
    private int year;

    private GameController() {
        db = Database.getInstance();
        
        selectedCity = null; 
        attackedCity = null;
        player = null;
        month = 1;
        year = 0;
    }
    
    public void increaseIncome(City city) {
        
    }
    
    public Building getSlot(int index) {
        return selectedCity.getSlot(index);
    }
    
    public void setSlotData(int index, Building data) {
        selectedCity.setSlotData(index, data);
    }
    
    public String getBuildingName(int slot) {
        String name;
        
        if (selectedCity.getSlots()[slot - 1] == null) {
            name = "空地";
            return name;
        } else {     
            name = selectedCity.getSlots()[slot - 1].getName();
        }
        
        return name;
    }
    
    public void increaseResources()
    {
        // gold is increased every turn
        player.increaseGold(100);
        
        // soldier is increased every turn
        // increase soldiers code goes here
        
        // food is increased every season
        if (month == 3 || month == 6 || month == 9 || month == 12)
        {
            player.increaseFood(1000);
        }
    }
    
    public void increasePopulation(){
        // population is increased every year
        if (month == 12){
            player.getCityList().stream().forEach((city) -> {
                city.setPopulation((int)(city.getPopulation() * 1.1));
            });
        }
    }
            
    public void increaseTime()
    {
        month++;
        
        if (month == 13)
        {
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
    
    public Force getPlayer(){
        return player;
    }
    
    public int getPlayerGold(){
        if (player == null){
            return 0;
        }
        return player.getGold();
    }
    
    public void reducePlayerGold(int amount) {
        player.increaseGold(-amount);
    }
    
    public void setPlayerGold(int gold) {
        player.setGold(gold);
    }
    
    public int getPlayerFood(){
        if (player == null){
            return 0;
        }
        return player.getFood();
    }
    
    public String getPlayerName(){
        if (player == null){
            return "无";
        }
        return player.getForceName();
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
    
    public int getNumCharacters(City city){
        if (city == null){
            return 0;
        }
        return city.getCharacterList().size();
    }
    
    public String getPlayerCityNameOrNumber(){
        String cityNameOrNumber;
        if (player == null){
            cityNameOrNumber = "";
        } else if (player.getCityList().size() == 1) {
            cityNameOrNumber = player.getCityList().get(0).getCityName();
        } else {
            cityNameOrNumber = player.getCityList().size() + "";
        }
        return cityNameOrNumber;
    }
    
    public int getSoldiers(City city)
    {
        if (city == null){
            return 0;
        }
        return city.getSoldiers();
    }
    
    public int getSoldiers(Force force)
    {
        if (force == null){
            return 0;
        }
        force.calcSoldier();
        return force.getSoldier();
    }
    
    public int getPopulation(City city)
    {
        if (city == null){
            return 0;
        }
        return city.getPopulation();
    }
    
    public int getPopulation(Force force)
    {
        if (force == null){
            return 0;
        }
        force.calcPopulation();
        return force.getPopulation();
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
    
    public void setCity(City city) {
        selectedCity = city;
    }
    
    public void setAttackedCity(int num) {
        attackedCity = this.getCity(num);
    }
    
    public void setAttackedCity(City city) {
        attackedCity = city;
    }
    
    public City getSelectedCity() {
        return selectedCity;
    }
    
    public City getAttackedCity() {
        return attackedCity;
    }
    
    public boolean cityIsPlayerOwned() {
        return player.owns(selectedCity);
    }
    
    public Character getChar(int index) {
        return selectedCity.get(index);
    }
    
    public String getCityName() {
        if (selectedCity == null) {
            return "无";
        }
        return selectedCity.getCityName();
    }
    
    public int getCharNumber() {
        if (selectedCity == null) {
            return 0;
        }
        return selectedCity.getCharNumber();
    }
    
    public int getSoldiers() {
        if (selectedCity == null) {
            return 0;
        }
        return selectedCity.getSoldiers();
    }
        
    public void updateYear() {
       year = db.getYear();
    }
    
    public void updatePlayer(Force force) {
        player = force;
    }
    
    public static GameController getInstance() {
        return instance;
    }
}
