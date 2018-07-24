package bundaran.kasir.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import bundaran.kasir.backgroundtask.ServerRequestController;

public class JsonParsing {

    public static InputStream inputStream;
    public static JSONObject jsonObjectData;
    public static String jsonStringParsing;
    private boolean isFinish = false;

    public JsonParsing() {
        super();
        // TODO Auto-generated constructor stub
    }

    // Jika proses jsonParsing telah selesai
    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    // Fungsi yang digunakanuntuk melakukan httprequest
    public JSONObject makeHttpRequest(
            String backTaskID,
            Object instance,
            String url,
            String method,
            List<NameValuePair> pairs
    )  {
        jsonObjectData = null;
        jsonStringParsing = "";
        inputStream = null;

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = null;

        // Jika Method yang dignakan adalah POST
        if (method.equals("POST")) {
            try {
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(pairs));
                // proses permintaan respon dari server
                httpResponse = httpClient.execute(httpPost);
            } // Jika Method yang dignakan adalah GET
            catch (UnsupportedEncodingException ex) {
                ServerRequestController.PROCESS_CLOSED.replace(backTaskID, true);
                gettingError(instance, "Periksa koneksi internet anda\natau koneksi anda tidak stabil");
            } catch (IOException ex) {
                ServerRequestController.PROCESS_CLOSED.replace(backTaskID, true);
                gettingError(instance, "Periksa koneksi internet anda\natau koneksi anda tidak stabil");
            }
        }
        else if (method.equals("GET")) {
            try {
                String paramString = (pairs != null) ? URLEncodedUtils.format(pairs, "utf-8") : ""; // utf-8 (standar)
                url += (paramString.length() != 0) ? ("?" + paramString) : ""; // url yang ditambahkan dengan beberapa parameter yg dibutuhkan
                HttpGet httpGet = new HttpGet(url);
                // proses permintaan respon dari server
                httpResponse = httpClient.execute(httpGet);
            } catch (IOException ex) {
                ServerRequestController.PROCESS_CLOSED.replace(backTaskID, true);
                gettingError(instance, "Periksa koneksi internet anda\natau koneksi anda tidak stabil");
            }
        }

        if (httpResponse != null) {
            try {
                HttpEntity httpEntity = httpResponse.getEntity();
                // Inputstream dari server
                inputStream = httpEntity.getContent();
                
                try {
                    // Pembacaan data stream yang didapat dari respon yg diberikan server
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(inputStream, "iso-8859-1"), 8
                    );
                    // Proses pengubahan stream menjadi rangkaian String
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }
                    inputStream.close();
                    //==================================================
                    jsonStringParsing = stringBuilder.toString(); // mengubahnya menjadi string (seperti TXT)
                } catch (Exception e) {                    
                }
                
                try {
                    // Pengubahan String (txt menjadi JSONObject
                    jsonObjectData = new JSONObject(jsonStringParsing);
                } catch (JSONException e) {
                    //gettingError(instance, "Terjadi kesalahan");
                }
                
                // Memberi identifier bahwa proses JSONParsing selesai tanpa ada yang error
                setFinish(true);
                
                // retrn
                return jsonObjectData;
            } catch (IOException ex) {
                
            } catch (UnsupportedOperationException ex) {                
            }
        } else {
            return null;
        }
        
        return null;
    }
    
    
    
    private void gettingError(Object classUsingThisTask, String message){
        try {
            Class[] classTypes = {String.class, String.class};
            Object[] data = {"Interuption", message};
            
            Method toResult = classUsingThisTask.getClass().getMethod("httpParsingError", classTypes);
            toResult.setAccessible(true);
            toResult.invoke(classUsingThisTask, data);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
        }
    }

}
