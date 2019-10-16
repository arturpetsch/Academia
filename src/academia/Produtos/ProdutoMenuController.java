/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Produtos;

import academia.BarraDeProgressoController;
import academia.Vendas.OpcoesVendaController;
import classes_academia.Venda;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class ProdutoMenuController implements Initializable {

   @FXML
    public AnchorPane idPainelDireitoCliente;
    
    @FXML
    private Button botaoProduto;
    
    @FXML
    private Button botaoVenda;
    
    private Venda venda = new Venda();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void mostrarProduto(ActionEvent action) throws IOException { //quando clica no botao cadastrar;
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
        fXMLLoader.setLocation(getClass().getResource("produto.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoCliente.getChildren().setAll(pane);
        stage1.close();
    }
    
    @FXML
    public void mostrarVenda(ActionEvent action) throws IOException{ //quando clica no botao buscar;
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
        fXMLLoader.setLocation(getClass().getResource("/academia/Vendas/opcoesVenda.fxml"));
        //AnchorPane pane = (AnchorPane) fXMLLoader.load();
        //idPainelDireitoCliente.getChildren().setAll(pane);
        Parent root2 = (Parent) fXMLLoader.load();
        OpcoesVendaController opcoesVendaController = fXMLLoader.getController();
        
        Scene alert2 = new Scene(root2);
        Stage stage2 = new Stage();
        Image icone2 = new Image(getClass().getResourceAsStream("/academia/icon/refresh.png"));
        stage2.getIcons().add(icone2);

        stage2.setScene(alert2);
        stage2.setResizable(false);
        stage2.centerOnScreen();
        stage2.initModality(Modality.APPLICATION_MODAL);
        this.venda = opcoesVendaController.getVenda();        
        stage2.showAndWait();
        abrirTelaVenda();
        stage1.close();
    }
    
    @FXML
    private void abrirTelaVenda() throws IOException{
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
        fXMLLoader.setLocation(getClass().getResource("/academia/Vendas/vendas.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        idPainelDireitoCliente.getChildren().setAll(pane);
        

        stage1.close();
    }
    
    @FXML
    protected void alterarCorDoBotaoProduto(){
         botaoProduto.setStyle("-fx-background-color: white; -fx-text-fill: black");
         botaoProduto.setTextFill(Color.BLACK);
         voltarCorOriginalBotaoVenda();
    }
    
    @FXML
    protected void voltarCorOriginalBotaoProduto(){
         botaoProduto.setStyle("-fx-background-color: #001235");
         botaoProduto.setTextFill(Color.WHITE);
    }
    
    @FXML
    protected void alterarCorDoBotaoVenda(){
        botaoVenda.setStyle("-fx-background-color: white; -fx-text-fill: black");
        botaoVenda.setTextFill(Color.BLACK);
        voltarCorOriginalBotaoProduto();
    }
    
    @FXML
    protected void voltarCorOriginalBotaoVenda(){
         botaoVenda.setStyle("-fx-background-color: #001235");
         botaoVenda.setTextFill(Color.WHITE);
    }

   
}