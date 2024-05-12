package br.edu.fateczl.calcularsalarioprofessor.model;

public class ProfessorTitular extends Professor{
    private int anosInst;
    private double salario;

    public ProfessorTitular(){
        super();
    }

    public int getAnosInst() {
        return anosInst;
    }

    public void setAnosIsnt(int anosInst) {
        this.anosInst = anosInst;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public double calcSalario() {
        int valorDeIncremento = anosInst / 5;
        double incremento = valorDeIncremento * 0.05;
        this.salario += salario * incremento;
        return salario;
    }
}
