/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.ArmyType;
import controller.GameController;
import controller.GameParameters;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Character;
import model.City;
import model.Army;
import model.Formation;
import model.Player;

/**
 *
 * @author RuN
 */
public class PrepareBattlePanel extends JPanel implements GameParameters {
    private static final long serialVersionUID = 1L;
    private final Formation formation;
    private final GamePanel gp;
    private final BattlePanel bp;
    private final GameController gc;
    private final Player player;
    private final JButton[] cityButtonList;
    private final int[] equipments;
    
    private City currentCity;
    private Character mainGeneral, frontGeneral, leftWingGeneral, rightWingGeneral;
    private Character armyCommander, armyLieutenant;
    private Army mainArmy, frontArmy,leftWingArmy, rightWingArmy;
    private int remainingSoldiers;
    
    private enum General {COMMANDER, LIEUTENANT}
    private ArmyType selectedArmy;
    private General selectedGeneral;


    
    /**
     * Creates new form Prepare Battle Panel
     * @param gp
     */
    public PrepareBattlePanel(GamePanel gp) {
     
        initComponents();
        
        // Create Battle Panel
        bp = new BattlePanel();
        MainFrame.getInstance().getContentPane().add(bp);
        bp.setSize(800, 600);
        bp.setVisible(false);
        
        // initDropDownLists();
        selectFormationPanel.setVisible(false);
        selectCharacterPanel.setVisible(false);
        selectArmyPanel.setVisible(false);
        
        this.gp = gp;
        mainArmy = null;
        frontArmy = null;
        leftWingArmy = null;
        rightWingArmy = null;
        equipments = new int[4];
        cityButtonList = new JButton[8];
        formation = new Formation(this);
        player = Player.getInstance();
        gc = GameController.getInstance();
        
        initButtons();
        initArray();
    }
    
    private void initArray() {
        Arrays.fill(equipments, 0);
    }
    
    private void initButtons() {
        cityButtonList[0] = north;
        cityButtonList[1] = south;
        cityButtonList[2] = west;
        cityButtonList[3] = east;
        cityButtonList[4] = northWest;
        cityButtonList[5] = northEast;
        cityButtonList[6] = southWest;
        cityButtonList[7] = southEast;
    }
    
    private void initFormationPanel() {
        
        // Sets the layout to a selected Formation.
        formation.initDefaultFormation();
        formationForceLabel.setText(gc.getAttackedCity().getOwner().getForceName() + "势");
        castleLabel.setText(gc.getAttackedCity().getCityName() + "城");
    }
      
    private void updateFormationPanel() {
        updateFormationButtons();
        updateFormationlabels();
    }
    
    private void initArmyPanel() {
        armyCommander = null;
        armyLieutenant = null;
        updateArmyPanel();
    }
    
    private void updateArmyPanel() {
        updateArmyLabels();
        updateSlider();
        updateBox();
    }
    
    private void updateArmyPanel(Army army) {
        armyCommanderName.setText(army.getCommander().getName() + "军");
        armyCommanderButton.setText(army.getCommander().getName());
        armyAttackLabel.setText("攻击：" + army.getCommander().getDmg());
        armyDefenceLabel.setText("防御：" + army.getCommander().getDef());
        armyLieutenantName.setText("副将: " + "");
        armyLieutenantButton.setText("");
    }

    public void init() {
        currentCity = player.getSelectedCity();
        // remainingSoldiers = currentCity.getSoldiers();
        initCity();
        initTable();
    }
    
    private void initTable() {
        Character character;
        for (int i = 0; i < currentCity.getCharacterList().size(); i++) {
            character = player.getSelectedCity().getCharacterList().get(i);
            charTable.setValueAt(character.getName(), i, 0);
            charTable.setValueAt("无", i, 1);
            charTable.setValueAt(character.getCommandPower(), i, 2);
            charTable.setValueAt("无", i, 3);
            charTable.setValueAt(character.getLeadership(), i, 4);
            charTable.setValueAt(character.getCombatPower(), i, 5);
            charTable.setValueAt(character.getIntelligence(), i, 6);
            charTable.setValueAt(character.getPolitics(), i, 7);
            charTable.setValueAt(false, i, 8);
        }
    }
    
    private void initCity() {
        int cityNumber;
        middle.setText(currentCity.getCityName());
        for (int i = 0; i < 8; i++) {
            cityNumber = currentCity.getNeighbour(i + 1);
            if (cityNumber == CITY_EMPTY) {
                cityButtonList[i].setVisible(false);
            } else {
                cityButtonList[i].setText(gc.getCity(cityNumber).getCityName());
                cityButtonList[i].setVisible(true);
            }
        }
    }
    
    private int calcSlider(Character general) {
        int result;
        int commandPower = general.getCommandPower();
        
        if (remainingSoldiers >= commandPower) {
            result = commandPower;
        } else {
            result = remainingSoldiers;
        }
        
        return result;
    }

    private void showFormationPanel() {
        initFormationPanel();
        selectFormationPanel.setVisible(true);
        selectCityPanel.setVisible(false);
    }
    
