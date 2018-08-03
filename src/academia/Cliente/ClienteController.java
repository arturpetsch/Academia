/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Cliente;

import classes_academia.Cliente;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class ClienteController implements Initializable {

    /**
     * Classe que controla toda a parte gráfica de clientes, e também é responsável pela chamada de métodos da parte
     * lógica do negócio.
     */
    
    @FXML
    public  AnchorPane idPainelDireitoCliente;
    
    @FXML
    private Button botaoCadastrarCliente;
    
    @FXML
    private Button botaoBuscarCliente;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    @FXML
    private void mostrarOpcaoCadastrar(ActionEvent action) throws IOException{ //quando clica no botao cadastrar;
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("FXMLcadastrarCliente.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoCliente.getChildren().setAll(pane);
    }
    
    @FXML
    private void mostrarOpcaoBuscar(ActionEvent action) throws IOException{ //quando clica no botao buscar;
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("FXMLbuscarCliente.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoCliente.getChildren().setAll(pane);
    }
    
  
    @FXML
    protected void alterarCorDoBotaoCliente(){
         botaoCadastrarCliente.setStyle("-fx-background-color: white; -fx-text-fill: black");
         botaoCadastrarCliente.setTextFill(Color.BLACK);
         voltarCorOriginalBotaoBuscar();
    }
    
    @FXML
    protected void voltarCorOriginalBotaoCliente(){
         botaoCadastrarCliente.setStyle("-fx-background-color: #001235");
         botaoCadastrarCliente.setTextFill(Color.WHITE);
    }
    
    @FXML
    protected void alterarCorDoBotaoBuscar(){
         botaoBuscarCliente.setStyle("-fx-background-color: white; -fx-text-fill: black");
         botaoBuscarCliente.setTextFill(Color.BLACK);
         voltarCorOriginalBotaoCliente();
    }
    
    @FXML
    protected void voltarCorOriginalBotaoBuscar(){
         botaoBuscarCliente.setStyle("-fx-background-color: #001235");
         botaoBuscarCliente.setTextFill(Color.WHITE);
    }
}
