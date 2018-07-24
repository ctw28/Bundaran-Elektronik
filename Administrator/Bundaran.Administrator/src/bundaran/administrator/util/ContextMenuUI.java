/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bundaran.administrator.util;

import java.awt.Color;
import javax.swing.plaf.basic.BasicMenuItemUI;

/**
 *
 * @author HAMSTER
 */
public class ContextMenuUI  extends BasicMenuItemUI {
    
    
    @Override
    protected void installDefaults() {
        
        selectionBackground = new Color(0x2b2b2b);
        super.installDefaults(); //To change body of generated methods, choose Tools | Templates.
    }
    
}






