/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bundaran.kasir.report;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import java.awt.Color;

/**
 *
 * @author HAMSTER-BIRU
 */
public final class DetailStruckCell extends PdfPCell {

    public DetailStruckCell(String data, Color c, Color fontColor, String font, int align, boolean border) {
        super(new Paragraph(data, FontFactory.getFont(
                            font, 10, Font.NORMAL, fontColor)));
        
        setHorizontalAlignment(align);
        setPaddingLeft(17);
        setPaddingRight(5);
        setPaddingTop(6);
        setPaddingBottom(9);
        setVerticalAlignment(align);
        
        if (!border) {
            setBorder(Rectangle.NO_BORDER);
        }
        
        if (c != null) 
            setBackgroundColor(c);
    }
    
    
    
    

    @Override
    public void setPadding(float f) {
        super.setPadding(f); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVerticalAlignment(int i) {
        super.setVerticalAlignment(i); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public void setHorizontalAlignment(int i) {        
        super.setHorizontalAlignment(i); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public void setBackgroundColor(Color color) {
        super.setBackgroundColor(color); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
