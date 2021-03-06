/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.view;

import annas.jdbc.service.configuration.DatabaseTableList;
import annas.jdbc.service.configuration.StyleButtonUI;
import annas.jdbc.service.impl.SqlImplementImpl;
import annas.jdbc.service.model.PreparingSQLWriting;
import annas.jdbc.service.model.QueryAttribut;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.apache.http.NameValuePair;
import sun.awt.AppContext;
import bundaran.administrator.TokoBangunanAdministrator;
import bundaran.administrator.internet.entity.UserEnt;
import bundaran.administrator.internet.impl.UserImpl;
import bundaran.administrator.util.ImageToPanel;
import bundaran.administrator.util.MoveFrameListener;
import bundaran.administrator.util.staticIdentifier;
import bundaran.administrator.view.progress.RequestProgress;

/**
 *
 * @author AsyncTask.Void.087
 */
public class DataAdministratorSubMain extends javax.swing.JDialog {

    /**
     * Creates new form DataBarangSubMain
     */
    //private final String tableName = DatabaseTableList.getTableNames()[2];
    Object instance;
    ArrayList<String> dataSelected;

    public DataAdministratorSubMain(Object instance, ArrayList<String> dataSelected) {
        super((Frame) null, true);
        initComponents();
        setLocationRelativeTo(null);

        this.instance = instance;
        this.dataSelected = dataSelected;

        if (dataSelected != null) {
            actionButton.setText(staticIdentifier.buttonChangeName);
            setContainer();
        } else {
            actionButton.setText(staticIdentifier.buttonSaveName);
        }
    }

    private void setContainer() {
        username.setText(dataSelected.get(1));
        password.setText(dataSelected.get(2));
        userType.setSelectedItem(dataSelected.get(3));
    }

    private ArrayList<Object> componentQueueValue() {
        ArrayList<Object> list = new ArrayList<>();
        Date date = new Date();
        String id = String.valueOf(new SimpleDateFormat("yyyyddHHmmss").format(date));

        list.add((dataSelected == null) ? id : dataSelected.get(0));
        list.add(username.getText());
        list.add(password.getText());
        list.add(String.valueOf(userType.getSelectedItem()));

        return list;
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
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        actionButton = new javax.swing.JButton();
        username = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        password = new javax.swing.JTextField();
        userType = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        progressText7 = new javax.swing.JLabel();
        progressText1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        progressText2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(246, 246, 246));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Type Pengguna");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setText("Pengguna");

        actionButton.setUI(new StyleButtonUI());
        actionButton.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        actionButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/save.PNG"))); // NOI18N
        actionButton.setText("Simpan Data   ");
        actionButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        actionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionButtonActionPerformed(evt);
            }
        });

        username.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        username.setMargin(new java.awt.Insets(2, 10, 2, 10));
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("Kata Sandi");

        password.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        password.setMargin(new java.awt.Insets(2, 10, 2, 10));
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });

        userType.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        userType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Salah Satu --", "Client", "Owner", "Superuser" }));

        MoveFrameListener frameListenerx = new MoveFrameListener(jPanel2);
        addMouseListener(frameListenerx);
        addMouseMotionListener(frameListenerx);
        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        progressText7.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        progressText7.setForeground(new java.awt.Color(255, 255, 255));
        progressText7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/close.png"))); // NOI18N
        progressText7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                progressText7MouseClicked(evt);
            }
        });

        progressText1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        progressText1.setForeground(new java.awt.Color(255, 255, 255));
        progressText1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/logo14.PNG"))); // NOI18N
        progressText1.setText("   Aplikasi Pendataan Barang Toko Bundaran Elektronik");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(progressText1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progressText7)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressText1)
                    .addComponent(progressText7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        progressText2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        progressText2.setText("Data Barang");

        jLabel4.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        jLabel4.setText("Pastikan Data Pengguna sudah Benar!!! ");

        jLabel13.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        jLabel13.setText("Toko Bundaran Elektronik, Kendari, Sulawesi Tenggara");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                        .addComponent(actionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addGap(95, 95, 95)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(userType, 0, 254, Short.MAX_VALUE)
                                            .addComponent(password)
                                            .addComponent(username)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(progressText2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4)))))
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(progressText2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userType, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(actionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(27, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void requestToServer(String process) {
        String backTaskID = TokoBangunanAdministrator.getIDTask();
        RequestProgress progress = new RequestProgress(this, backTaskID);
        progress.setVisible(true);

        //List<NameValuePair> list = new ArrayList<>();
        UserEnt ent = new UserEnt(progress, componentQueueValue(), process, "Mohon menunggu...");
        UserImpl impl = new UserImpl(backTaskID, instance);

        if (process.equals("SAVE_USER")) {
            impl.enchanceSave(ent);
        } else {
            impl.enchanceUpdate(ent);
        }

    }


    private void actionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionButtonActionPerformed

        String buttonID = actionButton.getText();

        if (username.getText().isEmpty()
                || password.getText().isEmpty()
                || userType.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Periksa kembali field anda");
            return;
        }

        try {
            
            

            PreparingSQLWriting psw = new PreparingSQLWriting();
            SqlImplementImpl query = new SqlImplementImpl();

            if (buttonID.equals(staticIdentifier.buttonSaveName)) {

                /*
                Object[] param = psw.parameterReceived(null, componentQueueValue());
                QueryAttribut attribut = new QueryAttribut(false, null, tableName,
                        param
                );

                if (query.goInsert(attribut)) {
                    dispose();
                }
                 */
                requestToServer("SAVE_USER");
            } else {
                /*
                Object[] param = psw.parameterReceived(new String[]{dataSelected.get(0)}, componentQueueValue());
                QueryAttribut attribut = new QueryAttribut(false,
                        new String[]{
                            DatabaseTableList.getColumnList().get(tableName)[0]
                        },
                        tableName, param);

                if (query.goUpdate(attribut)) {
                    dispose();
                }
                
                 */

                requestToServer("UPDATE_USER");
            }

            //if (instance != null) 
            //  psw.refreshTableContainer(instance, staticIdentifier.refreshTableName);
        } catch (RemoteException ex) {
            Logger.getLogger(DataBarangSubMain.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_actionButtonActionPerformed

    private void progressText7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_progressText7MouseClicked

        dispose();
    }//GEN-LAST:event_progressText7MouseClicked

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        actionButtonActionPerformed(evt);
    }//GEN-LAST:event_usernameActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        actionButtonActionPerformed(evt);
    }//GEN-LAST:event_passwordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actionButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField password;
    private javax.swing.JLabel progressText1;
    private javax.swing.JLabel progressText2;
    private javax.swing.JLabel progressText7;
    private javax.swing.JComboBox<String> userType;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
