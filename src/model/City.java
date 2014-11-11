/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import controller.GameController;
import controller.GameParameters;
import controller.Neighbour;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.buildings.Building;

/**
 *
 * @author RuN
 * 
 * Description: This class is a model for City. 
 *                      Each City has:
 *                       - 1 to 8 neighboring cities.
 *                       - A list of Characters;
 *                       - A number of Population
 *                       - A number of Soldiers
 *                       - It's own food and gold income.
 */
public class City {
    private String cityName;
    
    private final List<Character> characterList;        // belongs to play and city
    private final List<Character> undiscoveredList;     // undiscovered character
    private final Rectangle rectangle;
    private final byte[] neighbours;
    // private final Neighbour[] newNeighbours;
    private final Building[] slots;
    protected int soldierIncome;
    private boolean frontline;
    private int goldIncome;
    private int foodIncome;
    private int population;
    private int soldiers;
    private int weapon;
    private int armour;
    private int horse;
    private int chariot;
    
    public final int length;
    public final int offset;
    public int x, y;                                // city location on GamePanel, x-coordinate and y-coordinate

    
    public City(String cityName) {
        
        this.cityName = cityName;
        
        // Initialise Arrays and Lists
        characterList = new ArrayList<>(10);
        undiscoveredList = new ArrayList<>(10);
        neighbours = new byte[8];
        // newNeighbours = new Neighbour[8];
        slots = new Building[8];
        
        // Graphics Content
        length = 25;
        offset = 1;
        x = 0;
        y = 0;
        rectangle = new Rectangle(x, y, length, length);
        
        // Initialise Variables
        initValues();
    }
    
    private void initValues() {
        soldiers = 15000;
        population = 10;
        
        goldIncome = 500;
        foodIncome = 800;
        soldierIncome = 100;
        
        weapon = 0;
        armour = 0;
        horse = 0;
        chariot = 0;
        Arrays.fill(neighbours, (byte) 0);
        Arrays.fill(slots, null);
    }
    
    public void updateIncomes(Building building) {
        goldIncome = goldIncome + building.getGoldIncome();
        foodIncome = foodIncome + building.getFoodIncome();
        soldierIncome = soldierIncome + building.getSoldierIncome();
    }

    public String getCityName(){
        return cityName;
    }

    public void setCityName(String cityName){
        this.cityName = cityName;
    }
    
    public void increasePopulationByRate() {
        population = (int) (population * (1 + GameController.POPULATION_INCREASE_RATE));
    }
    
    public void increasePopulation(int value) {
        population += value;
    }

    public int getPopulation(){
        return population;
    }

    public void setPopulation(int population){
        this.population = population;
    }

    public int getSoldiers(){
        return soldiers;
    }

    public void setSoldiers(int soldiers){
        this.soldiers = soldiers;
    }

    public int getGoldIncome(){
        return goldIncome;
    }

    public void setGoldIncome(int goldIncome){
        this.goldIncome = goldIncome;
    }

    public int getFoodIncome(){
        return foodIncome;
    }

    public void setFoodIncome(int foodIncome){
        this.foodIncome = foodIncome;
    }
    
    public List<Character> getCharacterList(){
        return characterList;
    }
    
    public List<Character> getUndiscoveredList(){
        return undiscoveredList;
    }
    
    public int getNumCharacters(){
        return characterList.size();
    }
    
    public Character get(int index){
        return characterList.get(index);
    }
    
    public Character get(String name){
        for (Character tempChar : characterList) {
            if (tempChar.getName().equals(name)) {
                return tempChar;
            }
        }
        return null;
    }
    
    public int getNeighbour(int index){
        return neighbours[index - 1];
    }
    
    /*
    public void setNewNeighbours(Neighbour n, Neighbour s, Neighbour w, Neighbour e, Neighbour nw, Neighbour ne, Neighbour sw, Neighbour se) {
        newNeighbours[0] = n;
        newNeighbours[1] = s;
        newNeighbours[2] = w;
        newNeighbours[3] = e;
        newNeighbours[4] = nw;
        newNeighbours[5] = ne;
        newNeighbours[6] = sw;
        newNeighbours[7] = se;
    }
    */
    
    public void setNeighbours(int north, int south, int west, int east, int northWest, int northEast, int southWest, int southEast) {
        setNeighbours(north, south, west, east);
        neighbours[4] = (byte)northWest;
        neighbours[5] = (byte)northEast;
        neighbours[6] = (byte)southWest;
        neighbours[7] = (byte)southEast;
    }
    
