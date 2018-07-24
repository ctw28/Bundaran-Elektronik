/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import bundaran.administrator.backgroundtask.ServerRequestController;
import bundaran.administrator.view.progress.RequestProgress;

/**
 *
 * @author AsyncTask.Void.087
 */
public class RefreshContentData {
    
    
    String backTaskID;
    Object instance;
    RequestProgress progress;

    public RefreshContentData() {
    }

    public RefreshContentData(Object instance, String backTaskID, RequestProgress progress) {
        this.backTaskID = backTaskID;
        this.instance = instance;
        this.progress = progress;
    }
    
    
    private ServerRequestController getRequestInstance(Object objectProgress, List<NameValuePair> pairs, String process, String message) {
        ServerRequestController controller = new ServerRequestController(backTaskID, instance, objectProgress, process, pairs, message);
        return controller;
    }
    
    
    
    public synchronized void enchanceProductData() {
        getRequestInstance(progress, null, "SELECT_ALL_PRODUCT", "Sedang mengunduh data barang").execute();
    }

    public synchronized void enchanceUserConfirmation(String... param) {
        String pengguna = param[0]; // username
        String sandi = param[1];// password
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("username", pengguna));
        pairs.add(new BasicNameValuePair("password", sandi));
        pairs.add(new BasicNameValuePair("type", "Owner"));

        getRequestInstance(progress, pairs, "LOGIN", "Sedang mengkonfirmasi kombinasi username").execute();
    }
    
    
    
    /**
     * Pengambilan data penjualan 
     */
    public synchronized void enchanceUser() {
        getRequestInstance(progress, null, "SELECT_ALL_SELLED", "Sedang mengambil konten").execute();
    }
    
    
    /**
     * Pengambilan data barang penjualan 
     */
    public synchronized void enchanceUserProduct() {

        getRequestInstance(progress, null, "SELECT_ALL_PRODUCT_SELLED", "Sedang mengambil konten").execute();
    }
    
    
    
    
    
    
    
    
}
