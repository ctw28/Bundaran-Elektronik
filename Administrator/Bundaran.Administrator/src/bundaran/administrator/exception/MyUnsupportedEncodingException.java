/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bundaran.administrator.exception;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author AsyncTask.Void.087
 */
public class MyUnsupportedEncodingException extends UnsupportedEncodingException{

    public MyUnsupportedEncodingException() {
        //setMessage();
    }

    public MyUnsupportedEncodingException(String string) {
        super(string);
        //setMessage();
    }

    private void setMessage(){
        //System.err.println("Terjadi Kesalahan");
    }
    
}
