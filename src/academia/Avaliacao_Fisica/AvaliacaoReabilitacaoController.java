/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Avaliacao_Fisica;

import academia_DAO.ClienteDAO;
import classes_academia.AvaliacaoReabilitacao;
import classes_academia.Cliente;
import com.sun.org.apache.bcel.internal.Repository;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

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
    
    AvaliacaoReabilitacao avaliacaoReabilitacao = new AvaliacaoReabilitacao();
    
    Cliente cliente = new Cliente();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            popularDadosCliente();
            if (this.avaliacaoReabilitacao != null && this.avaliacaoReabilitacao.getIdAvaliacaoReabilitacao()>0) {
                popularCamposAvaliacao();
            }
    }
    
    private void popularDadosCliente(){
        nomeClienteReabilitacao.setText(cliente.getNome());
        dataReabilitacao.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        dataNascimentoReabilitacao.setText(cliente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        if(cliente.getSexo()){
            sexoReabilitacao.setText("Masculino");
        }else{
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
        
    }
    
    @FXML
    private void cancelarOperacao(ActionEvent action) {
        limparCampos();
    }
    
    @FXML
    private void abrirImagem(ActionEvent action) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*"));
        imagens = fileChooser.showOpenMultipleDialog(null);
        int i = 0;
        for(File file : imagens){
            Image imagem = new Image(file.toURI().toString());
            BufferedImage imag = ImageIO.read(file);
            if(imagem1.getImage()==null){
                //Image imagem = new Image(file.toURI().toString());
                imagem1.setImage(imagem);
            }else if(imagem2.getImage()==null){
            imagem2.setImage(imagem);
            }else if(imagem3.getImage()==null){
            imagem3.setImage(imagem);
            }else if(imagem4.getImage()==null){
            imagem4.setImage(imagem);
            }else if(imagem5.getImage()==null){
            imagem5.setImage(imagem);
            }else if(imagem6.getImage()==null){
            imagem6.setImage(imagem);
            }else if(imagem7.getImage()==null){
            imagem7.setImage(imagem);
            }
        }
       
    }
    
    @FXML
    private void salvarImagens(ActionEvent action) throws IOException{
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
                    //BufferedImage imageB  = setImagem(image.getAbsolutePath(), 500, 500);
                    BufferedImage imag = ImageIO.read(image);
            
                    File outputFile = new File("C:\\Users\\Public\\Documents\\Fotos Academia"+"\\"+i+nomeClienteReabilitacao.getText()+dataReabilitacao.getText()+".jpeg");
                    ImageIO.write( imag, "jpg", outputFile);
                    i++;
                } catch (Exception e) {
                }
            }
                    
    }
    
    private BufferedImage setImagem(String caminho, int largura, int altura){
        
        BufferedImage imagemB = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
        return imagemB;
    }
}
