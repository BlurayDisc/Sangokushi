/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.units;

import java.awt.Shape;

/**
 *
 * @author RuN
 */
public abstract class Unit {
    
    public double x, y;
    protected final int length;
    
    public Unit() {
        length = 8;
        x = 0;
        y = 0;
    }
    
    public int getLength() {
        return length;
    }
    
    public abstract Shape getShape();
    
    public abstract void translate(double x, double y);    
}
