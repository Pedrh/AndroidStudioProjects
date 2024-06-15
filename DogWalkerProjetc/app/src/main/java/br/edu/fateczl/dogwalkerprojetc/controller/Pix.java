package br.edu.fateczl.dogwalkerprojetc.controller;

import androidx.annotation.NonNull;

public class Pix implements IPagamento{
    @Override
    public float calcularPagamento() {
        float desconto = 0.15f;
        return desconto;
    }

    @NonNull
    @Override
    public String toString() {
        return "Forma de pagamento Pix";
    }
}
