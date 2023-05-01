package com.example.projetodespesas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener{
    final int TELA_MAIN = 1234;
    ArrayList<Categoria> categorias;
    ListView listView;
    EditText edCat, catTotal, catCount, catDesc;
    CategoriaAdapter adapter;
    Categoria categoriaIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            categorias = (ArrayList<Categoria>) savedInstanceState.getSerializable("lista_cat");
        }else{
            categorias = new ArrayList<>();
        }
        setContentView(R.layout.activity_main);
        edCat = (EditText) findViewById(R.id.ed_cat);
        listView = (ListView) findViewById(R.id.lista_cat);


        adapter = new CategoriaAdapter(this, categorias);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemLongClickListener(this);
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Categoria cat = categorias.get(position);
        cat.setContas(categorias.get(position).getContas());
        Intent intent = new Intent(MainActivity.this, TelaContas.class);
        intent.putExtra("category", cat);
        startActivityForResult(intent, TELA_MAIN);
        return true;
    }


    public void addCategoria(View v){
        Categoria cat = new Categoria();
        if(edCat.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.campo_vazio, Toast.LENGTH_SHORT).show();
        }else {
            cat.setDescricao(edCat.getText().toString());
            categorias.add(cat);
            adapter.notifyDataSetChanged();
        }
        edCat.setText("");

    }

    @Override
    public void onActivityResult(int codigo, int resultado, Intent dados) {
        super.onActivityResult(codigo, resultado, dados);
        if(codigo == TELA_MAIN){
                Categoria catReplace = (Categoria) dados.getSerializableExtra("category1");
                categorias.set(categorias.indexOf(catReplace), catReplace);
                adapter.notifyDataSetChanged();
            }
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_categorias, menu);
        return true;
    }
    class CategoriaAdapter extends ArrayAdapter<Categoria> {
        public CategoriaAdapter(Context ctx, ArrayList<Categoria> lista) {
            super(ctx, android.R.layout.simple_list_item_single_choice, lista);
        }

        @Override
        public View getView(int posicao, View reciclada, ViewGroup grupo) {
            if (reciclada == null) {
                reciclada = getLayoutInflater().inflate(R.layout.itens_categoria,
                        null);
            }
            Categoria c = categorias.get( posicao );
            ((TextView) reciclada.findViewById(R.id.cat_desc)).setText(getText(R.string.categoria) +": "+c.getDescricao());
            ((TextView) reciclada.findViewById(R.id.cat_count)).setText(getText(R.string.quantidade) +": "+String.valueOf(c.getContas().size()));
            ((TextView) reciclada.findViewById(R.id.cat_total)).setText("R$ "+c.getContas().stream().mapToDouble((a)-> a.getValor()).sum());
            return reciclada;
        }

    }
    public void removerCat(MenuItem mi){
        int pos = listView.getCheckedItemPosition();
        if(pos >=0){
            Categoria c = categorias.get(pos);
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setTitle(getText(R.string.confirmacao));
            bld.setMessage(getText(R.string.excluir_cat)+ c.getDescricao());
            bld.setPositiveButton(getText(R.string.sim), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    categorias.remove(pos);
                    adapter.notifyDataSetChanged();
                }
            });
            bld.setCancelable(false);
            bld.setNegativeButton(getText(R.string.nao), null);
            bld.show();
        }else {
            Toast.makeText(this, getText(R.string.selecione_cat_remove), Toast.LENGTH_SHORT).show();
        }
    }
    public void editarCat(MenuItem mi){
        int pos = listView.getCheckedItemPosition();
        if(pos >=0){
            Categoria cat = categorias.get(pos);
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setTitle(getText(R.string.confirmacao));
            bld.setMessage(getText(R.string.editar_cat)+ cat.getDescricao());
            bld.setPositiveButton(getText(R.string.sim), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cat.setContas(categorias.get(pos).getContas());
                    Intent intent = new Intent(MainActivity.this, TelaContas.class);
                    intent.putExtra("category", cat);
                    startActivityForResult(intent, TELA_MAIN);
                }
            });
            bld.setCancelable(false);
            bld.setNegativeButton(getText(R.string.nao), null);
            bld.show();
        }else {
            Toast.makeText(this, getText(R.string.editar_cat), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle dados) {
        dados.putSerializable("lista_cat", categorias);
        super.onSaveInstanceState(dados);
    }

    }

