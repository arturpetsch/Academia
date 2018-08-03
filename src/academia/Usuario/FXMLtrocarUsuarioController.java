/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Usuario;

import academia.PrincipalController;
import academia_DAO.UsuarioDAO;
import classes_academia.ParametroUsuario;
import classes_academia.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class FXMLtrocarUsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button botaoLogin;
    
    @FXML
    private Button botaoCancelar;
    
    @FXML
    private TextField txtNomeUsuario;
    
    @FXML
    private PasswordField txtSenhaUsuario;
    
    @FXML
    private ImageView imagemTrocarUsuario;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       Image icone = new Image(getClass().getResourceAsStream("/academia/icon/change_user.png"));
       imagemTrocarUsuario.setImage(icone);
    }    
 
    @FXML
    private void botaoLogin(ActionEvent action){
         if (validarCampos()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            if (usuarioDAO.verificarLogin(txtNomeUsuario.getText(), txtSenhaUsuario.getText())) {
                inserirDadosUsuario();
                Stage stage = (Stage) botaoCancelar.getScene().getWindow();
                stage.close();
                
            }
    }
    }
    
    private void inserirDadosUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getUsuarioPorNome(txtNomeUsuario.getText());
        ParametroUsuario parametroUsuario = new ParametroUsuario(usuario);
    }
    
    @FXML
    private void botaoCancelar(ActionEvent event){
        Stage stage = (Stage) botaoCancelar.getScene().getWindow();
        stage.close();
    }
    
    private boolean validarCampos() {
        String e = "";

        if (txtNomeUsuario.getText().isEmpty()) {
            e = "Nome Usuario Inválido!\n";
        }

        if (txtSenhaUsuario.getText().isEmpty()) {
            e = "Senha Inválida!";
        }

        if (e.length() == 0) {
            return true;
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Erro ao trocar usuário no sistema");
            alerta.setHeaderText(e);
            alerta.showAndWait();
            return false;
        }
    }
    
//    @FXML
//    protected void botaoLoginSelecionado(){
//        botaoLogin.setStyle("-fx-border-color: white" + " -fx-border-width: 1" + " -fx-background-color:  #4aa4ff;" + " -fx-border-radius: 3;");
//        tirarSelecaoBotaoCancelar();
//    }
//    
//    @FXML
//    protected void botaoCancelarSelecionado(){
//        botaoCancelar.setStyle("-fx-border-color: white;" + " -fx-border-width: 1;" + " -fx-background-color:  #F13333;" + " -fx-border-radius: 3;");
//        tirarSelecaoBotaoLogin();
//    }
//    
//    @FXML
//    private void tirarSelecaoBotaoLogin(){
//        botaoLogin.setStyle("-fx-border-width: 0" + " -fx-background-color:   #4aa4ff");
//        botaoLogin.setTextFill(Color.WHITE);
//    }
//    
//    @FXML
//    private void tirarSelecaoBotaoCancelar(){
//        botaoCancelar.setStyle("-fx-border-width: 0" + " -fx-background-color:  #F13333");
//        botaoCancelar.setTextFill(Color.WHITE);
//    
//    }
}
