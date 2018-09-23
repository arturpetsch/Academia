/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Financeiro;

import classes_academia.ContaReceber;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import negocio_academia.Negocio_Financeiro;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class FXMLalertaContaReceberController implements Initializable {

   @FXML
    private Button botaoSim;

    @FXML
    private Button botaoNao;

    ContaReceber contaReceber = new ContaReceber();
    
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
        
        if (negocioFinanceiro.deletarContaReceber(contaReceber)) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Confirmação");
            alerta.setHeaderText("Conta Receber \nCliente: " + contaReceber.getCliente().getNome() + "  valor de R$" + contaReceber.getValor() + "0 excluída com sucesso!");
            alerta.showAndWait();
            Stage stage = (Stage) botaoSim.getScene().getWindow();
            stage.close();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Falha ao excluir a conta a receber ");
            alerta.show();
        }
    }
    
    public void setContaReceber(ContaReceber contaReceber){
        this.contaReceber = contaReceber;
    }
    
}

