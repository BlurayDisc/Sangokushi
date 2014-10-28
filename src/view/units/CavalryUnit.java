/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.units;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author RuN
 */
public class CavalryUnit extends Unit{
    
    private final Rectangle2D.Double unit;
    
    public CavalryUnit(double x, double y) {
        this.x = x;
        this.y = y;
        length = 9;
        unit = new Rectangle2D.Double(x, y, length, length);
    }
    
    public CavalryUnit() {
        length = 9;
        unit = new Rectangle2D.Double(x, y, length, length);
    }
    
    @Override
    public void translate(double x, double y) {
        this.x = x;
        this.y = y;
        unit.x = x;
        unit.y = y;
    }
        
    public Rectangle2D.Double getUnit() {
        return unit;
    }
    
    @Override
    public Shape getShape() {
        return unit;
    }
}
