/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.print.PrintService;
import static bundaran.kasir.MainClass.dataGudangBarang;
import bundaran.kasir.util.NumberValidation;
import bundaran.kasir.view.Login;
import java.awt.BasicStroke;
import java.awt.Stroke;

/**
 *
 * @author AsyncTask.Void.087
 */
public class MainClass {
    
    public static  String[] colNames;
    public static  Object[] listData;
    public static  Object[] paraPembeli;
    //ArrayList<ArrayList<String>>

    public static ArrayList<ArrayList<String>> dataGudangBarang;
    String title;
    boolean auto;
    String fileNames;
    public static String[] valve;
    
    
    public MainClass(String[] colNames, Object[] paraPembeli, Object[] listData, ArrayList<ArrayList<String>> dataGudangBarang, String title, boolean auto, String fileNames) {
        MainClass.colNames = colNames;
        MainClass.listData = listData;
        this.title = title;
        this.auto = auto;
        this.fileNames = fileNames;
        MainClass.paraPembeli = paraPembeli;
        MainClass.dataGudangBarang = dataGudangBarang;

        if (!auto) {
            colNames = new String[colNames.length + 1];

            for (int i = 0; i < colNames.length; i++) {
                if (i == 0) {
                    colNames[i] = "No ";
                } else {
                    colNames[i] = MainClass.colNames[i - 1];
                }
            }

            this.colNames = colNames;
        }
    }


    public static PrintService findPrintService(String printerName) {
        for (PrintService service : PrinterJob.lookupPrintServices()) {
            if (service.getName().equalsIgnoreCase(printerName)) {
                return service;
            }
        }

        return null;
    }
    
    
    

    public static String payedBefore;
    public boolean doPrint(String[] valve, String payedBefore) throws Exception {
        MainClass.valve = valve;
        MainClass.payedBefore = payedBefore;
        PrinterJob pj = PrinterJob.getPrinterJob();

        PrintService myPrintService = findPrintService("Canon iP2700 series");
//        PrintService myPrintService = findPrintService("Canon MP230 series Printer");

        pj.setPrintService(myPrintService);
        PageFormat pf = new PageFormat();
        Paper paper = new Paper();
        //java.awt.print.Book book = new Book();
        //book.setPage(PAGE_EXISTS, prntbl, pf);
        
        ArrayList<ArrayList<String>> tempData = (ArrayList<ArrayList<String>>) MainClass.listData[0];
        int jumlahBelanjaan = tempData.get(0).size();
        //265
        double tg = 265 + (jumlahBelanjaan * 10);
        double LETTER_WIDTH = 8.5 * 72d;
        
        //paper.setSize(LETTER_WIDTH + 400, tg);
        
        //paper.setSize(tg, 100);
        
        //paper.setImageableArea(margin, margin, margin, margin);
        paper.setImageableArea(0, 0, 1000, tg);
        paper.setSize((1000 > tg)? 1000 : tg, tg);
        
        
        //pf.setOrientation(PageFormat.LANDSCAPE);
        pf.setPaper(paper);
        //pf.clone()        //PageFormat validatePage = pj.validatePage(pf);

        pj.setPrintable(new MyPrintable(), pf);
        //pj.
        //pj.
        //if (pj.printDialog()) {
        try {
            pj.print();
        } catch (PrinterException e) {
            System.out.println(e);
        }
        //}
        
        
        
        return true;
    }
}

class MyPrintable implements Printable {
    
    
    int getHargaModal(String idBarcode) {

        for (ArrayList<String> colName : MainClass.dataGudangBarang) {
            if (colName.get(0).equals(idBarcode)) {
                return Integer.parseInt(colName.get(2));
            }
        }

        return 0;
    }

    private String getSomeDetailFromProduct(String id, int indexReturn) {
        for (ArrayList<String> colName : MainClass.dataGudangBarang) {
            if (colName.get(0).equals(id)) {
                return colName.get(indexReturn);
            }
        }

        return null;
    }

    /**
     * Keterangan pembayaran
     */
    String[] identifierPembayaran = new String[]{
        "Total ",
        "Bayar ",
        "Kembalian "
       
    };
    
    

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        
        NumberValidation nv = new NumberValidation(",");
        
        if (pageIndex != 0) {
            return NO_SUCH_PAGE;
        }
        
        
        Graphics2D g2 = (Graphics2D) g;
        //Font.
        g2.setPaint(Color.black);
        g2.setFont(new Font("SansSerif", Font.BOLD, 12));

        int left = 10;

