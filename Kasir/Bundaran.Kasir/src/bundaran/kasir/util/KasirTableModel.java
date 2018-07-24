/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir.util;

import java.math.BigInteger;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author D
 */
public class KasirTableModel extends AbstractTableModel {
    
    private int colNum;
    private ArrayList<String> colNames = new ArrayList<>();
    private ArrayList<String[]> ResultSets;
    
    
     public KasirTableModel (String[] columnNames, ArrayList<ArrayList<String>> list, boolean auto, boolean noNumber, int[] forNumberException,  String... forNumber){
         
         int up = (auto) ? 1 : 0;
         int extra = (auto) ? 0 : 1;
         
         extra = (noNumber) ? 0 : extra;
         
         if (list.isEmpty()) {
             return;
         }
 
        ResultSets = new ArrayList<>();
        colNum = list.get(0).size() + extra;
        
         if (!noNumber) 
             colNames.add("   No");
        
        boolean n = auto;
        for (String d : columnNames) {
            if (n == true) {
                n = false;
                continue;
            }            
            colNames.add("   " + d);            
        }
        
        
        int i = 0;
        try {            
            
            for (ArrayList<String> arrayList : list) {
                String[] row = new String[arrayList.size() + extra];
                //boolean[] canEdit = new boolean [arrayList.size() + extra];
                
                if (!noNumber) 
                    row[0] = "    " +String.valueOf(i+1);
                
                for (int j = up; j < arrayList.size(); j++) {
                    String v = arrayList.get(j);
                    try {
                        String split = (forNumber == null) ? null : forNumber[0];
                        String money = (forNumber.length > 0) ? null : forNumber[1];
                        BigInteger.valueOf(Long.parseLong(v));
                        NumberValidation valid = new NumberValidation(
                                split, money);
                        
                        if (forNumber[0] != null) {
                            boolean same = false;
                            if (forNumberException != null) {
                                for (int ex : forNumberException) {
                                    if (ex == j) {
                                        same = true;
                                        break;
                                    }
                                }
                            }
                            
                            if (!same) 
                                v = valid.fieldChecked(v);
                        } 
                    } catch (Exception e) {
                    }
                    
                    row[j + extra] = "    " + v;
                    //canEdit[j + extra] = false;
                }
                
                i++;
                ResultSets.add(row);
            }
        } catch (Exception e) {
        }
                
    }
     
    @Override
     public int getRowCount() {
        if (ResultSets == null) 
            return 0;
        else
            return ResultSets.size();
    }

    @Override
    public int getColumnCount() {
        return colNum;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] row = ResultSets.get(rowIndex);
        return row[columnIndex];
    }

    @Override
    public String getColumnName(int param) {
       return colNames.get(param);
    }
    
    
    
}
