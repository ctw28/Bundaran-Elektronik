/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.view;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import sun.awt.AppContext;
import bundaran.administrator.TokoBangunanAdministrator;
import bundaran.administrator.backgroundtask.RequestVoidCallbackTask;
import bundaran.administrator.backgroundtask.ServerRequestController;
import bundaran.administrator.internet.entity.BackupEnt;
import bundaran.administrator.internet.impl.BackupImpl;
import bundaran.administrator.util.MoveFrameListener;
import bundaran.administrator.view.progress.RequestProgress;

/**
 *
 * @author AsyncTask.Void.087
 */
public class BackupKontrol extends javax.swing.JDialog implements RequestVoidCallbackTask {

    String fileRestore = "";
    String fileBackup = "";
    private Map<String, ArrayList<ArrayList<String>>> mapRestoreData = new HashMap<>();
    private final String[] backupTableName = {
        "3rd_administrator",
        "1st_data_barang",
        "5th_barang_keluar",
        "2nd_data_penjualan",
        "4th_data_penjualan_barang",
        "6th_suplier"
    };

    public BackupKontrol() {
        super((Frame) null, true);
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        restoreChooser = new javax.swing.JFileChooser();
        backupChooser = new javax.swing.JFileChooser();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        progressText4 = new javax.swing.JLabel();
        progressText10 = new javax.swing.JLabel();
        progressText5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        progressText6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        backupPath = new javax.swing.JTextField();
        restorePath = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        progressText7 = new javax.swing.JLabel();
        progressText8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        restoreChooser.setFileFilter(new FileNameExtensionFilter("File Toko Duta Bangunan (*.dt)","dt"));
        restoreChooser.setCurrentDirectory(new java.io.File("C:\\"));
            restoreChooser.setDialogTitle("Pilih Data Restore");

            backupChooser.setFileFilter(new FileNameExtensionFilter("File Toko Duta Bangunan (*.dt)","dt"));
            backupChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
            backupChooser.setCurrentDirectory(new java.io.File("C:\\"));
                backupChooser.setDialogTitle("Pilih Data Restore");
                backupChooser.setApproveButtonText("Simpan");

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setUndecorated(true);

                jPanel7.setBackground(new java.awt.Color(246, 246, 246));
                jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
                /*try {
                    URL url = getClass().getResource("/tokobangunan/administrator/res/wal1.jpg");
                    BufferedImage image = ImageIO.read(url);
                    jPanel1.setUI(new ImageToPanel(image, jPanel1));
                } catch (Exception x) {
                }*/

                jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 11)); // NOI18N

                MoveFrameListener frameListenerx = new MoveFrameListener(jPanel8);
                addMouseListener(frameListenerx);
                addMouseMotionListener(frameListenerx);
                jPanel8.setBackground(new java.awt.Color(0, 153, 153));

                progressText4.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
                progressText4.setForeground(new java.awt.Color(255, 255, 255));
                progressText4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/logo14.PNG"))); // NOI18N
                progressText4.setText("   Backup Data");

                progressText10.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
                progressText10.setForeground(new java.awt.Color(255, 255, 255));
                progressText10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/close.png"))); // NOI18N
                progressText10.setToolTipText("Tutup");
                progressText10.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        progressText10MouseClicked(evt);
                    }
                });

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(progressText4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(progressText10)
                        .addGap(19, 19, 19))
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(progressText10)
                            .addComponent(progressText4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                progressText5.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
                progressText5.setText("-    Backup Data  : ");

                jButton1.setUI(new bundaran.administrator.util.WarningUI());
                jButton1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
                jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/offline.PNG"))); // NOI18N
                jButton1.setText("BACKUP DATA   ");
                jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton1ActionPerformed(evt);
                    }
                });

                progressText6.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
                progressText6.setText("-    Restore Data  : ");

                jButton3.setUI(new bundaran.administrator.util.WarningUI());
                jButton3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
                jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/online.PNG"))); // NOI18N
                jButton3.setText("RESTORE DATA   ");
                jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                jButton3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton3ActionPerformed(evt);
                    }
                });

                backupPath.setEditable(false);
                backupPath.setBackground(new java.awt.Color(255, 255, 255));
                backupPath.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
                backupPath.setForeground(new java.awt.Color(102, 102, 102));
                backupPath.setMargin(new java.awt.Insets(2, 10, 2, 10));

                restorePath.setEditable(false);
                restorePath.setBackground(new java.awt.Color(255, 255, 255));
                restorePath.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
                restorePath.setForeground(new java.awt.Color(102, 102, 102));
                restorePath.setMargin(new java.awt.Insets(2, 10, 2, 10));

                jButton4.setUI(new bundaran.administrator.util.WarningUI());
                jButton4.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
                jButton4.setText("...");
                jButton4.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton4ActionPerformed(evt);
                    }
                });

                jButton5.setUI(new bundaran.administrator.util.WarningUI());
                jButton5.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
                jButton5.setText("...");
                jButton5.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton5ActionPerformed(evt);
                    }
                });

                progressText7.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
                progressText7.setText("Tentukan dimana letak data file backup akan disimpan.");

                progressText8.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
                progressText8.setText("Cari dimana letak data file yang akan direstore.");

                jLabel14.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
                jLabel14.setText("Toko Bundaran Elektronik, Kendari, Sulawesi Tenggara");

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(progressText5)
                                    .addComponent(progressText6)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel14)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                    .addComponent(progressText8)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                    .addComponent(restorePath, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(progressText7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(backupPath, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(37, Short.MAX_VALUE))
                );
                jPanel7Layout.setVerticalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(progressText5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(backupPath, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(progressText7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(progressText6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(restorePath, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(progressText8))))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel14)
                        .addContainerGap(24, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    public void receiverBackupData(Map<String, ArrayList<ArrayList<String>>> map) {
        //int k = 0;
        if (fileBackup.isEmpty()) {
            return;
        }

        if (!map.isEmpty()) {
            try (PrintWriter writer = new PrintWriter(fileBackup, "UTF-8")) {

                int i = 0;
                for (String param : backupTableName) {
                    ArrayList<ArrayList<String>> r = map.get(param);
                    for (ArrayList<String> data : r) {
                        StringBuilder builder = new StringBuilder("{");
                        for (String string : data) {
                            builder.append("[").append(string).append("]");
                        }
                        builder.append("}");
                        writer.println(builder.toString());
                    }

                    i++;
                    if (i < backupTableName.length) {
                        writer.println("[" + param + "]");
                    } else {
                        writer.print("[" + param + "]");
                    }
                }

            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(BackupKontrol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        JOptionPane.showMessageDialog(null, (!map.isEmpty()) ? "Proses backup data berhasil" : "");
        fileBackup = "";
        backupPath.setText("");
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (fileBackup.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih lokasi file yang akan disimpan...  ", null, JOptionPane.WARNING_MESSAGE);
            return;
        }

        // remove swing worker tables instance
        AppContext appContext = AppContext.getAppContext();
        if (appContext != null) {
            appContext.remove(SwingWorker.class);
        }

        String backTaskIDx = TokoBangunanAdministrator.getIDTask();
        RequestProgress progressx = new RequestProgress(this, backTaskIDx);
        progressx.setVisible(true);

        List<NameValuePair> list = new ArrayList<>();
        BackupEnt ent = new BackupEnt(progressx, list, "SELECT_ALL_USER", "Sedang membackup data...", null);
        BackupImpl impl = new BackupImpl(backTaskIDx, this);
        impl.enchanceBackup(ent);
    }//GEN-LAST:event_jButton1ActionPerformed

    private boolean readInputFile() {
        if (fileRestore.isEmpty()) {
            return false;
        }

        try (BufferedReader in = new BufferedReader(new FileReader(fileRestore))) {

            ArrayList<ArrayList<String>> dataRestore = new ArrayList<>();
            String Start = "[";
            String End = "]";
            String tableContentStart = "{";
            String tableContentEnd = "}";
            String str;
            while ((str = in.readLine()) != null) {
                if (str.substring(0, 2).equals(tableContentStart + Start)
                        || str.substring(0, 1).equals(Start)) {
                } else {
                    JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada file...");
                    return false;
                }

                int strLength = str.length();
                if (!str.substring(0, 2).equals(tableContentStart + Start)) {
                    StringBuilder param = new StringBuilder("");
                    for (int i = 0; i < strLength; i++) {
                        String character = String.valueOf(str.charAt(i));
                        if (character.equals(End)) {
                            break;
                        } else if (character.equals(Start)) {
                            continue;
                        }

                        param.append(character);
                    }

                    mapRestoreData.put(param.toString(), dataRestore);
                    dataRestore = new ArrayList<>();
                } else {
                    ArrayList<String> content = new ArrayList<>();
                    StringBuilder value = new StringBuilder("");
                    for (int i = 0; i < strLength; i++) {
                        String character = String.valueOf(str.charAt(i));

                        if (character.equals(End)) {
                            content.add(value.toString());
                            value = new StringBuilder("");
                            continue;
                        } else if (character.equals(tableContentEnd)) {
                            break;
                        } else if (character.equals(tableContentStart) || character.equals(Start)) {
                            continue;
                        }

                        value.append(character);
                    }
                    dataRestore.add(content);
                }
                //System.out.println(str);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    String backTaskID;
    RequestProgress progress;

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (fileRestore.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih file yang akan direstore...  ", null, JOptionPane.WARNING_MESSAGE);
            return;
        }

        //
        if (!readInputFile()) {
            return;
        }

        // remove swing worker tables instance
        AppContext appContext = AppContext.getAppContext();
        if (appContext != null) {
            appContext.remove(SwingWorker.class);
        }

        backTaskID = TokoBangunanAdministrator.getIDTask();
        progress = new RequestProgress(this, backTaskID);
        progress.setVisible(true);
        //JOptionPane.showMessageDialog(null, "Berhasil" );
        //*
        ServerRequestController controller = new ServerRequestController(
                backTaskID, this,
                progress, "CREATE_DB", null, "Sedang melakukan pengecekan struktur database ...");
        controller.execute();

        //*/
        ///int k = 0;

    }//GEN-LAST:event_jButton3ActionPerformed

    private void progressText10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_progressText10MouseClicked

        dispose();
    }//GEN-LAST:event_progressText10MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (backupChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fileBackup = backupChooser.getSelectedFile().getPath();
            if (!fileBackup.substring(fileBackup.length() - 3, fileBackup.length()).equals(".dt")) {
                fileBackup += ".dt";
            }

            backupPath.setText(fileBackup);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    public void receiverRestoreData(String message) {
        fileRestore = "";
        JOptionPane.showMessageDialog(null, message);
        restorePath.setText("");
    }


    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (restoreChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fileRestore = restoreChooser.getSelectedFile().getPath();
            restorePath.setText(fileRestore);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BackupKontrol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BackupKontrol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BackupKontrol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BackupKontrol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BackupKontrol().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser backupChooser;
    private javax.swing.JTextField backupPath;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel progressText10;
    private javax.swing.JLabel progressText4;
    private javax.swing.JLabel progressText5;
    private javax.swing.JLabel progressText6;
    private javax.swing.JLabel progressText7;
    private javax.swing.JLabel progressText8;
    private javax.swing.JFileChooser restoreChooser;
    private javax.swing.JTextField restorePath;
    // End of variables declaration//GEN-END:variables

    @Override
    public void processDone(JSONObject jSONObject, String process) {
        if (process.equals("CREATE_DB")) {
            try {
                String message = jSONObject.getString("message");
                if (!message.equals("Terjadi Kesalahan ...")) {
                    List<NameValuePair> list = new ArrayList<>();
                    BackupEnt ent = new BackupEnt(progress, list, "SAVE_USER", "Sedang merestore data...", null, mapRestoreData);
                    BackupImpl impl = new BackupImpl(backTaskID, this);
                    impl.enchanceRestore(ent);
                } else {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada struktur database ...");
                }
            } catch (JSONException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada struktur database ...");
            }
        }
    }

    @Override
    public void httpParsingError(String process, String message) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada struktur database ...");
    }
}
