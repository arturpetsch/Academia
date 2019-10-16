/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia_DAO;

import classes_academia.Cliente;
import classes_academia.Produto;
import classes_academia.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Artur
 */
public class VendaDAO {
    private Connection connection = null;
    
    public boolean salvarVenda(Venda venda){
        String sqlVenda = "INSERT INTO venda(dataVenda, valor_total, diaVencimento, pagamentoAVista, pagamentoParcelado, numeroParcelas)" +
                "VALUES(?,?,?,?,?,?)";
        
        
        try {
            connection = Conexao.conexao();
            //connection.setAutoCommit(false);

            PreparedStatement preparedStatementVenda = connection.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
            preparedStatementVenda.setDate(1, java.sql.Date.valueOf(venda.getDataVenda()));
            preparedStatementVenda.setBigDecimal(2, venda.getValor_total());
            preparedStatementVenda.setInt(3, venda.getDiaVencimento());
            
            if(venda.getPagamentoAVista()){
                preparedStatementVenda.setBoolean(4, venda.getPagamentoAVista());
                preparedStatementVenda.setBoolean(5, false);
            }else{
                preparedStatementVenda.setBoolean(4, false);
                preparedStatementVenda.setBoolean(5, venda.getPagamentoParcelado());
            }
            
            preparedStatementVenda.setInt(6, venda.getNumeroParcelas());
            
            preparedStatementVenda.executeUpdate();
            
            final ResultSet rsCte = preparedStatementVenda.getGeneratedKeys();
            int idVenda = 0;
            if (rsCte.next()) {
                idVenda = (rsCte.getInt(1));
            }
            
            int i = 0;
            while(venda.getProdutos().size() > i){
                salvarProdutos(venda, idVenda, venda.getProdutos().get(i));
                i++;
            }
            
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean atualizarVenda(Venda venda){
        return true;
    }
    
    private void salvarProdutos(Venda venda, int idVenda, Produto produto){
        String sqlVendaProduto = "INSERT INTO vendaproduto(valor_unitario, id_cliente, id_produto, id_venda)"
                + "VALUES(?,?,?,?)";
        
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlVendaProduto);
            preparedStatement.setBigDecimal(1, produto.getValorUnitario());
            preparedStatement.setInt(2, venda.getCliente().getId());
            preparedStatement.setInt(3, produto.getIdProduto());
            preparedStatement.setInt(4, idVenda);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Venda> buscarVendasPorCliente(Cliente cliente){
        String sql = "SELECT * FROM venda WHERE id_cliente = " + cliente.getId();
        
        ResultSet resultSet;
        ArrayList<Venda> vendas = new ArrayList<Venda>();
        
        try {

            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                Venda venda = new Venda();
                venda.setIdVenda(resultSet.getInt("idVenda"));
                venda.setDataVenda(resultSet.getDate("dataVenda").toLocalDate());
                venda.setValor_total(resultSet.getBigDecimal("valor_total"));
                venda.setDiaVencimento(resultSet.getInt("diaVencimento"));
                
                if(resultSet.getBoolean("pagamentoAVista")){
                    venda.setPagamentoAVista(true);
                }else{
                    venda.setPagamentoParcelado(Boolean.TRUE);
                }
                
                venda.setNumeroParcelas(resultSet.getInt("numeroParcelas"));
                
                venda.setProdutos(buscarProdutosPorVenda(venda.getIdVenda()));
               
                vendas.add(venda);
            }
            return vendas;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    private ArrayList<Produto> buscarProdutosPorVenda(int idVenda){
        String sql = "SELECT * from vendaproduto WHERE id_venda = " + idVenda;
        
        ResultSet resultSet;
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        
        try {

            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                    
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}