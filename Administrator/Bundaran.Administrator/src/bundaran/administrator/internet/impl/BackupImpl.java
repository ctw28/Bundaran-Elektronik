/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.internet.impl;

import annas.jdbc.service.configuration.DatabaseTableList;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import bundaran.administrator.backgroundtask.RequestVoidCallbackTask;
import bundaran.administrator.backgroundtask.ServerRequestController;
import bundaran.administrator.backgroundtask.ServerRequestTimeOutController;
import bundaran.administrator.internet.entity.BackupEnt;
import bundaran.administrator.internet.entity.UserEnt;
import bundaran.administrator.internet.event.BackupEvent;
import bundaran.administrator.internet.event.UserEvent;
import bundaran.administrator.util.RewriteDatabase;
import bundaran.administrator.util.SwingWorkerSecurityInstance;
import bundaran.administrator.util.staticIdentifier;
import bundaran.administrator.view.DataAdministratorMain;
import bundaran.administrator.view.DataAdministratorSubMain;
import bundaran.administrator.view.Login;
import bundaran.administrator.view.Main;
import bundaran.administrator.view.BackupKontrol;
import bundaran.administrator.view.progress.RequestProgress;

/**
 *
 * @author AsyncTask.Void.087
 */
public class BackupImpl extends SwingWorkerSecurityInstance implements RequestVoidCallbackTask, BackupEvent {

    /**
     * ID back task
     */
    private String backTaskID;

    /**
     * Object class yang meanggil class ini
     */
    private Object instance;

    /**
     * progres
     */
    private RequestProgress requestProgress;

    /**
     * Object JSON yang didapaat
     */
    private JSONObject jSONObject;

    /**
     * identifier process yang sedang berjalan
     */
    private String process;

    private BackupEnt userEnt;

    boolean isGettingError = false;

    int backupIndexTable = 0;
    private final String[] backupTableName = {
        "3rd_administrator",
        "1st_data_barang",
        "5th_barang_keluar",
        "2nd_data_penjualan",
        "4th_data_penjualan_barang",
        "6th_suplier"
    };

    private final String[] backupProcessIdentifier = {
        "SELECT_ALL_USER",
        "SELECT_ALL_PRODUCT",
        "SELECT_ALL_PRODUCT_OUT",
        "SELECT_ALL_SELLED",
        "SELECT_ALL_PRODUCT_SELLED",
        "SELECT_ALL_SUPLIER"
    };

    private final String[] restoreProcessIdentifier = {
        "SAVE_USER",
        "SAVE_PRODUCT",
        "SAVE_PRODUCT_OUT",
        "SAVE_TRANSACTION",
        "SAVE_TRANSACTION_PRODUCT",
        "SAVE_SUPLIER"
    };

    private final String[] backupJsonIdentifier = {
        "all_user_data",
        "all_product_data",
        "all_product_out_data",
        "all_data_penjualan",
        "all_data_penjualan_barang",
        "all_suplier_data"
    };

    private final String[] backupMessage = {
        "Sedang membackup data user...",
        "Sedang membackup data barang...",
        "Sedang membackup barang keluar...",
        "Sedang membackup data penjualan...",
        "Sedang membackup data penjualan barang...",
        "Sedang membackup data suplier..."
    };

    private final String[] restoreMessage = {
        "Sedang merestore data user ",
        "Sedang merestore data barang ",
        "Sedang merestore barang keluar ",
        "Sedang merestore data penjualan ",
        "Sedang merestore data penjualan barang ",
        "Sedang merestore data suplier "
    };

    private Map<String, ArrayList<ArrayList<String>>> mapBackupData = new HashMap<>();

    int tableContentIndex = 0;

    public BackupImpl() {
    }

    /**
     *
     * @param backTaskID
     * @param instance
     */
    public BackupImpl(String backTaskID, Object instance) {
        this.backTaskID = backTaskID;
        this.instance = instance;
    }

    @Override
    public ServerRequestController getRequestInstance(BackupEnt ue) {

        requestProgress = (RequestProgress) ue.getObjectProgress();

        ServerRequestController controller = new ServerRequestController(
                backTaskID,
                this,
                ue.getObjectProgress(),
                ue.getProcess(),
                ue.getPairs(),
                ue.getMessage());

        return controller;
    }

    @Override
    public void enchanceBackup(BackupEnt ue) {
        if (ue != null) { // persiapan prosess request ke server
            userEnt = ue;
            ue.setProcess(backupProcessIdentifier[backupIndexTable]);
            ue.setMessage(backupMessage[backupIndexTable]);
            getRequestInstance(ue).execute();
        } else // jika proses request ke server telah selesai
        {
            if (jSONObject != null) {
                JSONArray array = jSONObject.getJSONArray(backupJsonIdentifier[backupIndexTable]);
                if (array != null && array.length() > 0) {
                    selectAllResult(array);
                } else {
                    httpParsingError(process, "Kombinasi username dan password tidak valid");
                }
            } else {
                httpParsingError(process, "Kombinasi username dan password tidak valid");
            }
        }
    }

    private List<NameValuePair> getPairListFromMap() {

        if (mapBackupData.isEmpty()) {
            return null;
        }

        List<NameValuePair> pairing = new ArrayList<>();
        String[] tableStructure = getTableStructure();
        ArrayList<ArrayList<String>> dataTable = mapBackupData.get(backupTableName[backupIndexTable]);

        int i = 0;
        for (String strktr : tableStructure) {
            pairing.add(new BasicNameValuePair(strktr, dataTable.get(tableContentIndex).get(i)));
            i++;
        }

        return pairing;
    }

