/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Financeiro;

import academia.FXMLDocumentController;
import classes_academia.Cliente;
import classes_academia.ContaPagar;
import classes_academia.ContaReceber;
import classes_academia.Usuario;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio_academia.Negocio_Financeiro;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class FXMLmovimentacaoFinanceira implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox mesMovFinan;

    @FXML
    private ComboBox anoMovFinan;

    @FXML
    private Label labelSaldo;

    @FXML
    private TableView tabelaMovFinan = new TableView();

    @FXML
    private TableColumn<ContaPagar, String> idMovFinan;

    @FXML
    private TableColumn<ContaPagar, String> descricaoMovFinan;

    @FXML
    private TableColumn<ContaPagar, String> clienteMovFinan;

    @FXML
    private TableColumn<ContaPagar, Usuario> usuarioMovFinan;

    @FXML
    private TableColumn<ContaPagar, Boolean> tipoContaMovFinanTabela;

    @FXML
    private TableColumn<ContaPagar, Double> valorTabelaMovFinan;

    @FXML
    private TableColumn<ContaPagar, LocalDate> dataVencimentoMovFinan;

    @FXML
    private TableColumn<ContaPagar, LocalDate> dataPagamentoMovFinan;

    @FXML
    private TextField valorTotalEntradaMovFinan;

    @FXML
    private TextField valorTotalSaidaMovFinan;

    @FXML
    private TextField valorTotalMovFinan;

    @FXML
    private TableView tabelaContaReceber = new TableView();

    @FXML
    private TableColumn<ContaReceber, String> idTabelaCR;

    @FXML
    private TableColumn<ContaReceber, String> descricaoTabelaCR;

    @FXML
    private TableColumn<ContaReceber, Cliente> clienteTabelaCR;

    @FXML
    private TableColumn<ContaReceber, Usuario> usuarioTabelaCR;

    @FXML
    private TableColumn<ContaReceber, Boolean> tipoContaTabelaCR;

    @FXML
    private TableColumn<ContaReceber, Double> valorTabelaCR;

    @FXML
    private TableColumn<ContaReceber, LocalDate> dataVencimentoTabelaCR;

    @FXML
    private TableColumn<ContaReceber, LocalDate> dataPagamentoTabelaCR;

    final ObservableList<ContaReceber> contaBusca = FXCollections.observableArrayList();
    final ObservableList<ContaPagar> contaPagarBusca = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Negocio_Financeiro negocio_Financeiro = new Negocio_Financeiro();
        double saldo = negocio_Financeiro.getSaldo();
        labelSaldo.setText("R$" + saldo + "0");
        
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

        anoMovFinan.setItems(anos);
        int i = 0;
        while (anos.size() > i) {
            if (anos.get(i) == LocalDate.now().getYear()) {
                anoMovFinan.getSelectionModel().select(anos.get(i));
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

        mesMovFinan.setItems(meses);
        int mesAtual = LocalDate.now().getMonthValue();
        mesMovFinan.getSelectionModel().select(mesAtual - 1);

        acaoDoSpinnerAno();
        acaoDoSpinnerMes();
    }

    @FXML
    protected void selecionarContaPagar() {
        tabelaMovFinan.setRowFactory(tv -> {
            TableRow<ContaPagar> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ContaPagar rowData = row.getItem();

                    try {

                    } catch (Exception e) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            });

            return row;
        });
    }

    @FXML
    protected void selecionarContaReceber() {
        tabelaContaReceber.setRowFactory(tv -> {
            TableRow<ContaReceber> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ContaReceber rowData = row.getItem();

                    try {

                    } catch (Exception e) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            });

            return row;
        });
        
        
    }

    @FXML
    protected void acaoDoSpinnerMes() {
        buscarContasPagar();
        buscarContasReceber();

    }

    @FXML
    protected void acaoDoSpinnerAno() {
        buscarContasPagar();
        buscarContasReceber();

    }

    @FXML
    protected void buscarContasPagar() {
        Negocio_Financeiro negocio_Financeiro = new Negocio_Financeiro();
        List<ContaPagar> contas = new ArrayList();
        int mesSelecionado = mesMovFinan.getSelectionModel().getSelectedIndex() + 1;
        int anoSelecionado = (int) anoMovFinan.getValue();

        contas = negocio_Financeiro.pegarContasPagarMesAno(mesSelecionado, anoSelecionado);

        if (contas != null) {

            idMovFinan.setCellValueFactory(new PropertyValueFactory("idContaPagar"));
            descricaoMovFinan.setCellValueFactory(new PropertyValueFactory("descricao"));
            clienteMovFinan.setCellValueFactory(new PropertyValueFactory("fornecedor"));
            usuarioMovFinan.setCellValueFactory(new PropertyValueFactory("usuario"));
            valorTabelaMovFinan.setCellValueFactory(new PropertyValueFactory("valor"));
            dataVencimentoMovFinan.setCellValueFactory(new PropertyValueFactory("dataVencimento"));
            dataPagamentoMovFinan.setCellValueFactory(new PropertyValueFactory("dataPagamento"));
            tipoContaMovFinanTabela.setCellValueFactory(new PropertyValueFactory("tipoConta"));

            formatarNomeUsuarioCP();
            formatarDataPagamento();
            formatarDataVencimento();
            formatarValor();
            formatarTipoConta();
            tabelaMovFinan.setItems(contaPagarBusca);
            tabelaMovFinan.getItems().setAll(contas);

            inserirValorNoCampoSaida(contas);
        }
    }

    @FXML
    protected void buscarContasReceber() {
        Negocio_Financeiro negocio_Financeiro = new Negocio_Financeiro();
        List<ContaReceber> contas = new ArrayList();
        int mesSelecionado = mesMovFinan.getSelectionModel().getSelectedIndex() + 1;
        int anoSelecionado = (int) anoMovFinan.getValue();
        contas = negocio_Financeiro.buscarContasReceberMesAno(mesSelecionado, anoSelecionado);

        if (contas != null) {

            idTabelaCR.setCellValueFactory(new PropertyValueFactory("id"));
            descricaoTabelaCR.setCellValueFactory(new PropertyValueFactory("descricao"));
            clienteTabelaCR.setCellValueFactory(new PropertyValueFactory("cliente"));
            usuarioTabelaCR.setCellValueFactory(new PropertyValueFactory("usuario"));
            valorTabelaCR.setCellValueFactory(new PropertyValueFactory("valor"));
            dataVencimentoTabelaCR.setCellValueFactory(new PropertyValueFactory("dataVencimento"));
            dataPagamentoTabelaCR.setCellValueFactory(new PropertyValueFactory("dataBaixa"));
            tipoContaTabelaCR.setCellValueFactory(new PropertyValueFactory("tipoConta"));

            formatarNomeUsuario();
            formatarDataPagamento();
            formatarDataVencimento();
            formatarValor();
            formatarTipoConta();
            formatarNomeCliente();
            tabelaContaReceber.setItems(contaBusca);
            tabelaContaReceber.getItems().setAll(contas);

            inserirValoresNosCampos(contas);
            
        }
    }

    private String formatarData(LocalDate data) {
        final String DATE_PATTERN = "dd/MM/yyyy";
        final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

        return DATE_FORMATTER.format(data);
    }

    private void formatarNomeCliente(){
        clienteTabelaCR.setCellFactory(column -> {
            return new TableCell<ContaReceber, Cliente>() {
                
                @Override
                protected void updateItem(Cliente item, boolean empty){
                    super.updateItem(item, empty);
                    if(item == null){
                        
                    }else{
                        setText(item.getNome());
                    }
                }
            };
        });
    }
    
    private void formatarNomeUsuario(){
        usuarioTabelaCR.setCellFactory(column -> {
            return new TableCell<ContaReceber, Usuario>() {
                
                @Override
                protected void updateItem(Usuario item, boolean empty){
                    super.updateItem(item, empty);
                    if(item == null){
                        
                    }else{
                        setText(item.getNome());
                    }
                }
            };
        });
    }
    
    private void formatarNomeUsuarioCP(){
        usuarioMovFinan.setCellFactory(column -> {
            return new TableCell<ContaPagar, Usuario>() {
                
                @Override
                protected void updateItem(Usuario item, boolean empty){
                    super.updateItem(item, empty);
                    if(item == null){
                        
                    }else{
                        setText(item.getNome());
                    }
                }
            };
        });
    }
    
    private void formatarDataPagamento() {
        dataPagamentoMovFinan.setCellFactory(column -> {
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

        
        dataPagamentoTabelaCR.setCellFactory(column -> {
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

    private void formatarTipoConta() {
        tipoContaMovFinanTabela.setCellFactory(column -> {
            return new TableCell<ContaPagar, Boolean>() {

                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {

                    } else {
                        if(!item){
                            setText("Saída");
                        }
                    }
                }
            };
        });
        
        tipoContaTabelaCR.setCellFactory(column -> {
            return new TableCell<ContaReceber, Boolean>() {

                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {

                    } else {
                        if(item){
                            setText("Entrada");
                        }
                    }
                }
            };
        });
    }
    
    private void formatarDataVencimento() {
        dataVencimentoMovFinan.setCellFactory(column -> {
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
        dataVencimentoTabelaCR.setCellFactory(column -> {
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

    private void formatarValor() {
        valorTabelaMovFinan.setCellFactory(column -> {
            return new TableCell<ContaPagar, Double>() {

                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {

                    } else {
                        DecimalFormat formato = new DecimalFormat("0.##");
                         
                         item = Double.valueOf(formato.format(item));
                        setText("R$ " + item.toString().replace('.', ','));
                        //setText("R$ " + item.toString().replace('.', ',') + "0");
                    }
                }
            };
        });

        valorTabelaCR.setCellFactory(column -> {
            return new TableCell<ContaReceber, Double>() {

                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {

                    } else {
                        DecimalFormat formato = new DecimalFormat("0.##");
                         
                         item = Double.valueOf(formato.format(item));
                        setText("R$ " + item.toString().replace('.', ','));
                        //setText("R$ " + item.toString().replace('.', ',') + "0");
                    }
                }
            };
        });
    }

    @FXML
    protected void inserirValoresNosCampos(List<ContaReceber> contasReceber) {
        int i = 0;
        double valor = 0;
        
        if (contasReceber != null) {
            while (contasReceber.size() > i) {
                valor = valor + contasReceber.get(i).getValor();
                i++;
            }
        }
        
        DecimalFormat formato = new DecimalFormat("#.##");
        valor= Double.valueOf(formato.format(valor));
        valorTotalEntradaMovFinan.setText(("R$" + valor).replace('.', ','));
        atualizarValorTotal(valor, true);
    }

    @FXML
    protected void inserirValorNoCampoSaida(List<ContaPagar> contasPagar) {
        int i = 0;
        double valorSaida = 0;
        
        if (contasPagar != null) {
            while (contasPagar.size() > i) {
                valorSaida = valorSaida + contasPagar.get(i).getValor();
                i++;
            }
        }
        
       DecimalFormat formato = new DecimalFormat("#.##");
        valorSaida = Double.valueOf(formato.format(valorSaida));
        valorTotalSaidaMovFinan.setText(("R$" + valorSaida).replace('.', ','));
     
       atualizarValorTotal(valorSaida, false);
    }
    
    private void atualizarValorTotal(Double valor, boolean tipo){
       double  valorTotal = 0;
        if(valorTotalMovFinan.getText().isEmpty()){
           valorTotalMovFinan.setText(("R$ " + valor + "0").replace('.', ','));
       }else if(tipo){
           
           valorTotal = Double.parseDouble(valorTotalMovFinan.getText().substring(2).replace(',', '.'));
           valorTotal += valor;
       }else{
           valorTotal -= valor;
       }
        
        valorTotalMovFinan.setText(("R$" + valorTotal + "0").replace('.', ','));
        }
}
