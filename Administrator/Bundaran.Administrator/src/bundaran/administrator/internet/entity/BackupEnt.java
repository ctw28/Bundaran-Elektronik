/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.internet.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import bundaran.administrator.util.staticIdentifier;

/**
 *
 * @author AsyncTask.Void.087
 */
public final class BackupEnt {
    
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
    
    
    private File fileBackupName;
    
    
    public BackupEnt() {
    }
    
    
    Map<String, ArrayList<ArrayList<String>>> mapRestoreData;

    
    /**
     * 
     * @param objectProgress
     * @param pairsObject
     * @param process
     * @param message 
     */
    public BackupEnt(Object objectProgress, ArrayList<Object> pairsObject, String process, String message, File fileBackupName) {
        this.objectProgress = objectProgress;
        setPairsObject(pairsObject);
        this.process = process;
        this.message = message;
        this.fileBackupName = fileBackupName;
    }
    
    
    /**
     * 
     * @param objectProgress
     * @param pairs
     * @param process
     * @param message 
     * @param fileBackupName 
     */
    public BackupEnt(Object objectProgress, List<NameValuePair> pairs, String process, String message, File fileBackupName) {
        this.objectProgress = objectProgress;
        this.pairs = pairs;
        this.process = process;
        this.message = message;
        this.fileBackupName = fileBackupName;
    }
    
    
    /**
     * 
     * @param objectProgress
     * @param pairs
     * @param process
     * @param message 
     * @param fileBackupName 
     * @param mapRestoreData 
     */
    public BackupEnt(Object objectProgress, List<NameValuePair> pairs, String process, String message, File fileBackupName,
            Map<String, ArrayList<ArrayList<String>>> mapRestoreData) {
        this.objectProgress = objectProgress;
        this.pairs = pairs;
        this.process = process;
        this.message = message;
        this.fileBackupName = fileBackupName;
        this.mapRestoreData = mapRestoreData;
    }

    public Map<String, ArrayList<ArrayList<String>>> getMapRestoreData() {
        return mapRestoreData;
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