        // COP
        g2.drawString("BUNDARAN ELEKTRONIK KENDARI", left+10, 50);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 10));

        ArrayList<String> pembeli = (ArrayList<String>) MainClass.paraPembeli[0];
        //spasi
        //g2.drawString(" ", left, 83);

        //Status
//        g2.drawString("Status Transaksi : " + pembeli.get(2), left + 420, 50);
//
//        //spasi
//        g2.drawString(" ", left, 105);
//
//        
        // Garis
        g2.drawLine(left, 60, 570, 60);
        
        
        // detail informasi
        g2.drawString("ID Transaksi", left + 10, 80);
        /*Value*/ g2.drawString(": " + pembeli.get(0), 130, 80);
        g2.drawString("Tanggal Transaksi", left + 10, 95);
        Date tgl_t = new Date();
        
        String tgl_trans = new SimpleDateFormat("dd/MM/yyyy").format(tgl_t);

        g2.drawString(": " + tgl_trans, 130, 95);
//        g2.drawString("Nama Pembeli", left + 10, 108);
//        g2.drawString(": " + ((pembeli.get(2).isEmpty()) ? "-" : pembeli.get(2)), 150, 108);
//        g2.drawString("Alamat Pembeli", left + 10, 119);
//        g2.drawString(": " + ((pembeli.get(2).isEmpty()) ? "-" : pembeli.get(2)), 150, 119);
//        
//        
//        g2.drawString("Kontak Pembeli", left + 10 + (280), 86);
//        g2.drawString(": " + ((pembeli.get(3).isEmpty()) ? "-" : pembeli.get(3)), left + 10 + (300) + 130, 86);
//        g2.drawString("Jatuh Tempo", left + 10 + (280), 97);
//        g2.drawString(": " + ((pembeli.get(2).isEmpty()) ? "-" : pembeli.get(2)), left + 10 + (300) + 130, 97);
//        g2.drawString("Tanggal Pembayaran/ Pelunasan", left + 10 + (280), 108);
//        g2.drawString(": " + ((pembeli.get(2).isEmpty()) ? "-" : pembeli.get(2)), left + 10 + (300) + 130, 108);

        //g2.drawString("Daftar Barang Transaksi :", left, 246);

        int[] columnWiths = {
            33, // no
            180, // nama barang
            50,// Jumlah
            110,// harga
            100,// Diskon
            130// total
        };
        
        
        String[] tittless = {
            "No", // no
            "Nama Barang", // nama barang
            "Qty",// jenis
            "Harga",// harga
            "Disc.",// diskon
            "Jumlah"// total
        };

        int startY = 110;

        int t = 0;
        int ifi = 0;
        for (String str : tittless) {
//            g2.drawRect(left + t, startY, columnWiths[ifi], 17);

            g2.drawString(str, (left + 10) + t, startY + 12);
            t += columnWiths[ifi];
            ifi++;
        }
        startY += 18;
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
        g2.setStroke(dashed);
        g2.drawLine(left, startY, 570, startY);

        
        ArrayList<ArrayList<String>> tempData = (ArrayList<ArrayList<String>>) MainClass.listData[0];
        //** ISi data barang
        
        int d = 0;
        for (ArrayList<String> dataBarangnya : tempData) { //ini bagian mau tampilkan barang yang dibeli

            int k = 0;
            int naik = 0;
            
            String barcodeCode = dataBarangnya.get(0);
//               System.out.println(getSomeDetailFromProduct(barcodeCode, 1)); 
//               System.out.println(getSomeDetailFromProduct(barcodeCode, 2)); 
//               System.out.println(getSomeDetailFromProduct(barcodeCode, 3)); 
//               System.out.println(getSomeDetailFromProduct(barcodeCode, 4)); 
//               System.out.println(getSomeDetailFromProduct(barcodeCode, 5)); 
//               System.out.println(getSomeDetailFromProduct(barcodeCode, 6)); 
//               System.out.println(getSomeDetailFromProduct(barcodeCode, 7)); 
//               System.out.println(getSomeDetailFromProduct(barcodeCode, 8)); 
//               System.out.println();
//               System.out.println();
//               System.out.println();
//                       
//               System.out.println(dataBarangnya.get(0));
//               System.out.println(dataBarangnya.get(1));
//               System.out.println(dataBarangnya.get(2));
//               System.out.println(dataBarangnya.get(3));
//               System.out.println(dataBarangnya.get(4));
            
            for (int columnWith : columnWiths) {
               
               
                //g2.drawRect(left + k, startY, columnWith, 25);

                if (naik == 0) {
                    g2.drawString(String.valueOf(d + 1), (left + 10) + k, startY + 15);
                } else if (naik == 1) {
                    g2.drawString(getSomeDetailFromProduct(barcodeCode, 1), (left + 10) + k, startY + 15);
                } else if (naik == 2) {
                    g2.drawString(dataBarangnya.get(3), (left + 10) + k, startY + 15);
                } else if (naik == 3) {
                    g2.drawString(nv.fieldChecked(getSomeDetailFromProduct(barcodeCode, 3)), (left + 10) + k, startY + 15);
                } else if (naik == 4) {
                    g2.drawString(nv.fieldChecked(dataBarangnya.get(4)), (left + 10) + k, startY + 15);
                } else if (naik == 5) {
                    int harga = Integer.parseInt(getSomeDetailFromProduct(barcodeCode, 3));
                    int jumlah = Integer.parseInt(dataBarangnya.get(3));
                    int diskon = Integer.parseInt(dataBarangnya.get(4));
                    int hargaKunatitas = (harga * jumlah) - diskon;
                    
                    g2.drawString(nv.fieldChecked(String.valueOf(hargaKunatitas)), (left + 10) + k, startY + 15);
                }
                
                naik++;
                k += columnWith;
            }
            startY += 15;
            d++;
        }
        float[] dash4 = { 4f, 4f, 1f };

    
        
        g2.setStroke(dashed);
        g2.drawLine(left, startY+10, 570, startY+10);

        // ketereangan lebih lanjut
        int cost = Integer.parseInt(MainClass.valve[0]);
            int pay = Integer.parseInt(MainClass.valve[1]);
            int rest = pay - cost;
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx");
        
            
            
            
            
