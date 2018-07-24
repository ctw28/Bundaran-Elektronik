/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bundaran.kasir.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author HAMSTER
 */
public class WarningUI  extends BasicButtonUI {
    
    public int warna;
    public AbstractButton button;
    static JComponent c;
    
    @Override
    public void installUI (JComponent c){
        //StyledButtonUI.c = c;
        super.installUI(c);
        button = (AbstractButton) c;
        button.setMargin(new Insets(2, 14, 2, 14));
        
        int warnaDasar = 0x009999;
        int warnaTouch = 0x894a00;
        warna  = warnaDasar;  // warna awal button
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                  warna = warnaTouch;//4e4e4e;   ///
                  button.setBorder(new LineBorder(new Color(warna), 1));
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                  warna = warnaTouch;//4e4e4e;   ///
                  button.setBorder(new LineBorder(new Color(warna), 1));
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                  warna = warnaTouch;//4e4e4e;   ///
                  button.setBorder(new LineBorder(new Color(warna), 1));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                  warna = warnaDasar;//4e4e4e;   ///
                  button.setBorder(new LineBorder(new Color(warna), 1));
            }
        });
        button.setForeground(new Color(0xffffff));
        button.setOpaque(false);
        button.setBorder(new LineBorder(new Color(warna), 1));
        
        //button.setBorder(new EmptyBorder(0,0,0,0));
    }
    
    @Override
    public void paint (Graphics g, JComponent c){
        AbstractButton b = (AbstractButton) c;
        paintBackground(g, b, b.getModel().isPressed() ? 2 : 1);
        super.paint(g, c);
    }
    
    
    private void paintBackground (Graphics g, JComponent c, int yOffset) {
        Dimension size = c.getSize();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(warna));
        g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 0, 0);
        g.setColor(new Color(warna));
        g.fillRoundRect(0, yOffset, size.width, size.height + yOffset, 0, 0);
    }
    
}






