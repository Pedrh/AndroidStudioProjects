package br.edu.fateczl.dogwalkerprojetc.model;

import androidx.annotation.NonNull;

public class Dono extends Usuario{
    private int cep;
    private String email;

    public Dono() {
        super();
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
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
        return "Dono: " + nome + "(" + cep + ") - " + email;
    }
}
