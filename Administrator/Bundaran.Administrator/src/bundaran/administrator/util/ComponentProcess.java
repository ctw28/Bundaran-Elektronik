/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.util;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author AsyncTask.Void.087
 */
public class ComponentProcess extends ComponentIdentifier {

    ArrayList<Component> map;
    public static int extraY;
    Object[] extraParameter;

    public ComponentProcess(Object instance, ArrayList<Component> map, int extraY, Object... canNull) {
        super(instance);
        this.map = map;
        ComponentProcess.extraY = extraY;
        this.extraParameter = canNull;
    }

    
    public boolean isNotComponentWarning() {

        String tracking = "Y";
        int up = 0;
        for (Component value : map) {
            if (value == null)
                continue;
            
            String key = ((String[]) extraParameter[0])[up];
            String split = ((String[]) extraParameter[2])[up];
            if (!((boolean[]) extraParameter[1])[up]) {
                if (value instanceof JTextField) {
                    if (((boolean) JTextFieldComponent(value, WARNING, key, split))) {
                        tracking += "N";
                        break;
                    }
                } else if (value instanceof JComboBox) {
                    if (((boolean) JComboBoxComponent(value, WARNING, key, split))) {
                        tracking += "N";
                        break;
                    }
                } else if (value instanceof JSpinner) {
                    if (((boolean) JSpinnerComponent(value, WARNING, key, split))) {
                        tracking += "N";
                        break;
                    }
                } else if (value instanceof JTextArea) {
                    if (((boolean) JTextAreaComponent(value, WARNING, key, split))) {
                        tracking += "N";
                        break;
                    }
                } else if (value instanceof JDateChooser) {
                    if (((boolean) JDateChooserComponent(value, WARNING, key, split))) {
                        tracking += "N";
                        break;
                    }
                }
            }
            up++;
        }

        return !tracking.contains("N");
    }

    
    
    public ArrayList<Object> componentsText() {
        ArrayList<Object> list = new ArrayList<>();

        int up = 0;
        for (Component value : map) {
            String key = ((String[]) extraParameter[0])[up];
            String split = ((String[]) extraParameter[2])[up];
            
            if (value instanceof JTextField) 
                list.add(JTextFieldComponent(value, GET_TEXT, key, split));
            else if (value instanceof JComboBox) 
                list.add(JComboBoxComponent(value, GET_TEXT, key, split));
            else if (value instanceof JSpinner)
                list.add(JSpinnerComponent(value, GET_TEXT, key, split));
            else if (value instanceof JTextArea)
                list.add(JTextAreaComponent(value, GET_TEXT, key, split));
            else if (value instanceof JDateChooser)
                list.add(JDateChooserComponent(value, GET_TEXT, key, split));
            
            up++;
        } 

        return list;
    }
    
    
    public void setComponentsText(ArrayList<String> dataSelected){
        
        int up = 0;
        for (Component value : map) {
            String key = dataSelected.get(up);
            String split = ((String[]) extraParameter[2])[up];
            
            if (value instanceof JTextField) 
                JTextFieldComponent(value, SET_TEXT, key, split);
            else if (value instanceof JComboBox) 
                JComboBoxComponent(value, SET_TEXT, key, split);
            else if (value instanceof JSpinner)
                JSpinnerComponent(value, SET_TEXT, key, split);
            else if (value instanceof JTextArea)
                JTextAreaComponent(value, SET_TEXT, key, split);
            else if (value instanceof JDateChooser)
                JDateChooserComponent(value, SET_TEXT, key, split);
            
            
            up++;
        }
        
    }
    
    
}
