package br.edu.fateczl.natacaofragments.model;

import androidx.annotation.NonNull;

public class AtletaComum extends Atleta{
    private String academia;
    private int recorde;

    public AtletaComum() {
        super();
    }

    public String getAcademia() {
        return academia;
    }

    public void setAcademia(String academia) {
        this.academia = academia;
    }

    public int getRecorde() {
        return recorde;
    }

    public void setRecorde(int recorde) {
        this.recorde = recorde;
    }

    @NonNull
    @Override
    public String toString() {
        return "AtletaComum{" +
                "academia='" + academia + '\'' +
                ", recorde=" + recorde +
                ", nome='" + nome + '\'' +
                ", dataNasc=" + dataNasc +
                ", bairro='" + bairro + '\'' +
                '}';
    }
}
