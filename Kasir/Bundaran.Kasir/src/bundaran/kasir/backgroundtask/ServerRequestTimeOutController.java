/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir.backgroundtask;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import org.json.JSONObject;
import static bundaran.kasir.backgroundtask.ServerRequestController.PROCESS_CLOSED;
import bundaran.kasir.util.staticIdentifier;

/**
 *
 * @author dan
 */
public class ServerRequestTimeOutController extends SwingWorker<Boolean, String> {

    /**
     * Time out untuk proses 
     */
    public static Map<String, Boolean> TIMEOUTED = new HashMap<>();
    public static long TIME_OUT = 30000;
    String processID;
    Object instance;

    public ServerRequestTimeOutController(String processID, Object instance) {
        
        if (TIMEOUTED.containsKey(processID)) {
            TIMEOUTED.replace(processID, false);
        } else {
            TIMEOUTED.put(processID, false);
        }
        
        this.processID = processID;
        this.instance = instance;
    }
    
    

    @Override
    protected Boolean doInBackground() throws Exception {
        Thread.sleep(TIME_OUT);
        if (!ServerRequestController.PROCESS_CLOSED.get(processID)) {
            TIMEOUTED.replace(processID, true);
        }
        return true;
    }

    @Override
    protected void done() {
        if (!ServerRequestController.PROCESS_CLOSED.get(processID)) {
            ServerRequestController.PROCESS_CLOSED.replace(processID, true);
            String resultFunction = staticIdentifier.BackgroundProcess.RESUL_FUNCTION_NAME;
            Class[] classTypes = {String.class};
            Object[] dataReceived = {"REQUEST TIMEOUT"};

            try {
                Method goResult = instance.getClass().getMethod(resultFunction, classTypes);
                goResult.invoke(instance, dataReceived);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(ServerRequestController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void process(List<String> chunks) {
    }
    

}
