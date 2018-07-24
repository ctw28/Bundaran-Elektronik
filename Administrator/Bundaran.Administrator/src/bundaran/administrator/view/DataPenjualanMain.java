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
import bundaran.administrator.internet.entity.SelledEnt;
import bundaran.administrator.internet.impl.ProductImpl;
import bundaran.administrator.internet.impl.SelledImpl;
import bundaran.administrator.report.DataPenjualanDetailReport;
import bundaran.administrator.util.ContextMenuUI;
import bundaran.administrator.util.CustomComboBox;
import bundaran.administrator.util.MoveFrameListener;
import bundaran.administrator.util.NumberValidation;
import bundaran.administrator.view.progress.RequestProgress;

/**
 *
 * @author AsyncTask.Void.087
 */
public final class DataPenjualanMain extends javax.swing.JFrame {

    /**
     * Creates new form DataBarangMain
     */
    String penampilan = "";
    ArrayList<ArrayList<String>> tableData;
    public static ArrayList<ArrayList<String>> dataBarangOriginal;
    public ArrayList<ArrayList<String>> dataPenjualanBarangOriginal;
    public ArrayList<ArrayList<String>> dataPenjualanOriginal;

    int totalDataPerPage = 7;

    int page = 0;

    int totalPage = 0;

    public DataPenjualanMain() {
        initComponents();
        setLocationRelativeTo(null);

        requestDataBarangToServer();
        //numberNameBarcodeFilter.requestFocus();
        forCalender();
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

    public void setDataBarangOriginal(ArrayList<ArrayList<String>> dataBarangOriginal) {
        this.dataBarangOriginal = dataBarangOriginal;
//        System.out.println(dataBarangOriginal);
    }

    public void setDataPenjualanBarangOriginal(ArrayList<ArrayList<String>> dataPenjualanBarangOriginal) {
        this.dataPenjualanBarangOriginal = dataPenjualanBarangOriginal;
                

    }

    public void setDataPenjualanOriginal(ArrayList<ArrayList<String>> dataPenjualanOriginal) {
        this.dataPenjualanOriginal = dataPenjualanOriginal;
    }

    void setSincronyzedDay() {
        dayChooser.setMonth(monthChooser.getMonth());
        dayChooser.setYear(yearChooser.getYear());

        if (!useDay.isSelected()) {
            dayChooser.setEnabled(false);
        }
    }

    /**
     * Melakukan request data barang ke server
     */
    private void requestDataBarangToServer() {
        String backTaskID = TokoBangunanAdministrator.getIDTask();
        RequestProgress progress = new RequestProgress(this, backTaskID);
        progress.setVisible(true);

        List<NameValuePair> list = new ArrayList<>();

        SelledEnt ent = new SelledEnt(progress, list, "SELECT_ALL_PRODUCT", "Sedang merefresh konten...");
        SelledImpl impl = new SelledImpl(backTaskID, this);
        impl.enchanceSelectAllProduct(ent);
    }

    public void refreshTable(ArrayList<ArrayList<String>> temp) throws RemoteException {

        tableData = new ArrayList<>();
        tableData = temp;
        kett.setText("Ditemukan " + String.valueOf(tableData.size()) + " Buah Data Transaksi");

        totalPage = 0;
        totalPage = tableData.size() / totalDataPerPage;
        int sisa = tableData.size() % totalDataPerPage;

        totalPage = (sisa > 0) ? (totalPage + 1) : totalPage;

        System.out.println(totalPage);
        setToTable();
    }

    String tahun = "";
    String bulan = "";
    String hari = "";

    private void setToTable() {

        totalHalaman.setText(String.valueOf(totalPage));
        halaman.setText(String.valueOf(page + 1));
        String[] colNames = new String[]{"No", "ID Transaksi", "Tanggal Transaksi", "Diskon", "Harga Transaksi",
            "Kasir"};
        MyTableModel model = new MyTableModel(colNames, perPage(tableData), false, true, new int[]{0, 4}, ".");
        tablePenjualan.setModel(model);
        tablePenjualan.removeAll();
    }

    private ArrayList<ArrayList<String>> perPage(ArrayList<ArrayList<String>> data) {
        ArrayList<ArrayList<String>> ret = new ArrayList<>();

        int start = page * totalDataPerPage;
        int sizeData = data.size();

        for (int i = start; i < start + totalDataPerPage; i++) {
            if (i < sizeData) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(String.valueOf(i + 1));
                for (String arrayList : data.get(i)) {
                    temp.add(arrayList);
                }
                ret.add(temp);
            } else {
                return ret;
            }
        }

        return ret;
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
        tablePenjualan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        progressText1 = new javax.swing.JLabel();
        progressText7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        progressText3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        dayChooser = new com.toedter.calendar.JDayChooser();
        useDay = new javax.swing.JCheckBox();
        monthChooser = new com.toedter.calendar.JMonthChooser();
        useMonth = new javax.swing.JCheckBox();
        yearChooser = new com.toedter.calendar.JYearChooser();
        useYear = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        SearchButton = new javax.swing.JButton();
        status = new javax.swing.JComboBox<>();
        SearchButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        kett = new javax.swing.JLabel();
        totalHalaman = new javax.swing.JLabel();
        progressText5 = new javax.swing.JLabel();
        progressText6 = new javax.swing.JLabel();
        progressText8 = new javax.swing.JLabel();
        halaman = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        progressText4 = new javax.swing.JLabel();
        first = new javax.swing.JButton();
        previous = new javax.swing.JButton();
        next = new javax.swing.JButton();
        last = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jumpToPage = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        cetak = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        progressText9 = new javax.swing.JLabel();

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

        tablePenjualan.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        tablePenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tablePenjualan.setRowHeight(30);
        tablePenjualan.getTableHeader().setReorderingAllowed(false);
        tablePenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePenjualanMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablePenjualanMousePressed(evt);
            }
        });
        tablePenjualan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablePenjualanKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablePenjualan);
        if (tablePenjualan.getColumnModel().getColumnCount() > 0) {
            tablePenjualan.getColumnModel().getColumn(0).setResizable(false);
            tablePenjualan.getColumnModel().getColumn(1).setResizable(false);
            tablePenjualan.getColumnModel().getColumn(2).setResizable(false);
        }
        tablePenjualan.setDefaultRenderer(Object.class, new TableCellRenderer() {
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progressText7)
                .addGap(20, 20, 20))
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

        progressText3.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        progressText3.setText("Opsi Menampilkan Data Transaksi :");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        useDay.setBackground(new java.awt.Color(255, 255, 255));
        useDay.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        useDay.setForeground(new java.awt.Color(0, 153, 0));
        useDay.setSelected(true);
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
        useMonth.setForeground(new java.awt.Color(0, 153, 0));
        useMonth.setSelected(true);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(useDay)
                    .addComponent(dayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(useYear)
                    .addComponent(useMonth)
                    .addComponent(monthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(useDay)
                    .addComponent(useMonth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(monthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(useYear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Tanggal Transaksi");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        SearchButton.setUI(new bundaran.administrator.util.WarningUI());
        SearchButton.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        SearchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/search14l.PNG"))); // NOI18N
        SearchButton.setText("Transaksi   ");
        SearchButton.setFocusable(false);
        SearchButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        status.setRenderer(new CustomComboBox());
        status.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Salah Satu --", "LUNAS", "BELUM LUNAS" }));

        SearchButton1.setUI(new bundaran.administrator.util.WarningUI());
        SearchButton1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        SearchButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/search14l.PNG"))); // NOI18N
        SearchButton1.setText("Jatuh Tempo   ");
        SearchButton1.setFocusable(false);
        SearchButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        SearchButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SearchButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(SearchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("Status Transaksi");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(progressText3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(108, 108, 108))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressText3)
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        kett.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kett.setText("List Data Penjualan");

        totalHalaman.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        totalHalaman.setForeground(new java.awt.Color(0, 153, 0));
        totalHalaman.setText("1");

        progressText5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        progressText5.setText("Halaman");

        progressText6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        progressText6.setText("Menampilkan halaman ke-");

        progressText8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        progressText8.setText("dari");

        halaman.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        halaman.setForeground(new java.awt.Color(255, 0, 0));
        halaman.setText("1");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        progressText4.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        progressText4.setText("Kontrol Halaman :");

        first.setUI(new bundaran.administrator.util.WarningUI());
        first.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/first.PNG"))); // NOI18N
        first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstActionPerformed(evt);
            }
        });

        previous.setUI(new bundaran.administrator.util.WarningUI());
        previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/back.png"))); // NOI18N
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        next.setUI(new bundaran.administrator.util.WarningUI());
        next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/login16.PNG"))); // NOI18N
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        last.setUI(new bundaran.administrator.util.WarningUI());
        last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/last.png"))); // NOI18N
        last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel4.setText("Tampilkan Halaman Ke :");

        jumpToPage.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jumpToPage.setForeground(new java.awt.Color(255, 0, 0));
        jumpToPage.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jumpToPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumpToPageActionPerformed(evt);
            }
        });
        jumpToPage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jumpToPageKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumpToPageKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumpToPageKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressText4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(last, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jumpToPage, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(progressText4)
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(last, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jumpToPage, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        cetak.setUI(new bundaran.administrator.util.WarningUI());
        cetak.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/administrator/res/print16.PNG"))); // NOI18N
        cetak.setText("Cetak Semua Halaman   ");
        cetak.setFocusable(false);
        cetak.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });
        cetak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cetakKeyPressed(evt);
            }
        });

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

        jLabel13.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        jLabel13.setText("Toko Bundaran Elektronik, Kendari, Sulawesi Tenggara");

        progressText9.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        progressText9.setText("Kontrol Fungsi :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(progressText9, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cetak, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                        .addGap(27, 27, 27))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(progressText9)
                .addGap(11, 11, 11)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(kett)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(progressText6)
                                .addGap(4, 4, 4)
                                .addComponent(halaman)
                                .addGap(4, 4, 4)
                                .addComponent(progressText8)
                                .addGap(4, 4, 4)
                                .addComponent(totalHalaman)
                                .addGap(4, 4, 4)
                                .addComponent(progressText5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(32, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kett)
                    .addComponent(totalHalaman)
                    .addComponent(progressText5)
                    .addComponent(progressText6)
                    .addComponent(progressText8)
                    .addComponent(halaman))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        try {
            tablePenjualan.getSelectedRow();
        } catch (Exception e) {
            //numberNameBarcodeFilter.requestFocus();
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        try {
            int indexTable = tablePenjualan.getSelectedRow();

            new DataBarangSubMain(this, tableData.get(indexTable)).setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    private void tablePenjualanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePenjualanMousePressed


    }//GEN-LAST:event_tablePenjualanMousePressed

    int x, y;
    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved

        x = evt.getComponent().getX();
        y = evt.getComponent().getY();

    }//GEN-LAST:event_formComponentMoved

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        try {
            int row = tablePenjualan.getSelectedRow();
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

    private void tablePenjualanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablePenjualanKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            try {
                int row = tablePenjualan.getSelectedRow();
                int urut = page * totalDataPerPage;
                String id = tableData.get(urut + row).get(0);
                ArrayList<ArrayList<String>> d = getDatabarang(id);

                DataPenjualanDetail dpd = new DataPenjualanDetail(tableData.get(urut + row), d, tahun, bulan, hari);
                dpd.setVisible(true);
            } catch (Exception e) {
            }
        }


    }//GEN-LAST:event_tablePenjualanKeyPressed

    private void progressText7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_progressText7MouseClicked

        dispose();
    }//GEN-LAST:event_progressText7MouseClicked

    String addZero(int value) {
        if (value < 10) {
            return "0" + String.valueOf(value);
        }

        return String.valueOf(value);
    }

    String statusTransactionSelected = "";

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed

        tahun = (useYear.isSelected()) ? String.valueOf(yearChooser.getYear()) : "";
        bulan = (useMonth.isSelected()) ? String.valueOf(monthChooser.getMonth() + 1) : "";
        hari = (useDay.isSelected()) ? String.valueOf(dayChooser.getDay()) : "";

        penampilan = ((!hari.isEmpty()) ? "Tanggal " + hari : "")
                + ((!bulan.isEmpty()) ? " Bulan " + bulan : "")
                + ((!tahun.isEmpty()) ? " Tahun " + tahun : "");

        page = 0;
        statusTransactionSelected = "";
        String statusTransaksi = "";
        String paramWaktuTransaksi
                = (useYear.isSelected() ? String.valueOf(yearChooser.getYear()) : "")
                + (useMonth.isSelected() ? ("-" + addZero(monthChooser.getMonth() + 1)) : "")
                + (useDay.isSelected() ? ("-" + addZero(dayChooser.getDay())) : "");

        //System.out.println(statusTransaksi + paramWaktuTransaksi);
        try {
            refreshTable(searching(statusTransaksi, paramWaktuTransaksi));
//            kett.setText(kett.getText() + " "
//                    + ((status.getSelectedIndex() == 0) ? "" : String.valueOf(status.getSelectedItem()) + " ")
//                    + ((!penampilan.isEmpty()) ? " pada " + penampilan : ""));
        } catch (RemoteException ex) {
            Logger.getLogger(DataPenjualanMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_SearchButtonActionPerformed

    /**
     *
     * @param param 0 = tgl transaksi, 1 = status transaksi
     * @return
     */
    private ArrayList<ArrayList<String>> searching(String... param) {
        ArrayList<ArrayList<String>> temp = new ArrayList<>();

        /**
         * Mencari pada nama barcode
         */
        for (ArrayList<String> arrayList : dataPenjualanOriginal) {
            if (param[0].isEmpty()) {
                if (arrayList.get(1).toLowerCase().contains(param[1].toLowerCase())
                        /**
                         * Untuk taggl transaksi
                         */
                        && arrayList.get(4).toLowerCase().contains(param[0].toLowerCase()) /**
                         * Untuk status transaksi
                         */
                        ) {
                    temp.add(arrayList);
                }
            } else if (arrayList.get(1).toLowerCase().contains(param[1].toLowerCase())
                    /**
                     * Untuk taggl transaksi
                     */
                    && arrayList.get(4).toLowerCase().equals(param[0].toLowerCase()) /**
                     * Untuk status transaksi
                     */
                    ) {
                temp.add(arrayList);
            }

        }

        return temp;
    }


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //numberNameBarcodeFilter.setText("");
        statusTransactionSelected = "";
        tahun = "";
        bulan = "";
        hari = "";
        page = 0;
        requestDataBarangToServer();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed

        String[] colNames = new String[]{"Nomor Barcode", "Nama Barang", "Qty", "Harga", "Total Harga (Rp)"};
        Object[] paraPembeli = new Object[tableData.size()];
        Object[] paraDataPembeli = new Object[tableData.size()];

        int i = 0;
        for (ArrayList<String> object : tableData) {
            paraPembeli[i] = object;
            paraDataPembeli[i] = getDatabarang(object.get(0));
            i++;
        }
                        System.out.println();
                System.out.println();
                System.out.println(paraDataPembeli);
                System.out.println();
                System.out.println();
                System.out.println();

        DataPenjualanDetailReport detail = new DataPenjualanDetailReport(colNames, paraPembeli, paraDataPembeli, dataBarangOriginal, "DATA TRANSAKSI", false, "data_semua_penjualan_detail.pdf");
        if (detail.doCreateReport(hari, bulan, tahun, statusTransactionSelected)) {
            File file = new File("data_semua_penjualan_detail.pdf");
            if (file.exists()) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                    Logger.getLogger(DataBarangMainx.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }//GEN-LAST:event_cetakActionPerformed

    private void cetakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cetakKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        }
    }//GEN-LAST:event_cetakKeyPressed

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

    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed
        if (page != 0) {
            jumpToPage.setText("");
            page--;
            setToTable();
            halaman.setText(String.valueOf(page + 1));
        }

        //System.err.println(page);

    }//GEN-LAST:event_previousActionPerformed

    private void firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstActionPerformed
        jumpToPage.setText("");
        page = 0;
        setToTable();
        halaman.setText(String.valueOf(page + 1));
    }//GEN-LAST:event_firstActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed

        if (page + 1 != totalPage) {
            jumpToPage.setText("");
            page++;
            setToTable();
        }

        halaman.setText(String.valueOf(page + 1));
        //System.err.println(page);
    }//GEN-LAST:event_nextActionPerformed

    private void lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastActionPerformed
        jumpToPage.setText("");
        page = totalPage - 1;
        setToTable();
        halaman.setText(String.valueOf(page + 1));

    }//GEN-LAST:event_lastActionPerformed

    private void jumpToPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumpToPageActionPerformed

        if (jumpToPage.getText().isEmpty()) {
            return;
        }

        int jump = Integer.parseInt(jumpToPage.getText()) - 1;

        if (jump >= 0 && jump < totalPage) {
            page = jump;
            setToTable();
        } else {
            JOptionPane.showMessageDialog(null, "Halaman yang dipilih tidak ada...");
        }
    }//GEN-LAST:event_jumpToPageActionPerformed

    private void jumpToPageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumpToPageKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        } else {
            NumberValidation nv = new NumberValidation("", "");
            jumpToPage.setText(nv.fieldChecked(jumpToPage.getText()));
        }
    }//GEN-LAST:event_jumpToPageKeyPressed

    private void jumpToPageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumpToPageKeyReleased
        /*        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        } else {*/
        NumberValidation nv = new NumberValidation("", "");
        jumpToPage.setText(nv.fieldChecked(jumpToPage.getText()));


    }//GEN-LAST:event_jumpToPageKeyReleased

    private void jumpToPageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumpToPageKeyTyped
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        }
    }//GEN-LAST:event_jumpToPageKeyTyped

    private void tablePenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePenjualanMouseClicked
        if (evt.getClickCount() == 2) {
            try {
                int row = tablePenjualan.getSelectedRow();
                int urut = page * totalDataPerPage;
                String id = tableData.get(urut + row).get(0);
                ArrayList<ArrayList<String>> d = getDatabarang(id);

                DataPenjualanDetail dpd = new DataPenjualanDetail(tableData.get(urut + row), d, tahun, bulan, hari);
                dpd.setVisible(true);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_tablePenjualanMouseClicked

    private void SearchButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButton1ActionPerformed

        if (!useYear.isSelected() && !useMonth.isSelected() && !useDay.isSelected()) {
            JOptionPane.showMessageDialog(null, "Pohon pilih salah satu waktu pada kalender...");
            return;
        }

        tahun = (useYear.isSelected()) ? String.valueOf(yearChooser.getYear()) : "";
        bulan = (useMonth.isSelected()) ? String.valueOf(monthChooser.getMonth() + 1) : "";
        hari = (useDay.isSelected()) ? String.valueOf(dayChooser.getDay()) : "";
        JatuhTempo tempo = new JatuhTempo(dataPenjualanOriginal, dataPenjualanBarangOriginal,
            ((status.getSelectedIndex() == 0) ? "" : String.valueOf(status.getSelectedItem())), tahun, bulan, hari);
        tempo.setVisible(true);
    }//GEN-LAST:event_SearchButton1ActionPerformed

    private ArrayList<ArrayList<String>> getDatabarang(String id) {

        ArrayList<ArrayList<String>> r = new ArrayList<>();

        for (ArrayList<String> t : dataPenjualanBarangOriginal) {
            ArrayList<String> temp = new ArrayList<>();
                System.out.println(t);

            if (t.get(1).toLowerCase().equals(id.toLowerCase())) {
                temp.add(t.get(0));
                int harga = 0;
                for (ArrayList<String> t2 : dataBarangOriginal) {
                    System.out.println(t2);
                    if (t.get(0).equals(t2.get(0))) {
                        harga = Integer.parseInt(t2.get(3));
                        temp.add(t2.get(1));
                        temp.add(t.get(3));
                        temp.add(t2.get(2));
                        break;
                    }
                }
//                temp.add(t.get(4));

//                int total = Integer.parseInt(t.get(4)) * harga;
                temp.add(t.get(2));

                r.add(temp);
            }
                            System.out.println();

                            System.out.println();
                            System.out.println();
                System.out.println(temp);
                                            System.out.println();

        }

        return r;
    }

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
            java.util.logging.Logger.getLogger(DataPenjualanMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataPenjualanMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataPenjualanMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataPenjualanMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataPenjualanMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SearchButton;
    private javax.swing.JButton SearchButton1;
    private javax.swing.JButton cetak;
    private com.toedter.calendar.JDayChooser dayChooser;
    private javax.swing.JButton first;
    private javax.swing.JLabel halaman;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jumpToPage;
    private javax.swing.JLabel kett;
    private javax.swing.JButton last;
    private com.toedter.calendar.JMonthChooser monthChooser;
    private javax.swing.JButton next;
    private javax.swing.JButton previous;
    private javax.swing.JLabel progressText1;
    private javax.swing.JLabel progressText3;
    private javax.swing.JLabel progressText4;
    private javax.swing.JLabel progressText5;
    private javax.swing.JLabel progressText6;
    private javax.swing.JLabel progressText7;
    private javax.swing.JLabel progressText8;
    private javax.swing.JLabel progressText9;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTable tablePenjualan;
    private javax.swing.JLabel totalHalaman;
    private javax.swing.JCheckBox useDay;
    private javax.swing.JCheckBox useMonth;
    private javax.swing.JCheckBox useYear;
    private com.toedter.calendar.JYearChooser yearChooser;
    // End of variables declaration//GEN-END:variables
}
