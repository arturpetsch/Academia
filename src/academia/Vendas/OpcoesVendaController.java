/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Vendas;

import academia.Avaliacao_Fisica.TabelaClientesAvaliacaoController;
import academia.BarraDeProgressoController;
import academia_DAO.ClienteDAO;
import academia_DAO.VendaDAO;
import classes_academia.Cliente;
import classes_academia.Venda;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class OpcoesVendaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button novaVenda;
    
    @FXML
    private Button consultarVenda;
    
    @FXML
    private TextField nomeCliente;
    
    @FXML
    private Button buscaCliente;
    
    @FXML
    private TableView tabelaVendas;
    
    @FXML
    private TableColumn<Venda, Integer> cod;
    
    @FXML
    private TableColumn<Venda, LocalDate> data;
    
    @FXML
    private TableColumn<Venda, BigDecimal> valor;
    
    @FXML
    public AnchorPane idPainelDireitoCliente;
    
    @FXML
    private Label labelTexto;
    
    private Venda venda = new Venda();
    
    private Cliente cliente = new Cliente();
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labelTexto.setVisible(false);
        nomeCliente.setVisible(false);
        buscaCliente.setVisible(false);
        tabelaVendas.setVisible(false);
    }    
    
    @FXML
    private void novaVenda(ActionEvent action) throws IOException{
        Stage stage = (Stage) nomeCliente.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void consultarVenda(ActionEvent action){
        labelTexto.setVisible(true);
        nomeCliente.setVisible(true);
        buscaCliente.setVisible(true);
        tabelaVendas.setVisible(true);
    }
    
    public Venda getVenda(){
        return venda;
    }
    
        @FXML
    private void buscarCliente(ActionEvent action) {
        
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        String nomeInformado = nomeCliente.getText();
        clientes = clienteDAO.buscarCliente(nomeInformado);

        try {
            mostrarClientes(clientes);
        } catch (IOException ex) {
            Logger.getLogger(VendasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que mostra a tabela com todos os clientes. Apos selecionado o
     * cliente, é mostrado a tabela de datas de avaliações já realizadas.
     *
     * @param clientes
     */
    private void mostrarClientes(ArrayList<Cliente> clientes) throws IOException {
        if (!clientes.isEmpty()) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/academia/Avaliacao_Fisica/tabelaClientesAvaliacao.fxml"));
            Parent root = (Parent) loader.load();
            TabelaClientesAvaliacaoController tabelaClientesAvaliacaoController = loader.getController();
            Scene alert = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(alert);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            tabelaClientesAvaliacaoController.setCliente(clientes);
            stage.showAndWait();
            this.cliente = tabelaClientesAvaliacaoController.getClienteSelecionado();
            if(cliente!=null){
                buscarVendas();
            }else{
                Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
                confirmacao.setTitle("Buscar Cliente");
                confirmacao.setHeaderText("Nenhum cliente encontrado.\nPor favor, refaça sua pesquisa!");
                confirmacao.showAndWait();
            }
        }
    }
    
    private void buscarVendas(){
        VendaDAO vendaDAO = new VendaDAO();
        ArrayList<Venda> vendas = new ArrayList<Venda>();
        vendas = vendaDAO.consultarVendasPorCliente(cliente);
    }
}
