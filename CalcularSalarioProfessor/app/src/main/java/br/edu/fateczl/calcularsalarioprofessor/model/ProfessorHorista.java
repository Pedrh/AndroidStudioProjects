package br.edu.fateczl.calcularsalarioprofessor.model;

public class ProfessorHorista extends Professor{
    private int horaAula;
    private double valorHora;

    public ProfessorHorista(){
        super();
    }

    public int getHoraAula() {
        return horaAula;
    }

    public void setHoraAula(int horaAula) {
        this.horaAula = horaAula;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    @Override
    public double calcSalario() {
        return horaAula * valorHora;
    }
}
