/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Usuario;

import classes_academia.Usuario;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import negocio_academia.Negocio_Usuario;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class NovoUsuario implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField idNomeUser;

    @FXML
    private PasswordField idSenha;

    @FXML
    private PasswordField idConfirmarSenha;

    @FXML
    private ComboBox tipoUsuario;

    @FXML
    private ComboBox idPerguntaSecreta;

    @FXML
    private PasswordField respostaSecreta;

    @FXML
    private Button botaoConfirmar;

    @FXML
    private Button botaoCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> tipoUser = FXCollections.observableArrayList();
        tipoUser.add("ADMIN");
        tipoUser.add("USUARIO");

        tipoUsuario.setItems(tipoUser);
        tipoUsuario.getSelectionModel().select(0);

        ObservableList<String> resposta = FXCollections.observableArrayList();
        resposta.add("Qual é o nome do seu animal de estimação?");
        resposta.add("Qual é a sua comida preferida?");
        resposta.add("Qual sua cor preferida?");
        resposta.add("Cidade natal de sua mãe?");
        idPerguntaSecreta.setItems(resposta);
        idPerguntaSecreta.getSelectionModel().select(0);
    }
    
    @FXML
    protected void salvarUsuario(ActionEvent event){
        if(validarCampos()){
            if(idSenha.getText().equals(idConfirmarSenha.getText())){
                Usuario usuario = new Usuario();
                usuario.setNome(idNomeUser.getText());
                usuario.setSenha(idSenha.getText());
                usuario.setTipoUser((String) tipoUsuario.getValue());
                usuario.setRespostaSecreta(respostaSecreta.getText());
                
                Negocio_Usuario negocio_Usuario = new Negocio_Usuario();
                if(negocio_Usuario.adicionarUsuario(usuario)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Usuario Adicionado Com Sucesso!");
                    alert.setHeaderText("Usuario Cadastrado Com Sucesso!");
                    alert.showAndWait();
                }
                limparCampos();
            }else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Senha Inválida");
                alerta.setHeaderText("Confirmação de senha falhou. Por favor, verifique!");
                alerta.showAndWait();
              }
        }   
        
    }
    
    @FXML
    protected void cancelar(ActionEvent action){
        limparCampos();
    }
    
    private boolean validarCampos(){
        String e = "";
        
        if(idNomeUser.getText().isEmpty()){
            e += "Nome Usuário Informado Inválido\n";
        }
        
        if(idSenha.getText().isEmpty()){
            e += "Senha Informada Inválida!\n";
        }
        
        if(idConfirmarSenha.getText().isEmpty()){
            e += "Confirmação de Senha Inválida\n";
        }
        
        if(respostaSecreta.getText().isEmpty()){
            e += "Resposta Secreta Inválida!\n";
        }
        
        if(e.length() == 0){
            return true;
        }else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Campos Inválidos");
            alerta.setHeaderText("Por favor, corrija os campos inválidos!");
            alerta.setContentText(e);
            alerta.showAndWait();
            return false;
        }
    }
    
    private void limparCampos(){
        idNomeUser.setText("");
        idSenha.setText("");
        idPerguntaSecreta.getSelectionModel().select(0);
        idConfirmarSenha.setText("");
        tipoUsuario.getSelectionModel().select(0);
        respostaSecreta.setText("");
    }
    
    @FXML
    public void AcaoBotaEnter() {
        botaoConfirmar.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode() == KeyCode.ENTER) {
                ActionEvent event = null;
                salvarUsuario(event);
            }
        });
    }

    @FXML
    public void AcaoBotaoCancelar() {
        botaoCancelar.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode() == KeyCode.ENTER) {
                ActionEvent event = null;
                cancelar(event);
            }
        });
    }
}
