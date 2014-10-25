/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.GameController;
import controller.GameParameters;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Character;
import model.City;
import model.Legion;

/**
 *
 * @author RuN
 */
public class PrepareBattlePanel extends JPanel implements GameParameters
{
    private static final long serialVersionUID = 1L;
    private final GamePanel gp;
    private final MainFrame frame;
    private final int NONE = 0, NORTH = 1, SOUTH = 2, WEST = 3, EAST = 4;
    private int gate;
    private City city;
    private City attackedCity;
    private Character general, northGeneral, southGeneral, westGeneral, eastGeneral;
    private Legion northGateLegion, southGateLegion, westGateLegion, eastGateLegion;
    private String gateName;
    private String charName;
    private final int[] soldiers;
    private int remainingSoldiers;
    private final GameController gc;
    private final JButton[] cityButtonList;
    
    /**
     * Creates new form CombatPanel
     * @param frame
     * @param gp
     * @param controller
     */
    public PrepareBattlePanel(MainFrame frame, GamePanel gp, GameController controller) {
        super();
        this.setSize(800, 600);
        this.gp = gp;
        this.frame = frame;
        this.gc = controller;
        
        soldiers = new int[4];
        Arrays.fill(soldiers, 0);
        cityButtonList = new JButton[8];
        
        initComponents();
        
        cityButtonList[0] = north;
        cityButtonList[1] = south;
        cityButtonList[2] = west;
        cityButtonList[3] = east;
        cityButtonList[4] = northWest;
        cityButtonList[5] = northEast;
        cityButtonList[6] = southWest;
        cityButtonList[7] = southEast;
        
        // initDropDownLists();
        
        dispatchTroopsPanel.setVisible(false);
        selectCharacterPanel.setVisible(false);
        jLayeredPane1.setVisible(false);
    }
    
    public void updateData() {
        city = gc.getCity();
        remainingSoldiers = gc.getSoldiers();
        initCity();
        initTable();
    }
    
    private void initTable()
    {        
        int i;
        for (i = 0; i < city.getCharacterList().size(); i++)
        {
            charTable.setValueAt(city.getCharacterList().get(i).getName(), i, 0);
            charTable.setValueAt(city.getCharacterList().get(i).calcCommandPower(), i, 1);
            charTable.setValueAt(city.getCharacterList().get(i).getLeadership(), i, 2);
            charTable.setValueAt(city.getCharacterList().get(i).getCombatPower(), i, 3);
            charTable.setValueAt(city.getCharacterList().get(i).getIntelligence(), i, 4);
            charTable.setValueAt(city.getCharacterList().get(i).getPolitics(), i, 5);
        }
    }
    
    private void initCity()
    {
        int cityNumber;
        middle.setText(city.getCityName());
        for (int i = 0; i < 8; i++) {
            cityNumber = city.getNeighbour(i + 1);
            if (cityNumber == CITY_EMPTY) {
                cityButtonList[i].setVisible(false);
            } else {
                cityButtonList[i].setText(gc.getCity(cityNumber).getCityName());
                cityButtonList[i].setVisible(true);
            }
        }
    }
    
    private void calcRemainingSoldiers()
    {
        soldiers[0] = northSlider.getValue();
        soldiers[1] = southSlider.getValue();
        soldiers[2] = westSlider.getValue();
        soldiers[3] = eastSlider.getValue();
        
        remainingSoldiers = city.getSoldiers() - soldiers[0] - soldiers[1] - soldiers[2] - soldiers[3];
    }
    
    private int calcSlider(Character general)
    {
        int result;
        int commandPower = general.calcCommandPower();
        
        if (remainingSoldiers >= commandPower) {
            result = commandPower;
        } else {
            result = remainingSoldiers;
        }
        
        return result;
    }
    
