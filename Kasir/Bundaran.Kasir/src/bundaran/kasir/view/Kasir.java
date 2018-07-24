package bundaran.kasir.view;

import annas.jdbc.service.configuration.DatabaseTableList;
import annas.jdbc.service.configuration.MyTableModel;
import annas.jdbc.service.impl.SqlImplementImpl;
import annas.jdbc.service.model.QueryAttribut;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import sun.awt.AppContext;
import bundaran.kasir.TokoBangunanKasir;
import bundaran.kasir.backgroundtask.LocalRequestVoidCallbackTask;
import bundaran.kasir.util.ComboKeyController;
import bundaran.kasir.util.CustomComboBox;
import bundaran.kasir.util.NumberValidation;
import bundaran.kasir.util.RefreshAllData;
import bundaran.kasir.view.progress.RequestProgress;

/**
 *
 * @author AsyncTask.Void.087
 */
public final class Kasir extends javax.swing.JFrame implements LocalRequestVoidCallbackTask {

    public String[] array = {};

    /**
     * Data-data barang bangunan
     */
    public ArrayList<ArrayList<String>> dataBarang;

    /**
     * Merupakan data dari transaksi yag belum lunas
     */
    public ArrayList<ArrayList<String>> dataPenjualan;

    /**
     * Merupakan data barang dari tansaksi yang belum lunas
     */
    public ArrayList<ArrayList<String>> dataPenjualanBarang;

    /**
     * ID transaksi yang digenerateRandom
     */
    private String generatedTransactioID;

    /**
     * Data barang yang dibeli
     */
    public ArrayList<ArrayList<String>> dataTransaksi;

    /**
     * Creates new form Kasir
     *
     * @param dataBarang
     * @param dataPenjualan
     * @param dataPenjualanBarang
     * @param progress
     */
    public Kasir(ArrayList<ArrayList<String>> dataBarang,
            ArrayList<ArrayList<String>> dataPenjualan,
            ArrayList<ArrayList<String>> dataPenjualanBarang,
            RequestProgress progress) {

        if (dataBarang != null || dataPenjualan != null || dataPenjualanBarang != null) {
            this.dataBarang = dataBarang;
            this.dataPenjualan = dataPenjualan;
            this.dataPenjualanBarang = dataPenjualanBarang;
            array = makeStringSuggestionProduct();
        }

        if (progress != null) {
            progress.dispose();
        }
        initComponents();
        setLocationRelativeTo(null);

        /*
         * Fungsi yang dipanggil jika akan mulai melakukan transaksi yang baru
         */
        newTrancsaction();

//        if (!TokoBangunanKasir.OFFLINE_MODE) {
//            offlineModeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/kasir/res/offline.PNG"))); // NOI18N
//            offlineModeButton.setText("GUNAKAN OFFLINE");
//            labelOfflineOnline.setText("ONLINE");
//        } else {
//            offlineModeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/kasir/res/online.PNG"))); // NOI18N
//            offlineModeButton.setText("GUNAKAN ONLINE");
//            labelOfflineOnline.setText("OFFLINE");
//        }
    }

    /*
     * Fungsi yang dipanggil jika akan mulai melakukan transaksi yang baru
     */
    public void newTrancsaction() {
        dataTransaksi = new ArrayList<>();

        generatedTransactioID = generateTransactionID();//System.out.println(generateTransactionID());
        labelTransactionId.setText(generatedTransactioID);
        setToTable();
    }

    public void setToTable() {
        String[] colNames = new String[]{"Nomor Barcode", "Nama Barang", "jumlah", "Harga Satuan", "Diskon", "Total Harga (Rp)"};
        MyTableModel model = new MyTableModel(colNames, dataTransaksi, false, false, new int[]{0}, ",");
        tabelTransaksi.setModel(model);
        tabelTransaksi.setRowSelectionAllowed(false);
        setCostTotal();
    }

    void setCostTotal() {

        if (!dataTransaksi.isEmpty()) {
            int cost = 0;
            for (ArrayList<String> value : dataTransaksi) {
                cost += Integer.parseInt(value.get(5));
            }

            NumberValidation nv = new NumberValidation(",", "Rp ");
            showCostTotal.setText(nv.fieldChecked(String.valueOf(cost)));

        } else {
            showCostTotal.setText("Rp 0");
        }
    }

