/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Usuario;

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
public class FXMLcontroleUsuario implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button botaoTrocarSenha;
    
    @FXML
    private Button botaoNovoUsuario;
    
    @FXML
    public AnchorPane idPainelDireitoUsuario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void mostrarOpcaoNovoUsuario(ActionEvent action) throws IOException{ //quando clica no botao cadastrar;
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
        fXMLLoader.setLocation(getClass().getResource("FXMLnovoUsuario.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoUsuario.getChildren().setAll(pane);
        stage1.close();
    }
    
    @FXML
    private void mostrarOpcaoAlterarSenha(ActionEvent action) throws IOException{ //quando clica no botao cadastrar;
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
        fXMLLoader.setLocation(getClass().getResource("FXMLalterarSenha.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoUsuario.getChildren().setAll(pane);
        stage1.close();
    }
    
    @FXML
    protected void alterarCorDoBotaoNovoUsuario(){
         botaoNovoUsuario.setStyle("-fx-background-color: white; -fx-text-fill: black");
         botaoNovoUsuario.setTextFill(Color.BLACK);
         voltarCorOriginalTrocarSenha();
    }
    
    @FXML
    protected void voltarCorOriginalBotaoNovoUsuario(){
         botaoNovoUsuario.setStyle("-fx-background-color: #001235");
         botaoNovoUsuario.setTextFill(Color.WHITE);
    }
    
    @FXML
    protected void alterarCorDoBotaoTrocarSenha(){
         botaoTrocarSenha.setStyle("-fx-background-color: white; -fx-text-fill: black");
         botaoTrocarSenha.setTextFill(Color.BLACK);
         voltarCorOriginalBotaoNovoUsuario();
    }
    
    @FXML
    protected void voltarCorOriginalTrocarSenha(){
         botaoTrocarSenha.setStyle("-fx-background-color: #001235");
         botaoTrocarSenha.setTextFill(Color.WHITE);
    }
}
