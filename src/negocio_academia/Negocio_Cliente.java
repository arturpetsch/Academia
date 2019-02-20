/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio_academia;

import academia.Academia;
import academia_DAO.ClienteDAO;
import academia_DAO.ContaReceberDAO;
import academia_DAO.MensalidadeDAO;
import classes_academia.Cliente;
import classes_academia.ContaReceber;
import classes_academia.Mensalidade;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Artur
 */
public class Negocio_Cliente {

    public boolean cadastrarCliente(Cliente cliente, Mensalidade mensalidade) {
        MensalidadeDAO mensalidadeDAO = new MensalidadeDAO();
        ClienteDAO clienteDao = new ClienteDAO();
        ContaReceberDAO contaReceberDAO = new ContaReceberDAO();

        if (cliente != null) {
            if (clienteDao.buscarClientePeloCPF(cliente.getCpf())) {
                clienteDao.cadastrarCliente(cliente);
                mensalidadeDAO.cadastrarMensalidade(mensalidade);
                inserirMensalidadeAVista();
                return true;
            }
        }
        return false;

    }

    public void inserirMensalidadeAVista() {
        ContaReceberDAO contaReceberDAO = new ContaReceberDAO();
        MensalidadeDAO mensalidadeDAO = new MensalidadeDAO();
        ClienteDAO clienteDAO = new ClienteDAO();

        Cliente cliente = clienteDAO.buscarUltimoClienteRetObjeto();
        Mensalidade mensalidade = mensalidadeDAO.pegarUltimaMensalidade();
        ContaReceber contaReceber = new ContaReceber();
        contaReceber.setCliente(cliente);
        contaReceber.setValor((mensalidade.getValor()));
        contaReceber.setTipoConta(new SimpleBooleanProperty(true));
        contaReceber.setDescricao(new SimpleStringProperty("Mensalidade a vista"));
        contaReceber.setDataVencimento(LocalDate.now());
        contaReceber.setMensalidade(mensalidade);
        contaReceberDAO.inserirNovaMensalidade(contaReceber);
    }

    public boolean alterarCliente(Cliente cliente, Mensalidade mensalidade) {
        ClienteDAO clienteDAO = new ClienteDAO();
        MensalidadeDAO mensalidadeDAO = new MensalidadeDAO();

        if (cliente != null && cliente.getId() > 0) {
            if (clienteDAO.buscarClientePeloCPFparaAlterar(cliente.getCpf())) {
                clienteDAO.alterarCliente(cliente);
                mensalidadeDAO.alterarMensalidade(mensalidade);
                return true;
            }
        }
        return false;
    }

    public boolean deletarCliente(Cliente cliente) {
        ClienteDAO clienteDAO = new ClienteDAO();
        if (cliente != null && cliente.getId() != 0) {
            clienteDAO.removerCliente(cliente);
            return true;

        } else {
            return false;
        }
    }

    public boolean alterarStatusCliente(Cliente cliente) {
        ClienteDAO clienteDAO = new ClienteDAO();
        if (cliente != null) {
            if (cliente.getStatus()) {
                clienteDAO.alterarStatusCliente(false, cliente.getId());
            } else {
                clienteDAO.alterarStatusCliente(true, cliente.getId());
                Academia academia = new Academia();
                academia.gerarMensalidades();
                inserirMensalidadeClienteAlterado(cliente);
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Metodo que gera uma mensalidade a vista para clientes que estavam
     * inativos.
     *
     * @param cliente
     */
    public void inserirMensalidadeClienteAlterado(Cliente cliente) {
        ContaReceberDAO contaReceberDAO = new ContaReceberDAO();
        Mensalidade mensalidade;
        MensalidadeDAO mensalidadeDAO = new MensalidadeDAO();
        mensalidade = mensalidadeDAO.pegarMensalidadeClienteSelecionado(cliente.getId());
        
        if (contaReceberDAO.validarContaReceberNoMes(LocalDate.now().getMonth().getValue(), cliente.getId())) {
            ContaReceber contaReceber = new ContaReceber();
            contaReceber.setCliente(cliente);
            contaReceber.setValor((mensalidade.getValor()));
            contaReceber.setTipoConta(new SimpleBooleanProperty(true));
            contaReceber.setDescricao(new SimpleStringProperty("Mensalidade a vista"));
            contaReceber.setDataVencimento(LocalDate.now());
            contaReceber.setMensalidade(mensalidade);
            contaReceberDAO.inserirNovaMensalidade(contaReceber);
        }
    }

    public ArrayList<Cliente> buscarCliente(String nomeCliente) {
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes = clienteDAO.buscarCliente(nomeCliente);
        if (clientes != null) {
            return clientes;
        } else {
            return null;
        }
    }

    public List<Cliente> getClientesAtivos() {
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = new ArrayList();
        clientes = clienteDAO.getClientesAtivos();
        if (clientes != null) {
            return clientes;
        } else {
            return null;
        }
    }

    public List<Mensalidade> getMensalidadeHoje() {
        MensalidadeDAO mensalidadeDAO = new MensalidadeDAO();
        List<Mensalidade> mensalidades = new ArrayList();

        mensalidades = mensalidadeDAO.pegarTodasMensalidadesClientesAtivos();
        if (mensalidades != null) {
            return mensalidades;
        } else {
            System.err.println("Nenhuma mensalidade encontrada!");
            return null;
        }
    }

    public List<Cliente> buscarHorariosClientes() {
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.getClientesAtivos();
        if (clientes != null) {
            return clientes;
        } else {
            return null;
        }
    }
}
