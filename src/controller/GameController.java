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
    
    private ArrayList<Force> forceList;
    private ArrayList<City> cityList;
    private ArrayList<Character> characterList;
    
    private Force player;
    private City selectedCity, attackedCity;
    private int month;
    private int year;
    
    private static GameController instance = null;

    private GameController() {
        forceList = null;
        cityList = null;
        characterList = null;
        player = null;
        month = 1;
        year = 0;
    }
    
    public void loadScenario(Database scenario) {
        forceList = scenario.getForceList();
        cityList = scenario.getCityList();
        characterList = scenario.getCharList();
        year = scenario.getYear();
    }
    
    public void updatePlayer(Force force) {
        player = force;
    }
    
    public Force getOwnerOfCity(City city) {
        for (Force force: getForceList()){
            if (force.getCityList().contains(city)) {
                return force;
            }
        }
        return null;
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
        return forceList.size();
    }
        
    public String getForceName(int forceNumber){
        return getForce(forceNumber).getForceName();
    }

    public Force getForce(int forceNumber){
        return forceList.get(forceNumber);
    }
    
    public ArrayList<Force> getForceList(){
        return forceList;
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
        return cityList.get(cityNumber - 1);
    }
    
    public ArrayList<City> getCityList() {
        return cityList;
    }
    
    public City getCityByName(String inCityName) {
        City tempCity = null;
        for (City city: cityList){
            if (city.getCityName().equals(inCityName)){
                return city;
            }
        }
        return tempCity;
    }
    
    public City[] getNeighbours(City currentCity) {
        City[] neighbourList = new City[8];
        int[] cityNumberList = currentCity.getNeighbours();
        int i = 0;
        for (City neighbourCity: neighbourList){
            if (cityNumberList[i] == CITY_EMPTY) {
                neighbourList[i] = null;
            }
            else{
                neighbourList[i] = getCity(cityNumberList[i]);
            }
            i++;
        }
        return neighbourList;
    }

    public ArrayList<Character> getCharacterList() {
        return characterList;
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
    
    public City getCity() {
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
    
    public static synchronized GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }
}
