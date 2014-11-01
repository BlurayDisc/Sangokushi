package view;

import controller.GameController;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.buildings.Barracks;
import model.buildings.Building;
import model.buildings.Farm;
import model.buildings.Market;
import model.buildings.ResearchCentre;
import model.buildings.Wall;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RuN
 */
public class BuildingsPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private final GamePanel gp;
    private final GameController gc;
    private final JButton[] buttons;
    private final Building[] structureDataList;
    private final int BARRACKS = 0, FARM = 1, MARKET = 2, WALL = 3, RESEARCH = 4;
    private int selectedSlot;
    private int selectedBuilding;

    /**
     * Creates new form NewJPanel
     * @param gp
     */
    public BuildingsPanel(GamePanel gp)
    {
        super();
        setSize(800, 600);
        
        this.gp = gp;
        gc = GameController.getInstance();
        
        buttons = new JButton[8];
        structureDataList = new Building[5];
                
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
        
        structureDataList[0] = new Barracks();
        structureDataList[1] = new Farm();
        structureDataList[2] = new Market();
        structureDataList[3] = new Wall();
        structureDataList[4] = new ResearchCentre();
    }
    
    public void updateData() {
        for (int i = 0; i < buttons.length; i++) {
            if (gc.getSlot(i) == null){
                buttons[i].setText("空地");
            } else {
                buttons[i].setText(gc.getSlot(i).getName() + gc.getSlot(i).getLevelText());
            }
        }
        
        titleLabel.setText(gc.getSelectedCity().getCityName() + "城建筑物");
        descriptionText.setText("");
        buildLayer.setVisible(false);
        
        soldierIncomeLabel.setText(gc.getSelectedCity().getSoldierIncome() + "");
        foodIncomeLabel.setText(gc.getSelectedCity().getFoodIncome() + "");
        goldIncomeLabel.setText(gc.getSelectedCity().getGoldIncome() + "");
        goldLabel.setText("现有白银: " + gc.getPlayerGold() + "两");
    }
    
    private void changeSlotDescription() {
        if (gc.getSlot(selectedSlot) == null) {
            descriptionText.setText("您可以为这块空地建造设施。");
        } else if (gc.getSlot(selectedSlot).getLevel() == 9) {
            descriptionText.setText("该建筑以达到最高等级。");
        } else {
            descriptionText.setText("可以提升" + gc.getSlot(selectedSlot).getName() + "的等级从而增加其" + "金收入。");
        }
    }
    
    private void changeBuildDescription() {
        if (gc.getSlot(selectedSlot).getLevel() == 1) {                                                         // Construction
            descriptionText.setText(gc.getSlot(selectedSlot).getName() + " 建造完毕。");
        } else {
            // Level Up
            descriptionText.setText(gc.getSlot(selectedSlot).getName() + gc.getSlot(selectedSlot).getLevelText() + " 升级完毕。");
        }
    }
    
    private void changeIncomeValues() {
        // soldierIncome = soldierIncome + buildings[selectedSlot - 1].getUnitIncome();
        // goldIncome = goldIncome + buildings[selectedSlot - 1].getGoldIncome();
        // foodIncome = foodIncome + buildings[selectedSlot - 1].getFoodIncome();
        
        gc.getSelectedCity().updateIncomes(gc.getSlot(selectedSlot));
        
        soldierIncomeLabel.setText(gc.getSelectedCity().getSoldierIncome() + "");
        foodIncomeLabel.setText(gc.getSelectedCity().getFoodIncome() + "");
        goldIncomeLabel.setText(gc.getSelectedCity().getGoldIncome() + "");
    }
    
    private void changeSlotButtonText() {
        buttons[selectedSlot].setText(gc.getSlot(selectedSlot).getName());
    }
    
    private void createBuilding() {
        if (selectedBuilding == FARM){
            gc.setSlotData(selectedSlot, new Farm());
        } else if (selectedBuilding == MARKET) {
            gc.setSlotData(selectedSlot, new Market());
        } else if (selectedBuilding == BARRACKS) {
            gc.setSlotData(selectedSlot, new Barracks());   
        } else if (selectedBuilding == WALL) {
            gc.setSlotData(selectedSlot, new Wall());
        } else if (selectedBuilding == RESEARCH) {
            gc.setSlotData(selectedSlot, new ResearchCentre());
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
        return gc.getSlot(selectedSlot).getName() + "可升级为" + gc.getSlot(selectedSlot).getLevel() + "级";
    }
    
    private void hideBuildLayer() {
        buildLayer.setVisible(false);
    }
    
    private void showBuildLayer() {
        changeSlotDescription();
        
        if (gc.getSlot(selectedSlot) == null) {       // the slot is empty, initliase create menu again.
            buildLayerTitle.setText("建造");
            buildBarracksButton.setText("兵营");
            buildFarmButton.setVisible(true);
            buildMarketButton.setVisible(true);
            buildWallButton.setVisible(true);
            buildResearchButton.setVisible(true);
        } else {                               // If buildings already exist, change create menu to upgrade meu.
            buildLayerTitle.setText("升级");
            buildBarracksButton.setText(getLevelUpText());
            buildFarmButton.setVisible(false);
            buildMarketButton.setVisible(false);
            buildWallButton.setVisible(false);
            buildResearchButton.setVisible(false);
        }
        backButton.setVisible(true);
        buildLayer.setVisible(true);
    }
    
    private void upgrade() {
        // Order is important！ do not mess up.
        String name = gc.getSlot(selectedSlot).getName();
        
        if (gc.getSlot(selectedSlot).getLevel() == 9) {             // Reach Max Lvl.
            descriptionText.setText(name + " 已达到最高等级！");
        } else {
            applyLvlUpCost(); 
            gc.getSlot(selectedSlot).increaseLevel();
            buttons[selectedSlot].setText(name + gc.getSlot(selectedSlot).getLevelText());
            changeBuildDescription();
        }
    }
    
    private void applyBuildingCost() {
        gc.reducePlayerGold(structureDataList[selectedBuilding].getCost());
        goldLabel.setText("现有白银: " + gc.getPlayerGold() + "两");
    }
    
    private void applyLvlUpCost() {
        // apply lvling up cost
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
        buildBarracksButton = new javax.swing.JButton();
        buildFarmButton = new javax.swing.JButton();
        buildMarketButton = new javax.swing.JButton();
        buildWallButton = new javax.swing.JButton();
        buildResearchButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        summaryDisplayLabel = new javax.swing.JLabel();
        goldDisplayLabel = new javax.swing.JLabel();
        foodDisplayLabel = new javax.swing.JLabel();
        soldierDisplayLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(153, 102, 0));
        titleLabel.setText("许昌城建筑物");

        goldIncomeLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        goldIncomeLabel.setText("100");

        foodIncomeLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        foodIncomeLabel.setText("200");

        soldierIncomeLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        soldierIncomeLabel.setText("300");

        buildingNameLabel.setText("建造市场:");

        costLabel.setText("所需白银: 0");

        goldLabel.setText("现有白银: 2000两");

        emptySlot1.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot1.setText("空地");
        emptySlot1.setFocusable(false);
        emptySlot1.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot1ActionPerformed(evt);
            }
        });

        emptySlot2.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot2.setText("空地");
        emptySlot2.setFocusable(false);
        emptySlot2.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot2ActionPerformed(evt);
            }
        });

        emptySlot3.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot3.setText("空地");
        emptySlot3.setFocusable(false);
        emptySlot3.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot3ActionPerformed(evt);
            }
        });

        emptySlot4.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot4.setText("空地");
        emptySlot4.setFocusable(false);
        emptySlot4.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot4ActionPerformed(evt);
            }
        });

        emptySlot5.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot5.setText("空地");
        emptySlot5.setFocusable(false);
        emptySlot5.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot5ActionPerformed(evt);
            }
        });

        emptySlot6.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot6.setText("空地");
        emptySlot6.setFocusable(false);
        emptySlot6.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot6ActionPerformed(evt);
            }
        });

        emptySlot7.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot7.setText("空地");
        emptySlot7.setFocusable(false);
        emptySlot7.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot7ActionPerformed(evt);
            }
        });

        emptySlot8.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        emptySlot8.setText("空地");
        emptySlot8.setFocusable(false);
        emptySlot8.setPreferredSize(new java.awt.Dimension(130, 90));
        emptySlot8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptySlot8ActionPerformed(evt);
            }
        });

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

        descriptionText.setEditable(false);
        descriptionText.setFont(new java.awt.Font("Microsoft YaHei", 0, 14)); // NOI18N
        descriptionText.setForeground(new java.awt.Color(153, 102, 0));
        descriptionText.setText("您好");

        buildLayer.setMaximumSize(new java.awt.Dimension(211, 413));
        buildLayer.setMinimumSize(new java.awt.Dimension(211, 413));

        buildLayerTitle.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        buildLayerTitle.setForeground(new java.awt.Color(153, 102, 0));
        buildLayerTitle.setText("建造");

        buildBarracksButton.setText("兵营");
        buildBarracksButton.setFocusable(false);
        buildBarracksButton.setPreferredSize(new java.awt.Dimension(130, 90));
        buildBarracksButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildBarracksButtonMouseEntered(evt);
            }
        });
        buildBarracksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildBarracksButtonActionPerformed(evt);
            }
        });

        buildFarmButton.setText("农田");
        buildFarmButton.setFocusable(false);
        buildFarmButton.setMinimumSize(new java.awt.Dimension(130, 90));
        buildFarmButton.setPreferredSize(new java.awt.Dimension(130, 90));
        buildFarmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildFarmButtonMouseEntered(evt);
            }
        });
        buildFarmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildFarmButtonActionPerformed(evt);
            }
        });

        buildMarketButton.setText("市场");
        buildMarketButton.setFocusable(false);
        buildMarketButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildMarketButtonMouseEntered(evt);
            }
        });
        buildMarketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildMarketButtonActionPerformed(evt);
            }
        });

        buildWallButton.setText("城墙");
        buildWallButton.setFocusable(false);
        buildWallButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildWallButtonMouseEntered(evt);
            }
        });
        buildWallButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildWallButtonActionPerformed(evt);
            }
        });

        buildResearchButton.setText("研究所");
        buildResearchButton.setFocusable(false);
        buildResearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buildResearchButtonMouseEntered(evt);
            }
        });
        buildResearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildResearchButtonActionPerformed(evt);
            }
        });

        backButton.setText("返回");
        backButton.setFocusable(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buildLayerLayout = new javax.swing.GroupLayout(buildLayer);
        buildLayer.setLayout(buildLayerLayout);
        buildLayerLayout.setHorizontalGroup(
            buildLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buildLayerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(buildLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buildBarracksButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(buildLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buildResearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buildWallButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buildMarketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buildFarmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(buildLayerLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(buildLayerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buildLayerLayout.setVerticalGroup(
            buildLayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buildLayerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buildLayerTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buildBarracksButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buildFarmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buildMarketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buildWallButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buildResearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        buildLayer.setLayer(buildLayerTitle, javax.swing.JLayeredPane.DEFAULT_LAYER);
        buildLayer.setLayer(buildBarracksButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        buildLayer.setLayer(buildFarmButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        buildLayer.setLayer(buildMarketButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        buildLayer.setLayer(buildWallButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        buildLayer.setLayer(buildResearchButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        buildLayer.setLayer(backButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        summaryDisplayLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        summaryDisplayLabel.setText("设施总概要");

        goldDisplayLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        goldDisplayLabel.setText("金收入：");

        foodDisplayLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        foodDisplayLabel.setText("粮收入：");

        soldierDisplayLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 13)); // NOI18N
        soldierDisplayLabel.setText("征兵量：");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(542, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(emptySlot1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emptySlot5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emptySlot3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emptySlot7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(emptySlot4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emptySlot2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emptySlot6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emptySlot8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addComponent(buildLayer, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(soldierDisplayLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(soldierIncomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(foodDisplayLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(foodIncomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(goldDisplayLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(goldIncomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(summaryDisplayLabel)
                                    .addComponent(buildingNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(costLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(goldLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(descriptionText, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(finishButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(titleLabel)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(emptySlot1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emptySlot2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(emptySlot4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emptySlot3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(emptySlot6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emptySlot5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(emptySlot7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emptySlot8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(buildLayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(summaryDisplayLabel)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(goldDisplayLabel)
                            .addComponent(goldIncomeLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(foodDisplayLabel)
                            .addComponent(foodIncomeLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(soldierDisplayLabel)
                            .addComponent(soldierIncomeLabel))
                        .addGap(67, 67, 67)
                        .addComponent(buildingNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(costLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(goldLabel)
                        .addGap(119, 119, 119)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finishButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buildBarracksButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buildBarracksButtonActionPerformed
    {//GEN-HEADEREND:event_buildBarracksButtonActionPerformed
        if (gc.getSlot(selectedSlot) == null) {
            selectedBuilding = BARRACKS;
            build();
        } else {
            upgrade();
        }
        hideBuildLayer();
    }//GEN-LAST:event_buildBarracksButtonActionPerformed

    private void emptySlot1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot1ActionPerformed
    {//GEN-HEADEREND:event_emptySlot1ActionPerformed
        selectedSlot = 0;
        showBuildLayer();
    }//GEN-LAST:event_emptySlot1ActionPerformed

    private void emptySlot5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot5ActionPerformed
    {//GEN-HEADEREND:event_emptySlot5ActionPerformed
        selectedSlot = 4;
        showBuildLayer();
    }//GEN-LAST:event_emptySlot5ActionPerformed

    private void emptySlot3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot3ActionPerformed
    {//GEN-HEADEREND:event_emptySlot3ActionPerformed
        selectedSlot = 2;
        showBuildLayer();
    }//GEN-LAST:event_emptySlot3ActionPerformed

    private void emptySlot2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot2ActionPerformed
    {//GEN-HEADEREND:event_emptySlot2ActionPerformed
        selectedSlot = 1;
        showBuildLayer();
    }//GEN-LAST:event_emptySlot2ActionPerformed

    private void emptySlot4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot4ActionPerformed
    {//GEN-HEADEREND:event_emptySlot4ActionPerformed
        selectedSlot = 3;
        showBuildLayer();
    }//GEN-LAST:event_emptySlot4ActionPerformed

    private void emptySlot6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot6ActionPerformed
    {//GEN-HEADEREND:event_emptySlot6ActionPerformed
        selectedSlot = 5;
        showBuildLayer();
    }//GEN-LAST:event_emptySlot6ActionPerformed

    private void emptySlot7ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot7ActionPerformed
    {//GEN-HEADEREND:event_emptySlot7ActionPerformed
        selectedSlot = 6;
        showBuildLayer();
    }//GEN-LAST:event_emptySlot7ActionPerformed

    private void emptySlot8ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_emptySlot8ActionPerformed
    {//GEN-HEADEREND:event_emptySlot8ActionPerformed
        selectedSlot = 7;
        showBuildLayer();
    }//GEN-LAST:event_emptySlot8ActionPerformed

    private void buildFarmButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buildFarmButtonActionPerformed
    {//GEN-HEADEREND:event_buildFarmButtonActionPerformed
        selectedBuilding = FARM;
        build();
        hideBuildLayer();
    }//GEN-LAST:event_buildFarmButtonActionPerformed

    private void buildMarketButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buildMarketButtonActionPerformed
    {//GEN-HEADEREND:event_buildMarketButtonActionPerformed
        selectedBuilding = MARKET;
        build();
        hideBuildLayer();
    }//GEN-LAST:event_buildMarketButtonActionPerformed

    private void buildWallButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buildWallButtonActionPerformed
    {//GEN-HEADEREND:event_buildWallButtonActionPerformed
        selectedBuilding = WALL;
        build();
        hideBuildLayer();
    }//GEN-LAST:event_buildWallButtonActionPerformed

    private void buildResearchButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buildResearchButtonActionPerformed
    {//GEN-HEADEREND:event_buildResearchButtonActionPerformed
        selectedBuilding = RESEARCH;
        build();
        hideBuildLayer();
    }//GEN-LAST:event_buildResearchButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_backButtonActionPerformed
    {//GEN-HEADEREND:event_backButtonActionPerformed
        hideBuildLayer();
        descriptionText.setText("");
    }//GEN-LAST:event_backButtonActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_formMouseClicked
    {//GEN-HEADEREND:event_formMouseClicked
        hideBuildLayer();
        descriptionText.setText("");
    }//GEN-LAST:event_formMouseClicked

    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_finishButtonActionPerformed
    {//GEN-HEADEREND:event_finishButtonActionPerformed
        this.setVisible(false);
        gp.setVisible(true);
    }//GEN-LAST:event_finishButtonActionPerformed

    private void buildBarracksButtonMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_buildBarracksButtonMouseEntered
    {//GEN-HEADEREND:event_buildBarracksButtonMouseEntered
        buildingNameLabel.setText("建造" +  structureDataList[BARRACKS].getName() + ":");
        costLabel.setText("所需白银: " + structureDataList[BARRACKS].getCost() + "两");
    }//GEN-LAST:event_buildBarracksButtonMouseEntered

    private void buildFarmButtonMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_buildFarmButtonMouseEntered
    {//GEN-HEADEREND:event_buildFarmButtonMouseEntered
        buildingNameLabel.setText("建造" +  structureDataList[FARM].getName() + ":");
        costLabel.setText("所需白银: " + structureDataList[FARM].getCost() + "两");
    }//GEN-LAST:event_buildFarmButtonMouseEntered

    private void buildMarketButtonMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_buildMarketButtonMouseEntered
    {//GEN-HEADEREND:event_buildMarketButtonMouseEntered
        buildingNameLabel.setText("建造" +  structureDataList[MARKET].getName() + ":");
        costLabel.setText("所需白银: " + structureDataList[MARKET].getCost() + "两");
    }//GEN-LAST:event_buildMarketButtonMouseEntered

    private void buildWallButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildWallButtonMouseEntered
        buildingNameLabel.setText("建造" +  structureDataList[WALL].getName() + ":");
        costLabel.setText("所需白银: " + structureDataList[WALL].getCost() + "两");
    }//GEN-LAST:event_buildWallButtonMouseEntered

    private void buildResearchButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildResearchButtonMouseEntered
        buildingNameLabel.setText("建造" +  structureDataList[RESEARCH].getName() + ":");
        costLabel.setText("所需白银: " + structureDataList[RESEARCH].getCost() + "两");
    }//GEN-LAST:event_buildResearchButtonMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton buildBarracksButton;
    private javax.swing.JButton buildFarmButton;
    private javax.swing.JLayeredPane buildLayer;
    private javax.swing.JLabel buildLayerTitle;
    private javax.swing.JButton buildMarketButton;
    private javax.swing.JButton buildResearchButton;
    private javax.swing.JButton buildWallButton;
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
    private javax.swing.JLabel soldierDisplayLabel;
    private javax.swing.JLabel soldierIncomeLabel;
    private javax.swing.JLabel summaryDisplayLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
