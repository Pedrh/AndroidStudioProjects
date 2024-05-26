package br.edu.fateczl.geometriafragments.controller;

public interface IGeometriaController<T> {

    public float calcularArea(T t);
    public float calcularPerimetro(T t);
}
