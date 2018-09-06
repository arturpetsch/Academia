/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Financeiro;

import academia.FXMLDocumentController;
import classes_academia.ContaPagar;
import classes_academia.ContaPagarParametro;
import classes_academia.ContaReceber;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import negocio_academia.Negocio_Financeiro;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class FXMLcontaPagar implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox anoContaPagar;

    @FXML
    private ComboBox mesContaPagar;

    @FXML
    private Button botaoMostrarContaPagar;

    @FXML
    private TableView tabelaContasPagar = new TableView<>();

    @FXML
    private TableColumn<ContaPagar, String> codigoTabelaContasPagar;

    @FXML
    private TableColumn<ContaPagar, String> descricaoTabelaContasPagar;

    @FXML
    private TableColumn<ContaPagar, String> empresaTabelaContasPagar;

    @FXML
    private TableColumn<ContaPagar, String> parcelaTabelaContasPagar;

    @FXML
    private TableColumn<ContaPagar, String> valorParcelaTabelaContasPagar;

    @FXML
    private TableColumn<ContaPagar, LocalDate> dataVencimentoTabelaContasPagar;

    @FXML
    private TableColumn<ContaPagar, LocalDate> dataPagamentoTabelaContasPagar;

    @FXML
    private TextField descricaoContaPagar;

    @FXML
    private TextField empresaContaPagar;

    @FXML
    private TextField valorContaPagar;

    @FXML
    private ComboBox parcelaContaPagar;

    @FXML
    private DatePicker dataVencimentoContaPagar;

    @FXML
    private DatePicker dataPagamentoContaPagar;

    @FXML
    private Button botaoCancelarLancamento;

    @FXML
    private Button botaoSalvarLancamento;

    @FXML
    private Label labelNovaConta;

    final ObservableList<ContaReceber> contaPagarBusca = FXCollections.observableArrayList();

    ContaPagar contaPagarGlobal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contaPagarGlobal = new ContaPagar();

        
        tabelaContasPagar.setOnKeyPressed((KeyEvent e) -> {

            if (!tabelaContasPagar.getSelectionModel().isEmpty()
                    && e.getCode() == KeyCode.DELETE) {

                System.out.println("APAGAR CONTA");

                DialogPane alerta = new DialogPane();
                try {
                    ContaPagar cp = (ContaPagar) tabelaContasPagar.getSelectionModel().getSelectedItem();
                    ContaPagarParametro contaPagarParametro = new ContaPagarParametro(cp);
                    Parent root = FXMLLoader.load(getClass().getResource("FXMLalert.fxml"));
                    Scene alert = new Scene(root);
                    Stage stage = new Stage();
                    
                    Image icone = new Image(getClass().getResourceAsStream("/academia/icon/cancel.png")); 
                    stage.getIcons().add(icone);
                    stage.setScene(alert);
                    stage.setResizable(false);
                    stage.centerOnScreen();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                   
                } catch (IOException ex) {
                    Logger.getLogger(FXMLcontaPagar.class.getName()).log(Level.SEVERE, null, ex);
                }

                e.consume();
            }
        });

        tabelaContasPagar.setRowFactory(tv -> {
            TableRow<ContaPagar> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ContaPagar rowData = row.getItem();

                    try {
                        labelNovaConta.setText("Alterar Conta a Pagar:");
                        popularCamposContaPagar(rowData);
                    } catch (Exception e) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            });

            return row;
        });

        ObservableList<Integer> parcela = FXCollections.observableArrayList();
        parcela.add(1);
        parcela.add(2);
        parcela.add(3);
        parcela.add(4);
        parcela.add(5);
        parcela.add(6);
        parcela.add(7);
        parcela.add(8);
        parcela.add(9);
        parcela.add(10);
        parcela.add(11);
        parcela.add(12);

        parcelaContaPagar.setItems(parcela);
        parcelaContaPagar.getSelectionModel().select(0);

        ObservableList<Integer> anos = FXCollections.observableArrayList();

        anos.add(2018);
        anos.add(2019);
        anos.add(2020);
        anos.add(2021);
        anos.add(2022);
        anos.add(2023);
        anos.add(2024);
        anos.add(2025);
        anos.add(2026);
        anos.add(2027);
        anos.add(2028);
        anos.add(2029);
        anos.add(2030);
        anos.add(2031);
        anos.add(2032);
        anos.add(2033);
        anos.add(2034);
        anos.add(2035);
        anos.add(2036);
        anos.add(2037);
        anos.add(2038);
        anos.add(2039);
        anos.add(2040);

        anoContaPagar.setItems(anos);
        int i = 0;
        while (anos.size() > i) {
            if (anos.get(i) == LocalDate.now().getYear()) {
                anoContaPagar.getSelectionModel().select(anos.get(i));
                i = anos.size() + 1;
            }
            i++;
        }

        ObservableList<String> meses = FXCollections.observableArrayList();
        meses.add("Janeiro");
        meses.add("Fevereiro");
        meses.add("Março");
        meses.add("Abril");
        meses.add("Maio");
        meses.add("Junho");
        meses.add("Julho");
        meses.add("Agosto");
        meses.add("Setembro");
        meses.add("Outubro");
        meses.add("Novembro");
        meses.add("Dezembro");

        mesContaPagar.setItems(meses);
        int mesAtual = LocalDate.now().getMonthValue();
        mesContaPagar.getSelectionModel().select(mesAtual - 1);

    }

    protected ContaPagar returnContaPagar(){
        return contaPagarGlobal;
    }
    
    private void popularCamposContaPagar(ContaPagar contaPagar) {
        contaPagarGlobal = contaPagar;
        descricaoContaPagar.setText(contaPagar.getDescricao());
        empresaContaPagar.setText(contaPagarGlobal.getFornecedor());
        valorContaPagar.setText((contaPagarGlobal.getValor() + "").replace('.', ','));

        parcelaContaPagar.getSelectionModel().select(contaPagarGlobal.getParcela() - 1);
        dataVencimentoContaPagar.setValue(contaPagarGlobal.getDataVencimento());
        dataPagamentoContaPagar.setValue(contaPagarGlobal.getDataPagamento());
    }

    @FXML
    protected void mostrarContasPagar(ActionEvent action) {
        int mesSelecionado = mesContaPagar.getSelectionModel().getSelectedIndex() + 1;
        int anoSelecionado = (int) anoContaPagar.getValue();

        Negocio_Financeiro negocioFinanceiro = new Negocio_Financeiro();
        List<ContaPagar> contasPagar = new ArrayList();
        contasPagar = negocioFinanceiro.pegarContasPagarMesAno(mesSelecionado, anoSelecionado);

        if (contasPagar != null) {
            codigoTabelaContasPagar.setCellValueFactory(new PropertyValueFactory("idContaPagar"));
            descricaoTabelaContasPagar.setCellValueFactory(new PropertyValueFactory("descricao"));
            empresaTabelaContasPagar.setCellValueFactory(new PropertyValueFactory("fornecedor"));
            parcelaTabelaContasPagar.setCellValueFactory(new PropertyValueFactory("parcela"));
            valorParcelaTabelaContasPagar.setCellValueFactory(new PropertyValueFactory("valor"));
            dataVencimentoTabelaContasPagar.setCellValueFactory(new PropertyValueFactory("dataVencimento"));
            dataPagamentoTabelaContasPagar.setCellValueFactory(new PropertyValueFactory("dataPagamento"));

            formatarDataPagamento();
            formatarDataVencimento();
            formatarValor();
            tabelaContasPagar.setItems(contaPagarBusca);
            tabelaContasPagar.getItems().setAll(contasPagar);
        }
    }

    
    
    @FXML
    protected void salvarLancamento(ActionEvent action) {
        ContaPagar contaPagar = coletarDadosContaPagar();
        Negocio_Financeiro negocio_Financeiro = new Negocio_Financeiro();
        contaPagar = coletarDadosContaPagar();

        if (contaPagar != null) {
            if (contaPagar.getIdContaPagar() > 0) {
                if (dataPagamentoContaPagar.getValue() != null) {
                    negocio_Financeiro.alterarContaPagar(contaPagar);
                    mostrarContasPagar(action);
                    limparCampos();
                } else {
                    Alert information = new Alert(Alert.AlertType.ERROR);
                    information.setTitle("Erro ao alterar Conta a Pagar");
                    information.setHeaderText("Data Pagamento inválida! Verifique.");
                    information.showAndWait();
                }
            } else {
                negocio_Financeiro.inserirNovaContaPagar(contaPagar);
                limparCampos();
                mostrarContasPagar(action);
            }
        }
    }

    private void limparCampos() {
        descricaoContaPagar.setText("");
        empresaContaPagar.setText("");
        valorContaPagar.setText("");
        parcelaContaPagar.getSelectionModel().select(0);
        dataPagamentoContaPagar.setValue(null);
        dataVencimentoContaPagar.setValue(null);
    }

    @FXML
    private void cancelarLancamento(ActionEvent event) {
        limparCampos();
        labelNovaConta.setText("Nova Conta a Pagar");
        tabelaContasPagar.setItems(null);
    }

    private ContaPagar coletarDadosContaPagar() {
        if (validarCampos()) {
            String descricao = descricaoContaPagar.getText();
            String empresa = empresaContaPagar.getText();
            //Double valor = converterValorParaDecimal(valorContaPagar.getText());
           
            
            String valor = (valorContaPagar.getText().replace(',', '.'));
            
            int parcela = (int) parcelaContaPagar.getValue();
            LocalDate dataVencimento = dataVencimentoContaPagar.getValue();
            LocalDate dataPagamento = dataPagamentoContaPagar.getValue();
            int id = contaPagarGlobal.getIdContaPagar();
            if (id > 0) {
                ContaPagar contaPagar = new ContaPagar(id, valor, descricao, dataVencimento, dataPagamento, parcela, empresa, false);
                return contaPagar;
            }
            ContaPagar contaPagar = new ContaPagar(valor, descricao, dataVencimento, dataPagamento, parcela, empresa, false);
            return contaPagar;
        }
        return null;
    }

    private void deletar() {

    }

    private boolean validarCampos() {
        String e = "";

        if (dataVencimentoContaPagar.getValue() == null) {
            e += "Data Vencimento Inválida!\n";

        }

        /*if(dataVencimentoContaPagar.getValue().isBefore(LocalDate.now())){
            e += "Data Vencimento Anterior a Data Atual";
        }*/
        if (valorContaPagar.getText().isEmpty()) {
            e += "Valor Inválido!\n";
        }

        if (!valorContaPagar.getText().isEmpty()) {
            Double valor = Double.parseDouble(valorContaPagar.getText().replace(',', '.'));
            if (valor <= 0) {
                e += "Número Inválido! Informe apenas números maiores que zero!\n";

            }
        }

        if (descricaoContaPagar.getText().isEmpty()) {
            e += "Descrição Inválida!\n";
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
    public void AcaoBotaEnter() {
        botaoSalvarLancamento.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode() == KeyCode.ENTER) {
                ActionEvent event = null;
                salvarLancamento(event);
                System.out.println("AQUI NO ENTER");
            }
        });
    }

    @FXML
    public void AcaoBotaoCancelar() {
        botaoCancelarLancamento.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode() == KeyCode.ENTER) {
                ActionEvent event = null;
                cancelarLancamento(event);
            }
        });
    }

    @FXML
    public void acaoTeclaDeletar() {
        tabelaContasPagar.setRowFactory(tv -> {
            TableRow<ContaPagar> row = new TableRow<>();
            row.setOnKeyPressed((final KeyEvent event) -> {
                if (event.getCode().equals(KeyCode.DELETE) && (!row.isEmpty())) {
                    System.err.println("DELETAR CONTA");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    ButtonType btnClose = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.setTitle("Erro ao Buscar Cliente");
                    alert.setHeaderText("Nenhum cliente encontrado com o nome informado. \nFavor verificar!");
                    alert.getButtonTypes().setAll(btnClose);
                    alert.showAndWait();
                }
            });
            return row;
        });
    }

    private String formatarData(LocalDate data) {
        final String DATE_PATTERN = "dd/MM/yyyy";
        final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

        return DATE_FORMATTER.format(data);
    }

    private void formatarDataPagamento() {
        dataPagamentoTabelaContasPagar.setCellFactory(column -> {
            return new TableCell<ContaPagar, LocalDate>() {

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
        dataVencimentoTabelaContasPagar.setCellFactory(column -> {
            return new TableCell<ContaPagar, LocalDate>() {

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
        valorParcelaTabelaContasPagar.setCellFactory(column -> {
            return new TableCell<ContaPagar, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {

                    } else {
                         DecimalFormat formato = new DecimalFormat("0.##");
                         
                         
                        setText("R$ " + item.replace('.', ','));
                        
                    }
                }
            };
        });
    }
}
