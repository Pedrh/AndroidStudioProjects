package br.edu.fateczl.contabancaria.controller;

import br.edu.fateczl.contabancaria.model.ContaBancaria;

public abstract class ContaFactory {
    private int diaRend;
    private float limite;

    public int getDiaRend() {
        return diaRend;
    }

    public void setDiaRend(int diaRend) {
        this.diaRend = diaRend;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    public abstract ContaBancaria criarConta(String nome, int numConta, float saldo);
}
