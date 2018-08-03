/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Financeiro;

import classes_academia.ContaPagar;
import classes_academia.ContaPagarParametro;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import negocio_academia.Negocio_Financeiro;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class FXMLalertController implements Initializable {

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
        Negocio_Financeiro negocioFinanceiro = new Negocio_Financeiro();
        ContaPagarParametro contaPagarParametro = new ContaPagarParametro();
        ContaPagar cp = ContaPagarParametro.getContaPagar();
        if (negocioFinanceiro.deletarContaPagar(cp)) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Confirmação");
            alerta.setHeaderText("Conta Pagar " + cp.getDescricao() + " com valor de R$" + cp.getValor() + "0 excluída com sucesso!");
            alerta.show();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Falha ao excluir a conta a pagar " + cp.getDescricao() + " com valor de R$" + cp.getValor() + "0!");
            alerta.show();
        }
    }
}
