/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes_academia;

/**
 *
 * @author Artur
 */
public class ParametroUsuario {
    private static Usuario usuario;

    public ParametroUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ParametroUsuario() {
    }

    public  Usuario getUsuario() {
        return this.usuario;
    }

    public  void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
