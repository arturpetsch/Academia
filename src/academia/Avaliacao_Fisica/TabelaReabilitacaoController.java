/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Avaliacao_Fisica;

import classes_academia.AvaliacaoFisica;
import classes_academia.AvaliacaoReabilitacao;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class TabelaReabilitacaoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TableView tabelaDataAvaliacao = new TableView();

    @FXML
    private TableColumn<AvaliacaoReabilitacao, LocalDate> dataAvaliacaoTabela = new TableColumn();
    
    @FXML
    private TableColumn<AvaliacaoReabilitacao, Integer> idAvaliacaoTabela = new TableColumn();
    
    ArrayList<AvaliacaoReabilitacao> avaliacoes = new ArrayList<AvaliacaoReabilitacao>();

    ObservableList<AvaliacaoReabilitacao> avaliacaoBusca = FXCollections.observableArrayList();
    
    AvaliacaoReabilitacao avaliacaoReabilitacao = new AvaliacaoReabilitacao();
    
    @FXML
    private Button botaoNovaAvaliacao;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    tabelaDataAvaliacao.setRowFactory(tv -> {
            TableRow<AvaliacaoReabilitacao> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        selecionaAvaliacao(row.getItem());
                    } catch (IOException ex) {
                        Logger.getLogger(TabelaReabilitacaoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });

            return row;
        });
       
       formatarDataViagem();
    }    
    
     /**
     * Método que insere os dados do arraylist na tabela.
     */
    @FXML
    protected void popularTabela() {
        if (avaliacoes != null) {
            
            dataAvaliacaoTabela.setCellValueFactory(new PropertyValueFactory("data_hora"));
            idAvaliacaoTabela.setCellValueFactory(new PropertyValueFactory("idAvaliacaoReabilitacao"));
            
            tabelaDataAvaliacao.setItems(avaliacaoBusca);
            tabelaDataAvaliacao.getItems().setAll(avaliacoes);

        }
    }
    
     /**
     * Metodo que recebe um arraylist de viagens oriundos da tela Viagens.
     *
     * @param viagens
     */
    public void setAvaliacao(ArrayList<AvaliacaoReabilitacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
        popularTabela();
    }
    
     /**
     * Método que recebe a avaliação selecionada da tabela e retorna a tela de
     * viagem.
     */
    private void selecionaAvaliacao(AvaliacaoReabilitacao avaliacaoReabilitacao) throws IOException {
        this.avaliacaoReabilitacao = avaliacaoReabilitacao;
         Stage stage = (Stage) tabelaDataAvaliacao.getScene().getWindow();
        stage.close();
        getAvaliacaoSelecionada();
    }
    
    public AvaliacaoReabilitacao getAvaliacaoSelecionada(){
        return this.avaliacaoReabilitacao;
    }
      
    @FXML
    public void novaAvaliacao(ActionEvent action){
         Stage stage = (Stage) tabelaDataAvaliacao.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void formatarDataViagem() {
        dataAvaliacaoTabela.setCellFactory(column -> {
            return new TableCell<AvaliacaoReabilitacao, LocalDate>() {

                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {

                    } else {
                        setText(formatarData(item));
                    }
                }
            };
        });
    }

 private String formatarData(LocalDate data) {
        final String DATE_PATTERN = "dd/MM/yyyy";
        final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

        return DATE_FORMATTER.format(data);
    }
}