    @Override
    public void enchanceRestore(BackupEnt ue) {
        if (ue != null) { // persiapan prosess request ke server
            
           

            if (mapBackupData.isEmpty()) {
                mapBackupData = ue.getMapRestoreData();
            }
            String idRestore = mapBackupData.get(backupTableName[backupIndexTable]).get(tableContentIndex).get(0);

            ue.setProcess(restoreProcessIdentifier[backupIndexTable]);
            ue.setPairs(getPairListFromMap());
            ue.setMessage(restoreMessage[backupIndexTable] + idRestore + "...");
            userEnt = ue;
            getRequestInstance(ue).execute();
        } else {// if (jSONObject != null) {
            try {
                //jSONObject.getString("message");
                if (backupIndexTable < backupTableName.length) {
                    int tableContentSize = mapBackupData.get(backupTableName[backupIndexTable]).size();
                    tableContentIndex++;
                    if (tableContentIndex == tableContentSize) {
                        tableContentIndex = 0;
                        backupIndexTable++;
                    }
                }

                if (backupIndexTable == backupTableName.length) {
                    if (requestProgress != null) {
                        requestProgress.dispose(); // menutup progress loading
                    }
                    try {

                        Class[] classTypes = {String.class};
                        Object[] data = {"Proses restore data selesai..."};

                        Method toResult = instance.getClass().getMethod("receiverRestoreData", classTypes);
                        toResult.setAccessible(true);
                        toResult.invoke(instance, data);
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    } catch (SecurityException ex) {
                        Logger.getLogger(BackupImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                }

                enchanceRestore(userEnt);
            } catch (Exception e) {
                httpParsingError(process, "Terjadi kesalahan...");
            }
        } //else {
            //httpParsingError(process, "Terjadi kesalahan...");
        //}
    }

    String[] getTableStructure() {
        switch (backupIndexTable) {
            case 0:
                return staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_ADMIN;
            case 1:
                return staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_BARANG;
            case 2:
                return staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_BARANG_KELUAR;
            case 3:
                return staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_PENJUALAN;
            case 4:
                return staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_PENJUALAN_BARANG;
            case 5:
                return staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_SUPLIER;
            default:
                return null;
        }
    }

    void selectAllResult(JSONArray array) {
        if (array != null) {
            String[] tableStructure = getTableStructure();

            ArrayList<ArrayList<String>> dataReceived = convertJsonToList(array, tableStructure);
            mapBackupData.put(backupTableName[backupIndexTable], dataReceived);

            backupIndexTable++;

            if (backupIndexTable == backupTableName.length) {
                callVoidFromInstance(); // mengesetnya pada tabel
            } else {
                //userEnt = new BackupEnt
                userEnt.setMessage(backupMessage[backupIndexTable]);
                userEnt.setProcess(backupProcessIdentifier[backupIndexTable]);
                enchanceBackup(userEnt);
            }
        } else {
            callVoidFromInstance(); // mengesetnya pada tabel
        }

        BackupKontrol dam = (BackupKontrol) instance;
        if (requestProgress != null) {
            requestProgress.dispose(); // menutup progress loading
        }

        //if (!dam.isVisible()) {
        //  if (!isGettingError) {
        //    dam.setVisible(true);
        //}
        //}
    }

    @Override
    public void processDone(JSONObject jSONObject, String process) {
        String procesNow = backupProcessIdentifier[backupIndexTable];
        this.jSONObject = null;
        this.jSONObject = jSONObject;
        
        if(process.equals("SAVE_PRODUCT_OUT")){
            int k = 0;
           k += 3;
            int hasil = 0;
            hasil = hasil + k;
            
        }
        
        //for (String string : backupProcessIdentifier) {
        if (process.equals(procesNow)) {
            enchanceBackup(null);
            //      break;
        } else if (process.equals(restoreProcessIdentifier[backupIndexTable])) {
            enchanceRestore(null);
        }

    }

    @Override
    public void httpParsingError(String process, String message) {

        if (requestProgress != null) {
            requestProgress.dispose();
        }

        if (!message.toLowerCase().equals("proses telah dibatalkan oleh user".toLowerCase())) {
            isGettingError = true;
            if (message.toLowerCase().equals("REQUEST TIMEOUT".toLowerCase())) {
                JOptionPane.showMessageDialog(null, message);
            } else {
                JOptionPane.showMessageDialog(null, message);
            }
        }
    }

    private ArrayList<ArrayList<String>> convertJsonToList(JSONArray array, String[] tableStructure) {
        ArrayList<ArrayList<String>> temp = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            ArrayList<String> man = new ArrayList<>();
            for (String d : tableStructure) {
                String t = array.getJSONObject(i).getString(d);
                man.add(t);
            }
            temp.add(man);
        }

        return temp;
    }

    /**
     * Memanggil void refresh pada class instance nya
     *
     */
    public void callVoidFromInstance() {

        try {

            Class[] classTypes = {Map.class};
            Object[] data = {mapBackupData};

            Method toResult = instance.getClass().getMethod("receiverBackupData", classTypes);
            toResult.setAccessible(true);
            toResult.invoke(instance, data);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
        } catch (SecurityException ex) {
            Logger.getLogger(BackupImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean setOriData(ArrayList<ArrayList<String>> dataReceived) {
        try {

            Class[] classTypes = {ArrayList.class};
            Object[] data = {dataReceived};

            Method toResult = instance.getClass().getMethod("setDataOriginal", classTypes);
            toResult.setAccessible(true);
            toResult.invoke(instance, data);
            return true;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            return false;
        } catch (SecurityException ex) {
            Logger.getLogger(BackupImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
