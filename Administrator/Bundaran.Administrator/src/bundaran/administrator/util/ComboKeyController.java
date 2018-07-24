/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.util;

import java.awt.EventQueue;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import bundaran.administrator.backgroundtask.ServerRequestController;
//import tokobangunan.kasir.view.PayDetail;

/**
 *
 * @author AsyncTask.Void.087
 */
public class ComboKeyController extends KeyAdapter {

    private final JComboBox<String> comboBox;
    private final List<String> list = new ArrayList<>();
    private boolean shouldHide = false;
    final private Object instance;

    public ComboKeyController(Object instance, JComboBox<String> combo) {
        super();
        this.comboBox = combo;
        this.instance = instance;

        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            list.add((String) comboBox.getItemAt(i));
        }
    }

    @Override
    public void keyTyped(final KeyEvent e) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JTextField field = (JTextField) e.getComponent();
                field.setMargin(new java.awt.Insets(2, 10, 2, 10));
                String text = field.getText();
                ComboBoxModel<String> m;
                if (text.isEmpty()) {
                    // return;/*
                    String[] array = list.toArray(new String[list.size()]);

                    m = new DefaultComboBoxModel<>(array);
                    setSuggestionModel(comboBox, m, "");
                    comboBox.hidePopup();//*/
                } else {
                    m = getSuggestedModel(list, text);

                    if (m.getSize() == 0 || shouldHide) {
                        comboBox.hidePopup();
                    } else {
                        ((JTextField) e.getComponent()).setText(text);
                        setSuggestionModel(comboBox, m, text);
                            comboBox.showPopup();
                    }
                }
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {

        JTextField textField = (JTextField) e.getComponent();
        String text = textField.getText();
        shouldHide = false;

        if (text.isEmpty()) {
            comboBox.hidePopup();
            return;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                for (String s : list) {
                    if (s.contains(text)) {
                        textField.setText(s);
                        return;
                    }
                }
                break;
            case KeyEvent.VK_ENTER:
                shouldHide = true;

                String valueDetected = String.valueOf(comboBox.getSelectedItem());
                if (!valueDetected.isEmpty()) {
                    if (!valueDetected.equals("null")) {
                        //comboBox.hidePopup();
                        String resultFunction = staticIdentifier.BackgroundProcess.RESUL_FUNCTION_NAME;
                        Class[] classTypes = {String.class};
                        Object[] dataReceived = {valueDetected};
                        try {
                            Method goResult = instance.getClass().getMethod(resultFunction, classTypes);
                            goResult.invoke(instance, dataReceived);
                        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            Logger.getLogger(ServerRequestController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        shouldHide = true;
                    }
                }

                break;
            case KeyEvent.VK_ESCAPE:
                shouldHide = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        

        JTextField textField = (JTextField) e.getComponent();
        String text = textField.getText();
        

        if (text.length() == 12) {
            textField.selectAll();
            comboBox.hidePopup();
            String resultFunction = staticIdentifier.BackgroundProcess.RESUL_FUNCTION_NAME;
            Class[] classTypes = {String.class};
            Object[] dataReceived = {text};
            try {
                Method goResult = instance.getClass().getMethod(resultFunction, classTypes);
                goResult.invoke(instance, dataReceived);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(ServerRequestController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.print(text);
        }
        
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            shouldHide = true;
        }
    }

    private static void setSuggestionModel(JComboBox<String> comboBox, ComboBoxModel<String> mdl, String str) {
        comboBox.setModel(mdl);
        comboBox.setSelectedIndex(-1);
        JTextField textField = (JTextField) comboBox.getEditor().getEditorComponent();
        textField.setMargin(new java.awt.Insets(2, 10, 2, 10));
        textField.setText(str);
    }

    private static ComboBoxModel<String> getSuggestedModel(List<String> list, String text) {
        DefaultComboBoxModel<String> m = new DefaultComboBoxModel<>();
        for (String s : list) {
            boolean find = false;
            for (int i = 0; i < s.length() - (text.length() - 1); i++) {
                if (s.toLowerCase().startsWith(text.toLowerCase(), i)) {
                    find = true;
                    break;
                }
            }

            if (find) {
                m.addElement(s);
            }

        }
        return m;
    }

}
