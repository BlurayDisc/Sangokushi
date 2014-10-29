/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sangokushi;

import view.MainFrame;

/**
 *
 * @author RuN
 */
public class Sangokushi {
    public static void main(String args[]) {     
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code ">
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))        // Windows
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex){}
        //</editor-fold>        
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                new MainFrame().setVisible(true);
            }
        });

    }
    
}