    private void showNewArmyPanel() {
        initArmyPanel();
        selectArmyPanel.setVisible(true);
        selectFormationPanel.setVisible(false);
    }
    
    private void showEditArmyPanel() {
        if (selectedArmy == ArmyType.PLAYER_FRONT) {
            updateArmyPanel(frontArmy);
        } else if (selectedArmy == ArmyType.PLAYER_MAIN) {
            updateArmyPanel(mainArmy);
        } else if (selectedArmy == ArmyType.PLAYER_LEFTWING) {
            updateArmyPanel(leftWingArmy);
        } else if (selectedArmy == ArmyType.PLAYER_RIGHTWING) {
            updateArmyPanel(rightWingArmy);
        }
        selectArmyPanel.setVisible(true);
        selectFormationPanel.setVisible(false);
    }

    private void selectAttackedCity(int cityNumber) {
        gc.setAttackedCity(cityNumber);
    }
    
    private void updateFormationButtons() {
        if (frontArmy != null) {
            frontArmyButton.setText(frontArmy.getCommander().getName());
        } 
        if (mainArmy != null) {
            mainArmyButton.setText(mainArmy.getCommander().getName());
        } 
        if (leftWingArmy != null) {
            leftWingArmyButton.setText(leftWingArmy.getCommander().getName());
        }
        if (rightWingArmy != null) {
            rightWingArmyButton.setText(rightWingArmy.getCommander().getName());
        }
    }
    
    private void updateFormationlabels() {
        
        // enemy soldier, player soldier and remaining soldier code goes here.
        int front, main, left, right;
        
        if (frontArmy == null) {front = 0;}
        else {front = frontArmy.getSoldiers();}
        
        if (mainArmy == null) {main = 0;}
        else {main = mainArmy.getSoldiers();}
        
        if (leftWingArmy == null) {left = 0;}
        else {left = leftWingArmy.getSoldiers();}
        
        if (rightWingArmy == null) {right = 0;}
        else {right = rightWingArmy.getSoldiers();}
        
        totalLabel.setText(front + main + left + right + "");
        remainingLabel.setText(player.getSelectedCity().getSoldiers() - (front + main + left + right) + "");
        enemyTotalLabel.setText(front + main + left + right + 2000 + "");
    }
    
    private void updateArmyLabels() {     
        if (armyCommander == null) {
            armyCommanderName.setText("无");
            armyCommanderButton.setText("主将");
            armyAttackLabel.setText("攻击：" + 0);
            armyDefenceLabel.setText("防御：" + 0);
        } else {
            armyCommanderName.setText(armyCommander.getName() + "军");
            armyCommanderButton.setText(armyCommander.getName());
            armyAttackLabel.setText("攻击：" + armyCommander.getDmg());
            armyDefenceLabel.setText("防御：" + armyCommander.getDef());
        }
        
        if (armyLieutenant == null) {
            armyLieutenantName.setText("无");
            armyLieutenantButton.setText("副将");
        } else {
            armyLieutenantName.setText("副将: " + armyLieutenant.getName());
            armyLieutenantButton.setText(armyLieutenant.getName());
        }
    }
    
    private void updateSlider() {
        if (armyCommander != null) {
            armySlider.setMaximum(armyCommander.getCommandPower());
            armySlider.setValue(armySlider.getMaximum() / 2);
        } else {
            armySlider.setMaximum(0);
        }
    }
    private void updateBox() {
        // black smith system code goes here.
    }
    
    private void selectGeneral() {
        Character character = player.getSelectedCity().getCharacterList().get(charTable.getSelectedRow());
        if (selectedGeneral == General.COMMANDER) {
            armyCommander = character;
        } else if (selectedGeneral == General.LIEUTENANT) {
            armyLieutenant = character;
        }
    }
    
