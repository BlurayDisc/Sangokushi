/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ArmyType;
import controller.Battle;
import controller.UnitType;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import model.Army;

/**
 *
 * @author RuN
 */
public class BattlePanel extends JPanel {
    /**
     * Creates new form BattlePanel
     */
    private final BattleScreen battleScreen;
    private final Battle mainBattle;
    
    
    public BattlePanel() {
        super();
        initComponents();
        setSize(800, 600);
        
        battleScreen = new BattleScreen();
        mainBattle = new Battle();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        battleScreen.drawPlayerArmy(g2);
        battleScreen.drawEnemyArmy(g2);
    }
    
    public void initBattle(Army frontArmy, Army mainArmy, Army leftWingArmy, Army rightWingArmy) {
        battleScreen.setSoldiers(ArmyType.PLAYER_MAIN, mainArmy.getSoldiers(), mainArmy.getUnitTypt());
        battleScreen.setSoldiers(ArmyType.ENEMY_MAIN, 10000, UnitType.LIGHT_CAVALRY);
        mainBattle.setSoldiers(mainArmy.getSoldiers(), 10000);
    }
    
    public void runExample() {
        battleScreen.setSoldiers(ArmyType.PLAYER_MAIN, 5000, UnitType.LIGHT_CAVALRY);
        battleScreen.setSoldiers(ArmyType.ENEMY_MAIN, 12500, UnitType.SWORDSMAN);
        mainBattle.setSoldiers(5000, 12500);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jLabel1.setText("敌对");

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jLabel2.setText("玩家");

        jButton1.setText("March");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(711, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(88, 88, 88)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("Player Soldiers: " + mainBattle.getPlayerSoldiers());
        System.out.println("Enemy Soldiers: " + mainBattle.getEnemySoldiers());
        if (battleScreen.isMovable()) { battleScreen.move();}
        //mainBattle.startBattle();
        battleScreen.updateSoldiers(ArmyType.PLAYER_MAIN, 1);
        battleScreen.updateSoldiers(ArmyType.ENEMY_MAIN, 3);
        repaint();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
