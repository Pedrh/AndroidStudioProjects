package br.edu.fateczl.vendaingressos.model;

public class Ingresso {
    protected String id;
    protected float valor;

    public Ingresso(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float valorFinal(float taxa){
        float valorAumento = valor * (taxa / 100f);
        return valor += valorAumento;
    }
}