    private void createArmy() {
        
        // Legion(Commander, Soldiers, UnitType)
        if (selectedArmy == ArmyType.PLAYER_FRONT) {
            if (frontArmy == null) {
                frontGeneral = armyCommander;
                frontArmy = new Army(frontGeneral, armySlider.getValue(), 1);
            }
        } else if (selectedArmy == ArmyType.PLAYER_MAIN) {
            if (mainArmy == null) {
                mainGeneral = armyCommander;
                mainArmy = new Army(mainGeneral, armySlider.getValue(), 1);
            }
        } else if (selectedArmy == ArmyType.PLAYER_LEFTWING) {
            if (leftWingArmy == null) {
                leftWingGeneral = armyCommander;
                leftWingArmy = new Army(leftWingGeneral, armySlider.getValue(), 1);
            }
            
        } else if (selectedArmy == ArmyType.PLAYER_RIGHTWING) {
            if (rightWingArmy == null) {
                rightWingGeneral = armyCommander;
                rightWingArmy = new Army(rightWingGeneral, armySlider.getValue(), 1);
            }
        }
    }
    
    
    // Getters for JComponents.
    public JButton getFrontButton() {
        return frontArmyButton;
    }
    public JButton getMainButton() {
        return mainArmyButton;
    }
    public JButton getLeftButton() {
        return leftWingArmyButton;
    }
    public JButton getRightButton() {
        return rightWingArmyButton;
    }
    public JLabel getFrontLabel() {
        return frontLabel;
    }
    public JLabel getMainLabel() {
        return mainLabel;
    }
    public JLabel getLeftLabel() {
        return leftWingLabel;
    }
    public JLabel getRightLabel() {
        return rightWingLabel;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        selectCityPanel = new javax.swing.JLayeredPane();
        characterTitleLabel1 = new javax.swing.JLabel();
        northWest = new javax.swing.JButton();
        north = new javax.swing.JButton();
        northEast = new javax.swing.JButton();
        west = new javax.swing.JButton();
        east = new javax.swing.JButton();
        southWest = new javax.swing.JButton();
        south = new javax.swing.JButton();
        southEast = new javax.swing.JButton();
        middle = new javax.swing.JButton();
        selectFormationPanel = new javax.swing.JLayeredPane();
        formationTittle = new javax.swing.JLabel();
        formationTotalLabel = new javax.swing.JLabel();
        formationEnemyTotalLabel = new javax.swing.JLabel();
        formationSoldierRemainLabel = new javax.swing.JLabel();
        frontLabel = new javax.swing.JLabel();
        mainLabel = new javax.swing.JLabel();
        leftWingLabel = new javax.swing.JLabel();
        rightWingLabel = new javax.swing.JLabel();
        formationConfirmButton = new javax.swing.JButton();
        formationBackButton = new javax.swing.JButton();
        leftWingArmyButton = new javax.swing.JButton();
        rightWingArmyButton = new javax.swing.JButton();
        mainArmyButton = new javax.swing.JButton();
        frontArmyButton = new javax.swing.JButton();
        castleLabel = new javax.swing.JLabel();
        formationForceLabel = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        remainingLabel = new javax.swing.JLabel();
        enemyTotalLabel = new javax.swing.JLabel();
        selectArmyPanel = new javax.swing.JLayeredPane();
        armyTitle1 = new javax.swing.JLabel();
        armyTitle2 = new javax.swing.JLabel();
        armyTitle3 = new javax.swing.JLabel();
        armyLabel1 = new javax.swing.JLabel();
        armyLabel2 = new javax.swing.JLabel();
        armyCommanderName = new javax.swing.JLabel();
        armyLieutenantName = new javax.swing.JLabel();
        armyAttackLabel = new javax.swing.JLabel();
        armyDefenceLabel = new javax.swing.JLabel();
        armySlider = new javax.swing.JSlider();
        armySoldierText = new javax.swing.JTextField();
        armyBox = new javax.swing.JComboBox();
        armyConfirmButton = new javax.swing.JButton();
        armyBackButton = new javax.swing.JButton();
        armyCommanderButton = new javax.swing.JButton();
        armyLieutenantButton = new javax.swing.JButton();
        swordValue = new javax.swing.JLabel();
        spearValue = new javax.swing.JLabel();
        halberdValue = new javax.swing.JLabel();
        bowValue = new javax.swing.JLabel();
        leatherValue = new javax.swing.JLabel();
        armourValue = new javax.swing.JLabel();
        horseValue = new javax.swing.JLabel();
        chariotValue = new javax.swing.JLabel();
        armyTitle4 = new javax.swing.JLabel();
        armyTitle5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        selectCharacterPanel = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        charTable = new javax.swing.JTable();
        characterTitleLabel = new javax.swing.JLabel();
        characterConfirmButton = new javax.swing.JButton();
        characterBackButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        selectCityPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        selectCityPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        characterTitleLabel1.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        characterTitleLabel1.setText("出兵");
        selectCityPanel.add(characterTitleLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 24, -1, -1));

