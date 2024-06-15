package br.edu.fateczl.dogwalkerprojetc.controller;

import androidx.annotation.NonNull;

public class PasseioDuasHoras implements IPagamento{
    @Override
    public float calcularPagamento() {
        float valorPasseio = 160f;
        return valorPasseio;
    }

    @NonNull
    @Override
    public String toString() {
        return "Duas horas de passeio";
    }
}
