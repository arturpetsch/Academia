/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia_DAO;

import classes_academia.ContaPagar;
import classes_academia.ContaReceber;
import classes_academia.ParametroUsuario;
import classes_academia.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artur
 */
public class ContaPagarDAO {

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
    
    public ArrayList<ContaPagar> buscarContasAPagarPorAno(int ano){
        String sql = "SELECT * FROM conta_pagar WHERE YEAR(data_vencimento) = " + ano + " AND data_baixa IS NOT NULL";
        ResultSet resultSet;
        ArrayList<ContaPagar> contasPagar = new ArrayList();

        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                ContaPagar contaPagar = new ContaPagar();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                contaPagar.setIdContaPagar(resultSet.getInt("idConta_Pagar"));
                contaPagar.setDescricao(resultSet.getString("descricao"));
                Double valor = resultSet.getDouble("valor");
                contaPagar.setValor(String.valueOf(valor));
                 Usuario usuario = usuarioDAO.getUsuarioPorID(resultSet.getInt("id_usuario"));
                contaPagar.setUsuario(usuario);
                contaPagar.setDataVencimento(resultSet.getDate("data_vencimento").toLocalDate());

                if (resultSet.getDate("data_baixa") != null) {
                    contaPagar.setDataPagamento(resultSet.getDate("data_baixa").toLocalDate());
                }
                contaPagar.setParcela(resultSet.getInt("parcela"));
                contaPagar.setFornecedor(resultSet.getString("fornecedor"));
                contaPagar.setTipoConta(resultSet.getBoolean("tipo_conta"));
                contasPagar.add(contaPagar);
            }
            return contasPagar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ContaPagar> pegarContasPagarMesAno(int mes, int ano) {
        String sql = "SELECT * FROM conta_pagar WHERE MONTH(data_vencimento) = " + mes + " AND YEAR(data_vencimento) = " + ano;

        ResultSet resultSet;
        List<ContaPagar> contasPagar = new ArrayList();

        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                ContaPagar contaPagar = new ContaPagar();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                contaPagar.setIdContaPagar(resultSet.getInt("idConta_Pagar"));
                contaPagar.setDescricao(resultSet.getString("descricao"));
                Double valor = resultSet.getDouble("valor");
                contaPagar.setValor(String.valueOf(valor));
                 Usuario usuario = usuarioDAO.getUsuarioPorID(resultSet.getInt("id_usuario"));
                contaPagar.setUsuario(usuario);
                contaPagar.setDataVencimento(resultSet.getDate("data_vencimento").toLocalDate());

                if (resultSet.getDate("data_baixa") != null) {
                    contaPagar.setDataPagamento(resultSet.getDate("data_baixa").toLocalDate());
                }
                contaPagar.setParcela(resultSet.getInt("parcela"));
                contaPagar.setFornecedor(resultSet.getString("fornecedor"));
                contaPagar.setTipoConta(resultSet.getBoolean("tipo_conta"));
                contasPagar.add(contaPagar);
            }
            return contasPagar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void inserirNovaConta(ContaPagar contaPagar, int parcela) {
        String sql = "INSERT INTO conta_pagar(descricao, valor, data_vencimento, data_baixa, parcela, fornecedor, tipo_conta, id_usuario) " + " VALUES(?,?,?,?,?,?,?,?)";

        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, contaPagar.getDescricao());
            
            //DecimalFormat formato = new DecimalFormat("#.##");      
            //Double valor = Double.valueOf(formato.format(contaPagar.getValor()));
            Double valor = Double.valueOf(contaPagar.getValor());
            preparedStatement.setDouble(2, valor);
            preparedStatement.setDate(3, java.sql.Date.valueOf(contaPagar.getDataVencimento()));
            if (contaPagar.getDataPagamento() != null) {
                preparedStatement.setDate(4, java.sql.Date.valueOf(contaPagar.getDataPagamento()));
            } else {
                preparedStatement.setDate(4, null);
            }
            int i =pegarIdUsuario();
            preparedStatement.setInt(5, parcela);
            preparedStatement.setString(6, contaPagar.getFornecedor());
            preparedStatement.setBoolean(7, false);
            preparedStatement.setInt(8, i);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean alterarContaPagar(ContaPagar contaPagar) {
        int idUser = pegarIdUsuario();
        Double valor = Double.parseDouble(contaPagar.getValor());
       // if (validarAlteracaoContaPagar(contaPagar)) {
            String sql = "UPDATE conta_pagar SET data_baixa = '" + contaPagar.getDataPagamento() + "', valor = " + valor + ", data_vencimento = '" 
                    + contaPagar.getDataVencimento() + "', id_usuario = '" + idUser
                    +  "', fornecedor = '" + contaPagar.getFornecedor() + "', descricao = '" + contaPagar.getDescricao() + "'"
                    + "  WHERE idConta_Pagar = " + contaPagar.getIdContaPagar() + " ";

            
            try {
                System.err.println(valor + "NO ALTERAR CONTA");
                connection = Conexao.conexao();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.execute();
                preparedStatement.close();
                 SaldoDAO saldoDAO = new SaldoDAO();
                //saldoDAO.subtrairSaldo(contaPagar.getValor());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        //}
        //return false;
    }

    protected boolean validarAlteracaoContaPagar(ContaPagar contaPagar){
        String sql = "SELECT data_baixa FROM conta_pagar WHERE idConta_Pagar = " + contaPagar.getIdContaPagar()+ " AND data_baixa IS NULL";
        
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
    
    public boolean deletarContaPagar(ContaPagar contaPagar){
        String sql = "DELETE FROM conta_pagar WHERE idConta_Pagar = " + contaPagar.getIdContaPagar() + " ";
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
    
    public ArrayList<ContaPagar> buscarContasAPagarVencidasEAVencer() {
        LocalDate dataInicial = LocalDate.now().plusDays(15);
        LocalDate dataFinal = LocalDate.now().minusDays(30);
         String sql = "SELECT * "
                + "FROM conta_pagar WHERE date(data_vencimento) BETWEEN date('" + dataFinal + "') AND date('" 
                 + dataInicial + "') AND data_baixa IS NULL ORDER BY data_vencimento DESC";
         ResultSet resultSet;
        ArrayList<ContaPagar> contasPagar = new ArrayList();

        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                ContaPagar contaPagar = new ContaPagar();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                contaPagar.setIdContaPagar(resultSet.getInt("idConta_Pagar"));
                contaPagar.setDescricao(resultSet.getString("descricao"));
                Double valor = resultSet.getDouble("valor");
                contaPagar.setValor(String.valueOf(valor));
                 Usuario usuario = usuarioDAO.getUsuarioPorID(resultSet.getInt("id_usuario"));
                contaPagar.setUsuario(usuario);
                contaPagar.setDataVencimento(resultSet.getDate("data_vencimento").toLocalDate());

                if (resultSet.getDate("data_baixa") != null) {
                    contaPagar.setDataPagamento(resultSet.getDate("data_baixa").toLocalDate());
                }
                contaPagar.setParcela(resultSet.getInt("parcela"));
                contaPagar.setFornecedor(resultSet.getString("fornecedor"));
                contaPagar.setTipoConta(resultSet.getBoolean("tipo_conta"));
                contasPagar.add(contaPagar);
            }
            return contasPagar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }  
    }
}
