package com.example.buscacep;

import android.app.IntentService;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class ServicoCep extends IntentService {
    Gson gson;
    public ServicoCep() {
        super("ServicoCep");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String cep = intent.getStringExtra("CEP");
            String endereco = "https://viacep.com.br/ws/"+cep+"/json";
            try {
                URL ender = new URL(endereco);
                HttpsURLConnection conn = (HttpsURLConnection) ender.openConnection();
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuilder bld = new StringBuilder(1000);

                do {
                    line = reader.readLine();
                    if(line != null){
                        bld.append(line);
                    }
                }while(line != null);

            } catch (Throwable t) {
                t.printStackTrace();
            }

        }
    }

}