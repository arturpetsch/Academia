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
public class Mensalidade {
 
    private double valor;
    private int idMensalidade;
    private int diaVencimento;
    private Cliente cliente;

    public Mensalidade() {
    }

    
    public Mensalidade(double valor, int diaVencimento, Cliente cliente){
        this.valor = valor;
        this.diaVencimento = diaVencimento;
        this.cliente = cliente;
    }
    
    public Mensalidade(int id, double valor, int diaVencimento, Cliente cliente){
        this.idMensalidade = id;
        this.valor = valor;
        this.diaVencimento = diaVencimento;
        this.cliente = cliente;
    }

    public Mensalidade(double valor, int diaVencimento){
        this.valor = valor;
        this.diaVencimento = diaVencimento;
    }
    
    public Mensalidade(double valor, int diaVencimento, int idMensalidade){
        this.valor = valor;
        this.diaVencimento = diaVencimento;
        this.idMensalidade = idMensalidade;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdMensalidade() {
        return idMensalidade;
    }

    public void setIdMensalidade(int idMensalidade) {
        this.idMensalidade = idMensalidade;
    }

    public int getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(int diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
