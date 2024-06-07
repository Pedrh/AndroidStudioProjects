package br.edu.fateczl.crudbiblioteca.model;

import androidx.annotation.NonNull;

public class Aluno {

    private int Ra;
    private String nome;
    private String email;

    public Aluno() {
        super();
    }

    public int getRa() {
        return Ra;
    }

    public void setRa(int ra) {
        Ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return nome + " - " + Ra;
    }
}
