package com.codefactory.facedetect;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

/**
 * Created by kudlaty on 12/09/2016.
 */
public class FppConnect {
    private final static String TAG = FppConnect.class.getSimpleName();

    private final static String MAIN= "https://apius.faceplusplus.com/v2/";
    private final static int TIMEOUT=20000;
    private final static int BUFFERSIZE = 1048576;

//    private String apiKey;
//    private String apiSecretKey;

    public FppConnect(){
//        this.apiKey = activity.getResources().getString(R.string.fpp_key);
//        this.apiSecretKey = activity.getResources().getString(R.string.fpp_secret_key);

    }

    public String fppRequest(String mode, PostParameters params){
        URL url;
        HttpURLConnection urlConn;
        String resultString;
        String link = MAIN+mode;
        String apiKey;
        String apiSecretKey;
        try {
            apiKey = FppSingleton.getApiKey();
            apiSecretKey = FppSingleton.getSecretApiKey();
            url = new URL(link);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setConnectTimeout(TIMEOUT);
            urlConn.setReadTimeout(TIMEOUT);
            urlConn.setDoOutput(true);

            urlConn.setRequestProperty("connection", "keep-alive");
            urlConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + params.boundaryString());

            MultipartEntity reqEntity = params.getMultiPart();
            reqEntity.addPart("api_key", new StringBody(apiKey, Charset.forName("UTF-8")));
            reqEntity.addPart("api_secret", new StringBody(apiSecretKey, Charset.forName("UTF-8")));

            reqEntity.writeTo(urlConn.getOutputStream());

            if (urlConn.getResponseCode() == 200)
                resultString = readString(urlConn.getInputStream());
            else
                resultString = readString(urlConn.getErrorStream());
        }catch(UnknownHostException e){
            e.printStackTrace();
            resultString = FppError.serializeError(96,e.getMessage());
            System.out.println("exception: "+e.toString());
        }catch (Exception e){
            e.printStackTrace();
            resultString = FppError.serializeError(99,e.getMessage());
            System.out.println("exception: "+e.toString());
        }
        System.out.println("json request result: \n"+resultString);
        return resultString;
    }

    private static String readString(InputStream is) {
        StringBuffer rst = new StringBuffer();
        byte[] buffer = new byte[BUFFERSIZE];
        int len;
        try {
            while ((len = is.read(buffer)) > 0)
                for (int i = 0; i < len; ++i)
                    rst.append((char)buffer[i]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rst.toString();
    }

}
