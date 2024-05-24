package br.edu.fateczl.natacaofragments.model;

import androidx.annotation.NonNull;

public class AtletaSenior extends Atleta{
    private boolean cardiaco;
    private String isCardiaco;
    public AtletaSenior() {
        super();
    }

    public boolean isCardiaco() {
        return cardiaco;
    }

    public void setCardiaco(boolean cardiaco) {
        if(cardiaco){
            isCardiaco = "Sim";
        } else{
            isCardiaco = "Nao";
        }
        this.cardiaco = cardiaco;
    }



    @NonNull
    @Override
    public String toString() {
        return "AtletaSenior{" +
                "cardiaco=" + isCardiaco +
                ", nome='" + nome + '\'' +
                ", dataNasc=" + dataNasc +
                ", bairro='" + bairro + '\'' +
                '}';
    }
}
