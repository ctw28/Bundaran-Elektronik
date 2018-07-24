/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.util;

/**
 *
 * @author dan
 */
public interface staticIdentifier {
    
    public static final String refreshTableName = "refreshTable";
    public static final String buttonSaveName = "Simpan Data ";
    public static final String buttonChangeName = "Ubah Data ";
    
    public interface BackgroundProcess {
        public static final String RESUL_FUNCTION_NAME = "processDone";
        public static final String ERROR_FUNCTION_NAME = "httpParsingError";
        public static final String LABEL_PROGRESS_NAME = "progressText";
    }
    
    
    public interface URL_REQUEST {
        
        //public static final String MAIN_URL = "http://dutasite.esy.es/process/";
        public static final String MAIN_URL = "http://localhost/TokoBangunan/process/";
        
        //user
        //
        public static final String LOGIN_URL = MAIN_URL + "login.php";
        public static final String SELECT_ALL_USER_URL = MAIN_URL + "select_user_all.php";
        public static final String SAVE_USER_URL = MAIN_URL + "save_user.php";
        public static final String UPDATE_USER_URL = MAIN_URL + "update_user.php";
        public static final String DELETE_USER_URL = MAIN_URL + "delete_user.php";

        public static final String SELECT_ALL_SUPLIER_URL = MAIN_URL + "select_suplier_all.php";
        public static final String SAVE_SUPLIER_URL = MAIN_URL + "save_suplier.php";
        public static final String SELECT_SOME_SUPLIER_URL = MAIN_URL + "select_suplier_some.php";
        
        //produk
        public static final String SELECT_ALL_PRODUCT_URL = MAIN_URL + "select_product.php";
        public static final String SAVE_PRODUCT_URL = MAIN_URL + "save_product.php";
        public static final String UPDATE_PRODUCT_URL = MAIN_URL + "update_product.php";
        public static final String DELETE_PRODUCT_URL = MAIN_URL + "delete_product.php";
        
        //produk keluar
        public static final String SELECT_ALL_PRODUCT_OUT_URL = MAIN_URL + "select_product_out.php";
        public static final String SAVE_PRODUCT_OUT_URL = MAIN_URL + "save_product_out.php";
        public static final String DELETE_PRODUCT_OUT_URL = MAIN_URL + "delete_product_out.php";
        //delete_product_out
        
        //transaksi
        public static final String SELECT_ALL_SELLED_URL = MAIN_URL + "select_data_selled_all.php";
        public static final String SELECT_ALL_PRODUCT_SELLED_URL = MAIN_URL + "select_data_product_all.php";
        
        
        public static final String SAVE_TRANSACTION_URL = MAIN_URL + "save_transaction.php";
        public static final String SAVE_TRANSACTION_PRODUCT_URL = MAIN_URL + "save_transaction_product.php";
        public static final String CREATE_DB_STRUCTURE_URL = MAIN_URL + "create_database_structure.php";
        
        
    }
    
    
    public interface DATABASE_SEVER_STRUCTURE {
        
        public static final String[] TABEL_ADMIN = {
            "id_administrator", "username", "password", "type"
        };
        
        public static final String[] TABEL_BARANG_KELUAR = {
            "id_keluar", "nomor_barcode", "tanggal_keluar", "jumlah", "tujuan"
        };
        
//        public static final String[] TABEL_BARANG = {
//            "nomor_barcode", "nama_barang", "harga_barang", "stok_barang", "keterangan"
//        };

        public static final String[] TABEL_BARANG = {
            "nomor_barcode", "nama_barang","harga_barang", 
            "stok_barang",  "type", "keterangan"
        };        
        
        public static final String[] TABEL_PENJUALAN = {
            "id_transaksi", "tanggal_transaksi", "diskon", "cost_transaksi", "nama_kasir"
        };
        
        public static final String[] TABEL_PENJUALAN_BARANG = {
            "nomor_barcode", "id_transaksi", "jenis_pembelian", "jumlah_barang"
        };

        public static final String[] TABEL_SUPLIER = {
            "no_barcode", "suplier", "tgl_transaksi", "harga_awal", "harga_modal",  "ket_suplier"
        };
    }
    
}
