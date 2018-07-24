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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import bundaran.administrator.backgroundtask.RequestVoidCallbackTask;
import bundaran.administrator.backgroundtask.ServerRequestController;
import bundaran.administrator.backgroundtask.ServerRequestTimeOutController;
import bundaran.administrator.internet.entity.ProductEnt;
import bundaran.administrator.internet.entity.SelledEnt;
import bundaran.administrator.internet.entity.UserEnt;
import bundaran.administrator.internet.event.ProductEvent;
import bundaran.administrator.internet.event.SelledEvent;
import bundaran.administrator.internet.event.UserEvent;
import bundaran.administrator.util.RewriteDatabase;
import bundaran.administrator.util.SwingWorkerSecurityInstance;
import bundaran.administrator.util.staticIdentifier;
import bundaran.administrator.view.DataAdministratorMain;
import bundaran.administrator.view.DataAdministratorSubMain;
import bundaran.administrator.view.DataBarangSubMain;
import bundaran.administrator.view.DataPenjualanMain;
import bundaran.administrator.view.Login;
import bundaran.administrator.view.Main;
import bundaran.administrator.view.progress.RequestProgress;

/**
 *
 * @author AsyncTask.Void.087
 */
public class SelledImpl extends SwingWorkerSecurityInstance implements RequestVoidCallbackTask, SelledEvent {

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

    private SelledEnt selledEnt;
    
    boolean isGettingError = false;

    public SelledImpl() {
        super();
    }

    /**
     *
     * @param backTaskID
     * @param instance
     */
    public SelledImpl(String backTaskID, Object instance) {
        super();
        this.backTaskID = backTaskID;
        this.instance = instance;
    }

    @Override
    public ServerRequestController getRequestInstance(SelledEnt ue) {

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
    public void enchanceSelectAllSelled(SelledEnt ue) {
        if (ue != null) {
            selledEnt = ue;
            getRequestInstance(ue).execute();
        } else if (ue == null) {
            if (jSONObject != null) {
                JSONArray array = jSONObject.getJSONArray("all_data_penjualan");
                selectAllSelled(array);
            } else {
                selectAllSelled(null);
            }
        }
    }

    @Override
    public void enchanceSelectAllProductSelled(SelledEnt ue) {
        if (ue != null) {
            selledEnt = ue;
            getRequestInstance(ue).execute();
        } else if (ue == null) {
            if (jSONObject != null) {
                JSONArray array = jSONObject.getJSONArray("all_data_penjualan_barang");
                selectAllProductSelled(array);
            } else {
                selectAllProductSelled(null);
            }
        }
    }

    @Override
    public void enchanceSelectAllProduct(SelledEnt ue) {
        if (ue != null) {
            selledEnt = ue;
            getRequestInstance(ue).execute();
        } else if (ue == null) {
            if (jSONObject != null) {
                JSONArray array = jSONObject.getJSONArray("all_product_data");
                selectAllProduct(array);
            } else {
                selectAllProduct(null);
            }
        }
    }

    
    synchronized void selectAllProduct(JSONArray array) {
        ArrayList<ArrayList<String>> dataReceived = new ArrayList<>();
        
        if (array != null) {
            dataReceived = convertJsonToList(array,
                    staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_BARANG);
        } 
        
        /**
         * Set ori data barang 
         */
        setOriData(dataReceived, "setDataBarangOriginal");
        
        
        /**
         * next : ambil data penjualan barang
         */
        selledEnt.setProcess("SELECT_ALL_PRODUCT_SELLED");
        selledEnt.setPairs(null);
        selledEnt.setMessage("Sedang mengambil konten...");
        enchanceSelectAllProductSelled(selledEnt);
    }

    synchronized void selectAllProductSelled(JSONArray array) {
        ArrayList<ArrayList<String>> dataReceived = new ArrayList<>();
        
        if (array != null) {
            dataReceived = convertJsonToList(array,
                    staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_PENJUALAN_BARANG);
        } 
        
        /**
         * Set ori data barang 
         */
        setOriData(dataReceived, "setDataPenjualanBarangOriginal");
        
        
        /**
         * next : ambil data penjualan barang
         */
        selledEnt.setProcess("SELECT_ALL_SELLED");
        selledEnt.setPairs(null);
        selledEnt.setMessage("Sedang mengambil konten...");
        enchanceSelectAllSelled(selledEnt);
    }

    synchronized void selectAllSelled(JSONArray array) {
        ArrayList<ArrayList<String>> dataReceived = new ArrayList<>();
        
        if (array != null) {
            dataReceived = convertJsonToList(array,
                    staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_PENJUALAN);
        } 
        
        callVoidFromInstance(dataReceived, "setDataPenjualanOriginal");
        
        DataPenjualanMain dpm = (DataPenjualanMain) instance;
        if (requestProgress != null) {
            requestProgress.dispose();
        }
        
        if (!dpm.isVisible()) {
            if (!isGettingError) {
                dpm.setVisible(true);
            }
        }
    }

    @Override
    public void processDone(JSONObject jSONObject, String process) {
        this.jSONObject = null;
        this.jSONObject = jSONObject;
        this.process = process;
        switch (process) {

            case "SELECT_ALL_PRODUCT":
                enchanceSelectAllProduct(null);
                break;
            case "SELECT_ALL_SELLED":
                enchanceSelectAllSelled(null);
                break;
            case "SELECT_ALL_PRODUCT_SELLED":
                enchanceSelectAllProductSelled(null);
                break;
            default:
        }
    }

    @Override
    public void httpParsingError(String process, String message) {

        if (requestProgress != null) {
            requestProgress.dispose();
        }

        if (!message.toLowerCase().equals("proses telah dibatalkan oleh user".toLowerCase())) {
            isGettingError = true;
            //System.err.println("aaaaaaaaaaaa");
            
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
     * @param dataReceived
     * @param voidName
     */
    public void callVoidFromInstance(ArrayList<ArrayList<String>> dataReceived, String voidName) {

        try {
            if (setOriData(dataReceived, voidName)) {
                Class[] classTypes = {ArrayList.class};
                Object[] data = {dataReceived};

                Method toResult = instance.getClass().getMethod("refreshTable", classTypes);
                toResult.setAccessible(true);
                toResult.invoke(instance, data);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
        } catch (SecurityException ex) {
            Logger.getLogger(SelledImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * mengeset data original yang didapat dari server
     *
     * @param dataReceived data dari server database
     * @param voidName
     * @return
     */
    boolean setOriData(ArrayList<ArrayList<String>> dataReceived, String voidName) {
        try {

            Class[] classTypes = {ArrayList.class};
            Object[] data = {dataReceived};

            Method toResult = instance.getClass().getMethod(voidName, classTypes);
            toResult.setAccessible(true);
            toResult.invoke(instance, data);
            return true;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            return false;
        } catch (SecurityException ex) {
            Logger.getLogger(SelledImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
