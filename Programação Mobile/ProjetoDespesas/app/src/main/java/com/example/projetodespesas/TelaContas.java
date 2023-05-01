package com.example.projetodespesas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TelaContas extends AppCompatActivity implements AdapterView.OnItemClickListener{
    int itemSelecionado = -1;
    TextView nomeCategoria, valor, despesaDesc;
    Categoria categoriaSelecionada;
    Date data;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    ArrayList contas;
    ContaAdapter adapter;
    ListView listView;
    EditText dataVenc;
    Conta contaEdicao;
    int posicao;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == itemSelecionado){
            itemSelecionado = 1;
        } else {
            itemSelecionado = position;
        }
        adapter.notifyDataSetChanged();
    }

    class ContaAdapter extends ArrayAdapter<Conta> {
        public ContaAdapter(Context ctx, ArrayList<Conta> lista) {
            super(ctx, android.R.layout.simple_list_item_single_choice, lista);
        }
        @Override
        public View getView(int posicao, View reciclada, ViewGroup grupo) {
            if (reciclada == null) {
                reciclada = getLayoutInflater().inflate(R.layout.itens_conta,
                        null);
            }

            Conta c = (Conta) contas.get( posicao );
            ((TextView) reciclada.findViewById(R.id.conta_desc)).setText(getText(R.string.despesa)+": "+c.getDescricao());
            ((TextView) reciclada.findViewById(R.id.conta_valor)).setText(getText(R.string.valor)+": R$"+String.valueOf(c.getValor()));
            ((TextView) reciclada.findViewById(R.id.conta_vencimento)).setText(getText(R.string.vencimento)+": "+sdf.format(c.getVencimento()));
            return reciclada;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            contas = (ArrayList<Conta>) savedInstanceState.getSerializable("lista_contas");
        }else{
            setContentView(R.layout.activity_tela_contas);
            categoriaSelecionada = (Categoria) getIntent().getSerializableExtra("category");
            contas = new ArrayList<Conta> (categoriaSelecionada.getContas());
        }

        posicao = getIntent().getExtras().getInt("posicao");
        nomeCategoria = (TextView) findViewById(R.id.ed_nome_cat);
        nomeCategoria.setText(categoriaSelecionada.getDescricao());
        dataVenc = ((EditText) findViewById(R.id.ed_vencimento));
        despesaDesc = (TextView) findViewById(R.id.ed_conta);
        valor = (TextView) findViewById(R.id.ed_valor);
        listView = (ListView) findViewById(R.id.lista_contas);
        if(data != null){
            ((EditText) findViewById(R.id.ed_vencimento)).setText(sdf.format(data));
        }
        adapter =  new ContaAdapter(this, contas);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter.notifyDataSetChanged();
    }
    public void addConta(View v){
        Conta c = new Conta();
        if(contaEdicao != null){
            c = contaEdicao;
        }

        if(despesaDesc.getText().toString().isEmpty() || valor.getText().toString().isEmpty() || dataVenc.getText().toString().isEmpty()){
            Toast.makeText(this, getText(R.string.campo_vazio), Toast.LENGTH_SHORT).show();
        }else{
            c.setDescricao(despesaDesc.getText().toString());
            c.setValor(Double.parseDouble(valor.getText().toString()));
            c.setVencimento(data);
            if( contaEdicao == null) {
                contas.add(c);
            } else {
                contaEdicao = null;
            }
            adapter.notifyDataSetChanged();
            categoriaSelecionada.setContas(contas);
        }

        despesaDesc.setText("");
        valor.setText("");
        dataVenc.setText("");
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
                data = calendar.getTime();
                dataVenc.setText(sdf.format(data));
            }
        });
        dlgData.show();
    }

    public void onBackPressed() {
        Intent intent = new Intent(TelaContas.this, MainActivity.class);
        intent.putExtra("category1", categoriaSelecionada);
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }
    public void removerConta(MenuItem mi){
        int pos = listView.getCheckedItemPosition();
        if(pos >=0){
            Conta c = (Conta) contas.get(pos);
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setTitle(getText(R.string.confirmacao));
            bld.setMessage("Deseja excluir a despesa: "+ c.getDescricao());
            bld.setPositiveButton(getText(R.string.sim), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    contas.remove(pos);
                    adapter.notifyDataSetChanged();
                }
            });
            bld.setCancelable(false);
            bld.setNegativeButton(getText(R.string.nao), null);
            bld.show();
        }else {
            Toast.makeText(this,getText(R.string.excluir_despesa), Toast.LENGTH_SHORT).show();
        }
    }
    public void editarConta(MenuItem mi){
        int pos = listView.getCheckedItemPosition();
        if(pos >=0){
            Conta cat = (Conta) contas.get(pos);
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setTitle(getText(R.string.confirmacao));
            bld.setMessage(getText(R.string.editar_despesa) + cat.getDescricao());
            bld.setPositiveButton(getText(R.string.sim), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    contaEdicao = (Conta) contas.get(pos);
                    despesaDesc.setText(contaEdicao.getDescricao());
                    valor.setText(String.valueOf(contaEdicao.getValor()));
                    dataVenc.setText(sdf.format(contaEdicao.getVencimento()));
                }
            });
            bld.setCancelable(false);
            bld.setNegativeButton(getText(R.string.nao), null);
            bld.show();
        }else {
            Toast.makeText(this, getText(R.string.editar_despesa), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contas, menu);
        return true;
    }
    @Override
    public void onSaveInstanceState(Bundle dados) {
        dados.putSerializable("lista_contas", contas);
        super.onSaveInstanceState(dados);
    }

    public void voltar(MenuItem mi){
        onBackPressed();
    }
}