    /**
     * Fungsi yang digunakan untuk menerima inputan detail dari barang yang beli
     *
     * @param add
     * @param data
     */
    public void catchUpDetail(boolean add, String... data) {

        JTextField field = (JTextField) jComboBox1.getEditor().getEditorComponent();
        field.setText("");

        ArrayList<String> trans = new ArrayList<>();
        trans.addAll(Arrays.asList(data));

        if (add) {
            int i = -1;
            if (!dataTransaksi.isEmpty() && (i = isProductExists(trans)) > -1) {
                dataTransaksi.set(i, reBuildProductDetail(i, trans));
            } else {
                dataTransaksi.add(trans);
            }
        } else {
            dataTransaksi.set(tabelTransaksi.getSelectedRow(), trans);
        }

        setToTable();

        jComboBox1.requestFocus();
    }

    synchronized ArrayList<String> reBuildProductDetail(int i, ArrayList<String> trans) {

        int jumlahBarang = Integer.parseInt(dataTransaksi.get(i).get(2));
        int hargaPerItem = Integer.parseInt(dataTransaksi.get(i).get(3));

        int totalBarang = jumlahBarang + Integer.parseInt(trans.get(2));
        trans.set(2, String.valueOf(totalBarang));

        int totalHarga = totalBarang * hargaPerItem;
        trans.set(5, String.valueOf(totalHarga));

        return trans;
    }

    /**
     * Jika ada data belanjaan yang sama
     *
     * @param trans
     * @return
     */
    int isProductExists(ArrayList<String> trans) {
        int i = 0;

        for (ArrayList<String> tran : dataTransaksi) {
            if (tran.get(0).equals(trans.get(0))) {
                return i;
            }
            i++;
        }

        return -1;
    }

    /**
     * Membuat sugget untuk data barang yang dicari
     *
     * @return
     */
    public String[] makeStringSuggestionProduct() {
        String[] data = new String[dataBarang.size()];

        int i = 0;
        for (ArrayList<String> list : dataBarang) {
            String temp = list.get(0) + "                     Dengan nama barang : " + list.get(1);
            data[i] = temp;
            i++;
        }

        return data;
    }

