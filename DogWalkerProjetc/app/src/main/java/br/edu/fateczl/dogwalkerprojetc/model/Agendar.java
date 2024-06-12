package br.edu.fateczl.dogwalkerprojetc.model;

import androidx.annotation.NonNull;

public class Agendar {
    private Dono dono;
    private Walker walker;
    private int qtdPasseio;
    private int tmpPasseio;
    private String formaPagto;
    private String localEncontro;
    private String dataEncontro;
    private String horaEncontro;

    public Agendar() {
        super();
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public Walker getWalker() {
        return walker;
    }

    public void setWalker(Walker walker) {
        this.walker = walker;
    }

    public int getQtdPasseio() {
        return qtdPasseio;
    }

    public void setQtdPasseio(int qtdPasseio) {
        this.qtdPasseio = qtdPasseio;
    }

    public int getTmpPasseio() {
        return tmpPasseio;
    }

    public void setTmpPasseio(int tmpPasseio) {
        this.tmpPasseio = tmpPasseio;
    }

    public String getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(String formaPagto) {
        this.formaPagto = formaPagto;
    }

    public String getLocalEncontro() {
        return localEncontro;
    }

    public void setLocalEncontro(String localEncontro) {
        this.localEncontro = localEncontro;
    }

    public String getDataEncontro() {
        return dataEncontro;
    }

    public void setDataEncontro(String dataEncontro) {
        this.dataEncontro = dataEncontro;
    }

    public String getHoraEncontro() {
        return horaEncontro;
    }

    public void setHoraEncontro(String horaEncontro) {
        this.horaEncontro = horaEncontro;
    }

    @NonNull
    @Override
    public String toString() {
        return "Agendamento - Hora:" + horaEncontro + ", " + localEncontro + " " + dataEncontro +"\n" +
                "Dono: " + dono.getNome() + " - Walker: " + walker.nome;
    }
}
