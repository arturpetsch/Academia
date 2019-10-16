/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia_DAO;

import classes_academia.Produto;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Artur
 */
public class ProdutoDAO {
    private Connection connection = null;
    
    public ArrayList<Produto> buscarProduto(String nomeInformado){
        String busca = "SELECT * FROM produto WHERE descricao LIKE " + "'%" + nomeInformado + "%' order by descricao";
        
        ResultSet resultSet;
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        
        try {
            
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            resultSet = preparedStatement.executeQuery(busca);
            
            while(resultSet.next()){
                Produto produto = new Produto();
                produto.setIdProduto(resultSet.getInt("idProduto"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setDataVencimento(resultSet.getDate("dataVencimento").toLocalDate());
                produto.setUnidade_medida(resultSet.getString("unidade_medida"));
                produto.setValorUnitario(resultSet.getBigDecimal("valorUnitario"));
                produto.setLote(resultSet.getString("lote"));
                produto.setDataCompra(resultSet.getDate("dataCompra").toLocalDate());
                produto.setFornecedor(resultSet.getString("fornecedor"));
                produto.setQuantidade(resultSet.getDouble("quantidade"));
                
                produtos.add(produto);
            
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return produtos;
    }
    
    public boolean salvarProduto(Produto produto){
        String salvar = "INSERT INTO produto(descricao, dataVencimento, unidade_medida, valorUnitario, lote"
                + ", dataCompra, fornecedor, quantidade)" + "VALUES(?,?,?,?,?,?,?,?)";   
        
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(salvar);
            preparedStatement.setString(1, produto.getDescricao());
            preparedStatement.setDate(2, java.sql.Date.valueOf(produto.getDataVencimento()));
            preparedStatement.setString(3, produto.getUnidade_medida());
            preparedStatement.setBigDecimal(4, produto.getValorUnitario());
            preparedStatement.setString(5, produto.getLote());
            preparedStatement.setDate(6, java.sql.Date.valueOf(produto.getDataCompra()));
            preparedStatement.setString(7, produto.getFornecedor());
            preparedStatement.setDouble(8, produto.getQuantidade());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean atualizarProduto(Produto produto){
        String salvar = "UPDATE produto SET descricao = ?, dataVencimento = ?, "
                +  " unidade_medida = ?, valorUnitario = ?, lote = ?, dataCompra = ?,"
                + " fornecedor = ?, quantidade = ?"
                 + " WHERE idProduto = " + produto.getIdProduto();
        
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(salvar);
            preparedStatement.setString(1, produto.getDescricao());
            preparedStatement.setDate(2, java.sql.Date.valueOf(produto.getDataVencimento()));
            preparedStatement.setString(3, produto.getUnidade_medida());
            preparedStatement.setBigDecimal(4, produto.getValorUnitario());
            preparedStatement.setString(5, produto.getLote());
            preparedStatement.setDate(6, java.sql.Date.valueOf(produto.getDataCompra()));
            preparedStatement.setString(7, produto.getFornecedor());
            preparedStatement.setDouble(8, produto.getQuantidade());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deletarProduto(Produto produto){
        String sql = "DELETE FROM produto WHERE idProduto = " + produto.getIdProduto();
        
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean atualizarEstoqueProduto(int idProduto, Double valor){
        String salvar = "UPDATE produto SET quantidade = ?"
                 + " WHERE idProduto = " + idProduto;
        
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(salvar);
            preparedStatement.setDouble(1, valor);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
