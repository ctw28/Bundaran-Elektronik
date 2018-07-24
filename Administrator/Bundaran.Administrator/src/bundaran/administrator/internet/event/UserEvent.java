/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.internet.event;

import bundaran.administrator.backgroundtask.ServerRequestController;
import bundaran.administrator.internet.entity.UserEnt;

/**
 *
 * @author AsyncTask.Void.087
 */
public interface UserEvent {
    
    ServerRequestController getRequestInstance(UserEnt ue);
    
    public void enchanceLogin(UserEnt ue);
    
    public void enchanceSelectAll(UserEnt ue);
            
    public void enchanceSave(UserEnt ue);
    
    public void enchanceUpdate(UserEnt ue);
    
    public void enchanceDelete(UserEnt ue);
    
    
}
