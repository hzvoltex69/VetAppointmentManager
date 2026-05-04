/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vetappointmentmanager.ui.gui;

/**
 *
 * @author hzvol
 */

import javax.swing.*;
import java.awt.*;

public abstract class WindowBase extends JFrame {
    
    public WindowBase(String title) {
        setTitle(title);
        setSize(300, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));
        initComponents();
    }
    
    protected abstract void initComponents();
    
    protected String askInput(String message) {
        return JOptionPane.showInputDialog(null, message);
    }
    
    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
    protected boolean askConfirm(String message) {
        int result = JOptionPane.showConfirmDialog(null, message, "Confirmar",
                        JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
    
    protected JButton createButton(String text) {
        return new JButton(text);
    }
}
