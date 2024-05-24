package br.edu.fateczl.natacaofragments.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.natacaofragments.model.AtletaJuvenil;

public class OperacaoJuvenil implements IOperacao<AtletaJuvenil>{
    List<AtletaJuvenil> lista;

    public OperacaoJuvenil(){
        this.lista = new ArrayList<>();
    }

    @Override
    public void cadastrar(AtletaJuvenil atletaJuvenil) {
        lista.add(atletaJuvenil);
    }

    @Override
    public List<AtletaJuvenil> listar() {
        return this.lista;
    }
}
