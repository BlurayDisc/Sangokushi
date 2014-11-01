/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.GameController;
import controller.GameParameters;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.City;

/**
 *
 * @author RuN
 */
public class GamePanel extends JPanel implements GameParameters {
    private static final long serialVersionUID = 1L;
    
    private final BuildingsPanel buildPanel;
    private final PrepareBattlePanel prepareBattlePanel;
    private Image menuImage;
    private Image hideMenuImage;
    private Image backgroundImage;
    private Rectangle[] menuButtons;
    private City selectedCity;
    private int selectedButton;
    private final MainFrame frame;
    private final GameController controller;
    private final ForceColourManager forceColourManager;
    private final AILogicFrame aiFrame;

    /**
     * Creates new form GamePanel
     */
    public GamePanel() {
        // Inititalise Variables
        super();
        frame = MainFrame.getInstance();
        controller = GameController.getInstance();
        selectedCity = null;
        removePanels();
        
        // Graphics Content
        forceColourManager = new ForceColourManager(controller.getForceList());
        InitBackground();
        initMenuRectangles();

        // Swing Content
        initComponents();
        initDisplayLabels();
        
        aiFrame = new AILogicFrame(this);
        aiFrame.setVisible(false);
        
        prepareBattlePanel = new PrepareBattlePanel(this);
        frame.getContentPane().add(prepareBattlePanel);
        prepareBattlePanel.setVisible(false);
        
        buildPanel = new BuildingsPanel(this);
        frame.getContentPane().add(buildPanel);
        buildPanel.setVisible(false);
    }
    
    @Override
    public void paintComponent(Graphics g) {   
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        drawBackground(g2);
        forceColourManager.drawPlayerForceColour(controller.getPlayer(), g2);
        forceColourManager.drawCities(g2);
    }
    
    private void removePanels() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(this);
        this.setBounds(0, 0, 800, 600);
    }
        
    private void InitBackground() {
        try{
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/resources/gameBackground.png"));
            menuImage = ImageIO.read(getClass().getResourceAsStream("/resources/optionPanelBackground.png"));
            hideMenuImage = ImageIO.read(getClass().getResourceAsStream("/resources/hideOptionPanel.png"));
        }
        catch (Exception e){}
    }
    
    private void initMenuRectangles() {
        menuButtons = new Rectangle[6];
        menuButtons[0] = new Rectangle(19 + 20, 29 + 90, 80, 40);
        menuButtons[1] = new Rectangle(19 + 20, 76 + 90, 80, 40);
        menuButtons[2] = new Rectangle(137 + 20, 29 + 90, 80, 40);
        menuButtons[3] = new Rectangle(137 + 20, 76+ 90, 80, 40);
        menuButtons[4] = new Rectangle(251 + 20, 28 + 90, 80, 40);
        menuButtons[5] = new Rectangle(251 + 20, 75 + 90, 80, 40);
    }
    
    private void initDisplayLabels() {
        // initial display
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
    
    private void drawBackground(Graphics2D g2) {
        g2.drawImage(backgroundImage, 0, 0, null);
    }
        
    private void restoreCitySquare() {
        Graphics2D g2 = (Graphics2D)getGraphics();
        forceColourManager.drawCity(selectedCity, controller.getOwnerOfCity(selectedCity).getForceColor(), g2);          // change city back
    }
    
    private void makeCitySquarePressed() {
        Graphics2D g2 = (Graphics2D)getGraphics();
        forceColourManager.drawCityPressed(selectedCity, controller.getOwnerOfCity(selectedCity).getForceColor(), g2);   // make city pressed
    }
    
    private void showSelectedPanel() {
        switch(selectedButton) {
            case MENU_BUTTON_BATTLE: battleButtonClicked();
                break;
            case MENU_BUTTON_BUILD: buildButtonClicked();
                break;
            case MENU_BUTTON_MILITARY: militaryButtonClicked();
                break;
            case MENU_BUTTON_POLITICS: politicsButtonClicked();
                break;
            case MENU_BUTTON_PERSONELS: personelsButtonClicked();
                break;
            case MENU_BUTTON_EXIT: exitButtonClicked();
                break;
        }
    }
    
    private void setMenuVisible(boolean isVisible) {
        Graphics g = getGraphics();
        
        if (isVisible) {
            g.drawImage(menuImage, 20, 90, null);
            menuButtons[0].setSize(80, 40);
            menuButtons[1].setSize(80, 40);
            menuButtons[2].setSize(80, 40);
            menuButtons[3].setSize(80, 40);
            menuButtons[4].setSize(80, 40);
            menuButtons[5].setSize(80, 40);
        } else {
            g.drawImage(hideMenuImage, 20, 90, null);
            menuButtons[0].setSize(0, 0);
            menuButtons[1].setSize(0, 0);
            menuButtons[2].setSize(0, 0);
            menuButtons[3].setSize(0, 0);
            menuButtons[4].setSize(0, 0);
            menuButtons[5].setSize(0, 0);
        }
    }
    
    private int getSelectedButton(MouseEvent evt) {
        for (int i = 0; i < menuButtons.length; i++) {
            if (menuButtons[i].contains(evt.getPoint())) {
                return i + 1;
            }
        }
        return MENU_BUTTON_UNSELECTED;
    }
    
    private City getSelectedCity(MouseEvent evt) {
        City city;
        for (int i = 1; i < 41; i ++) {
            city = controller.getCity(i);
            if (city.getRect().contains(evt.getPoint())) {
                return city;
            }
        }
        return null;
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
        setMenuVisible(false);
        
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
        selectedCity = getSelectedCity(evt);
        selectedButton = getSelectedButton(evt);
        
        if (selectedCity != null) {
            controller.setCity(selectedCity);          // if city is valid, then store it in the GameController class.
            makeCitySquarePressed();
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_formMouseReleased
    {//GEN-HEADEREND:event_formMouseReleased
        if (selectedCity != null) {                                         // Clicks a city
            if (controller.cityIsPlayerOwned()) {setMenuVisible(true);} 
            else {setMenuVisible(false);}   // To show or hide menu
            restoreCitySquare();            // update graphics
            updateDisplayLabels();          // change things to according values
        } else if (selectedButton != MENU_BUTTON_UNSELECTED) {              // Clicks a button
            setMenuVisible(false);          // hide menu
            showSelectedPanel();            // show next panel
        } else {                                                            // Clicks other location on map
            setMenuVisible(false);          // hide menu
            updateDisplayLabels();          // change things to 0
        }
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