        northWest.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        northWest.setText("西北");
        northWest.setFocusable(false);
        northWest.setPreferredSize(new java.awt.Dimension(120, 100));
        northWest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                northWestActionPerformed(evt);
            }
        });
        selectCityPanel.add(northWest, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 77, -1, -1));

        north.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        north.setText("北");
        north.setFocusable(false);
        north.setPreferredSize(new java.awt.Dimension(120, 100));
        north.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                northActionPerformed(evt);
            }
        });
        selectCityPanel.add(north, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 77, -1, -1));

        northEast.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        northEast.setText("东北");
        northEast.setFocusable(false);
        northEast.setPreferredSize(new java.awt.Dimension(120, 100));
        northEast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                northEastActionPerformed(evt);
            }
        });
        selectCityPanel.add(northEast, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 77, -1, -1));

        west.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        west.setText("西");
        west.setFocusable(false);
        west.setPreferredSize(new java.awt.Dimension(120, 100));
        west.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                westActionPerformed(evt);
            }
        });
        selectCityPanel.add(west, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 272, -1, -1));

        east.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        east.setText("东");
        east.setFocusable(false);
        east.setPreferredSize(new java.awt.Dimension(120, 100));
        east.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eastActionPerformed(evt);
            }
        });
        selectCityPanel.add(east, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 272, -1, -1));

        southWest.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        southWest.setText("西南");
        southWest.setFocusable(false);
        southWest.setPreferredSize(new java.awt.Dimension(120, 100));
        southWest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                southWestActionPerformed(evt);
            }
        });
        selectCityPanel.add(southWest, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 467, -1, -1));

        south.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        south.setText("南");
        south.setFocusable(false);
        south.setPreferredSize(new java.awt.Dimension(120, 100));
        south.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                southActionPerformed(evt);
            }
        });
        selectCityPanel.add(south, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 467, -1, -1));

        southEast.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        southEast.setText("东南");
        southEast.setFocusable(false);
        southEast.setPreferredSize(new java.awt.Dimension(120, 100));
        southEast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                southEastActionPerformed(evt);
            }
        });
        selectCityPanel.add(southEast, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 467, -1, -1));

        middle.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        middle.setText("中");
        middle.setFocusable(false);
        middle.setPreferredSize(new java.awt.Dimension(120, 100));
        middle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                middleActionPerformed(evt);
            }
        });
        selectCityPanel.add(middle, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 272, -1, -1));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(selectCityPanel, gridBagConstraints);

        selectFormationPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        selectFormationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        formationTittle.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        formationTittle.setText("阵型");
        selectFormationPanel.add(formationTittle, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 24, -1, -1));

        formationTotalLabel.setText("己方总兵力：");
        selectFormationPanel.add(formationTotalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 520, -1, -1));

        formationEnemyTotalLabel.setText("敌方总兵力：");
        selectFormationPanel.add(formationEnemyTotalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, -1, -1));

        formationSoldierRemainLabel.setText("剩余兵力：");
        selectFormationPanel.add(formationSoldierRemainLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 540, -1, -1));

        frontLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        frontLabel.setText("前军");
        selectFormationPanel.add(frontLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 206, -1, -1));

        mainLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        mainLabel.setText("中军");
        selectFormationPanel.add(mainLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 313, -1, -1));

        leftWingLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        leftWingLabel.setText("左军");
        selectFormationPanel.add(leftWingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 313, -1, -1));

        rightWingLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        rightWingLabel.setText("右军");
        selectFormationPanel.add(rightWingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 313, -1, -1));

        formationConfirmButton.setText("确定");
        formationConfirmButton.setFocusable(false);
        formationConfirmButton.setMaximumSize(new java.awt.Dimension(80, 40));
        formationConfirmButton.setMinimumSize(new java.awt.Dimension(80, 40));
        formationConfirmButton.setPreferredSize(new java.awt.Dimension(100, 50));
        formationConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formationConfirmButtonActionPerformed(evt);
            }
        });
        selectFormationPanel.add(formationConfirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 529, -1, -1));

        formationBackButton.setText("取消");
        formationBackButton.setFocusable(false);
        formationBackButton.setMaximumSize(new java.awt.Dimension(80, 40));
        formationBackButton.setMinimumSize(new java.awt.Dimension(80, 40));
        formationBackButton.setPreferredSize(new java.awt.Dimension(100, 50));
        formationBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formationBackButtonActionPerformed(evt);
            }
        });
        selectFormationPanel.add(formationBackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 529, -1, -1));

        leftWingArmyButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 15)); // NOI18N
        leftWingArmyButton.setFocusable(false);
        leftWingArmyButton.setMaximumSize(new java.awt.Dimension(75, 100));
        leftWingArmyButton.setMinimumSize(new java.awt.Dimension(75, 100));
        leftWingArmyButton.setPreferredSize(new java.awt.Dimension(75, 100));
        leftWingArmyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftWingArmyButtonActionPerformed(evt);
            }
        });
        selectFormationPanel.add(leftWingArmyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 355, -1, -1));

        rightWingArmyButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 15)); // NOI18N
        rightWingArmyButton.setFocusable(false);
        rightWingArmyButton.setMaximumSize(new java.awt.Dimension(75, 100));
        rightWingArmyButton.setMinimumSize(new java.awt.Dimension(75, 100));
        rightWingArmyButton.setPreferredSize(new java.awt.Dimension(75, 100));
        rightWingArmyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightWingArmyButtonActionPerformed(evt);
            }
        });
        selectFormationPanel.add(rightWingArmyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(504, 355, -1, -1));

        mainArmyButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 15)); // NOI18N
        mainArmyButton.setFocusable(false);
        mainArmyButton.setMaximumSize(new java.awt.Dimension(100, 100));
        mainArmyButton.setMinimumSize(new java.awt.Dimension(100, 100));
        mainArmyButton.setPreferredSize(new java.awt.Dimension(100, 100));
        mainArmyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainArmyButtonActionPerformed(evt);
            }
        });
        selectFormationPanel.add(mainArmyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 355, -1, -1));

        frontArmyButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 15)); // NOI18N
        frontArmyButton.setFocusable(false);
        frontArmyButton.setMaximumSize(new java.awt.Dimension(100, 40));
        frontArmyButton.setMinimumSize(new java.awt.Dimension(100, 40));
        frontArmyButton.setPreferredSize(new java.awt.Dimension(100, 40));
        frontArmyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frontArmyButtonActionPerformed(evt);
            }
        });
        selectFormationPanel.add(frontArmyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 243, -1, -1));

        castleLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 16)); // NOI18N
        castleLabel.setText("北平城");
        selectFormationPanel.add(castleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 90, 75, -1));

        formationForceLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 16)); // NOI18N
        formationForceLabel.setText("公孙瓒势");
        selectFormationPanel.add(formationForceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 80, -1));

        totalLabel.setText("0");
        selectFormationPanel.add(totalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 520, 80, -1));

        remainingLabel.setText("0");
        selectFormationPanel.add(remainingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 540, 80, -1));

        enemyTotalLabel.setText("0");
        selectFormationPanel.add(enemyTotalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, 80, -1));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(selectFormationPanel, gridBagConstraints);

        armyTitle1.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        armyTitle1.setText("编辑军队");

        armyTitle2.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        armyTitle2.setText("军队概要");

        armyTitle3.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        armyTitle3.setText("兵装概要");

        armyLabel1.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        armyLabel1.setText("兵科:");

        armyLabel2.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        armyLabel2.setText("兵力:");

        armyCommanderName.setFont(new java.awt.Font("Microsoft YaHei", 1, 13)); // NOI18N
        armyCommanderName.setText("曹操军");

        armyLieutenantName.setFont(new java.awt.Font("Microsoft YaHei", 1, 13)); // NOI18N
        armyLieutenantName.setText("副将: 曹仁");

        armyAttackLabel.setText("攻击: 100");

        armyDefenceLabel.setText("防御: 100");

        armySlider.setMajorTickSpacing(100);
        armySlider.setMaximum(10000);
        armySlider.setMinorTickSpacing(1);

        armySoldierText.setEditable(false);
        armySoldierText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        armySoldierText.setMaximumSize(new java.awt.Dimension(60, 22));
        armySoldierText.setMinimumSize(new java.awt.Dimension(60, 22));
        armySoldierText.setPreferredSize(new java.awt.Dimension(60, 22));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, armySlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), armySoldierText, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        armyBox.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        armyBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "剑", "枪", "弓", "骑" }));

        armyConfirmButton.setText("确定");
        armyConfirmButton.setFocusable(false);
        armyConfirmButton.setPreferredSize(new java.awt.Dimension(100, 50));
        armyConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                armyConfirmButtonActionPerformed(evt);
            }
        });

        armyBackButton.setText("取消");
        armyBackButton.setFocusable(false);
        armyBackButton.setPreferredSize(new java.awt.Dimension(100, 50));
        armyBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                armyBackButtonActionPerformed(evt);
            }
        });

        armyCommanderButton.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        armyCommanderButton.setText("主将");
        armyCommanderButton.setPreferredSize(new java.awt.Dimension(125, 100));
        armyCommanderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                armyCommanderButtonActionPerformed(evt);
            }
        });

        armyLieutenantButton.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        armyLieutenantButton.setText("副将");
        armyLieutenantButton.setPreferredSize(new java.awt.Dimension(100, 75));
        armyLieutenantButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                armyLieutenantButtonActionPerformed(evt);
            }
        });

        swordValue.setText("剑: 0");

        spearValue.setText("枪: 0");

        halberdValue.setText("戟: 0");

        bowValue.setText("弓: 0");

        leatherValue.setText("皮: 0");

        armourValue.setText("甲: 0");

        horseValue.setText("马: 0");

        chariotValue.setText("车: 0");

        armyTitle4.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        armyTitle4.setText("配置士兵");

        armyTitle5.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        armyTitle5.setText("配置武将");

        jLabel1.setText("消耗粮草： 10000");

        javax.swing.GroupLayout selectArmyPanelLayout = new javax.swing.GroupLayout(selectArmyPanel);
        selectArmyPanel.setLayout(selectArmyPanelLayout);
        selectArmyPanelLayout.setHorizontalGroup(
            selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectArmyPanelLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selectArmyPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(armyTitle5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(armyTitle4)
                        .addGap(138, 138, 138))
                    .addGroup(selectArmyPanelLayout.createSequentialGroup()
                        .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(armyCommanderButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(armyLieutenantButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectArmyPanelLayout.createSequentialGroup()
                .addContainerGap(276, Short.MAX_VALUE)
                .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectArmyPanelLayout.createSequentialGroup()
                        .addComponent(armyConfirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(armyBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(274, 274, 274))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectArmyPanelLayout.createSequentialGroup()
                        .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(armyLieutenantName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(armyCommanderName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(armyDefenceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(armyAttackLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                            .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(swordValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(spearValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(halberdValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(bowValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(leatherValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(armourValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(horseValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(chariotValue, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                            .addGap(22, 22, 22)
                                            .addComponent(armyTitle3))))
                                .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                    .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                            .addComponent(armyTitle1)
                                            .addGap(136, 136, 136))
                                        .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                            .addComponent(armyTitle2)
                                            .addGap(164, 164, 164)))
                                    .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                            .addComponent(armyLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(armyBox, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(armySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                            .addComponent(armyLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(armySoldierText, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(78, 78, 78))))
        );
        selectArmyPanelLayout.setVerticalGroup(
            selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectArmyPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(armyTitle1)
                .addGap(51, 51, 51)
                .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(armyTitle5)
                    .addComponent(armyTitle4))
                .addGap(18, 18, 18)
                .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selectArmyPanelLayout.createSequentialGroup()
                        .addComponent(armyCommanderButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(armyLieutenantButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(armyConfirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(armyBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(selectArmyPanelLayout.createSequentialGroup()
                        .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                .addComponent(armyTitle2)
                                .addGap(18, 18, 18)
                                .addComponent(armyCommanderName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(armyLieutenantName)
                                .addGap(18, 18, 18)
                                .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                        .addComponent(armyAttackLabel)
                                        .addGap(26, 26, 26))
                                    .addComponent(armyDefenceLabel)))
                            .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(armyLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(armyBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(armyLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(armySoldierText, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(armySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1))
                            .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(armyTitle3)
                                .addGap(18, 18, 18)
                                .addGroup(selectArmyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                        .addComponent(leatherValue)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(armourValue)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(horseValue)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chariotValue))
                                    .addGroup(selectArmyPanelLayout.createSequentialGroup()
                                        .addComponent(swordValue)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spearValue)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(halberdValue)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bowValue)))))
                        .addContainerGap(157, Short.MAX_VALUE))))
        );
        selectArmyPanel.setLayer(armyTitle1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyTitle2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyTitle3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyCommanderName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyLieutenantName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyAttackLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyDefenceLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armySlider, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armySoldierText, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyConfirmButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyBackButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyCommanderButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyLieutenantButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(swordValue, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(spearValue, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(halberdValue, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(bowValue, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(leatherValue, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armourValue, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(horseValue, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(chariotValue, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyTitle4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(armyTitle5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectArmyPanel.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(selectArmyPanel, gridBagConstraints);

        charTable.setFont(new java.awt.Font("Microsoft YaHei", 0, 14)); // NOI18N
        charTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "武将", "官职", "指挥兵力", "技能", "统率", "武力", "智力", "政治", "已出战"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        charTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        charTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        charTable.setMaximumSize(new java.awt.Dimension(450, 1600));
        charTable.setMinimumSize(new java.awt.Dimension(450, 1600));
        charTable.getTableHeader().setReorderingAllowed(false);
        charTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                charTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(charTable);
        if (charTable.getColumnModel().getColumnCount() > 0) {
            charTable.getColumnModel().getColumn(0).setResizable(false);
            charTable.getColumnModel().getColumn(0).setPreferredWidth(75);
            charTable.getColumnModel().getColumn(1).setResizable(false);
            charTable.getColumnModel().getColumn(1).setPreferredWidth(75);
            charTable.getColumnModel().getColumn(2).setResizable(false);
            charTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            charTable.getColumnModel().getColumn(3).setResizable(false);
            charTable.getColumnModel().getColumn(4).setResizable(false);
            charTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            charTable.getColumnModel().getColumn(5).setResizable(false);
            charTable.getColumnModel().getColumn(5).setPreferredWidth(50);
            charTable.getColumnModel().getColumn(6).setResizable(false);
            charTable.getColumnModel().getColumn(6).setPreferredWidth(50);
            charTable.getColumnModel().getColumn(7).setResizable(false);
            charTable.getColumnModel().getColumn(7).setPreferredWidth(50);
            charTable.getColumnModel().getColumn(8).setResizable(false);
            charTable.getColumnModel().getColumn(8).setPreferredWidth(55);
        }

        characterTitleLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        characterTitleLabel.setText("选择武将");

        characterConfirmButton.setText("确定");
        characterConfirmButton.setFocusable(false);
        characterConfirmButton.setPreferredSize(new java.awt.Dimension(100, 50));
        characterConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                characterConfirmButtonActionPerformed(evt);
            }
        });

        characterBackButton.setText("返回");
        characterBackButton.setFocusable(false);
        characterBackButton.setPreferredSize(new java.awt.Dimension(100, 50));
        characterBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                characterBackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout selectCharacterPanelLayout = new javax.swing.GroupLayout(selectCharacterPanel);
        selectCharacterPanel.setLayout(selectCharacterPanelLayout);
        selectCharacterPanelLayout.setHorizontalGroup(
            selectCharacterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectCharacterPanelLayout.createSequentialGroup()
                .addGroup(selectCharacterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selectCharacterPanelLayout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(characterConfirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(characterBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(selectCharacterPanelLayout.createSequentialGroup()
                        .addGap(364, 364, 364)
                        .addComponent(characterTitleLabel))
                    .addGroup(selectCharacterPanelLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        selectCharacterPanelLayout.setVerticalGroup(
            selectCharacterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectCharacterPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(characterTitleLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(selectCharacterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(characterConfirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(characterBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        selectCharacterPanel.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCharacterPanel.setLayer(characterTitleLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCharacterPanel.setLayer(characterConfirmButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCharacterPanel.setLayer(characterBackButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(selectCharacterPanel, gridBagConstraints);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void formationBackButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_formationBackButtonActionPerformed
    {//GEN-HEADEREND:event_formationBackButtonActionPerformed
        selectedArmy = null;
        selectCityPanel.setVisible(true);
        selectFormationPanel.setVisible(false);
    }//GEN-LAST:event_formationBackButtonActionPerformed

    private void leftWingArmyButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_leftWingArmyButtonActionPerformed
    {//GEN-HEADEREND:event_leftWingArmyButtonActionPerformed
        selectedArmy = ArmyType.PLAYER_LEFTWING;
        if (leftWingArmy == null) {
            showNewArmyPanel();
        } else {
            showEditArmyPanel();
        }
    }//GEN-LAST:event_leftWingArmyButtonActionPerformed

    private void rightWingArmyButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_rightWingArmyButtonActionPerformed
    {//GEN-HEADEREND:event_rightWingArmyButtonActionPerformed
        selectedArmy = ArmyType.PLAYER_RIGHTWING;
        if (rightWingArmy == null) {
            showNewArmyPanel();
        } else {
            showEditArmyPanel();
        }    
    }//GEN-LAST:event_rightWingArmyButtonActionPerformed

    private void mainArmyButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mainArmyButtonActionPerformed
    {//GEN-HEADEREND:event_mainArmyButtonActionPerformed
        selectedArmy = ArmyType.PLAYER_MAIN;
        if (mainArmy== null) {
            showNewArmyPanel();
        } else {
            showEditArmyPanel();
        }  
    }//GEN-LAST:event_mainArmyButtonActionPerformed

    private void frontArmyButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_frontArmyButtonActionPerformed
    {//GEN-HEADEREND:event_frontArmyButtonActionPerformed
        selectedArmy = ArmyType.PLAYER_FRONT;
        if (frontArmy == null) {
            showNewArmyPanel();
        } else {
            showEditArmyPanel();
        }        
    }//GEN-LAST:event_frontArmyButtonActionPerformed

    private void characterConfirmButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_characterConfirmButtonActionPerformed
    {//GEN-HEADEREND:event_characterConfirmButtonActionPerformed
        if ((boolean)charTable.getValueAt(charTable.getSelectedRow(), 8) == true) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "该武将已经出战！", "选择错误", JOptionPane.PLAIN_MESSAGE);
        } else {
            selectGeneral();
            updateArmyPanel();
            selectArmyPanel.setVisible(true);
            selectCharacterPanel.setVisible(false);
        }
    }//GEN-LAST:event_characterConfirmButtonActionPerformed

    private void characterBackButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_characterBackButtonActionPerformed
    {//GEN-HEADEREND:event_characterBackButtonActionPerformed
        selectArmyPanel.setVisible(true);
        selectCharacterPanel.setVisible(false);
    }//GEN-LAST:event_characterBackButtonActionPerformed

    private void formationConfirmButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_formationConfirmButtonActionPerformed
    {//GEN-HEADEREND:event_formationConfirmButtonActionPerformed
        this.setVisible(false);
        bp.initBattle(frontArmy, mainArmy, leftWingArmy, rightWingArmy);
        bp.setVisible(true);
    }//GEN-LAST:event_formationConfirmButtonActionPerformed

    private void charTableMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_charTableMouseClicked
    {//GEN-HEADEREND:event_charTableMouseClicked
        if (charTable.getSelectedRow() >= player.getSelectedCity().getNumCharacters()) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "请选择有效武将！", "选择错误", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_charTableMouseClicked

    private void armyConfirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_armyConfirmButtonActionPerformed
        createArmy();
        updateFormationPanel();
        selectFormationPanel.setVisible(true);
        selectArmyPanel.setVisible(false);
    }//GEN-LAST:event_armyConfirmButtonActionPerformed

    private void armyBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_armyBackButtonActionPerformed
        selectFormationPanel.setVisible(true);
        selectArmyPanel.setVisible(false);
    }//GEN-LAST:event_armyBackButtonActionPerformed

    private void middleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_middleActionPerformed
        this.setVisible(false);
        gp.setVisible(true);
    }//GEN-LAST:event_middleActionPerformed

    private void southEastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_southEastActionPerformed
        selectAttackedCity(currentCity.getNeighbour(DIRECTION_SOUTHEAST));
        showFormationPanel();
    }//GEN-LAST:event_southEastActionPerformed

    private void southActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_southActionPerformed
        selectAttackedCity(currentCity.getNeighbour(DIRECTION_SOUTH));
        showFormationPanel();
    }//GEN-LAST:event_southActionPerformed

    private void southWestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_southWestActionPerformed
        selectAttackedCity(currentCity.getNeighbour(DIRECTION_SOUTHWEST));
        showFormationPanel();
    }//GEN-LAST:event_southWestActionPerformed

    private void eastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eastActionPerformed
        selectAttackedCity(currentCity.getNeighbour(DIRECTION_EAST));
        showFormationPanel();
    }//GEN-LAST:event_eastActionPerformed

    private void westActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_westActionPerformed
        selectAttackedCity(currentCity.getNeighbour(DIRECTION_WEST));
        showFormationPanel();
    }//GEN-LAST:event_westActionPerformed

    private void northEastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_northEastActionPerformed
        selectAttackedCity(currentCity.getNeighbour(DIRECTION_NORTHEAST));
        showFormationPanel();
    }//GEN-LAST:event_northEastActionPerformed

    private void northActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_northActionPerformed
        selectAttackedCity(currentCity.getNeighbour(DIRECTION_NORTH));
        showFormationPanel();
    }//GEN-LAST:event_northActionPerformed

    private void northWestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_northWestActionPerformed
        selectAttackedCity(currentCity.getNeighbour(DIRECTION_NORTHWEST));
        showFormationPanel();
    }//GEN-LAST:event_northWestActionPerformed

    private void armyCommanderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_armyCommanderButtonActionPerformed
       selectedGeneral = General.COMMANDER;
       selectCharacterPanel.setVisible(true);
       selectArmyPanel.setVisible(false);
    }//GEN-LAST:event_armyCommanderButtonActionPerformed

    private void armyLieutenantButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_armyLieutenantButtonActionPerformed
        selectedGeneral = General.LIEUTENANT;
        selectCharacterPanel.setVisible(true);
        selectArmyPanel.setVisible(false);
    }//GEN-LAST:event_armyLieutenantButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel armourValue;
    private javax.swing.JLabel armyAttackLabel;
    private javax.swing.JButton armyBackButton;
    private javax.swing.JComboBox armyBox;
    private javax.swing.JButton armyCommanderButton;
    private javax.swing.JLabel armyCommanderName;
    private javax.swing.JButton armyConfirmButton;
    private javax.swing.JLabel armyDefenceLabel;
    private javax.swing.JLabel armyLabel1;
    private javax.swing.JLabel armyLabel2;
    private javax.swing.JButton armyLieutenantButton;
    private javax.swing.JLabel armyLieutenantName;
    private javax.swing.JSlider armySlider;
    private javax.swing.JTextField armySoldierText;
    private javax.swing.JLabel armyTitle1;
    private javax.swing.JLabel armyTitle2;
    private javax.swing.JLabel armyTitle3;
    private javax.swing.JLabel armyTitle4;
    private javax.swing.JLabel armyTitle5;
    private javax.swing.JLabel bowValue;
    private javax.swing.JLabel castleLabel;
    private javax.swing.JTable charTable;
    private javax.swing.JButton characterBackButton;
    private javax.swing.JButton characterConfirmButton;
    private javax.swing.JLabel characterTitleLabel;
    private javax.swing.JLabel characterTitleLabel1;
    private javax.swing.JLabel chariotValue;
    private javax.swing.JButton east;
    private javax.swing.JLabel enemyTotalLabel;
    private javax.swing.JButton formationBackButton;
    private javax.swing.JButton formationConfirmButton;
    private javax.swing.JLabel formationEnemyTotalLabel;
    private javax.swing.JLabel formationForceLabel;
    private javax.swing.JLabel formationSoldierRemainLabel;
    private javax.swing.JLabel formationTittle;
    private javax.swing.JLabel formationTotalLabel;
    private javax.swing.JButton frontArmyButton;
    private javax.swing.JLabel frontLabel;
    private javax.swing.JLabel halberdValue;
    private javax.swing.JLabel horseValue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel leatherValue;
    private javax.swing.JButton leftWingArmyButton;
    private javax.swing.JLabel leftWingLabel;
    private javax.swing.JButton mainArmyButton;
    private javax.swing.JLabel mainLabel;
    private javax.swing.JButton middle;
    private javax.swing.JButton north;
    private javax.swing.JButton northEast;
    private javax.swing.JButton northWest;
    private javax.swing.JLabel remainingLabel;
    private javax.swing.JButton rightWingArmyButton;
    private javax.swing.JLabel rightWingLabel;
    private javax.swing.JLayeredPane selectArmyPanel;
    private javax.swing.JLayeredPane selectCharacterPanel;
    private javax.swing.JLayeredPane selectCityPanel;
    private javax.swing.JLayeredPane selectFormationPanel;
    private javax.swing.JButton south;
    private javax.swing.JButton southEast;
    private javax.swing.JButton southWest;
    private javax.swing.JLabel spearValue;
    private javax.swing.JLabel swordValue;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JButton west;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
