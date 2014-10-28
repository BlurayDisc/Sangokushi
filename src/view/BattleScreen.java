/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ArmyType;
import controller.UnitType;
import view.units.Unit;
import view.units.FootmanUnit;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import model.Army;
import view.units.CavalryUnit;

/**
 *
 * @author RuN
 * 
 * 
 * 
 *  Ancient Military Ranking:
 *  1. 什长     10人     什
 *  2. 百夫长   100人    行
 *  3. 屯长     500人    旅  
 *  4. 千夫长   1000人   大行
 *  5. 正副将   2500人   师
 *  6. 正副将军 12500人  军
 * 
 * Ancient Military Chariot Formation:
 * 
 * Details:     每辆战车甲士10名(车上3人车下7人), 徒役20名(5人养马炊饮15人作战)。
 *              左为弓箭手， 右为长矛手， 中为驭手。
 *              每辆战车需4匹马。
 * 
 * 1. 乘        1辆战车      30人    乘
 * 1. 仆射      5辆战车      150人    队
 * 2. 官        10辆战车     300人   两队
 * 3. 卒长      100辆战车    3000人   卒
 * 4. 师氏      200辆战车    6000人  师
 * 
 * 
 *  Han Dynasty Imperial Grade:
 *  1. 大将军
 *  2. 骠骑将军
 *  3. 车骑将军
 *  4. 卫将军
 *  5. 前后左右将军
 * 
 * 
 * 
 */
public class BattleScreen {
    
    private final List<Unit> playerMainArmy, enemyMainArmy;
    public enum Side {PLAYER, ENEMY}
    
    public BattleScreen() {
        playerMainArmy = new ArrayList<>(100);
        enemyMainArmy = new ArrayList<>(100);
    }
    
    public void move() {
        for (int i = 0; i < enemyMainArmy.size(); i++) {
            translate(enemyMainArmy.get(i).getShape(), Side.ENEMY);
        }
        
        for (int i = 0; i < playerMainArmy.size(); i++) {
            translate(playerMainArmy.get(i).getShape(), Side.PLAYER);
        }
    }
    
    private void translate(Shape shape, Side side) {
        if (shape instanceof Ellipse2D) {
            Ellipse2D.Double footman = (Ellipse2D.Double)shape;
            if (side == Side.PLAYER) {footman.y -= 10;}
            else {footman.y += 10;}
        } else if (shape instanceof Rectangle2D) {
            Rectangle2D.Double calvary = (Rectangle2D.Double)shape;
            if (side == Side.PLAYER) {calvary.y -= 30;}
            else {calvary.y += 30;}
        }
    }
    
    public void drawPlayerArmy(Graphics2D g) {        
        g.setPaint(Color.RED);
        for (int i = 0; i < playerMainArmy.size(); i++) {
            g.fill(playerMainArmy.get(i).getShape());
        }
    }
    
    public void drawEnemyArmy(Graphics2D g) {        
        g.setPaint(Color.BLUE);
        for (int i = 0; i < enemyMainArmy.size(); i++) {
            g.fill(enemyMainArmy.get(i).getShape());
        }
    }
    
    public void paintBackground() {
        
    }
    
    public void setSoldiers(ArmyType armyType, int numSoldiers, UnitType unitType) {
        numSoldiers = numSoldiers / 100;
        List<Unit> army = getArmy(armyType);
        
        Unit head = getUnitType(unitType);
        head.setSpawnPoint(armyType);
        head.adjustSpawnPoint(armyType, numSoldiers);
        head.translate(head.getSpawnPoint().x, head.getSpawnPoint().y);
        
        army.add(head);
        addSoldiers(army, numSoldiers, unitType);
        adjustSoldierPositions(armyType, army, head);
    }
    
    private void addSoldiers(List<Unit> army, int numSoldiers, UnitType unitType) {
        for (int i = 1; i < numSoldiers; i++) {
            army.add(getUnitType(unitType));
        }
    }
    
    private void adjustSoldierPositions(ArmyType armyType, List<Unit> army, Unit head) {
        
        int x = 0, y = 0, dx = 0, dy = 0;
        
        for (int i = 1; i < army.size(); i++) {
            if (x == 5) {x = 0; y++;}           // 5 little columss
            if (y == 5) {y = 0; dx += 6;}       // 5 rows
            if (dx == 30) {dx = 0; dy += 6;}    // dx / 3 big columns
            if (armyType == ArmyType.PLAYER_MAIN) {
                army.get(i).translate(head.x + 10 * (x + dx), head.y + 10 * (y + dy));
            } else {
                army.get(i).translate(head.x + 10 * (x + dx), head.y - 10 * (y + dy));
            }
            x++;
        }
    }
    
    private Unit getUnitType(UnitType type) {
        switch(type) {
            case SWORDSMAN: return new FootmanUnit();
            case LIGHT_CAVALRY: return new CavalryUnit();
            default: return null;
        }
    }
    
    private List<Unit> getArmy(ArmyType type){
        switch(type) {
            case PLAYER_FRONT: return null;
            case PLAYER_MAIN: return playerMainArmy;
            case PLAYER_LEFTWING: return null;
            case PLAYER_RIGHTWING: return null;
            case ENEMY_FRONT: return null;
            case ENEMY_MAIN: return enemyMainArmy;
            case ENEMY_LEFTWING: return null;
            case ENEMY_RIGHTWING: return null;
            default: return null;
        }
    }
    
    public void drawChariotFormation(Graphics2D g) {
        /*
                 |*| 
                 * *
                 * *
                 * *
        */
    }
    
    public void drawMainArmy(Graphics2D g) {
        
    }
    
    public void drawLeftArmy(Graphics2D g) {
        
    }
    
    public void drawRightArmy(Graphics2D g) {
        
    }
    
    public void drawFrontArmy(Graphics2D g) {
        
    }
    
    public void drawAmbushArmy(Graphics2D g) {
        
    }
}
