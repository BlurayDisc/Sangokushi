/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sangokushi;

import sangokushi.view.MainFrame;

/**
 *
 * @author RuN
 */
public class Sangokushi {
    
    public static void main(String args[]) {
        setLookAndFeel();
        run();
    }
    
    private static void setLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {          // Windows
                    javax.swing.UIManager.setLookAndFeel(info.getClassName()); break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex){}
    }
    
    private static void run() {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = MainFrame.getInstance();
                frame.setVisible(true);
            }
        });
    }
}
