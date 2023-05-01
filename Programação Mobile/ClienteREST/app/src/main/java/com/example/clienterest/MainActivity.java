package com.example.clienterest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.clienterest.model.Produto;
import com.example.clienterest.model.Setor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity{
    ArrayAdapter<Setor> adapter;
    ListView lista;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new GsonBuilder().create();



    }

    public void inserir(View v) {
        String descricao = ((EditText) findViewById(R.id.edSetor)).getText().toString();
        double margem = Double.parseDouble(((EditText) findViewById(R.id.edMargem)).getText().toString());
        Setor s = new Setor(0, descricao, margem);
        final String json = gson.toJson( s );
        new Thread() {
            public void run(){
                Looper.prepare();
                try {
                    URL url = new URL("http://argo.td.utfpr.edu.br/clients/ws/setor");
                    HttpURLConnection cnx = (HttpURLConnection) url.openConnection();
                    cnx.setRequestMethod("POST");
                    cnx.setRequestProperty("Content-Type","application/json");
                    PrintWriter saida = new PrintWriter(cnx.getOutputStream() );
                    saida.println(json);
                    saida.flush();
                    cnx.connect();
                    if (cnx.getResponseCode() == 201){
                        Toast.makeText(getApplicationContext(),
                                "Setor cadastrado com sucesso!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Falha no cadastro do setor.",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                Looper.loop();
            }
        }.start();
    }

    public void excluir(View v){
        new Thread() {
            public void run(){
                Looper.prepare();
                try {
                    URL url = new URL("http://argo.td.utfpr.edu.br/clients/ws/setor/14");
                    HttpURLConnection cnx = (HttpURLConnection) url.openConnection();
                    cnx.setRequestMethod("DELETE");
                    cnx.connect();
                    if (cnx.getResponseCode() == 204){
                        Toast.makeText(getApplicationContext(),
                                "Setor Deletado!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Falha.",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                Looper.loop();
            }
        }.start();
    }

    public void listar(View v){

    }
    class Buscador extends AsyncTask<String, Void, Setor[]> {

        @Override
        protected Setor[] doInBackground(String... strings) {
            try {
                URL url = new URL("http://argo.td.utfpr.edu.br/clients/ws/setor/");
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.connect();
                if (conn.getResponseCode() == 200) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String linha;
                    do {
                        linha = reader.readLine();
                        if (linha != null) {
                            sb.append(linha);
                        }
                    } while ( linha != null);
                    String json = sb.toString();

                    Gson gson = new Gson();
                    Setor[] setores = gson.fromJson(json, Setor[].class);

                    return setores;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(Setor[] s) {

            ArrayList<Setor> sets = new ArrayList<>(Arrays.asList(s));
            lista = ((ListView) findViewById(R.id.listaSetores));
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, sets);

            lista.setAdapter(adapter);
            lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        }

 }
}