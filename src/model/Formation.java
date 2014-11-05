/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.PrepareBattlePanel;

/**
 *
 * @author RuN
 */
public class Formation {
    
    private final PrepareBattlePanel panel;
    
    public Formation(PrepareBattlePanel panel) {
        
        this.panel = panel;
    }
    
    private void reset() {
        panel.getFrontButton().setVisible(false);
        panel.getFrontLabel().setVisible(false);
        
        panel.getMainButton().setVisible(false);
        panel.getMainLabel().setVisible(false);
        
        panel.getLeftButton().setVisible(false);
        panel.getLeftLabel().setVisible(false);
        
        panel.getRightButton().setVisible(false);
        panel.getRightLabel().setVisible(false);
    }
    
    // 中军
    public void initDefaultFormation() {
        
        reset();
        
        panel.getMainButton().setVisible(true);
        panel.getMainLabel().setVisible(true);
    }
    
    // 左军， 右军
    public void initFormation2() {
        
        reset();
        
        panel.getLeftButton().setVisible(true);
        panel.getLeftLabel().setVisible(true);
        
        panel.getRightButton().setVisible(true);
        panel.getRightLabel().setVisible(true);
    }
    
    // 三军
    public void initFormation3() {
        
        reset();
                
        panel.getMainButton().setVisible(true);
        panel.getMainLabel().setVisible(true);
        
        panel.getLeftButton().setVisible(true);
        panel.getLeftLabel().setVisible(true);
        
        panel.getRightButton().setVisible(true);
        panel.getRightLabel().setVisible(true);
    }
    
    // 左中右前军
    public void initFormation4() {
        
        reset();
        
        panel.getFrontButton().setVisible(true);
        panel.getFrontLabel().setVisible(true);
        
        panel.getMainButton().setVisible(true);
        panel.getMainLabel().setVisible(true);
        
        panel.getLeftButton().setVisible(true);
        panel.getLeftLabel().setVisible(true);
        
        panel.getRightButton().setVisible(true);
        panel.getRightLabel().setVisible(true);
    }
    
    // In Progress... 
    public void initFormation5() {
        
        reset();
        
    }
}
