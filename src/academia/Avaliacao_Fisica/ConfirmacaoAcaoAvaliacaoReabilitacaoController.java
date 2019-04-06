/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Avaliacao_Fisica;

import academia_DAO.AvaliacaoReabilitacaoDAO;
import classes_academia.AvaliacaoReabilitacao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class ConfirmacaoAcaoAvaliacaoReabilitacaoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label labelDataAvaliacao;

    @FXML
    private Label labelNomeCliente;

    @FXML
    private Button botaoSim;

    @FXML
    private Button botaoNao;
    
    AvaliacaoReabilitacao avaliacaoReabilitacao = new AvaliacaoReabilitacao();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     /**
     * Método que confirma a ação selecionada.
     *
     * @param action
     */
    @FXML
    private void confirmarAcao(ActionEvent action){
       AvaliacaoReabilitacaoDAO avaliacaoReabilitacaoDAO = new AvaliacaoReabilitacaoDAO();
        if(this.avaliacaoReabilitacao != null){
            if (avaliacaoReabilitacaoDAO.deletarAvaliacaoReabilitacao(avaliacaoReabilitacao)) {
                Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
                confirmacao.setTitle("Deletar Avaliação Reabilitação");
                confirmacao.setHeaderText("Avaliação Excluída com Sucesso!");
                confirmacao.showAndWait();
                Stage stage = (Stage) botaoSim.getScene().getWindow();
                stage.close();
            }
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
    public void setAvaliacaoReabilitacao(AvaliacaoReabilitacao avaliacaoReabilitacao){
        this.avaliacaoReabilitacao = avaliacaoReabilitacao;
        popularCampos();
    }
    
    /**
     * Método que insere os dados do veiculo nos labels modelo e placa.
     */
    private void popularCampos(){
        labelDataAvaliacao.setText((avaliacaoReabilitacao.getData_hora().toString()));
        labelNomeCliente.setText(avaliacaoReabilitacao.getCliente().getNome());
    }
}
