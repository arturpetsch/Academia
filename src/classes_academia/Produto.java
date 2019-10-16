/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes_academia;

import java.math.BigDecimal;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Artur
 */
public class Produto {
    private int idProduto;
    private SimpleStringProperty descricao;
    private LocalDate dataVencimento;
    private SimpleStringProperty unidade_medida;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private SimpleStringProperty lote;
    private LocalDate dataCompra;
    private SimpleStringProperty fornecedor;
    private double quantidade;

    public Produto() {
        this.descricao = new SimpleStringProperty();
        this.unidade_medida  = new SimpleStringProperty();
        this.lote  = new SimpleStringProperty();
        this.fornecedor  = new SimpleStringProperty();
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
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

    public String getUnidade_medida() {
        return unidade_medida.get();
    }

    public void setUnidade_medida(String unidade_medida) {
        this.unidade_medida.set(unidade_medida);
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getLote() {
        return lote.get();
    }

    public void setLote(String lote) {
        this.lote.set(lote);
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getFornecedor() {
        return fornecedor.get();
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor.set(fornecedor);
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    
}
