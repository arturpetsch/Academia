/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Cliente;

import academia.FXMLDocumentController;
import classes_academia.Cliente;
import classes_academia.ParametroCliente;
import classes_academia.ParametroUsuario;
import classes_academia.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import negocio_academia.Negocio_Cliente;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class FXMLbuscarCliente implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<Cliente, String> tabelaCodCliente;

    @FXML
    private TableColumn<Cliente, String> tabelaNomeCliente;

    @FXML
    private TableColumn<Cliente, String> tabelaCPFCliente;

    @FXML
    private TableColumn<Cliente, String> tabelaContatoCliente;

    @FXML
    private TableColumn<Cliente, Boolean> tabelaStatusCliente;

    @FXML
    private AnchorPane idPainelBuscarCliente;

    @FXML
    private Button botaoBuscarClienteBusca;

    @FXML
    private TextField nomeClienteBusca;

    @FXML
    private Button botaoAtualizarTabela;

    @FXML
    private TableView<Cliente> tabelaBuscaCliente = new TableView<>();

    final ObservableList<Cliente> clienteBusca = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image iconRefresh = new Image(getClass().getResourceAsStream("/academia/icon/refresh.png")); //inserir icone no projeto
        botaoAtualizarTabela.setGraphic(new ImageView(iconRefresh));
        tabelaBuscaCliente.setRowFactory(tv -> {
            TableRow<Cliente> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Cliente rowData = row.getItem();
                    try {

                        mostrarOpcoesCliente(rowData);
                    } catch (Exception e) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            });

            return row;
        });
    }

    private void mostrarOpcoesCliente(Cliente cliente) throws IOException {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnAlterarCliente = new ButtonType("Alterar Cliente ");
        ButtonType btnAlterarStatus = new ButtonType("Alterar Status Cliente");
        ButtonType btnExcluirCliente = new ButtonType("Excluir Cliente");
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alerta.setTitle("Opções Clientes");
        alerta.setHeaderText("Escolha a opção relacionada ao Cliente " + cliente.getNome());
        alerta.getButtonTypes().setAll(btnAlterarCliente, btnAlterarStatus, btnCancelar, btnExcluirCliente);

        alerta.showAndWait().ifPresent(b -> {
            if (b == btnAlterarCliente) {
                try {
                    mostrarOpcaoAlterar(cliente);
                    tabelaBuscaCliente.refresh();

                } catch (IOException ex) {
                    Logger.getLogger(FXMLbuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            if (b == btnExcluirCliente) {
                Alert remocao = new Alert(Alert.AlertType.WARNING);
                ButtonType btnSim = new ButtonType("Sim");
                ButtonType btnNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
                remocao.setTitle("Excluir Cliente");
                remocao.setHeaderText("Deseja Realmente Excluir o Cliente " + cliente.getNome() + " ?");
                remocao.getButtonTypes().setAll(btnSim, btnNao);
                remocao.showAndWait().ifPresent(btn -> {
                    if (btn == btnSim) {
                        Negocio_Cliente negocio_Cliente = new Negocio_Cliente();
                        ParametroUsuario parametroUsuario = new ParametroUsuario();
                        Usuario usuario = parametroUsuario.getUsuario();
                        if (usuario.getTipoUser().equals("ADMIN")) {
                            negocio_Cliente.deletarCliente(cliente);
                            tabelaBuscaCliente.setItems(clienteBusca);
                        } else {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setTitle("Erro ao deletar o Cliente!");
                            alert1.setHeaderText("Você não possui permissão para deletar este usuário.");
                            alert1.showAndWait();
                        }
                    }
                });
            }

            if (b == btnAlterarStatus) {
                Alert remocao = new Alert(Alert.AlertType.CONFIRMATION);
                ButtonType btnSim = new ButtonType("Sim");
                ButtonType btnNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
                remocao.setTitle("Alterar Status Cliente");
                remocao.setHeaderText("Deseja Realmente Alterar o Status do Cliente " + cliente.getNome() + " ?");
                remocao.getButtonTypes().setAll(btnSim, btnNao);
                remocao.showAndWait().ifPresent(btn -> {
                    if (btn == btnSim) {
                        Negocio_Cliente negocio_Cliente = new Negocio_Cliente();
                        negocio_Cliente.alterarStatusCliente(cliente);
                        tabelaBuscaCliente.refresh();

                    }

                });
            }
        });
        tabelaBuscaCliente.setItems(clienteBusca);

    }

    @FXML
    public void buscarClientes(ActionEvent action) {
        Negocio_Cliente negocio_Cliente = new Negocio_Cliente();
        List<Cliente> clientes;
        clientes = negocio_Cliente.buscarCliente(nomeClienteBusca.getText());
        if (clientes != null) {
            tabelaCodCliente.setCellValueFactory(new PropertyValueFactory("id"));
            tabelaNomeCliente.setCellValueFactory(new PropertyValueFactory("nome"));
            tabelaCPFCliente.setCellValueFactory(new PropertyValueFactory("cpf"));
            tabelaContatoCliente.setCellValueFactory(new PropertyValueFactory("contato"));
            tabelaStatusCliente.setCellValueFactory(new PropertyValueFactory("status"));

            atualizarStatusNaTabela();

            tabelaBuscaCliente.setItems(clienteBusca);
            tabelaBuscaCliente.getItems().setAll(clientes);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            ButtonType btnClose = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.setTitle("Erro ao Buscar Cliente");
            alert.setHeaderText("Nenhum cliente encontrado com o nome informado. \nFavor verificar!");
            alert.getButtonTypes().setAll(btnClose);
            alert.showAndWait();
        }
    }

    private void atualizarStatusNaTabela() {
        tabelaStatusCliente.setCellFactory(column -> {
            return new TableCell<Cliente, Boolean>() {

                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {

                    } else if (item) {
                        setAlignment(Pos.CENTER);
                        setText("Ativo");

                    } else {
                        setAlignment(Pos.CENTER);
                        setText("Inativo");
                    }
                }
            };
        });

    }

    private void mostrarOpcaoAlterar(Cliente cliente) throws IOException {
        ParametroCliente parametroCliente = new ParametroCliente(cliente);
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("FXMLalterarCliente.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelBuscarCliente.getChildren().setAll(pane);
        idPainelBuscarCliente.setVisible(true);

        FXMLalterarCliente fXMLalterarCliente = new FXMLalterarCliente();

    }

    @FXML
    public void botaoAtualizarTabela(ActionEvent event) {
        buscarClientes(event);
        tabelaBuscaCliente.refresh();
        System.err.println("att tabela");

    }

}
