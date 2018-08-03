/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes_academia;

/**
 *
 * @author Artur
 */
public class QuadroHorario {
    
    private int qtdePessoasPorHora;
    private String hora;

    public QuadroHorario() {
    }

    public QuadroHorario(int qtdePessoasPorHora, String hora) {
        this.qtdePessoasPorHora = qtdePessoasPorHora;
        this.hora = hora;
    }

    
    public int getQtdePessoasPorHora() {
        return qtdePessoasPorHora;
    }

    public void setQtdePessoasPorHora(int qtdePessoasPorHora) {
        this.qtdePessoasPorHora = qtdePessoasPorHora;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    
}
