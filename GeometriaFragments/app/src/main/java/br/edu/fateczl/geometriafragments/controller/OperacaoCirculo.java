package br.edu.fateczl.geometriafragments.controller;

import br.edu.fateczl.geometriafragments.model.Circulo;

public class OperacaoCirculo implements IGeometriaController<Circulo>{

    @Override
    public float calcularArea(Circulo c) {
        return 3.14f * (c.getRaio() * c.getRaio());
    }

    @Override
    public float calcularPerimetro(Circulo c) {
        return 6.28f * c.getRaio();
    }
}
