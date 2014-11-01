/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.GameController;
import controller.GameParameters;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.City;

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
    private Timer mouseClickTimer;
    private int selectedButton;
    private City selectedCity;
    private boolean isRunning = false;
    
    public GamePanel() {
        // Inititalise Variables
        super();
        frame = MainFrame.getInstance();
        controller = GameController.getInstance();
        controller.updateYear();
        selectedCity = null;
        
        // Graphics Content
        gameScreen = GameScreen.getInstance();
        
        // Swing Content
        initComponents();
        initDisplayLabels();
        initTimer();
        removePanels();
        
        // Create AIFrame
        aiFrame = new AILogicFrame(this);
        aiFrame.setVisible(false);
        
        // Create PrepareBattlePanel
        prepareBattlePanel = new PrepareBattlePanel(this);
        frame.getContentPane().add(prepareBattlePanel);
        prepareBattlePanel.setVisible(false);
        
        // Create BuildingsPanel
        buildPanel = new BuildingsPanel(this);
        frame.getContentPane().add(buildPanel);
        buildPanel.setVisible(false);
    }
    
    @Override
    public void paintComponent(Graphics g) {   
        super.paintComponent(g);
        gameScreen.setGraphics(g);
        gameScreen.drawBackground();
        gameScreen.drawPlayerForceColour();
        gameScreen.drawCities();
    }
    
    private void initTimer() {
        ActionListener mouseClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                mouseReleasedLogics();
                isRunning = false;
            }
        };
        mouseClickTimer = new Timer(150, mouseClickListener);
        mouseClickTimer.setRepeats(false);
    }
    
    private void removePanels() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(this);
        this.setBounds(0, 0, 800, 600);
    }
    
    private void initDisplayLabels() {
        nameLabel.setText(controller.getPlayerName());
        yearLabel.setText(controller.getYear() + "年 " + controller.getMonth() + "月");
        cityLabel.setText("城市: " + controller.getPlayer().getPrimaryCityName());
        charLabel.setText("武将: " + controller.getPlayer().getPrimaryCity().getCharNumber() + " 名");
        soldierLabel.setText("兵力: " + controller.getPlayer().getPrimaryCity().getSoldiers());
        populationLabel.setText("人口: " + controller.getPlayer().getPrimaryCity().getPopulation() + "万");
        goldLabel.setText("银两: " + controller.getPlayerGold());
        grainLabel.setText("粮草: " + controller.getPlayerFood());
    }

    private void updateDisplayLabels() {
        cityLabel.setText("城市: " + controller.getCityName());
        charLabel.setText("武将: " + controller.getCharNumber() + " 名");
        soldierLabel.setText("兵力: " + controller.getSoldiers(selectedCity));
        populationLabel.setText("人口: " + controller.getPopulation(selectedCity) + "万");
    }

    private void mouseReleasedLogics() {
        if (selectedCity != null) {                                         // Clicks a city
            if (controller.cityIsPlayerOwned()) {gameScreen.setMenuVisible();} 
            else {gameScreen.setMenuHidden();}      // To show or hide menu
                gameScreen.restoreCitySquare();     // update graphics
                updateDisplayLabels();              // change things to according values
        } else if (selectedButton != GameParameters.MENU_BUTTON_UNSELECTED) {              // Clicks a button
            gameScreen.setMenuHidden();     // hide menu
            showSelectedPanel();            // show next panel
        } else {                                                            // Clicks other location on map
            gameScreen.setMenuHidden();     // hide menu
            updateDisplayLabels();          // change things to 0
        }
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

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(800, 600));
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
                                .addComponent(soldierLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(populationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 495, Short.MAX_VALUE)
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
        soldierLabel.setText("兵力: " + controller.getSoldiers(selectedCity));
        goldLabel.setText("银两: " + controller.getPlayerGold());
        grainLabel.setText("粮草: " + controller.getPlayerFood());
    }//GEN-LAST:event_formComponentShown

    private void proceedButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_proceedButtonActionPerformed
    {//GEN-HEADEREND:event_proceedButtonActionPerformed
        gameScreen.setMenuHidden();
        
        controller.increaseTime();
        controller.increaseResources();
        controller.increasePopulation();
        
        // update labels
        yearLabel.setText(controller.getYear() + "年 " + controller.getMonth() + "月");
        goldLabel.setText("银两: " + controller.getPlayerGold());
        grainLabel.setText("粮草: " + controller.getPlayerFood());
        soldierLabel.setText("兵力: " + controller.getSoldiers(selectedCity));
        populationLabel.setText("人口: " + controller.getPopulation(selectedCity) + "万");
        
        aiFrame.setVisible(true);
        aiFrame.calcAI();
    }//GEN-LAST:event_proceedButtonActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_formMousePressed
    {//GEN-HEADEREND:event_formMousePressed
        if (isRunning) {return;}
        
        gameScreen.setGraphics(getGraphics());      // sets a graphic to work with.
        
        selectedCity = gameScreen.getSelectedCity(evt);
        selectedButton = gameScreen.getSelectedButton(evt);
        
        if (selectedCity != null) {
            controller.setCity(selectedCity);          // if city is valid, then store it in the GameController class.
            gameScreen.makeCitySquarePressed();
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_formMouseReleased
    {//GEN-HEADEREND:event_formMouseReleased
        if (isRunning) {return;}
        
        isRunning = true;
        mouseClickTimer.start();
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
