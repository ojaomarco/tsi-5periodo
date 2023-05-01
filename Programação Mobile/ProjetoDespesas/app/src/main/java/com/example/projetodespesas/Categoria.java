package com.example.projetodespesas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Categoria implements Serializable {
    private static int count = 1;
    private int id;
    private String descricao;
    private ArrayList<Conta> contas;

    public Categoria(){
        this.id = count;
        count++;
        contas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public void setContas(ArrayList<Conta> contas) {
        this.contas = contas;
    }
    @Override
    public String toString() {
        return (this.descricao+" "+contas.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria categoria = (Categoria) o;
        return id == categoria.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