    void selectProductAndSelledData() {
        /**
         * Tabel data barang
         */
        String tableProduct = DatabaseTableList.getTableNames()[0];

        /**
         * Tabel data penjualan
         */
        String tableSelled = DatabaseTableList.getTableNames()[2];

        /**
         * Tabel data penjualan barang
         */
        String tableSelledProduct = DatabaseTableList.getTableNames()[4];

        try {
            QueryAttribut qAttribut = new QueryAttribut(false, null, tableProduct, "");
            SqlImplementImpl query = new SqlImplementImpl();
            dataBarang = (dataBarang == null) ? query.goSelect(qAttribut) : dataBarang;
        } catch (RemoteException ex) {
            Logger.getLogger(Kasir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mengenarate d transaksi yang digunakan
     *
     * @return
     */
    private String generateTransactionID() {
        Date date = new Date();
        String id = String.valueOf(new SimpleDateFormat("ddMMyyHHmmss").format(date));
        return "BE" + id;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        progressText1 = new javax.swing.JLabel();
        progressText2 = new javax.swing.JLabel();
        progressText3 = new javax.swing.JLabel();
        progressText4 = new javax.swing.JLabel();
        labelOfflineOnline = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelTransactionId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTransaksi = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        showCostTotal = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelTransactionId1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        progressText1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        progressText1.setForeground(new java.awt.Color(255, 255, 255));
        progressText1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/kasir/res/logo14.PNG"))); // NOI18N
        progressText1.setText("   APLIKASI KASIR TOKO BUNDARAN ELEKTRONIK");

        progressText2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        progressText2.setForeground(new java.awt.Color(255, 255, 255));
        progressText2.setText("Jln. , No , Kendari Sulawesi Tenggara");

        progressText3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        progressText3.setForeground(new java.awt.Color(255, 255, 255));
        progressText3.setText("No. Telepon (0401)  / Hp : 085241800852");

        progressText4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        progressText4.setForeground(new java.awt.Color(255, 255, 255));
        progressText4.setText("Mode");

        labelOfflineOnline.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelOfflineOnline.setForeground(new java.awt.Color(255, 255, 255));
        labelOfflineOnline.setText("OFFLINE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressText2)
                    .addComponent(progressText3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelOfflineOnline)
                .addGap(5, 5, 5)
                .addComponent(progressText4)
                .addGap(50, 50, 50))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(progressText1)
                .addGap(46, 46, 46))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressText1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(progressText2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progressText3))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelOfflineOnline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(progressText4)))
                .addGap(14, 14, 14))
        );

        jPanel3.setBackground(new java.awt.Color(246, 246, 246));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel1.setText("BARCODE/ NAMA BARANG");

        labelTransactionId.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        labelTransactionId.setForeground(new java.awt.Color(254, 137, 0));
        labelTransactionId.setText("jLabel3");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel2.setText("NOMOR TRANSAKSI");

        jComboBox1 = makeComboBox(array);
        jComboBox1.setEditable(true);
        //(()jComboBox1.getEditor().getEditorComponent())
        //JTextField jtf = (JTextField) jComboBox1.getEditor().getEditorComponent();
        //jtf.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.black));
        //jtf.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jComboBox1.setSelectedItem("");
        jComboBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jComboBox1.setRenderer(new CustomComboBox());
        jComboBox1.setSelectedIndex(-1);
        JTextField field = (JTextField) jComboBox1.getEditor().getEditorComponent();
        field.setText("");
        field.addKeyListener(new ComboKeyController(this, jComboBox1));
        jComboBox1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jComboBox1.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

        jButton1.setUI(new bundaran.kasir.util.WarningUI());
        jButton1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/kasir/res/refresh.PNG"))); // NOI18N
        jButton1.setText("Segarkan   ");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabelTransaksi.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tabelTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelTransaksi.setRowHeight(32);
        tabelTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelTransaksiMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelTransaksiMousePressed(evt);
            }
        });
        tabelTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelTransaksiKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabelTransaksi);
        tabelTransaksi.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    c.setBackground(new Color(0xff8a00));
                } else {
                    c.setBackground(new Color(0xffffff));
                }

                return c;
            }

        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel3.setText(":");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel4.setText(":");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel5.setText("DAFTAR BELANJA");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel6.setText(":");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        showCostTotal.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        showCostTotal.setForeground(new java.awt.Color(204, 0, 0));
        showCostTotal.setText("Rp 0");
        showCostTotal.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        showCostTotal.setFocusable(false);
        showCostTotal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel8.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("Total Harga Belanja  :  ");

        labelTransactionId1.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        labelTransactionId1.setForeground(new java.awt.Color(0, 153, 51));
        labelTransactionId1.setText("** Silahkan Tekan Tombol 'INSERT' untuk melakukan pembayaran");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelTransactionId1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(showCostTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(showCostTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTransactionId1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton2.setUI(new bundaran.kasir.util.WarningUI());
        jButton2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/kasir/res/close.png"))); // NOI18N
        jButton2.setText("KELUAR");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setUI(new bundaran.kasir.util.WarningUI());
        jButton3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/kasir/res/search16.PNG"))); // NOI18N
        jButton3.setText("CARI TRANSAKSI");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton8.setUI(new bundaran.kasir.util.WarningUI());
        jButton8.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/kasir/res/reset.PNG"))); // NOI18N
        jButton8.setText("RESET BELANJA");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        jLabel13.setText("Toko Bundaran Elektronik, Kendari");

        jButton9.setUI(new bundaran.kasir.util.WarningUI());
        jButton9.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tokobangunan/kasir/res/about.PNG"))); // NOI18N
        jButton9.setText("TENTANG");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel6))
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(456, 456, 456)
                                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(labelTransactionId, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(921, 921, 921)))
                                    .addGap(0, 2, Short.MAX_VALUE))))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTransactionId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(126, 125, 125));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String backTaskID = TokoBangunanKasir.getIDTask();
        if (!TokoBangunanKasir.OFFLINE_MODE) {

            // remove swing worker tables instance
            AppContext appContext = AppContext.getAppContext();
            if (appContext != null) {
                appContext.remove(SwingWorker.class);
            }

            RequestProgress rp = new RequestProgress(this, backTaskID);
            rp.setVisible(true);
            jComboBox1.requestFocus();
            RefreshAllData allData = new RefreshAllData(this, backTaskID, rp);
            allData.execute();
        } else {
            JOptionPane.showMessageDialog(null, "Mohon Gunakan Online Mode...");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabelTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTransaksiMouseClicked

        try {
            int row = tabelTransaksi.getSelectedRow();
            if (evt.getClickCount() == 2) {
                //tabelTransaksi.setRowSelectionAllowed(false);
                update(row);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tabelTransaksiMouseClicked

    private void tabelTransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelTransaksiKeyPressed

        try {
            int row = tabelTransaksi.getSelectedRow();

            switch (evt.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                    update(row);
                    break;
                case KeyEvent.VK_INSERT:
                    processTransaction();
                    break;
                case KeyEvent.VK_DELETE:
                    int y = JOptionPane.showConfirmDialog(null, "Yakin akan dihapus?");
                    if (y == 0) {
                        dataTransaksi.remove(row);
                    }

                    setToTable();
                    jComboBox1.requestFocus();
                    break;
                default:
            }

        } catch (Exception e) {
        }

    }//GEN-LAST:event_tabelTransaksiKeyPressed

    private void tabelTransaksiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTransaksiMousePressed
        tabelTransaksi.setRowSelectionAllowed(true);
    }//GEN-LAST:event_tabelTransaksiMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        System.exit(0);
        //new Login(dataBarang)
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new Cari(this).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        newTrancsaction();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        new Tentang().setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    void update(int row) {
        String jenisPembelian = dataTransaksi.get(row).get(2);
//        System.out.println(this.dataTransaksi);

        //jenisPembelian = jenisPembelian.substring(4, jenisPembelian.length());
        ProductDetail productDetail = new ProductDetail(this, false, dataBarang,
                dataTransaksi.get(row).get(0), dataTransaksi.get(row).get(2), dataTransaksi.get(row).get(5), dataTransaksi.get(row).get(4));
        productDetail.setVisible(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {

            UIManager.setLookAndFeel(new WindowsLookAndFeel());
            new Kasir(null, null, null, null).setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TokoBangunanKasir.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static JComboBox<String> makeComboBox(String... model) {
        return new JComboBox<>(model);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    public javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelOfflineOnline;
    private javax.swing.JLabel labelTransactionId;
    private javax.swing.JLabel labelTransactionId1;
    private javax.swing.JLabel progressText1;
    private javax.swing.JLabel progressText2;
    private javax.swing.JLabel progressText3;
    private javax.swing.JLabel progressText4;
    private javax.swing.JLabel showCostTotal;
    public javax.swing.JTable tabelTransaksi;
    // End of variables declaration//GEN-END:variables

    public static boolean isDetailShow = false;

    @Override
    public void processDone(String resultMessage) throws SQLException {
        try {
            if (resultMessage.length() < 12 /**/) {
                JOptionPane.showMessageDialog(null, "Silahkan periksa Barcode/ Nama Barang");
            } else {
                isDetailShow = true;
                ProductDetail detail = new ProductDetail(this, true, dataBarang, resultMessage.substring(0, 12 /*Jumlah digit barcode yang dipakai*/));
                KeyEvent event = new KeyEvent(this, WIDTH, WIDTH, WIDTH, WIDTH);
                event.setKeyCode(KeyEvent.VK_ENTER);
                detail.sendDetail(event);

                //jComboBox1.hidePopup();
            }
        } catch (Exception e) {
        }
    }

    /**
     * Memproses data transaksi untuk segera dibayar
     */
    public void processTransaction() {

        if (dataTransaksi.isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Daftar belanja masih kosong");
            return;
        }

        new PayDetails(this, dataTransaksi, showCostTotal.getText(), labelTransactionId.getText()).setVisible(true);

    }

}
