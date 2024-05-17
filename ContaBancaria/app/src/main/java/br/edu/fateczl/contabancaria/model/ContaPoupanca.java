package br.edu.fateczl.contabancaria.model;

import androidx.annotation.NonNull;

public class ContaPoupanca extends ContaBancaria{
    private int diaRendimento;

    public ContaPoupanca(){
        super();
    }

    public int getDiaRendimento() {
        return diaRendimento;
    }

    public void setDiaRendimento(int diaRendimento) {
        this.diaRendimento = diaRendimento;
    }

    public float recalcSaldo(float valor){
        float taxaRendimento = diaRendimento * (valor / 100f);
        return saldo += (saldo * taxaRendimento);
    }

    @NonNull
    @Override
    public String toString() {
        return "Nome cliente: " + cliente + ", numero da conta: " + numConta + ", dias de rendimento: " + diaRendimento +
                ", saldo: " + saldo;
    }
}
