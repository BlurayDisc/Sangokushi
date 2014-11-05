/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.GameController;
import controller.GameParameters;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.City;
import model.Player;

/**
 *
 * @author RuN
 */
public class GamePanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private final PrepareBattlePanel prepareBattlePanel;
    private final GameController controller;
    private final BuildingsPanel buildPanel;
    private final GameScreen gameScreen;
    private final AILogicFrame aiFrame;
    private final MainFrame frame;
    private final Player player;
    private int selectedButton;
    private City selectedCity;
    private boolean showMenu;
    private boolean pressed;
    private boolean released;
    
    public GamePanel() {

        frame = MainFrame.getInstance();
                        
        // Graphics Content
        gameScreen = new GameScreen();
        
        // Inititalise Variables
        player = Player.getInstance();
        controller = GameController.getInstance();
        controller.update();
        selectedCity = null;
        showMenu = false;
        pressed = false;
        released = false;
        
        // Swing Content
        removePanels();
        initComponents();
        setSize(800, 600);
        frame.requestFocus();
        initLabels();
        
        // Create AIFrame
        aiFrame = new AILogicFrame(this);
        aiFrame.setVisible(false);
        
        // Create PrepareBattlePanel
        prepareBattlePanel = new PrepareBattlePanel(this);
        frame.getContentPane().add(prepareBattlePanel);
        prepareBattlePanel.setSize(800, 600);
        prepareBattlePanel.setVisible(false);
        
        // Create BuildingsPanel
        buildPanel = new BuildingsPanel(this);
        frame.getContentPane().add(buildPanel);
        buildPanel.setSize(800, 600);
        buildPanel.setVisible(false);
    }
    
    @Override
    public void paintComponent(Graphics g) {   
        super.paintComponent(g);
        
        // Sets the Graphics to work with
        gameScreen.setGraphics(g);
        
        // Initialisation
        gameScreen.drawBackground();
        gameScreen.drawPlayerForceColour();
        gameScreen.drawCities();
        
        // City Pressed
        if (pressed) {gameScreen.drawCityPressed(); pressed = false;}
        if (released) {gameScreen.drawCityReleased(); released = false;}
        
        // Show menu
        if (showMenu) {gameScreen.setMenuVisible();}
        else {gameScreen.setMenuHidden();};
    }
    
    private void removePanels() {
        frame.getContentPane().removeAll();
    }
    
    private void initLabels() {
        nameLabel.setText(player.getPlayerName());
        yearLabel.setText(controller.getYear() + "年 " + controller.getMonth() + "月");
        cityLabel.setText("城市: " + player.getPlayerForce().getPrimaryCityName());
        charLabel.setText("武将: " + player.getPlayerForce().getPrimaryCity().getNumCharacters() + " 名");
        soldierLabel.setText("兵力: " + player.getPlayerForce().getPrimaryCity().getSoldiers());
        populationLabel.setText("人口: " + player.getPlayerForce().getPrimaryCity().getPopulation() + "万");
        goldLabel.setText("银两: " + player.getGold());
        grainLabel.setText("粮草: " + player.getFood());
    }

    private void updateLabelsForMousePress() {
        cityLabel.setText("城市: " + player.getSelectedCityName());
        charLabel.setText("武将: " + player.getSelectedNumCharacters() + " 名");
        soldierLabel.setText("兵力: " + player.getSelectedSoldiers());
        populationLabel.setText("人口: " + player.getSelectedPopulation() + "万");
    }
    
    private void updateLabelsForPanelShown() {
        yearLabel.setText(controller.getYear() + "年 " + controller.getMonth() + "月");
        goldLabel.setText("银两: " + player.getGold());
        grainLabel.setText("粮草: " + player.getFood());
        soldierLabel.setText("兵力: " + player.getSelectedSoldiers());
        populationLabel.setText("人口: " + player.getSelectedPopulation() + "万");
    }
    
    private void showSelectedPanel() {
        switch(selectedButton) {
            case GameParameters.MENU_BUTTON_BATTLE: battleButtonClicked();
                break;
            case GameParameters.MENU_BUTTON_BUILD: buildButtonClicked();
                break;
            case GameParameters.MENU_BUTTON_MILITARY: militaryButtonClicked();
                break;
            case GameParameters.MENU_BUTTON_POLITICS: politicsButtonClicked();
                break;
            case GameParameters.MENU_BUTTON_PERSONELS: personelsButtonClicked();
                break;
            case GameParameters.MENU_BUTTON_EXIT: exitButtonClicked();
                break;
        }
    }
    
    public JButton getProceedButton() {
        return proceedButton;
    }
        
    private void battleButtonClicked() {
        prepareBattlePanel.init();
        prepareBattlePanel.setVisible(true);
        this.setVisible(false);
    }
    
    private void buildButtonClicked() {
        buildPanel.updateData();
        buildPanel.setVisible(true);
        this.setVisible(false);
    }
    
    private void militaryButtonClicked() {
        System.out.println("军事被选中");
    }
    
    private void politicsButtonClicked() {
        System.out.println("内政被选中");
    }
    
    private void personelsButtonClicked() {
        System.out.println("武将被选中");
    }
    
    private void exitButtonClicked() {
        System.exit(0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        yearLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        goldLabel = new javax.swing.JLabel();
        grainLabel = new javax.swing.JLabel();
        cityLabel = new javax.swing.JLabel();
        soldierLabel = new javax.swing.JLabel();
        populationLabel = new javax.swing.JLabel();
        charLabel = new javax.swing.JLabel();
        proceedButton = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        yearLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 16)); // NOI18N
        yearLabel.setText("251年 1月");

        nameLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 16)); // NOI18N
        nameLabel.setText("曹操");

        goldLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 16)); // NOI18N
        goldLabel.setText("银两: ");

        grainLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 16)); // NOI18N
        grainLabel.setText("粮草: ");

        cityLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        cityLabel.setText("城市: 无");

        soldierLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        soldierLabel.setText("兵力: 0");

        populationLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        populationLabel.setText("人口: 0");

        charLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        charLabel.setText("武将:  无");

        proceedButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/proceedButton.png"))); // NOI18N
        proceedButton.setBorder(null);
        proceedButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/proceedButtonPressed.png"))); // NOI18N
        proceedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proceedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(proceedButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(yearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(128, 128, 128)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(charLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soldierLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(populationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(goldLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(grainLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(grainLabel)
                            .addComponent(goldLabel)
                            .addComponent(populationLabel)
                            .addComponent(soldierLabel)
                            .addComponent(charLabel)
                            .addComponent(nameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 481, Short.MAX_VALUE)
                        .addComponent(proceedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(yearLabel)
                            .addComponent(cityLabel))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_formComponentShown
    {//GEN-HEADEREND:event_formComponentShown
        updateLabelsForPanelShown();
    }//GEN-LAST:event_formComponentShown

    private void proceedButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_proceedButtonActionPerformed
    {//GEN-HEADEREND:event_proceedButtonActionPerformed
        showMenu = false;
        
        controller.increaseTime();
        controller.increaseResources();
        controller.increasePopulation();
        
        // update labels
        updateLabelsForPanelShown();
        
        aiFrame.setVisible(true);
        aiFrame.calcAI();
    }//GEN-LAST:event_proceedButtonActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_formMousePressed
    {//GEN-HEADEREND:event_formMousePressed
        // Calculate mouse click
        selectedButton = gameScreen.getSelectedButton(evt);
        
        // If a menu button was not selected
        if (selectedButton == GameParameters.MENU_BUTTON_UNSELECTED) {
            // Calculate mouse click
            selectedCity = gameScreen.getSelectedCity(evt);
            player.setSelectedCity(selectedCity);
        }
        
        if (selectedCity != null) {
            
            // Make city visual Pressed.
            pressed = true;
            repaint(selectedCity.x, selectedCity.y, selectedCity.length + selectedCity.offset, selectedCity.length + selectedCity.offset);
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_formMouseReleased
    {//GEN-HEADEREND:event_formMouseReleased
        // Clicks a city
        if (selectedCity != null) {
            
             // Make city visual Restored.
            released = true;                                    
            repaint(selectedCity.x, selectedCity.y, selectedCity.length + selectedCity.offset, selectedCity.length + selectedCity.offset);
            
            // To show or hide the menu
            if (player.owns(selectedCity)) {showMenu = true;}
            else {showMenu = false;}
            
        // Clicks other location on map
        } else {showMenu = false;}

        // Repaint menu area
        repaint(gameScreen.menu_xPos, gameScreen.menu_yPos, gameScreen.menu_xPos + gameScreen.menu_height, gameScreen.menu_yPos + gameScreen.menu_width);
        
        // Update Labels
        updateLabelsForMousePress();
        
        // Clicks a button  
        if (selectedButton != GameParameters.MENU_BUTTON_UNSELECTED) {showSelectedPanel();}
    }//GEN-LAST:event_formMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel charLabel;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JLabel goldLabel;
    private javax.swing.JLabel grainLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel populationLabel;
    private javax.swing.JButton proceedButton;
    private javax.swing.JLabel soldierLabel;
    private javax.swing.JLabel yearLabel;
    // End of variables declaration//GEN-END:variables
}
