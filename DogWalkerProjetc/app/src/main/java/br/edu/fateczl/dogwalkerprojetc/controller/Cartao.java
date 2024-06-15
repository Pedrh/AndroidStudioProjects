package br.edu.fateczl.dogwalkerprojetc.controller;

import androidx.annotation.NonNull;

public class Cartao implements IPagamento{
    @Override
    public float calcularPagamento() {
        float desconto = 0.10f;
        return desconto;
    }

    @NonNull
    @Override
    public String toString() {
        return "Forma de pagamento Cartao";
    }
}
