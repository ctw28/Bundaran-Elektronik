/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.view;

import annas.jdbc.service.configuration.MyTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import sun.awt.AppContext;
import bundaran.administrator.TokoBangunanAdministrator;
import bundaran.administrator.internet.entity.ProductEnt;
//import tokobangunan.administrator.internet.impl.ProductImpl;
import bundaran.administrator.internet.impl.ProductImpl;
import bundaran.administrator.report.DataBarangReport;
import bundaran.administrator.util.ContextMenuUI;
import bundaran.administrator.util.CustomComboBox;
import bundaran.administrator.util.MoveFrameListener;
import bundaran.administrator.util.NumberValidation;
import bundaran.administrator.view.progress.RequestProgress;

/**
 *
 * @author AsyncTask.Void.087
 */
public final class DataBarangMainx extends javax.swing.JFrame {

    /**
     * Creates new form DataBarangMain
     */
    String tahun = "";
    String bulan = "";
    String hari = "";
    ArrayList<ArrayList<String>> tableData;
    public ArrayList<ArrayList<String>> dataOriginal;
    String filterString = "";

    public DataBarangMainx() {
        initComponents();
        setLocationRelativeTo(null);

        requestDataBarangToServer();
        numberNameBarcodeFilter.requestFocus();
        forCalender();
    }

    void setSincronyzedDay() {
        dayChooser.setMonth(monthChooser.getMonth());
        dayChooser.setYear(yearChooser.getYear());

        if (!useDay.isSelected()) {
            dayChooser.setEnabled(false);
        }
    }

