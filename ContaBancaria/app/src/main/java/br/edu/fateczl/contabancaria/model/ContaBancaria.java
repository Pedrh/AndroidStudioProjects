package br.edu.fateczl.contabancaria.model;

import androidx.annotation.NonNull;

public class ContaBancaria {
    protected String cliente;
    protected int numConta;
    protected float saldo;

    public ContaBancaria(){
        super();
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float sacar(float valor) throws ArithmeticException{
        if(valor > saldo){
            throw new ArithmeticException("Valor não pode ser maior que o saldo");
        }
        return saldo -= valor;
    }

    public float depositar(float valor){
        return saldo += valor;
    }

    @NonNull
    @Override
    public String toString() {
        return "Nome cliente: " + cliente + ", numero da conta: " + numConta + ", saldo: " + saldo;
    }
}

