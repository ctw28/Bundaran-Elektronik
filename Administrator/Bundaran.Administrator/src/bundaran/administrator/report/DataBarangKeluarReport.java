/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.report;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author HAMSTER-BIRU
 */
public class DataBarangKeluarReport {

    String[] colNames;
    ArrayList<ArrayList<String>> listData;
    String title;
    boolean auto;
    String fileNames;

    public DataBarangKeluarReport(String[] colNames, ArrayList<ArrayList<String>> listData, String title, boolean auto, String fileNames) {
        this.colNames = colNames;
        this.listData = listData;
        this.title = title;
        this.auto = auto;
        this.fileNames = fileNames;

        if (!auto) {
            colNames = new String[colNames.length + 1];

            for (int i = 0; i < colNames.length; i++) {
                if (i == 0) {
                    colNames[i] = "No ";
                } else {
                    colNames[i] = this.colNames[i - 1];
                }
            }

            this.colNames = colNames;
        }
    }

    public boolean doCreateReport(String filterType, String key, 
            String rentang, String waktu) {
        try {
            Paragraph filter = null;
            String filterString;

            //if (!filterType.isEmpty() && !filterInventory.isEmpty() && !key.isEmpty()) {
            filterString = ((!filterType.isEmpty()) ? "Tujuan barang keluar adalah " + filterType : "")
                    + ((!waktu.isEmpty()) ? " dan keluar pada "+waktu : "")
                    //+ ((!key.isEmpty()) ? " dengan barang " + key : "")
                    ;
            
            

  //          /*
            if (filterType.isEmpty() &&  key.isEmpty() &&  waktu.isEmpty()) {
            } else {
                filter = new Paragraph(filterString,
                        FontFactory.getFont(
                                FontFactory.HELVETICA_BOLD, 8, Font.ITALIC, Color.BLACK)
                );
                filter.setAlignment(Element.ALIGN_LEFT);
            }
//*/
            //} else if

            Document document = new Document(PageSize.A4.rotate(), 75, 50, 50, 75);
            //     document.set
            PdfWriter.getInstance(document, new FileOutputStream(fileNames));
            document.open();
            
            createTokoDetail(document);
            document.add(new Paragraph(" ",
                        FontFactory.getFont(
                                FontFactory.HELVETICA_BOLD, 2, Font.ITALIC, Color.BLACK)
                ));
            
            document.add(new Paragraph("___________________________________________________________________________________________________"
                    + "___________________________________________________________________________________________________"
                    + "___________________________________________________________________________________________________"
                    + "___________________________________________________________________________________________________"
                    + "___________________________________________________________________________________________________"
                    + "___________________________________________________________________________________________________"
                    + "__________________________________________________",
                        FontFactory.getFont(
                                FontFactory.HELVETICA_BOLD, 2, Font.ITALIC, Color.BLACK)
                ));
            
            document.add(createReportHeader("LAPORAN " + title));
            //document.add(new Paragraph("Login sebagai : " + mainForm.username));
            //document.add(paragraph);
            Date date = new Date();
            String tgl = new SimpleDateFormat("dd-MM-yyyy").format(date);
            
            Paragraph p = new Paragraph("Hari ini tanggal " + tgl + ",",
                        FontFactory.getFont(
                                FontFactory.HELVETICA_BOLD, 7, Font.ITALIC, Color.BLACK)
                ); 
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            
            document.add(new Paragraph(" "));
            if (filter != null) {
                document.add(filter);
                document.add(new Paragraph(" ",
                        FontFactory.getFont(
                                FontFactory.HELVETICA_BOLD, 5, Font.ITALIC, Color.BLACK)
                ));
            }

            //com.lowagie.text.Image image = com.lowagie.text.Image.getInstance("resource/logo.png");
            //document.add(image);
            PdfPTable table = new PdfPTable(colNames.length);
            int[] is = new int[colNames.length];
            for (int i = 0; i < colNames.length; i++) {
                is[i] = colNames[i].length() + 3;
            }

            table.setWidthPercentage(100f);
            table.setWidths(is);
            for (String name : colNames) {
                CellEditting editting = new CellEditting(name, new Color(254, 137, 0), Color.white);
                //editting.set
                table.addCell(editting);
            }

            int i = 1;
            for (ArrayList<String> arrayList : listData) {
                if (!auto) {
                    CellEditting editting = new CellEditting(String.valueOf(i), Color.WHITE, Color.BLACK);
                    table.addCell(editting);
                }
                for (String string : arrayList) {
                    CellEditting editting = new CellEditting(string, Color.WHITE, Color.black);
                    table.addCell(editting);
                }
                i++;
            }
            document.add(table);
            document.close();
            return true;
        } catch (IOException | DocumentException ex) {
            //Logger.getLogger(adminDataForm.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    private Paragraph createReportHeader(String title) {
        Paragraph paragraph = new Paragraph(title,
                FontFactory.getFont(
                        FontFactory.HELVETICA_BOLD, 9, Font.NORMAL, Color.BLACK)
        );

        paragraph.setAlignment(Element.ALIGN_RIGHT);

        return paragraph;
    }
    
    private void createTokoDetail(Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph("TOKO BUNDARAN ELEKTRONIK",
                FontFactory.getFont(
                        FontFactory.HELVETICA_BOLD, 11, Font.NORMAL, Color.BLACK)
        );
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        
        //alamat toko
        paragraph = new Paragraph("Jln. Doktor Sam Ratulangi, No 192, Kendari Sulawesi Tenggara",
                FontFactory.getFont(
                        FontFactory.HELVETICA_BOLD, 7, Font.NORMAL, Color.BLACK)
        );
        document.add(paragraph);

        
        //kontak
        paragraph = new Paragraph("No. Telepon (0401) 325117 / Hp : 085396696513",
                FontFactory.getFont(
                        FontFactory.HELVETICA_BOLD, 7, Font.NORMAL, Color.BLACK)
        );
        document.add(paragraph);
        
    }

}
