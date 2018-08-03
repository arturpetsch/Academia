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
public class ContaPagarParametro {
    private static ContaPagar contaPagar;

    public ContaPagarParametro() {
    }

    public ContaPagarParametro(ContaPagar contaPagar){
        this.contaPagar = contaPagar;
    }
    public static ContaPagar getContaPagar() {
        return contaPagar;
    }

    public static void setContaPagar(ContaPagar contaPagar) {
        ContaPagarParametro.contaPagar = contaPagar;
    }
    
    
}
