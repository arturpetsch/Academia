/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Usuario;

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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import negocio_academia.Negocio_Usuario;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class FXMLalterarSenhaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PasswordField confirmarNovaSenha;

    @FXML
    private PasswordField novaSenhaAlterar;

    @FXML
    private PasswordField respostaAlterar;

    @FXML
    private Button botaoCancelarAlteracao;

    @FXML
    private Button botaoSalvarAlteracao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
     private void alterarSenha(ActionEvent action){
         ParametroUsuario parametroUsuario = new ParametroUsuario();
         Usuario usuario = parametroUsuario.getUsuario();
         Negocio_Usuario negocio_Usuario = new Negocio_Usuario();
        
        if(validarCampos()){
            if(negocio_Usuario.verificacaoRespostaSecreta(respostaAlterar.getText(), usuario)){
                if(confirmarNovaSenha.getText().equals(novaSenhaAlterar.getText())){
                    if(negocio_Usuario.alterarSenha(novaSenhaAlterar.getText(), usuario)){
                        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
                        confirmacao.setTitle("Senha Alterada Com Sucesso!");
                        confirmacao.setHeaderText("Senha alterada com Sucesso!");
                        confirmacao.showAndWait();
                        limparCampos();
                    }else{
                        Alert erro = new Alert(Alert.AlertType.ERROR);
                        erro.setTitle("Erro ao alterar a senha!");
                        erro.setHeaderText("Erro ao alterar senha!");
                        erro.showAndWait();
                    }
                }
        }else{
                Alert resposta = new Alert(Alert.AlertType.ERROR);
                resposta.setTitle("Resposta Incorreta");
                resposta.setHeaderText("Resposta informada inválida!");
                resposta.showAndWait();
            }
            }
     }

     private void limparCampos(){
        respostaAlterar.setText("");
        novaSenhaAlterar.setText("");
        confirmarNovaSenha.setText("");
     }
     
    private boolean validarCampos() {
        String e = "";

        if (novaSenhaAlterar.getText().isEmpty()) {
            e += "Senha Informada Inválida!\n";
        }

        if (confirmarNovaSenha.getText().isEmpty()) {
            e += "Confirmação de Senha Inválida\n";
        }

        if (respostaAlterar.getText().isEmpty()) {
            e += "Resposta Secreta Inválida!\n";
        }

        if (e.length() == 0) {
            return true;
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Campos Inválidos");
            alerta.setHeaderText("Por favor, corrija os campos inválidos!");
            alerta.setContentText(e);
            alerta.showAndWait();
            return false;
        }
    }
    
     @FXML
    public void AcaoBotaEnter() {
        botaoSalvarAlteracao.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode() == KeyCode.ENTER) {
                ActionEvent event = null;
                alterarSenha(event);
                System.out.println("AQUI NO ENTER");
            }
        });
    }

    @FXML
    public void AcaoBotaoCancelar() {
        botaoCancelarAlteracao.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode() == KeyCode.ENTER) {
                ActionEvent event = null;
                cancelarAlteracao(event);
            }
        });
    }
    
    @FXML
    private void cancelarAlteracao(ActionEvent action){
        limparCampos();
    }
}
