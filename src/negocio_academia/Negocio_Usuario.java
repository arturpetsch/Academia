/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio_academia;

import academia_DAO.UsuarioDAO;
import classes_academia.Usuario;

/**
 *
 * @author Artur
 */
public class Negocio_Usuario {
    
    public boolean adicionarUsuario(Usuario usuario){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if(usuarioDAO.inserirUsuario(usuario)){
            return true;
        }
        return false;
    }
    
    public boolean verificacaoRespostaSecreta(String respostaSecreta, Usuario usuario){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if(usuarioDAO.verificacaoRespostaSecreta(respostaSecreta, usuario)){
            return true;
        }
        return false;
    }
    
    public boolean verificacaoRespostaSecreta(String respostaSecreta, String nome){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if(usuarioDAO.verificacaoRespostaSecreta(respostaSecreta, nome)){
            return true;
        }
        return false;
    } 
    
    public boolean alterarSenha(String senha, Usuario usuario){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if(usuarioDAO.alterarSenha(senha, usuario)){
            return true;
        }
        return false;
    }
    
    public boolean alterarSenha(String senha, String nome){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if(usuarioDAO.alterarSenha(senha, nome)){
            return true;
        }
        return false;
    }
}