    void forCalender() {

        //.setRenderer(new CustomComboBox());
        final JComboBox boxMont = (JComboBox) monthChooser.getComboBox();
        boxMont.setFocusable(false);
        boxMont.setRenderer(new CustomComboBox());
        boxMont.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                setSincronyzedDay();
            }
        });

        JSpinner a = (JSpinner) yearChooser.getSpinner();
        a.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                int year = (int) a.getValue();
                yearChooser.setValue(year);
                setSincronyzedDay();
            }
        });
        JTextField field = (JTextField) a.getEditor();
        field.setEditable(false);
        field.setBackground(Color.WHITE);
    }

    /**
     * Melakukan request data barang ke server
     */
    private void requestDataBarangToServer() {
        String backTaskID = TokoBangunanAdministrator.getIDTask();
        RequestProgress progress = new RequestProgress(this, backTaskID);
        progress.setVisible(true);

        List<NameValuePair> list = new ArrayList<>();

        ProductEnt ent = new ProductEnt(progress, list, "SELECT_ALL_PRODUCT", "Sedang merefresh konten...");
        ProductImpl impl = new ProductImpl(backTaskID, this);
        impl.enchanceSelectAll(ent);
    }

    public void setDataOriginal(ArrayList<ArrayList<String>> dataOriginal) {
        this.dataOriginal = dataOriginal;
    }

    public void refreshTable(ArrayList<ArrayList<String>> temp) throws RemoteException {

        tableData = new ArrayList<>();
        tableData = temp;
        ArrayList<ArrayList<String>> tableDataShow = new ArrayList<>();

        progressText2.setText("Ada " + tableData.size() + " buah Data Barang " + filterString);
//        System.out.println(tableData.get(1));

//            for (ArrayList<String> d : tableData) {
//                man.add(d.get(0));
//                man.add(d.get(1));
//                man.add(d.get(3));
//                man.add(d.get(5));
//                man.add(d.get(10));
//            }
//                System.out.println(tableData.get(0).get(1));
//        ArrayList<String> man = new ArrayList<>();
//        ArrayList<String> man2 = new ArrayList<>();
//        ArrayList<ArrayList<String>> tableDataShow2 = new ArrayList<>();
//            for(int i = 0; i<tableData.size(); i++){
//                man.add(tableData.get(i).get(0));
//                man.add(tableData.get(i).get(1));
//                man.add(tableData.get(i).get(3));
//                man.add(tableData.get(i).get(5));
//                man.add(tableData.get(i).get(10)); 
//               
//                    
//            }
////                    System.out.println(tableDataShow);
//            System.out.println(man);
//            int j=0;
//            for(int i = 0; i<man.size(); i++){
//                man2.add(j, man.get(i));
//                if(i==(tableData.size()*(j+1))-1){
//                    man2.add(j, man.get(i));
//                j++;
//                }
//            }
//            System.out.println();
//            System.out.println();
//            System.out.println(man2);
            
                
//            tableDataShow.add(tableDataShow2);
        

//        String[] colNames = new String[]{"Nomor Barcode", "Nama Barang", "Modal (Rp)", "Harga (Rp)", "Tanggal Masuk", "Stok Barang", "Type", "Item", "Supplier", "Tanggal Transaksi" ,"Keterangan","ket_sup","harga_sup"};
        String[] colNames = new String[]{"Nomor Barcode", "Nama Barang", "Harga (Rp)", "Stok Barang", "Type" , "Keterangan"};
        MyTableModel model = new MyTableModel(colNames, tableData, false, false, new int[]{0}, ".");
        tableProduct.setModel(model);
        tableProduct.removeAll();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        progressText1 = new javax.swing.JLabel();
        progressText7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        numberNameBarcodeFilter = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        progressText3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        dayChooser = new com.toedter.calendar.JDayChooser();
        useDay = new javax.swing.JCheckBox();
        monthChooser = new com.toedter.calendar.JMonthChooser();
        useMonth = new javax.swing.JCheckBox();
        yearChooser = new com.toedter.calendar.JYearChooser();
        useYear = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        progressText2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        progressText4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        SearchButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        jPopupMenu1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPopupMenu1.setOpaque(false);

        jMenuItem1.setUI(new ContextMenuUI());
        jMenuItem1.setBackground(new java.awt.Color(84, 83, 83));
        jMenuItem1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jMenuItem1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/update.png"))); // NOI18N
        jMenuItem1.setText("   Ubah Data   ");
        jMenuItem1.setMargin(new java.awt.Insets(10, 15, 10, 12));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setUI(new ContextMenuUI());
        jMenuItem2.setBackground(new java.awt.Color(84, 83, 83));
        jMenuItem2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jMenuItem2.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/delete.png"))); // NOI18N
        jMenuItem2.setText("   Hapus Data   ");
        jMenuItem2.setMargin(new java.awt.Insets(10, 15, 10, 12));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(246, 246, 246));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        tableProduct.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProduct.setRowHeight(30);
        tableProduct.getTableHeader().setReorderingAllowed(false);
        tableProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableProductMousePressed(evt);
            }
        });
        tableProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableProductKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableProduct);
        if (tableProduct.getColumnModel().getColumnCount() > 0) {
            tableProduct.getColumnModel().getColumn(0).setResizable(false);
            tableProduct.getColumnModel().getColumn(1).setResizable(false);
            tableProduct.getColumnModel().getColumn(2).setResizable(false);
        }
        tableProduct.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    c.setBackground(new Color(0xfe8900));
                } else {
                    c.setBackground(new Color(0xffffff));
                }

                return c;
            }

        });

        MoveFrameListener frameListenerx = new MoveFrameListener(jPanel2);
        addMouseListener(frameListenerx);
        addMouseMotionListener(frameListenerx);
        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        progressText1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        progressText1.setForeground(new java.awt.Color(255, 255, 255));
        progressText1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/logo14.PNG"))); // NOI18N
        progressText1.setText("   Aplikasi Pendataan Barang Toko Bundaran Elektronik");

        progressText7.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        progressText7.setForeground(new java.awt.Color(255, 255, 255));
        progressText7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/close.png"))); // NOI18N
        progressText7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                progressText7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(progressText1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 887, Short.MAX_VALUE)
                .addComponent(progressText7)
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressText7)
                    .addComponent(progressText1))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        numberNameBarcodeFilter.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        numberNameBarcodeFilter.setMargin(new java.awt.Insets(2, 10, 2, 10));
        numberNameBarcodeFilter.setNextFocusableComponent(SearchButton);
        numberNameBarcodeFilter.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                numberNameBarcodeFilterCaretUpdate(evt);
            }
        });
        numberNameBarcodeFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberNameBarcodeFilterActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("Nama/Nomor Barcode ");

        progressText3.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        progressText3.setText("Opsi Pencarian Tingkat Lanjut :");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        useDay.setBackground(new java.awt.Color(255, 255, 255));
        useDay.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        useDay.setForeground(new java.awt.Color(204, 0, 0));
        useDay.setText("  Gunakan Hari");
        useDay.setFocusable(false);
        useDay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                useDayItemStateChanged(evt);
            }
        });

        monthChooser.setFocusable(false);

        useMonth.setBackground(new java.awt.Color(255, 255, 255));
        useMonth.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        useMonth.setForeground(new java.awt.Color(204, 0, 0));
        useMonth.setText("  Gunakan Bulan");
        useMonth.setFocusable(false);
        useMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                useMonthItemStateChanged(evt);
            }
        });

        useYear.setBackground(new java.awt.Color(255, 255, 255));
        useYear.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        useYear.setForeground(new java.awt.Color(0, 153, 0));
        useYear.setSelected(true);
        useYear.setText("  Gunakan Tahun");
        useYear.setFocusable(false);
        useYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                useYearItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(useDay)
                    .addComponent(dayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(useYear)
                    .addComponent(useMonth)
                    .addComponent(monthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(useMonth)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(monthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(useYear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(useDay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel14.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        jLabel14.setText("Tanggal Barang Masuk");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(progressText3)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel14))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40))
                    .addComponent(numberNameBarcodeFilter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(11, 11, 11)
                        .addComponent(numberNameBarcodeFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(progressText3, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );

        progressText2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        progressText2.setText("List Data Barang");

        jButton3.setUI(new bundaran.administrator.util.WarningUI());
        jButton3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/refresh.PNG"))); // NOI18N
        jButton3.setText("Segarkan   ");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        progressText4.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        progressText4.setText("Kontrol Fungsi :");

        jButton4.setUI(new bundaran.administrator.util.WarningUI());
        jButton4.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/print.PNG"))); // NOI18N
        jButton4.setText("Cetak");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setNextFocusableComponent(numberNameBarcodeFilter);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setUI(new bundaran.administrator.util.WarningUI());
        jButton2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/add.PNG"))); // NOI18N
        jButton2.setText("Tambah");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setNextFocusableComponent(jButton4);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        SearchButton.setUI(new bundaran.administrator.util.WarningUI());
        SearchButton.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        SearchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/searh32.PNG"))); // NOI18N
        SearchButton.setText("Temukan");
        SearchButton.setFocusable(false);
        SearchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SearchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        jLabel13.setText("Toko Bundaran Elektronik, Kendari, Sulawesi Tenggara");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(SearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(progressText4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(progressText4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(progressText2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1179, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(progressText2)
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        try {
            tableProduct.getSelectedRow();
        } catch (Exception e) {
            numberNameBarcodeFilter.requestFocus();
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        try {
            int indexTable = tableProduct.getSelectedRow();
            new DataBarangSubMain(this, tableData.get(indexTable)).setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    private void tableProductMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductMousePressed
        if (SwingUtilities.isRightMouseButton(evt)) {
            jPopupMenu1.show(this, (int) evt.getXOnScreen() - x, (((int) evt.getYOnScreen()) - y));
        }
    }//GEN-LAST:event_tableProductMousePressed

    int x, y;
    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved

        x = evt.getComponent().getX();
        y = evt.getComponent().getY();

    }//GEN-LAST:event_formComponentMoved

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        try {
            int row = tableProduct.getSelectedRow();
            List<NameValuePair> pairing = new ArrayList<>();
            pairing.add(new BasicNameValuePair("nomor_barcode", tableData.get(row).get(0)));

            int y = JOptionPane.showConfirmDialog(null, "Yakin akan dihapus?");
            if (y == 0) {
                String backTaskID = TokoBangunanAdministrator.getIDTask();
                RequestProgress progress = new RequestProgress(this, backTaskID);
                progress.setVisible(true);

                ProductEnt ent = new ProductEnt(progress, pairing, "DELETE_PRODUCT", "Mohon menunggu...");
                ProductImpl impl = new ProductImpl(backTaskID, this);
                impl.enchanceDelete(ent);
            }

        } catch (Exception e) {
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void tableProductKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableProductKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (tableProduct.isFocusable()) {
                jMenuItem2ActionPerformed(null);
            }
        }
    }//GEN-LAST:event_tableProductKeyPressed

    private void progressText7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_progressText7MouseClicked

        dispose();
    }//GEN-LAST:event_progressText7MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        new DataBarangSubMain(this, null).setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    String addZero(int value) {
        if (value < 10) {
            return "0" + String.valueOf(value);
        }

        return String.valueOf(value);
    }

    String penampilan = "";
    String rentang = "";


    /**
     *
     * @param param 0 = type, 1 = gudang, 2 = barcode/ nama barang 3 = stok
     * barang dari 4 = stok barang ke 5 = waktu/ tgl
     * @return
     */
    private ArrayList<ArrayList<String>> searching(String... param) {
        ArrayList<ArrayList<String>> temp;

        /**
         * Mencari pada nama barcode
         */
        temp = searchingMethod(new int[]{1, 2, 3}, param);

        /**
         * mencari pada nomor barcode
         */
        if (temp.isEmpty()) {
            temp = searchingMethod(new int[]{0, 2, 3}, param);
        }

        return temp;
    }

    private ArrayList<ArrayList<String>> searchingMethod(int[] column, String[] param) {
        ArrayList<ArrayList<String>> temp = new ArrayList<>();

        for (ArrayList<String> arrayList : dataOriginal) {
//            System.out.println(arrayList);
            if (arrayList.get(column[0]).toLowerCase().contains(param[0].toLowerCase())) {
                temp.add(arrayList);
            }

        }

        return temp;
    }


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        String[] colNames = new String[]{"Nomor Barcode", "Nama Barang", "Harga (Rp)", "Stok Barang", "Type" ,"Keterangan"};

        DataBarangReport cell = new DataBarangReport(colNames, tableData, "DATA BARANG", false, "data_barang.pdf");

        String filterType = "";
        String filterInventory = "";
        String key = numberNameBarcodeFilter.getText();

        if (cell.doCreateReport(filterType, filterInventory, key, rentang, penampilan)) {
            File file = new File("data_barang.pdf");
            if (file.exists()) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                    Logger.getLogger(DataBarangMainx.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        filterString = "";
        penampilan = "";
        rentang = "";

        hari = "";
        bulan = "";
        tahun = "";
        numberNameBarcodeFilter.setText("");
        requestDataBarangToServer();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed


        String NumberOrName = numberNameBarcodeFilter.getText();

        tahun = (useYear.isSelected()) ? String.valueOf(yearChooser.getYear()) : "";
        bulan = (useMonth.isSelected()) ? ((monthChooser.getMonth() + 1 < 10) ? "0" + String.valueOf(monthChooser.getMonth() + 1) : String.valueOf(monthChooser.getMonth() + 1)) : "";
        hari = (useDay.isSelected()) ? ((dayChooser.getDay() < 10) ? "0" + String.valueOf(dayChooser.getDay()) : String.valueOf(dayChooser.getDay())) : "";

        String paramWaktuTransaksi
        = (useYear.isSelected() ? String.valueOf(yearChooser.getYear()) : "")
        + (useMonth.isSelected() ? ("-" + addZero(monthChooser.getMonth() + 1)) : "")
        + (useDay.isSelected() ? ("-" + addZero(dayChooser.getDay())) : "");

        penampilan = ((!hari.isEmpty() || !bulan.isEmpty() || !tahun.isEmpty()) ? " Barang masuk pada" : "") + ((!hari.isEmpty()) ? " Tanggal " + hari : "")
        + ((!bulan.isEmpty()) ? " Bulan " + bulan : "")
        + ((!tahun.isEmpty()) ? " Tahun " + tahun : "");
        
        try {
            refreshTable(searching(NumberOrName, paramWaktuTransaksi));
        } catch (RemoteException ex) {
            Logger.getLogger(DataBarangMainx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void useYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_useYearItemStateChanged
        if (!useYear.isSelected()) {
            useMonth.setSelected(false);
            useYear.setForeground(new java.awt.Color(204, 0, 0));
            yearChooser.setEnabled(false);

        } else {
            useMonth.setSelected(true);
            useYear.setForeground(new java.awt.Color(0, 153, 0));
            yearChooser.setEnabled(true);
        }
    }//GEN-LAST:event_useYearItemStateChanged

    private void useMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_useMonthItemStateChanged

        if (!useMonth.isSelected()) {
            useDay.setSelected(false);
            useMonth.setForeground(new java.awt.Color(204, 0, 0));
            monthChooser.setEnabled(false);
        } else {
            if (!useYear.isSelected()) {
                useMonth.setSelected(false);
                return;
            }
            useDay.setSelected(true);
            useMonth.setForeground(new java.awt.Color(0, 153, 0));
            monthChooser.setEnabled(true);
        }
    }//GEN-LAST:event_useMonthItemStateChanged

    private void useDayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_useDayItemStateChanged

        if (!useDay.isSelected()) {
            useDay.setForeground(new java.awt.Color(204, 0, 0));
            dayChooser.setEnabled(false);
        } else if (useDay.isSelected()) {
            if (!useMonth.isSelected()) {
                useDay.setSelected(false);
                return;
            }
            useDay.setForeground(new java.awt.Color(0, 153, 0));
            dayChooser.setEnabled(true);
        }
    }//GEN-LAST:event_useDayItemStateChanged

    private void numberNameBarcodeFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberNameBarcodeFilterActionPerformed

        //        SearchButtonActionPerformed(evt);
    }//GEN-LAST:event_numberNameBarcodeFilterActionPerformed

    private void numberNameBarcodeFilterCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_numberNameBarcodeFilterCaretUpdate

        // SearchButtonActionPerformed(null);
    }//GEN-LAST:event_numberNameBarcodeFilterCaretUpdate

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataBarangMainx.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataBarangMainx.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataBarangMainx.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataBarangMainx.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataBarangMainx().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SearchButton;
    private com.toedter.calendar.JDayChooser dayChooser;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JMonthChooser monthChooser;
    private javax.swing.JTextField numberNameBarcodeFilter;
    private javax.swing.JLabel progressText1;
    private javax.swing.JLabel progressText2;
    private javax.swing.JLabel progressText3;
    private javax.swing.JLabel progressText4;
    private javax.swing.JLabel progressText7;
    private javax.swing.JTable tableProduct;
    private javax.swing.JCheckBox useDay;
    private javax.swing.JCheckBox useMonth;
    private javax.swing.JCheckBox useYear;
    private com.toedter.calendar.JYearChooser yearChooser;
    // End of variables declaration//GEN-END:variables
}
