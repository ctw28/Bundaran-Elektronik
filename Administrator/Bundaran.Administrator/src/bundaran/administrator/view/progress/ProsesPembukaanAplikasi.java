/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.view.progress;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import bundaran.administrator.backgroundtask.StartDatabaseBackgroundController;
import bundaran.administrator.util.ImageToPanel;
import bundaran.administrator.util.MoveFrameListener;

/**
 *
 * @author dan
 */
public class ProsesPembukaanAplikasi extends javax.swing.JDialog {

    /**
     * Creates new form TsProgress
     * @param parent
     */
    public ProsesPembukaanAplikasi(java.awt.Frame parent) {
        
        /**
         * Harus False
         */
        super(parent, false);
        initComponents();
        setLocationRelativeTo(null);
        
        progressView.setIndeterminate(true);
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
        progressView = new javax.swing.JProgressBar();
        progressText = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        progressText1 = new javax.swing.JLabel();
        progressText2 = new javax.swing.JLabel();
        progressText3 = new javax.swing.JLabel();
        progressText4 = new javax.swing.JLabel();
        progressText5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        try {
            URL url = getClass().getResource("/tokobangunan/administrator/res/wal1.jpg");
            BufferedImage image = ImageIO.read(url);
            jPanel1.setUI(new ImageToPanel(image, jPanel1));
        } catch (Exception x) {
        }

        progressText.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        progressText.setText("Progress Text");

        jButton1.setUI(new bundaran.administrator.util.WarningUI());
        jButton1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/close.png"))); // NOI18N
        jButton1.setText("Batalkan   ");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        MoveFrameListener frameListener = new MoveFrameListener(jPanel2);
        addMouseListener(frameListener);
        addMouseMotionListener(frameListener);
        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        progressText1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        progressText1.setForeground(new java.awt.Color(255, 255, 255));
        progressText1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/logo14.PNG"))); // NOI18N
        progressText1.setText("   Memulai Aplikasi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(progressText1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressText1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        progressText2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        progressText2.setText("Aplikasi Pendataan Barang Toko Bundaran Elektronik");

        progressText3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        progressText3.setText("Merupakan aplikasi yang digunakan untuk melakukan pendataan data barang,");

        progressText4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        progressText4.setText("pembuatan laporang dari barang masuk dan keluar, serta pembuatan laporan");

        progressText5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        progressText5.setText("dari barang yang terjual.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(progressText4)
                            .addComponent(progressText3)
                            .addComponent(progressText5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(progressText)
                                    .addComponent(progressView, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(progressText2))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(progressText2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressText3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressText4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressText5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(progressText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progressView, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        StartDatabaseBackgroundController.PROCESS_CLOSED_BY_USER = true;
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel progressText;
    private javax.swing.JLabel progressText1;
    private javax.swing.JLabel progressText2;
    private javax.swing.JLabel progressText3;
    private javax.swing.JLabel progressText4;
    private javax.swing.JLabel progressText5;
    private javax.swing.JProgressBar progressView;
    // End of variables declaration//GEN-END:variables
}
