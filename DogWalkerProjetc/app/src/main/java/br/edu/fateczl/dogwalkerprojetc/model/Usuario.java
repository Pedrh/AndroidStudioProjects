package br.edu.fateczl.dogwalkerprojetc.model;

import androidx.annotation.NonNull;

public abstract class  Usuario {
    protected String nome;
    protected int codigo;
    protected int telefone;

    public Usuario() {
        super();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }


    @NonNull
    @Override
    public String toString() {
        return nome + "(" + telefone + ")";
    }

}
