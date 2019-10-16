/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Produtos;

import academia_DAO.ProdutoDAO;
import classes_academia.Produto;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class ProdutoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button botaoBuscar;
    
    @FXML
    private TextField nomePesquisar;
    
    @FXML
    private TextField descricao;
    
    @FXML
    private TextField fornecedor;
    
    @FXML
    private TextField valorUnitario;
    
    @FXML
    private ComboBox unidadeMedida;
    
    @FXML
    private DatePicker dataCompra;
    
    @FXML
    private DatePicker dataVencimento;
    
    @FXML
    private TextField quantidade;
    
    @FXML
    private TextField lote;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Button btnExcluir;
    
    @FXML
    private Button btnSalvar;
    
    Produto produto = new Produto();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        preencherUnidadeMedida();
        dataCompra.setValue(LocalDate.now());
    }    
    
    @FXML
    private void buscarProduto(ActionEvent action){
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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("tabelaProdutos.fxml"));
            Parent root = (Parent) loader.load();
            TabelaProdutosController tabelaProdutosController = loader.getController();
            Scene alert = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(alert);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            tabelaProdutosController.setProduto(produtos);
            stage.showAndWait();
            limparCampos();
            this.produto = tabelaProdutosController.getProdutoSelecionado();
                if (this.produto != null && this.produto.getIdProduto() > 0) {
                    popularDadosProduto();
                }
            /**} else {
            Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
            confirmacao.setTitle("Buscar Produto");
            confirmacao.setHeaderText("Nenhum produto encontrado.\nPor favor, refaça sua pesquisa!");
            confirmacao.showAndWait();
            }*/
        }
    
    
    private void popularDadosProduto(){
        this.descricao.setText(produto.getDescricao());
        this.fornecedor.setText(produto.getFornecedor());
        this.valorUnitario.setText(String.valueOf(produto.getValorUnitario()).replace(".", ","));
        this.unidadeMedida.getSelectionModel().select(produto.getUnidade_medida());
        this.dataCompra.setValue(produto.getDataCompra());
        this.dataVencimento.setValue(produto.getDataVencimento());
        this.quantidade.setText(String.valueOf(produto.getQuantidade()));
        this.lote.setText(produto.getLote());
    }
    
    private void limparCampos(){
        nomePesquisar.setText("");
        descricao.setText("");
        fornecedor.setText("");
        valorUnitario.setText("");
        unidadeMedida.getSelectionModel().select(0);
        dataCompra.setValue(LocalDate.now());
        dataVencimento.setValue(LocalDate.now());
        quantidade.setText("");
        lote.setText("");
    }
    
    /**
     * Método utilizado pelo botao salvar. Salva um novo produto ou as
     * alterações feitas em um já existente.
     *
     * @param action
     */
    @FXML
    protected void salvarProduto(ActionEvent action) {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        if (this.produto == null) {
            this.produto = new Produto();
        }

        if (produto.getIdProduto() == 0 && getAtributos()) {
            produtoDAO.salvarProduto(produto);
            Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
            confirmacao.setTitle("Salvar Produto");
            confirmacao.setHeaderText("Produto Cadastrado com Sucesso!");
            confirmacao.showAndWait();
            limparCampos();
        } else if (getAtributos() && produto.getIdProduto() > 0) {
            if (produtoDAO.atualizarProduto(produto)) {
                Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
                confirmacao.setTitle("Salvar Produto");
                confirmacao.setHeaderText("Produto Atualizado com Sucesso!");
                confirmacao.showAndWait();
                limparCampos();
            }
        }
    }
    
    @FXML
    private void cancelarOperacao(ActionEvent action) {
        limparCampos();
    }
    
    /**
     * Metodo utilizado pelo botao Excluir que executa a exclusão do produto
     * selecionado.
     *
     * @param action
     */
    @FXML
    private void deletarProduto(ActionEvent action) throws IOException {
        if (this.produto.getIdProduto() > 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmacaoAcaoProduto.fxml"));
            Parent root = (Parent) loader.load();
            ConfirmacaoAcaoProdutoController confirmacaoAcaoProdutoController = loader.getController();
            Scene alert = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(alert);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            confirmacaoAcaoProdutoController.setProduto(produto);
            stage.showAndWait();
            limparCampos();
        }
    }
    
    private boolean getAtributos(){
        if (verificarCamposVazios()) {
            this.produto.setDescricao(descricao.getText());
            this.produto.setFornecedor(fornecedor.getText());
            String valor = valorUnitario.getText();
            BigDecimal valorInformado = new BigDecimal(valor.replace(",", "."));
            
            this.produto.setValorUnitario(valorInformado);
            this.produto.setUnidade_medida(unidadeMedida.getValue().toString());
            this.produto.setDataCompra(dataCompra.getValue());
            this.produto.setDataVencimento(dataVencimento.getValue());
            this.produto.setQuantidade(Double.parseDouble(quantidade.getText()));
            this.produto.setLote(lote.getText());

            return true;
        }
        return false;
    }
    
    /**
     * Método que valida se os campos estão vazios.
     */
    private boolean verificarCamposVazios() {
        boolean retorno = true;
        int contador = 0;
        if (descricao.getText().isEmpty()) {
            descricao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            descricao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (fornecedor.getText().isEmpty()) {
            fornecedor.setStyle("-fx-border-color:red");
            contador++;
        } else {
            fornecedor.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (valorUnitario.getText().isEmpty()) {
            valorUnitario.setStyle("-fx-border-color:red");
            contador++;
        } else {
            valorUnitario.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (dataCompra.getValue() == null) {
            dataCompra.setStyle("-fx-border-color:red");
            contador++;
        } else {
            dataCompra.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (dataVencimento.getValue() == null || dataVencimento.getValue().isBefore(LocalDate.now())) {
            dataVencimento.setStyle("-fx-border-color:red");
            contador++;
        } else {
            dataVencimento.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }
        
        if (quantidade.getText().isEmpty()) {
            quantidade.setStyle("-fx-border-color:red");
            contador++;
        } else {
            quantidade.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        } 
        
        if (lote.getText().isEmpty()) {
            lote.setStyle("-fx-border-color:red");
            contador++;
        } else {
            lote.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }
        
        if (contador <= -7) {
            retorno = true;
        } else {
            retorno = false;
        }

        return retorno;

    }
    
    private void preencherUnidadeMedida(){
        ObservableList<String> unidades = FXCollections.observableArrayList();
        unidades.add("UN");
        unidades.add("KG");
        unidades.add("LT");
        unidades.add("MT");
        unidades.add("BD");
        unidades.add("OUTRO");
        
        unidadeMedida.setItems(unidades);
        unidadeMedida.getSelectionModel().select(0);

    }
}