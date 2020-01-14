package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.app.data.ConexionWebview;

import java.sql.SQLOutput;

public class WebViewActivity extends AppCompatActivity {
    ConexionWebview conexionWebview;
    WebView webView;

    public static WebViewActivity newInstance() {
        return new WebViewActivity();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
         webView = findViewById(R.id.MiWebview);
         webView.getSettings().setJavaScriptEnabled(true);
        System.out.println("DESPUES DEL GET");

         miHilo hilo = new miHilo();
         hilo.execute("http://www.google.com");

         conexionWebview.getWeb().observe(this, new Observer<String>() {
             @Override
             public void onChanged(String s) {
                 System.out.println("METODO ONCHANGED");
                 webView.loadData(s,"text/html","utf-8");
             }
         });
    }


    public class miHilo extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            System.out.println("ANTES DEL DOINBACKGRON");
            conexionWebview.downloadURL(strings[0]);
            System.out.println("DESPUES DEL DOINBACKGRON");
            return null;
        }

    }
}
