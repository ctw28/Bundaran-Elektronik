/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir.report;

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
import javax.swing.text.html.HTML;
import bundaran.kasir.util.NumberValidation;

/**
 *
 * @author HAMSTER-BIRU
 */
public class DataPenjualanDetailReport {

    String[] colNames;
    Object[] listData;
    Object[] paraPembeli;
    //ArrayList<ArrayList<String>>
    
    ArrayList<ArrayList<String>> dataGudangBarang;
    String title;
    boolean auto;
    String fileNames;

    public DataPenjualanDetailReport(String[] colNames, Object[] paraPembeli, Object[] listData, ArrayList<ArrayList<String>> dataGudangBarang, String title, boolean auto, String fileNames) {
        this.colNames = colNames;
        this.listData = listData;
        this.title = title;
        this.auto = auto;
        this.fileNames = fileNames;
        this.paraPembeli = paraPembeli;
        this.dataGudangBarang = dataGudangBarang;

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
    
    
    int getHargaModal(String idBarcode){
        
        for (ArrayList<String> colName : dataGudangBarang) {
            if (colName.get(0).equals(idBarcode)) {
                return Integer.parseInt(colName.get(2));
            }
        }
        
        return 0;
    }
    

    public boolean doCreateReport() {
        try {
            /*
            Paragraph filter = null;
            String filterString;

            //if (!filterType.isEmpty() && !filterInventory.isEmpty() && !key.isEmpty()) {
            filterString = ((!filterType.isEmpty()) ? "Untuk type penjualan per-" + filterType : "")
                    + ((!filterInventory.isEmpty()) ? " Di gudang " + filterInventory : "")
                    + ((!key.isEmpty()) ? " Dengan kata kunci " + key : "");

            if (filterType.isEmpty() && filterInventory.isEmpty() && key.isEmpty()) {
            } else {
                filter = new Paragraph(filterString,
                        FontFactory.getFont(
                                FontFactory.HELVETICA_BOLD, 8, Font.ITALIC, Color.BLACK)
                );
                filter.setAlignment(Element.ALIGN_LEFT);
            }
            //} else if
             */
            Document document = new Document(PageSize.A4, 75, 50, 40, 40);
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
                    + "___________________________________________________________________________________________________"
                    + "___________________________________________________________________________________________________"
                    + "____________________________________________________",
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
            /*
            if (filter != null) {
                document.add(filter);
                document.add(new Paragraph(" ",
                        FontFactory.getFont(
                                FontFactory.HELVETICA_BOLD, 5, Font.ITALIC, Color.BLACK)
                ));
            }
             */
            //com.lowagie.text.Image image = com.lowagie.text.Image.getInstance("resource/logo.png");
            //document.add(image);

            int index = 0;
            int totalTransaksi = 0;
            int totalModal = 0;
            NumberValidation nv = new NumberValidation(",", "Rp ");
            for (Object object : listData) {

                ArrayList<String> pembeli = (ArrayList<String>) paraPembeli[index];

                p = new Paragraph(String.valueOf(index + 1) + " : Rincian Transaksi " + pembeli.get(0),
                        FontFactory.getFont(
                                FontFactory.HELVETICA_BOLD, 6, Font.ITALIC, new Color(0, 153, 51))
                );
                p.setAlignment(Element.ALIGN_LEFT);
                document.add(p);
                document.add(new Paragraph(" ",
                        FontFactory.getFont(
                                FontFactory.HELVETICA_BOLD, 4, Font.ITALIC, Color.BLACK)
                ));

                PdfPTable paint = new PdfPTable(1);

                paint.setWidthPercentage(100f);
                paint.setWidths(new float[]{100f});

                String dataPembeli
                        = "\nID Transaksi                                  : " + pembeli.get(0) + "\n\n"
                        + "Tanggal Transaksi                         : " + pembeli.get(1) + "\n\n"
                        + "Nama Pembeli                               : " + ((pembeli.get(2).isEmpty()) ? "-" : pembeli.get(2)) + "\n\n"
                        + "Kontak Pembeli                             : " + ((pembeli.get(3).isEmpty()) ? "-" : pembeli.get(3)) + "\n\n"
                        + "Alamat Pembeli                             : " + ((pembeli.get(9).isEmpty()) ? "-" : pembeli.get(9)) + "\n\n"
                        + "Jatuh Tempo                                 : " + ((pembeli.get(5).isEmpty()) ? "-" : pembeli.get(5)) + "\n\n"
                        + "Status Transaksi                           : " + pembeli.get(4) + "\n\n"
                        + "Tanggal Pembayaran/Pelunasan  : " + ((pembeli.get(6).isEmpty()) ? "-" : pembeli.get(6)) + "\n\n"
                        + "Diskon                                           : " + pembeli.get(7) + "%" + "\n\n"
                        + "Biaya Transaksi                             : " + nv.fieldChecked(pembeli.get(8)) + "\n\n";

                totalTransaksi += Integer.parseInt(pembeli.get(8));

                Paragraph x = new Paragraph(dataPembeli, FontFactory.getFont(
                        FontFactory.HELVETICA, 7, Font.NORMAL, Color.BLACK));
                x.setAlignment(Element.ALIGN_LEFT);

                SellerDetail edit = new SellerDetail(x, Color.WHITE, Color.BLACK);
                paint.addCell(edit);

                //edit.set
                document.add(paint);

                PdfPTable table = new PdfPTable(colNames.length);
                int[] is = new int[colNames.length];
                for (int i = 0; i < colNames.length; i++) {
                    is[i] = colNames[i].length() + 3;
                }

                table.setWidthPercentage(100f);
                table.setWidths(is);
                for (String name : colNames) {
                    CellEditting editting = new CellEditting(name, new Color(254, 137, 0), Color.white, FontFactory.HELVETICA);
                    //editting.set
                    table.addCell(editting);
                }

                ArrayList<ArrayList<String>> tempData = (ArrayList<ArrayList<String>>) object;
                int i = 1;
                
                
                for (ArrayList<String> arrayList : tempData) {
                    totalModal += Integer.parseInt(arrayList.get(3)) * getHargaModal(arrayList.get(0));
                    if (!auto) {
                        CellEditting editting = new CellEditting(String.valueOf(i), Color.WHITE, Color.BLACK, FontFactory.HELVETICA);
                        table.addCell(editting);
                    }

                    int money = 0;
                    for (String string : arrayList) {
                        if (money == 4) {
                            string = nv.fieldChecked(string);
                        }

                        CellEditting editting = new CellEditting(string, Color.WHITE, Color.black, FontFactory.HELVETICA);
                        table.addCell(editting);
                        money++;
                    }
                    i++;
                }

                document.add(table);

                //if (index < paraPembeli.length - 1) {
                document.add(new Paragraph(" "));
                document.add(new Paragraph(" "));
                //}

                index++;
            }
            //document.add(new Paragraph());
            
            

            int untung = totalTransaksi - totalModal;
            p = new Paragraph(""
                    + "\nTotal Biaya Semua Transaksi                        :  " + nv.fieldChecked(String.valueOf(totalTransaksi)) +"\n\n"
                    + "Total Modal Untuk Semua Transaksi ini       : " + nv.fieldChecked(String.valueOf(totalModal)) + "\n\n"
                    + "Laba Untuk Semua Transaksi ini                   : " + ((untung > 0) ? nv.fieldChecked(String.valueOf(untung)) : "Rp 0") + "\n\n"
                    + "Rugi Untuk Semua Transaksi ini                   : " + ((untung > 0) ? "Rp 0" : nv.fieldChecked(String.valueOf(untung * -1))) + "\n\n"
                    ,
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD, 5, Font.NORMAL, Color.BLACK)
            );
            p.setAlignment(Element.ALIGN_RIGHT);

            PdfPTable paint = new PdfPTable(1);

            paint.setWidthPercentage(100f);
            paint.setWidths(new float[]{100f});
            
            //Paragraph x = new Paragraph(p, FontFactory.getFont(
              //      FontFactory.HELVETICA, 7, Font.NORMAL, Color.BLACK));
            //x.setAlignment(Element.ALIGN_LEFT);

            //PdfPCell
            SellerDetail edit = new SellerDetail(p, Color.WHITE, Color.BLACK);
            paint.addCell(edit);
            
            document.add(paint);
            //document.add(p.toString());

            //document.add(p);
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
        paragraph = new Paragraph("JJln. Doktor Sam Ratulangi, No 192, Kendari Sulawesi Tenggara",
                FontFactory.getFont(
                        FontFactory.HELVETICA_BOLD, 7, Font.NORMAL, Color.BLACK)
        );
        document.add(paragraph);

        //kontak
        paragraph = new Paragraph("No. Telepon (0401) 325117  / Hp : ",
                FontFactory.getFont(
                        FontFactory.HELVETICA_BOLD, 7, Font.NORMAL, Color.BLACK)
        );
        document.add(paragraph);

    }

}
