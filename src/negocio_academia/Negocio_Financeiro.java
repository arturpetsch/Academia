/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio_academia;

import academia_DAO.ContaPagarDAO;
import academia_DAO.ContaReceberDAO;
import academia_DAO.SaldoDAO;
import classes_academia.Cliente;
import classes_academia.ContaPagar;
import classes_academia.ContaReceber;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artur
 */
public class Negocio_Financeiro {
    
    public boolean inserirNovaConta(ContaReceber contaReceber){
        ContaReceberDAO contaReceberDAO = new ContaReceberDAO();
        if(contaReceberDAO.inserirNovaMensalidade(contaReceber)){
            return true;
        }
        return false;
    }
    
    public boolean alterarMensalidade(ContaReceber contaReceber){
        ContaReceberDAO contaReceberDAO = new ContaReceberDAO();
        if(contaReceberDAO.alterarMensalidade(contaReceber)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean validarMensalidadeCliente(LocalDate dataVenc, Cliente cliente){
        ContaReceberDAO contaReceberDAO = new ContaReceberDAO();
        if(contaReceberDAO.validarContaReceber(dataVenc, cliente.getId())){
            return true;
        }else{
            return false;
        }
    }
    
    public List<ContaPagar> pegarContasPagarMesAno(int mesSelecionado, int anoSelecionado){
        ContaPagarDAO contaPagarDAO = new ContaPagarDAO();
        List<ContaPagar> contasPagar = new ArrayList<>();
        
        contasPagar = contaPagarDAO.pegarContasPagarMesAno(mesSelecionado, anoSelecionado);
        if(contasPagar != null){
            return contasPagar;
        }
        return null;
    }
    
    public void inserirNovaContaPagar(ContaPagar contaPagar){
        ContaPagarDAO contaPagarDAO = new ContaPagarDAO();
        int i = 1;
        while(contaPagar.getParcela() >= i){
            contaPagarDAO.inserirNovaConta(contaPagar, i);
            contaPagar.setDataVencimento(contaPagar.getDataVencimento().plusMonths(1));
            i++;    
            }
    }
    
    public boolean alterarContaPagar(ContaPagar contaPagar){
        ContaPagarDAO contaPagarDAO = new ContaPagarDAO();
       if(contaPagarDAO.alterarContaPagar(contaPagar)){
           return true;
       }
       return false;
    }
    
     public boolean deletarContaPagar(ContaPagar contaPagar){
        ContaPagarDAO contaPagarDAO = new ContaPagarDAO();
       if(contaPagarDAO.deletarContaPagar(contaPagar)){
           return true;
       }
       return false;
    }
     
    public List<ContaReceber> buscarContasReceberMesAno(int mes, int ano){
        ContaReceberDAO contaReceberDAO = new ContaReceberDAO();
        List<ContaReceber> contasReceber = new ArrayList();
        
        contasReceber = contaReceberDAO.buscarMensalidadeAnoMes(mes, ano);
        
        if(contasReceber != null){
            return contasReceber;
        }
        
        return null;
    }
    
    public double getSaldo(){
        SaldoDAO saldoDAO = new SaldoDAO();
        return saldoDAO.getSaldo();
    }
}
