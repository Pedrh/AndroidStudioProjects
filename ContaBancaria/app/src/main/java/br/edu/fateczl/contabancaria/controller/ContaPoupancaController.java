package br.edu.fateczl.contabancaria.controller;

import br.edu.fateczl.contabancaria.model.ContaBancaria;
import br.edu.fateczl.contabancaria.model.ContaPoupanca;

public class ContaPoupancaController extends ContaFactory{

    @Override
    public ContaBancaria criarConta(String nome, int numConta, float saldo) {
        ContaPoupanca conta = new ContaPoupanca();

        conta.setCliente(nome);
        conta.setNumConta(numConta);
        conta.setSaldo(saldo);
        conta.setDiaRendimento(getDiaRend());

        return conta;
    }
}
