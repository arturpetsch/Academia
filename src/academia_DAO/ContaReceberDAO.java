/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia_DAO;

import classes_academia.Cliente;
import classes_academia.ContaPagar;
import classes_academia.ContaReceber;
import classes_academia.Mensalidade;
import classes_academia.ParametroUsuario;
import classes_academia.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Artur
 */
public class ContaReceberDAO {
    private Connection connection = null;
    
    private int pegarIdUsuario(){
        ParametroUsuario parametroUsuario = new ParametroUsuario();
        
        Usuario usuario = parametroUsuario.getUsuario();
        if(usuario != null){ 
            if(usuario.getId() > 0){
                return usuario.getId();
        }
        }
        return 1;
    }
    
    public boolean inserirNovaMensalidade(ContaReceber contaReceber){
        String sql = "INSERT INTO contas_receber( id_cliente_conta_receber, valor, data_vencimento, tipo_conta, id_usuario_conta_receber, descricao, id_mensalidade_conta_receber)" + "VALUES(?,?,?,?,?,?,?)";
       
        int idUser = pegarIdUsuario();
        try {
            if(!validarContaReceberNoMesEAno(contaReceber.getDataVencimento(), contaReceber.getCliente().getId())){
                connection = Conexao.conexao();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, contaReceber.getCliente().getId());
                preparedStatement.setDouble(2, contaReceber.getValor());
                preparedStatement.setDate(3, java.sql.Date.valueOf(contaReceber.getDataVencimento()));
                preparedStatement.setBoolean(4, contaReceber.isTipoConta());
           
          /* if(contaReceber.getUsuario() != null){
                preparedStatement.setInt(6, contaReceber.getUsuario().getId());
           } */ 
        
                preparedStatement.setInt(5, idUser);
                preparedStatement.setString(6, contaReceber.getDescricao());
                preparedStatement.setInt(7, contaReceber.getMensalidade().getIdMensalidade());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }else{
                return false;
        }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean alterarMensalidade(ContaReceber contaReceber){
        Date dataBaixa =  java.sql.Date.valueOf(contaReceber.getDataBaixa());
        Date dataVencimento = java.sql.Date.valueOf(contaReceber.getDataVencimento());
        System.err.println(dataBaixa);
        
        int idUser = pegarIdUsuario();
        
        //if(validarAlteracaoMensalidade(contaReceber)){
        String sql = "UPDATE contas_receber SET data_baixa = '" + dataBaixa + "', valor = "+ contaReceber.getValor() 
                + ", data_vencimento = '" + dataVencimento
                + "', id_usuario_conta_receber = "+idUser+" WHERE idContas_receber = " + contaReceber.getId() + "";
        System.err.println(sql);
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
            SaldoDAO saldoDAO = new SaldoDAO();
            saldoDAO.SomarSaldo(contaReceber.getValor());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //}
        //return false;
    }
    
    
    private boolean validarAlteracaoMensalidade(ContaReceber contaReceber){
        String sql = "SELECT data_baixa FROM contas_receber WHERE idContas_receber = " + contaReceber.getId() + " AND data_baixa IS NULL";
        
        ResultSet resultSet;
        try{
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            
            if(resultSet.next()){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    
    public boolean validarContaReceber(LocalDate dataVenc, int idCliente){
        String sql = "SELECT * FROM contas_receber WHERE MONTH(data_vencimento) = '" + dataVenc.getMonth().getValue() + "' AND id_cliente_conta_receber = " + idCliente;
        
        ResultSet resultSet;
        try{
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            
            if(resultSet.next()){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Método que valida se não existe outra mensalidade daquele cliente no mesmo mês.
     */
    public boolean validarContaReceberNoMes(int mes, int idCliente){
        String sql = "SELECT * FROM contas_receber WHERE MONTH(data_vencimento) = " + mes + " AND id_cliente_conta_receber = " + idCliente;
     
        ResultSet resultSet;
        try{
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            
            if(resultSet.next()){
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    
    /**
     * Método que valida se não existe outra mensalidade daquele cliente no mesmo mês e ano.
     */
    public boolean validarContaReceberNoMesEAno(LocalDate data, int idCliente){
        String sql = "SELECT * FROM contas_receber WHERE MONTH(data_vencimento) = " + data.getMonthValue() + " AND YEAR(data_vencimento) =" + 
                data.getYear() + " AND id_cliente_conta_receber = " + idCliente;
     
        ResultSet resultSet;
        try{
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            
            if(resultSet.next()){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public List<ContaReceber> buscarTodasContasPorCliente(int idCliente){
        
        LocalDate hoje = LocalDate.now();
        LocalDate dataRetroativa = LocalDate.now();
        dataRetroativa = dataRetroativa.minusMonths(12);
        hoje = hoje.plusMonths(2);
        System.err.println("Data hoje " + hoje);
        String sql = "SELECT * FROM contas_receber WHERE id_cliente_conta_receber = " + idCliente + " AND date(data_vencimento) BETWEEN date('" + dataRetroativa + "') AND date('" + hoje + "') ORDER BY data_vencimento DESC";
        
        ResultSet resultSet;
        List<ContaReceber> contasReceber = new ArrayList();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        try {

            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                ContaReceber contaReceber = new ContaReceber();
                MensalidadeDAO mensalidadeDAO = new MensalidadeDAO();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                
                Mensalidade mensalidade = mensalidadeDAO.pegarMensalidadeClienteSelecionado(idCliente);
                Cliente cliente = clienteDAO.buscarClientePeloID(idCliente);
                contaReceber.setId(resultSet.getInt("idContas_Receber"));
                contaReceber.setMensalidade(mensalidade);
                contaReceber.setCliente(cliente);
                Usuario usuario = usuarioDAO.getUsuarioPorID(resultSet.getInt("id_usuario_conta_receber"));
                contaReceber.setUsuario(usuario);
                contaReceber.setValor(((resultSet.getDouble("valor"))));
                contaReceber.setDataVencimento(resultSet.getDate("data_vencimento").toLocalDate()); //aqui esta o erro
                if(resultSet.getDate("data_baixa") != null){
                    contaReceber.setDataBaixa(resultSet.getDate("data_baixa").toLocalDate());
                }
                contaReceber.setTipoConta(new SimpleBooleanProperty((resultSet.getBoolean("tipo_conta"))));
                contaReceber.setDescricao(new SimpleStringProperty(resultSet.getString("descricao")));
                
                contasReceber.add(contaReceber);
            }     
            return contasReceber;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
    }
    
    public ArrayList<ContaReceber> buscarMensalidadesPorAno(int ano){
        String sql = "SELECT * FROM contas_receber WHERE YEAR(data_vencimento) = " 
                + ano + " AND data_baixa IS NOT NULL"; 
        
        ClienteDAO clienteDAO = new ClienteDAO();
        ResultSet resultSet;
        ArrayList<ContaReceber> contasReceber = new ArrayList();

        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                ContaReceber contaReceber = new ContaReceber();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                contaReceber.setId(resultSet.getInt("idContas_Receber"));
                Cliente cliente = clienteDAO.buscarClientePeloID(resultSet.getInt("id_cliente_conta_receber"));
                contaReceber.setCliente(cliente);
                Usuario usuario = usuarioDAO.getUsuarioPorID(resultSet.getInt("id_usuario_conta_receber"));
                contaReceber.setUsuario(usuario);
                contaReceber.setValor(((resultSet.getDouble("valor"))));
                contaReceber.setDataVencimento(resultSet.getDate("data_vencimento").toLocalDate()); //aqui esta o erro
                if(resultSet.getDate("data_baixa") != null){
                    contaReceber.setDataBaixa(resultSet.getDate("data_baixa").toLocalDate());
                }
                contaReceber.setTipoConta(new SimpleBooleanProperty((resultSet.getBoolean("tipo_conta"))));
                contaReceber.setDescricao(new SimpleStringProperty(resultSet.getString("descricao")));
                contasReceber.add(contaReceber);
            }
            return contasReceber;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ContaReceber> buscarMensalidadeAnoMes(int mes, int ano) {
        String sql = "SELECT * FROM contas_receber WHERE MONTH(data_vencimento) = " + mes + " AND YEAR(data_vencimento) = " + ano + " AND MONTH(data_baixa) IS NOT NULL";
        ClienteDAO clienteDAO = new ClienteDAO();
        ResultSet resultSet;
        List<ContaReceber> contasReceber = new ArrayList();

        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                ContaReceber contaReceber = new ContaReceber();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                contaReceber.setId(resultSet.getInt("idContas_Receber"));
                Cliente cliente = clienteDAO.buscarClientePeloID(resultSet.getInt("id_cliente_conta_receber"));
                contaReceber.setCliente(cliente);
                Usuario usuario = usuarioDAO.getUsuarioPorID(resultSet.getInt("id_usuario_conta_receber"));
                contaReceber.setUsuario(usuario);
                contaReceber.setValor(((resultSet.getDouble("valor"))));
                contaReceber.setDataVencimento(resultSet.getDate("data_vencimento").toLocalDate()); //aqui esta o erro
                if(resultSet.getDate("data_baixa") != null){
                    contaReceber.setDataBaixa(resultSet.getDate("data_baixa").toLocalDate());
                }
                contaReceber.setTipoConta(new SimpleBooleanProperty((resultSet.getBoolean("tipo_conta"))));
                contaReceber.setDescricao(new SimpleStringProperty(resultSet.getString("descricao")));
                contasReceber.add(contaReceber);
            }
            return contasReceber;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean deletarContaReceber(ContaReceber contaReceber){
        String sql = "DELETE FROM contas_receber WHERE idContas_Receber = " + contaReceber.getId() + " ";
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
