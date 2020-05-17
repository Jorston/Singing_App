package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.app.R;
import com.example.app.DataConexiones.ConexionWebview;

public class WebViewActivity extends AppCompatActivity {
    ConexionWebview conexionWebview;
    WebView webView;

    public static WebViewActivity newInstance() {
        return new WebViewActivity();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        if(getSupportActionBar() != null)

            getSupportActionBar().hide();

         webView = findViewById(R.id.MiWebview);
         webView.getSettings().setJavaScriptEnabled(true);
         conexionWebview = new ConexionWebview();
         final String URL = "https://www.google.com/";
        System.out.println("DESPUES DEL GET");

         miHilo hilo = new miHilo();
         hilo.execute(URL);

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
            return null;
        }

    }
}
