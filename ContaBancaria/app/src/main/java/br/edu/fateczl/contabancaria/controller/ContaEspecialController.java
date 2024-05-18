package br.edu.fateczl.contabancaria.controller;

import br.edu.fateczl.contabancaria.model.ContaBancaria;
import br.edu.fateczl.contabancaria.model.ContaEspecial;

public class ContaEspecialController extends ContaFactory{
    private float limite;

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public ContaBancaria criarConta(String nome, int numConta, float saldo) {
        ContaEspecial conta = new ContaEspecial();

        conta.setCliente(nome);
        conta.setNumConta(numConta);
        conta.setSaldo(saldo);
        conta.setLimite(limite);

        return conta;
    }
}
