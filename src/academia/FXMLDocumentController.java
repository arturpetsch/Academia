/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia;

import academia_DAO.UsuarioDAO;
import classes_academia.ContaReceber;
import classes_academia.Mensalidade;
import classes_academia.ParametroUsuario;
import classes_academia.Usuario;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import negocio_academia.Negocio_Cliente;
import negocio_academia.Negocio_Financeiro;

/**
 *
 * @author Artur
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField idNomeUsuario;
    @FXML
    private PasswordField idSenhaUsuario;
    @FXML
    private Button btnEntrarSistema;
    @FXML
    private Button btnEsqueceuSenha;
    @FXML
    private Button botaoNovoUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gerarMensalidade();
        idNomeUsuario.textProperty().addListener((ov, oldValue, newValue) -> {
        idNomeUsuario.setText(newValue.toUpperCase());
        });
        
    }

    private void gerarMensalidade(){
     int hoje = LocalDate.now().getDayOfMonth();

        Negocio_Cliente negocioCliente = new Negocio_Cliente();

        List<Mensalidade> mensalidadesAtivas = new ArrayList<>();
        mensalidadesAtivas = negocioCliente.getMensalidadeHoje();

        for (Mensalidade mensalidadesAtiva : mensalidadesAtivas) {
            ContaReceber contaReceber = new ContaReceber();
            contaReceber.setCliente(mensalidadesAtiva.getCliente());
            contaReceber.setValor(mensalidadesAtiva.getValor());

            /*LocalDate dataVenc = LocalDate.now();
            dataVenc = dataVenc.plusMonths(1);
            dataVenc.plusMonths(1);*/
            Month mesVenc = LocalDate.now().getMonth().plus(1);
            int anoVenc;
            
            if (mesVenc == Month.JANUARY) {
                anoVenc = LocalDate.now().getYear() + 1;
            } else {
                anoVenc = LocalDate.now().getYear();
            }

            LocalDate dataVenc;
            if(mensalidadesAtiva.getDiaVencimento() > 28 && mesVenc.equals(Month.FEBRUARY)){
                dataVenc = LocalDate.of(anoVenc, mesVenc, 28);
            
            }else{
                dataVenc = LocalDate.of(anoVenc, mesVenc, mensalidadesAtiva.getDiaVencimento());
            }
            
            contaReceber.setDataVencimento(dataVenc);
            contaReceber.setDescricao(new SimpleStringProperty("Mensalidade"));
            contaReceber.setTipoConta(new SimpleBooleanProperty(true));
            contaReceber.setMensalidade(mensalidadesAtiva);

            Negocio_Financeiro negocio_Financeiro = new Negocio_Financeiro();
                        
            if (!negocio_Financeiro.validarMensalidadeCliente(dataVenc, contaReceber.getCliente())) {
                if (negocio_Financeiro.inserirNovaConta(contaReceber)) {
                    System.err.println("Inserido");
                } else {
                    System.err.println("Nao inserido");
                }
            } else {
                System.err.println("validação funcionou");
            }
        }
    }
        
    @FXML
    protected void botaoEntrarSistema(ActionEvent evento) throws IOException {
        if (validarCampos()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            if (usuarioDAO.verificarLogin(idNomeUsuario.getText(), idSenhaUsuario.getText())) {
                Parent telaPrincipal = FXMLLoader.load(getClass().getResource("FXMLtelaPrincipal.fxml"));
                inserirDadosUsuario();
                Scene scene = new Scene(telaPrincipal);
                Stage stage = (Stage) ((Node) evento.getSource()).getScene().getWindow();
                stage.setTitle("Gerenciador de Academia");
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.show();
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Erro ao entrar no sistema");
                alerta.setHeaderText("Usuário e Senha Informados Inválidos.\nPor Favor, verifique!");
                alerta.showAndWait();
            }
        }
    }

    private void inserirDadosUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getUsuarioPorNome(idNomeUsuario.getText());
        ParametroUsuario parametroUsuario = new ParametroUsuario(usuario);
    }

    private boolean validarCampos() {
        String e = "";

        if (idNomeUsuario.getText().isEmpty()) {
            e = "Nome Usuario Inválido!\n";
        }

        if (idSenhaUsuario.getText().isEmpty()) {
            e = "Senha Inválida!";
        }

        if (e.length() == 0) {
            return true;
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Erro ao entrar no sistema");
            alerta.setHeaderText(e);
            alerta.showAndWait();
            return false;
        }
    }

    @FXML
    public void AcaoBotaEnter() {
       
        btnEntrarSistema.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode() == KeyCode.ENTER) {
                 if (validarCampos()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            if (usuarioDAO.verificarLogin(idNomeUsuario.getText(), idSenhaUsuario.getText())) {
                Parent telaPrincipal;
                try {
                    telaPrincipal = FXMLLoader.load(getClass().getResource("FXMLtelaPrincipal.fxml"));
               
                inserirDadosUsuario();
                Scene scene = new Scene(telaPrincipal);
                Stage stage = (Stage) ((Node) evento.getSource()).getScene().getWindow();
                stage.setTitle("Gerenciador de Academia");
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.show();
                 } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Erro ao entrar no sistema");
                alerta.setHeaderText("Usuário e Senha Informados Inválidos.\nPor Favor, verifique!");
                alerta.showAndWait();
            }
        }
            }
        });
    }

    @FXML
    private void recuperarSenha(ActionEvent event) throws IOException{
        Parent telaPrincipal = FXMLLoader.load(getClass().getResource("Usuario/FXMLrecuperarSenha.fxml"));
               
                Scene scene = new Scene(telaPrincipal);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Image icone = new Image(getClass().getResourceAsStream("/academia/icon/gym.png"));
                stage.getIcons().add(icone);
        
                stage.setTitle("Gerenciador de Academia");
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.show();
    }
    
    
}
