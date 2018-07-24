/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.internet.event;

import bundaran.administrator.backgroundtask.ServerRequestController;
import bundaran.administrator.internet.entity.ProductEnt;
import bundaran.administrator.internet.entity.ProductOutEnt;

/**
 *
 * @author AsyncTask.Void.087
 */
public interface ProductOutEvent {
    
    ServerRequestController getRequestInstance(ProductOutEnt ue);
    
    public void enchanceSelectAll(ProductOutEnt ue);
            
    public void enchanceSave(ProductOutEnt ue);
    
    public void enchanceUpdate(ProductEnt ue);
    
    public void enchanceDelete(ProductEnt ue);
    
    
}
