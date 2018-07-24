/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.backgroundtask;

import org.json.JSONObject;

/**
 *
 * @author AsyncTask.Void.087
 */
public interface RequestVoidCallbackTask {
    
    long TIME_OUT = 5000;
    
    
    /**
     * 
     * @param jSONObject data parsing yang didapat dari proses ini
     * @param process identifier id yang dipakai pada process ini
     */
    public void processDone(JSONObject jSONObject, String process);
    
    
    /**
     * 
     * @param process
     * @param message pesan yang didapat jika didapati error
     */
    public void httpParsingError(String process, String message);
    
    
    
}
