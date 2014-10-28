/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.units;

import controller.ArmyType;
import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 *
 * @author RuN
 */
public abstract class Unit {
    
    public double x, y;
    protected int length;
    protected Point2D.Double spawnPoint;
    
    public Unit() {
        length = 8;
        x = 0;
        y = 0;
    }
    
    public int getLength() {
        return length;
    }
    
    public Point2D.Double getSpawnPoint() {
        return spawnPoint;
    }
    
    public void setSpawnPoint(ArmyType type) {
        switch(type) {
            case PLAYER_FRONT: break;
            case PLAYER_MAIN: spawnPoint = new Point2D.Double(380, 540); break;
            case PLAYER_LEFTWING: break;
            case PLAYER_RIGHTWING: break;
            case ENEMY_FRONT: break;
            case ENEMY_MAIN: spawnPoint = new Point2D.Double(380, 50); break;
            case ENEMY_LEFTWING: break;
            case ENEMY_RIGHTWING: break;
        }
    }
    
    public void adjustSpawnPoint(ArmyType type, int numSoldiers) {
        if (numSoldiers > 250) {
            spawnPoint.x -= 120;
            if (type == ArmyType.PLAYER_MAIN) spawnPoint.y -= 120;
            else if (type == ArmyType.ENEMY_MAIN) spawnPoint.y += 120;
        } else if (numSoldiers > 125) {
            spawnPoint.x -= 120; 
            if (type == ArmyType.PLAYER_MAIN) spawnPoint.y -= 60;
            else if (type == ArmyType.ENEMY_MAIN) spawnPoint.y += 60;
        } else if (numSoldiers > 100) {
            spawnPoint.x -= 120;
        } else if (numSoldiers > 75) {
            spawnPoint.x -= 90;
        } else if (numSoldiers > 50) {
            spawnPoint.x -= 60;
        } else if (numSoldiers > 25) {
            spawnPoint.x -= 30;
        }
    }
    
    public abstract Shape getShape();
    
    public abstract void translate(double x, double y);    
}
