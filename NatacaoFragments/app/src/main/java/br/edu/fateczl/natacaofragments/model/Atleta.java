package br.edu.fateczl.natacaofragments.model;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public abstract class Atleta {
    protected String nome;
    protected String dataNasc;
    protected String bairro;

    public Atleta() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @NonNull
    @Override
    public String toString() {
        return "Atleta{" +
                "nome='" + nome + '\'' +
                ", dataNasc=" + dataNasc +
                ", bairro='" + bairro + '\'' +
                '}';
    }
}
