/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir.view;

import annas.jdbc.service.configuration.DatabaseTableList;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.awt.AppContext;
import bundaran.kasir.MainClass;
import bundaran.kasir.TokoBangunanKasir;
import bundaran.kasir.backgroundtask.RequestVoidCallbackTask;
import bundaran.kasir.backgroundtask.ServerRequestController;
import bundaran.kasir.report.StruckReport;
import bundaran.kasir.util.MoveFrameListener;
import bundaran.kasir.util.NumberValidation;
import bundaran.kasir.util.OfflineTransactionSaver;
import bundaran.kasir.util.RefreshAllData;
import bundaran.kasir.util.RefreshContentData;
import bundaran.kasir.util.RewriteDatabase;
import bundaran.kasir.util.staticIdentifier;

/**
 *
 * @author HAMSTER-BIRU
 */
public final class PayUnLunas extends javax.swing.JDialog implements RequestVoidCallbackTask {

    ArrayList<String> dataPembeli;
    ArrayList<ArrayList<String>> dataBarang;
    Kasir instance;
    PayDetails detail;
    String backTaskID;

    RefreshContentData rcd;
    /**
     * index data barang yang sedang diproses
     */
    public int dataBarangIndex = 0;

    public PayUnLunas(Kasir instance, PayDetails detail, ArrayList<String> dataPembeli,
            ArrayList<ArrayList<String>> dataBarang) {
        super((Frame) null, true);

        initComponents();
        setLocationRelativeTo(null);

        this.dataPembeli = dataPembeli;
        this.dataBarang = dataBarang;
        this.instance = instance;
        this.detail = detail;

        setData();
    }

