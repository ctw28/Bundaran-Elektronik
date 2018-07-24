/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir;

import annas.jdbc.service.configuration.Database;
import annas.jdbc.service.configuration.DatabaseTableList;
import annas.jdbc.service.impl.SqlImplementImpl;
import annas.jdbc.service.model.SelectClauseAttribut;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import bundaran.kasir.backgroundtask.LocalRequestVoidCallbackTask;
import bundaran.kasir.backgroundtask.StartDatabaseBackgroundController;
import bundaran.kasir.view.Login;
import bundaran.kasir.view.progress.ProsesPembukaanAplikasi;

/**
 *
 * @author AsyncTask.Void.087
 */
public class TokoBangunanKasir implements LocalRequestVoidCallbackTask {

    public static boolean OFFLINE_MODE = false;
    ProsesPembukaanAplikasi progressTask;

    public TokoBangunanKasir() {
        try {

            UIManager.setLookAndFeel(new WindowsLookAndFeel());

            progressTask = new ProsesPembukaanAplikasi(null);
            progressTask.setVisible(true);
            StartDatabaseBackgroundController controller = new StartDatabaseBackgroundController(this, progressTask, 0 /*Start databse*/);
            controller.execute();
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TokoBangunanKasir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TokoBangunanKasir tokoBangunanKasir = new TokoBangunanKasir();
    }

    @Override
    public void processDone(String resultMessage) throws SQLException {

        if (progressTask != null) {
            progressTask.dispose();
        }

        try {
            //Database xdatabase = new Database("db_toko_bangunan_kasir");
            Database.DB_TYPE = "HSQLDB";
            Database xdatabase = new Database("local\\BukuTamu");

            /**
             * nama tabel data admin
             */
            String tableName = DatabaseTableList.getTableNames()[1];

            try {
                SelectClauseAttribut attribut = new SelectClauseAttribut(null, null, null, tableName);
                SqlImplementImpl impl = new SqlImplementImpl();
                ArrayList<ArrayList<String>> dataAdmin = impl.goSelectWith(attribut);
                new Login(dataAdmin).setVisible(true);
            } catch (RemoteException ex) {
                Logger.getLogger(TokoBangunanKasir.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception s) {
            JOptionPane.showMessageDialog(null, "Aplikasi sedang berjalan...");
            System.exit(0);
        }

    }

    public static String getIDTask() {
        Date date = new Date();
        String id = String.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(date));
        return id;
    }

}
