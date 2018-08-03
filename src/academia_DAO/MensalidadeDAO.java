/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia_DAO;

import classes_academia.Cliente;
import classes_academia.Mensalidade;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artur
 */
public class MensalidadeDAO {
    private Connection connection = null;

    public boolean cadastrarMensalidade(Mensalidade mensalidade){
        ClienteDAO clienteDAO = new ClienteDAO();
        
        int idCliente = clienteDAO.buscarUltimoCliente();
        String sql = "INSERT INTO Mensalidade(diaVencimento, valor, idCliente)" + "VALUES(?,?,?)";
        
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, mensalidade.getDiaVencimento());
            preparedStatement.setDouble(2, (mensalidade.getValor()));
            preparedStatement.setInt(3, idCliente);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Mensalidade pegarMensalidadeClienteSelecionado(int idCliente){
        String sql = "SELECT * FROM Mensalidade WHERE idCliente = " + idCliente + " ";
        
        ResultSet resultSet;
        try{
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            
            if(resultSet.next()){
                Mensalidade mensalidade = new Mensalidade();
                mensalidade.setIdMensalidade(resultSet.getInt("idMensalidade"));
                mensalidade.setDiaVencimento(resultSet.getInt("diaVencimento"));
                mensalidade.setValor((resultSet.getDouble("valor")));
                
                return mensalidade;
            }
        }catch(Exception e){
            e.printStackTrace();
            
        }
        return null;
    }
    
    public void alterarMensalidade(Mensalidade mensalidade){
        String sql = "UPDATE Mensalidade SET valor = "+mensalidade.getValor() + ", diaVencimento = "+mensalidade.getDiaVencimento()+" WHERE idMensalidade = " +mensalidade.getIdMensalidade() + " AND idCliente = " + mensalidade.getCliente().getId() + "";
    
        System.err.println(sql);
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    public List<Mensalidade> pegarTodasMensalidadesClientesAtivos(){
        int dia = LocalDate.now().getDayOfMonth();
        List<Mensalidade> mensalidades = new ArrayList();
        String sql = "SELECT * FROM Mensalidade m INNER JOIN Cliente c ON m.idCliente = c.idCliente WHERE c.status = 1";
        System.err.println(sql);
        ResultSet resultSet;
        try{
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            
            while(resultSet.next()){
                Mensalidade mensalidade = new Mensalidade();
                mensalidade.setIdMensalidade(resultSet.getInt("idMensalidade"));
                mensalidade.setDiaVencimento(resultSet.getInt("diaVencimento"));
                mensalidade.setValor(resultSet.getDouble("valor"));
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt("idCliente"));
                mensalidade.setCliente(cliente);
                mensalidades.add(mensalidade);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return mensalidades;
    }
    
    public Mensalidade pegarUltimaMensalidade(){
        String sql = "SELECT * FROM Mensalidade WHERE idMensalidade = (SELECT MAX(idMensalidade) FROM Mensalidade)";
        
        ResultSet resultSet;
        try{
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            Mensalidade mensalidade = new Mensalidade();
            if(resultSet.next()){
                
                mensalidade.setIdMensalidade(resultSet.getInt("idMensalidade"));
                mensalidade.setDiaVencimento(resultSet.getInt("diaVencimento"));
                mensalidade.setValor(resultSet.getDouble("valor"));
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt("idCliente"));
                mensalidade.setCliente(cliente);
                
            }
            return mensalidade;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
