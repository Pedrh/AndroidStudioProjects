package br.edu.fateczl.natacaofragments.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.natacaofragments.model.AtletaSenior;

public class OperacaoSenior implements IOperacao<AtletaSenior>{
    List<AtletaSenior> lista;

    public OperacaoSenior(){
        this.lista = new ArrayList<>();
    }


    @Override
    public void cadastrar(AtletaSenior atletaSenior) {
        lista.add(atletaSenior);
    }

    @Override
    public List<AtletaSenior> listar() {
        return this.lista;
    }
}