    public void setNeighbours(int north, int south, int west, int east) {
        neighbours[0] = (byte)north;
        neighbours[1] = (byte)south;
        neighbours[2] = (byte)west;
        neighbours[3] = (byte)east;
        neighbours[4] = (byte)GameParameters.CITY_EMPTY;
        neighbours[5] = (byte)GameParameters.CITY_EMPTY;
        neighbours[6] = (byte)GameParameters.CITY_EMPTY;
        neighbours[7] = (byte)GameParameters.CITY_EMPTY;
    }

    public boolean isFrontline(){
        return frontline;
    }

    public void setFrontline(boolean frontline){
        this.frontline = frontline;
    }
    
    /**
     * Add a character to this city
     * @param inChar a Character to be added
     */
    public void add(Character inChar){
        characterList.add(inChar);
    }
    
    // Add an Undiscovered character to this city
    public void addU(Character inChar){
        undiscoveredList.add(inChar);
    }
    
    public void setMapCoordinate(int x, int y){
        rectangle.setLocation(x, y);
        this.x = x;
        this.y = y;
    }
    
    public Rectangle getRect(){
        return rectangle;
    }
    
    public Building[] getSlots() {
        return slots;
    }
    
    public Building getSlot(int index) {
        return slots[index];
    }
    
    public void setSlotData (int index, Building data) {
        slots[index] = data;
    }
    
    public void setSlotData(Building[] slots) {
        System.arraycopy(slots, 0, this.slots, 0, slots.length);
    }

    public int getSoldierIncome() {
        return soldierIncome;
    }

    public void setSoldierIncome(int soldierIncome) {
        this.soldierIncome = soldierIncome;
    }
    
    public City[] getNeighbours() {
        City[] neighbourList = new City[8];
        Database db = Database.getInstance();
        
        for (int i = 0; i < neighbours.length; i++) {
            if (neighbours[i] == GameParameters.CITY_EMPTY) {
                neighbourList[i] = null;
            } else {
                neighbourList[i] = db.getCity(neighbours[i]);
            }
        }
        
        return neighbourList;
    }
    
    public Force getOwner() {
        Database db = Database.getInstance();
        
        for (Force force: db.getForceList()){
            if (force.getCityList().contains(this)) {
                return force;
            }
        }
        return null;
    }

    public int getWeapon() {
        return weapon;
    }

    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public int getHorse() {
        return horse;
    }

    public void setHorse(int horse) {
        this.horse = horse;
    }

    public int getChariot() {
        return chariot;
    }

    public void setChariot(int chariot) {
        this.chariot = chariot;
    }
    
    public void draw(Graphics g, Color forceColour) {
        
        // convert to Graphics2D
        Graphics2D g2 = (Graphics2D)g;
        
        // set border size
        g2.setStroke(new BasicStroke(2));

        // fill the square
        g2.setPaint(forceColour);
        g2.fill(rectangle);
        
        // upper line
        g2.setPaint(forceColour.brighter());
        g2.drawLine(x + offset, y + offset, x + length, y + offset);
        
        // bottom line
        g2.setPaint(forceColour.darker());
        g2.drawLine(x + offset, y + length, x + length - offset, y + length);
        
        // left line
        g2.setPaint(forceColour.darker());
        g2.drawLine(x + offset, y + offset, x + offset, y + length);
        
        // right line
        g2.setPaint(forceColour.brighter());
        g2.drawLine(x + length, y + offset, x + length, y + length);
        
        // making the button more 3D.
        g2.setStroke(new BasicStroke(1));
        g2.setPaint(forceColour.brighter());
        g2.drawLine(x + offset, y, x + offset + offset, y);
        g2.setPaint(forceColour.darker());
        g2.drawLine(x + length - offset - offset, y + length, x + length - offset, y + length);
    }
    
    public void drawPressed(Graphics g, Color forceColour) {
        
        // convert to Graphics2D
        Graphics2D g2 = (Graphics2D)g;
        
        // set border size
        g2.setStroke(new BasicStroke(2));

        // fill the square
        g2.setPaint(forceColour);
        g2.fill(rectangle);
        
        // upper line
        g2.setPaint(forceColour.darker().darker());
        g2.drawLine(x + offset, y + offset, x + length, y + offset);
        
        // bottom line
        g2.setPaint(forceColour.darker().darker());
        g2.drawLine(x + offset, y + length, x + length - offset, y + length);
        
        // left line
        g2.setPaint(forceColour.darker().darker());
        g2.drawLine(x + offset, y + offset, x + offset, y + length);
        
        // right line
        g2.setPaint(forceColour.darker().darker());
        g2.drawLine(x + length, y + offset, x + length, y + length);
    }
}
