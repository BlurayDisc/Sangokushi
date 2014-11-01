/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 *
 * @author RuN
 */
public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final MainFrame instance = new MainFrame();
    private final StartPanel startPanel;
    private final ForcePanel forcePanel;
    private final OptionPanel optionPanel;
    
    /**
     * Creates new form NewJFrame
     */
    private MainFrame() {
        super();
        setIcon();
        initComponents();
        
        // Create StartPanel
        startPanel = new StartPanel();
        getContentPane().add(startPanel);
        
        // Create ForcePanel
        forcePanel = new ForcePanel();
        getContentPane().add(forcePanel);
        forcePanel.setVisible(false);
        
        // Create OptionPanel
        optionPanel = new OptionPanel();
        getContentPane().add(optionPanel);
        optionPanel.setVisible(false);
        
        setWindowLocation(); 
    }
    
    public void showOptionPanel() {
        optionPanel.updateOptionPanel();
        optionPanel.setVisible(true);
    }
    
    public void showForcePanel() {
        forcePanel.updateForcePanel();
        forcePanel.setVisible(true);
    }
    
    public void showStartPanel() {
        startPanel.setVisible(true);
    }
    
    private void setIcon() {
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
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("MainFrame"); // NOI18N
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
