/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.internet.event;

import bundaran.administrator.backgroundtask.ServerRequestController;
import bundaran.administrator.internet.entity.ProductEnt;
import bundaran.administrator.internet.entity.SelledEnt;

/**
 *
 * @author AsyncTask.Void.087
 */
public interface SelledEvent {
    
    ServerRequestController getRequestInstance(SelledEnt ue);
    
    public void enchanceSelectAllSelled(SelledEnt ue);
    
    public void enchanceSelectAllProductSelled(SelledEnt ue);
    
    public void enchanceSelectAllProduct(SelledEnt ue);
    
    /*
    public void enchanceSave(SelledEnt ue);
    
    public void enchanceUpdate(SelledEnt ue);
    
    public void enchanceDelete(SelledEnt ue);
    */
    
    
}
