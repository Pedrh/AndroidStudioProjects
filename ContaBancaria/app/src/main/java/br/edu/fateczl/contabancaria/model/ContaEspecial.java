package br.edu.fateczl.contabancaria.model;

import androidx.annotation.NonNull;

public class ContaEspecial extends ContaBancaria{
    private float limite;

    public ContaEspecial(){
        super();
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public float sacar(float valor) throws ArithmeticException {
        if(valor > saldo + limite){
            throw new ArithmeticException("Valor não pode ser maior que o saldo + limite");
        }
        return saldo -= valor;
    }

    @NonNull
    @Override
    public String toString() {
        return "Nome cliente: " + cliente + ", numero da conta: " + numConta + ", limite: " + limite +
                ", saldo: " + saldo;
    }
}
