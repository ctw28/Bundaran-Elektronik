/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.util;

import java.math.BigInteger;
import javax.swing.JTextField;

/**
 *
 * @author AsyncTask.Void.087
 */
public class NumberValidation {
    
    String spliter = null;
    boolean setSplitter = false;
    String money = null;

    public NumberValidation(String spliter) {
        this.spliter = spliter;
    }

    public NumberValidation(String spliter, String money) {
        this.spliter = spliter;
        this.money = money;
    }
    
    
    public String fieldChecked(String text){
        
        text =  (checkError((text = identifySpliting(text)))) ? 
                text : valueSyncronized(text);
        
        text = spliterSave(text);
        
        return ((money != null) ? money : "") + text;
    }
    
    private String identifySpliting(String text) {       
        
        if (money != null && text.contains(money)) {
            text = text.replace(money, "");
        }
        
        return (spliter != null && text.contains(spliter)) 
                ? (text.replace(spliter, "")) : text;
    }

    private boolean checkError(String text) {
        try {
            BigInteger.valueOf(Long.parseLong(text));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    private String valueSyncronized(String text){
        if (text.length() > 1) {
            while (!checkError(text)) {                
                if (text.length() == 1) 
                    return "";
                text = text.substring(0, text.length() - 1);   
            }            
        } else
            text = "";
        
        return text;
    }

    public void setSetSplitter(boolean setSplitter) {
        this.setSplitter = setSplitter;
    }

    
    private String spliterSave(String text){
        int lengthString = text.length();

        if (lengthString > 3) {            
            
            int k = lengthString / 3;
            k = (lengthString % 3 == 0) ? k : (k + 1);
            
            String[] subText = new String[k];
            
            int dec = 0;
            for (int i = k; i >= 1; i--) {
                int start = (lengthString - dec) - 3;
                subText[i-1] = text.substring((start > 0) ? start : 0 , lengthString - dec);
                dec += 3;
            }
            
            text = "";
            int sum = 0;
            for (String string : subText) {
                text += string + ((sum != (k - 1)) ? spliter : "");
                sum++;
            }
        }
        
        
        return text;
    }
    
}