    void setData() {

        namaPembeli.setText(dataPembeli.get(2));
        kontak.setText(dataPembeli.get(3));

        NumberValidation nv = new NumberValidation(",", "Rp ");
        totalCost.setText(nv.fieldChecked(dataPembeli.get(8)));
        jatuhTempo.setText(dataPembeli.get(5));

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
        panelDetail = new javax.swing.JPanel();
        labelBarcode = new javax.swing.JLabel();
        labelBarcodeName = new javax.swing.JLabel();
        xxx = new javax.swing.JLabel();
        barcode1 = new javax.swing.JLabel();
        barcode2 = new javax.swing.JLabel();
        barcode4 = new javax.swing.JLabel();
        labelBarcodeName3 = new javax.swing.JLabel();
        barcode5 = new javax.swing.JLabel();
        totalCost = new javax.swing.JTextField();
        kontak = new javax.swing.JLabel();
        namaPembeli = new javax.swing.JLabel();
        jatuhTempo = new javax.swing.JLabel();
        labelBarcodeName4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

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
        progressText1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/kasir/res/logo14.PNG"))); // NOI18N
        progressText1.setText("   Data Pembeli");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(progressText1)
                .addContainerGap(337, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressText1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDetail.setBackground(new java.awt.Color(255, 255, 255));
        panelDetail.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelBarcode.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcode.setText("Nama Pembeli");

        labelBarcodeName.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcodeName.setText("Kontak");

        xxx.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        xxx.setText("Total Harga");

        barcode1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        barcode1.setText(":");

        barcode2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        barcode2.setText(":");

        barcode4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        barcode4.setText(":");

        labelBarcodeName3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcodeName3.setText("Jatuh Tempo");

        barcode5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        barcode5.setText(":");

        totalCost.setEditable(false);
        totalCost.setBackground(new java.awt.Color(255, 255, 255));
        totalCost.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        totalCost.setForeground(new java.awt.Color(204, 0, 0));
        totalCost.setFocusable(false);
        totalCost.setMargin(new java.awt.Insets(2, 10, 2, 10));
        totalCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalCostActionPerformed(evt);
            }
        });
        totalCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                totalCostKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalCostKeyReleased(evt);
            }
        });

        kontak.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        kontak.setText("085342598141");

        namaPembeli.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        namaPembeli.setText("Annas Dan");

        jatuhTempo.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jatuhTempo.setText("11-11-2016");

        javax.swing.GroupLayout panelDetailLayout = new javax.swing.GroupLayout(panelDetail);
        panelDetail.setLayout(panelDetailLayout);
        panelDetailLayout.setHorizontalGroup(
            panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelBarcodeName)
                    .addComponent(xxx)
                    .addComponent(labelBarcodeName3)
                    .addComponent(labelBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelDetailLayout.createSequentialGroup()
                        .addComponent(barcode2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kontak, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                    .addGroup(panelDetailLayout.createSequentialGroup()
                        .addComponent(barcode1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namaPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                    .addGroup(panelDetailLayout.createSequentialGroup()
                        .addComponent(barcode4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalCost))
                    .addGroup(panelDetailLayout.createSequentialGroup()
                        .addComponent(barcode5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jatuhTempo, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelDetailLayout.setVerticalGroup(
            panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barcode1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namaPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(barcode2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kontak, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelBarcodeName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(xxx, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(barcode4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(totalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelBarcodeName3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(barcode5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jatuhTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        labelBarcodeName4.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcodeName4.setText("RINCIAN BELANJA");

        jButton1.setUI(new bundaran.kasir.util.WarningUI());
        jButton1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/kasir/res/close.png"))); // NOI18N
        jButton1.setText("TERIMA KASIH ATAS KUNJUNGAN ANDA     ");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jButton1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelBarcodeName4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelDetail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(labelBarcodeName4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void totalCostKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalCostKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            detail.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_totalCostKeyReleased

    private void totalCostKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalCostKeyPressed

    }//GEN-LAST:event_totalCostKeyPressed

    private void totalCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalCostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalCostActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        releaseAction();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
            detail.setVisible(true);
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            releaseAction();
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyReleased

    }//GEN-LAST:event_jButton1KeyReleased

    private void jButton1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyTyped


    }//GEN-LAST:event_jButton1KeyTyped

    private void releaseAction() {
        // remove swing worker tables instance
        AppContext appContext = AppContext.getAppContext();
        if (appContext != null) {
            appContext.remove(SwingWorker.class);
        }
        
        instance.newTrancsaction();
        detail.dispose();

        backTaskID = TokoBangunanKasir.getIDTask();

        dispose();

        if (!TokoBangunanKasir.OFFLINE_MODE) {
            saveIntoBackground();
        } else {
            OfflineTransactionSaver offlineTransactionSaver = new OfflineTransactionSaver(dataPembeli, dataBarang);
            offlineTransactionSaver.execute(0);
        }
    }

    synchronized void saveIntoBackground() {
        /**
         * simpan data barang di database utama/online
         */
        processDone(null, "SAVE_TRANSACTION_PRODUCT");

        createStruck("0");
    }

    private void createStruck(String payedBefore) {
        String[] colNames = new String[]{"Nama Barang", "Jenis", "Harga (Rp)", "Jumlah", "Total Harga (Rp)"};

        Object[] object = new Object[1];
        object[0] = dataBarang;

        Object[] pembeli = new Object[1];
        pembeli[0] = dataPembeli;

        String[] valve = new String[]{
            dataPembeli.get(8), // cost
            "0", // nlai bayar
            dataPembeli.get(7), // diskon
            "0"
        };

        MainClass detail
                = new MainClass(colNames, pembeli, object, instance.dataBarang, "DATA TRANSAKSI JATUH TEMPO", false, "data_struck.pdf");

        try {
            detail.doPrint(valve, payedBefore);
        } catch (Exception ex) {
            Logger.getLogger(FinalCost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ServerRequestController getRequestInstance(Object objectProgress, List<NameValuePair> pairs, String process, String message) {
        ServerRequestController controller = new ServerRequestController(backTaskID, this, objectProgress, process, pairs, message);
        return controller;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel barcode1;
    private javax.swing.JLabel barcode2;
    private javax.swing.JLabel barcode4;
    private javax.swing.JLabel barcode5;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jatuhTempo;
    private javax.swing.JLabel kontak;
    private javax.swing.JLabel labelBarcode;
    private javax.swing.JLabel labelBarcodeName;
    private javax.swing.JLabel labelBarcodeName3;
    private javax.swing.JLabel labelBarcodeName4;
    private javax.swing.JLabel namaPembeli;
    private javax.swing.JPanel panelDetail;
    private javax.swing.JLabel progressText1;
    private javax.swing.JTextField totalCost;
    private javax.swing.JLabel xxx;
    // End of variables declaration//GEN-END:variables

    @Override
    public void processDone(JSONObject jSONObject, String process) {

        switch (process) {
            case "SAVE_TRANSACTION_PRODUCT":
                if (dataBarangIndex > 0 && jSONObject == null) {
                    return;
                }

                if (dataBarangIndex > 0 && jSONObject.getString("message").equals("Terjadi Kesalahan ...")) {
                    return;
                }

                if ((dataBarang.size()) > dataBarangIndex) {
                    getRequestInstance(null, enchanceData(dataBarang.get(dataBarangIndex),
                            staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_PENJUALAN_BARANG),
                            "SAVE_TRANSACTION_PRODUCT", "").execute();
                    dataBarangIndex++;
                } else {
                    getRequestInstance(null, enchanceData(dataPembeli, staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_PENJUALAN),
                            "SAVE_TRANSACTION", "").execute();
                }
                break;
            case "SAVE_TRANSACTION":
                String result = jSONObject.getString("message");
                if (result.equals("Terjadi Kesalahan ...")) {
                    break;
                }

                RefreshAllData allData = new RefreshAllData(instance, backTaskID);
                allData.execute();
                break;

            default:
        }

    }

    @Override
    public void httpParsingError(String process, String message) {

    }

    /**
     *
     * @param data
     * @return
     */
    synchronized List<NameValuePair> enchanceData(ArrayList<String> data, String[] identifier) {
        List<NameValuePair> pairs = new ArrayList<>();

        int i = 0;
        for (String pair : identifier) {
            pairs.add(new BasicNameValuePair(pair, data.get(i)));
            i++;
        }

        return pairs;
    }

}