    private void dispatchTroops()
    {
        if (gate == NORTH)
        {
            if (isDispatched()) {
                JOptionPane.showMessageDialog(frame, charName + "已在" + gateName +"出战！", "武将分配错误", JOptionPane.PLAIN_MESSAGE);
            } else
            {
                northGeneral = city.getCharacterList().get(charTable.getSelectedRow());
                northGateButton.setText(northGeneral.getName());
                northSlider.setMaximum(calcSlider(northGeneral));
                northSlider.setValue(northSlider.getMaximum());
            }
        }
        else if (gate == SOUTH)
        {
            if (isDispatched()) {
                JOptionPane.showMessageDialog(frame, charName + "已在" + gateName +"出战！", "武将分配错误", JOptionPane.PLAIN_MESSAGE);
            } else
            {
                southGeneral = city.getCharacterList().get(charTable.getSelectedRow());
                southGateButton.setText(southGeneral.getName());
                southSlider.setMaximum(calcSlider(southGeneral));
                southSlider.setValue(southSlider.getMaximum());
            }
        }
        else if (gate == WEST)
        {
            if (isDispatched()) {
                JOptionPane.showMessageDialog(frame, charName + "已在" + gateName +"出战！", "武将分配错误", JOptionPane.PLAIN_MESSAGE);
            } else
            {
                westGeneral = city.getCharacterList().get(charTable.getSelectedRow());
                westGateButton.setText(westGeneral.getName());
                westSlider.setMaximum(westGeneral.calcCommandPower());
                westSlider.setValue(westSlider.getMaximum());
            }
        }
        else if (gate == EAST)
        {
            if (isDispatched()) {
                JOptionPane.showMessageDialog(frame, charName + "已在" + gateName +"出战！", "武将分配错误", JOptionPane.PLAIN_MESSAGE);
            } else
            {
                eastGeneral = city.getCharacterList().get(charTable.getSelectedRow());
                eastGateButton.setText(eastGeneral.getName());
                eastSlider.setMaximum(eastGeneral.calcCommandPower());
                eastSlider.setValue(eastSlider.getMaximum());
            }
        }
    }
    
    
    // Sets the currentCity and attackingCity.
    // Imports selected city and name of the attackingCity.
    // Returns nothing.
    private void setAttackedCity(int attackedCityNumber)
    {
        attackedCity = gc.getCity(attackedCityNumber);
        selectCityPanel.setVisible(false);
        castleLabel.setText(attackedCity.getCityName() + "城");
        dispatchTroopsPanel.setVisible(true);
    }

    private boolean isDispatched()
    {
        boolean isDispatched = false;
        
        if (northGateButton.getText().equals(charName) || southGateButton.getText().equals(charName) ||
           eastGateButton.getText().equals(charName) || westGateButton.getText().equals(charName))
        {
            isDispatched = true;
            if (northGateButton.getText().equals(charName)) {
                gateName = "北门";
            } else if (southGateButton.getText().equals(charName)) {
                gateName = "南门";
            } else if (westGateButton.getText().equals(charName)) {
                gateName = "西门";
            } else if (eastGateButton.getText().equals(charName)) {
                gateName = "东门";
            }
        }
           
        return isDispatched;
    }
    
    private void createBattlePanel()
    {
        BattlePanel bp = new BattlePanel(gp, city, attackedCity, northGateLegion, southGateLegion, westGateLegion, eastGateLegion);
        frame.getContentPane().add(bp);
    }
    
