/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia_DAO;

import classes_academia.ParametroUsuario;
import classes_academia.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Artur
 */
public class UsuarioDAO {

    private Connection connection = null;

    public boolean inserirUsuario(Usuario usuario) {

        // if (validarUsuario(usuario.getNome())) {
        String sql = "INSERT INTO usuario (nome, senha, resposta_secreta, tipo)" + " VALUES(?,?,?,?)";

        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getRespostaSecreta());
            preparedStatement.setString(4, usuario.getTipoUser());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //}
        //return false;
    }

    private boolean validarUsuario(String nomeUser) {
        String sql = "SELECT * FROM usuario WHERE nome = '" + nomeUser + "'";
        System.err.println(sql);
        ResultSet resultSet;

        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);

            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean verificarLogin(String nomeUser, String senhaUser) {
        String sql = "SELECT * FROM usuario WHERE nome = '" + nomeUser + "' AND senha = '" + senhaUser + "'";

        ResultSet resultSet;
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);

            if (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getInt("idUsuario"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setRespostaSecreta(resultSet.getString("resposta_secreta"));
                
                ParametroUsuario parametroUsuario = new ParametroUsuario(usuario);
                preparedStatement.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Usuario getUsuarioPorNome(String nome) {
        String sql = "SELECT nome, tipo, idUsuario, senha FROM usuario WHERE nome = '" + nome + "'";

        ResultSet resultSet;

        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            Usuario usuario = new Usuario();

            if (resultSet.next()) {

                usuario.setNome(resultSet.getString("nome"));
                usuario.setId(resultSet.getInt("idUsuario"));
                usuario.setTipoUser(resultSet.getString("tipo"));
                usuario.setSenha(resultSet.getString("senha"));
            }
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Usuario getUsuarioPorID(int idUser) {
        String sql = "SELECT nome, tipo, idUsuario, senha FROM usuario WHERE idUsuario = " + idUser + "";

        ResultSet resultSet;
        Usuario usuario = new Usuario();

        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            
            if (resultSet.next()) {

                usuario.setNome(resultSet.getString("nome"));
                usuario.setId(idUser);
                usuario.setTipoUser(resultSet.getString("tipo"));
                usuario.setSenha(resultSet.getString("senha"));
            }
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    public boolean verificacaoRespostaSecreta(String resposta, Usuario usuario) {
        String sql = "SELECT * FROM usuario WHERE idUsuario = " + usuario.getId() + " AND nome = '" + usuario.getNome() + "' AND resposta_secreta = '" + resposta + "'";
        ResultSet resultSet;
     
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);

            if (resultSet.next()) {

                return true;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    
    public boolean verificacaoRespostaSecreta(String resposta, String nome) { //parametro como nome;
        Usuario usuario = getUsuarioPorNome(nome);
        if(verificacaoRespostaSecreta(resposta, usuario)){
            return true;
        }
        return false;
    }
    
    
    public boolean alterarSenha(String senha, Usuario usuario){
        String sql = "UPDATE Usuario SET senha = '" + senha + "' WHERE idUsuario = " + usuario.getId() + " AND nome = '" + usuario.getNome() + "'; ";
        System.err.println(sql);
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }
    
    public boolean alterarSenha(String senha, String nome){
        Usuario usuario = getUsuarioPorNome(nome);
        if(alterarSenha(senha, usuario)){
            return true;
        }
        return false;
    }
}
