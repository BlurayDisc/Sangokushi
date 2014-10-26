/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.units;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author RuN
 */
public class Footman extends Unit {
    
    private final Rectangle2D.Double unit;
    
    public Footman(double x, double y) {
        super();
        this.x = x;
        this.y = y;
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
