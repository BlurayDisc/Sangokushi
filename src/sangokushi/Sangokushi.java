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
public class Sangokushi
{

    /**
        * @param args the command line arguments
        */
    public static void main(String args[])
    {
        /* Set the Windows look and feel */
        // Look and feel setting code (optional)
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
        }
        
        //<editor-fold defaultstate="collapsed" desc=" Catch Statement ">
        
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
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
