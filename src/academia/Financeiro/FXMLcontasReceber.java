/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Financeiro;

import academia.Cliente.FXMLbuscarCliente;
import academia.FXMLDocumentController;
import academia.Relatorios.ReciboPagamento;
import academia_DAO.ContaReceberDAO;
import classes_academia.Cliente;
import classes_academia.ContaReceber;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import negocio_academia.Negocio_Cliente;
import negocio_academia.Negocio_Financeiro;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class FXMLcontasReceber implements Initializable {

    @FXML
    private TextField nomeClienteBuscaContasReceber;

    @FXML
    private Button botaoBuscarClienteContasReceber;

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

    @FXML
    private TableView tabelaMensalidadeContaReceber = new TableView<>();

    @FXML
    private TableColumn<ContaReceber, String> descricaoTabelaMensalidade;

    @FXML
    private TableColumn<ContaReceber, Double> valorTabelaMensalidade;

    @FXML
    private TableColumn<ContaReceber, LocalDate> dataVencimentoTabelaMensalidade;

    @FXML
    private TableColumn<ContaReceber, LocalDate> dataPagamentoTabelaMensalidade;

    @FXML
    private Label labelNomeClienteContasReceber;

    @FXML
    private DatePicker dataVencimentoContaReceber;

    @FXML
    private DatePicker dataPagamentoContaReceber;

    @FXML
    private TextField valorMensalidadeContaReceber;

    @FXML
    private Button botaoLancarContaReceber;

    @FXML
    private Button botaoCancelarLancamentoContaReceber;

    final ObservableList<Cliente> clienteBusca = FXCollections.observableArrayList();

    final ObservableList<ContaReceber> contaReceberBusca = FXCollections.observableArrayList();

    Cliente clienteGlobal;
    ContaReceber contaReceberGlobal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    private void selecionarCliente(Cliente cliente) {
        clienteGlobal = cliente;
        labelNomeClienteContasReceber.setText(cliente.getNome());
        ContaReceberDAO contaReceberDAO = new ContaReceberDAO();
        List<ContaReceber> contasReceber = new ArrayList<>();

        contasReceber = contaReceberDAO.buscarTodasContasPorCliente(cliente.getId());

        if (contasReceber != null) {
            descricaoTabelaMensalidade.setCellValueFactory(new PropertyValueFactory("descricao"));
            valorTabelaMensalidade.setCellValueFactory(new PropertyValueFactory("valor"));
            dataVencimentoTabelaMensalidade.setCellValueFactory(new PropertyValueFactory("dataVencimento"));
            dataPagamentoTabelaMensalidade.setCellValueFactory(new PropertyValueFactory("dataBaixa"));

            formatarDataPagamento();
            formatarDataVencimento();
            formatarValor();
            tabelaMensalidadeContaReceber.setItems(contaReceberBusca);
            tabelaMensalidadeContaReceber.getItems().setAll(contasReceber);

            tabelaMensalidadeContaReceber.setRowFactory(tv -> {
                TableRow<ContaReceber> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        ContaReceber rowData = row.getItem();
                        try {
                            selecionarContaReceber(rowData);
                        } catch (Exception e) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                });

                return row;
            });

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            ButtonType btnClose = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.setTitle("Erro ao buscar mensalidades");
            alert.setHeaderText("Nenhuma mensalidade deste Cliente está cadastrada no sistema. \nFavor verificar!");
            alert.getButtonTypes().setAll(btnClose);
            alert.showAndWait();
        }
    }

    private void selecionarContaReceber(ContaReceber contaReceber) {
        dataVencimentoContaReceber.setValue(contaReceber.getDataVencimento());
        dataPagamentoContaReceber.setValue(LocalDate.now());
        valorMensalidadeContaReceber.setText((contaReceber.getValor() + "0").replace('.', ','));

        contaReceberGlobal = contaReceber;
    }

    @FXML
    private void buscarCliente(ActionEvent action) {
        Negocio_Cliente negocioCliente = new Negocio_Cliente();
        List<Cliente> clientes = new ArrayList<>();
        clientes = negocioCliente.buscarCliente(nomeClienteBuscaContasReceber.getText());

        if (clientes != null) {
            idTabelaClienteContaReceber.setCellValueFactory(new PropertyValueFactory("id"));
            nomeTabelaClienteContaReceber.setCellValueFactory(new PropertyValueFactory("nome"));
            CPFTabelaClienteContaReceber.setCellValueFactory(new PropertyValueFactory("cpf"));
            DataNascimentoTabelaClienteContaReceber.setCellValueFactory(new PropertyValueFactory("dataNascimento"));

            formatarDataNascimento();
            tabelaClientesContaReceber.setItems(clienteBusca);
            tabelaClientesContaReceber.getItems().setAll(clientes);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            ButtonType btnClose = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.setTitle("Erro ao Buscar Cliente");
            alert.setHeaderText("Nenhum cliente encontrado com o nome informado. \nFavor verificar!");
            alert.getButtonTypes().setAll(btnClose);
            alert.showAndWait();

        }
    }

    private String formatarData(LocalDate data) {
        final String DATE_PATTERN = "dd/MM/yyyy";
        final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

        return DATE_FORMATTER.format(data);
    }

    private void formatarDataPagamento() {
        dataPagamentoTabelaMensalidade.setCellFactory(column -> {
            return new TableCell<ContaReceber, LocalDate>() {

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

    private void formatarDataVencimento() {
        dataVencimentoTabelaMensalidade.setCellFactory(column -> {
            return new TableCell<ContaReceber, LocalDate>() {

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

    private void formatarValor() {
        valorTabelaMensalidade.setCellFactory(column -> {
            return new TableCell<ContaReceber, Double>() {

                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {

                    } else {
                        setText("R$ " + item.toString().replace('.', ',') + "0");
                    }
                }
            };
        });
    }

    @FXML
    public void salvarLancamento(ActionEvent action) {
        if (validarCampos()) {
            Negocio_Financeiro negocio_Financeiro = new Negocio_Financeiro();
            LocalDate dataPagamento = dataPagamentoContaReceber.getValue();
            System.err.println(dataPagamento);
            contaReceberGlobal.setDataBaixa(dataPagamento);
            Double valor = Double.parseDouble(valorMensalidadeContaReceber.getText().replace(',', '.'));
            contaReceberGlobal.setValor((valor));
            if (contaReceberGlobal.getId() > 0) {
                if (negocio_Financeiro.alterarMensalidade(contaReceberGlobal)) {
                    System.err.println("alterado a mensalidade");
                    //so permitir alterar duas vezes se for o admin;
                    limparCampos();
                    selecionarCliente(clienteGlobal);

                    Alert gerarMensalidade = new Alert(Alert.AlertType.NONE);
                    ButtonType gerar = new ButtonType("Gerar Recibo");
                    ButtonType cancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
                    gerarMensalidade.setTitle("Gerar Recibo");
                    gerarMensalidade.setHeaderText("Pagamento de Mensalidade realizado com sucesso!");
                   gerarMensalidade.getButtonTypes().addAll(gerar, cancel);
                   gerarMensalidade.initStyle(StageStyle.UTILITY);
                  
                    gerarMensalidade.showAndWait().ifPresent(b -> {
                        if (b == gerar) {
                            gerarRecibo();
                        } else {
                            System.err.println("Nao alterado a mensalidade");
                        }
                    });
                }

            }
        }
    }
    

    public void gerarRecibo() {
        /*ReciboPagamento reciboPagamento = new ReciboPagamento();
        ContaReceberDAO contaReceberDAO = new ContaReceberDAO();
        Collection<ContaReceber> contaReceber = new ArrayList();
        contaReceber = (Collection<ContaReceber>) contaReceberGlobal;
        try {
            reciboPagamento.gerarRecibo(contaReceber);
        } catch (JRException ex) {
            Logger.getLogger(FXMLcontasReceber.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        ReciboPagamento reciboPagamento = new ReciboPagamento();
        ContaReceberDAO contaReceberDAO = new ContaReceberDAO();
        Collection<ContaReceber> contaReceber = new ArrayList();
        
        contaReceber.add(contaReceberGlobal);
        try {
            reciboPagamento.gerarRecibo(contaReceber);
        } catch (JRException ex) {
            Logger.getLogger(FXMLcontasReceber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limparCampos() {
        valorMensalidadeContaReceber.setText("");
        dataPagamentoContaReceber.setValue(null);
        dataVencimentoContaReceber.setValue(null);
    }

    private boolean validarCampos() {
        String e = "";

        if (dataPagamentoContaReceber.getValue() == null) {
            e += "Data Pagamento Inválida\n";

        }

        if (dataVencimentoContaReceber.getValue() == null) {
            e += "Data Vencimento Inválida!\n";

        }
        if (valorMensalidadeContaReceber.getText().isEmpty()) {
            e += "Valor Mensalidade Inválido!";
        }

        if (!valorMensalidadeContaReceber.getText().isEmpty()) {
            Double valor = Double.parseDouble(valorMensalidadeContaReceber.getText().replace(',', '.'));
            if (valor <= 0) {
                e += "Número Inválido! Informe apenas números maiores que zero!\n";

            }
        }
        if (e.length() == 0) {
            return true;
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Campos Inválidos");
            alerta.setHeaderText("Por favor, corrija os campos inválidos!");
            alerta.setContentText(e);
            alerta.showAndWait();
            return false;
        }
    }

    @FXML
    private void cancelarLancamento(ActionEvent action) {
        limparCampos();
        tabelaClientesContaReceber.setItems(null);
        tabelaMensalidadeContaReceber.setItems(null);
    }

    @FXML
    public void AcaoBotaEnter() {
        botaoLancarContaReceber.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode() == KeyCode.ENTER) {
                ActionEvent event = null;
                salvarLancamento(event);
                System.out.println("AQUI NO ENTER");
            }
        });
    }

    @FXML
    public void AcaoBotaoCancelar() {
        botaoCancelarLancamentoContaReceber.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode() == KeyCode.ENTER) {
                ActionEvent event = null;
                cancelarLancamento(event);
            }
        });
    }
}
