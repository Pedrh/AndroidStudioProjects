package br.edu.fateczl.vendaingressos.model;

public class IngressoVip extends Ingresso{
    private String func;

    public IngressoVip(){
        super();
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    @Override
    public float valorFinal(float taxa) {
        float valorAumento = valor * ((taxa + 18) / 100f);
        return valor += valorAumento;
    }
}
