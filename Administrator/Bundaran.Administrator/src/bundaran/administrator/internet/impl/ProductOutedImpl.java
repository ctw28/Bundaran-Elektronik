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
import bundaran.administrator.internet.entity.ProductOutEnt;
import bundaran.administrator.internet.entity.SelledEnt;
import bundaran.administrator.internet.entity.UserEnt;
import bundaran.administrator.internet.event.ProductEvent;
import bundaran.administrator.internet.event.ProductOutedEvent;
import bundaran.administrator.internet.event.SelledEvent;
import bundaran.administrator.internet.event.UserEvent;
import bundaran.administrator.util.RewriteDatabase;
import bundaran.administrator.util.SwingWorkerSecurityInstance;
import bundaran.administrator.util.staticIdentifier;
import bundaran.administrator.view.DataAdministratorMain;
import bundaran.administrator.view.DataAdministratorSubMain;
import bundaran.administrator.view.DataBarangKeluarMain;
import bundaran.administrator.view.DataBarangKeluarSubMain;
import bundaran.administrator.view.DataBarangSubMain;
import bundaran.administrator.view.DataPenjualanMain;
import bundaran.administrator.view.Login;
import bundaran.administrator.view.Main;
import bundaran.administrator.view.progress.RequestProgress;

/**
 *
 * @author AsyncTask.Void.087
 */
public class ProductOutedImpl extends SwingWorkerSecurityInstance implements RequestVoidCallbackTask, ProductOutedEvent {

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

    private ProductOutEnt selledEnt;
    
    boolean isGettingError = false;

    public ProductOutedImpl() {
        super();
    }

    /**
     *
     * @param backTaskID
     * @param instance
     */
    public ProductOutedImpl(String backTaskID, Object instance) {
        super();
        this.backTaskID = backTaskID;
        this.instance = instance;
    }

    @Override
    public ServerRequestController getRequestInstance(ProductOutEnt ue) {

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
    public void enchanceSelectAllProduct(ProductOutEnt ue) {
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
        selledEnt.setProcess("SELECT_ALL_PRODUCT_OUT");
        selledEnt.setPairs(null);
        selledEnt.setMessage("Sedang mengambil konten...");
        enchanceSelectAllProductOut(selledEnt);
    }
    
    @Override
    public void enchanceSelectAllProductOut(ProductOutEnt ue) {
        if (ue != null) {
            selledEnt = ue;
            getRequestInstance(ue).execute();
        } else if (ue == null) {
            if (jSONObject != null) {
                JSONArray array = jSONObject.getJSONArray("all_product_out_data");
                selectAllProductOuted(array);
            } else {
                selectAllProductOuted(null);
            }
        }
    }

    
    
    

    synchronized void selectAllProductOuted(JSONArray array) {
        ArrayList<ArrayList<String>> dataReceived = new ArrayList<>();
        
        if (array != null) {
            dataReceived = convertJsonToList(array,
                    staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_BARANG_KELUAR);
        } 
        
        callVoidFromInstance(dataReceived, "setDataBarangKeluarOriginal");
        
        DataBarangKeluarMain dpm = (DataBarangKeluarMain) instance;
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
            case "SELECT_ALL_PRODUCT_OUT":
                enchanceSelectAllProductOut(null);
                break;
            case "SAVE_PRODUCT_OUT":
                enchanceSave(null);
                break;    
            case "DELETE_PRODUCT_OUT":
                enchanceDelete(null);
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
            Logger.getLogger(ProductOutedImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProductOutedImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public void enchanceSave(ProductOutEnt ue) {
        if (ue != null) {
            selledEnt = ue;
            getRequestInstance(ue).execute();
        } else {
            //if (jSONObject != null) {
              //  String result = jSONObject.getString("message");
                //if (!result.equals("Terjadi Kesalahan ...")) {
                    /**
                     * KIll parent progress
                     */
                    DataBarangKeluarSubMain dasm = (DataBarangKeluarSubMain) requestProgress.getParent();
                    dasm.dispose();
                    
                    selledEnt.setProcess("SELECT_ALL_PRODUCT");
                    selledEnt.setPairs(null);
                    selledEnt.setMessage("Sedang mengambil konten...");
                    enchanceSelectAllProduct(selledEnt);
                //} else {
                  //  httpParsingError(process, "Terjadi kesalahan...");
                //}
            //} else {
              //  httpParsingError(process, "Terjadi kesalahan...");
            //}
        }
    }


    
    @Override
    public void enchanceDelete(ProductOutEnt ue){
        if (ue != null) {
            selledEnt = ue;
            getRequestInstance(ue).execute();
        } else {
            if (jSONObject != null) {
                String result = jSONObject.getString("message");
                if (!result.equals("Terjadi Kesalahan ...")) {
                    selledEnt.setProcess("SELECT_ALL_PRODUCT");
                    selledEnt.setPairs(null);
                    selledEnt.setMessage("Sedang mengambil konten...");
                    enchanceSelectAllProduct(selledEnt);
                } else {
                    httpParsingError(process, "Terjadi kesalahan...");
                }
            } else {
                httpParsingError(process, "Terjadi kesalahan...");
            }
        }
    }
            

}
