/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.backgroundtask;

import java.sql.SQLException;

/**
 *
 * @author AsyncTask.Void.087
 */
public interface LocalRequestVoidCallbackTask {

    /**
     * 
     * @param resultMessage 
     * @throws java.sql.SQLException 
     */
    public void processDone(String resultMessage) throws SQLException;
    
}
