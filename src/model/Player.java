/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author RuN
 */
public class Player {
    private static final Player instance = new Player();
    private Force force;
    
    private Player() {
        force = null;
    }
    
    public void setForce(Force force) {
        this.force = force;
    }
    
    public Force getForce() {
        return force;
    }
    
    public static Player getInstance() {
        return instance;
    }    
}
