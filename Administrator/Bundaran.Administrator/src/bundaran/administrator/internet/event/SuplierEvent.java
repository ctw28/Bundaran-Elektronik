/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.internet.event;

import bundaran.administrator.backgroundtask.ServerRequestController;
import bundaran.administrator.internet.entity.SuplierEnt;

/**
 *
 * @author bandit
 */
public interface SuplierEvent {
    
    ServerRequestController getRequestInstance(SuplierEnt ue);
    
    public void enchanceSelectAll(SuplierEnt ue);

    public void enchanceSelectSome(SuplierEnt ue);
            
    public void enchanceSave(SuplierEnt ue);
    
    public void enchanceUpdate(SuplierEnt ue);
    
    public void enchanceDelete(SuplierEnt ue);
    
    
}
