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
import bundaran.administrator.internet.entity.UserEnt;
import bundaran.administrator.internet.event.UserEvent;
import bundaran.administrator.util.RewriteDatabase;
import bundaran.administrator.util.SwingWorkerSecurityInstance;
import bundaran.administrator.util.staticIdentifier;
import bundaran.administrator.view.DataAdministratorMain;
import bundaran.administrator.view.DataAdministratorSubMain;
import bundaran.administrator.view.Login;
import bundaran.administrator.view.Main;
import bundaran.administrator.view.progress.RequestProgress;

/**
 *
 * @author AsyncTask.Void.087
 */
public class UserImpl extends SwingWorkerSecurityInstance implements RequestVoidCallbackTask, UserEvent {

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
    
    
    private UserEnt userEnt;
    
    
    boolean isGettingError = false;
    

    public UserImpl() {
        super();
    }

    /**
     *
     * @param backTaskID
     * @param instance
     */
    public UserImpl(String backTaskID, Object instance) {
        super();
        this.backTaskID = backTaskID;
        this.instance = instance;
    }

    @Override
    public ServerRequestController getRequestInstance(UserEnt ue) {

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
    public void enchanceLogin(UserEnt ue) {
        if (ue != null) { // persiapan prosess request ke server
            getRequestInstance(ue).execute();
        } else // jika proses request ke server telah selesai
        {
            if (jSONObject != null) {
                JSONArray array = jSONObject.getJSONArray("user_data_by");
                if (array != null && array.length() > 0) {
                    Login login = (Login) instance;
                    login.dispose();
                    requestProgress.dispose();
                    new Main().setVisible(true);
                } else {
                    httpParsingError(process, "Kombinasi username dan password tidak valid");
                }
            } else {
                httpParsingError(process, "Kombinasi username dan password tidak valid");
            }
        }
    }

    @Override
    public void enchanceSelectAll(UserEnt ue) {
        if (ue != null) { // persiapan prosess request ke server
            getRequestInstance(ue).execute();
        } else if (jSONObject != null) {
            JSONArray array = jSONObject.getJSONArray("all_user_data");
            if (array != null && array.length() > 0) {
                selectAllResult(array);
            } else {
                selectAllResult(array);
                //httpParsingError(process, "Terjadi kesalahan...");
            }
        } else {
            selectAllResult(null);
            //httpParsingError(process, "Terjadi kesalahan...");
        }
    }

    
    void selectAllResult(JSONArray array) {
        if (array != null) {
            ArrayList<ArrayList<String>> dataReceived = convertJsonToList(array,
                staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_ADMIN);
            callVoidFromInstance(dataReceived); // mengesetnya pada tabel
        } else {
            callVoidFromInstance(null); // mengesetnya pada tabel
        }
        
        DataAdministratorMain dam = (DataAdministratorMain) instance;
        if (requestProgress != null) {
            requestProgress.dispose(); // menutup progress loading
        }

        if (!dam.isVisible()) {
            if (!isGettingError) {
                dam.setVisible(true);
            }
        }
    }

    @Override
    public void enchanceSave(UserEnt ue) {
        if (ue != null) {
            userEnt = ue;
            getRequestInstance(ue).execute();
        } else {
            //if (jSONObject != null) {
                //String result = jSONObject.getString("message");
                //if (!result.equals("Terjadi Kesalahan ...")) {
                    /**
                     * KIll parent progress
                     */
                    DataAdministratorSubMain dasm = (DataAdministratorSubMain) requestProgress.getParent();
                    dasm.dispose();
                    
                    userEnt.setProcess("SELECT_ALL_USER");
                    userEnt.setPairs(null);
                    userEnt.setMessage("Sedang mengambil konten...");
                    enchanceSelectAll(userEnt);
               // } else {
                 //   httpParsingError(process, "Terjadi kesalahan...");
                //}
            //} else {
              //  httpParsingError(process, "Terjadi kesalahan...");
            //}
        }
        
    }

    @Override
    public void enchanceUpdate(UserEnt ue) {
        if (ue != null) {
            userEnt = ue;
            getRequestInstance(ue).execute();
        } else {
            if (jSONObject != null) {
                String result = jSONObject.getString("message");
                if (!result.equals("Terjadi Kesalahan ...")) {
                    /**
                     * KIll parent progress
                     */
                    DataAdministratorSubMain dasm = (DataAdministratorSubMain) requestProgress.getParent();
                    dasm.dispose();
                    
                    userEnt.setProcess("SELECT_ALL_USER");
                    userEnt.setPairs(null);
                    userEnt.setMessage("Sedang mengambil konten...");
                    enchanceSelectAll(userEnt);
                } else {
                    httpParsingError(process, "Terjadi kesalahan...");
                }
            } else {
                httpParsingError(process, "Terjadi kesalahan...");
            }
        }
    }

    @Override
    public void enchanceDelete(UserEnt ue) {
        if (ue != null) {
            userEnt = ue;
            getRequestInstance(ue).execute();
        } else {
            if (jSONObject != null) {
                String result = jSONObject.getString("message");
                if (!result.equals("Terjadi Kesalahan ...")) {
                    userEnt.setProcess("SELECT_ALL_USER");
                    userEnt.setPairs(null);
                    userEnt.setMessage("Sedang mengambil konten...");
                    enchanceSelectAll(userEnt);
                } else {
                    httpParsingError(process, "Terjadi kesalahan...");
                }
            } else {
                httpParsingError(process, "Terjadi kesalahan...");
            }
        }
    }

    @Override
    public void processDone(JSONObject jSONObject, String process) {
        this.jSONObject = null;
        this.jSONObject = jSONObject;
        this.process = process;
        switch (process) {
            case "LOGIN":
                enchanceLogin(null);
                break;
            case "SELECT_ALL_USER":
                enchanceSelectAll(null);
                break;
            case "SAVE_USER"   :
                enchanceSave(null);
                break;
            case "UPDATE_USER"   :
                enchanceUpdate(null);
                break;    
            case "DELETE_USER"   :
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
     */
    public void callVoidFromInstance(ArrayList<ArrayList<String>> dataReceived) {
        
        try {
            if (setOriData(dataReceived)) {
                Class[] classTypes = {ArrayList.class};
                Object[] data = {dataReceived};

                Method toResult = instance.getClass().getMethod("refreshTable", classTypes);
                toResult.setAccessible(true);
                toResult.invoke(instance, data);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
        } catch (SecurityException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    boolean setOriData(ArrayList<ArrayList<String>> dataReceived){
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
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
