/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Side;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import model.units.Calvary;
import model.units.Footman;
import model.units.Unit;

/**
 *
 * @author RuN
 * 
 * 
 * 
 *  Ancient Military Ranking:
 *  1. 什长     10人     什
 *  2. 百夫长   100人    行
 *  3. 千夫长   1000人   大行
 *  4. 正副将   3000人   师
 *  5. 正副将军 10000人  王师
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
    private final Point2D.Double playerMainArmySpawnPoint, enemyMainArmySpawnPoint;
    
    public BattleScreen() {
        playerMainArmySpawnPoint = new Point2D.Double(380, 540);
        enemyMainArmySpawnPoint = new Point2D.Double(380, 50);
        
        playerMainArmy = new ArrayList<>(100);
        enemyMainArmy = new ArrayList<>(100);
        
    }
    
    public void init() {
        setPlayerSoldiers(5000, playerMainArmy, playerMainArmySpawnPoint);
        setEnemySoldiers(10000, enemyMainArmy, enemyMainArmySpawnPoint);
    }
    
    public void move() {
        for (int i = 0; i < enemyMainArmy.size(); i++) {
            translate(enemyMainArmy.get(i).getShape(), Side.ENEMY);
        }
        
        for (int i = 0; i < playerMainArmy.size(); i++) {
            translate(playerMainArmy.get(i).getShape(), Side.PLAYER);
        }
    }
    
    private void translate(Shape shape, Side s) {
        if (shape instanceof Ellipse2D.Double) {
            Ellipse2D.Double footman = (Ellipse2D.Double)shape;
            if (s == Side.PLAYER) {footman.y -= 10;}
            else {footman.y += 10;}
        } else if (shape instanceof Rectangle2D.Double) {
            Rectangle2D.Double calvary = (Rectangle2D.Double)shape;
            if (s == Side.PLAYER) {calvary.y -= 30;}
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
        
    // value bounds: 1 - 37500
    public void setPlayerSoldiers(int inValue, List<Unit> soldierList, Point2D.Double inSpawnPoint) {
        int x = 0, y = 0, dx = 0, dy = 0;
        int value = inValue / 100;
        
        Point2D.Double spawnPoint = calcPlayerSpawnPoint(value, inSpawnPoint);
        
        for(int i = 0; i < value; i++) {
            if (x == 5) { x = 0; y++; }
            if (y == 5) { y=0; dx+=6; }
            if (dx == 30) {dx = 0; dy+=6;}
            soldierList.add(new Footman(spawnPoint.x + 10 * (x + dx), spawnPoint.y + 10 * (y + dy)));
            x++;
        }
    }
    
    // value bounds: 1 - 37500
    public void setEnemySoldiers(int inValue, List<Unit> soldierList, Point2D.Double inSpawnPoint) {
        int x = 0, y = 0, dx = 0, dy = 0;
        int value = inValue / 100;
        
        Point2D.Double spawnPoint = calcEnemySpawnPoint(value, inSpawnPoint);
        
        for(int i = 0; i < value; i++) {
            if (x == 5) { x = 0; y++; }
            if (y == 5) { y=0; dx+=6; }
            if (dx == 30) {dx = 0; dy+=6;}
            soldierList.add(new Calvary(spawnPoint.x + 10 * (x + dx), spawnPoint.y - 10 * (y + dy)));
            x++;
        }
    }
    
    private Point2D.Double calcPlayerSpawnPoint(int value, Point2D.Double spawnPoint) {
        
        Point2D.Double newSpawn = new Point2D.Double();
        
        if (value > 250) {
            newSpawn.setLocation(spawnPoint.x - 120, spawnPoint.y - 120);
        } else if (value > 125) {
            newSpawn.setLocation(spawnPoint.x - 120, spawnPoint.y - 60);
        } else if (value > 100) {
            newSpawn.setLocation(spawnPoint.x - 120, spawnPoint.y);
        } else if (value > 75) {
            newSpawn.setLocation(spawnPoint.x - 90, spawnPoint.y);
        } else if (value > 50) {
            newSpawn.setLocation(spawnPoint.x - 60, spawnPoint.y);
        }else if (value > 25) {
            newSpawn.setLocation(spawnPoint.x - 30, spawnPoint.y);
        } else {
            newSpawn.setLocation(spawnPoint.x, spawnPoint.y);
        }
        
        return newSpawn;
    }
    
    private Point2D.Double calcEnemySpawnPoint(int value, Point2D.Double spawnPoint) {
        
        Point2D.Double newSpawn = new Point2D.Double();
        
        if (value > 250) {
            newSpawn.setLocation(spawnPoint.x - 120, spawnPoint.y + 120);
        } else if (value > 125) {
            newSpawn.setLocation(spawnPoint.x - 120, spawnPoint.y + 60);
        } else if (value > 100) {
            newSpawn.setLocation(spawnPoint.x - 120, spawnPoint.y);
        } else if (value > 75) {
            newSpawn.setLocation(spawnPoint.x - 90, spawnPoint.y);
        } else if (value > 50) {
            newSpawn.setLocation(spawnPoint.x - 60, spawnPoint.y);
        } else if (value > 25) {
            newSpawn.setLocation(spawnPoint.x - 30, spawnPoint.y);
        } else {
            newSpawn.setLocation(spawnPoint.x, spawnPoint.y);
        }
        
        return newSpawn;
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
