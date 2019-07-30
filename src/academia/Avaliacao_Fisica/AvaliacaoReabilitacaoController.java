/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Avaliacao_Fisica;

import academia.Financeiro.FXMLcontasReceber;
import academia.Relatorios.RelatorioAvaliacaoReabilitacao;
import academia_DAO.AvaliacaoReabilitacaoDAO;
import academia_DAO.ClienteDAO;
import classes_academia.AvaliacaoReabilitacao;
import classes_academia.Cliente;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;

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

    @FXML
    private Button abrirImagem;

    @FXML
    private ImageView imagem1 = null;

    @FXML
    private ImageView imagem2 = null;

    @FXML
    private ImageView imagem3 = null;

    @FXML
    private ImageView imagem4 = null;

    @FXML
    private ImageView imagem5 = null;

    @FXML
    private ImageView imagem6 = null;

    @FXML
    private ImageView imagem7 = null;

    List<File> imagens = new ArrayList();

    List<File> imagensCarregas = new ArrayList();
    
    File[] imagens1;

    AvaliacaoReabilitacao avaliacaoReabilitacao = new AvaliacaoReabilitacao();

    Cliente cliente = new Cliente();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       abrirImagem.setVisible(false);
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

    @FXML
    private void visualizarImagem(MouseEvent action) throws IOException {

        if (imagem1 != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("visualizarImagem.fxml"));
            Parent root = (Parent) loader.load();
            VisualizarImagemController visualizarImagemController = loader.getController();
            Scene alert = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(alert);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            visualizarImagemController.setImagem(imagensCarregas);
            stage.showAndWait();
            imagens = visualizarImagemController.getImagens();
            atualizarImagensView();
        } else {
            Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
            confirmacao.setTitle("Visualizar Imagem");
            confirmacao.setHeaderText("Nenhuma imagem encontrada.");
            confirmacao.showAndWait();
        }
    }

    private void atualizarImagensView(){
        imagem1.setImage(null);
        imagem2.setImage(null);
        imagem3.setImage(null);
        imagem4.setImage(null);
        imagem5.setImage(null);
        imagem6.setImage(null);
        imagem7.setImage(null);
        
        int i = 0;
        if (imagens != null) {
            while(i<imagens.size()){
                Image imagem = new Image(imagens.get(i).toURI().toString());
                if(i==0){
                    imagem1.setImage(imagem);
                }else if(i==1){
                    imagem2.setImage(imagem);
                }else if(i==2){
                    imagem3.setImage(imagem);
                }else if(i==3){
                    imagem4.setImage(imagem);
                }else if(i==4){
                    imagem5.setImage(imagem);
                }else if(i==5){
                    imagem6.setImage(imagem);
                }else if(i==6){
                    imagem7.setImage(imagem);
                }
                
                i++;
            }
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
     * Metodo que mostra a tabela com todas as avaliações do cliente
     * selecionado.
     */
    @FXML
    private void mostrarAvaliacoes() throws IOException {
        AvaliacaoReabilitacaoDAO avaliacaoReabilitacaoDAO = new AvaliacaoReabilitacaoDAO();

        ArrayList<AvaliacaoReabilitacao> avaliacoes = new ArrayList<AvaliacaoReabilitacao>();
        avaliacoes = avaliacaoReabilitacaoDAO.consultarAvaliacaoPorCliente(cliente);

        //if(!avaliacoes.isEmpty()){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tabelaReabilitacao.fxml"));
        Parent root = (Parent) loader.load();
        TabelaReabilitacaoController tabelaReabilitacaoController = loader.getController();
        Scene alert = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(alert);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        tabelaReabilitacaoController.setAvaliacao(avaliacoes);
        stage.showAndWait();
        this.avaliacaoReabilitacao = tabelaReabilitacaoController.getAvaliacaoSelecionada();
        if (this.avaliacaoReabilitacao != null && this.avaliacaoReabilitacao.getIdAvaliacaoReabilitacao() > 0) {
            popularDadosClienteAvaliacao();
            popularCamposAvaliacao();
        } else {
            popularDadosCliente();
        }
        abrirImagem.setVisible(true);
    }

    private void popularDadosClienteAvaliacao() {
        nomeClienteReabilitacao.setText(cliente.getNome());
        dataReabilitacao.setText(avaliacaoReabilitacao.getData_hora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        dataNascimentoReabilitacao.setText(cliente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        if (cliente.getSexo()) {
            sexoReabilitacao.setText("Masculino");
        } else {
            sexoReabilitacao.setText("Feminino");
        }

        long idade = ChronoUnit.YEARS.between(cliente.getDataNascimento(), LocalDate.now());
        idadeClienteReabilitacao.setText(String.valueOf(idade));
    }

    private void popularDadosCliente() {
        nomeClienteReabilitacao.setText(cliente.getNome());
        dataReabilitacao.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        dataNascimentoReabilitacao.setText(cliente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        if (cliente.getSexo()) {
            sexoReabilitacao.setText("Masculino");
        } else {
            sexoReabilitacao.setText("Feminino");
        }

        long idade = ChronoUnit.YEARS.between(cliente.getDataNascimento(), LocalDate.now());
        idadeClienteReabilitacao.setText(String.valueOf(idade));
    }

    /**
     * Método que popula os campos da tela com as informações da avaliacao
     * selecionado.
     */
    @FXML
    private void popularCamposAvaliacao() {
        //colocar todas as fotos aqui

        File diretorio = new File("C:\\Users\\Public\\Documents\\Fotos Academia" + "\\");
        imagens1 = diretorio.listFiles();

        for (File imagen : imagens1) {
            String nome = imagen.getName();

            if (nome.contains(nomeClienteReabilitacao.getText() + dataReabilitacao.getText().replace("/", "."))) {
                Image imagem = new Image(imagen.toURI().toString());
                //inserir no imagensCarrega apenas a foto que eh do cliente.
                imagensCarregas.add(imagen);
                if (imagem1.getImage() == null) {
                    imagem1.setImage(imagem);
                } else if (imagem2.getImage() == null) {
                    imagem2.setImage(imagem);
                } else if (imagem3.getImage() == null) {
                    imagem3.setImage(imagem);
                } else if (imagem4.getImage() == null) {
                    imagem4.setImage(imagem);
                } else if (imagem5.getImage() == null) {
                    imagem5.setImage(imagem);
                } else if (imagem6.getImage() == null) {
                    imagem6.setImage(imagem);
                } else if (imagem7.getImage() == null) {
                    imagem7.setImage(imagem);
                }
            }
            
        }
        descricao.setText(avaliacaoReabilitacao.getDescricao());
        exercicios.setText(avaliacaoReabilitacao.getExercicios());
        medicamentos.setText(avaliacaoReabilitacao.getMedicamentos());
        tratamentosAnteriores.setText(avaliacaoReabilitacao.getTratamento_anterior());
    }

    /**
     * Método que limpa o(s) campo(s) na tela de Veiculo.
     */
    private void limparCampos() {
        nomeClienteBusca.setText("");
        nomeClienteReabilitacao.setText("");
        dataReabilitacao.setText("");
        dataNascimentoReabilitacao.setText("");
        idadeClienteReabilitacao.setText("");
        sexoReabilitacao.setText("");

        descricao.setText("");
        tratamentosAnteriores.setText("");
        exercicios.setText("");
        medicamentos.setText("");
        imagem1.setImage(null);
        imagem2.setImage(null);
        imagem3.setImage(null);
        imagem4.setImage(null);
        imagem5.setImage(null);
        imagem6.setImage(null);
        imagem7.setImage(null);
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
        AvaliacaoReabilitacaoDAO avaliacaoReabilitacaoDAO = new AvaliacaoReabilitacaoDAO();

        if (this.avaliacaoReabilitacao == null) {
            this.avaliacaoReabilitacao = new AvaliacaoReabilitacao();
        }

        if (avaliacaoReabilitacao.getIdAvaliacaoReabilitacao() == 0 && getAtributosAvaliacao()) {
            if (avaliacaoReabilitacao.getCliente() != null) {
                avaliacaoReabilitacaoDAO.salvarAvaliacaoFisica(avaliacaoReabilitacao);
                salvarImagens();
                Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
                confirmacao.setTitle("Salvar Avaliação");
                confirmacao.setHeaderText("Avaliação Cadastrada com Sucesso!");
                confirmacao.showAndWait();
                limparCampos();
                Alert gerarMensalidade = new Alert(Alert.AlertType.NONE);
                    ButtonType gerar = new ButtonType("Sim");
                    ButtonType cancel = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
                    gerarMensalidade.setTitle("Gerar Relatório");
                    gerarMensalidade.setHeaderText("Gerar Relatório Avaliação Reabilitação?");
                   gerarMensalidade.getButtonTypes().addAll(gerar, cancel);
                   gerarMensalidade.initStyle(StageStyle.UTILITY);
                  
                    gerarMensalidade.showAndWait().ifPresent(b -> {
                        if (b == gerar) {
                            gerarRecibo();
                        }
                    });
            }
        } else if (getAtributosAvaliacao() && avaliacaoReabilitacao.getIdAvaliacaoReabilitacao() > 0) {
            if (avaliacaoReabilitacaoDAO.atualizarAvaliacaoFisica(avaliacaoReabilitacao)) {
                salvarImagens();
                Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
                confirmacao.setTitle("Salvar Avaliação");
                confirmacao.setHeaderText("Avaliação Atualizada com Sucesso!");
                confirmacao.showAndWait();
                limparCampos();
                Alert gerarMensalidade = new Alert(Alert.AlertType.NONE);
                    ButtonType gerar = new ButtonType("Sim");
                    ButtonType cancel = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
                    gerarMensalidade.setTitle("Gerar Relatório");
                    gerarMensalidade.setHeaderText("Gerar Relatório Avaliação Física?");
                   gerarMensalidade.getButtonTypes().addAll(gerar, cancel);
                   gerarMensalidade.initStyle(StageStyle.UTILITY);
                  
                    gerarMensalidade.showAndWait().ifPresent(b -> {
                        if (b == gerar) {
                            gerarRecibo();
                        }
                    });
                            
            }
        } else {
            Alert confirmacao = new Alert(Alert.AlertType.WARNING);
            confirmacao.setTitle("Salvar Avaliação");
            confirmacao.setHeaderText("Faltam campos a ser preenchidos!");
            confirmacao.showAndWait();
        }
    }

    @FXML
    private void abrirImagem(ActionEvent action) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens [ .jpeg ]", "*"));
        imagens = fileChooser.showOpenMultipleDialog(null);

        int i = 0;
        if (imagens != null) {
            for (File file : imagens) {
                Image imagem = new Image(file.toURI().toString());
                BufferedImage imag = ImageIO.read(file);
                
                if (imagem1.getImage() == null) {
                    //Image imagem = new Image(file.toURI().toString());
                    imagem1.setImage(imagem);
                } else if (imagem2.getImage() == null) {
                    imagem2.setImage(imagem);
                } else if (imagem3.getImage() == null) {
                    imagem3.setImage(imagem);
                } else if (imagem4.getImage() == null) {
                    imagem4.setImage(imagem);
                } else if (imagem5.getImage() == null) {
                    imagem5.setImage(imagem);
                } else if (imagem6.getImage() == null) {
                    imagem6.setImage(imagem);
                } else if (imagem7.getImage() == null) {
                    imagem7.setImage(imagem);
                }
                imagensCarregas.add(file);
                System.err.println(imagensCarregas.size());
            }
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
        if (this.avaliacaoReabilitacao.getIdAvaliacaoReabilitacao() > 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmacaoAcaoAvaliacaoReabilitacao.fxml"));
            Parent root = (Parent) loader.load();
            ConfirmacaoAcaoAvaliacaoReabilitacaoController confirmacaoAcaoAvaliacaoReabilitacaoController = loader.getController();
            Scene alert = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(alert);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            confirmacaoAcaoAvaliacaoReabilitacaoController.setAvaliacaoReabilitacao(avaliacaoReabilitacao);
            stage.showAndWait();
            deletarImagens();
            limparCampos();
        }
    }

    private void deletarImagens() throws IOException {
        for (File file : imagens1) {
            file.delete();

        }
    }

    @FXML
    private void salvarImagens() {
        int i = 1;

        ArrayList<Image> img = new ArrayList<>();
        img.add(imagem1.getImage());
        img.add(imagem2.getImage());
        img.add(imagem3.getImage());
        img.add(imagem4.getImage());
        img.add(imagem5.getImage());
        img.add(imagem6.getImage());
        img.add(imagem7.getImage());

        for (File image : imagens) {
            try {

                BufferedImage imag = ImageIO.read(image);
                String data = dataReabilitacao.getText().replace("/", ".");
                File outputFile = new File("C:\\Users\\Public\\Documents\\Fotos Academia" + "\\" + i + nomeClienteReabilitacao.getText() + data + ".jpeg");
                ImageIO.write(imag, "jpg", outputFile);
                System.err.println(image.getPath());

                i++;
            } catch (Exception e) {
            }
        }

    }

    private BufferedImage setImagem(String caminho, int largura, int altura) {

        BufferedImage imagemB = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
        return imagemB;
    }

    /**
     * Método que coleta os dados de todos os campos da tela e seta no objeto
     * avaliacao fisica.
     */
    private boolean getAtributosAvaliacao() {
        if (verificarCamposVazios()) {
            this.avaliacaoReabilitacao.setDescricao(descricao.getText());
            this.avaliacaoReabilitacao.setMedicamentos(medicamentos.getText());
            this.avaliacaoReabilitacao.setExercicios(exercicios.getText());
            this.avaliacaoReabilitacao.setTratamento_anterior(tratamentosAnteriores.getText());
            this.avaliacaoReabilitacao.setCliente(cliente);
            this.avaliacaoReabilitacao.setData_hora(LocalDate.now());
            this.avaliacaoReabilitacao.setIdade(Integer.parseInt(idadeClienteReabilitacao.getText()));

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
        if (descricao.getText().isEmpty()) {
            descricao.setStyle("-fx-border-color:red");
            contador++;
        } else {
            descricao.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (exercicios.getText().isEmpty()) {
            exercicios.setStyle("-fx-border-color:red");
            contador++;
        } else {
            exercicios.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (medicamentos.getText().isEmpty()) {
            medicamentos.setStyle("-fx-border-color:red");
            contador++;
        } else {
            medicamentos.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (tratamentosAnteriores.getText().isEmpty()) {
            tratamentosAnteriores.setStyle("-fx-border-color:red");
            contador++;
        } else {
            tratamentosAnteriores.setStyle("-fx-border-color:#bbaFFF");
            contador--;
        }

        if (contador <= -4) {
            retorno = true;
        } else {
            retorno = false;
        }

        return retorno;

    }
    
    public void gerarRecibo() {
        
         RelatorioAvaliacaoReabilitacao relatorioAvaliacaoReabilitacao = new RelatorioAvaliacaoReabilitacao();
        AvaliacaoReabilitacaoDAO avaliacaoReabilitacaoDAO = new AvaliacaoReabilitacaoDAO();
        Collection<AvaliacaoReabilitacao> avaliacaoF = new ArrayList();
        
        avaliacaoF = avaliacaoReabilitacaoDAO.consultarDadosClienteEmAvaliacao(cliente);
        
        try {
            relatorioAvaliacaoReabilitacao.gerarRelatorioAvaliacaoReabilitacao(avaliacaoF, imagensCarregas);
        } catch (JRException ex) {
            Logger.getLogger(FXMLcontasReceber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
