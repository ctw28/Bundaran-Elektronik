/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir.util;

import annas.jdbc.service.configuration.DatabaseTableList;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import org.json.JSONArray;
import org.json.JSONObject;
import bundaran.kasir.backgroundtask.RequestVoidCallbackTask;
import bundaran.kasir.view.Kasir;
import bundaran.kasir.view.progress.RequestProgress;

/**
 *
 * @author AsyncTask.Void.087
 */
public class RefreshAllData implements RequestVoidCallbackTask {

    Kasir kasir;
    String backTaskID;
    RequestProgress progress = null;
    RefreshContentData rcd;

    public RefreshAllData() {
    }

    public RefreshAllData(Kasir kasir, String backTaskID) {
        this.kasir = kasir;
        this.backTaskID = backTaskID;

    }

    public RefreshAllData(Kasir kasir, String backTaskID, RequestProgress progress) {
        this.kasir = kasir;
        this.backTaskID = backTaskID;
        this.progress = progress;

    }

    public void execute() {
        rcd = new RefreshContentData(this, backTaskID, null);
        rcd.enchanceProductData();
    }

    @Override
    public void processDone(JSONObject jSONObject, String process) {
        switch (process) {
            case "SELECT_ALL_PRODUCT":
                kasir.dataBarang = new ArrayList<>();
                if (jSONObject != null) {
                    try {
                        JSONArray array2nd = jSONObject.getJSONArray("all_product_data");
                        if (array2nd != null && array2nd.length() > 0) {
                            kasir.dataBarang = convertJsonToList(array2nd, staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_BARANG);

                        }

                    } catch (Exception x) {
                    }
                }
                rcd.enchanceUserUnLunas();
                break;
            case "SELECT_ALL_SELLED_UNLUNAS":
                kasir.dataPenjualan = new ArrayList<>();

                if (jSONObject != null) {
                    //all_data_penjualan_by_status
                    try {
                        JSONArray array3rd = jSONObject.getJSONArray("all_data_penjualan_by_status");
                        if (array3rd != null && array3rd.length() > 0) {

                            kasir.dataPenjualan = convertJsonToList(array3rd, staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_PENJUALAN);

                        }
                    } catch (Exception x) {
                    }

                }

                rcd.enchanceUserProductUnLunas();
                break;
            case "SELECT_ALL_PRODUCT_SELLED_UNLUNAS":
                kasir.dataPenjualanBarang = new ArrayList<>();
                if (jSONObject != null) {
                    try {
                        JSONArray array4th = jSONObject.getJSONArray("all_data_penjualan_barang_by_status");
                        if (array4th != null && array4th.length() > 0) {
                            kasir.dataPenjualanBarang = convertJsonToList(array4th, staticIdentifier.DATABASE_SEVER_STRUCTURE.TABEL_PENJUALAN_BARANG);

                        }
                    } catch (Exception x) {
                    }
                }

                RewriteDatabase rd = new RewriteDatabase(
                        kasir.dataBarang, kasir.dataPenjualan, kasir.dataPenjualanBarang);
                rd.execute(DatabaseTableList.getTableNames()[0],
                        DatabaseTableList.getTableNames()[2],
                        DatabaseTableList.getTableNames()[4]
                );

                kasir.array = kasir.makeStringSuggestionProduct();
                DefaultComboBoxModel boxModel = new javax.swing.DefaultComboBoxModel<>(kasir.array);
                kasir.jComboBox1.setModel(boxModel);
                kasir.jComboBox1.setSelectedIndex(-1);
                kasir.jComboBox1.setEditable(true);
                kasir.jComboBox1.setSelectedItem("");
                kasir.jComboBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));                
                kasir.jComboBox1.setRenderer(new CustomComboBox());
                JTextField field = (JTextField) kasir.jComboBox1.getEditor().getEditorComponent();
                field.setText("");
                ComboKeyController box = new ComboKeyController(kasir, kasir.jComboBox1);
                
                field.addKeyListener(box);
                
                /*
                kasir.jComboBox1 = Kasir.makeComboBox(kasir.array);
                kasir.jComboBox1.setEditable(true);
                kasir.jComboBox1.setSelectedItem("");
                kasir.jComboBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                kasir.jComboBox1.setRenderer(new CustomComboBox());
                kasir.jComboBox1.setSelectedIndex(-1);
                JTextField field = (JTextField) kasir.jComboBox1.getEditor().getEditorComponent();
                field.setText("");
                ComboKeyController box = new ComboKeyController(kasir, kasir.jComboBox1);
                
                field.addKeyListener(box);
                kasir.pack();
                */
                //initComponents
                
                /*
                String resultFunction = "initComponents";
                //Class[] classTypes = {JSONObject.class, String.class};
                //Object[] dataReceived = {this.get(), process};

                Object object = kasir;
                try {
                    Method goResult = object.getClass().getDeclaredMethod(resultFunction);
                    goResult.setAccessible(true);
                    goResult.invoke(object);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(RefreshAllData.class.getName()).log(Level.SEVERE, null, ex);
                }   

                */
                /*
                Kasir k = new Kasir(kasir.dataBarang, kasir.dataPenjualan, kasir.dataPenjualanBarang, null);
                k.setVisible(true);
                kasir.dispose();
                 */
                if (progress != null) {
                    progress.dispose();
                }
                break;
        }
    }

    @Override
    public void httpParsingError(String process, String message) {

    }

    private void resetSuggestInComboBox() {
        kasir.jComboBox1 = new javax.swing.JComboBox<>();
        kasir.jComboBox1 = Kasir.makeComboBox(kasir.array);
        kasir.jComboBox1.setEditable(true);
        kasir.jComboBox1.setSelectedItem("");

        kasir.jComboBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        kasir.jComboBox1.setRenderer(new CustomComboBox());
        kasir.jComboBox1.setSelectedIndex(-1);
        JTextField field = (JTextField) kasir.jComboBox1.getEditor().getEditorComponent();
        field.setText("");
        field.addKeyListener(new ComboKeyController(kasir, kasir.jComboBox1));
    }

    private ArrayList<ArrayList<String>> convertJsonToList(JSONArray array, String[] tableStructure) {
        ArrayList<ArrayList<String>> temp = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            ArrayList<String> man = new ArrayList<>();
            for (String d : tableStructure) {
                String t = array.getJSONObject(i).getString(d);
                man.add(t);
            }
            temp.add(man);
        }

        return temp;
    }

}
