/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Avaliacao_Fisica;

import academia.BarraDeProgressoController;
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
public class AvaliacaoMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public  AnchorPane idPainelDireitoCliente;
    
    @FXML
    private Button botaoAvaliacaoFisica;
    
    @FXML
    private Button botaoAvaliacaoReabilitacao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void mostrarOpcaoAvaliacaoFisica(ActionEvent action) throws IOException{ //quando clica no botao cadastrar;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/academia/barraDeProgresso.fxml"));
        Parent root1 = (Parent) loader.load();
        BarraDeProgressoController barraDeProgressoController = loader.getController();
        Scene alert1 = new Scene(root1);
        Stage stage1 = new Stage();
        Image icone1 = new Image(getClass().getResourceAsStream("/academia/icon/refresh.png"));
        stage1.getIcons().add(icone1);

        stage1.setScene(alert1);
        stage1.setResizable(false);
        stage1.centerOnScreen();
        stage1.initModality(Modality.APPLICATION_MODAL);

        stage1.show();

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("avalicaoFisica.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoCliente.getChildren().setAll(pane);
        stage1.close();
    }
    
    @FXML
    private void mostrarOpcaoAvaliacaoReabilitacao(ActionEvent action) throws IOException{ //quando clica no botao buscar;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/academia/barraDeProgresso.fxml"));
        Parent root1 = (Parent) loader.load();
        BarraDeProgressoController barraDeProgressoController = loader.getController();
        Scene alert1 = new Scene(root1);
        Stage stage1 = new Stage();
        Image icone1 = new Image(getClass().getResourceAsStream("/academia/icon/refresh.png"));
        stage1.getIcons().add(icone1);

        stage1.setScene(alert1);
        stage1.setResizable(false);
        stage1.centerOnScreen();
        stage1.initModality(Modality.APPLICATION_MODAL);

        stage1.show();

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("avaliacaoReabilitacao.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoCliente.getChildren().setAll(pane);
        stage1.close();
    }
    
  
    @FXML
    protected void alterarCorDoBotaoAvaliacaoFisica(){
         botaoAvaliacaoFisica.setStyle("-fx-background-color: white; -fx-text-fill: black");
         botaoAvaliacaoFisica.setTextFill(Color.BLACK);
         voltarCorOriginalBotaoAvaliacaoReabilitacao();
    }
    
    @FXML
    protected void voltarCorOriginalBotaoAvaliacaoFisica(){
         botaoAvaliacaoFisica.setStyle("-fx-background-color: #001235");
         botaoAvaliacaoFisica.setTextFill(Color.WHITE);
    }
    
    @FXML
    protected void alterarCorDoBotaoAvaliacaoReabilitacao(){
        botaoAvaliacaoReabilitacao.setStyle("-fx-background-color: white; -fx-text-fill: black");
        botaoAvaliacaoReabilitacao.setTextFill(Color.BLACK);
        voltarCorOriginalBotaoAvaliacaoFisica();
    }
    
    @FXML
    protected void voltarCorOriginalBotaoAvaliacaoReabilitacao(){
         botaoAvaliacaoReabilitacao.setStyle("-fx-background-color: #001235");
         botaoAvaliacaoReabilitacao.setTextFill(Color.WHITE);
    }
}


