/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Produtos;

import academia_DAO.ProdutoDAO;
import classes_academia.ParametroUsuario;
import classes_academia.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class ConfirmacaoAcaoProdutoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Produto produto = new Produto();
    
    @FXML
    private Label labelDescricao;

    @FXML
    private Label labelValorUnitario;

    @FXML
    private Button botaoSim;

    @FXML
    private Button botaoNao;
    
    @FXML
    private PasswordField senha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        senha.requestFocus();
    }    
    
    /**
     * Método que confirma a ação selecionada.
     *
     * @param action
     */
    @FXML
    private void confirmarAcao(ActionEvent action){
       ProdutoDAO produtoDAO = new ProdutoDAO();
       ParametroUsuario parametroUsuario = new ParametroUsuario();
       
       if(senha.getText().equalsIgnoreCase(parametroUsuario.getUsuario().getSenha())){ 
       if(this.produto != null){
            if (produtoDAO.deletarProduto(produto)) {
                Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
                confirmacao.setTitle("Deletar Produto");
                confirmacao.setHeaderText("Produto Excluído com Sucesso!");
                confirmacao.showAndWait();
                Stage stage = (Stage) botaoSim.getScene().getWindow();
                stage.close();
            }
        }
       }else{
           Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setTitle("Deletar Produto");
                erro.setHeaderText("Senha do Usuário Incorreta!");
                erro.showAndWait();
                
       }
    } 
    
     /**
     * Método que cancela a ação e volta a tela de Veículo.
     *
     * @param action
     */
    @FXML
    private void cancelarAcao(ActionEvent action) {
        Stage stage = (Stage) botaoNao.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Método que recebe o objeto veiculo da tela Veículo.
     * @param veiculo
     */
    public void setProduto(Produto produto){
        this.produto = produto;
        popularCampos();
    }
    
    /**
     * Método que insere os dados do veiculo nos labels modelo e placa.
     */
    private void popularCampos(){
        labelDescricao.setText((produto.getDescricao()));
        labelValorUnitario.setText("R$" + produto.getValorUnitario().toString());
    }
}
