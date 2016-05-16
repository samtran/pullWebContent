package com.example.samanthatran.pullwebcontent;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by samanthatran on 5/11/16.
 */
public class NetworkConnection extends AsyncTask<String,Void,String> {

    //Constructor
    @Override
    protected String doInBackground(String...urls){
        try{
            return downloadUrl(urls[0]);
        }catch(IOException e) {
            return "Unable to receive webpage content";
        }
    }

    public AsyncResponse delegate = null;
    @Override
    protected void onPostExecute(String result){
        delegate.processFinish(result);
        Log.d("DEBUGGING",result);
        //Good debug point @Log.d to check value of result variable
    }


    private String downloadUrl(String myurl) throws IOException{
        URL url = null;
        try {
            url = new URL(myurl);
        }catch(MalformedURLException mal){}
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        }catch(IOException io){}

        String content="tempString";
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            content = readStream(in);
        }catch(Exception e){
            content = "exception in InputStream";
        }
        finally{
            urlConnection.disconnect();
        }
        return content;
    }

    private String readStream(InputStream in){
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = in.read();
            while(i != -1) {
                bo.write(i);
                i = in.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "exception in readStream";
        }
    }

}
