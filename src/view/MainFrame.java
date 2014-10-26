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
public class MainFrame extends JFrame
{
    private static final long serialVersionUID = 1L;
    private final StartPanel startPanel;
    
    /**
     * Creates new form NewJFrame
     */
    public MainFrame()
    {
        super();
        
        setIcon();
        initComponents();

        // Create StartPanel
        startPanel = new StartPanel(this);
        getContentPane().add(startPanel);
        
        setWindowLocation(); 
    }
    
    private void setIcon()
    {
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/frameicon.png"));
        setIconImage(icon.getImage());
    }
    
    private void setWindowLocation()
    {
        // setUndecorated(true);
        Dimension windowSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)(screenSize.getWidth() - windowSize.getWidth()) / 2, (int)(screenSize.getHeight() - windowSize.getHeight()) / 2);
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
