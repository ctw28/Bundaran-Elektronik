/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bundaran.administrator.util;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.plaf.basic.BasicMenuBarUI;

/**
 *
 * @author HAMSTER
 */
public class MenuBarCustomize extends BasicMenuBarUI {
    
    Color warnaBar;

    public MenuBarCustomize(Color warnaBar) {
        this.warnaBar = warnaBar;
    }

    @Override
    public void installUI(JComponent c) {        
        JComponent jc = c;
        super.installUI(c); //To change body of generated methods, choose Tools | Templates.
        JMenuBar bar = (JMenuBar) jc;
    }
    
    
    @Override
    public void paint ( Graphics g, JComponent c ){
        g.setColor(warnaBar);
        g.fillRect ( 0, 0, c.getWidth (), c.getHeight () );
    }
    
}
