package br.edu.fateczl.contabancaria.controller;

import br.edu.fateczl.contabancaria.model.ContaBancaria;

public abstract class ContaFactory {
    public abstract ContaBancaria criarConta(String nome, int numConta, float saldo);
}
