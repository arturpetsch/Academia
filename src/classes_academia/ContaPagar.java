/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes_academia;

import java.math.BigDecimal;
import java.time.LocalDate;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Artur
 */
public class ContaPagar {
    
    private int idContaPagar;
    private SimpleDoubleProperty valor;
    private SimpleStringProperty descricao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private int parcela;
    private SimpleStringProperty fornecedor;
    private SimpleBooleanProperty tipoConta;
    private Usuario usuario;

    public ContaPagar(int idContaPagar, double valor, String descricao, LocalDate dataVencimento, LocalDate dataPagamento, 
            int parcela, String fornecedor, boolean tipoConta, Usuario usuario) {
        this.idContaPagar = idContaPagar;
        this.valor = new SimpleDoubleProperty(valor);
        this.descricao = new SimpleStringProperty(descricao);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.parcela = parcela;
        this.fornecedor = new SimpleStringProperty(fornecedor);
        this.tipoConta = new SimpleBooleanProperty(tipoConta);
        this.usuario = usuario;
    }

     public ContaPagar(int idContaPagar, double valor, String descricao, LocalDate dataVencimento, LocalDate dataPagamento, 
            int parcela, String fornecedor, boolean tipoConta) {
        this.idContaPagar = idContaPagar;
        this.valor = new SimpleDoubleProperty(valor);
        this.descricao = new SimpleStringProperty(descricao);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.parcela = parcela;
        this.fornecedor = new SimpleStringProperty(fornecedor);
        this.tipoConta = new SimpleBooleanProperty(tipoConta);
    }
     
    public ContaPagar(double valor, String descricao, LocalDate dataVencimento, LocalDate dataPagamento, int parcela, 
            String fornecedor, boolean tipoConta, Usuario usuario) {
        this.valor = new SimpleDoubleProperty(valor);
        this.descricao = new SimpleStringProperty(descricao);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.parcela = parcela;
        this.fornecedor = new SimpleStringProperty(fornecedor);
        this.tipoConta = new SimpleBooleanProperty(tipoConta);
        this.usuario = usuario;
    }

    public ContaPagar(double valor, String descricao, LocalDate dataVencimento, LocalDate dataPagamento, int parcela, 
            String fornecedor, boolean tipoConta) {
        this.valor = new SimpleDoubleProperty(valor);
        this.descricao = new SimpleStringProperty(descricao);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.parcela = parcela;
        this.fornecedor = new SimpleStringProperty(fornecedor);
        this.tipoConta = new SimpleBooleanProperty(tipoConta);
    }

    public ContaPagar() {
        this.descricao = new SimpleStringProperty();
         this.fornecedor = new SimpleStringProperty();
        this.tipoConta = new SimpleBooleanProperty();
        this.valor = new SimpleDoubleProperty();
    }
    
    public int getIdContaPagar() {
        return idContaPagar;
    }

    public void setIdContaPagar(int idContaPagar) {
        this.idContaPagar = idContaPagar;
    }

    public double getValor() {
        return valor.get();
    }

    public void setValor(double valor) {
        this.valor.set(valor);
    }

    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public String getFornecedor() {
        return fornecedor.get();
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor.set(fornecedor);
    }

    public boolean getTipoConta() {
        return tipoConta.get();
    }

    public void setTipoConta(boolean tipoConta) {
        this.tipoConta.set(tipoConta);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
