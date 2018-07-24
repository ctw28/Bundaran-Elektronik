/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.backgroundtask;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import bundaran.administrator.util.staticIdentifier;

/**
 *
 * @author dan
 */
public class StartDatabaseBackgroundController extends SwingWorker<Boolean, String> {

    /**
     * <p>
     * Instance dari class yang memanggil clas ini</p>
     */
    Object instance;

    /**
     * <p>
     * Label progreass untukmenampilkan pesan progress </p>
     */
    JLabel label;

    /**
     *
     * @param instance
     * <p>
     * Instance dari class yang memanggil clas ini</p>
     * @param progressView
     * <p>
     * Instance dari Form progresBar yang manampilkan loading dari proses
     * bckground</p>
     */
    int process;
    public static boolean PROCESS_CLOSED_BY_USER;
    
    public StartDatabaseBackgroundController(Object instance, Object progressView, int process) {
        StartDatabaseBackgroundController.PROCESS_CLOSED_BY_USER = false;
        this.instance = instance;
        this.label = getLabelProgress(progressView);
        this.process = process;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        int k = 0;
        int y = 9 + k;
        
        switch(process){
            case 0 :
                starting();
                break;
            case 1 : 
                stoping();
                break;
        }
        
        return true;
    }

    @Override
    protected void done() {
        
        if (PROCESS_CLOSED_BY_USER) 
            return;

        String resultFunction = staticIdentifier.BackgroundProcess.RESUL_FUNCTION_NAME;
        Class[] classTypes = {String.class};
        Object[] data = {"Proses ini telah berhasil"};

        try {
            Method goResult = instance.getClass().getMethod(resultFunction, classTypes);
            goResult.invoke(instance, data);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(StartDatabaseBackgroundController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void process(List<String> chunks) {
        label.setText(chunks.get(0));
    }
    
    
    /**
     * Fungsi yang digunakan untuk memulai aplikasi dan mengkonfigurasi database
     * 
     * @throws IOException
     * @throws InterruptedException 
     */
    private void starting() throws IOException, InterruptedException{
        String[] message = {"Mohon menunggu...", "Mencari ketersediaan Database...", "Sedang menyalakan Service Apache...",
            "Sedang menyalakan Service MySql...", "Mengkonfigurasi Tabel...", "Konfigurasi Sukses..."
        };

        int[] sleep = {500, 1000, 2000, 2000, 1000, 1000};

        String[][] command = {
            null, null,
            {"cmd.exe", "/c",
                "cd \"C:\\xampp\" && apache\\bin\\httpd.exe"
            },
            {"cmd.exe", "/c",
                "cd \"C:\\xampp\" && mysql\\bin\\mysqld --defaults-file=mysql\\bin\\my.ini --standalone --console"
            }, null, null
        };
        
        for (int i = 0; i < sleep.length; i++) 
            startCommand(command[i], sleep[i], message[i]);
    }

    
    /**
     * Start command untuk mengkkonfigurasi database
     * 
     * @param command
     * @param sleep
     * @param message
     * @throws IOException
     * @throws InterruptedException 
     */
    private void startCommand(String[] command, int sleep, String message) throws IOException, InterruptedException {
        publish(message);

        if (command != null) {
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            builder.start();
        }

        Thread.sleep(sleep);
    }
    
    
    private void stoping() throws IOException, InterruptedException{
        String[] message = {"Sedang menutup aplikasi...", 
            "Sedang menutup Service Apache...",
            "Sedang menutup Service MySql...", "Proses Selesai..."
        };

        int[] sleep = {500, 1000, 1000, 500};

        String[][] command = {
            null,
            null,
            null, null
        };
        
        for (int i = 0; i < sleep.length; i++) 
            startCommand(command[i], sleep[i], message[i]);
    }

    /**
     * Fungsi untuk mendapatkan label Progress
     *
     * @param progressView
     * @return
     */
    private JLabel getLabelProgress(Object progressView) {
        try {
            Field field = progressView.getClass().getDeclaredField(staticIdentifier.BackgroundProcess.LABEL_PROGRESS_NAME);
            field.setAccessible(true);
            return (JLabel) field.get(progressView);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(StartDatabaseBackgroundController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
