/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Financeiro;

import classes_academia.ParametroUsuario;
import classes_academia.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class FXMLfinanceiro implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button botaoEntradaFinanceiro;
    
    @FXML
    private Button botaoSaidaFinanceiro;
    
    @FXML
    private Button botaoMovFinan;
    
    @FXML
    private AnchorPane idPainelDireitoFinanceiro;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ParametroUsuario parametroUsuario = new ParametroUsuario();
        Usuario usuario = parametroUsuario.getUsuario();
        if(usuario.getTipoUser().equals("ADMIN")){
            botaoMovFinan.setVisible(true);
        }else{
            botaoMovFinan.setVisible(false);
        }
    }    
    
    @FXML
    private void mostrarOpcaoContaReceber(ActionEvent event) throws IOException{
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("FXMLcontasReceber.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoFinanceiro.getChildren().setAll(pane);
    }
    
    @FXML
    private void mostrarOpcaoContaPagar(ActionEvent event) throws IOException{
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("FXMLcontaPagar.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoFinanceiro.getChildren().setAll(pane);
    }
    
    @FXML
    private void mostrarOpcaoMovimentacaoFinanceira(ActionEvent event) throws IOException{
        
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("FXMLmovimentacaoFinanceira.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoFinanceiro.getChildren().setAll(pane);
        
    }
    
    @FXML
    protected void alterarCorDoBotaoMovFinan(){
         botaoMovFinan.setStyle("-fx-background-color: white; -fx-text-fill: black");
         botaoMovFinan.setTextFill(Color.BLACK);
         voltarCorOriginalBotaoFinanceiro();
         voltarCorOriginalCR();
    }
    
    @FXML
    protected void voltarCorOriginalMovFinan(){
         botaoMovFinan.setStyle("-fx-background-color: #001235");
         botaoMovFinan.setTextFill(Color.WHITE);
    }
    
    
    @FXML
    protected void alterarCorDoBotaoCR(){
         botaoEntradaFinanceiro.setStyle("-fx-background-color: white; -fx-text-fill: black");
         botaoEntradaFinanceiro.setTextFill(Color.BLACK);
         voltarCorOriginalBotaoFinanceiro();
         voltarCorOriginalMovFinan();
    }
    
    @FXML
    protected void voltarCorOriginalCR(){
         botaoEntradaFinanceiro.setStyle("-fx-background-color: #001235");
         botaoEntradaFinanceiro.setTextFill(Color.WHITE);
    }
    
    @FXML
    protected void alterarCorDoBotaoFinanceiro(){
        botaoSaidaFinanceiro.setStyle("-fx-background-color: white; -fx-text-fill: black");
         botaoSaidaFinanceiro.setTextFill(Color.BLACK);
         voltarCorOriginalCR();
         voltarCorOriginalMovFinan();
    }
    
    @FXML
    protected void voltarCorOriginalBotaoFinanceiro(){
        botaoSaidaFinanceiro.setStyle("-fx-background-color:  #001235");
         botaoSaidaFinanceiro.setTextFill(Color.WHITE);
    }
}
