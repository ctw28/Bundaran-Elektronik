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
import com.lowagie.text.pdf.PdfPCell;
import java.awt.Color;

/**
 *
 * @author HAMSTER-BIRU
 */
public final class CellEditting extends PdfPCell {

    public CellEditting(String data, Color c, Color fontColor, String font) {
        super(new Paragraph(data, FontFactory.getFont(
                            font, 10, Font.NORMAL, fontColor)));
        
        setHorizontalAlignment(Element.ALIGN_LEFT);
        //setPaddingLeft(5);
        //setPaddingRight(5);
        setPaddingTop(6);
        setPaddingBottom(9);
        setVerticalAlignment(Element.ALIGN_CENTER);
        
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
        super.setHorizontalAlignment(Element.ALIGN_CENTER); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public void setBackgroundColor(Color color) {
        super.setBackgroundColor(color); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
