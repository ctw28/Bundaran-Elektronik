/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.util;

import annas.jdbc.service.impl.SqlImplementImpl;
import annas.jdbc.service.model.QueryAttribut;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import bundaran.administrator.backgroundtask.LocalRequestVoidCallbackTask;

/**
 *
 * @author AsyncTask.Void.087
 */
public class RewriteDatabase implements LocalRequestVoidCallbackTask {

    ArrayList<ArrayList<String>>[] data;
    SqlImplementImpl impl;

    public RewriteDatabase(ArrayList<ArrayList<String>>... data) {
        this.data = data;
    }

    public boolean execute(String... tableNames) {

        try {
            if (deleteAllData(tableNames)) {

                int i = 0;
                for (String tableName : tableNames) {

                    ArrayList<ArrayList<String>> tableData = data[i];

                    if (tableData != null) {
                        for (ArrayList<String> param : tableData) {
                            QueryAttribut attribut = new QueryAttribut(false, null, tableName, param.toArray());
                            impl = new SqlImplementImpl();
                            if (!impl.goInsert(attribut)) {
                                return false;
                            }
                        }
                    }
                    i++;
                }
            }

            return true;
        } catch (RemoteException ex) {
            Logger.getLogger(RewriteDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public void processDone(String resultMessage) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean deleteAllData(String... tableNames) throws RemoteException {

        for (String tableName : tableNames) {
            impl = new SqlImplementImpl();
            QueryAttribut query = new QueryAttribut(false, null, tableName);
            impl.goDelete(query);
        }

        return true;
    }

}
