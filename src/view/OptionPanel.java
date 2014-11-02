/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.GameMessage;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import model.Database;
import model.Player;

/**
 *
 * @author RuN
 */
public final class OptionPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private final GameMessage gm;
    private String year, scenarioName, playerName;
    
    public OptionPanel() {
        super();
        initComponents();
        setSize(800, 600);
        
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Microsoft YaHei", Font.PLAIN, 18)));
        difficultyGroup.add(easy);
        difficultyGroup.add(medium);
        difficultyGroup.add(hard);
        medium.doClick();
        
        gm = new GameMessage();
    }
    
    private void createGamePanel() {
        // Create GamePanel
        GamePanel gamePanel = new GamePanel();
        MainFrame.getInstance().getContentPane().add(gamePanel);
        gamePanel.setVisible(true);
    }
    
    public void updateOptionPanel() {
        year = Database.getInstance().getYear() + "年";
        scenarioName = Database.getInstance().getScenarioName();
        playerName = Player.getInstance().getPlayerName();
        
        yearLabel.setText(year);
        scenarioLabel.setText(scenarioName);
        playerForceLabel.setText(playerName);
        
        switch (playerName) {
            case "曹操": forceDescription.setText(gm.getCaocaoDesc()); break;
            case "刘备": forceDescription.setText(gm.getLiubeiDesc()); break;
            case "孙权": forceDescription.setText(gm.getSunquanDesc()); break;
            case "刘表": forceDescription.setText(gm.getLiubiaoDesc()); break;
            default:     forceDescription.setText("未填写"); break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        difficultyGroup = new javax.swing.ButtonGroup();
        titleLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        yearLabel = new javax.swing.JLabel();
        scenarioLabel = new javax.swing.JLabel();
        playerForceLabel = new javax.swing.JLabel();
        difficultyLabel = new javax.swing.JLabel();
        easy = new javax.swing.JRadioButton();
        medium = new javax.swing.JRadioButton();
        hard = new javax.swing.JRadioButton();
        descriptionScrollPane = new javax.swing.JScrollPane();
        forceDescription = new javax.swing.JTextArea();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));

        titleLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(102, 0, 0));
        titleLabel.setText("选项");

        backButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        backButton.setForeground(new java.awt.Color(102, 0, 0));
        backButton.setText("返回");
        backButton.setFocusable(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        startButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        startButton.setForeground(new java.awt.Color(102, 0, 0));
        startButton.setText("开始游戏");
        startButton.setFocusable(false);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        yearLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        yearLabel.setText("251年");

        scenarioLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        scenarioLabel.setText("英雄集结");

        playerForceLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        playerForceLabel.setText("曹操");

        difficultyLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 16)); // NOI18N
        difficultyLabel.setText("难度");

        easy.setFont(new java.awt.Font("Microsoft YaHei", 0, 16)); // NOI18N
        easy.setText("简单");

        medium.setFont(new java.awt.Font("Microsoft YaHei", 0, 16)); // NOI18N
        medium.setText("中等");

        hard.setFont(new java.awt.Font("Microsoft YaHei", 0, 16)); // NOI18N
        hard.setText("困难");

        forceDescription.setEditable(false);
        forceDescription.setColumns(20);
        forceDescription.setFont(new java.awt.Font("Microsoft YaHei", 0, 16)); // NOI18N
        forceDescription.setRows(5);
        forceDescription.setText("你好");
        descriptionScrollPane.setViewportView(forceDescription);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(376, 376, 376)
                        .addComponent(titleLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(difficultyLabel)
                                .addGap(78, 78, 78)
                                .addComponent(easy)
                                .addGap(57, 57, 57)
                                .addComponent(medium)
                                .addGap(75, 75, 75)
                                .addComponent(hard))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(yearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(scenarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playerForceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(titleLabel)
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearLabel)
                    .addComponent(playerForceLabel)
                    .addComponent(scenarioLabel))
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(difficultyLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(easy)
                        .addComponent(medium)
                        .addComponent(hard)))
                .addGap(41, 41, 41)
                .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_backButtonActionPerformed
    {//GEN-HEADEREND:event_backButtonActionPerformed
        MainFrame.getInstance().showForcePanel();
        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_startButtonActionPerformed
    {//GEN-HEADEREND:event_startButtonActionPerformed
        //Custom button text
        Object[] options = {"确认", "取消"};
        int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(), "开始游戏吗？", "三国志", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (choice == 0) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "    251年 英雄集结 " + playerName, "开始游戏", JOptionPane.PLAIN_MESSAGE);
            createGamePanel();
            this.setVisible(false);
        }
    }//GEN-LAST:event_startButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.ButtonGroup difficultyGroup;
    private javax.swing.JLabel difficultyLabel;
    private javax.swing.JRadioButton easy;
    private javax.swing.JTextArea forceDescription;
    private javax.swing.JRadioButton hard;
    private javax.swing.JRadioButton medium;
    private javax.swing.JLabel playerForceLabel;
    private javax.swing.JLabel scenarioLabel;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel yearLabel;
    // End of variables declaration//GEN-END:variables
}
