/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Vendas;

import academia.Avaliacao_Fisica.TabelaClientesAvaliacaoController;
import academia_DAO.ClienteDAO;
import academia_DAO.VendaDAO;
import classes_academia.Cliente;
import classes_academia.Produto;
import classes_academia.ProdutoVenda;
import classes_academia.Venda;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class VendasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label labelCodigo;
    
    @FXML
    private Label labelNome;
    
    @FXML
    private Label labelCpf;
    
    @FXML
    private Button buscarCliente;
    
    @FXML
    private TableColumn<ProdutoVenda, Produto> idProduto;
    
    @FXML
    private TableColumn<Produto, String> descricao;
    
    @FXML
    private TableColumn<Produto, Double> quantidade;
    
    @FXML
    private TableColumn<Produto, BigDecimal> vlUnit;
    
    @FXML
    private TableColumn<Produto, BigDecimal> vlTotal;
    
    @FXML
    private TableView tabelaProduto = new TableView();
    
    @FXML
    private Button adicionarProduto;
    
    @FXML
    private RadioButton aVista;
    
    @FXML
    private RadioButton aPrazo;
    
    @FXML
    private ComboBox parcelas;
    
    @FXML
    private ComboBox diaVencimento;
    
    @FXML
    private Label valorTotal;
    
    @FXML
    private Button btnExcluir;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Button btnSalvar;
    
    Cliente cliente = new Cliente();
    
    ProdutoVenda produtoVenda = new ProdutoVenda();
    
    ObservableList<Produto> produtoBusca = FXCollections.observableArrayList();
    
    ArrayList<Produto> produtosVenda = new ArrayList<Produto>();
    
    Venda venda = new Venda();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        adicionarProduto.setVisible(false);
        popularParcelas();
        popularDiaVencimento();
    }    
    
    public void setVenda(Venda venda){
        this.venda = venda;
    }
    
    @FXML
    private void buscarCliente(ActionEvent action) {
        
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes = clienteDAO.buscarCliente("");

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
                preencherDadosCliente();
                adicionarProduto.setVisible(true);
            }else{
                Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
                confirmacao.setTitle("Buscar Cliente");
                confirmacao.setHeaderText("Nenhum cliente encontrado.\nPor favor, refaça sua pesquisa!");
                confirmacao.showAndWait();
            }
        }
    }

    @FXML
    private void salvarVenda(ActionEvent action){
        VendaDAO vendaDAO = new VendaDAO();
        if(this.venda == null){
            this.venda = new Venda();
        }
        
        if(produtosVenda != null && produtosVenda.size() > 0 && this.cliente != null){
            
            if(venda.getIdVenda() == 0){
                getAtributos();
                vendaDAO.salvarVenda(venda);
                Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
                confirmacao.setTitle("Salvar Venda");
                confirmacao.setHeaderText("Venda Cadastrada com Sucesso!");
                confirmacao.showAndWait();
                limparCampos();
            }else{
                getAtributos();
                vendaDAO.atualizarVenda(venda);
                Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
                confirmacao.setTitle("Atualizar Venda");
                confirmacao.setHeaderText("Venda Atualizada com Sucesso!");
                confirmacao.showAndWait();
                limparCampos();
            }
        }
    }
    
    private boolean getAtributos(){
        this.venda.setDataVenda(LocalDate.now());
        this.venda.setDiaVencimento(Integer.parseInt(diaVencimento.getValue().toString()));
        if(aVista.isSelected()){
            this.venda.setPagamentoAVista(true);
            this.venda.setNumeroParcelas(1);
        }else{
            this.venda.setPagamentoParcelado(true);
            this.venda.setNumeroParcelas(Integer.parseInt(this.parcelas.getValue().toString()));
        }
        this.venda.setProdutos(produtosVenda);
        this.venda.setCliente(cliente);
        String valor = valorTotal.getText().substring(2);
        BigDecimal valorInformado = new BigDecimal(valor); //.replace(",", ".")
        venda.setValor_total(valorInformado);
            
        return true;
    }
    
    private void limparCampos(){
        this.aPrazo.setSelected(false);
        this.aVista.setSelected(true);
        this.cliente = null;
        this.labelCodigo.setText("");
        this.labelCpf.setText("");
        this.labelNome.setText("");
        this.produtosVenda = null;
        this.valorTotal.setText("");
        this.parcelas.getSelectionModel().selectFirst();
        this.diaVencimento.getSelectionModel().selectFirst();
        produtoBusca.removeAll(produtoBusca);
    }
    
    @FXML
    private void adicionarProduto(ActionEvent action) throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdicionarProduto.fxml"));
            Parent root = (Parent) loader.load();
            AdicionarProdutoController adicionarProdutoController = loader.getController();
            Scene alert = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(alert);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            adicionarProdutoController.setProdutoVenda(produtosVenda);
            this.produtosVenda = adicionarProdutoController.getProdutoVenda();
            stage.showAndWait();
            popularTabela();
            popularValorTotal();
    }
    
    @FXML
    private void popularTabela(){
        if(produtosVenda != null){
            idProduto.setCellValueFactory(new PropertyValueFactory("idProduto"));
            descricao.setCellValueFactory(new PropertyValueFactory("descricao"));
            quantidade.setCellValueFactory(new PropertyValueFactory("quantidade"));
            vlUnit.setCellValueFactory(new PropertyValueFactory("valorUnitario"));
            vlTotal.setCellValueFactory(new PropertyValueFactory("valorTotal"));
            //formatarCodigoProduto();
            formatarValorUnitario();
            formatarValorTotalPorProduto();
            tabelaProduto.setItems(produtoBusca);
            tabelaProduto.getItems().setAll(produtosVenda);
        }
    }
    
    private void preencherDadosCliente(){
        labelCodigo.setText(String.valueOf(cliente.getId()));
        labelCpf.setText(cliente.getCpf());
        labelNome.setText(cliente.getNome());
    }
    
    private void popularValorTotal(){
        BigDecimal valorTT = new BigDecimal(0);
        for (Produto produto : produtoBusca) {
            valorTT = valorTT.add(produto.getValorTotal());
        }
        valorTotal.setText("R$" + valorTT);
    }
    
    private void formatarValorTotalPorProduto(){
        vlTotal.setCellFactory(column -> {
            return new TableCell<Produto, BigDecimal>(){
                
                @Override
                protected void updateItem(BigDecimal item, boolean empty){
                    super.updateItem(item, empty);
                    if (item == null){
                        
                    }else{
                        setText("R$" + item);
                    }
                }
            };
        });
    }
    
    private void formatarValorUnitario(){
        vlUnit.setCellFactory(column -> {
            return new TableCell<Produto, BigDecimal>(){
                
                @Override
                protected void updateItem(BigDecimal item, boolean empty){
                    super.updateItem(item, empty);
                    if (item == null){
                        
                    }else{
                        setText("R$" + item);
                    }
                }
            };
        });
    }
    
    private void popularParcelas(){
        ObservableList<Integer> parcelas = FXCollections.observableArrayList();
        parcelas.add(1);
        parcelas.add(2);
        parcelas.add(3);
        parcelas.add(4);
        parcelas.add(5);
        parcelas.add(6);
        parcelas.add(7);
        parcelas.add(8);
        parcelas.add(9);
        parcelas.add(10);
        parcelas.add(11);
        parcelas.add(12);
        
        this.parcelas.setItems(parcelas);
        this.parcelas.getSelectionModel().selectFirst();
    }
    
    private void popularDiaVencimento(){
        ObservableList<Integer> dias = FXCollections.observableArrayList();
        dias.add(1);
        dias.add(2);
        dias.add(3);
        dias.add(4);
        dias.add(5);
        dias.add(6);
        dias.add(7);
        dias.add(8);
        dias.add(9);
        dias.add(10);
        dias.add(11);
        dias.add(12);
        dias.add(13);
        dias.add(14);
        dias.add(15);
        dias.add(16);
        dias.add(17);
        dias.add(18);
        dias.add(19);
        dias.add(20);
        dias.add(22);
        dias.add(22);
        dias.add(23);
        dias.add(24);
        dias.add(25);
        dias.add(26);
        dias.add(27);
        dias.add(28);
        dias.add(29);
        dias.add(30);
        
        this.diaVencimento.setItems(dias);
        this.diaVencimento.getSelectionModel().selectFirst();
    }
        /**private void formatarCodigoProduto() {
        idProduto.setCellFactory(column -> {
            return new TableCell<Produto, ProdutoVenda>() {

                @Override
                protected void updateItem(ProdutoVenda item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {

                    } else {
                        setText(String.valueOf(item.getProduto().getIdProduto()));
                    }
                }
            };
        });
    }*/
    
     /**private void formatarDescricaoProduto() {
        descricao.setCellFactory(column -> {
            return new TableCell<Produto, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {

                    } else {
                        setText(String.valueOf(item.getProduto().getDescricao()));
                    }
                }
            };
        });
    }*/
}