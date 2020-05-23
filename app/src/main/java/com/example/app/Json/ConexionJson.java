package com.example.app.Json;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConexionJson extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... strings) {

        HttpURLConnection connection;
        URL url;
        connection = null;
        String result;
        result ="";

        try{

            url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();

            int data = inputStream.read();

            while(data != -1){
                result += (char) data;
                data = inputStream.read();
            }

        }catch (Exception e){

            e.printStackTrace();

        }

        Log.i("RESULT", result);

        return result;
    }

    @Override
    protected void onPostExecute(String data) {
        super.onPostExecute(data);

        /*try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("weather");

            Log.i("WEATHER", jsonArray.toString());

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonitem = jsonArray.getJSONObject(i);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*/


    }
}
