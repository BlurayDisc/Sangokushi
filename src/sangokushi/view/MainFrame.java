/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sangokushi.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author RuN
 */
public class MainFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    public static final MainFrame instance;
    public static final StartPanel startPanel;
    public static final ForcePanel forcePanel;
    public static final OptionPanel optionPanel;
    public static final GamePanel gamePanel;
    public static final BuildingsPanel buildingsPanel;
    public static final PrepareBattlePanel prepareBattlePanel;
    public static final BattlePanel battlePanel;
    private final JPanel[] panels;
    
    static {
        startPanel = new StartPanel();
        forcePanel = new ForcePanel();
        optionPanel = new OptionPanel();
        gamePanel = new GamePanel();
        buildingsPanel = new BuildingsPanel();
        prepareBattlePanel = new PrepareBattlePanel();
        battlePanel = new BattlePanel();
        instance = new MainFrame();
    }
    
    {
        panels = new JPanel[] {startPanel, forcePanel, optionPanel, gamePanel, buildingsPanel, prepareBattlePanel, battlePanel};
    }
    
    private MainFrame() {
        initIcon();
        initComponents();
        setWindowLocation();
        initPanels();
        startPanel.setVisible(true);
    }
    
    private void initPanels() {
        for (JPanel panel: panels) {
            panel.setSize(800, 600);
            panel.setVisible(false);
            add(panel);
        }
    }
    
    private void initIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/frameicon.png"));
        setIconImage(icon.getImage());
    }
    
    private void setWindowLocation() {
        // setUndecorated(true);
        Dimension windowSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)(screenSize.getWidth() - windowSize.getWidth()) / 2, (int)(screenSize.getHeight() - windowSize.getHeight()) / 2);
    }
    
    public static MainFrame getInstance() {
        return instance;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sangokushi");
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
