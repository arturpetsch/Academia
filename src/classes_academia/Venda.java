/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes_academia;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Artur
 */
public class Venda {
    
       private int idVenda;
       private LocalDate dataVenda;
       private BigDecimal valor_total;
       private int diaVencimento;
       private SimpleBooleanProperty pagamentoAVista;
       private SimpleBooleanProperty pagamentoParcelado;
       private int numeroParcelas;
       private ArrayList<Produto> produtos;
       private Cliente cliente;
       
    public Venda() {
        pagamentoAVista = new SimpleBooleanProperty();
        pagamentoParcelado = new SimpleBooleanProperty();
    }

       
    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getValor_total() {
        return valor_total;
    }

    public void setValor_total(BigDecimal valor_total) {
        this.valor_total = valor_total;
    }

    public int getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(int diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public Boolean getPagamentoAVista() {
        return pagamentoAVista.get();
    }

    public void setPagamentoAVista(Boolean pagamentoAVista) {
        this.pagamentoAVista.set(pagamentoAVista);
    }

    public Boolean getPagamentoParcelado() {
        return pagamentoParcelado.get();
    }

    public void setPagamentoParcelado(Boolean pagamentoParcelado) {
        this.pagamentoParcelado.set(pagamentoParcelado);
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
       
       
}