//        String[] valuePembayaran = new String[]{
//                nv.fieldChecked(pembeli.get(4)),
//                MainClass.valve[1],
//                nv.fieldChecked(MainClass.valve[0]),
//                nv.fieldChecked(MainClass.valve[1]),
//                (MainClass.valve.length > 1) 
//                    ? nv.fieldChecked(MainClass.valve[1]) 
//                    : nv.fieldChecked(String.valueOf(rest * ((rest < 0) ? -1 : 1))),
//                nv.fieldChecked(MainClass.payedBefore),
//                (rest < 0) ? nv.fieldChecked("0") : nv.fieldChecked(String.valueOf(rest))
//            };
         String[] valuePembayaran = new String[]{
                nv.fieldChecked(String.valueOf(cost)),
                nv.fieldChecked(String.valueOf(pay)),
                nv.fieldChecked(String.valueOf(rest))};
        
        
        int kolom1W = columnWiths[0] + columnWiths[1] + columnWiths[2] + columnWiths[3] + columnWiths[4] ;
        int kolom2W = columnWiths[5];
        int kolom3W = columnWiths[0] + columnWiths[1] + columnWiths[2] + columnWiths[3] ;
        
        startY += 10;
        for (int i = 0; i < valuePembayaran.length; i++) {
            //int k = 0;
            if (i == 0 || i == (valuePembayaran.length - 1)) {
                
//                if (i != 0) {
//                    g2.drawRect(left, startY + 3, kolom1W, 20);                
//                    g2.drawRect(left + kolom1W, startY + 3, kolom2W, 20);                
//                } else {
//                    g2.drawRect(left, startY, kolom1W, 20);                
//                    g2.drawRect(left + kolom1W, startY, kolom2W, 20);                
//                }
                //k += kolom1W;
            }

            if (i == (valuePembayaran.length - 1)) {
                g2.drawString(identifierPembayaran[i], kolom3W+30, startY+10 + 12 + 2);
                g2.drawString(":", kolom1W, startY+10 + 12 + 2);
                g2.drawString(valuePembayaran[i], (left + 10) + kolom1W, startY+10 + 12 + 2);
            } else {
//                if (i == 1) {
//                    startY += 12;
//                }
                g2.drawString(identifierPembayaran[i], kolom3W+30, startY+10 + 12);
                g2.drawString(":", kolom1W, startY+10 + 12);
                g2.drawString(valuePembayaran[i], (left + 10) + kolom1W, startY+10 + 12);
            }
            startY += 12;
        }
//        
        
        
        
        
        
//        Date date = new Date();
//            String tgl = new SimpleDateFormat("dd/MM/yyyy").format(date);
//        startY += 25;
//        g2.drawString("Diterima Oleh KASIR", kolom1W , startY);
//        startY += 11;
//        g2.drawString(Login.USER_LOGGED + ", " + tgl, kolom1W , startY);
//        startY += 15;
        g2.drawString("*** Terima Kasih Atas Kunjungan Anda ***", left + 200 , startY+40);
        
        return PAGE_EXISTS;
    }
}
