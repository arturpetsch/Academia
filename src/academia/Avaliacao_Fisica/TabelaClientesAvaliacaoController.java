/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Avaliacao_Fisica;

import classes_academia.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class TabelaClientesAvaliacaoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView tabelaClientes = new TableView();

    @FXML
    private TableColumn<Cliente, Integer> idTabelaClientes = new TableColumn();

    @FXML
    private TableColumn<Cliente, String> nomeTabelaClientes = new TableColumn();

    ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    ObservableList<Cliente> clienteBusca = FXCollections.observableArrayList();
    
    Cliente cliente = new Cliente();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    tabelaClientes.setRowFactory(tv -> {
            TableRow<Cliente> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        selecionaCliente(row.getItem());
                    } catch (IOException ex) {
                        Logger.getLogger(TabelaClientesAvaliacaoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }
            });

            return row;
        });
    }    
    
     /**
     * Método que insere os dados do arraylist na tabela.
     */
    @FXML
    protected void popularTabela() {
        
        if (clientes != null) {
            idTabelaClientes.setCellValueFactory(new PropertyValueFactory("id"));
            nomeTabelaClientes.setCellValueFactory(new PropertyValueFactory("nome"));

            tabelaClientes.setItems(clienteBusca);
            tabelaClientes.getItems().setAll(clientes);

        }
    }
    
     /**
     * Metodo que recebe um arraylist de clientes oriundos da tela Clientes.
     *
     * @param clientes
     */
    public void setCliente(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
        popularTabela();
    }
    
     /**
     * Método que recebe o cliente selecionado da tabela e retorna a tela de
     * cliente.
     */
    private void selecionaCliente(Cliente cliente) throws IOException {
        this.cliente = cliente;
         Stage stage = (Stage) tabelaClientes.getScene().getWindow();
        stage.close();
        getClienteSelecionado();
    }
    
    public Cliente getClienteSelecionado(){
        return this.cliente;
    }
    
}
