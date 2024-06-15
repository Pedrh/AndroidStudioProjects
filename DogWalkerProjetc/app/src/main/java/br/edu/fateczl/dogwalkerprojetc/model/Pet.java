package br.edu.fateczl.dogwalkerprojetc.model;

import androidx.annotation.NonNull;

public class Pet {
    private String nome;
    private int id;
    private String raca;
    private String porte;
    private String idade;
    private Dono dono;

    public Pet() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    @NonNull
    @Override
    public String toString() {
        return id +" - " + nome + "(" + raca + ")";
    }
}
