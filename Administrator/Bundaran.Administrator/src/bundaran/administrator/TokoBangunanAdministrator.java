/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator;

import annas.jdbc.service.configuration.Database;
import annas.jdbc.service.configuration.DatabaseTableList;
import annas.jdbc.service.impl.SqlImplementImpl;
import annas.jdbc.service.model.SelectClauseAttribut;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.scene.layout.Background;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import bundaran.administrator.backgroundtask.LocalRequestVoidCallbackTask;
import bundaran.administrator.backgroundtask.StartDatabaseBackgroundController;
import bundaran.administrator.view.Login;
import bundaran.administrator.view.Main;
import bundaran.administrator.view.progress.ProsesPembukaanAplikasi;

/**
 *
 * @author AsyncTask.Void.087
 */
public class TokoBangunanAdministrator implements LocalRequestVoidCallbackTask{

    /**
     * @param args the command line arguments
     */
    //public static Database database;
    public static boolean OFFLINE_MODE = false;
    ProsesPembukaanAplikasi progressTask;

    public TokoBangunanAdministrator() {
        try {

            UIManager.setLookAndFeel(new WindowsLookAndFeel());

            

            progressTask = new ProsesPembukaanAplikasi(null);
            progressTask.setVisible(true);
            StartDatabaseBackgroundController controller = new StartDatabaseBackgroundController(this, progressTask, 0 /*Start databse*/);
            controller.execute();
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TokoBangunanAdministrator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        TokoBangunanAdministrator tokoBangunanAdministrator = new TokoBangunanAdministrator();
    }

    /**
     * <p>
     * Fungsi yang akan dipanggil ketika proses background dari TsController
     * Selesai
     *
     * @param resultMessage
     * @throws java.sql.SQLException
     */
    @Override
    public void processDone(String resultMessage) throws SQLException {
        progressTask.dispose();

        Database xdatabase = new Database("db_toko_bangunan_admin");
        //UIManager.setLookAndFeel(new GTKLookAndFeel());

        //new Main().setVisible(true);
        //new HomeForm().setVisible(true);
        /**
         * nama tabel data admin
         */
        String tableName = DatabaseTableList.getTableNames()[2];

        try {

            SelectClauseAttribut attribut = new SelectClauseAttribut(null, null, null, tableName);
            SqlImplementImpl impl = new SqlImplementImpl();
            ArrayList<ArrayList<String>> dataAdmin = impl.goSelectWith(attribut);
            new Login(dataAdmin).setVisible(true);
        } catch (RemoteException ex) {
            Logger.getLogger(TokoBangunanAdministrator.class.getName()).log(Level.SEVERE, null, ex);
        }

        //snew Login(dataAdmin)
    }


    public static String getIDTask() {
        Date date = new Date();
        String id = String.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(date));
        return id;
    }
}
