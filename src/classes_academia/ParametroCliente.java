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
public class ParametroCliente {

    private  static Cliente cliente;
    public ParametroCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ParametroCliente() {
        
    }
    
    
    public Cliente getClienteParametro(){
        return this.cliente;
    }
    
    public void setClienteParametro(Cliente cliente){
        this.cliente = cliente;
    }
}
