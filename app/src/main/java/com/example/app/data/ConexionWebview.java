package com.example.app.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConexionWebview {

    private MutableLiveData<String> mWeb;

    public ConexionWebview(){
        mWeb = new MutableLiveData<>();
    }

    public MutableLiveData<String> getWeb(){
        return mWeb;
    }

    public void downloadURL(String web){
        HttpURLConnection con;
        URL url;
        String resultado;
        resultado = "";
        try{
            url = new URL(web);
            con = (HttpURLConnection) url.openConnection();
            InputStream inputStream = con.getInputStream();
            int data = inputStream.read();
            while( data != -1){
                resultado += (char) data;
                data = inputStream.read();

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("RESULT", resultado);
        System.out.println("RESULTADOOOOOOO DE DOWNLOAD");
        mWeb.postValue(resultado);
        System.out.println("RESULTADOOOOOOO DE DOWNLOAD1111111111111111111111111");
    }
}
