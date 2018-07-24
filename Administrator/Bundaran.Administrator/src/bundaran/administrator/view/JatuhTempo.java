/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.view;

import annas.jdbc.service.configuration.MyTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import bundaran.administrator.report.DataPenjualanDetailReport;
import bundaran.administrator.util.MoveFrameListener;

/**
 *
 * @author HAMSTER-BIRU
 */
public final class JatuhTempo extends javax.swing.JDialog {

    ArrayList<ArrayList<String>> dataPembeli;
    ArrayList<ArrayList<String>> dataBarang;
    ArrayList<ArrayList<String>> jatuhTempo;
    String[] waktu;
    String today = "";
    String status;

    public JatuhTempo(ArrayList<ArrayList<String>> dataPembeli,
            ArrayList<ArrayList<String>> dataBarang, String status, String... waktu) {
        super((Frame) null, true);

        this.dataBarang = dataBarang;
        this.dataPembeli = dataPembeli;
        this.status = status;
        this.waktu = waktu;

        if (waktu != null) {
            for (String string : waktu) {
                if (!string.isEmpty()) {
                    int s = Integer.parseInt(string);
                    this.today += ((s < 10) ? ("0" + string) : string) + "-";
                }
                
            }
        }

        
        String filterString = "Pada " + ((!waktu[2].isEmpty()) ? "Tanggal " + waktu[2] : "")
                + ((!waktu[1].isEmpty()) ? " Bulan " + waktu[1] : "")
                + ((!waktu[0].isEmpty()) ? " Tahun " + waktu[0] : "");

        
        initComponents();
        setLocationRelativeTo(null);

        if (waktu[0].isEmpty() && waktu[1].isEmpty() && waktu[2].isEmpty()) {
        } else {
            jLabelHariIni.setText(filterString);
        }

        try {
            getDatas();
        } catch (RemoteException ex) {
            Logger.getLogger(JatuhTempo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void getDatas() throws RemoteException {

        jatuhTempo = new ArrayList<>();
        
        jatuhTempo = deadline(dataPembeli);
        jLabel2.setText("DITEMUKAN " + ((!jatuhTempo.isEmpty()) ? jatuhTempo.size() : 0) + " DATA TRANSAKSI JATUH TEMPO" + ((!status.isEmpty()) ? " yang " + status : ""));
        
        String[] colNames = new String[]{"ID transaksi", "Tanggal Transaksi", "Pembeli", "Kontak",
            "Status", "Jatuh Tempo", "Pembayaran", "Diskon", "Nilai Transaksi(Rp)", "Alamat"};

        MyTableModel model = new MyTableModel(colNames, jatuhTempo, false, true, new int[]{3}, ",");
        tableDataPembeli.setModel(model);

    }

    private ArrayList<ArrayList<String>> deadline(ArrayList<ArrayList<String>> data) {
        ArrayList<ArrayList<String>> deadline = new ArrayList<>();

        String today = today();

        if (data == null) {
            return deadline;
        }

        for (ArrayList<String> arrayList : data) {
            String tempo = arrayList.get(5 /*urutan tanggal jatuh tempo dalam database*/);

            if (!tempo.isEmpty()) {
                if (tempo.equals(today)) {
                    if (!status.isEmpty()) {
                        if (status.equals(arrayList.get(4))) {
                            deadline.add(arrayList);
                        }
                    } else {
                        deadline.add(arrayList);
                    }
                } else {
                    int tahunTempo = Integer.parseInt(tempo.substring(0, 4));
                    int bulanTempo = Integer.parseInt(tempo.substring(5, 7));
                    int hariTempo = Integer.parseInt(tempo.substring(8, 10));

                    int tahunToday = Integer.parseInt(today.substring(0, 4));
                    int bulanToday = (today.length() >= 7) ? Integer.parseInt(today.substring(5, 7)) : 13;
                    int hariToday = (today.length() >= 10) ? Integer.parseInt(today.substring(8, 10)) : 31;

                    if (hariTempo <= hariToday && bulanTempo <= bulanToday && tahunTempo <= tahunToday) {
                        if (!status.isEmpty()) {
                            if (status.equals(arrayList.get(4))) {
                                deadline.add(arrayList);
                            }
                        } else {
                            deadline.add(arrayList);
                        }
                    } else if (hariTempo > hariToday && bulanTempo < bulanToday && tahunTempo <= tahunToday) {
                        if (!status.isEmpty()) {
                            if (status.equals(arrayList.get(4))) {
                                deadline.add(arrayList);
                            }
                        } else {
                            deadline.add(arrayList);
                        }
                    } else if (hariTempo > hariToday && bulanTempo > bulanToday && tahunTempo < tahunToday) {
                        if (!status.isEmpty()) {
                            if (status.equals(arrayList.get(4))) {
                                deadline.add(arrayList);
                            }
                        } else {
                            deadline.add(arrayList);
                        }
                    } else if (hariTempo <= hariToday && bulanTempo > bulanToday && tahunTempo < tahunToday) {
                        if (!status.isEmpty()) {
                            if (status.equals(arrayList.get(4))) {
                                deadline.add(arrayList);
                            }
                        } else {
                            deadline.add(arrayList);
                        }
                    } else if (hariTempo <= hariToday && bulanTempo <= bulanToday && tahunTempo < tahunToday) {
                        if (!status.isEmpty()) {
                            if (status.equals(arrayList.get(4))) {
                                deadline.add(arrayList);
                            }
                        } else {
                            deadline.add(arrayList);
                        }
                    }
                }
            }
        }

        return deadline;
    }

    private String today() {
        return this.today;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        progressText1 = new javax.swing.JLabel();
        progressText7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDataPembeli = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        cetak = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabelHariIni = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(246, 246, 246));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 11)); // NOI18N

        MoveFrameListener frameListenerx = new MoveFrameListener(jPanel2);
        addMouseListener(frameListenerx);
        addMouseMotionListener(frameListenerx);
        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        progressText1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        progressText1.setForeground(new java.awt.Color(255, 255, 255));
        progressText1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/logo14.PNG"))); // NOI18N
        progressText1.setText("   Jatuh Tempo");

        progressText7.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        progressText7.setForeground(new java.awt.Color(255, 255, 255));
        progressText7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/close.png"))); // NOI18N
        progressText7.setToolTipText("Tutup");
        progressText7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                progressText7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(progressText1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 916, Short.MAX_VALUE)
                .addComponent(progressText7)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(progressText7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(progressText1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addContainerGap())
        );

        tableDataPembeli.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        tableDataPembeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDataPembeli.setRowHeight(30);
        tableDataPembeli.getTableHeader().setReorderingAllowed(false);
        tableDataPembeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDataPembeliMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableDataPembeliMousePressed(evt);
            }
        });
        tableDataPembeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableDataPembeliKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableDataPembeli);
        tableDataPembeli.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    c.setBackground(new Color(0xff8a00));
                } else {
                    c.setBackground(new Color(0xffffff));
                }

                return c;
            }

        });

        jLabel13.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        jLabel13.setText("Toko Bundaran Elektronik, Kendari, Sulawesi Tenggara");

        cetak.setUI(new bundaran.administrator.util.WarningUI());
        cetak.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/print16.PNG"))); // NOI18N
        cetak.setText("Cetak   ");
        cetak.setFocusable(false);
        cetak.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });
        cetak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cetakKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel2.setText("DATA-DATA TRANSAKSI JATUH TEMPO");

        jLabelHariIni.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        jLabelHariIni.setForeground(new java.awt.Color(204, 0, 0));
        jLabelHariIni.setText("Hari ini, 2016-07-19");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4)
                        .addComponent(jLabelHariIni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabelHariIni))
                            .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel13)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableDataPembeliMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDataPembeliMousePressed


    }//GEN-LAST:event_tableDataPembeliMousePressed

    private void tableDataPembeliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDataPembeliKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                int row = tableDataPembeli.getSelectedRow();

                findTransaction(jatuhTempo.get(row).get(0)); // 0 untuk urutan ID transaksi
            } catch (Exception e) {
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        }


    }//GEN-LAST:event_tableDataPembeliKeyPressed

    private void progressText7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_progressText7MouseClicked

        dispose();
    }//GEN-LAST:event_progressText7MouseClicked

    private ArrayList<ArrayList<String>> dataBarangBy(String id) {
        ArrayList<ArrayList<String>> list = new ArrayList<>();

        for (ArrayList<String> arrayList : dataBarang) {
            if (arrayList.get(1).equals(id)) {
                list.add(arrayList);
            }
        }

        return list;
    }


    private void tableDataPembeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDataPembeliMouseClicked

        try {
            int row = tableDataPembeli.getSelectedRow();
            if (evt.getClickCount() == 2) {
                findTransaction(jatuhTempo.get(row).get(0)); // 0 untuk urutan ID transaksi
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_tableDataPembeliMouseClicked

    private void cetakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cetakKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        }
    }//GEN-LAST:event_cetakKeyPressed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed

        String[] colNames = new String[]{"Nomor Barcode", "Nama Barang", "Jenis Pembelian", "Jumlah", "Total Harga (Rp)"};
        Object[] paraPembeli = new Object[jatuhTempo.size()];
        Object[] paraDataPembeli = new Object[jatuhTempo.size()];

        int i = 0;
        for (ArrayList<String> object : jatuhTempo) {
            paraPembeli[i] = object;
            paraDataPembeli[i] = getDatabarang(object.get(0));
            i++;
        }

        String y = (waktu.length >= 3) ? waktu[0] : "";
        String m = (waktu.length >= 3) ? waktu[1] : "";
        String d = (waktu.length >= 3) ? waktu[2] : "";

        DataPenjualanDetailReport detail = new DataPenjualanDetailReport(colNames, paraPembeli, paraDataPembeli, DataPenjualanMain.dataBarangOriginal,
                "DATA TRANSAKSI JATUH TEMPO", false, "data_semua_jatuh_tempo_detail.pdf");
        if (detail.doCreateReport(d, m, y, status)) {
            File file = new File("data_semua_jatuh_tempo_detail.pdf");
            if (file.exists()) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                    Logger.getLogger(JatuhTempo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }//GEN-LAST:event_cetakActionPerformed

    private ArrayList<ArrayList<String>> getDatabarang(String id) {

        ArrayList<ArrayList<String>> r = new ArrayList<>();

        for (ArrayList<String> t : dataBarang) {
            ArrayList<String> temp = new ArrayList<>();
            if (t.get(1).toLowerCase().equals(id.toLowerCase())) {
                temp.add(t.get(0));
                int harga = 0;
                for (ArrayList<String> t2 : DataPenjualanMain.dataBarangOriginal) {
                    if (t.get(0).equals(t2.get(0))) {
                        harga = Integer.parseInt(t2.get(3));
                        temp.add(t2.get(1));
                        break;
                    }
                }
                temp.add(t.get(3));
                temp.add(t.get(4));

                int total = Integer.parseInt(t.get(4)) * harga;
                temp.add(String.valueOf(total));

                r.add(temp);
            }
        }

        return r;
    }

    private void findTransaction(String id) {
        if (dataPembeli == null) {
            return;
        }

        String y = (waktu.length >= 3) ? waktu[0] : "";
        String m = (waktu.length >= 3) ? waktu[1] : "";
        String D = (waktu.length >= 3) ? waktu[2] : "";

        for (ArrayList<String> dt : dataPembeli) {
            if (dt.get(0).toLowerCase().equals(id.toLowerCase())) {
                //dispose();
                ArrayList<ArrayList<String>> d = getDatabarang(id);

                DataPenjualanDetail dpd = new DataPenjualanDetail(dt, d, y, m, D);
                dpd.setVisible(true);
                //new JathTempoDetail(kasir, dt, d).setVisible(true);
                //new HasilCari(kasir, dt, d, dBarangOri).setVisible(true);
                return;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cetak;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelHariIni;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel progressText1;
    private javax.swing.JLabel progressText7;
    private javax.swing.JTable tableDataPembeli;
    // End of variables declaration//GEN-END:variables
}
