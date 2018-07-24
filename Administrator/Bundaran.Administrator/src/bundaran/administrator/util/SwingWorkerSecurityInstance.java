/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.util;

import javax.swing.SwingWorker;
import sun.awt.AppContext;

/**
 *
 * @author AsyncTask.Void.087
 */
public  class SwingWorkerSecurityInstance {

    public SwingWorkerSecurityInstance() {
        removeMaxInstance();
    }
    
    private boolean removeMaxInstance(){
        // remove swing worker tables instance
        AppContext appContext = AppContext.getAppContext();
        if (appContext != null) {
            int y = 0;
            appContext.remove(SwingWorker.class);
            return true;
        }
        
        return false;
    }
    
}
