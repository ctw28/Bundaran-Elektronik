/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir.backgroundtask;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import org.json.JSONObject;
import org.apache.http.NameValuePair;
import bundaran.kasir.util.JsonParsing;
import bundaran.kasir.util.staticIdentifier;

/**
 *
 * @author dan
 */
public class ServerRequestController extends SwingWorker<JSONObject, String> implements LocalRequestVoidCallbackTask {

    /**
     * <p>
     * Instance dari class yang memanggil clas ini</p>
     */
    Object instance;

    /**
     * <p>
     * Label progreass untukmenampilkan pesan progress </p>
     */
    JLabel label;

    /**
     * <p>
     * String identifier untuk proses request apa yaang sedan </p>
     */
    String process;

    /**
     * <p>
     * Merupakan aksi pembatalan manual yang dilakukan oleh USER</p>
     */
    //public static boolean PROCESS_CLOSED_BY_USER;
    //public static Map<String, Boolean> PROCESS_DONE = new HashMap<>();
    public static Map<String, Boolean> PROCESS_CLOSED = new HashMap<>();

    /**
     * <p>
     * List data yang dibutuhkan</p>
     */
    List<NameValuePair> data;

    String backgroundTaskID;
    Object progressView;
    String messageOnProgress;

    public ServerRequestController() {
    }

    public ServerRequestController(String backgroundTaskID, Object instance, Object progressView, String process, List<NameValuePair> pairsData, String message) {

        /**
         * TimeOut or canceled by user identifing
         */
        if (PROCESS_CLOSED.containsKey(backgroundTaskID)) {
            ServerRequestController.PROCESS_CLOSED.replace(backgroundTaskID, false);
        } else {
            ServerRequestController.PROCESS_CLOSED.put(backgroundTaskID, false);
            ServerRequestTimeOutController timeOut = new ServerRequestTimeOutController(backgroundTaskID, this);
            timeOut.execute();
        }

        this.instance = instance;
        this.backgroundTaskID = backgroundTaskID;
        this.process = process;
        this.data = pairsData;
        this.progressView = progressView;
        this.messageOnProgress = message;
    }

    @Override
    protected JSONObject doInBackground() throws Exception {
        JSONObject jSONObject = null;

        publish(messageOnProgress);
        //Thread.sleep(7000);
        ///*SELECT_ALL_SELLED_UNLUNAS
        JsonParsing parsing = new JsonParsing();
        switch (process) {
            case "LOGIN" :
                jSONObject = parsing.makeHttpRequest(backgroundTaskID, instance, staticIdentifier.URL_REQUEST.LOGIN_URL, "GET", data);
                break;
            case "SELECT_ALL_PRODUCT" :
                jSONObject = parsing.makeHttpRequest(backgroundTaskID, instance, staticIdentifier.URL_REQUEST.SELECT_ALL_PRODUCT_URL, "GET", data);
                break;
            case "SELECT_ALL_SELLED_UNLUNAS" :
                jSONObject = parsing.makeHttpRequest(backgroundTaskID, instance, staticIdentifier.URL_REQUEST.SELECT_ALL_SELLED_URL, "GET", data);
                break;
            case "SELECT_ALL_PRODUCT_SELLED_UNLUNAS" :
                jSONObject = parsing.makeHttpRequest(backgroundTaskID, instance, staticIdentifier.URL_REQUEST.SELECT_ALL_PRODUCT_SELLED_URL, "GET", data);
                break;    
            case "SAVE_TRANSACTION"  :
                jSONObject = parsing.makeHttpRequest(backgroundTaskID, instance, staticIdentifier.URL_REQUEST.SAVE_TRANSACTION_URL, "GET", data);
                break;
            case "SAVE_TRANSACTION_PRODUCT" :
                jSONObject = parsing.makeHttpRequest(backgroundTaskID, instance, staticIdentifier.URL_REQUEST.SAVE_TRANSACTION_PRODUCT_URL, "GET", data);
                break;
            default:
        }

        
        return jSONObject;
    }

    @Override
    protected void done() {

        try {

            if (PROCESS_CLOSED.get(backgroundTaskID)) {
                if (!ServerRequestTimeOutController.TIMEOUTED.get(backgroundTaskID)) {
                    //System.err.println("proses telah dibatalkan oleh user");
                    processDone("proses telah dibatalkan oleh user");
                }
            } else {
                if (instance == null) {
                    return;
                }
                
                PROCESS_CLOSED.replace(backgroundTaskID, true);
                String resultFunction = staticIdentifier.BackgroundProcess.RESUL_FUNCTION_NAME;
                Class[] classTypes = {JSONObject.class, String.class};
                Object[] dataReceived = {this.get(), process};

                try {
                    Method goResult = instance.getClass().getMethod(resultFunction, classTypes);
                    goResult.invoke(instance, dataReceived);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    //System.out.println(instance.getClass().getName());
                    //System.out.println(process);
                    //Logger.getLogger(ServerRequestController.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerRequestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ServerRequestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServerRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void process(List<String> chunks) {
        if (progressView == null) {
            return;
        }

        label = getLabelProgress(progressView);
        label.setText("   " + chunks.get(0) + "...");
    }

    /**
     * Fungsi untuk mendapatkan label Progress
     *
     * @param progressView
     * @return
     */
    private JLabel getLabelProgress(Object progressView) {
        try {
            Field field = progressView.getClass().getDeclaredField(staticIdentifier.BackgroundProcess.LABEL_PROGRESS_NAME);
            field.setAccessible(true);
            return (JLabel) field.get(progressView);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(ServerRequestTimeOutController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void processDone(String resultMessage) throws SQLException {

        String resultFunction = staticIdentifier.BackgroundProcess.ERROR_FUNCTION_NAME;
        Class[] classTypes = {String.class, String.class};
        Object[] dataReceived = {process, resultMessage};

        try {
            Method goResult = instance.getClass().getMethod(resultFunction, classTypes);
            goResult.invoke(instance, dataReceived);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ServerRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
