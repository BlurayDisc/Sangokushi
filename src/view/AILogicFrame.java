/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.AI;

/**
 *
 * @author RuN
 */
public class AILogicFrame extends javax.swing.JFrame
{
    private static final long serialVersionUID = 1L;
    // AI ai = new AI();
    private final MainFrame frame;
    private GamePanel gp;
    private ActionListener proceedListener, closeListener;
    private Timer proceedTimer, closeTimer;
    /**
     * Creates new form AILogicFrame
     * @param frame
     * @param gp
     */
    public AILogicFrame(MainFrame frame, GamePanel gp)
    {
        super();
        this.frame = frame;
        this.gp = gp;
        setUndecorated(true);
        initComponents();
        initListeners();
    }
    
    public void calcAI()
    {
        disableProceedButton();
        centerWindowOnScreen();
        startTimers();
    }
    
    private void disableProceedButton()
    {
        gp.getProceedButton().setEnabled(false);
        frame.setEnabled(false);
        requestFocus();
    }
    
    private void centerWindowOnScreen()
    {
        Point windowLocation = frame.getLocation();
        setLocation((int)windowLocation.getX() + 250, (int)windowLocation.getY() + 250);
        this.setVisible(true);
    }
    
    private void startTimers()
    {
        jLabel1.setText("正在执行AI逻辑, 请等待。。。");
        proceedTimer.start();
    }
    
    private void initListeners()
    {
        proceedListener = new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent event)
            {
                jLabel1.setText("                 已完成                        ");
                closeTimer.start();
            }
        };
        
        closeListener = new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent event)
            {
                frame.setEnabled(true);
                gp.getProceedButton().setEnabled(true);
                frame.requestFocus();
                setVisible(false);
            }
        };

        proceedTimer = new Timer(1000, proceedListener);        // start after 1 seconds
        proceedTimer.setRepeats(false);
        closeTimer = new Timer(500, closeListener);             // start after 0.5 seconds
        closeTimer.setRepeats(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AI");
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(575, 400, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        setMaximumSize(new java.awt.Dimension(300, 100));
        setMinimumSize(new java.awt.Dimension(300, 100));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(300, 100));
        jPanel1.setMinimumSize(new java.awt.Dimension(300, 100));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("正在执行AI逻辑, 请等待。。。");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 40, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
