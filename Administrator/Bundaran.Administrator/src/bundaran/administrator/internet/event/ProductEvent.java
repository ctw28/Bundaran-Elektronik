/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.internet.event;

import bundaran.administrator.backgroundtask.ServerRequestController;
import bundaran.administrator.internet.entity.ProductEnt;

/**
 *
 * @author AsyncTask.Void.087
 */
public interface ProductEvent {
    
    ServerRequestController getRequestInstance(ProductEnt ue);
    
    public void enchanceSelectAll(ProductEnt ue);
            
    public void enchanceSave(ProductEnt ue);
    
    public void enchanceUpdate(ProductEnt ue);
    
    public void enchanceDelete(ProductEnt ue);
    
    
}
