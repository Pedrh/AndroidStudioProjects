package br.edu.fateczl.dogwalkerprojetc.controller;

import br.edu.fateczl.dogwalkerprojetc.model.Agendar;

public class PagamentoAgendarController {

    public float calcularValorFinal(Agendar ag){
        IPagamento forma;
        if(ag.getFormaPagto().equals("pix")){
            forma = new Pix();
        } else if(ag.getFormaPagto().equals("cartao")){
            forma = new Cartao();
        } else{
            forma = new Dinheiro();
        }

        IPagamento tempo;
        if(ag.getTmpPasseio() == 30){
            tempo = new PasseioTrintaMin();
        } else if(ag.getTmpPasseio() == 1){
            tempo = new PasseioUmaHora();
        } else{
            tempo = new PasseioDuasHoras();
        }

        float valorFinal = (ag.getQtdPasseio() * 30) + tempo.calcularPagamento();
        valorFinal -= (valorFinal * forma.calcularPagamento());

        return valorFinal;
    }
}
