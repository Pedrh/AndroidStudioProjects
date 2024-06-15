package br.edu.fateczl.dogwalkerprojetc.controller;

import androidx.annotation.NonNull;

public class Dinheiro implements IPagamento{
    @Override
    public float calcularPagamento() {
        float desconto = 0.05f;
        return desconto;
    }

    @NonNull
    @Override
    public String toString() {
        return "Forma de pagamento Dinheiro";
    }
}
