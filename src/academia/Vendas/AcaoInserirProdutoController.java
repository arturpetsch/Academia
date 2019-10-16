/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Vendas;

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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class AcaoInserirProdutoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button botaoSim;

    @FXML
    private Button botaoNao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    protected void acaoBotaoNao(ActionEvent event) throws IOException {
        Stage stage = (Stage) botaoNao.getScene().getWindow();
        stage.close();

    }

    @FXML
    protected void acaoBotaoSim(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdicionarProduto.fxml"));
        Parent root = (Parent) loader.load();
        AdicionarProdutoController adicionarProdutoController = loader.getController();
        Scene alert = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(alert);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}
