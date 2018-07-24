/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir.view;

//import tokobangunan.kasir.backgroundtask.;
import annas.jdbc.service.configuration.MyTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import bundaran.kasir.util.MoveFrameListener;
import bundaran.kasir.util.NumberValidation;

/**
 *
 * @author dan
 */
public final class TransaksiTundaDetail extends javax.swing.JDialog {

    ArrayList<String> dataPembeli;
    ArrayList<ArrayList<String>> dataBarang;
    //ArrayList<ArrayList<String>> dataBarangOriginal;
    String totalHarga;
    Kasir kasir;
    
    
    public TransaksiTundaDetail(Kasir kasir, ArrayList<String> dataPembeli, ArrayList<ArrayList<String>> dataBarang) {
        
        super((Frame) null, true);
        this.kasir = kasir;
        this.dataBarang = dataBarang;
        //this.dataBarangOriginal = dataBarangOriginal;
        this.dataPembeli = dataPembeli;
        
        initComponents();
        setLocationRelativeTo(null);
        
        setToTable();
        setdata();
        discount.requestFocus();
    }
    
    
    void setToTable(){
        String[] colNames = new String[]{"Nomor Barcode", "Nama Barang", "Jenis Pembelian", "Jumlah", "Total Harga (Rp)"};
        MyTableModel model = new MyTableModel(colNames, dataBarang, false, true, new int[]{0}, ",");
        tabelBarang.setModel(model);
        tabelBarang.setRowSelectionAllowed(false);
        
    }
    
    
    
    
    synchronized void setdata(){
        labelId.setText(":  " + dataPembeli.get(0));
        labelNamaPembeli.setText(":  " + dataPembeli.get(2));
        labelKkontak.setText(":  " + dataPembeli.get(3));
        labelAlamat.setText(":  " + dataPembeli.get(9));
        labelTglTransaksi.setText(":  " + dataPembeli.get(1));
        labelJatuhTempo.setText(":  " + dataPembeli.get(5));
        discount.setText(dataPembeli.get(7));
        
        NumberValidation nv = new NumberValidation(",", "Rp ");
        costTotal.setText(nv.fieldChecked(dataPembeli.get(8)));
        
        totalHarga = costTotal.getText();
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
        jPanel2 = new javax.swing.JPanel();
        progressText = new javax.swing.JLabel();
        progressText7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        discount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();
        labelBarcodeName10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelNamaPembeli = new javax.swing.JLabel();
        labelKkontak = new javax.swing.JLabel();
        labelBarcodeName4 = new javax.swing.JLabel();
        labelAlamat = new javax.swing.JLabel();
        labelBarcodeName5 = new javax.swing.JLabel();
        labelTglTransaksi = new javax.swing.JLabel();
        labelBarcodeName6 = new javax.swing.JLabel();
        labelJatuhTempo = new javax.swing.JLabel();
        labelBarcodeName7 = new javax.swing.JLabel();
        labelBarcodeName8 = new javax.swing.JLabel();
        labelId = new javax.swing.JLabel();
        labelBarcodeName3 = new javax.swing.JLabel();
        labelBarcodeName11 = new javax.swing.JLabel();
        labelBarcodeName12 = new javax.swing.JLabel();
        costTotal = new javax.swing.JLabel();
        labelBarcodeName13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(246, 246, 246));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        MoveFrameListener frameListener = new MoveFrameListener(jPanel2);
        addMouseListener(frameListener);
        addMouseMotionListener(frameListener);
        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        progressText.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        progressText.setForeground(new java.awt.Color(255, 255, 255));
        progressText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/kasir/res/logo14.PNG"))); // NOI18N
        progressText.setText("   Rincian ");

