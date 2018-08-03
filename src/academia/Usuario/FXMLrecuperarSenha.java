/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio_academia.Negocio_Usuario;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class FXMLrecuperarSenha implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField nomeRecuperar;

    @FXML
    private PasswordField respostaRecuperar;

    @FXML
    private PasswordField novaSenhaRecuperar;

    @FXML
    private PasswordField confirmarNovaSenhaRecuperar;

    @FXML
    private Button botaoAvancar;

    @FXML
    private Button botaoCancelarRecuperar;

    @FXML
    private Button botaoSalvarRecuperar;

    @FXML
    private Label labelConfirmarSenha;

    @FXML
    private Label labelNovaSenha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        botaoSalvarRecuperar.setVisible(false);
        confirmarNovaSenhaRecuperar.setVisible(false);
        novaSenhaRecuperar.setVisible(false);
        labelConfirmarSenha.setVisible(false);
        labelNovaSenha.setVisible(false);
    }

    @FXML
    private void avancarRecuperacao(ActionEvent action) {
        if (!nomeRecuperar.getText().isEmpty() && !respostaRecuperar.getText().isEmpty()) {
            Negocio_Usuario negocio_Usuario = new Negocio_Usuario();
            if (negocio_Usuario.verificacaoRespostaSecreta(respostaRecuperar.getText(), nomeRecuperar.getText())) {
                botaoSalvarRecuperar.setVisible(true);
                confirmarNovaSenhaRecuperar.setVisible(true);
                novaSenhaRecuperar.setVisible(true);
                labelConfirmarSenha.setVisible(true);
                labelNovaSenha.setVisible(true);
                botaoAvancar.setVisible(false);
            }
        }
    }

    @FXML
    private void alterarSenha(ActionEvent action) throws IOException {
        Negocio_Usuario negocio_Usuario = new Negocio_Usuario();
        if (!confirmarNovaSenhaRecuperar.getText().isEmpty() && !novaSenhaRecuperar.getText().isEmpty()) {
            if (confirmarNovaSenhaRecuperar.getText().equals(novaSenhaRecuperar.getText())) {
                if (negocio_Usuario.alterarSenha(novaSenhaRecuperar.getText(), nomeRecuperar.getText())) {
                    Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
                    confirmacao.setTitle("Senha Alterada Com Sucesso!");
                    confirmacao.setHeaderText("Senha alterada com Sucesso!");
                    confirmacao.showAndWait();
                    limparCampos();
                    chamarTelaLogin(action);
                } else {
                    Alert erro = new Alert(Alert.AlertType.ERROR);
                    erro.setTitle("Erro ao alterar a senha!");
                    erro.setHeaderText("Erro ao alterar senha!");
                    erro.showAndWait();
                }
            }
        }
    }

    private void limparCampos() {
        nomeRecuperar.setText("");
        respostaRecuperar.setText("");
        botaoAvancar.setVisible(true);
        botaoSalvarRecuperar.setVisible(false);
        confirmarNovaSenhaRecuperar.setVisible(false);
        novaSenhaRecuperar.setVisible(false);
        labelConfirmarSenha.setVisible(false);
        labelNovaSenha.setVisible(false);
        botaoAvancar.setVisible(false);
    }

    @FXML
    private void cancelar(ActionEvent action) throws IOException {
        limparCampos();
        chamarTelaLogin(action);
    }

    @FXML
    private void chamarTelaLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("academia/FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Entrar no Sistema");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }
}
