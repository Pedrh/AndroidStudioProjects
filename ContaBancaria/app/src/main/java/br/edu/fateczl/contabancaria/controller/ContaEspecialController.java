package br.edu.fateczl.contabancaria.controller;

import br.edu.fateczl.contabancaria.model.ContaBancaria;
import br.edu.fateczl.contabancaria.model.ContaEspecial;

public class ContaEspecialController extends ContaFactory{
    @Override
    public ContaBancaria criarConta(String nome, int numConta, float saldo) {
        ContaEspecial conta = new ContaEspecial();

        conta.setCliente(nome);
        conta.setNumConta(numConta);
        conta.setSaldo(saldo);
        conta.setLimite(getLimite());

        return conta;
    }
}