        progressText7.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        progressText7.setForeground(new java.awt.Color(255, 255, 255));
        progressText7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/kasir/res/close.png"))); // NOI18N
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
                .addGap(27, 27, 27)
                .addComponent(progressText, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progressText7)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(progressText7)
                    .addComponent(progressText))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        jLabel13.setText("Toko Bundaran Elektronik, Kendari, Sulawesi Tenggara");

        discount.setEditable(false);
        discount.setBackground(new java.awt.Color(255, 255, 255));
        discount.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        discount.setMargin(new java.awt.Insets(2, 10, 2, 10));
        discount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                discountKeyPressed(evt);
            }
        });

        tabelBarang.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelBarang.setRowHeight(32);
        tabelBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelBarangKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBarang);
        tabelBarang.setDefaultRenderer(Object.class, new TableCellRenderer() {
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

        labelBarcodeName10.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcodeName10.setText("DATA BARANG");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelNamaPembeli.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelNamaPembeli.setText("ID Transaksi");

        labelKkontak.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelKkontak.setText("ID Transaksi");

        labelBarcodeName4.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcodeName4.setText("Nama Pembeli");

        labelAlamat.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelAlamat.setText("ID Transaksi");

        labelBarcodeName5.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcodeName5.setText("Kontak");

        labelTglTransaksi.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelTglTransaksi.setText("ID Transaksi");

        labelBarcodeName6.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcodeName6.setText("Alamat");

        labelJatuhTempo.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelJatuhTempo.setText("ID Transaksi");

        labelBarcodeName7.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcodeName7.setText("Tangal Transaksi");

        labelBarcodeName8.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcodeName8.setText("Jatuh Tempo");

        labelId.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelId.setText("ID Transaksi");

        labelBarcodeName3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcodeName3.setText("ID Transaksi");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelBarcodeName3)
                    .addComponent(labelBarcodeName4)
                    .addComponent(labelBarcodeName5)
                    .addComponent(labelBarcodeName6)
                    .addComponent(labelBarcodeName7)
                    .addComponent(labelBarcodeName8))
                .addGap(94, 94, 94)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelId, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addComponent(labelNamaPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelKkontak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTglTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelJatuhTempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBarcodeName3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBarcodeName4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNamaPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBarcodeName5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelKkontak, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBarcodeName6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBarcodeName7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTglTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBarcodeName8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelJatuhTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelBarcodeName11.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcodeName11.setForeground(new java.awt.Color(102, 0, 102));
        labelBarcodeName11.setText("DATA TRANSAKSI");

        labelBarcodeName12.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcodeName12.setText("Total Harga Barang    :");

        costTotal.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        costTotal.setForeground(new java.awt.Color(255, 0, 0));
        costTotal.setText("Rp ");

        labelBarcodeName13.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        labelBarcodeName13.setText("Diskon               :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelBarcodeName10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelBarcodeName11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelBarcodeName13)
                        .addGap(18, 18, 18)
                        .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(labelBarcodeName12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(costTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelBarcodeName11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelBarcodeName10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelBarcodeName12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(costTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelBarcodeName13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void progressText7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_progressText7MouseClicked
        dispose();
    }//GEN-LAST:event_progressText7MouseClicked

    private void discountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discountKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dispose();
        }
    }//GEN-LAST:event_discountKeyPressed

    private void tabelBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelBarangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dispose();
        }
    }//GEN-LAST:event_tabelBarangKeyPressed

    
    
/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel costTotal;
    private javax.swing.JTextField discount;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAlamat;
    private javax.swing.JLabel labelBarcodeName10;
    private javax.swing.JLabel labelBarcodeName11;
    private javax.swing.JLabel labelBarcodeName12;
    private javax.swing.JLabel labelBarcodeName13;
    private javax.swing.JLabel labelBarcodeName3;
    private javax.swing.JLabel labelBarcodeName4;
    private javax.swing.JLabel labelBarcodeName5;
    private javax.swing.JLabel labelBarcodeName6;
    private javax.swing.JLabel labelBarcodeName7;
    private javax.swing.JLabel labelBarcodeName8;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelJatuhTempo;
    private javax.swing.JLabel labelKkontak;
    private javax.swing.JLabel labelNamaPembeli;
    private javax.swing.JLabel labelTglTransaksi;
    private javax.swing.JLabel progressText;
    private javax.swing.JLabel progressText7;
    public javax.swing.JTable tabelBarang;
    // End of variables declaration//GEN-END:variables
}
