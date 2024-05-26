package br.edu.fateczl.geometriafragments.controller;

import br.edu.fateczl.geometriafragments.model.Retangulo;

public class OperacaoRetangulo implements IGeometriaController<Retangulo>{

    @Override
    public float calcularArea(Retangulo ret) {

        return ret.getBase() * ret.getAltura();
    }

    @Override
    public float calcularPerimetro(Retangulo ret) {
        return (ret.getBase() + ret.getAltura()) * 2;
    }
}
