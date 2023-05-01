package com.example.primeiro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText entrada;
    TextView resultado;
    ListView lista;
    ArrayAdapter adapter;

    ArrayList<Integer> numeros;
    int soma=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada = (EditText) findViewById(R.id.editor);
        resultado = (TextView) findViewById(R.id.resultado);
        lista = (ListView) findViewById(R.id.lista);

        if(savedInstanceState != null){
            soma = savedInstanceState.getInt("soma_atual", 0);
            numeros = savedInstanceState.getIntegerArrayList("lista_nums");
            resultado.setText("Resultado: "+soma);
        }
        if(numeros == null){
            numeros = new ArrayList<>();
        }

        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_multiple_choice, numeros);
        lista.setAdapter(adapter);
        lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

    }
    public void somar(View btn){

        try {
            String txt = entrada.getText().toString();
            int num = Integer.parseInt(txt);
            numeros.add(num);
            int vlr = Integer.parseInt(txt);
            soma += vlr;
            resultado.setText("Resultado: "+ soma);
            entrada.setText("");
            adapter.notifyDataSetChanged();
        }catch (Throwable ex){
            Toast.makeText(this, "Informe um numero a somar",
                    Toast.LENGTH_SHORT).show();
        }


    }
    public void remover(View btn){
       /*
        try{
            int idx = lista.getCheckedItemPosition();
            if (idx < 0){
                Toast.makeText(this, "Selecione um número",
                        Toast.LENGTH_SHORT).show();
            }else {
                int num = numeros.remove(idx);
                soma -= num;
                resultado.setText("Resultado: "+ soma);
                adapter.notifyDataSetChanged();
                lista.clearChoices();

            }
        }catch (Throwable t){

        }*/

        int count = lista.getCheckedItemCount();
        if(count>0){
            SparseBooleanArray sels = lista.getCheckedItemPositions();
            ArrayList aRemover = new ArrayList<>(count);
            for(int i=0; i < numeros.size(); i++){
                if(sels.get(i)){
                    aRemover.add(numeros.get(i));
                    soma -= numeros.get(i);
                }

            }
            numeros.removeAll(aRemover);
        }
        resultado.setText("Resultado: "+ soma);
        adapter.notifyDataSetChanged();
        lista.clearChoices();


    }

    @Override
    public void onSaveInstanceState(Bundle dados){
        super.onSaveInstanceState(dados);
        dados.putInt("soma_atual", soma);
        dados.putIntegerArrayList("lista_nums", numeros);
    }





}