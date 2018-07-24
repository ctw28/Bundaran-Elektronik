/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.internet.event;

import bundaran.administrator.backgroundtask.ServerRequestController;
import bundaran.administrator.internet.entity.BackupEnt;
import bundaran.administrator.internet.entity.UserEnt;

/**
 *
 * @author AsyncTask.Void.087
 */
public interface BackupEvent {
    
    ServerRequestController getRequestInstance(BackupEnt ue);
    
    public void enchanceBackup(BackupEnt ue);
    
    public void enchanceRestore(BackupEnt ue);
    
}
