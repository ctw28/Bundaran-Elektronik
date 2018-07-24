/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.exception;

import java.io.IOException;

/**
 *
 * @author AsyncTask.Void.087
 */
public class MyIOException extends IOException {

    public MyIOException() {
        setMessage();
    }

    public MyIOException(String string) {
        super(string);
        setMessage();
    }

    public MyIOException(String string, Throwable thrwbl) {
        super(string, thrwbl);
        setMessage();
    }

    public MyIOException(Throwable thrwbl) {
        super(thrwbl);
        setMessage();
    }

    private void setMessage(){
        System.err.println("Terjadi Kesalahan");
    }
    
}
