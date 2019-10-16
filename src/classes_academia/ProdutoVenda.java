/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes_academia;

import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Artur
 */
public class ProdutoVenda {
    private Produto produto;
    private Venda venda;
    private SimpleDoubleProperty qtde;

    public ProdutoVenda() {
        this.qtde = new SimpleDoubleProperty();
    }

    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Double getQuantidade() {
        return qtde.get();
    }

    public void setQuantidade(Double decimal) {
        this.qtde.set(decimal);
    }
    
    
}
