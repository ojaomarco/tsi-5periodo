package com.example.garagem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Veiculo> veiculos;
    EditText edMarca, edModelo, edAno, edPlaca, edValor;
    RadioGroup botoes;
    ListView lista;
    VeiculoAdapter adapter;
    int posicaoSelecionada = -1;

    Veiculo veiculoEdicao = null;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == posicaoSelecionada){
            posicaoSelecionada = 1;
        } else {
            posicaoSelecionada = position;
        }
        adapter.notifyDataSetChanged();
    }

    class VeiculoAdapter extends ArrayAdapter<Veiculo> {
        public VeiculoAdapter(Context ctx, ArrayList<Veiculo> lista) {
            super(ctx, android.R.layout.simple_list_item_1, lista);
        }

        @Override
        public View getView(int posicao, View reciclada, ViewGroup grupo) {
            if (reciclada == null) {
                reciclada = getLayoutInflater().inflate(R.layout.item_veiculo,
                        null);
            }
            if (posicao == posicaoSelecionada){
                reciclada.setBackgroundColor(Color.LTGRAY);
            } else {
                reciclada.setBackgroundColor(Color.TRANSPARENT);
            }

            Veiculo v = veiculos.get( posicao );
            ImageView img = (ImageView) reciclada.findViewById(R.id.item_imagem);
            switch (v.getTipo()) {
                case 'A': img.setImageResource(R.drawable.automovel); break;
                case 'M': img.setImageResource(R.drawable.moto); break;
                case 'C': img.setImageResource(R.drawable.caminhao); break;
                case 'B': img.setImageResource(R.drawable.barco); break;

            }
            ((TextView) reciclada.findViewById(R.id.item_marca_modelo)).setText(
                    v.getMarca() + " - " +v.getModelo());
            ((TextView) reciclada.findViewById(R.id.item_placa_ano)).setText(
                    v.getPlaca()+ " - " + v.getAno() );
            ((TextView) reciclada.findViewById(R.id.item_valor)).setText("R$ "+v.getValor());
            return reciclada;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edMarca = (EditText) findViewById(R.id.ed_marca);
        edModelo = (EditText) findViewById(R.id.ed_modelo);
        edAno = (EditText) findViewById(R.id.ed_ano);
        edPlaca = (EditText) findViewById(R.id.ed_placa);
        edValor = (EditText) findViewById(R.id.ed_valor);
        botoes = (RadioGroup) findViewById(R.id.rg_tipo);
        lista = (ListView) findViewById(R.id.lista_veiculos);
        veiculos = new ArrayList<>();
        adapter = new VeiculoAdapter(this, veiculos);
        lista.setAdapter( adapter );
        lista.setOnItemClickListener( this );
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                veiculoEdicao = veiculos.get(position);
                edMarca.setText(veiculoEdicao.getMarca());
                edModelo.setText(veiculoEdicao.getModelo());
                edAno.setText(""+veiculoEdicao.getAno());
                edPlaca.setText(veiculoEdicao.getPlaca());
                edValor.setText(""+veiculoEdicao.getValor());
                if(veiculoEdicao.getTipo() == 'A'){
                    botoes.check(R.id.rb_automovel);
                }
                if(veiculoEdicao.getTipo() == 'M'){
                    botoes.check(R.id.rb_moto);
                }
                if(veiculoEdicao.getTipo() == 'C'){
                    botoes.check(R.id.rb_caminhao);
                }
                return true;
            }
        });
    }

    public void adicionar( View view) {
        Veiculo v = new Veiculo();
        if(veiculoEdicao != null){
            v = veiculoEdicao;
        }
        switch (botoes.getCheckedRadioButtonId()) {
            case R.id.rb_automovel: v.setTipo('A'); break;
            case R.id.rb_moto: v.setTipo('M'); break;
            case R.id.rb_caminhao: v.setTipo('C'); break;
        }
        v.setMarca( edMarca.getText().toString() );
        v.setModelo( edModelo.getText().toString() );
        v.setAno( Integer.parseInt( edAno.getText().toString() ) );
        v.setPlaca( edPlaca.getText().toString() );
        v.setValor( Double.parseDouble(edValor.getText().toString() ) );
        if( veiculoEdicao == null) {
            veiculos.add(v);
        } else {
            veiculoEdicao = null;
        }
        adapter.notifyDataSetChanged();
        edMarca.setText("");
        edModelo.setText("");
        edAno.setText("");
        edValor.setText("");
        edPlaca.setText("");
    }
    public void remover(View v){
        if (posicaoSelecionada == -1){
            Toast.makeText(this, "Selecione o veiculo a remover", Toast.LENGTH_SHORT).show();
        } else {
            veiculos.remove(posicaoSelecionada);
            posicaoSelecionada = -1;
            adapter.notifyDataSetChanged();
        }

    }
}