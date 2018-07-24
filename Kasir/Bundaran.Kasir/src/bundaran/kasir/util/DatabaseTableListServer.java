/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir.util;

import java.util.Map;

/**
 *
 * @author AsyncTask.Void.087
 */
public class DatabaseTableListServer {
    
    
    public static String[] TableNames = null;
    public static Map<String, String[]> ColumnList = null;

    public DatabaseTableListServer() {
    }

    public static String[] getTableNames() {
        return TableNames;
    }

    public static Map<String, String[]> getColumnList() {
        return ColumnList;
    }

    public static void setTableNames(String[] TableNames) {
        DatabaseTableListServer.TableNames = TableNames;
    }

    public static void setColumnList(Map<String, String[]> ColumnList) {
        DatabaseTableListServer.ColumnList = ColumnList;
    }
    
    
}
