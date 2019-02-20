/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Financeiro;

import academia.FXMLDocumentController;
import classes_academia.Cliente;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
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
public class TabelaClientesContaReceberController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView tabelaClientesContaReceber = new TableView<>();

    @FXML
    private TableColumn<Cliente, String> idTabelaClienteContaReceber;

    @FXML
    private TableColumn<Cliente, String> nomeTabelaClienteContaReceber;

    @FXML
    private TableColumn<Cliente, LocalDate> DataNascimentoTabelaClienteContaReceber;

    @FXML
    private TableColumn<Cliente, String> CPFTabelaClienteContaReceber;

    final ObservableList<Cliente> clienteBusca = FXCollections.observableArrayList();

    Cliente cliente = new Cliente();

    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tabelaClientesContaReceber.setRowFactory(tv -> {
            TableRow<Cliente> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Cliente rowData = row.getItem();
                    try {

                        selecionarCliente(rowData);
                    } catch (Exception e) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
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
        idTabelaClienteContaReceber.setCellValueFactory(new PropertyValueFactory("id"));
        nomeTabelaClienteContaReceber.setCellValueFactory(new PropertyValueFactory("nome"));
        CPFTabelaClienteContaReceber.setCellValueFactory(new PropertyValueFactory("cpf"));
        DataNascimentoTabelaClienteContaReceber.setCellValueFactory(new PropertyValueFactory("dataNascimento"));

        formatarDataNascimento();
        tabelaClientesContaReceber.setItems(clienteBusca);
        tabelaClientesContaReceber.getItems().setAll(clientes);
    }
    
    /**
     * Metodo que recebe um arraylist de clientes oriundos da tela Clientes.
     *
     * @param clientes
     */
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
        popularTabela();
    }

    /**
     * Método que recebe o cliente selecionado da tabela e retorna a tela de
     * conta a receber.
     */
    private void selecionarCliente(Cliente cliente) throws IOException {
        this.cliente = cliente;
         Stage stage = (Stage) tabelaClientesContaReceber.getScene().getWindow();
        stage.close();
        getClienteSelecionado();
    }
    
    public Cliente getClienteSelecionado(){
        return this.cliente;
    }
    
    private String formatarData(LocalDate data) {
        final String DATE_PATTERN = "dd/MM/yyyy";
        final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

        return DATE_FORMATTER.format(data);
    }
    
    private void formatarDataNascimento() {
        DataNascimentoTabelaClienteContaReceber.setCellFactory(column -> {
            return new TableCell<Cliente, LocalDate>() {

                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {

                    } else {
                        setText(formatarData(item));
                    }
                }
            };
        });
    }
}


