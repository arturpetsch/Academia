/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia_DAO;

import classes_academia.Mensalidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Artur
 */
public class SaldoDAO {
    private Connection connection = null;
    
    public void criarSaldo(){
        String sql = "SELECT idSaldo FROM saldo WHERE idSaldo = 1";
        
        ResultSet resultSet;
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            if(resultSet.next()){
                
            }else{
                iniciarSaldo();
            }
        } catch (Exception e) {
        }
    }
    
    private void iniciarSaldo(){
        String sql = "INSERT INTO saldo (valorSaldo)" + " VALUES(?)";
        
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, 0.00);
             preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void SomarSaldo(double valor){
        String sql = "UPDATE saldo SET valorSaldo = (" + valor + "+ valorSaldo) WHERE idSaldo = 1";
        
         try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    public void subtrairSaldo(double valor){
        String sql = "UPDATE saldo SET valorSaldo = (valorSaldo - " + valor + ") WHERE idSaldo = 1";
        
         try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    public double getSaldo(){
        String sql = "SELECT valorSaldo FROM saldo WHERE idSaldo = 1";
        double saldo = 0;
        ResultSet resultSet;
        try{
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
             resultSet = preparedStatement.executeQuery(sql);
            
            if(resultSet.next()){
               saldo = (resultSet.getDouble("valorSaldo"));
            }
            return saldo;
        }catch(Exception e){
            e.printStackTrace();
        }
        return saldo;
    }
}
