/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.util;

import com.a.a.a.b.i;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author AsyncTask.Void.087
 */
public class ComponentIdentifier {

    public static final int GET_TEXT = 0;

    public static final int WARNING = 1;

    public static final int SET_TEXT = 2;

    Object instance;
    
    public static JPopupMenu menu;

    public ComponentIdentifier(Object instance) {
        this.instance = instance;
    }

    private void popUpCreator(Point point, String message) {
        menu = new JPopupMenu();
        menu.setOpaque(true);
        menu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(43, 43, 43)));
        menu.setLightWeightPopupEnabled(false);
        menu.setBackground(Color.BLACK);
        JMenuItem item = new JMenuItem(String.valueOf(message));
        item.setUI(new StyleMenuItemUI());
        item.setOpaque(true);
        item.setBackground(Color.RED);
        item.setContentAreaFilled(false);
        item.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        item.setForeground(new java.awt.Color(255, 255, 255));
        menu.add(item);
        menu.show((Component) instance, (int) point.getX(), (int) point.getY());
    }
    
    
    
    
    public Object JSpinnerComponent(Component component, Object... object) {
        
        JSpinner box = ((JSpinner) component);
        
        switch((int) object[0]){
            case GET_TEXT :
                return (int) box.getValue();
            case WARNING :
                if ((int) box.getValue() == 0) {
                    popUpCreator(new Point(box.getX() + box.getWidth() - 100, 
                                           box.getY() + box.getHeight() + ComponentProcess.extraY - 5), 
                            String.valueOf(object[1]));
                    box.requestFocus();
                }
                return ((int) box.getValue() == 0);
            case SET_TEXT :
                int i = Integer.parseInt(String.valueOf(object[1]));
                box.setValue(i);
            default:
        }
        
        return true;
    }
    
    
    
    public Object JComboBoxComponent(Component component, Object... object) {
        
        JComboBox box = ((JComboBox) component);
        
        switch((int) object[0]){
            case GET_TEXT :
                return (box.getSelectedIndex() == 0) ? "" :
                        String.valueOf(box.getSelectedItem()).replace(String.valueOf(object[2]), "").replace(".", "");
            case WARNING :
                if (box.getSelectedIndex() == 0) {
                    popUpCreator(new Point(box.getX() + box.getWidth() - 100, 
                                           box.getY() + box.getHeight() + ComponentProcess.extraY - 5), 
                            String.valueOf(object[1]));
                    box.requestFocus();
                }
                return (box.getSelectedIndex() == 0);
            case SET_TEXT :
                box.setSelectedItem(String.valueOf(object[1]));
            default:
        }
        
        return true;
    }
    

    public Object JTextFieldComponent(Component component, Object... object) {
        JTextField field = ((JTextField) component);

        switch ((int) object[0]) {
            case GET_TEXT:
                return field.getText().replace(String.valueOf(object[2]), "").replace(".", "");
            case WARNING:
                if (field.getText().replaceAll(String.valueOf(object[2]) , "").isEmpty()) {
                    popUpCreator(new Point(field.getX() + field.getWidth() - 100,
                                 field.getY() + field.getHeight() + ComponentProcess.extraY - 5), (String) object[1]);
                    field.requestFocus();
                }
                return field.getText().replaceAll(String.valueOf(object[2]) , "").isEmpty();
            case SET_TEXT:
                field.setText(String.valueOf(object[1]));
            default:
        }

        return true;
    }
    
    
    public Object JTextAreaComponent(Component component, Object... object) {
        JTextArea field = ((JTextArea) component);

        switch ((int) object[0]) {
            case GET_TEXT:
                return field.getText().replace(String.valueOf(object[2]), "").replace(".", "");
            case WARNING:
                if (field.getText().replaceAll(String.valueOf(object[2]) , "").isEmpty()) {
                    popUpCreator(new Point(field.getX() + field.getWidth() - 100,
                                 field.getY() + field.getHeight() + ComponentProcess.extraY - 5), (String) object[1]);
                    field.requestFocus();
                }
                return field.getText().replaceAll(String.valueOf(object[2]) , "").isEmpty();
            case SET_TEXT:
                field.setText(String.valueOf(object[1]));
            default:
        }

        return true;
    }
    
    
    
    public Object JDateChooserComponent(Component component, Object... object) {
        
        try {
            JDateChooser box = ((JDateChooser) component);
            
            switch((int) object[0]){
                case GET_TEXT :
                    Date date = box.getDate();
                    String value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    return value;
                case WARNING :
                    if (box.getDate().toString().isEmpty()) {
                        popUpCreator(new Point(box.getX() + box.getWidth() - 100,
                                box.getY() + box.getHeight() + ComponentProcess.extraY - 5),
                                String.valueOf(object[1]));
                        box.requestFocus();
                    }
                    return (box.getDate().toString().isEmpty());
                case SET_TEXT :
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = format.parse(String.valueOf(object[1]));
                    box.setDate(d);
                default:
            }
            
            return true;
        } catch (ParseException ex) {
            Logger.getLogger(ComponentIdentifier.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
