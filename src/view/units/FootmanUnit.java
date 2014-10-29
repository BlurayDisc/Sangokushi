/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.units;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author RuN
 */
public class FootmanUnit extends Unit {
    
    private final Ellipse2D.Double unit;
    
    public FootmanUnit(double x, double y) {
        super();
        this.x = x;
        this.y = y;
        unit = new Ellipse2D.Double(x, y, length, length);
    }
    
    public FootmanUnit() {
        super();
        unit = new Ellipse2D.Double(x, y, length, length);
    }
    
    @Override
    public void translate(double x, double y) {
        this.x += x;
        this.y += y;
        unit.x += x;
        unit.y += y;
    }
    
    public void applyDefaultTranslation() {
        
    }
    
    public Ellipse2D.Double getUnit() {
        return unit;
    }
    
    @Override
    public Shape getShape() {
        return unit;
    }

    @Override
    public void moveForward() {
        translate(0, -10);
    }

    @Override
    public void moveBackward() {
        translate(0, 10);
    }

    
}
