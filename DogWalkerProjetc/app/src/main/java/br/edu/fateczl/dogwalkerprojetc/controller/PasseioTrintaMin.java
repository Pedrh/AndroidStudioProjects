package br.edu.fateczl.dogwalkerprojetc.controller;

import androidx.annotation.NonNull;

public class PasseioTrintaMin implements IPagamento{
    @Override
    public float calcularPagamento() {
        float valorPasseio = 40f;
        return valorPasseio;
    }

    @NonNull
    @Override
    public String toString() {
        return "Trinta minutos de passeio";
    }
}
