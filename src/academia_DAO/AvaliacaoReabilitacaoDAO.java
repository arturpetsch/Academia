/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia_DAO;

import classes_academia.AvaliacaoReabilitacao;
import classes_academia.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Artur
 */
public class AvaliacaoReabilitacaoDAO {
    
    private Connection connection = null;
    
    public ArrayList<AvaliacaoReabilitacao> consultarDadosClienteEmAvaliacao(Cliente cliente){
         
        String busca = "SELECT * FROM avaliacaoreabilitacao WHERE id_cliente_aval_reab = " 
                + cliente.getId() + " ORDER BY id_cliente_aval_reab DESC LIMIT 1";
        
        ResultSet resultSet;
        AvaliacaoReabilitacao avaliacaoReabilitacao = new AvaliacaoReabilitacao();
        ArrayList<AvaliacaoReabilitacao> avaliacao = new ArrayList<>();
        
        try {
            
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            resultSet = preparedStatement.executeQuery(busca);
            
            if(resultSet.next()){
               // avaliacaoReabilitacao.setPeso(resultSet.getDouble("peso"));
                //avaliacaoReabilitacao.setAltura(resultSet.getDouble("altura"));
                avaliacaoReabilitacao.setDescricao(resultSet.getString("descricao"));
                avaliacaoReabilitacao.setMedicamentos(resultSet.getString("medicamentos"));
                avaliacaoReabilitacao.setTratamento_anterior(resultSet.getString("tratamento_anterior"));
                avaliacaoReabilitacao.setExercicios(resultSet.getString("exercicios"));
                avaliacaoReabilitacao.setIdade(resultSet.getInt("idade"));
                avaliacaoReabilitacao.setCliente(cliente);
                //avaliacaoReabilitacao.setIdAvaliacaoFisica(resultSet.getInt("idAvaliacaoFisica"));
                avaliacao.add(avaliacaoReabilitacao);
            }
            return avaliacao;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<AvaliacaoReabilitacao> consultarAvaliacaoPorCliente(Cliente cliente){
        String busca = "SELECT * FROM avaliacaoreabilitacao WHERE id_cliente_aval_reab = " + cliente.getId();
        
        ResultSet resultSet;
        ArrayList<AvaliacaoReabilitacao> avaliacoes = new ArrayList<AvaliacaoReabilitacao>();
        
        try {
            
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            resultSet = preparedStatement.executeQuery(busca);
            
            while(resultSet.next()){
                AvaliacaoReabilitacao avaliacaoReabilitacao = new AvaliacaoReabilitacao();
                avaliacaoReabilitacao.setIdAvaliacaoReabilitacao(resultSet.getInt("idAvaliacaoReabilitacao"));
                avaliacaoReabilitacao.setCliente(cliente);
                avaliacaoReabilitacao.setData_hora(resultSet.getDate("data").toLocalDate());
                avaliacaoReabilitacao.setIdade(resultSet.getInt("idade"));
                avaliacaoReabilitacao.setDescricao(resultSet.getString("descricao"));
                avaliacaoReabilitacao.setMedicamentos(resultSet.getString("medicamentos"));
                avaliacaoReabilitacao.setTratamento_anterior(resultSet.getString("tratamento_anterior"));
                avaliacaoReabilitacao.setExercicios(resultSet.getString("exercicios"));
                
                
                avaliacoes.add(avaliacaoReabilitacao);
            
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return avaliacoes;
    }
    
    public boolean salvarAvaliacaoFisica(AvaliacaoReabilitacao avaliacao){
        String salvar = "INSERT INTO avaliacaoreabilitacao(id_cliente_aval_reab, idade, descricao, tratamento_anterior"
                + ", data, exercicios, medicamentos)" + "VALUES(?,?,?,?,?,?,?)";   
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(salvar);
            preparedStatement.setInt(1, avaliacao.getCliente().getId());
            preparedStatement.setInt(2, avaliacao.getIdade());
            preparedStatement.setString(3, avaliacao.getDescricao());
            preparedStatement.setString(4, avaliacao.getTratamento_anterior());
            preparedStatement.setDate(5, java.sql.Date.valueOf(avaliacao.getData_hora()));
            preparedStatement.setString(6, avaliacao.getExercicios());
            preparedStatement.setString(7, avaliacao.getMedicamentos());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean atualizarAvaliacaoFisica(AvaliacaoReabilitacao avaliacao){
        String salvar = "UPDATE avaliacaoreabilitacao SET descricao = ?, tratamento_anterior = ?, "
                +  " exercicios = ?, medicamentos = ?"
                 + " WHERE idAvaliacaoReabilitacao =" + avaliacao.getIdAvaliacaoReabilitacao();
        
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(salvar);
            preparedStatement.setString(1, avaliacao.getDescricao());
            preparedStatement.setString(2, avaliacao.getTratamento_anterior());
            preparedStatement.setString(3, avaliacao.getExercicios());
            preparedStatement.setString(4, avaliacao.getMedicamentos());
            
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    public boolean deletarAvaliacaoReabilitacao(AvaliacaoReabilitacao avaliacao){
        String sql = "DELETE FROM avaliacaoreabilitacao WHERE idAvaliacaoReabilitacao = " + avaliacao.getIdAvaliacaoReabilitacao();
        
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
}

