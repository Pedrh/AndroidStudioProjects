package br.edu.fateczl.natacaofragments.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.natacaofragments.model.AtletaComum;

public class OperacaoComum implements IOperacao<AtletaComum>{
    List<AtletaComum> lista;

    public OperacaoComum(){
        this.lista = new ArrayList<>();
    }

    @Override
    public void cadastrar(AtletaComum atletaComum) {
        lista.add(atletaComum);
    }

    @Override
    public List<AtletaComum> listar() {
        return this.lista;
    }
}
