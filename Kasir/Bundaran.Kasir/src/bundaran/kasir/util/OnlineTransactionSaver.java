/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir.util;

import annas.jdbc.service.configuration.DatabaseTableList;
import annas.jdbc.service.impl.SqlImplementImpl;
import annas.jdbc.service.model.QueryAttribut;
import java.awt.HeadlessException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import bundaran.kasir.backgroundtask.RequestVoidCallbackTask;
import bundaran.kasir.backgroundtask.ServerRequestController;
import bundaran.kasir.view.Kasir;
import bundaran.kasir.view.progress.RequestProgress;

/**
 *
 * @author AsyncTask.Void.087
 */
public class OnlineTransactionSaver implements RequestVoidCallbackTask {

    int dataBarangIndex = 0;
    Kasir kasir;
    String backTaskID;
    RequestProgress progress;
    ArrayList<String> dataPembeli;
    ArrayList<ArrayList<String>> dataBarang;
    public boolean RUNNING;

    ArrayList<ArrayList<String>> dataSemuaPembeli = null;
    ArrayList<ArrayList<String>> dataSemuaBarang = null;
    int indexSemua = 0;
    
    OfflineTransactionSaver offlineTransactionSaver;

    boolean executeAll;

    public OnlineTransactionSaver() {
        executeAll = false;
    }

    public OnlineTransactionSaver(Kasir kasir, String backTaskID) {
        executeAll = false;
        this.kasir = kasir;
        this.backTaskID = backTaskID;
    }

    public OnlineTransactionSaver(Kasir kasir, String backTaskID, RequestProgress progress) {
        executeAll = false;
        this.kasir = kasir;
        this.backTaskID = backTaskID;
        this.progress = progress;
    }

    public void execute(ArrayList<String> dataPembeli, ArrayList<ArrayList<String>> dataBarang) {
        
        /**
         * save offline jika ada data yg gagal di save online
         */
        offlineTransactionSaver = new OfflineTransactionSaver(dataPembeli, dataBarang);
        
        this.dataPembeli = dataPembeli;
        this.dataBarang = dataBarang;
//        System.out.println(dataBarang);
//        System.out.println(dataPembeli);

        processDone(null, "SAVE_TRANSACTION_PRODUCT");
    }

    public void executeAll(ArrayList<ArrayList<String>> dataPembeli, ArrayList<ArrayList<String>> dataBarang) {
        this.dataSemuaPembeli = dataPembeli;
        this.dataSemuaBarang = dataBarang;

        executeThisAll();
    }

    private void executeThisAll() {
        /**
         * save offline jika ada data yg gagal di save online
         */
        offlineTransactionSaver = new OfflineTransactionSaver(dataPembeli, dataBarang);
        
        executeAll = true;
        if (dataSemuaPembeli != null && indexSemua < dataSemuaPembeli.size()) {
            dataBarangIndex = 0;
            dataPembeli = new ArrayList<>();
            dataPembeli = dataSemuaPembeli.get(indexSemua);
            dataBarang = dataBarangBy(dataPembeli.get(0));
            processDone(null, "SAVE_TRANSACTION_PRODUCT");
        } else {
            /**
             * Refresh Semua data
             */
            RefreshAllData allData = new RefreshAllData(kasir, backTaskID, progress);
            allData.execute();
            
            /**
             * Menghaps data lokal yangtelah tersimpan di database pusat
             */
            deleteLocalData();
        }

        indexSemua++;
    }

    private ServerRequestController getRequestInstance(Object objectProgress, List<NameValuePair> pairs, String process, String message) {
        ServerRequestController controller = new ServerRequestController(backTaskID, this, objectProgress, process, pairs, message);
        return controller;
    }

