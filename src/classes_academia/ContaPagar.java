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
    private SimpleStringProperty valor;
    private SimpleStringProperty descricao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private int parcela;
    private SimpleStringProperty fornecedor;
    private SimpleBooleanProperty tipoConta;
    private Usuario usuario;

    public ContaPagar(int idContaPagar, String valor, String descricao, LocalDate dataVencimento, LocalDate dataPagamento, 
            int parcela, String fornecedor, boolean tipoConta, Usuario usuario) {
        this.idContaPagar = idContaPagar;
        this.valor = new SimpleStringProperty(valor);
        this.descricao = new SimpleStringProperty(descricao);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.parcela = parcela;
        this.fornecedor = new SimpleStringProperty(fornecedor);
        this.tipoConta = new SimpleBooleanProperty(tipoConta);
        this.usuario = usuario;
    }

     public ContaPagar(int idContaPagar, String valor, String descricao, LocalDate dataVencimento, LocalDate dataPagamento, 
            int parcela, String fornecedor, boolean tipoConta) {
        this.idContaPagar = idContaPagar;
        this.valor = new SimpleStringProperty(valor);
        this.descricao = new SimpleStringProperty(descricao);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.parcela = parcela;
        this.fornecedor = new SimpleStringProperty(fornecedor);
        this.tipoConta = new SimpleBooleanProperty(tipoConta);
    }
     
    public ContaPagar(String valor, String descricao, LocalDate dataVencimento, LocalDate dataPagamento, int parcela, 
            String fornecedor, boolean tipoConta, Usuario usuario) {
        this.valor = new SimpleStringProperty(valor);
        this.descricao = new SimpleStringProperty(descricao);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.parcela = parcela;
        this.fornecedor = new SimpleStringProperty(fornecedor);
        this.tipoConta = new SimpleBooleanProperty(tipoConta);
        this.usuario = usuario;
    }

    public ContaPagar(String valor, String descricao, LocalDate dataVencimento, LocalDate dataPagamento, int parcela, 
            String fornecedor, boolean tipoConta) {
        this.valor = new SimpleStringProperty(valor);
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
        this.valor = new SimpleStringProperty();
    }
    
    public int getIdContaPagar() {
        return idContaPagar;
    }

    public void setIdContaPagar(int idContaPagar) {
        this.idContaPagar = idContaPagar;
    }

    public String getValor() {
        return valor.get();
    }

    public void setValor(String valor) {
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
