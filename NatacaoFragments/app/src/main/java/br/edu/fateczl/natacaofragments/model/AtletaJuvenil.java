package br.edu.fateczl.natacaofragments.model;

import androidx.annotation.NonNull;

public class AtletaJuvenil extends Atleta{
    private int anosPratica;

    public AtletaJuvenil() {
        super();
    }

    public int getAnosPratica() {
        return anosPratica;
    }

    public void setAnosPratica(int anosPratica) {
        this.anosPratica = anosPratica;
    }

    @NonNull
    @Override
    public String toString() {
        return "AtletaJuvenil{" +
                "anosPratica=" + anosPratica +
                ", nome='" + nome + '\'' +
                ", dataNasc=" + dataNasc +
                ", bairro='" + bairro + '\'' +
                '}';
    }
}
