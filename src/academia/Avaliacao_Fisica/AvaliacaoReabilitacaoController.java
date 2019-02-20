/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Avaliacao_Fisica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class AvaliacaoReabilitacaoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button botaoCancelarReabilitacao;
    
    @FXML
    private Button botaoSalvarReabilitacao;
    
    @FXML
    private Button botaoExcluirReabilitacao;
    
    @FXML
    private TextArea tratamentosAnteriores;
    
    @FXML
    private TextArea medicamentos;
    
    @FXML
    private TextArea exercicios;
    
    @FXML
    private TextArea descricao;
    
    @FXML
    private TextField nomeClienteBusca;
    
    @FXML
    private Button botaoBuscar;
    
    @FXML
    private Label nomeClienteReabilitacao;
    
    @FXML
    private Label dataReabilitacao;
    
    @FXML
    private Label idadeClienteReabilitacao;
    
    @FXML
    private Label dataNascimentoReabilitacao;
    
    @FXML
    private Label sexoReabilitacao;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
