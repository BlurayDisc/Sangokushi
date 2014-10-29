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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
    
    private final LinkedList<Unit> playerMainArmy, enemyMainArmy;
    private LinkedList<Unit> army;
    private Unit firstUnit;
    public enum Side {PLAYER, ENEMY}
    
    public BattleScreen() {
        playerMainArmy = new LinkedList<>();
        enemyMainArmy = new LinkedList<>();
    }
    
    public void updateSoldiers(ArmyType type, int soldiers) {
        army = getArmyByType(type);
        if (!army.isEmpty()) {
            removeUnits(army, soldiers);
        }
    }
    
    private void removeUnits(LinkedList<Unit> unitList, int soldiers) {
        for (int i = 0; i < soldiers; i++) {
            if (!army.isEmpty()) {
                unitList.removeLast();
            }
        }
    }
    
    public boolean isMovable() {
        if (playerMainArmy.isEmpty() || enemyMainArmy.isEmpty()) {
            return false;
        } else if (playerMainArmy.getFirst().y >= enemyMainArmy.getFirst().y + 50) {
            return true;
        }
        return false;
    }
    
    public void move() {
        for (Iterator<Unit> it = enemyMainArmy.iterator(); it.hasNext();) {
            translate(it.next(), Side.ENEMY);
        }
        
        for (Iterator<Unit> it = playerMainArmy.iterator(); it.hasNext();) {
            translate(it.next(), Side.PLAYER);
        }
    }
    
    private void translate(Unit unit, Side side) {
        if (side == Side.PLAYER) {
            unit.moveForward();
        } else {
            unit.moveBackward();
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
        
        army = getArmyByType(armyType);
        firstUnit = getUnitByType(unitType);
        
        firstUnit.setSpawnPoint(armyType);                                                   // set spawn point according to armyType
        firstUnit.adjustSpawnPoint(armyType, numSoldiers);                                   // adjust inital spawn postions
        firstUnit.translate(firstUnit.getSpawnPoint().x, firstUnit.getSpawnPoint().y);       // translate it
        
        addSoldiers(army, numSoldiers, unitType);               // add soldiers to army list
        adjustSoldierPositions(armyType, army);                 // adjust spawn postions for every soldier in the army
        army.addFirst(firstUnit);
    }
    
    private void addSoldiers(List<Unit> army, int numSoldiers, UnitType unitType) {
        for (int i = 0; i < numSoldiers; i++) {
            army.add(getUnitByType(unitType));
        }
    }
    
    private void adjustSoldierPositions(ArmyType armyType, List<Unit> army) {
        
        int x = 0, y = 0, dx = 0, dy = 0;
        
        for (Iterator<Unit> it = army.iterator(); it.hasNext();) {
            if (x == 5) {x = 0; y++;}
            if (y == 5) {y = 0; dx += 6;}
            if (dx == 30) {dx = 0; dy += 6;}
            if (armyType == ArmyType.PLAYER_MAIN) {
                it.next().translate(firstUnit.x + 10 * (x + dx), firstUnit.y + 10 * (y + dy));
            } else {
                it.next().translate(firstUnit.x + 10 * (x + dx), firstUnit.y - 10 * (y + dy));
            }
            x++;
        }
    }
    
    private Unit getUnitByType(UnitType type) {
        switch(type) {
            case SWORDSMAN: return new FootmanUnit();
            case LIGHT_CAVALRY: return new CavalryUnit();
            default: return null;
        }
    }
    
    private LinkedList<Unit> getArmyByType(ArmyType type){
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
