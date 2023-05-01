package com.example.contatos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TelaContato extends AppCompatActivity {
    Date dataNascimento;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contato);
        Intent it = getIntent();
        if(it != null){
            Contato ed = (Contato) it.getSerializableExtra("contato_edicao");
            if(ed!= null){
                ((EditText) findViewById(R.id.ed_nome)).setText(ed.getNome());
                ((EditText) findViewById(R.id.ed_telefone)).setText(ed.getTelefone());
                ((EditText) findViewById(R.id.ed_endereco)).setText(ed.getEndereco());
                ((EditText) findViewById(R.id.ed_email)).setText(ed.getEmail());
                if(dataNascimento != null){
                    ((EditText) findViewById(R.id.ed_data_nasc)).setText(sdf.format(dataNascimento));
                }
            }
        }
    }

    public void cancelar(View v){
        setResult(RESULT_CANCELED);
        finish();
    }
    public void confirmar(View v){
        Contato c = new Contato();
        c.setNome(((EditText) findViewById(R.id.ed_nome)).getText().toString());
        c.setTelefone(((EditText) findViewById(R.id.ed_telefone)).getText().toString());
        c.setEndereco(((EditText) findViewById(R.id.ed_endereco)).getText().toString());
        c.setEmail(((EditText) findViewById(R.id.ed_email)).getText().toString());

        Intent resp = new Intent();
        resp.putExtra("novo_contato", c);
        setResult(RESULT_OK, resp);
        finish();

    }

    public void selecionarData(View v){
        DatePickerDialog dlgData = new DatePickerDialog(this);
        dlgData.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dataNascimento = calendar.getTime();
                ((EditText) findViewById(R.id.ed_data_nasc)).setText(sdf.format(dataNascimento));
            }
        });
        dlgData.show();
    }
}