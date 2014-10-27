/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Battle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.City;
import model.Army;

/**
 *
 * @author RuN
 */
public class BattlePanel extends javax.swing.JPanel
{
    private final BattleScreen bs;
    private final GamePanel gp;
    private Battle battle1, battle2, battle3, battle4;
    private final Army northLegion;
    private final Army southLegion;
    private final Army westLegion;
    private final Army eastLegion;
    private String resultString, playerLog, enemyLog;
    private String playerMaxHp, enemyMaxHp;
    ActionListener playerMsgListener, enemyMsgListener;
    Timer playerMsgTimer, enemyMsgTimer;
    private final City currentCity, attackingCity;

    /**
     * Creates new form Battle
     */
    public BattlePanel(GamePanel gp, City currentCity, City attackingCity, Army northLegion, Army southLegion, Army westLegion, Army eastLegion)
    {
        super();
        setSize(800, 600);
        this.gp = gp;
        this.currentCity = currentCity;
        this.attackingCity = attackingCity;
        this.northLegion = northLegion;
        this.southLegion = southLegion;
        this.eastLegion = eastLegion;
        this.westLegion = westLegion;
        this.bs = new BattleScreen();
        
        initComponents();
               
        initBattles();
        initLabels();
        initTimers();
    }
    
    public void wtf()
    {
        Object[] options = new Object[]{"确定"};
        int optionChoice = JOptionPane.showOptionDialog(null, "进攻？",  "三国志", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (optionChoice == 0)
        {
        }
    }
    
    
    private void initTimers()
    {
        // Anonymous Inner Class
        playerMsgListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent event)
            {
                // Display Player Message.
                // Start Enemy Message Timer.
            }
        };
        
        enemyMsgListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                // Display Enemy Message.
            }
        };
        
        // Timer(interval, listener)
        playerMsgTimer = new Timer(1000, playerMsgListener);
        enemyMsgTimer = new Timer(1000, enemyMsgListener);
        playerMsgTimer.setRepeats(false);
        enemyMsgTimer.setRepeats(false); 
    }
    
    public void startRound()
    {
        battle1.playerAttack();
        battle1.enemyAttack();
        battle1.incrementRoundCounter();
        
        if (battle1.getPlayerHp() <= 0 || battle1.getEnemyHp() <= 0)   // end battle and print victory side.
        {
            updateLabels();
            battle1.setRoundCounter(0);     // when counter = 0, event => end of battle.
            createCombatLog();
            printCombatLog();
            
            attackButton.setVisible(false);
            abilityButton.setVisible(false);
            retreatButton.setVisible(false);
            backButton.setVisible(true);
        }
        else                              // both sides still have soldiers left.
        {
            updateLabels();
            createCombatLog();
            printCombatLog();
        }
    }
    
    private void updateLabels()
    {
        playerSoldiersLabel.setText("兵力：" +  battle1.getPlayerHp() + "/" + playerMaxHp);
        enemySoldiersLabel.setText("兵力：" +  battle1.getEnemyHp() + "/" + enemyMaxHp);
        playerHpBar.setValue(battle1.getPlayerHp());
        enemyHpBar.setValue(battle1.getEnemyHp());
        
        if (battle1.getPlayerHp() <= 0)    // player defeated
        {
            playerSoldiersLabel.setText("兵力：" + 0 + "/" + playerMaxHp);
            playerHpBar.setValue(0);
            
        }
        else if (battle1.getEnemyHp() <= 0)     // enemy defeated
        {
            enemySoldiersLabel.setText("兵力：" +  0 + "/" + enemyMaxHp);
            enemyHpBar.setValue(0);
        }
    }
    
    private void initBattles()
    {
        // AI.callForceBeingAttacked(attackingCity);
        // AI.createLegions();
        // battle1 = new Battle(northLegion, AI.getNorthLegion);
        battle1 = new Battle(this.northLegion, this.southLegion);
        playerMaxHp = "" + battle1.getPlayerHp();
        enemyMaxHp = "" + battle1.getEnemyHp();
    }
    
    private void initLabels()
    {
        playerNameLabel.setText(battle1.getPlayerName() + "军");
        enemyNameLabel.setText(battle1.getEnemyName() + "军");
        playerStats.setText("攻击: " + battle1.getPlayerDmg() / 10 + "    " + "防御: " + battle1.getPlayerDef() / 10);
        enemyStats.setText("攻击: " + battle1.getEnemyDmg() / 10 + "    " + "防御: " + battle1.getEnemyDef() / 10);
        playerSoldiersLabel.setText("兵力：" +  playerMaxHp + "/" + playerMaxHp);
        enemySoldiersLabel.setText("兵力：" +  enemyMaxHp + "/" + enemyMaxHp);
        
        playerHpBar.setMaximum(battle1.getPlayerHp());
        playerHpBar.setValue(battle1.getPlayerHp());
        enemyHpBar.setMaximum(battle1.getEnemyHp());
        enemyHpBar.setValue(battle1.getEnemyHp());
        
        backButton.setVisible(false);
     }
    
    private void createCombatLog()
    {
        if (battle1.getRoundCounter() == 0)
            resultString = "最终回合" + '\n';
        else
            resultString = "第" + battle1.getRoundCounter() + "回合" + '\n';
        
        resultString = resultString +
                      battle1.getPlayerName() + " 对 " + battle1.getEnemyName() + " 造成了 " + battle1.getDmgToEnemy() + " 的伤害！" + '\n' +
                      battle1.getEnemyName() + " 兵力: " + battle1.getEnemyHp() + '\n' + '\n' +
                      battle1.getEnemyName() + " 对 " + battle1.getPlayerName() + " 造成了 " + battle1.getDmgToPlayer() + " 的伤害！" + '\n' +
                      battle1.getPlayerName() + " 兵力: " + battle1.getPlayerHp() + '\n';
        
        if (battle1.getPlayerHp() <= 0)
            resultString = resultString +
                          '\n' +
                          battle1.getPlayerName() + "军 以被歼灭   " + battle1.getEnemyName() + " 获胜！\n" +
                          "*******************************";
        else if (battle1.getEnemyHp() <= 0)
            resultString = resultString +
                          '\n' +
                          battle1.getEnemyName() + "军 以被歼灭   " + battle1.getPlayerName() + " 获胜！\n" +
                          "*******************************";
    }
    
    private void printCombatLog()
    {        
        combatLogTextArea.append("*******************************\n");        
        combatLogTextArea.append(resultString);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        playerHpBar = new javax.swing.JProgressBar();
        enemyHpBar = new javax.swing.JProgressBar();
        attackButton = new javax.swing.JButton();
        abilityButton = new javax.swing.JButton();
        retreatButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        playerSoldiersLabel = new javax.swing.JLabel();
        enemySoldiersLabel = new javax.swing.JLabel();
        playerNameLabel = new javax.swing.JLabel();
        enemyNameLabel = new javax.swing.JLabel();
        playerStats = new javax.swing.JLabel();
        enemyStats = new javax.swing.JLabel();
        playerPortrait = new javax.swing.JLabel();
        enemyPortrait = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        combatLogTextArea = new javax.swing.JTextArea();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));

        playerHpBar.setStringPainted(true);

        enemyHpBar.setStringPainted(true);

        attackButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        attackButton.setText("进攻");
        attackButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                attackButtonActionPerformed(evt);
            }
        });

        abilityButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        abilityButton.setText("技能");
        abilityButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                abilityButtonActionPerformed(evt);
            }
        });

        retreatButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        retreatButton.setText("撤退");
        retreatButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                retreatButtonActionPerformed(evt);
            }
        });

        backButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        backButton.setText("返回");
        backButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                backButtonActionPerformed(evt);
            }
        });

        playerSoldiersLabel.setText("兵力： 10000/10000");

        enemySoldiersLabel.setText("兵力： 10000/10000");

        playerNameLabel.setText("曹操军");

        enemyNameLabel.setText("刘备军");

        playerStats.setText("攻击: 100    防御: 100");

        enemyStats.setText("攻击: 100    防御: 100");

        combatLogTextArea.setColumns(20);
        combatLogTextArea.setRows(6);
        jScrollPane1.setViewportView(combatLogTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(playerPortrait)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enemyPortrait))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(attackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(abilityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(retreatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(playerHpBar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(playerNameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(playerSoldiersLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enemyNameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(enemySoldiersLabel))
                            .addComponent(enemyHpBar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enemyStats)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(playerStats)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerNameLabel)
                    .addComponent(enemyNameLabel)
                    .addComponent(playerSoldiersLabel)
                    .addComponent(enemySoldiersLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerHpBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enemyHpBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerStats)
                    .addComponent(enemyStats))
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerPortrait)
                    .addComponent(enemyPortrait))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(attackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(retreatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(abilityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void attackButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_attackButtonActionPerformed
    {//GEN-HEADEREND:event_attackButtonActionPerformed
        startRound();
    }//GEN-LAST:event_attackButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_backButtonActionPerformed
    {//GEN-HEADEREND:event_backButtonActionPerformed
        currentCity.setSoldiers(battle1.getPlayerHp());
        attackingCity.setSoldiers(battle1.getEnemyHp());
        
        this.setVisible(false);
        gp.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void retreatButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_retreatButtonActionPerformed
    {//GEN-HEADEREND:event_retreatButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_retreatButtonActionPerformed

    private void abilityButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_abilityButtonActionPerformed
    {//GEN-HEADEREND:event_abilityButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_abilityButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abilityButton;
    private javax.swing.JButton attackButton;
    private javax.swing.JButton backButton;
    private javax.swing.JTextArea combatLogTextArea;
    private javax.swing.JProgressBar enemyHpBar;
    private javax.swing.JLabel enemyNameLabel;
    private javax.swing.JLabel enemyPortrait;
    private javax.swing.JLabel enemySoldiersLabel;
    private javax.swing.JLabel enemyStats;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JProgressBar playerHpBar;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JLabel playerPortrait;
    private javax.swing.JLabel playerSoldiersLabel;
    private javax.swing.JLabel playerStats;
    private javax.swing.JButton retreatButton;
    // End of variables declaration//GEN-END:variables
}
