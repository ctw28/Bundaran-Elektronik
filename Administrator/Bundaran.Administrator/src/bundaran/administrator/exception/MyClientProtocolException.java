/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.exception;

import org.apache.http.client.ClientProtocolException;

/**
 *
 * @author AsyncTask.Void.087
 */
public class MyClientProtocolException extends ClientProtocolException{

    public MyClientProtocolException() {
        setMessage();
    }

    public MyClientProtocolException(String string) {
        super(string);
        setMessage();
    }

    public MyClientProtocolException(String string, Throwable thrwbl) {
        super(string, thrwbl);
        setMessage();
    }

    public MyClientProtocolException(Throwable thrwbl) {
        super(thrwbl);
        setMessage();
    }

    private void setMessage(){
        System.err.println("Terjadi Kesalahan");
    }
    
}
