/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bundaran.administrator.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.PanelUI;

/**
 *
 * @author HAMSTER-BIRU
 */
public class ImageToPanel extends PanelUI {
    
    BufferedImage bi;
    ImageIcon lapas;
    
    public ImageToPanel(BufferedImage bi, JPanel panel) {
        this.bi = bi;
        lapas = new ImageIcon(bi);
        
    }

    
    @Override
    public void paint(Graphics g, JComponent c) {
        if (bi != null) {
            g.drawImage(lapas.getImage(), 0, 0, bi.getWidth(), bi.getHeight(), null);
        } 
    }
 
}
