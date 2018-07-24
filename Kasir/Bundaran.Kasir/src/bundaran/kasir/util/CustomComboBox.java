/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CustomComboBox extends JLabel implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        this.setFocusable(true);
        JLabel label = new JLabel();

        //list.setBackground(Color.yellow);
        list.setCellRenderer(new SelectedListCellRenderer());
        label.setText("  " + String.valueOf(value));

        return label;
    }

    public class SelectedListCellRenderer extends DefaultListCellRenderer {
        
        

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
            if (isSelected) {
                c.setBackground(new java.awt.Color(254, 137, 0));
            }

            c.setPreferredSize(new Dimension(200, 30));
            JLabel label = (JLabel) c;
            label.setText("  " + String.valueOf(value));
            return label;
        }
    }
    
    

}
