/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Vendas;

import academia.Produtos.TabelaProdutosController;
import classes_academia.Produto;
import java.io.IOException;
import java.math.BigDecimal;
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
public class TabelaProdutosVendaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Produto> tabelaProdutos = new TableView<>();
    
    @FXML
    private TableColumn<Produto, Integer> idProdutoTabela = new TableColumn();
    
    @FXML
    private TableColumn<Produto, String> descricaoTabela = new TableColumn();
    
    @FXML
    private TableColumn<Produto, Double> quantidadeTabela = new TableColumn();
    
    @FXML
    private TableColumn<Produto, BigDecimal> valorUnitarioTabela = new TableColumn();
    
     ArrayList<Produto> produtos = new ArrayList<Produto>();

    ObservableList<Produto> produtoBusca = FXCollections.observableArrayList();
    
    Produto produto = new Produto();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    tabelaProdutos.setRowFactory(tv -> {
            TableRow<Produto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        selecionaProduto(row.getItem());
                    } catch (IOException ex) {
                        Logger.getLogger(TabelaProdutosController.class.getName()).log(Level.SEVERE, null, ex);
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
        if (produtos != null) {
            
            idProdutoTabela.setCellValueFactory(new PropertyValueFactory("idProduto"));
            descricaoTabela.setCellValueFactory(new PropertyValueFactory("descricao"));
            quantidadeTabela.setCellValueFactory(new PropertyValueFactory("quantidade"));
            valorUnitarioTabela.setCellValueFactory(new PropertyValueFactory("valorUnitario"));
            
            tabelaProdutos.setItems(produtoBusca);
            tabelaProdutos.getItems().setAll(produtos);

        }
    }
    
    /**
     * Metodo que recebe um arraylist de produtos oriundos da tela Produtos.
     *
     * @param produtos
     */
    public void setProduto(ArrayList<Produto> produtos) {
        this.produtos = produtos;
        popularTabela();
    }
    
     /**
     * Método que recebe a avaliação selecionada da tabela e retorna a tela de
     * viagem.
     */
    private void selecionaProduto(Produto produto) throws IOException {
        this.produto = produto;
         Stage stage = (Stage) tabelaProdutos.getScene().getWindow();
        stage.close();
        getProdutoSelecionado();
    }
    
    public Produto getProdutoSelecionado(){
        return this.produto;
    }    
    
    
}
