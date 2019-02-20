/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia;

import academia_DAO.ClienteDAO;
import classes_academia.Cliente;
import classes_academia.ParametroUsuario;
import classes_academia.QuadroHorario;
import com.sun.javafx.robot.impl.FXRobotHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import negocio_academia.Negocio_Cliente;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class PrincipalController implements Initializable {

    @FXML
    private Button botaoClientes;

    @FXML
    private Button botaoFinanceiro;

    @FXML
    private AnchorPane paneVisualizar;

    @FXML
    private AnchorPane paneDefault;

    @FXML
    private AnchorPane paneVisualizarOpcao;

    @FXML
    private Button botaoUsuario;

    @FXML
    private Label labelUsuarioLogado;

    @FXML
    private Button botaoTrocarUsuario;

    @FXML
    private Button botaoHome;

    @FXML
    private TreeView<String> idQuadroHorario;

    TreeItem<String> quadroHorarios = new TreeItem<String>("Horários");
    private Scene scene;

    @FXML
    private Label labelTotal;
    
    @FXML
    private Label labelInativos;
    
    @FXML
    private Label labelAtivos;
    
    @FXML
    private Button botaoAvaliacaoFisica;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image iconRefresh = new Image(getClass().getResourceAsStream("/academia/icon/casa.png")); //inserir icone no projeto
        botaoHome.setGraphic(new ImageView(iconRefresh));
        ParametroUsuario parametroUsuario = new ParametroUsuario();
        labelUsuarioLogado.setText("Olá, " + parametroUsuario.getUsuario().getNome());

        preencherQuadroHorarios();
        buscarQuantidadeAlunos();
    }

    public void iniciar() throws IOException {
        ParametroUsuario parametroUsuario = new ParametroUsuario();
        labelUsuarioLogado.setText("Olá, " + parametroUsuario.getUsuario().getNome());
    }

    @FXML
    private void mostrarOpcaoCliente(ActionEvent action) throws IOException { //a partir desse metodo, sao chamados os botoes da parte de clientes.
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("Cliente/Cliente.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        paneVisualizar.getChildren().setAll(pane);

    }

    @FXML
    private void mostrarOpcaoFinanceiro(ActionEvent action) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("Financeiro/FXMLfinanceiro.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        paneVisualizar.getChildren().setAll(pane);

    }

    @FXML
    private void mostrarOpcaoHome(ActionEvent action) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("FXMLtelaPrincipal.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        paneDefault.getChildren().setAll(pane);
    }

    @FXML
    private void mostrarOpcaoUsuario(ActionEvent action) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("Usuario/FXMLcontroleUsuario.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        paneVisualizar.getChildren().setAll(pane);

    }

    @FXML
    private void mostrarOpcaoAvaliacao(ActionEvent action) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("Avaliacao_Fisica/avaliacaoMenu.fxml"));
        AnchorPane pane = (AnchorPane) fXMLLoader.load();
        paneVisualizar.getChildren().setAll(pane);

    }
    
    @FXML
    protected void alterarCorDoBotaoUsuario() {
        botaoUsuario.setStyle("-fx-background-color: white" + " -fx-text-fill: black");
        botaoUsuario.setTextFill(Color.BLACK);
        voltarCorOriginalBotaoFinanceiro();
        voltarCorOriginal();
        voltarCorOriginalBotaoAvaliacao();
    }

    @FXML
    protected void voltarCorOriginalUsuario() {
        botaoUsuario.setStyle("-fx-background-color: #4169E1");
        botaoUsuario.setTextFill(Color.WHITE);
    }

    @FXML
    protected void alterarCorDoBotao() {
        botaoClientes.setStyle("-fx-background-color: white" + " -fx-text-fill: black");
        botaoClientes.setTextFill(Color.BLACK);
        voltarCorOriginalBotaoFinanceiro();
        voltarCorOriginalUsuario();
        voltarCorOriginalBotaoAvaliacao();
    }

    @FXML
    protected void voltarCorOriginal() {
        botaoClientes.setStyle("-fx-background-color: #4169E1");
        botaoClientes.setTextFill(Color.WHITE);
    }

    @FXML
    protected void alterarCorDoBotaoFinanceiro() {
        botaoFinanceiro.setStyle("-fx-background-color: white" + " -fx-text-fill: black");
        botaoFinanceiro.setTextFill(Color.BLACK);
        voltarCorOriginal();
        voltarCorOriginalUsuario();
        voltarCorOriginalBotaoAvaliacao();
    }

    @FXML
    protected void voltarCorOriginalBotaoFinanceiro() {
        botaoFinanceiro.setStyle("-fx-background-color: #4169E1");
        botaoFinanceiro.setTextFill(Color.WHITE);
    }

    @FXML
    protected void alterarCorDoBotaoAvaliacao() {
        botaoAvaliacaoFisica.setStyle("-fx-background-color: white" + " -fx-text-fill: black");
        botaoAvaliacaoFisica.setTextFill(Color.BLACK);
        voltarCorOriginal();
        voltarCorOriginalUsuario();
        voltarCorOriginalBotaoFinanceiro();
    }

    @FXML
    protected void voltarCorOriginalBotaoAvaliacao() {
        botaoAvaliacaoFisica.setStyle("-fx-background-color: #4169E1");
        botaoAvaliacaoFisica.setTextFill(Color.WHITE);
    }
    
    @FXML
    private void preencherQuadroHorarios() {
        Negocio_Cliente negocioCliente = new Negocio_Cliente();
        List<Cliente> clientes = new ArrayList<>();

        clientes = negocioCliente.buscarHorariosClientes();

        quadroHorarios.setExpanded(true);

        TreeItem<String> segunda = new TreeItem<String>("Segunda");
        TreeItem<String> terca = new TreeItem<String>("Terca");
        TreeItem<String> quarta = new TreeItem<String>("Quarta");
        TreeItem<String> quinta = new TreeItem<String>("Quinta");
        TreeItem<String> sexta = new TreeItem<String>("Sexta");
        TreeItem<String> sabado = new TreeItem<String>("Sabado");

        quadroHorarios.getChildren().addAll(segunda, terca, quarta, quinta, sexta, sabado);

        List<Cliente> clientesSegunda = new ArrayList<>();
        List<Cliente> clientesTerca = new ArrayList<>();
        List<Cliente> clientesQuarta = new ArrayList<>();
        List<Cliente> clientesQuinta = new ArrayList<>();
        List<Cliente> clientesSexta = new ArrayList<>();
        List<Cliente> clientesSabado = new ArrayList<>();
        
        for (Cliente cliente : clientes) {
            if (cliente.getTreinoSegunda()) {
                clientesSegunda.add(cliente);
            }
            
            if(cliente.getTreinoTerca()){
                clientesTerca.add(cliente);
            }
            
            if(cliente.getTreinoQuarta()){
                clientesQuarta.add(cliente);
            }
            
            if(cliente.getTreinoQuinta()){
                clientesQuinta.add(cliente);
            }
            
            if(cliente.getTreinoSexta()){
                clientesSexta.add(cliente);
            }
            
            if(cliente.getTreinoSabado()){
                clientesSabado.add(cliente);
            }
        }

        
        /*for (Cliente cliente : clientesSegunda) {
            TreeItem<String> hora = new TreeItem<String>(cliente.getHoraTreino());
            TreeItem<String> nome = new TreeItem<String>(cliente.getNome());

            
            hora.getChildren().add(nome);
            segunda.getChildren().add(hora);
            
        }
        
         for (Cliente cliente : clientesTerca) {
            TreeItem<String> hora = new TreeItem<String>(cliente.getHoraTreino());
            TreeItem<String> nome = new TreeItem<String>(cliente.getNome());

            hora.getChildren().add(nome);
            terca.getChildren().add(hora);

        }
         
         for (Cliente cliente : clientesQuarta) {
            TreeItem<String> hora = new TreeItem<String>(cliente.getHoraTreino());
            TreeItem<String> nome = new TreeItem<String>(cliente.getNome());

            hora.getChildren().add(nome);
            quarta.getChildren().add(hora);

        } 

          for (Cliente cliente : clientesQuinta) {
            TreeItem<String> hora = new TreeItem<String>(cliente.getHoraTreino());
            TreeItem<String> nome = new TreeItem<String>(cliente.getNome());

            hora.getChildren().add(nome);
            quinta.getChildren().add(hora);

        }
          
           for (Cliente cliente : clientesSexta) {
            TreeItem<String> hora = new TreeItem<String>(cliente.getHoraTreino());
            TreeItem<String> nome = new TreeItem<String>(cliente.getNome());

            hora.getChildren().add(nome);
            sexta.getChildren().add(hora);

        }
           
            for (Cliente cliente : clientesSabado) {
            TreeItem<String> hora = new TreeItem<String>(cliente.getHoraTreino());
            TreeItem<String> nome = new TreeItem<String>(cliente.getNome());

            hora.getChildren().add(nome);
            sabado.getChildren().add(hora);

        }*/
        
            
        //atualização tabela de horários dos alunos
        for(Cliente cliente : clientesSegunda){
            TreeItem<String> folha = new TreeItem<String>(cliente.getNome());
            boolean found = false;
            
            for(TreeItem<String> horarios : segunda.getChildren()){
                if(horarios.getValue().contentEquals(cliente.getHoraTreino())){
                    horarios.getChildren().add(folha);
                    found = true;
                    break;
                }
            }
            if(!found){
                TreeItem<String> horario = new TreeItem<String>(cliente.getHoraTreino());
                segunda.getChildren().add(horario);
                horario.getChildren().add(folha);
            }
        }   
        
        for(Cliente cliente : clientesTerca){
            TreeItem<String> folha = new TreeItem<String>(cliente.getNome());
            boolean found = false;
            
            for(TreeItem<String> horarios : terca.getChildren()){
                if(horarios.getValue().contentEquals(cliente.getHoraTreino())){
                    horarios.getChildren().add(folha);
                    found = true;
                    break;
                }
            }
            if(!found){
                TreeItem<String> horario = new TreeItem<String>(cliente.getHoraTreino());
                terca.getChildren().add(horario);
                horario.getChildren().add(folha);
            }
        }
        
        for(Cliente cliente : clientesQuarta){
            TreeItem<String> folha = new TreeItem<String>(cliente.getNome());
            boolean found = false;
            
            for(TreeItem<String> horarios : quarta.getChildren()){
                if(horarios.getValue().contentEquals(cliente.getHoraTreino())){
                    horarios.getChildren().add(folha);
                    found = true;
                    break;
                }
            }
            if(!found){
                TreeItem<String> horario = new TreeItem<String>(cliente.getHoraTreino());
                quarta.getChildren().add(horario);
                horario.getChildren().add(folha);
            }
        }
        
        for(Cliente cliente : clientesQuinta){
            TreeItem<String> folha = new TreeItem<String>(cliente.getNome());
            boolean found = false;
            
            for(TreeItem<String> horarios : quinta.getChildren()){
                if(horarios.getValue().contentEquals(cliente.getHoraTreino())){
                    horarios.getChildren().add(folha);
                    found = true;
                    break;
                }
            }
            if(!found){
                TreeItem<String> horario = new TreeItem<String>(cliente.getHoraTreino());
                quinta.getChildren().add(horario);
                horario.getChildren().add(folha);
            }
        }
        
        for(Cliente cliente : clientesSexta){
            TreeItem<String> folha = new TreeItem<String>(cliente.getNome());
            boolean found = false;
            
            for(TreeItem<String> horarios : sexta.getChildren()){
                if(horarios.getValue().contentEquals(cliente.getHoraTreino())){
                    horarios.getChildren().add(folha);
                    found = true;
                    break;
                }
            }
            if(!found){
                TreeItem<String> horario = new TreeItem<String>(cliente.getHoraTreino());
                sexta.getChildren().add(horario);
                horario.getChildren().add(folha);
            }
        }
        
        for(Cliente cliente : clientesSabado){
            TreeItem<String> folha = new TreeItem<String>(cliente.getNome());
            boolean found = false;
            
            for(TreeItem<String> horarios : sabado.getChildren()){
                if(horarios.getValue().contentEquals(cliente.getHoraTreino())){
                    horarios.getChildren().add(folha);
                    found = true;
                    break;
                }
            }
            if(!found){
                TreeItem<String> horario = new TreeItem<String>(cliente.getHoraTreino());
                sabado.getChildren().add(horario);
                horario.getChildren().add(folha);
            }
        }
        
        idQuadroHorario.setShowRoot(true);
        idQuadroHorario.setRoot(quadroHorarios);
    }

    @FXML
    protected void botaoTrocarUsuario(ActionEvent evento) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Usuario/FXMLtrocarUsuario.fxml"));
        Scene alert = new Scene(root);
        Stage stage = new Stage();

        Image icone = new Image(getClass().getResourceAsStream("/academia/icon/refresh.png"));
        stage.getIcons().add(icone);
        stage.setScene(alert);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    @FXML
    protected void buscarQuantidadeAlunos(){
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = new ArrayList();
        int ativos = 0;
        int inativos = 0;
        
        clientes = clienteDAO.buscarTodosClientes();
        
        if(!clientes.isEmpty()){
            for (Cliente cliente : clientes){
                if(cliente.getStatus()){
                    ativos ++;
                }else{
                    inativos ++;
                }
            }
            
            labelAtivos.setText(""+ativos);
            labelInativos.setText(""+inativos);
            labelTotal.setText(""+clientes.size());
        }else{
            
            labelAtivos.setText("0");
            labelInativos.setText("0");
            labelTotal.setText("0");
        }
    }
}
