package br.edu.fateczl.dogwalkerprojetc.controller;

import androidx.annotation.NonNull;

public class PasseioUmaHora implements IPagamento{
    @Override
    public float calcularPagamento() {
        float valorPasseio = 80f;
        return valorPasseio;
    }

    @NonNull
    @Override
    public String toString() {
        return "Uma hora de passeio";
    }
}