    /**
     * Delete data yang ada pada database lokal
     */
    private void deleteLocalData() {
        try {
            if (dataSemuaPembeli == null) {
                return;
            }
            
            indexSemua--;

            if (indexSemua != -1) {
                String tableNamePT = DatabaseTableList.getTableNames()[3]; // tabel penjualan barang tunda
                String tableNamePBT = DatabaseTableList.getTableNames()[5]; // tabel penjualan barang tunda
                QueryAttribut attribut;
                SqlImplementImpl query;

                for (int i = 0; i <= indexSemua; i++) {
                    String idTransaksi = dataSemuaPembeli.get(i).get(0); // 0 urutan untuk id transaksi

                    boolean fail = false;
                    for (ArrayList<String> string : dataSemuaBarang) {
                        if (string.get(1).equals(idTransaksi)) {
                            String nomorBarcode = string.get(0); // 0 untuk nomor barcode urutannya
                            attribut = new QueryAttribut(false,
                                    new String[]{DatabaseTableList.getColumnList().get(tableNamePBT)[0],
                                                 DatabaseTableList.getColumnList().get(tableNamePBT)[1]},
                                    tableNamePBT,
                                    nomorBarcode, idTransaksi
                            );
                            
                            query = new SqlImplementImpl();
                            if (!query.goDelete(attribut)) {
                                fail = true;
                                break;
                            }
                        }
                    }

                    if (fail) {
                        break;
                    }
                    
                    attribut = new QueryAttribut(false,
                            new String[]{DatabaseTableList.getColumnList().get(tableNamePT)[0]},
                            tableNamePT,
                            idTransaksi
                    );
                    query = new SqlImplementImpl();
                    if (!query.goDelete(attribut)) {
                        break;
                    }
                }

            }
        } catch (RemoteException | HeadlessException e) {
        }

    }

    
    
    @Override
    public void processDone(JSONObject jSONObject, String process) {
        switch (process) {
            case "SAVE_TRANSACTION_PRODUCT":
                if (dataBarangIndex > 0 && jSONObject == null) {
                    if (executeAll) {
                        deleteLocalData();
                    } else {
                        offlineTransactionSaver.execute(dataBarangIndex);
                    }
                    return;
                }

                if (dataBarangIndex > 0 && jSONObject.getString("message").equals("Terjadi Kesalahan ...")) {
                    if (executeAll) {
                        deleteLocalData();
                    } else {
                        offlineTransactionSaver.execute(dataBarangIndex);
                    }
                    return;
                }

                if ((dataBarang.size()) > dataBarangIndex) {
//                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                    getRequestInstance(null, enchanceData(dataBarang.get(dataBarangIndex),
                            staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_PENJUALAN_BARANG),
                            "SAVE_TRANSACTION_PRODUCT", "Sedang menyimpan data barang...").execute();
                    dataBarangIndex++;
                } else {
                    System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
                    List<NameValuePair> a = enchanceData(dataPembeli, staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_PENJUALAN);
                    getRequestInstance(null, a,
                            "SAVE_TRANSACTION", "Sedang menyimpan data pembeli...").execute();
                }
                break;
            case "SAVE_TRANSACTION":
                String result = jSONObject.getString("message");
                if (result.equals("Terjadi Kesalahan ...")) {
                    if (executeAll) {
                        deleteLocalData();
                    } else {
                        offlineTransactionSaver.execute(dataBarangIndex);
                    }
                    break;
                }
                //RefreshAllData allData = new RefreshAllData(this, backTaskID, rp);
                //allData.execute();
                executeThisAll();
                break;

            default:
        }
    }

    @Override
    public void httpParsingError(String process, String message) {

    }

    private ArrayList<ArrayList<String>> dataBarangBy(String id) {
        dataBarang = new ArrayList<>();

        for (ArrayList<String> arrayList : dataSemuaBarang) {
            if (arrayList.get(1).equals(id)) {
                //list.add(arrayList);
                dataBarang.add(arrayList);
            }
        }

        return dataBarang;
    }

    /**
     *
     * @param data
     * @return
     */
    synchronized List<NameValuePair> enchanceData(ArrayList<String> data, String[] identifier) {
        List<NameValuePair> pairs = new ArrayList<>();

        int i = 0;
        for (String pair : identifier) {
            pairs.add(new BasicNameValuePair(pair, data.get(i)));
            i++;
        }

        return pairs;
    }

}
