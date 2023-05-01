package com.example.projetodespesas;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class Conta implements Serializable{
    private String descricao;
    private Date vencimento;
    private double valor;
    private Categoria categoria;


    @Override
    public String toString() {
        return (this.descricao +"             R$"+this.getValor());
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
