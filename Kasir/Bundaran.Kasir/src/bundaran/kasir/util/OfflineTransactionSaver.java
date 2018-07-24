/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir.util;

import annas.jdbc.service.configuration.DatabaseTableList;
import annas.jdbc.service.impl.SqlImplementImpl;
import annas.jdbc.service.model.PreparingSQLWriting;
import annas.jdbc.service.model.QueryAttribut;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AsyncTask.Void.087
 */
public class OfflineTransactionSaver {

    int indexUp;
    ArrayList<String> dataPembeli;
    ArrayList<ArrayList<String>> dataBarang;

    public OfflineTransactionSaver(ArrayList<String> dataPembeli, ArrayList<ArrayList<String>> dataBarang) {
        this.dataPembeli = dataPembeli;
        this.dataBarang = dataBarang;
        System.out.println(this.dataBarang);
        System.out.println(this.dataPembeli);
    }

    
    /**
     * 
     * @param indexUp
     * @return 
     */
    public boolean execute(int indexUp) {
        if (dataBarang == null || dataBarang == null) {
            return false;
        }

        try {
            /**
             * Save data yang tertunda ke database lokal
             */
            if (indexUp < dataBarang.size()) {

                boolean success = saveDataPembeli();
                for (int i = indexUp; i < dataBarang.size(); i++) {
                    if (success) {
                        if (!saveDataBarang(i)) {
                            break;   
                        }
                    } else {
                        break;
                    }
                }
                
                return success;
            } else {
                return saveDataPembeli();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(OfflineTransactionSaver.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private ArrayList<Object> convertToObjectList(ArrayList<String> data) {
        ArrayList<Object> list = new ArrayList<>();

        for (Object object : data) {
            list.add(object);
        }

        return list;
    }

    
    
    /**
     * Fungsi untuk menyimpan data pembeli ke database lokal
     * @return
     * @throws RemoteException 
     */
    private boolean saveDataPembeli() throws RemoteException {
        PreparingSQLWriting psw = new PreparingSQLWriting();
        SqlImplementImpl query = new SqlImplementImpl();
        
        Object[] param = psw.parameterReceived(null, convertToObjectList(dataPembeli));
        QueryAttribut attribut = new QueryAttribut(false, null,
                DatabaseTableList.getTableNames()[3], // urutan nama tabel penjualan  tunda
                param);

        return query.goInsert(attribut);
    }

    
    
    /**
     * Fungsi untuk mnyimpan data barang kedatabase lokal
     * @param i
     * @return
     * @throws RemoteException 
     */
    private boolean saveDataBarang(int i) throws RemoteException {
        PreparingSQLWriting psw = new PreparingSQLWriting();
        SqlImplementImpl query = new SqlImplementImpl();

        Object[] param = psw.parameterReceived(null, convertToObjectList(dataBarang.get(i)));
        QueryAttribut attribut = new QueryAttribut(false, null,
                DatabaseTableList.getTableNames()[5], // urutan nama tabel penjualan barang tunda
                param);
        
        
        return (query.goInsert(attribut));
    }
}
