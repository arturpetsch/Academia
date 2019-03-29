/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Avaliacao_Fisica;

import academia_DAO.AvaliacaoFisicaDAO;
import academia_DAO.ClienteDAO;
import classes_academia.AvaliacaoFisica;
import classes_academia.Cliente;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class AvalicaoFisicaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //atributos informações da tela
    @FXML
    private TextField nomeClienteBusca;

    @FXML
    private Button botaoBuscar;

    @FXML
    private Label nomeClienteAvalicao;

    @FXML
    private Label dataAvaliacao;

    @FXML
    private Label idadeClienteAvaliacao;

    @FXML
    private Label dataNascimentoAvaliacao;

    @FXML
    private Label sexoAvalicao;

    @FXML
    private TextField pesoAvaliacao;

    @FXML
    private TextField alturaAvaliacao;

    //atributos perimetros.
    @FXML
    private TextField toraxAvaliacao;

    @FXML
    private TextField toraxAvaliacao2;

    @FXML
    private TextField abdominalAvaliacao;

    @FXML
    private TextField cinturaAvaliacao;

    @FXML
    private TextField quadrilAvaliacao;

    @FXML
    private TextField bissepsEsquerdoAvaliacao;

    @FXML
    private TextField bissepsDireitoAvaliacao;

    @FXML
    private TextField pernaEsquerdaAvaliacao;

    @FXML
    private TextField pernaDireitaAvaliacao;

    @FXML
    private TextField coxaEsquerdaAvaliacao;

    @FXML
    private TextField coxaDireitaAvaliacao;

    @FXML
    private TextField percGorduraAvaliacao;

    @FXML
    private TextField percLivreGorduraAvaliacao;

    @FXML
    private TextField percIdealGorduraAvaliacao;

    @FXML
    private TextField percGorduraSobraAvaliacao;

    @FXML
    private TextField imcAvaliacao;

    @FXML
    private TextField rcQAvaliacao;

    @FXML
    private TextField pesoGorduraAvaliacao;

    @FXML
    private TextField pesoMagroAvaliacao;

    @FXML
    private TextField pesoExcedenteAvaliacao;

    //pressao arterial
    @FXML
    private TextField fcRepousoAvaliacao;

    @FXML
    private TextField fcMaximoAvaliacao;

    @FXML
    private TextField fcResAvaliacao;

    //zona alvo de treino
    @FXML
    private TextField fctInferiorAvaliacao;

    @FXML
    private TextField fctSuperiorAvaliacao;

    @FXML
    private TextField percIntInferiorAvaliacao;

    @FXML
    private TextField percIntSuperiorAvaliacao;

    //anamenese
    @FXML
    private RadioButton simDC;

    @FXML
    private RadioButton naoDC;

    @FXML
    private RadioButton simDiabetes;

    @FXML
    private RadioButton naoDiabetes;

    @FXML
    private RadioButton simHipertensao;

    @FXML
    private RadioButton naoHipertensao;

    @FXML
    private RadioButton simFumante;

    @FXML
    private RadioButton naoFumante;

    @FXML
    private ComboBox consumoBebida;

    @FXML
    private ComboBox doencaCardiacaHistoricoFamiliar;

    @FXML
    private ComboBox diabetesHistoricoFamiliar;

    @FXML
    private ComboBox hipertensaoHistoricoFamiliar;

    @FXML
    private ComboBox obesidadeHistoricoFamiliar;

    @FXML
    private RadioButton simAtividadeFisica;

    @FXML
    private RadioButton naoAtividadeFisica;

    @FXML
    private TextField modadlidadeAtividadeFisica;

    @FXML
    private TextField frequenciaAtividadeFisica;

    @FXML
    private ComboBox horaDuracaoAtividadeFisica;

    @FXML
    private ComboBox minutoDuracaoAtividadeFisica;

    @FXML
    private TextArea medicamentos;

    @FXML
    private TextArea outros;

    @FXML
    private TextArea objetivos;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnCancelar;

    AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();

    Cliente cliente = new Cliente();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        popularMinutoDuracaoAtividadeFisica();
        popularHoraDuracaoAtividadeFisica();
        popularConsumoBebidaAlcoolica();
        popularHistoricoFamiliar();
        
        percGorduraAvaliacao.setOnKeyPressed(t ->{
              final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
              final KeyCombination TAB = new KeyCodeCombination(KeyCode.TAB);
              if (ENTER.match(t) || TAB.match(t)) {
              if(!percGorduraAvaliacao.getText().isEmpty()){
                  double valor = Double.parseDouble(percGorduraAvaliacao.getText().replace(",", "."));
                  valor = 100 - valor;
                  
                  BigDecimal valorTotal = new BigDecimal(valor);
                  valorTotal = valorTotal.setScale(1, RoundingMode.HALF_UP);
                  
                  percLivreGorduraAvaliacao.setText(String.valueOf(valorTotal).replace(".", ","));
                  
                  double peso = Double.parseDouble(pesoAvaliacao.getText().replace(",", "."));
                  double pesoGord = Double.parseDouble(percGorduraAvaliacao.getText().replace(",", "."));
                  pesoGord = peso * pesoGord;
                  pesoGord = pesoGord / 100;
                  
                  BigDecimal pesoGordur = new BigDecimal(pesoGord);
                  pesoGordur = pesoGordur.setScale(1, RoundingMode.HALF_UP);
                  
                  pesoGorduraAvaliacao.setText(String.valueOf(pesoGordur).replace(".", ","));
              }
            }
        });
        
        percIdealGorduraAvaliacao.setOnKeyPressed(t ->{
              final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
              final KeyCombination TAB = new KeyCodeCombination(KeyCode.TAB);
              if (ENTER.match(t) || TAB.match(t)) {
              if(!percIdealGorduraAvaliacao.getText().isEmpty() && !percGorduraAvaliacao.getText().isEmpty()){
                  double valor = Double.parseDouble(percIdealGorduraAvaliacao.getText().replace(",", "."));
                  double valor2 = Double.parseDouble(percGorduraAvaliacao.getText().replace(",", "."));
                  valor = valor2 - valor;
                  
                  BigDecimal valorTotal = new BigDecimal(valor);
                  valorTotal = valorTotal.setScale(1, RoundingMode.HALF_UP);
                  
                  percGorduraSobraAvaliacao.setText(String.valueOf(valorTotal).replace(".", ","));
              }
            }
        });
        
        alturaAvaliacao.setOnKeyPressed(t ->{
              final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
              final KeyCombination TAB = new KeyCodeCombination(KeyCode.TAB);
              if (ENTER.match(t) || TAB.match(t)) {
              if(!alturaAvaliacao.getText().isEmpty() && !pesoAvaliacao.getText().isEmpty()){
                  double altura = Double.parseDouble(alturaAvaliacao.getText().replace(",", "."));
                  double peso = Double.parseDouble(pesoAvaliacao.getText().replace(",", "."));
                  double valor =  (altura * altura);
                  valor = peso / valor;
                  
                  BigDecimal valorTotal = new BigDecimal(valor);
                  valorTotal = valorTotal.setScale(1, RoundingMode.HALF_UP);
                  imcAvaliacao.setText(String.valueOf(valorTotal).replace(".", ","));
              }
            }
        });
        
        quadrilAvaliacao.setOnKeyPressed(t ->{
              final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
              final KeyCombination TAB = new KeyCodeCombination(KeyCode.TAB);
              if (ENTER.match(t) || TAB.match(t)) {
              if(!quadrilAvaliacao.getText().isEmpty() && !cinturaAvaliacao.getText().isEmpty()){
                  double quadril = Double.parseDouble(abdominalAvaliacao.getText().replace(",", "."));
                  double cintura = Double.parseDouble(cinturaAvaliacao.getText().replace(",", "."));
                  double valor =  cintura / quadril;
                  
                  BigDecimal valorTotal = new BigDecimal(valor);
                  valorTotal = valorTotal.setScale(1, RoundingMode.HALF_UP);
                  rcQAvaliacao.setText(String.valueOf(valorTotal).replace(".", ","));
              }
            }
        });
        
        
        percLivreGorduraAvaliacao.setOnKeyPressed(t ->{
              final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
              final KeyCombination TAB = new KeyCodeCombination(KeyCode.TAB);
              if (ENTER.match(t) || TAB.match(t)) {
              if(!percLivreGorduraAvaliacao.getText().isEmpty() && !pesoAvaliacao.getText().isEmpty()){
                  double percLivre = Double.parseDouble(percLivreGorduraAvaliacao.getText().replace(",", "."));
                  double peso = Double.parseDouble(pesoAvaliacao.getText().replace(",", "."));
                  double valor =  (peso * percLivre);
                  valor = valor / 100;
                  
                  BigDecimal valorTotal = new BigDecimal(valor);
                  valorTotal = valorTotal.setScale(1, RoundingMode.HALF_UP);
                  pesoMagroAvaliacao.setText(String.valueOf(valorTotal).replace(".", ","));
              }
            }
        });
        
        percGorduraSobraAvaliacao.setOnKeyPressed(t ->{
              final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
              final KeyCombination TAB = new KeyCodeCombination(KeyCode.TAB);
              if (ENTER.match(t) || TAB.match(t)) {
              if(!percGorduraSobraAvaliacao.getText().isEmpty() && !pesoAvaliacao.getText().isEmpty()){
                  double percLivre = Double.parseDouble(percGorduraSobraAvaliacao.getText().replace(",", "."));
                  double peso = Double.parseDouble(pesoAvaliacao.getText().replace(",", "."));
                  double valor =  (peso * percLivre);
                  valor = valor / 100;
                  
                  BigDecimal valorTotal = new BigDecimal(valor);
                  valorTotal = valorTotal.setScale(1, RoundingMode.HALF_UP);
                  pesoExcedenteAvaliacao.setText(String.valueOf(valorTotal).replace(".", ","));
              }
            }
        });
        
        fcRepousoAvaliacao.setOnKeyPressed(t ->{
              final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
              final KeyCombination TAB = new KeyCodeCombination(KeyCode.TAB);
              if (ENTER.match(t) || TAB.match(t)) {
              if(!fcRepousoAvaliacao.getText().isEmpty()){
                  double valor = 220 - Double.parseDouble(idadeClienteAvaliacao.getText());
                  
                  BigDecimal valorTotal = new BigDecimal(valor);
                  valorTotal = valorTotal.setScale(1, RoundingMode.HALF_UP);
                  fcMaximoAvaliacao.setText(String.valueOf(valorTotal).replace(".", ","));
                  
                  double fcRepouso = Double.parseDouble(fcRepousoAvaliacao.getText().replace(",", "."));
                  double fcMax = Double.parseDouble(fcMaximoAvaliacao.getText().replace(",", "."));
                  double fcRes = fcMax - fcRepouso;
                  
                  BigDecimal fcRES = new BigDecimal(fcRes);
                  fcRES = fcRES.setScale(1, RoundingMode.HALF_UP);
                  fcResAvaliacao.setText(String.valueOf(fcRES).replace(".", ","));
                  
              }
            }
        });
        
        percIntInferiorAvaliacao.setOnKeyPressed(t ->{
              final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
              final KeyCombination TAB = new KeyCodeCombination(KeyCode.TAB);
              if (ENTER.match(t) || TAB.match(t)) {
              if(!percIntInferiorAvaliacao.getText().isEmpty() && !fcResAvaliacao.getText().isEmpty() && !fcRepousoAvaliacao.getText().isEmpty()){
                  double fcRes = Double.parseDouble(fcResAvaliacao.getText().replace(",", "."));
                  double fcRepouso = Double.parseDouble(fcRepousoAvaliacao.getText().replace(",", "."));
                  double percIntInf = Double.parseDouble(percIntInferiorAvaliacao.getText().replace(",", "."));
                  percIntInf = percIntInf /100;
                  double valor =  fcRes * percIntInf;
                  valor = valor + fcRepouso;
                  
                  BigDecimal valorTotal = new BigDecimal(valor);
                  valorTotal = valorTotal.setScale(1, RoundingMode.HALF_UP);
                  fctInferiorAvaliacao.setText(String.valueOf(valorTotal).replace(".", ","));
              }
            }
        });
        
        percIntSuperiorAvaliacao.setOnKeyPressed(t ->{
              final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
              final KeyCombination TAB = new KeyCodeCombination(KeyCode.TAB);
              if (ENTER.match(t) || TAB.match(t)) {
              if(!percIntSuperiorAvaliacao.getText().isEmpty() && !fcResAvaliacao.getText().isEmpty() && !fcRepousoAvaliacao.getText().isEmpty()){
                  double fcRes = Double.parseDouble(fcResAvaliacao.getText().replace(",", "."));
                  double fcRepouso = Double.parseDouble(fcRepousoAvaliacao.getText().replace(",", "."));
                  double percIntInf = Double.parseDouble(percIntSuperiorAvaliacao.getText().replace(",", "."));
                  percIntInf = percIntInf /100;
                  double valor =  fcRes * percIntInf;
                  valor = valor + fcRepouso;
                  
                  BigDecimal valorTotal = new BigDecimal(valor);
                  valorTotal = valorTotal.setScale(1, RoundingMode.HALF_UP);
                  fctSuperiorAvaliacao.setText(String.valueOf(valorTotal).replace(".", ","));
              }
            }
        });
    }

    
    
    /**
     * Metodo utilizado para coletar o nome informado, buscar e retornar uma
     * lista com os clientes.
     *
     * @param action
     */
    @FXML
    private void buscarCliente(ActionEvent action) {
        String nomeInformado = nomeClienteBusca.getText();

        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes = clienteDAO.buscarCliente(nomeInformado);

        try {
            mostrarClientes(clientes);
        } catch (IOException ex) {
            Logger.getLogger(AvalicaoFisicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que mostra a tabela com todos os clientes. Apos selecionado o
     * cliente, é mostrado a tabela de datas de avaliações já realizadas.
     *
     * @param clientes
     */
    private void mostrarClientes(ArrayList<Cliente> clientes) throws IOException {
        if (!clientes.isEmpty()) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("tabelaClientesAvaliacao.fxml"));
            Parent root = (Parent) loader.load();
            TabelaClientesAvaliacaoController tabelaClientesAvaliacaoController = loader.getController();
            Scene alert = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(alert);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            tabelaClientesAvaliacaoController.setCliente(clientes);
            stage.showAndWait();
            this.cliente = tabelaClientesAvaliacaoController.getClienteSelecionado();
            if (this.cliente != null) {
                mostrarAvaliacoes();
            }
        } else {
            Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
            confirmacao.setTitle("Buscar Cliente");
            confirmacao.setHeaderText("Nenhum cliente encontrado.\nPor favor, refaça sua pesquisa!");
            confirmacao.showAndWait();
        }    
    }

    /**
     * Metodo que mostra a tabela com todas as avaliações do cliente selecionado.
     */
    @FXML
    private void mostrarAvaliacoes() throws IOException{
        AvaliacaoFisicaDAO avaliacaoFisicaDAO = new AvaliacaoFisicaDAO();
        
        ArrayList<AvaliacaoFisica> avaliacoes = new ArrayList<AvaliacaoFisica>();
        avaliacoes = avaliacaoFisicaDAO.consultarAvaliacaoPorCliente(cliente);
        
        //if(!avaliacoes.isEmpty()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tabelaAvaliacoes.fxml"));
            Parent root = (Parent) loader.load();
            TabelaAvaliacoesController tabelaAvaliacoesController = loader.getController();
            Scene alert = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(alert);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            tabelaAvaliacoesController.setAvaliacao(avaliacoes);
            stage.showAndWait();
            this.avaliacaoFisica = tabelaAvaliacoesController.getAvaliacaoSelecionada();
            popularDadosCliente();
            if (this.avaliacaoFisica != null && this.avaliacaoFisica.getIdAvaliacaoFisica()>0) {
                popularCamposAvaliacao();
            }
    }
    
     private void popularDadosClienteAvaliacao(){
       nomeClienteAvalicao.setText(cliente.getNome());
        dataAvaliacao.setText(avaliacaoFisica.getData_hora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        dataNascimentoAvaliacao.setText(cliente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        if(cliente.getSexo()){
            sexoAvalicao.setText("Masculino");
        }else{
            sexoAvalicao.setText("Feminino");
        }
        
        long idade = ChronoUnit.YEARS.between(cliente.getDataNascimento(), LocalDate.now());
        idadeClienteAvaliacao.setText(String.valueOf(idade));
    }
     
    private void popularDadosCliente(){
        nomeClienteAvalicao.setText(cliente.getNome());
        dataAvaliacao.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        dataNascimentoAvaliacao.setText(cliente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        if(cliente.getSexo()){
            sexoAvalicao.setText("Masculino");
        }else{
            sexoAvalicao.setText("Feminino");
        }
        
        long idade = ChronoUnit.YEARS.between(cliente.getDataNascimento(), LocalDate.now());
        idadeClienteAvaliacao.setText(String.valueOf(idade));
    }
    
    /**
     * Método que limpa o(s) campo(s) na tela de Veiculo.
     */
    private void limparCampos() {
        nomeClienteBusca.setText("");
        nomeClienteAvalicao.setText("");
        dataAvaliacao.setText("");
        dataNascimentoAvaliacao.setText("");
        idadeClienteAvaliacao.setText("");
        sexoAvalicao.setText("");
        pesoAvaliacao.setText("");
        alturaAvaliacao.setText("");
        //perimetro
        toraxAvaliacao.setText("");
        toraxAvaliacao2.setText("");
        abdominalAvaliacao.setText("");
        cinturaAvaliacao.setText("");
        quadrilAvaliacao.setText("");
        bissepsEsquerdoAvaliacao.setText("");
        bissepsDireitoAvaliacao.setText("");
        coxaDireitaAvaliacao.setText("");
        coxaEsquerdaAvaliacao.setText("");
        pernaEsquerdaAvaliacao.setText("");
        pernaDireitaAvaliacao.setText("");
        percGorduraAvaliacao.setText("");
        percGorduraSobraAvaliacao.setText("");
        pesoGorduraAvaliacao.setText("");
        percLivreGorduraAvaliacao.setText("");
        imcAvaliacao.setText("");
        pesoMagroAvaliacao.setText("");
        percIdealGorduraAvaliacao.setText("");
        rcQAvaliacao.setText("");
        pesoExcedenteAvaliacao.setText("");
        //pressao arterial
        fcRepousoAvaliacao.setText("");
        fcMaximoAvaliacao.setText("");
        fcResAvaliacao.setText("");
        //zona alvo treino
        fctInferiorAvaliacao.setText("");
        fctSuperiorAvaliacao.setText("");
        percIntInferiorAvaliacao.setText("");
        percIntSuperiorAvaliacao.setText("");
        //anamenese
        simDC.setSelected(false);
        naoDC.setSelected(true);
        simDiabetes.setSelected(false);
        naoDiabetes.setSelected(true);
        simHipertensao.setSelected(false);
        naoHipertensao.setSelected(true);
        simFumante.setSelected(false);
        naoFumante.setSelected(true);
        consumoBebida.getSelectionModel().selectFirst();
        doencaCardiacaHistoricoFamiliar.getSelectionModel().selectFirst();
        diabetesHistoricoFamiliar.getSelectionModel().selectFirst();
        hipertensaoHistoricoFamiliar.getSelectionModel().selectFirst();
        obesidadeHistoricoFamiliar.getSelectionModel().selectFirst();
        simAtividadeFisica.setSelected(false);
        naoAtividadeFisica.setSelected(true);
        modadlidadeAtividadeFisica.setText("");
        modadlidadeAtividadeFisica.setVisible(false);
        frequenciaAtividadeFisica.setVisible(false);
        horaDuracaoAtividadeFisica.setVisible(false);
        minutoDuracaoAtividadeFisica.setVisible(false);
        medicamentos.setText("");
        outros.setText("");
        objetivos.setText("");
    }

    @FXML
    private void cancelarOperacao(ActionEvent action) {
        limparCampos();
    }

    /**
     * Método utilizado pelo botao salvar. Salva uma nova avaliacao ou as
     * alterações feitas em um já existente.
     *
     * @param action
     */
    @FXML
    protected void salvarAvaliacao(ActionEvent action) {
        AvaliacaoFisicaDAO avaliacaoFisicaDAO = new AvaliacaoFisicaDAO();
        
        if(this.avaliacaoFisica == null){
            this.avaliacaoFisica = new AvaliacaoFisica();
        }
        
        
        if (avaliacaoFisica.getIdAvaliacaoFisica() == 0 && getAtributosAvaliacao()) {
            if(avaliacaoFisica.getCliente() != null){
            avaliacaoFisicaDAO.salvarAvaliacaoFisica(avaliacaoFisica);
            Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
            confirmacao.setTitle("Salvar Avaliação");
            confirmacao.setHeaderText("Avaliação Cadastrada com Sucesso!");
            confirmacao.showAndWait();
            limparCampos();
            }
        } else if (getAtributosAvaliacao() && avaliacaoFisica.getIdAvaliacaoFisica() > 0) {
            if (avaliacaoFisicaDAO.atualizarAvaliacaoFisica(avaliacaoFisica)) {
                    
                Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
                confirmacao.setTitle("Salvar Avaliação");
                confirmacao.setHeaderText("Avaliação Atualizada com Sucesso!");
                confirmacao.showAndWait();
                limparCampos();
            
        }
        }else{
                Alert confirmacao = new Alert(Alert.AlertType.WARNING);
                confirmacao.setTitle("Salvar Avaliação");
                confirmacao.setHeaderText("Faltam campos a ser preenchidos!");
                confirmacao.showAndWait();
                
            
        }
    }
    /**
     * Metodo utilizado pelo botao Excluir que executa a exclusão da avaliação
     * selecionada.
     *
     * @param action
     */
    @FXML
    private void deletarAvaliacao(ActionEvent action) throws IOException {
        if(this.avaliacaoFisica.getIdAvaliacaoFisica() > 0){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmacaoAcaoAvaliacaoFisica.fxml"));
            Parent root = (Parent) loader.load();
            ConfirmacaoAcaoAvaliacaoFisicaController confirmacaoAcaoAvaliacaoFisicaController = loader.getController();
            Scene alert = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(alert);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            confirmacaoAcaoAvaliacaoFisicaController.setAvaliacaoFisica(avaliacaoFisica);
            stage.showAndWait();
            limparCampos();
        }
    }

    /**
     * Método que popula os campos da tela com as informações da avaliacao
     * selecionado.
     */
    @FXML
    private void popularCamposAvaliacao() {
        pesoAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getPeso()).replace(".", ","));
        alturaAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getAltura()).replace(".", ","));
        toraxAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getTorax()).replace(".", ","));
        toraxAvaliacao2.setText(String.valueOf(this.avaliacaoFisica.getTorax2()).replace(".", ","));
        cinturaAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getCintura()).replace(".", ","));
        abdominalAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getAbdominal()).replace(".", ","));
        quadrilAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getQuadril()).replace(".", ","));
        bissepsEsquerdoAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getBisseps_esquerdo()).replace(".", ","));
        bissepsDireitoAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getBisseps_direito()).replace(".", ","));
        coxaEsquerdaAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getCoxa_esquerda()).replace(".", ","));
        coxaDireitaAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getCoxa_direita()).replace(".", ","));
        pernaEsquerdaAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getPerna_esquerda()).replace(".", ","));
        pernaDireitaAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getPerna_direita()).replace(".", ","));
        percGorduraAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getPerc_gordura()).replace(".", ","));
        percLivreGorduraAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getPerc_livre_gordura()).replace(".", ","));
        percIdealGorduraAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getPerc_ideal_gordura()).replace(".", ","));
        percGorduraSobraAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getPerc_gord_sobra()).replace(".", ","));
        imcAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getImc()).replace(".", ","));
        rcQAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getRc_q()).replace(".", ","));
        pesoGorduraAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getPeso_gordura()).replace(".", ","));
        pesoMagroAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getPeso_magro()).replace(".", ","));
        pesoExcedenteAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getPeso_excedente()).replace(".", ","));
        //pressao arterial
        fcRepousoAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getF_c_repouso()).replace(".", ","));
        fcMaximoAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getF_c_max()).replace(".", ","));
        fcResAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getF_c_res()).replace(".", ","));
        //zona alvo de treino
        fctInferiorAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getFct_inf()).replace(".", ","));
        fctSuperiorAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getFct_sup()).replace(".", ","));
        percIntInferiorAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getPerc_int_inf()).replace(".", ","));
        percIntSuperiorAvaliacao.setText(String.valueOf(this.avaliacaoFisica.getPerc_int_sup()).replace(".", ","));
        //anamense
        if(this.avaliacaoFisica.getD_c().equals("SIM")){
            simDC.setSelected(true);
        }else{
            naoDC.setSelected(true);
        }
        if(this.avaliacaoFisica.getDiabetes().equals("SIM")){
            simDiabetes.setSelected(true);
        }else{
            naoDiabetes.setSelected(true);
        }
        if(this.avaliacaoFisica.getHipertensao().equals("SIM")){
            simHipertensao.setSelected(true);
        }else{
            naoHipertensao.setSelected(true);
        }
        if(this.avaliacaoFisica.getFumante().equals("SIM")){
            simFumante.setSelected(true);
        }else{
            naoFumante.setSelected(true);
        }
        consumoBebida.getSelectionModel().select(this.avaliacaoFisica.getBebida_alcoolica());
        //historico familiar
        doencaCardiacaHistoricoFamiliar.getSelectionModel().select(this.avaliacaoFisica.getD_c_familiar());
        diabetesHistoricoFamiliar.getSelectionModel().select(this.avaliacaoFisica.getDiabetes_familiar());
        hipertensaoHistoricoFamiliar.getSelectionModel().select(this.avaliacaoFisica.getHipertensao_familiar());
        obesidadeHistoricoFamiliar.getSelectionModel().select(this.avaliacaoFisica.getObesidade_familiar());
        
        if(this.avaliacaoFisica.getPrat_atividade_fisica().equals("SIM")){
            simAtividadeFisica.setSelected(true);
        }else{
            naoAtividadeFisica.setSelected(true);
        }
        modadlidadeAtividadeFisica.setText(this.avaliacaoFisica.getModalidade());
        medicamentos.setText(this.avaliacaoFisica.getMedicamentos());
        outros.setText(this.avaliacaoFisica.getOutros());
        objetivos.setText(this.avaliacaoFisica.getObjetivos());
    }

    /**
     * Método que coleta os dados de todos os campos da tela e seta no objeto
     * avaliacao fisica.
     */
    private boolean getAtributosAvaliacao() {
        if (verificarCamposVazios()) {
            avaliacaoFisica.setPeso(Double.parseDouble(pesoAvaliacao.getText().replace(",", ".")));
            avaliacaoFisica.setAltura(Double.parseDouble(alturaAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setTorax(Double.parseDouble(toraxAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setTorax2(Double.parseDouble(toraxAvaliacao2.getText().replace(",", ".")));
            this.avaliacaoFisica.setCintura(Double.parseDouble(cinturaAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setAbdominal(Double.parseDouble(abdominalAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setQuadril(Double.parseDouble(quadrilAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setBisseps_esquerdo(Double.parseDouble(bissepsEsquerdoAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setBisseps_direito(Double.parseDouble(bissepsDireitoAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setCoxa_esquerda(Double.parseDouble(coxaEsquerdaAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setCoxa_direita(Double.parseDouble(coxaDireitaAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setPerna_esquerda(Double.parseDouble(pernaEsquerdaAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setPerna_direita(Double.parseDouble(pernaDireitaAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setPerc_gordura(Double.parseDouble(percGorduraAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setPerc_livre_gordura(Double.parseDouble(percLivreGorduraAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setPerc_ideal_gordura(Double.parseDouble(percIdealGorduraAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setPerc_gord_sobra(Double.parseDouble(percGorduraSobraAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setImc(Double.parseDouble(imcAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setRc_q(Double.parseDouble(rcQAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setPeso_gordura(Double.parseDouble(pesoGorduraAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setPeso_magro(Double.parseDouble(pesoMagroAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setPeso_excedente(Double.parseDouble(pesoExcedenteAvaliacao.getText().replace(",", ".")));
            //pressao arterial
            this.avaliacaoFisica.setF_c_repouso(Double.parseDouble(fcRepousoAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setF_c_max(Double.parseDouble(fcMaximoAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setF_c_res(Double.parseDouble(fcResAvaliacao.getText().replace(",", ".")));
            //zona alvo de treino
            this.avaliacaoFisica.setFct_inf(Double.parseDouble(fctInferiorAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setFct_sup(Double.parseDouble(fctSuperiorAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setPerc_int_inf(Double.parseDouble(percIntInferiorAvaliacao.getText().replace(",", ".")));
            this.avaliacaoFisica.setPerc_int_sup(Double.parseDouble(percIntSuperiorAvaliacao.getText().replace(",", ".")));
            //anamenese
            if(simDC.isSelected()){
                this.avaliacaoFisica.setD_c("SIM");
            }else{
                this.avaliacaoFisica.setD_c("NAO");
            }
            if(simDiabetes.isSelected()){
                this.avaliacaoFisica.setDiabetes("SIM");
            }else{
                this.avaliacaoFisica.setDiabetes("NAO");
            }
            if(simHipertensao.isSelected()){
                this.avaliacaoFisica.setHipertensao("SIM");
            }else{
                this.avaliacaoFisica.setHipertensao("NAO");
            }
            if(simFumante.isSelected()){
                this.avaliacaoFisica.setFumante("SIM");
            }else{
                this.avaliacaoFisica.setFumante("NAO");
            }
            this.avaliacaoFisica.setBebida_alcoolica(consumoBebida.getSelectionModel().getSelectedItem().toString());
            //historico familiar
            this.avaliacaoFisica.setD_c_familiar(doencaCardiacaHistoricoFamiliar.getValue().toString());
            this.avaliacaoFisica.setDiabetes_familiar(diabetesHistoricoFamiliar.getValue().toString());
            this.avaliacaoFisica.setHipertensao_familiar(hipertensaoHistoricoFamiliar.getValue().toString());
            this.avaliacaoFisica.setObesidade_familiar(obesidadeHistoricoFamiliar.getValue().toString());
            if(simAtividadeFisica.isSelected()){
                this.avaliacaoFisica.setPrat_atividade_fisica("SIM");
            }else{
                this.avaliacaoFisica.setPrat_atividade_fisica("NAO");
            }
            this.avaliacaoFisica.setModalidade(modadlidadeAtividadeFisica.getText());
            this.avaliacaoFisica.setFrequencia_semanal_atividade(frequenciaAtividadeFisica.getText());
            
            //duracao atividade fisica
            String duracao;
            if(!horaDuracaoAtividadeFisica.getSelectionModel().getSelectedItem().toString().isEmpty() &&
                    !minutoDuracaoAtividadeFisica.getSelectionModel().getSelectedItem().toString().isEmpty()){
            duracao = horaDuracaoAtividadeFisica.getSelectionModel().getSelectedItem().toString()
                    + "." + minutoDuracaoAtividadeFisica.getSelectionModel().getSelectedItem().toString();
            this.avaliacaoFisica.setDuracao(Double.parseDouble(duracao));
            }
            
            this.avaliacaoFisica.setMedicamentos(medicamentos.getText());
            this.avaliacaoFisica.setOutros(outros.getText());
            this.avaliacaoFisica.setObjetivos(objetivos.getText());
            this.avaliacaoFisica.setCliente(cliente);
            this.avaliacaoFisica.setData_hora(LocalDate.now());
            this.avaliacaoFisica.setIdade(Integer.parseInt(idadeClienteAvaliacao.getText()));
            return true;
        }
        return false;
    }

    /**
     * Método que valida se os campos estão vazios.
     */
    private boolean verificarCamposVazios() {
        boolean retorno = true;
        int contador = 0;
        if (pesoAvaliacao.getText().isEmpty()) {
            pesoAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            pesoAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (alturaAvaliacao.getText().isEmpty()) {
            alturaAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            alturaAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (toraxAvaliacao.getText().isEmpty()) {
            toraxAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            toraxAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (toraxAvaliacao2.getText().isEmpty()) {
            toraxAvaliacao2.setStyle("-fx-border-color:red");
            contador++;
        } else {
            toraxAvaliacao2.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (cinturaAvaliacao.getText().isEmpty()) {
            cinturaAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            cinturaAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (abdominalAvaliacao.getText().isEmpty()) {
            abdominalAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            abdominalAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (quadrilAvaliacao.getText().isEmpty()) {
            quadrilAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            quadrilAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (bissepsEsquerdoAvaliacao.getText().isEmpty()) {
            bissepsEsquerdoAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            bissepsEsquerdoAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (bissepsDireitoAvaliacao.getText().isEmpty()) {
            bissepsDireitoAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            bissepsDireitoAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (coxaEsquerdaAvaliacao.getText().isEmpty()) {
            coxaEsquerdaAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            coxaEsquerdaAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (coxaDireitaAvaliacao.getText().isEmpty()) {
            coxaDireitaAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            coxaDireitaAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (pernaEsquerdaAvaliacao.getText().isEmpty()) {
            pernaEsquerdaAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            pernaEsquerdaAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (pernaDireitaAvaliacao.getText().isEmpty()) {
            pernaDireitaAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            pernaDireitaAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (percGorduraAvaliacao.getText().isEmpty()) {
            percGorduraAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            percGorduraAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (percLivreGorduraAvaliacao.getText().isEmpty()) {
            percLivreGorduraAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            percLivreGorduraAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (percIdealGorduraAvaliacao.getText().isEmpty()) {
            percIdealGorduraAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            percIdealGorduraAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (percGorduraSobraAvaliacao.getText().isEmpty()) {
            percGorduraSobraAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            percGorduraSobraAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (imcAvaliacao.getText().isEmpty()) {
            imcAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            imcAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (rcQAvaliacao.getText().isEmpty()) {
            rcQAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            rcQAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (pesoGorduraAvaliacao.getText().isEmpty()) {
            pesoGorduraAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            pesoGorduraAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (pesoMagroAvaliacao.getText().isEmpty()) {
            pesoMagroAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            pesoMagroAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (pesoExcedenteAvaliacao.getText().isEmpty()) {
            pesoExcedenteAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            pesoExcedenteAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (fcRepousoAvaliacao.getText().isEmpty()) {
            fcRepousoAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            fcRepousoAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (fcMaximoAvaliacao.getText().isEmpty()) {
            fcMaximoAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            fcMaximoAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (fcResAvaliacao.getText().isEmpty()) {
            fcResAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            fcResAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (fctInferiorAvaliacao.getText().isEmpty()) {
            fctInferiorAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            fctInferiorAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (fctSuperiorAvaliacao.getText().isEmpty()) {
            fctSuperiorAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            fctSuperiorAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (percIntInferiorAvaliacao.getText().isEmpty()) {
            percIntInferiorAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            percIntInferiorAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (percIntSuperiorAvaliacao.getText().isEmpty()) {
            percIntSuperiorAvaliacao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            percIntSuperiorAvaliacao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (contador <= -29) {
            retorno = true;
        } else {
            retorno = false;
        }

        return retorno;
       
    }

    /**
     * Metodo utilizado para setar a avaliacao selecionada na tabela no
     * avaliacao dessa classe.
     *
     * @param avaliacao
     */
    public void setAvaliacao(AvaliacaoFisica avaliacaoFisica) {
        this.avaliacaoFisica = avaliacaoFisica;

    }

    /**
     * Metodo que insere as respostas pré determinadas no combo box minutoDuracaoAvaliacaoFisica;
     */
    private void popularMinutoDuracaoAtividadeFisica(){
        ObservableList<String> minutoTreino = FXCollections.observableArrayList();
        minutoTreino.add("00");
        minutoTreino.add("05");
        minutoTreino.add("10");
        minutoTreino.add("15");
        minutoTreino.add("20");
        minutoTreino.add("25");
        minutoTreino.add("30");
        minutoTreino.add("35");
        minutoTreino.add("40");
        minutoTreino.add("45");
        minutoTreino.add("50");
        minutoTreino.add("55");
        
        minutoDuracaoAtividadeFisica.setItems(minutoTreino);
        minutoDuracaoAtividadeFisica.getSelectionModel().selectFirst();
    }
    
    /**
     * Metodo que insere as respostas pré determinadas no combo box horaDuracaoAvaliacaoFisica;
     */
    private void popularHoraDuracaoAtividadeFisica(){
        ObservableList<String> horaTreino = FXCollections.observableArrayList();
        horaTreino.add("00");
        horaTreino.add("01");
        horaTreino.add("02");
        horaTreino.add("03");
        horaTreino.add("04");
        horaTreino.add("05");
        
        horaDuracaoAtividadeFisica.setItems(horaTreino);
        horaDuracaoAtividadeFisica.getSelectionModel().selectFirst();
    }
    
    /**
     * Metodo que insere as respostas pré determinadas no combo box consumoBebidaAlcoolica.
     */
    private void popularConsumoBebidaAlcoolica(){
        ObservableList<String> consumoBebida = FXCollections.observableArrayList();
        consumoBebida.add("Nao Consome");
        consumoBebida.add("1x por semana");
        consumoBebida.add("2x por semana");
        consumoBebida.add("3x por semana");
        consumoBebida.add("4x por semana");
        consumoBebida.add("Diariamente");
        
        
        this.consumoBebida.setItems(consumoBebida);
        this.consumoBebida.getSelectionModel().selectFirst();
    }
    
    /**
     * Metodo que insere as respostas pré determinadas nos combobox referentes ao historico familiar.
     */
    private void popularHistoricoFamiliar(){
        ObservableList<String> historicoFamiliar = FXCollections.observableArrayList();
        historicoFamiliar.add("Nenhum");
        historicoFamiliar.add("Ambos");
        historicoFamiliar.add("Materno");
        historicoFamiliar.add("Paterno");
        
        doencaCardiacaHistoricoFamiliar.setItems(historicoFamiliar);
        diabetesHistoricoFamiliar.setItems(historicoFamiliar);
        hipertensaoHistoricoFamiliar.setItems(historicoFamiliar);
        obesidadeHistoricoFamiliar.setItems(historicoFamiliar);
        
        doencaCardiacaHistoricoFamiliar.getSelectionModel().selectFirst();
        diabetesHistoricoFamiliar.getSelectionModel().selectFirst();
        hipertensaoHistoricoFamiliar.getSelectionModel().selectFirst();
        obesidadeHistoricoFamiliar.getSelectionModel().selectFirst();
    }
    
    
}
