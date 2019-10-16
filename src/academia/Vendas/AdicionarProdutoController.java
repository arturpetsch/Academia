/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Vendas;

import academia.Produtos.ProdutoController;
import academia.Produtos.TabelaProdutosController;
import academia_DAO.ProdutoDAO;
import classes_academia.Produto;
import classes_academia.ProdutoVenda;
import classes_academia.Venda;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class AdicionarProdutoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField nomePesquisar;

    @FXML
    private Button botaoBuscar;

    @FXML
    private Label codigo;

    @FXML
    private Label descricao;

    @FXML
    private Label dataVencimento;

    @FXML
    private Label unidadeMedida;

    @FXML
    private Label valorUnitario;

    @FXML
    private Label lote;

    @FXML
    private Label estoque;

    @FXML
    private TextField quantidade;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnSalvar;

    Produto produto = new Produto();

    ProdutoVenda produtoVenda = new ProdutoVenda();

    ArrayList<Produto> produtosVenda = new ArrayList<Produto>();

    @FXML
    private Button btnNovoProduto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void buscarProduto(ActionEvent action) {
        String produtoInformado = nomePesquisar.getText();

        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        produtos = produtoDAO.buscarProduto(produtoInformado);

        try {
            mostrarProdutos(produtos);
        } catch (IOException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarProdutos(ArrayList<Produto> produtos) throws IOException {
        //if (!produtos.isEmpty()) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("tabelaProdutosVenda.fxml"));
        Parent root = (Parent) loader.load();
        TabelaProdutosVendaController tabelaProdutosVendaController = loader.getController();
        Scene alert = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(alert);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        tabelaProdutosVendaController.setProduto(produtos);
        stage.showAndWait();
        //limparCampos();
        this.produto = tabelaProdutosVendaController.getProdutoSelecionado();
        if (this.produto != null && this.produto.getIdProduto() > 0) {
            popularDadosProduto();
        }
        /**
         * } else { Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
         * confirmacao.setTitle("Buscar Produto");
         * confirmacao.setHeaderText("Nenhum produto encontrado.\nPor favor,
         * refaça sua pesquisa!"); confirmacao.showAndWait(); }
         */
    }

    @FXML
    private void finalizarPedido(ActionEvent action) throws IOException {
        if(produtosVenda.size()>0){
            Stage stage = (Stage) botaoBuscar.getScene().getWindow();
            stage.close();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro ao finalizar o pedido!");
            alerta.setHeaderText("Nenhum item adicionado!!");
            alerta.showAndWait();
            Stage stage = (Stage) botaoBuscar.getScene().getWindow();
            stage.close();
        }
    }    

    @FXML
    private void adicionarProduto(ActionEvent action) throws IOException {
        if (verificaQuantidadeEstoque()) {
            Double valorEstoqueAnterior = produto.getQuantidade();
            Double qtde = Double.parseDouble(quantidade.getText());
            produto.setQuantidade(qtde);
            atualizarValorTotalPorProduto();
            
            produtosVenda.add(produto);
            
            Double valorEstoque = Double.parseDouble(quantidade.getText()) ;
            valorEstoque = valorEstoqueAnterior - valorEstoque;
            
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.atualizarEstoqueProduto(produto.getIdProduto(), valorEstoque);
            
            
            limparCampos();
            
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro ao adicionar produto!");
            alerta.setHeaderText("Valor incorreto ou estoque insuficiente!!");
            alerta.showAndWait();

        }
    }
    
    private void atualizarValorTotalPorProduto(){
        Double valorUnitario = Double.parseDouble(this.valorUnitario.getText().replace(",", "."));
        Double quantidadeUnit = Double.parseDouble(quantidade.getText());
        Double vlTotalUnit = valorUnitario * quantidadeUnit;
        BigDecimal vlTotalFinal = new BigDecimal(vlTotalUnit);
        vlTotalFinal = vlTotalFinal.setScale(2, RoundingMode.HALF_UP);
        this.produto.setValorTotal(vlTotalFinal);
        
    }

    public ArrayList<Produto> getProdutoVenda() {
        return this.produtosVenda;
    }

    public void setProdutoVenda(ArrayList<Produto> produtosVenda){
        this.produtosVenda = produtosVenda;
    }
    
    private void popularDadosProduto() {
        this.descricao.setText(produto.getDescricao());
        this.valorUnitario.setText(String.valueOf(produto.getValorUnitario()).replace(".", ","));
        this.unidadeMedida.setText(produto.getUnidade_medida());
        this.dataVencimento.setText(produto.getDataVencimento().toString());
        this.estoque.setText(String.valueOf(produto.getQuantidade()));
        this.lote.setText(produto.getLote());
        this.codigo.setText(String.valueOf(produto.getIdProduto()));
        this.produtoVenda.setProduto(produto);

    }

    private boolean verificaQuantidadeEstoque() {
        if (!quantidade.getText().isEmpty()) {
            double qtde = Double.parseDouble(quantidade.getText());
            double estq = Double.parseDouble(estoque.getText());
            if (estq > 0) {
                if (qtde > 0 && qtde <= estq) {
                    return true;
                }
            }
        }
        return false;
    }

    @FXML
    private void cancelarAcao(ActionEvent action) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    private void limparCampos(){
        this.descricao.setText("");
        this.valorUnitario.setText("");
        this.unidadeMedida.setText("");
        this.dataVencimento.setText("");
        this.estoque.setText("");
        this.lote.setText("");
        this.codigo.setText("");
        this.quantidade.setText("");
    }
}
