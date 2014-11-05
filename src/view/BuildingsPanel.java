package view;

import javax.swing.JButton;
import javax.swing.JPanel;
import model.City;
import model.Player;
import model.buildings.Barracks;
import model.buildings.BlackSmith;
import model.buildings.Building;
import model.buildings.Farm;
import model.buildings.HorseFarm;
import model.buildings.Market;
import model.buildings.ResearchCentre;
import model.buildings.Wall;

/**
 *
 * @author RuN
 * This class will enable to user to build strucutres and the data will be saved and stored.
 */
public class BuildingsPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private final Player player;
    private final GamePanel gp;
    private final JButton[] buttons;
    private final Building[] structureData;
    private City selectedCity;
    private final int BARRACKS = 0, FARM = 1, MARKET = 2, WALL = 3, RESEARCH = 4, BLACKSMITH = 5, HORSEFARM = 6;
    private final int UPGRADE = 0, PAGE1 = 1, PAGE2 = 2;
    private int page;
    private int selectedSlot;
    private int selectedBuilding;

    /**
     * Creates new form NewJPanel
     * @param gp
     */
    public BuildingsPanel(GamePanel gp) {

        this.gp = gp;
        player = Player.getInstance();
        
        buttons = new JButton[8];
        structureData = new Building[10];
                
        initComponents();
        init();
    }
    
    private void init() {
        selectedSlot = 0;
                
        buttons[0] = emptySlot1;
        buttons[1] = emptySlot2;
        buttons[2] = emptySlot3;
        buttons[3] = emptySlot4;
        buttons[4] = emptySlot5;
        buttons[5] = emptySlot6;
        buttons[6] = emptySlot7;
        buttons[7] = emptySlot8;
        
        structureData[0] = new Barracks();
        structureData[1] = new Farm();
        structureData[2] = new Market();
        structureData[3] = new Wall();
        structureData[4] = new ResearchCentre();
        structureData[5] = new BlackSmith();
        structureData[6] = new HorseFarm();
    }
    
    public void updateData() {
        selectedCity = player.getSelectedCity();
        for (int i = 0; i < buttons.length; i++) {
            if (selectedCity.getSlot(i) == null){
                buttons[i].setText("空地");
            } else {
                buttons[i].setText(selectedCity.getSlot(i).getName() + selectedCity.getSlot(i).getLevelText());
            }
        }
        
        titleLabel.setText(selectedCity.getCityName() + "城建筑物");
        descriptionText.setText("");
        buildLayer.setVisible(false);
        
        soldierIncomeLabel.setText(selectedCity.getSoldierIncome() + "");
        foodIncomeLabel.setText(selectedCity.getFoodIncome() + "");
        goldIncomeLabel.setText(selectedCity.getGoldIncome() + "");
        goldLabel.setText("现有白银: " + player.getGold() + "两");
    }
    
    private void updateDescriptionText() {
        if (selectedCity.getSlot(selectedSlot) == null) {
            descriptionText.setText("您可以为这块空地建造设施。");
        } else if (selectedCity.getSlot(selectedSlot).getLevel() == 9) {
            descriptionText.setText("该建筑以达到最高等级。");
        } else {
            descriptionText.setText("可以提升" + selectedCity.getSlot(selectedSlot).getName() + "的等级从而增加其" + "金收入。");
        }
    }
    
    private void changeBuildDescription() {
        if (selectedCity.getSlot(selectedSlot).getLevel() == 1) {                                                         // Construction
            descriptionText.setText(selectedCity.getSlot(selectedSlot).getName() + " 建造完毕。");
        } else {
            // Level Up
            descriptionText.setText(selectedCity.getSlot(selectedSlot).getName() + selectedCity.getSlot(selectedSlot).getLevelText() + " 升级完毕。");
        }
    }
    
    private void changeIncomeValues() {
        // soldierIncome = soldierIncome + buildings[selectedSlot - 1].getUnitIncome();
        // goldIncome = goldIncome + buildings[selectedSlot - 1].getGoldIncome();
        // foodIncome = foodIncome + buildings[selectedSlot - 1].getFoodIncome();
        
        selectedCity.updateIncomes(selectedCity.getSlot(selectedSlot));
        
        soldierIncomeLabel.setText(selectedCity.getSoldierIncome() + "");
        foodIncomeLabel.setText(selectedCity.getFoodIncome() + "");
        goldIncomeLabel.setText(selectedCity.getGoldIncome() + "");
    }
    
    private void changeSlotButtonText() {
        buttons[selectedSlot].setText(selectedCity.getSlot(selectedSlot).getName());
    }
    
    private void createBuilding() {
        switch(selectedBuilding) {
            case FARM: selectedCity.setSlotData(selectedSlot, new Farm()); break;
            case MARKET: selectedCity.setSlotData(selectedSlot, new Market()); break;
            case BARRACKS: selectedCity.setSlotData(selectedSlot, new Barracks()); break;
            case WALL: selectedCity.setSlotData(selectedSlot, new Wall()); break;
            case RESEARCH: selectedCity.setSlotData(selectedSlot, new ResearchCentre()); break;
            case BLACKSMITH: selectedCity.setSlotData(selectedSlot, new BlackSmith()); break;
            case HORSEFARM: selectedCity.setSlotData(selectedSlot, new HorseFarm()); break;
        }
    }
    
    private void build() {
        applyBuildingCost();
        createBuilding();
        changeSlotButtonText();
        changeIncomeValues();
        changeBuildDescription();
    }
    
    private String getLevelUpText() {
        return selectedCity.getSlot(selectedSlot).getName() + "可升级为" + selectedCity.getSlot(selectedSlot).getLevel() + "级";
    }
    
    private void hideBuildLayer() {
        buildLayer.setVisible(false);
    }
    
    private void showBuildLayer() {
        if (isUpgradable()) {
            showUpgradeMenu();
        } else {
            gotoPage1();
        }
        buildLayer.setVisible(true);
    }
    
    private boolean isUpgradable() {
        return selectedCity.getSlot(selectedSlot) != null;
    }
    
    private void upgrade() {
        // Order is important！ do not mess up.
        String name = selectedCity.getSlot(selectedSlot).getName();
        
        if (selectedCity.getSlot(selectedSlot).getLevel() == 9) {             // Reach Max Lvl.
            descriptionText.setText(name + " 已达到最高等级！");
        } else {
            applyLvlUpCost(); 
            selectedCity.getSlot(selectedSlot).increaseLevel();
            buttons[selectedSlot].setText(name + selectedCity.getSlot(selectedSlot).getLevelText());
            changeBuildDescription();
        }
    }
    
    private void applyBuildingCost() {
        player.increaseGold(-structureData[selectedBuilding].getCost());
        goldLabel.setText("现有白银: " + player.getGold() + "两");
    }
    
    private void applyLvlUpCost() {
        // apply lvling up cost
    }
    
    private void showUpgradeMenu() {
        page = UPGRADE;
        buildLayerTitle.setText("升级");
        buildButton1.setText(getLevelUpText());
        buildButton2.setVisible(false);
        buildButton3.setVisible(false);
        buildButton4.setVisible(false);
        buildButton5.setVisible(false);
        nextButton.setVisible(false);
        descriptionText.setText("");
    }
    
    private void gotoPage1() {
        page = PAGE1;
        buildLayerTitle.setText("建造");
        buildButton1.setText(structureData[BARRACKS].getName());
        buildButton2.setText(structureData[FARM].getName());
        buildButton2.setVisible(true);
        buildButton3.setVisible(true);
        buildButton4.setVisible(true);
        buildButton5.setVisible(true);
        nextButton.setText("下一页");
        nextButton.setVisible(true);
        descriptionText.setText("");
    }
    
    private void gotoPage2() {  
        page = PAGE2;
        buildButton1.setText(structureData[BLACKSMITH].getName());
        buildButton2.setText(structureData[HORSEFARM].getName());
        buildButton3.setVisible(false);
        buildButton4.setVisible(false);
        buildButton5.setVisible(false);
        nextButton.setText("上一页");
        descriptionText.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        goldIncomeLabel = new javax.swing.JLabel();
        foodIncomeLabel = new javax.swing.JLabel();
        soldierIncomeLabel = new javax.swing.JLabel();
        buildingNameLabel = new javax.swing.JLabel();
        costLabel = new javax.swing.JLabel();
        goldLabel = new javax.swing.JLabel();
        emptySlot1 = new javax.swing.JButton();
        emptySlot2 = new javax.swing.JButton();
        emptySlot3 = new javax.swing.JButton();
        emptySlot4 = new javax.swing.JButton();
        emptySlot5 = new javax.swing.JButton();
        emptySlot6 = new javax.swing.JButton();
        emptySlot7 = new javax.swing.JButton();
        emptySlot8 = new javax.swing.JButton();
        finishButton = new javax.swing.JButton();
        descriptionText = new javax.swing.JTextField();
        buildLayer = new javax.swing.JLayeredPane();
        buildLayerTitle = new javax.swing.JLabel();
        buildButton1 = new javax.swing.JButton();
        buildButton2 = new javax.swing.JButton();
        buildButton3 = new javax.swing.JButton();
        buildButton4 = new javax.swing.JButton();
        buildButton5 = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        summaryDisplayLabel = new javax.swing.JLabel();
        goldDisplayLabel = new javax.swing.JLabel();
        foodDisplayLabel = new javax.swing.JLabel();
        soldierDisplayLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(153, 102, 0));
        titleLabel.setText("许昌城建筑物");
        add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 29, 200, -1));

        goldIncomeLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        goldIncomeLabel.setText("100");
        add(goldIncomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 196, 75, -1));

        foodIncomeLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        foodIncomeLabel.setText("200");
        add(foodIncomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 232, 75, -1));

        soldierIncomeLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        soldierIncomeLabel.setText("300");
        add(soldierIncomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 268, 75, -1));

        buildingNameLabel.setText("建造市场:");
        add(buildingNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(636, 334, 95, -1));

        costLabel.setText("所需白银: 0");
        add(costLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(636, 357, 134, -1));

        goldLabel.setText("现有白银: 2000两");
        add(goldLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(636, 380, 134, -1));

        emptySlot1.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot1.setText("空地");
        emptySlot1.setFocusable(false);
        emptySlot1.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot1ActionPerformed(evt);
            }
        });
        add(emptySlot1, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 98, -1, -1));

        emptySlot2.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot2.setText("空地");
        emptySlot2.setFocusable(false);
        emptySlot2.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot2ActionPerformed(evt);
            }
        });
        add(emptySlot2, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 98, -1, -1));

        emptySlot3.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot3.setText("空地");
        emptySlot3.setFocusable(false);
        emptySlot3.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot3ActionPerformed(evt);
            }
        });
        add(emptySlot3, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 206, -1, -1));

        emptySlot4.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot4.setText("空地");
        emptySlot4.setFocusable(false);
        emptySlot4.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot4ActionPerformed(evt);
            }
        });
        add(emptySlot4, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 206, -1, -1));

        emptySlot5.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot5.setText("空地");
        emptySlot5.setFocusable(false);
        emptySlot5.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot5ActionPerformed(evt);
            }
        });
        add(emptySlot5, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 314, -1, -1));

        emptySlot6.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot6.setText("空地");
        emptySlot6.setFocusable(false);
        emptySlot6.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot6ActionPerformed(evt);
            }
        });
        add(emptySlot6, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 314, -1, -1));

        emptySlot7.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot7.setText("空地");
        emptySlot7.setFocusable(false);
        emptySlot7.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot7ActionPerformed(evt);
            }
        });
        add(emptySlot7, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 422, -1, -1));

        emptySlot8.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot8.setText("空地");
        emptySlot8.setFocusable(false);
        emptySlot8.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot8ActionPerformed(evt);
            }
        });
        add(emptySlot8, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 422, -1, -1));

        finishButton.setText("完成");
        finishButton.setFocusable(false);
        finishButton.setMaximumSize(new java.awt.Dimension(80, 40));
        finishButton.setMinimumSize(new java.awt.Dimension(80, 40));
        finishButton.setPreferredSize(new java.awt.Dimension(80, 40));
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
            }
        });
        add(finishButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 531, -1, -1));

        descriptionText.setEditable(false);
        descriptionText.setFont(new java.awt.Font("Microsoft YaHei", 0, 14)); // NOI18N
        descriptionText.setForeground(new java.awt.Color(153, 102, 0));
        descriptionText.setText("您好");
        add(descriptionText, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 538, 385, -1));

        buildLayer.setMaximumSize(new java.awt.Dimension(211, 413));
        buildLayer.setMinimumSize(new java.awt.Dimension(211, 413));
        buildLayer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buildLayerTitle.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        buildLayerTitle.setForeground(new java.awt.Color(153, 102, 0));
        buildLayerTitle.setText("建造");
        buildLayer.add(buildLayerTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 80, -1));

        buildButton1.setText("兵营");
        buildButton1.setFocusable(false);
        buildButton1.setPreferredSize(new java.awt.Dimension(130, 90));
        buildButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildButton1MouseEntered(evt);
            }
        });
        buildButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildButton1ActionPerformed(evt);
            }
        });
        buildLayer.add(buildButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 44, 120, 50));

        buildButton2.setText("农田");
        buildButton2.setFocusable(false);
        buildButton2.setMinimumSize(new java.awt.Dimension(130, 90));
        buildButton2.setPreferredSize(new java.awt.Dimension(130, 90));
        buildButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildButton2MouseEntered(evt);
            }
        });
        buildButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildButton2ActionPerformed(evt);
            }
        });
        buildLayer.add(buildButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 101, 120, 50));

        buildButton3.setText("市场");
        buildButton3.setFocusable(false);
        buildButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildButton3MouseEntered(evt);
            }
        });
        buildButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildButton3ActionPerformed(evt);
            }
        });
        buildLayer.add(buildButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 158, 120, 50));

        buildButton4.setText("城墙");
        buildButton4.setFocusable(false);
        buildButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildButton4MouseEntered(evt);
            }
        });
        buildButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildButton4ActionPerformed(evt);
            }
        });
        buildLayer.add(buildButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 215, 120, 50));

        buildButton5.setText("研究所");
        buildButton5.setFocusable(false);
        buildButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildButton5MouseEntered(evt);
            }
        });
        buildButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildButton5ActionPerformed(evt);
            }
        });
        buildLayer.add(buildButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 272, 120, 50));

        nextButton.setText("下一页");
        nextButton.setFocusable(false);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        buildLayer.add(nextButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 329, 120, 50));

        backButton.setText("返回");
        backButton.setFocusable(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        buildLayer.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 386, 120, 50));

        add(buildLayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 76, 155, -1));

        summaryDisplayLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        summaryDisplayLabel.setText("设施总概要");
        add(summaryDisplayLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(636, 154, -1, -1));

        goldDisplayLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        goldDisplayLabel.setText("金收入：");
        add(goldDisplayLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(636, 196, -1, -1));

        foodDisplayLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        foodDisplayLabel.setText("粮收入：");
        add(foodDisplayLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(636, 232, -1, -1));

        soldierDisplayLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        soldierDisplayLabel.setText("征兵量：");
        add(soldierDisplayLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(636, 268, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buildButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buildButton1ActionPerformed
    {//GEN-HEADEREND:event_buildButton1ActionPerformed
        if (selectedCity.getSlot(selectedSlot) == null) {
            if (page == PAGE1) {selectedBuilding = BARRACKS;}
            else if (page == PAGE2) {selectedBuilding = BLACKSMITH;}
            build();
        } else {
            upgrade();
        }
        hideBuildLayer();
    }//GEN-LAST:event_buildButton1ActionPerformed

    private void emptySlot1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot1ActionPerformed
    {//GEN-HEADEREND:event_emptySlot1ActionPerformed
        selectedSlot = 0;
        showBuildLayer();
        updateDescriptionText();
    }//GEN-LAST:event_emptySlot1ActionPerformed

    private void emptySlot5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot5ActionPerformed
    {//GEN-HEADEREND:event_emptySlot5ActionPerformed
        selectedSlot = 4;
        showBuildLayer();
        updateDescriptionText();
    }//GEN-LAST:event_emptySlot5ActionPerformed

    private void emptySlot3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot3ActionPerformed
    {//GEN-HEADEREND:event_emptySlot3ActionPerformed
        selectedSlot = 2;
        showBuildLayer();
        updateDescriptionText();
    }//GEN-LAST:event_emptySlot3ActionPerformed

    private void emptySlot2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot2ActionPerformed
    {//GEN-HEADEREND:event_emptySlot2ActionPerformed
        selectedSlot = 1;
        showBuildLayer();
        updateDescriptionText();
    }//GEN-LAST:event_emptySlot2ActionPerformed

    private void emptySlot4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot4ActionPerformed
    {//GEN-HEADEREND:event_emptySlot4ActionPerformed
        selectedSlot = 3;
        showBuildLayer();
        updateDescriptionText();
    }//GEN-LAST:event_emptySlot4ActionPerformed

    private void emptySlot6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot6ActionPerformed
    {//GEN-HEADEREND:event_emptySlot6ActionPerformed
        selectedSlot = 5;
        showBuildLayer();
        updateDescriptionText();
    }//GEN-LAST:event_emptySlot6ActionPerformed

    private void emptySlot7ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot7ActionPerformed
    {//GEN-HEADEREND:event_emptySlot7ActionPerformed
        selectedSlot = 6;
        showBuildLayer();
        updateDescriptionText();
    }//GEN-LAST:event_emptySlot7ActionPerformed

    private void emptySlot8ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot8ActionPerformed
    {//GEN-HEADEREND:event_emptySlot8ActionPerformed
        selectedSlot = 7;
        showBuildLayer();
        updateDescriptionText();
    }//GEN-LAST:event_emptySlot8ActionPerformed

    private void buildButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buildButton2ActionPerformed
    {//GEN-HEADEREND:event_buildButton2ActionPerformed
        if (page == PAGE1) {selectedBuilding = FARM;}
        else if (page == PAGE2) {selectedBuilding = HORSEFARM;}
        build();
        hideBuildLayer();
    }//GEN-LAST:event_buildButton2ActionPerformed

    private void buildButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buildButton3ActionPerformed
    {//GEN-HEADEREND:event_buildButton3ActionPerformed
        selectedBuilding = MARKET;
        build();
        hideBuildLayer();
    }//GEN-LAST:event_buildButton3ActionPerformed

    private void buildButton4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buildButton4ActionPerformed
    {//GEN-HEADEREND:event_buildButton4ActionPerformed
        selectedBuilding = WALL;
        build();
        hideBuildLayer();
    }//GEN-LAST:event_buildButton4ActionPerformed

    private void buildButton5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buildButton5ActionPerformed
    {//GEN-HEADEREND:event_buildButton5ActionPerformed
        selectedBuilding = RESEARCH;
        build();
        hideBuildLayer();
    }//GEN-LAST:event_buildButton5ActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_backButtonActionPerformed
    {//GEN-HEADEREND:event_backButtonActionPerformed
        hideBuildLayer(); 
        descriptionText.setText(""); 
    }//GEN-LAST:event_backButtonActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_formMouseClicked
    {//GEN-HEADEREND:event_formMouseClicked
        gotoPage1();
        hideBuildLayer();
        descriptionText.setText("");
    }//GEN-LAST:event_formMouseClicked

    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_finishButtonActionPerformed
    {//GEN-HEADEREND:event_finishButtonActionPerformed
        this.setVisible(false);
        gp.setVisible(true);
    }//GEN-LAST:event_finishButtonActionPerformed

    private void buildButton1MouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_buildButton1MouseEntered
    {//GEN-HEADEREND:event_buildButton1MouseEntered
        buildingNameLabel.setText("建造" +  structureData[BARRACKS].getName() + ":");
        costLabel.setText("所需白银: " + structureData[BARRACKS].getCost() + "两");
    }//GEN-LAST:event_buildButton1MouseEntered

    private void buildButton2MouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_buildButton2MouseEntered
    {//GEN-HEADEREND:event_buildButton2MouseEntered
        buildingNameLabel.setText("建造" +  structureData[FARM].getName() + ":");
        costLabel.setText("所需白银: " + structureData[FARM].getCost() + "两");
    }//GEN-LAST:event_buildButton2MouseEntered

    private void buildButton3MouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_buildButton3MouseEntered
    {//GEN-HEADEREND:event_buildButton3MouseEntered
        buildingNameLabel.setText("建造" +  structureData[MARKET].getName() + ":");
        costLabel.setText("所需白银: " + structureData[MARKET].getCost() + "两");
    }//GEN-LAST:event_buildButton3MouseEntered

    private void buildButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildButton4MouseEntered
        buildingNameLabel.setText("建造" +  structureData[WALL].getName() + ":");
        costLabel.setText("所需白银: " + structureData[WALL].getCost() + "两");
    }//GEN-LAST:event_buildButton4MouseEntered

    private void buildButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildButton5MouseEntered
        buildingNameLabel.setText("建造" +  structureData[RESEARCH].getName() + ":");
        costLabel.setText("所需白银: " + structureData[RESEARCH].getCost() + "两");
    }//GEN-LAST:event_buildButton5MouseEntered

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        switch(page) {
            case PAGE1: gotoPage2(); break;
            case PAGE2: gotoPage1(); break;
            default: gotoPage1(); break;
        }
    }//GEN-LAST:event_nextButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton buildButton1;
    private javax.swing.JButton buildButton2;
    private javax.swing.JButton buildButton3;
    private javax.swing.JButton buildButton4;
    private javax.swing.JButton buildButton5;
    private javax.swing.JLayeredPane buildLayer;
    private javax.swing.JLabel buildLayerTitle;
    private javax.swing.JLabel buildingNameLabel;
    private javax.swing.JLabel costLabel;
    private javax.swing.JTextField descriptionText;
    private javax.swing.JButton emptySlot1;
    private javax.swing.JButton emptySlot2;
    private javax.swing.JButton emptySlot3;
    private javax.swing.JButton emptySlot4;
    private javax.swing.JButton emptySlot5;
    private javax.swing.JButton emptySlot6;
    private javax.swing.JButton emptySlot7;
    private javax.swing.JButton emptySlot8;
    private javax.swing.JButton finishButton;
    private javax.swing.JLabel foodDisplayLabel;
    private javax.swing.JLabel foodIncomeLabel;
    private javax.swing.JLabel goldDisplayLabel;
    private javax.swing.JLabel goldIncomeLabel;
    private javax.swing.JLabel goldLabel;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel soldierDisplayLabel;
    private javax.swing.JLabel soldierIncomeLabel;
    private javax.swing.JLabel summaryDisplayLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