        /**
        * This method is called from within the constructor to initialize the form.
        * WARNING: Do NOT modify this code. The content of this method is always
        * regenerated by the Form Editor.
        */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLayeredPane1 = new javax.swing.JLayeredPane();
        selectCityPanel = new javax.swing.JLayeredPane();
        northWest = new javax.swing.JButton();
        north = new javax.swing.JButton();
        northEast = new javax.swing.JButton();
        west = new javax.swing.JButton();
        east = new javax.swing.JButton();
        southWest = new javax.swing.JButton();
        south = new javax.swing.JButton();
        southEast = new javax.swing.JButton();
        middle = new javax.swing.JButton();
        selectCharacterPanel = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        charTable = new javax.swing.JTable();
        confirmButton = new javax.swing.JButton();
        tableBackButton = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        attackLabel = new javax.swing.JLabel();
        defenceLabel = new javax.swing.JLabel();
        dispatchTroopsPanel = new javax.swing.JLayeredPane();
        startButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        northGateButton = new javax.swing.JButton();
        southGateButton = new javax.swing.JButton();
        westGateButton = new javax.swing.JButton();
        eastGateButton = new javax.swing.JButton();
        castleLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        northSlider = new javax.swing.JSlider();
        northSoldiersLabel = new javax.swing.JTextField();
        bingli1 = new javax.swing.JLabel();
        southSlider = new javax.swing.JSlider();
        bingli3 = new javax.swing.JLabel();
        southSoldiersLabel = new javax.swing.JTextField();
        westSlider = new javax.swing.JSlider();
        bingli2 = new javax.swing.JLabel();
        westSoldiersLabel = new javax.swing.JTextField();
        eastSlider = new javax.swing.JSlider();
        bingli4 = new javax.swing.JLabel();
        eastSoldiersLabel = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        remainingLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new java.awt.GridBagLayout());

        jLayeredPane1.setMaximumSize(new java.awt.Dimension(800, 600));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(800, 600));
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jLayeredPane1, gridBagConstraints);

        selectCityPanel.setMaximumSize(new java.awt.Dimension(800, 600));
        selectCityPanel.setMinimumSize(new java.awt.Dimension(800, 600));

        northWest.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        northWest.setText("西北");
        northWest.setFocusable(false);
        northWest.setPreferredSize(new java.awt.Dimension(120, 100));
        northWest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                northWestActionPerformed(evt);
            }
        });

        north.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        north.setText("北");
        north.setFocusable(false);
        north.setPreferredSize(new java.awt.Dimension(120, 100));
        north.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                northActionPerformed(evt);
            }
        });

        northEast.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        northEast.setText("东北");
        northEast.setFocusable(false);
        northEast.setPreferredSize(new java.awt.Dimension(120, 100));
        northEast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                northEastActionPerformed(evt);
            }
        });

        west.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        west.setText("西");
        west.setFocusable(false);
        west.setPreferredSize(new java.awt.Dimension(120, 100));
        west.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                westActionPerformed(evt);
            }
        });

        east.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        east.setText("东");
        east.setFocusable(false);
        east.setPreferredSize(new java.awt.Dimension(120, 100));
        east.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eastActionPerformed(evt);
            }
        });

        southWest.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        southWest.setText("西南");
        southWest.setFocusable(false);
        southWest.setPreferredSize(new java.awt.Dimension(120, 100));
        southWest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                southWestActionPerformed(evt);
            }
        });

        south.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        south.setText("南");
        south.setFocusable(false);
        south.setPreferredSize(new java.awt.Dimension(120, 100));
        south.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                southActionPerformed(evt);
            }
        });

        southEast.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        southEast.setText("东南");
        southEast.setFocusable(false);
        southEast.setPreferredSize(new java.awt.Dimension(120, 100));
        southEast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                southEastActionPerformed(evt);
            }
        });

        middle.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        middle.setText("中");
        middle.setFocusable(false);
        middle.setPreferredSize(new java.awt.Dimension(120, 100));
        middle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                middleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout selectCityPanelLayout = new javax.swing.GroupLayout(selectCityPanel);
        selectCityPanel.setLayout(selectCityPanelLayout);
        selectCityPanelLayout.setHorizontalGroup(
            selectCityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectCityPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(selectCityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(west, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(southWest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(northWest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(165, 165, 165)
                .addGroup(selectCityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(north, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(south, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(middle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(165, 165, 165)
                .addGroup(selectCityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(northEast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(southEast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(east, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        selectCityPanelLayout.setVerticalGroup(
            selectCityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectCityPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(selectCityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(northWest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(north, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(northEast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95)
                .addGroup(selectCityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(west, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(east, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(middle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95)
                .addGroup(selectCityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(southWest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(south, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(southEast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        selectCityPanel.setLayer(northWest, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCityPanel.setLayer(north, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCityPanel.setLayer(northEast, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCityPanel.setLayer(west, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCityPanel.setLayer(east, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCityPanel.setLayer(southWest, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCityPanel.setLayer(south, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCityPanel.setLayer(southEast, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCityPanel.setLayer(middle, javax.swing.JLayeredPane.DEFAULT_LAYER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(selectCityPanel, gridBagConstraints);

        selectCharacterPanel.setMaximumSize(new java.awt.Dimension(800, 600));
        selectCharacterPanel.setMinimumSize(new java.awt.Dimension(800, 600));

        charTable.setFont(new java.awt.Font("Microsoft YaHei", 0, 14)); // NOI18N
        charTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "武将", "指挥兵力", "统", "武", "智", "政"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        charTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                charTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(charTable);

        confirmButton.setText("确定");
        confirmButton.setFocusable(false);
        confirmButton.setPreferredSize(new java.awt.Dimension(100, 50));
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        tableBackButton.setText("返回");
        tableBackButton.setFocusable(false);
        tableBackButton.setPreferredSize(new java.awt.Dimension(100, 50));
        tableBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableBackButtonActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        title.setText("请选择武将");

        jLabel10.setText("portrait");

        nameLabel.setText("曹操");

        attackLabel.setText("攻击：100");

        defenceLabel.setText("防御：100");

        javax.swing.GroupLayout selectCharacterPanelLayout = new javax.swing.GroupLayout(selectCharacterPanel);
        selectCharacterPanel.setLayout(selectCharacterPanelLayout);
        selectCharacterPanelLayout.setHorizontalGroup(
            selectCharacterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectCharacterPanelLayout.createSequentialGroup()
                .addGroup(selectCharacterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(selectCharacterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(selectCharacterPanelLayout.createSequentialGroup()
                            .addGap(275, 275, 275)
                            .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31)
                            .addComponent(tableBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(selectCharacterPanelLayout.createSequentialGroup()
                            .addGap(332, 332, 332)
                            .addComponent(title))))
                .addGap(32, 32, 32)
                .addGroup(selectCharacterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(selectCharacterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(defenceLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addComponent(attackLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        selectCharacterPanelLayout.setVerticalGroup(
            selectCharacterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectCharacterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addGroup(selectCharacterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selectCharacterPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(selectCharacterPanelLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(nameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(attackLabel)
                        .addGap(18, 18, 18)
                        .addComponent(defenceLabel)))
                .addGap(32, 32, 32)
                .addGroup(selectCharacterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tableBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        selectCharacterPanel.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCharacterPanel.setLayer(confirmButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCharacterPanel.setLayer(tableBackButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCharacterPanel.setLayer(title, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCharacterPanel.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCharacterPanel.setLayer(nameLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCharacterPanel.setLayer(attackLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        selectCharacterPanel.setLayer(defenceLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(selectCharacterPanel, gridBagConstraints);

        dispatchTroopsPanel.setMaximumSize(new java.awt.Dimension(800, 600));
        dispatchTroopsPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        dispatchTroopsPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        startButton.setText("Start");
        startButton.setFocusable(false);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.setFocusable(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        northGateButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 15)); // NOI18N
        northGateButton.setFocusable(false);
        northGateButton.setPreferredSize(new java.awt.Dimension(80, 50));
        northGateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                northGateButtonActionPerformed(evt);
            }
        });

        southGateButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 15)); // NOI18N
        southGateButton.setFocusable(false);
        southGateButton.setPreferredSize(new java.awt.Dimension(80, 50));
        southGateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                southGateButtonActionPerformed(evt);
            }
        });

        westGateButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 15)); // NOI18N
        westGateButton.setFocusable(false);
        westGateButton.setPreferredSize(new java.awt.Dimension(80, 50));
        westGateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                westGateButtonActionPerformed(evt);
            }
        });

        eastGateButton.setFont(new java.awt.Font("Microsoft YaHei", 0, 15)); // NOI18N
        eastGateButton.setFocusable(false);
        eastGateButton.setPreferredSize(new java.awt.Dimension(80, 50));
        eastGateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eastGateButtonActionPerformed(evt);
            }
        });

        castleLabel.setText("castle");

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jLabel5.setText("北门");

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jLabel6.setText("南门");

        jLabel7.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jLabel7.setText("西门");

        jLabel8.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jLabel8.setText("东门");

        northSlider.setMajorTickSpacing(100);
        northSlider.setMaximum(30000);
        northSlider.setMinorTickSpacing(1);
        northSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                northSliderStateChanged(evt);
            }
        });

        northSoldiersLabel.setEditable(false);
        northSoldiersLabel.setText("0");
        northSoldiersLabel.setMaximumSize(new java.awt.Dimension(60, 22));
        northSoldiersLabel.setMinimumSize(new java.awt.Dimension(60, 22));
        northSoldiersLabel.setPreferredSize(new java.awt.Dimension(60, 22));

        bingli1.setText("兵力:");

        southSlider.setMajorTickSpacing(100);
        southSlider.setMaximum(10000);
        southSlider.setMinorTickSpacing(1);
        southSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                southSliderStateChanged(evt);
            }
        });

        bingli3.setText("兵力:");

        southSoldiersLabel.setEditable(false);
        southSoldiersLabel.setText("0");
        southSoldiersLabel.setMaximumSize(new java.awt.Dimension(60, 22));
        southSoldiersLabel.setMinimumSize(new java.awt.Dimension(60, 22));
        southSoldiersLabel.setPreferredSize(new java.awt.Dimension(60, 22));

        westSlider.setMajorTickSpacing(100);
        westSlider.setMaximum(10000);
        westSlider.setMinorTickSpacing(1);
        westSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                westSliderStateChanged(evt);
            }
        });

        bingli2.setText("兵力:");

        westSoldiersLabel.setEditable(false);
        westSoldiersLabel.setText("0");
        westSoldiersLabel.setMaximumSize(new java.awt.Dimension(60, 22));
        westSoldiersLabel.setMinimumSize(new java.awt.Dimension(60, 22));
        westSoldiersLabel.setPreferredSize(new java.awt.Dimension(60, 22));

        eastSlider.setMajorTickSpacing(100);
        eastSlider.setMaximum(10000);
        eastSlider.setMinorTickSpacing(1);
        eastSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                eastSliderStateChanged(evt);
            }
        });

        bingli4.setText("兵力:");

        eastSoldiersLabel.setEditable(false);
        eastSoldiersLabel.setText("0");
        eastSoldiersLabel.setMaximumSize(new java.awt.Dimension(60, 22));
        eastSoldiersLabel.setMinimumSize(new java.awt.Dimension(60, 22));
        eastSoldiersLabel.setPreferredSize(new java.awt.Dimension(60, 22));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "剑", "枪", "弓", "骑" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "剑", "枪", "弓", "骑" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "剑", "枪", "弓", "骑" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "剑", "枪", "弓", "骑" }));

        jLabel1.setText("兵种:");

        jLabel2.setText("兵种:");

        jLabel3.setText("兵种:");

        jLabel4.setText("兵种:");

        jLabel9.setText("剩余兵力：");

        remainingLabel.setText("0");

        javax.swing.GroupLayout dispatchTroopsPanelLayout = new javax.swing.GroupLayout(dispatchTroopsPanel);
        dispatchTroopsPanel.setLayout(dispatchTroopsPanelLayout);
        dispatchTroopsPanelLayout.setHorizontalGroup(
            dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispatchTroopsPanelLayout.createSequentialGroup()
                .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startButton)
                        .addGap(18, 18, 18)
                        .addComponent(backButton))
                    .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                        .addComponent(bingli2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(westSoldiersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(westSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                        .addComponent(bingli4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(eastSoldiersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(eastSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dispatchTroopsPanelLayout.createSequentialGroup()
                                .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(westGateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel9)
                                            .addComponent(remainingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(89, 89, 89)
                                .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(southGateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(northGateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel6))))
                                    .addComponent(castleLabel))
                                .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(northSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                                .addComponent(bingli1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(northSoldiersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(12, 12, 12)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(southSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                                .addComponent(bingli3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(southSoldiersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(12, 12, 12)
                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                        .addGap(237, 237, 237)
                                        .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel8)
                                                .addGap(28, 28, 28))
                                            .addComponent(eastGateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(18, 18, 18))
            .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dispatchTroopsPanelLayout.setVerticalGroup(
            dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(20, 20, 20)
                        .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(northGateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(remainingLabel))))
                    .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                        .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(northSoldiersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bingli1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(northSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(75, 75, 75)
                .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(westGateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispatchTroopsPanelLayout.createSequentialGroup()
                                .addComponent(castleLabel)
                                .addGap(20, 20, 20))))
                    .addGroup(dispatchTroopsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(eastGateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispatchTroopsPanelLayout.createSequentialGroup()
                        .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(westSoldiersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bingli2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(westSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispatchTroopsPanelLayout.createSequentialGroup()
                        .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bingli4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eastSoldiersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eastSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispatchTroopsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(20, 20, 20)
                        .addComponent(southGateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispatchTroopsPanelLayout.createSequentialGroup()
                        .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(southSoldiersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bingli3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(southSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(39, 39, 39)
                .addGroup(dispatchTroopsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(startButton))
                .addContainerGap())
        );
        dispatchTroopsPanel.setLayer(startButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(backButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(northGateButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(southGateButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(westGateButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(eastGateButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(castleLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(northSlider, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(northSoldiersLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(bingli1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(southSlider, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(bingli3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(southSoldiersLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(westSlider, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(bingli2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(westSoldiersLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(eastSlider, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(bingli4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(eastSoldiersLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(jComboBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(jComboBox2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(jComboBox3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(jComboBox4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dispatchTroopsPanel.setLayer(remainingLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(dispatchTroopsPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void middleActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_middleActionPerformed
    {//GEN-HEADEREND:event_middleActionPerformed
        this.setVisible(false);
        gp.setVisible(true);
    }//GEN-LAST:event_middleActionPerformed

    private void northActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_northActionPerformed
    {//GEN-HEADEREND:event_northActionPerformed
        setAttackedCity(city.getNeighbour(DIRECTION_NORTH));
    }//GEN-LAST:event_northActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_backButtonActionPerformed
    {//GEN-HEADEREND:event_backButtonActionPerformed
        gate = NONE;
        dispatchTroopsPanel.setVisible(false);
        selectCityPanel.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void northGateButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_northGateButtonActionPerformed
    {//GEN-HEADEREND:event_northGateButtonActionPerformed
        gate = NORTH;
        dispatchTroopsPanel.setVisible(false);
        selectCharacterPanel.setVisible(true);
    }//GEN-LAST:event_northGateButtonActionPerformed

    private void southGateButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_southGateButtonActionPerformed
    {//GEN-HEADEREND:event_southGateButtonActionPerformed
        gate = SOUTH;
        dispatchTroopsPanel.setVisible(false);
        selectCharacterPanel.setVisible(true);
    }//GEN-LAST:event_southGateButtonActionPerformed

    private void westGateButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_westGateButtonActionPerformed
    {//GEN-HEADEREND:event_westGateButtonActionPerformed
        gate = WEST;
        dispatchTroopsPanel.setVisible(false);
        selectCharacterPanel.setVisible(true);
    }//GEN-LAST:event_westGateButtonActionPerformed

    private void eastGateButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_eastGateButtonActionPerformed
    {//GEN-HEADEREND:event_eastGateButtonActionPerformed
        gate = EAST;
        dispatchTroopsPanel.setVisible(false);
        selectCharacterPanel.setVisible(true);
    }//GEN-LAST:event_eastGateButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_confirmButtonActionPerformed
    {//GEN-HEADEREND:event_confirmButtonActionPerformed
        charName = general.getName();
        
        if (charName.equals("无") || charName == null || charName.equals("")) {
            JOptionPane.showMessageDialog(frame, "请选择有效武将！", "选择错误", JOptionPane.PLAIN_MESSAGE);
        } else
        {
            dispatchTroops();
            selectCharacterPanel.setVisible(false);
            dispatchTroopsPanel.setVisible(true);
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void tableBackButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_tableBackButtonActionPerformed
    {//GEN-HEADEREND:event_tableBackButtonActionPerformed
        selectCharacterPanel.setVisible(false);
        dispatchTroopsPanel.setVisible(true);
    }//GEN-LAST:event_tableBackButtonActionPerformed

    private void southActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_southActionPerformed
    {//GEN-HEADEREND:event_southActionPerformed
        setAttackedCity(city.getNeighbour(DIRECTION_SOUTH));
    }//GEN-LAST:event_southActionPerformed

    private void westActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_westActionPerformed
    {//GEN-HEADEREND:event_westActionPerformed
        setAttackedCity(city.getNeighbour(DIRECTION_WEST));
    }//GEN-LAST:event_westActionPerformed

    private void eastActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_eastActionPerformed
    {//GEN-HEADEREND:event_eastActionPerformed
        setAttackedCity(city.getNeighbour(DIRECTION_EAST));
    }//GEN-LAST:event_eastActionPerformed

    private void northWestActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_northWestActionPerformed
    {//GEN-HEADEREND:event_northWestActionPerformed
        setAttackedCity(city.getNeighbour(DIRECTION_NORTHWEST));
    }//GEN-LAST:event_northWestActionPerformed

    private void northEastActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_northEastActionPerformed
    {//GEN-HEADEREND:event_northEastActionPerformed
        setAttackedCity(city.getNeighbour(DIRECTION_NORTHEAST));
    }//GEN-LAST:event_northEastActionPerformed

    private void southWestActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_southWestActionPerformed
    {//GEN-HEADEREND:event_southWestActionPerformed
        setAttackedCity(city.getNeighbour(DIRECTION_SOUTHWEST));
    }//GEN-LAST:event_southWestActionPerformed

    private void southEastActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_southEastActionPerformed
    {//GEN-HEADEREND:event_southEastActionPerformed
        setAttackedCity(city.getNeighbour(DIRECTION_SOUTHEAST));
    }//GEN-LAST:event_southEastActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_startButtonActionPerformed
    {//GEN-HEADEREND:event_startButtonActionPerformed
        // Legion(Commander, Soldiers, UnitType)
        northGateLegion = new Legion(northGeneral, northSlider.getValue(), 1);
        southGateLegion = new Legion(southGeneral, southSlider.getValue(), 1);
        // westGateLegion = new Legion(westGeneral, westSlider.getValue(), 1);
        // eastGateLegion = new Legion(eastGeneral, eastSlider.getValue(), 1);
        
        createBattlePanel();
        this.setVisible(false);

        // Display Commander info
        // northGeneral.calcCommanderStats();
        // southGeneral.calcCommanderStats();
        // System.out.println(northGeneral.getName() + " Damage: " + northGeneral.getDmg() + " Defence: " + northGeneral.getDef());
        // System.out.println(southGeneral.getName() + " Damage: " + southGeneral.getDmg() + " Defence: " + southGeneral.getDef());
        // Display Legion info
        // System.out.println("北门军队 HP:" + northGateLegion.getHP() + " Damage: " + northGateLegion.getDmg() + " Defence: " + northGateLegion.getDef());
        // System.out.println("南门军队 HP:" + southGateLegion.getHP() + " Damage " + southGateLegion.getDmg() + " Defence: " + southGateLegion.getDef());
    }//GEN-LAST:event_startButtonActionPerformed

    private void northSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_northSliderStateChanged
    {//GEN-HEADEREND:event_northSliderStateChanged
        northSoldiersLabel.setText(northSlider.getValue() + "");
        calcRemainingSoldiers();
        remainingLabel.setText(remainingSoldiers + "");
    }//GEN-LAST:event_northSliderStateChanged

    private void southSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_southSliderStateChanged
    {//GEN-HEADEREND:event_southSliderStateChanged
        southSoldiersLabel.setText(southSlider.getValue() + "");
        calcRemainingSoldiers();
        remainingLabel.setText(remainingSoldiers + "");
    }//GEN-LAST:event_southSliderStateChanged

    private void westSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_westSliderStateChanged
    {//GEN-HEADEREND:event_westSliderStateChanged
        westSoldiersLabel.setText(westSlider.getValue() + "");
        calcRemainingSoldiers();
        remainingLabel.setText(remainingSoldiers + "");
    }//GEN-LAST:event_westSliderStateChanged

    private void eastSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_eastSliderStateChanged
    {//GEN-HEADEREND:event_eastSliderStateChanged
        eastSoldiersLabel.setText(eastSlider.getValue() + "");
        calcRemainingSoldiers();
        remainingLabel.setText(remainingSoldiers + "");
    }//GEN-LAST:event_eastSliderStateChanged

    private void charTableMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_charTableMouseClicked
    {//GEN-HEADEREND:event_charTableMouseClicked
        if ((!city.getCharacterList().isEmpty()) && (charTable.getSelectedRow() < city.getCharacterList().size())) {
            general = city.getCharacterList().get(charTable.getSelectedRow());      // set general to the according selected row.
            nameLabel.setText(general.getName());
            general.calcCommanderStats();
            attackLabel.setText("攻击：" + general.getDmg());
            defenceLabel.setText("防御：" + general.getDef());
        }
    }//GEN-LAST:event_charTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel attackLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel bingli1;
    private javax.swing.JLabel bingli2;
    private javax.swing.JLabel bingli3;
    private javax.swing.JLabel bingli4;
    private javax.swing.JLabel castleLabel;
    private javax.swing.JTable charTable;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel defenceLabel;
    private javax.swing.JLayeredPane dispatchTroopsPanel;
    private javax.swing.JButton east;
    private javax.swing.JButton eastGateButton;
    private javax.swing.JSlider eastSlider;
    private javax.swing.JTextField eastSoldiersLabel;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton middle;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton north;
    private javax.swing.JButton northEast;
    private javax.swing.JButton northGateButton;
    private javax.swing.JSlider northSlider;
    private javax.swing.JTextField northSoldiersLabel;
    private javax.swing.JButton northWest;
    private javax.swing.JLabel remainingLabel;
    private javax.swing.JLayeredPane selectCharacterPanel;
    private javax.swing.JLayeredPane selectCityPanel;
    private javax.swing.JButton south;
    private javax.swing.JButton southEast;
    private javax.swing.JButton southGateButton;
    private javax.swing.JSlider southSlider;
    private javax.swing.JTextField southSoldiersLabel;
    private javax.swing.JButton southWest;
    private javax.swing.JButton startButton;
    private javax.swing.JButton tableBackButton;
    private javax.swing.JLabel title;
    private javax.swing.JButton west;
    private javax.swing.JButton westGateButton;
    private javax.swing.JSlider westSlider;
    private javax.swing.JTextField westSoldiersLabel;
    // End of variables declaration//GEN-END:variables
}
