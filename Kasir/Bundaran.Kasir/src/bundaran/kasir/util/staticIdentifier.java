/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.kasir.util;

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
        //httpParsingError
    }
    
    public interface URL_REQUEST {
        //public static final String MAIN_URL = "http://dutasite.esy.es/process/";
        public static final String MAIN_URL = "http://127.0.0.1/TokoBangunan/process/";
        public static final String LOGIN_URL = MAIN_URL + "login.php";
        public static final String SELECT_ALL_PRODUCT_URL = MAIN_URL + "select_product.php";
        
        
        public static final String SELECT_ALL_SELLED_URL = MAIN_URL + "select_data_selled_unlunas.php";
        public static final String SELECT_ALL_PRODUCT_SELLED_URL = MAIN_URL + "select_data_product_unlunas.php";
        
        public static final String SAVE_TRANSACTION_URL = MAIN_URL + "save_transaction.php";
        public static final String SAVE_TRANSACTION_PRODUCT_URL = MAIN_URL + "save_transaction_product.php";
        
    }
    
    
    public interface DATABASE_SEVER_STRUCTURE {
        public static final String[] TABEL_BARANG = {
            "nomor_barcode", "nama_barang", "modal_harga", "harga_barang", "tanggal_masuk",
            "stok_barang", "type", "item", "suplier", "tgl_transaksi", "keterangan","ket_sup","harga_suplier"
        };
        
        public static final String[] TABEL_PENJUALAN = {
            "id_transaksi", "tanggal_transaksi", "diskon", "cost_transaksi", "nama_kasir"
        };
        
        public static final String[] TABEL_PENJUALAN_BARANG = {
            "nomor_barcode", "id_transaksi", "jenis_pembelian", "jumlah_barang"
        };
    }
    
}
