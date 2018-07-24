/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.internet.entity;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import bundaran.administrator.util.staticIdentifier;

/**
 *
 * @author AsyncTask.Void.087
 */
public final class SelledEnt {
    
    /**
     * Loading progress
     */
    private Object objectProgress;
    
    
    /**
     * Pairs data 
     */
    private List<NameValuePair> pairs;
    
    
    /**
     * Data pairs dalam object
     */
    private ArrayList<Object> pairsObject;
    
    /**
     * Process Identifier
     */
    private String process;
    
    
    /**
     * Pesan pada loading progress
     */
    private String message;
    
    
    
    public SelledEnt() {
    }

    
    /**
     * 
     * @param objectProgress
     * @param pairsObject
     * @param process
     * @param message 
     */
    public SelledEnt(Object objectProgress, ArrayList<Object> pairsObject, String process, String message) {
        this.objectProgress = objectProgress;
        setPairsObject(pairsObject);
        this.process = process;
        this.message = message;
    }
    
    
    /**
     * 
     * @param objectProgress
     * @param pairs
     * @param process
     * @param message 
     */
    public SelledEnt(Object objectProgress, List<NameValuePair> pairs, String process, String message) {
        this.objectProgress = objectProgress;
        this.pairs = pairs;
        this.process = process;
        this.message = message;
    }

    public void setPairsObject(ArrayList<Object> pairsObject) {
        pairs = convertArrayListToPairs(pairsObject);
    }

    public void setObjectProgress(Object objectProgress) {
        this.objectProgress = objectProgress;
    }

    public void setPairs(List<NameValuePair> pairs) {
        this.pairs = pairs;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    private List<NameValuePair> convertArrayListToPairs(ArrayList<Object> pairsObject){
        List<NameValuePair> pairing = new ArrayList<>();
        
        int i = 0;
        for (String pair : staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_BARANG) {
            pairing.add(new BasicNameValuePair(pair, String.valueOf(pairsObject.get(i))));
            i++;
        }
        
        return pairing;
    }
    
    
    

    public Object getObjectProgress() {
        return objectProgress;
    }

    public List<NameValuePair> getPairs() {
        return pairs;
    }

    public String getProcess() {
        return process;
    }

    public String getMessage() {
        return message;
    }

    
    @Override
    public String toString() {
        return "UserEnt{" + "objectProgress=" + objectProgress + ", pairs=" + pairs + ", process=" + process + ", message=" + message + '}';
    }
    
    
}