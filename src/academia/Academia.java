/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia;

import academia_DAO.SaldoDAO;
import classes_academia.ContaReceber;
import classes_academia.Mensalidade;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio_academia.Negocio_Cliente;
import negocio_academia.Negocio_Financeiro;

/**
 *
 * @author Artur
 */
public class Academia extends Application {

    @FXML
    private TextField idNomeUsuario;

    @FXML
    private PasswordField idSenhaUsuario;

    @FXML
    private Button btnEntrarSistema;

    @FXML
    private Button btnEsqueceuSenha;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        
        Scene scene = new Scene(root);
        stage.setTitle("Entrar no Sistema");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        //gerarMensalidades();
        /*ReciboPagamento reciboPagamento = new ReciboPagamento();
        ContaReceberDAO contaReceberDAO = new ContaReceberDAO();
        Collection<ContaReceber> contaReceber = new ArrayList();
        contaReceber = contaReceberDAO.buscarMensalidadeAnoMes(03,2018);
        reciboPagamento.gerarRecibo(contaReceber);
        */
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

    public void gerarMensalidades() {

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

            LocalDate dataVenc = LocalDate.of(anoVenc, mesVenc, mensalidadesAtiva.getDiaVencimento());
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

        SaldoDAO saldoDAO = new SaldoDAO();
        saldoDAO.criarSaldo();
    }

    private String formatarData(LocalDate dataVenc) {
        final String DATE_PATTERN = "dd/MM/yyyy";
        final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

        return DATE_FORMATTER.format(dataVenc);
    }

}
