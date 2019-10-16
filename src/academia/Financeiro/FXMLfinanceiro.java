/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Financeiro;

import academia.BarraDeProgressoController;
import classes_academia.ParametroUsuario;
import classes_academia.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/academia/barraDeProgresso.fxml"));
        Parent root1 = (Parent) loader.load();
        BarraDeProgressoController barraDeProgressoController = loader.getController();
        Scene alert1 = new Scene(root1);
        Image icone1 = new Image(getClass().getResourceAsStream("/academia/icon/refresh.png"));
        Stage stage1 = new Stage();
        stage1.getIcons().add(icone1);

        stage1.setScene(alert1);
        stage1.setResizable(false);
        stage1.centerOnScreen();
        stage1.initModality(Modality.APPLICATION_MODAL);

        stage1.show();
        
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("FXMLcontasReceber.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoFinanceiro.getChildren().setAll(pane);
        stage1.close();
    }
    
    @FXML
    private void mostrarOpcaoContaPagar(ActionEvent event) throws IOException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/academia/barraDeProgresso.fxml"));
        Parent root1 = (Parent) loader.load();
        BarraDeProgressoController barraDeProgressoController = loader.getController();
        Scene alert1 = new Scene(root1);
        Image icone1 = new Image(getClass().getResourceAsStream("/academia/icon/refresh.png"));
        Stage stage1 = new Stage();
        stage1.getIcons().add(icone1);

        stage1.setScene(alert1);
        stage1.setResizable(false);
        stage1.centerOnScreen();
        stage1.initModality(Modality.APPLICATION_MODAL);

        stage1.show();
        
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("FXMLcontaPagar.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoFinanceiro.getChildren().setAll(pane);
        stage1.close();
    }
    
    @FXML
    private void mostrarOpcaoMovimentacaoFinanceira(ActionEvent event) throws IOException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/academia/barraDeProgresso.fxml"));
        Parent root1 = (Parent) loader.load();
        BarraDeProgressoController barraDeProgressoController = loader.getController();
        Scene alert1 = new Scene(root1);
        Image icone1 = new Image(getClass().getResourceAsStream("/academia/icon/refresh.png"));
        Stage stage1 = new Stage();
        stage1.getIcons().add(icone1);

        stage1.setScene(alert1);
        stage1.setResizable(false);
        stage1.centerOnScreen();
        stage1.initModality(Modality.APPLICATION_MODAL);

        stage1.show();
        
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("FXMLmovimentacaoFinanceira.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoFinanceiro.getChildren().setAll(pane);
        stage1.close();
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
