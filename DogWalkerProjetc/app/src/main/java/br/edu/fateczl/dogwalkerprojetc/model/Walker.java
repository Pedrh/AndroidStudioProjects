package br.edu.fateczl.dogwalkerprojetc.model;

import androidx.annotation.NonNull;

public class Walker extends Usuario{
    private int anosExperiencia;

    public Walker() {
        super();
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    @NonNull
    @Override
    public String toString() {
        return nome  + " - " + telefone + " (Walker a " + anosExperiencia + " anos)";
    }
}
