/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes_academia;

import java.time.LocalDate;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Artur
 */
public class ContaReceber {
    private int id;
    private Cliente cliente;
    private SimpleDoubleProperty valor;
    private LocalDate dataVencimento;
    private LocalDate dataBaixa;
    private SimpleBooleanProperty tipoConta;
    private Usuario usuario;
    private SimpleStringProperty descricao;
    private Venda venda;
    private Mensalidade mensalidade;
    
    public ContaReceber(int id, Cliente cliente, Double valor, LocalDate dataVencimento, LocalDate dataBaixa,
              boolean tipoConta, Usuario usuario, String descricao, Venda venda){
        this.id = id;
        this.cliente = cliente;
        this.valor = new SimpleDoubleProperty(valor);
        this.dataVencimento = dataVencimento;
        this.dataBaixa = dataBaixa;
        this.tipoConta = new SimpleBooleanProperty(tipoConta);
        this.usuario = usuario;
        this.descricao = new SimpleStringProperty(descricao);
        this.venda = venda;
    }
    
    public ContaReceber(Cliente cliente, Double valor, LocalDate dataVencimento, LocalDate dataBaixa,
              boolean tipoConta, Usuario usuario, String descricao, Venda venda){
        this.cliente = cliente;
        this.valor = new SimpleDoubleProperty(valor);
        this.dataVencimento = dataVencimento;
        this.dataBaixa = dataBaixa;
        this.tipoConta = new SimpleBooleanProperty(tipoConta);
        this.usuario = usuario;
        this.descricao = new SimpleStringProperty(descricao);
        this.venda = venda;
    }
    
    public ContaReceber(int id, Cliente cliente, Double valor, LocalDate dataVencimento, LocalDate dataBaixa,
              boolean tipoConta, Usuario usuario, String descricao, Mensalidade mensalidade){
              this.id = id;
        this.cliente = cliente;
        this.valor = new SimpleDoubleProperty(valor);
        this.dataVencimento = dataVencimento;
        this.dataBaixa = dataBaixa;
        this.tipoConta = new SimpleBooleanProperty(tipoConta);
        this.usuario = usuario;
        this.descricao = new SimpleStringProperty(descricao);
        this.mensalidade = mensalidade;
    }

    
    public ContaReceber(Cliente cliente, Double valor, LocalDate dataVencimento, LocalDate dataBaixa,
              boolean tipoConta, Usuario usuario, String descricao, Mensalidade mensalidade){
              
        this.cliente = cliente;
        this.valor = new SimpleDoubleProperty(valor);
        this.dataVencimento = dataVencimento;
        this.dataBaixa = dataBaixa;
        this.tipoConta = new SimpleBooleanProperty(tipoConta);
        this.usuario = usuario;
        this.descricao = new SimpleStringProperty(descricao);
        this.mensalidade = mensalidade;
    }
    
    public ContaReceber() {
        this.valor = new SimpleDoubleProperty();
        this.tipoConta = new SimpleBooleanProperty();
        this.descricao = new SimpleStringProperty();
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getValor() {
        return valor.get();
    }

    public void setValor(Double valor) {
        this.valor.set(valor);
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(LocalDate dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public boolean isTipoConta() {
        return tipoConta.get();
    }

    public void setTipoConta(SimpleBooleanProperty tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(SimpleStringProperty descricao) {
        this.descricao = descricao;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Mensalidade getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(Mensalidade mensalidade) {
        this.mensalidade = mensalidade;
    }
    
    
}
