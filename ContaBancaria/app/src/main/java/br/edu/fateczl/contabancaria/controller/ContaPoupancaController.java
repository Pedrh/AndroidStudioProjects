package br.edu.fateczl.contabancaria.controller;

import br.edu.fateczl.contabancaria.model.ContaBancaria;
import br.edu.fateczl.contabancaria.model.ContaPoupanca;

public class ContaPoupancaController extends ContaFactory{
    private int diaRend;

    public int getDiaRend() {
        return diaRend;
    }

    public void setDiaRend(int diaRend) {
        this.diaRend = diaRend;
    }

    @Override
    public ContaBancaria criarConta(String nome, int numConta, float saldo) {
        ContaPoupanca conta = new ContaPoupanca();

        conta.setCliente(nome);
        conta.setNumConta(numConta);
        conta.setSaldo(saldo);
        conta.setDiaRendimento(diaRend);

        return conta;
    }
}
