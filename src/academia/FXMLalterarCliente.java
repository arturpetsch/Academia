/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia;

import academia_DAO.MensalidadeDAO;
import classes_academia.Cliente;
import classes_academia.Mensalidade;
import classes_academia.ParametroCliente;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import negocio_academia.Negocio_Cliente;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class FXMLalterarCliente implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public TextField CPFalterarCliente;

    @FXML
    public TextField nomeAlterarCliente;

    @FXML
    public TextField emailAlterarCliente = new TextField();

    @FXML
    public TextField telefoneAlterarCliente;

    @FXML
    public TextField observacaoAlterarCliente;

    @FXML
    public TextField CEPAlterarCliente;

    @FXML
    public TextField enderecoAlterarCliente;

    @FXML
    public TextField numeroAlterarCliente;

    @FXML
    public TextField bairroAlterarCliente;

    @FXML
    public TextField cidadeAlterarCliente;

    @FXML
    public RadioButton statusAtivoAlterarCliente;

    @FXML
    public RadioButton statusInativoAlterarCliente;

    @FXML
    public ComboBox ufAlterarCliente;

    @FXML
    public Button botaoCancelarAlterarCliente;

    @FXML
    public Button botaoSalvarAlterarCliente;

    @FXML
    public AnchorPane idPainelAlterarCliente;

    @FXML
    public DatePicker dataNascimentoAlterarCliente;

    private LocalDate dataCadastro;

    @FXML
    private ComboBox horaTreinoAlterarCliente;

    @FXML
    private ComboBox minutoTreinoAlterarCliente;

    @FXML
    private ComboBox diaVencimentoAlterarCliente;
    
    @FXML
    private TextField valorMensalidadeAlterarCliente;
    
    protected int idClienteAlterar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dataNascimentoAlterarCliente.setPromptText(" /  /");
        ObservableList<String> estados = FXCollections.observableArrayList();
        estados.add("Acre");
        estados.add("Alagoas");
        estados.add("Amapa");
        estados.add("Amazonas");
        estados.add("Bahia");
        estados.add("Ceara");
        estados.add("Distrito Federal");
        estados.add("Espirito Santo");
        estados.add("Goias");
        estados.add("Maranhao");
        estados.add("Mato Grosso");
        estados.add("Mato Grosso do Sul");
        estados.add("Minas Gerais");
        estados.add("Para");
        estados.add("Paraiba");
        estados.add("Parana");
        estados.add("Pernambuco");
        estados.add("Piaui");
        estados.add("Rio de Janeiro");
        estados.add("Rio Grande do Norte");
        estados.add("Rio Grande do Sul");
        estados.add("Rondonia");
        estados.add("Roraima");
        estados.add("Santa Catarina");
        estados.add("Sao Paulo");
        estados.add("Sergipe");
        estados.add("Tocantins");

        ufAlterarCliente.setItems(estados);

        ObservableList<String> horaTreino = FXCollections.observableArrayList();
        horaTreino.add("01");
        horaTreino.add("02");
        horaTreino.add("03");
        horaTreino.add("04");
        horaTreino.add("05");
        horaTreino.add("06");
        horaTreino.add("07");
        horaTreino.add("08");
        horaTreino.add("09");
        horaTreino.add("10");
        horaTreino.add("11");
        horaTreino.add("12");
        horaTreino.add("13");
        horaTreino.add("14");
        horaTreino.add("15");
        horaTreino.add("16");
        horaTreino.add("17");
        horaTreino.add("18");
        horaTreino.add("19");
        horaTreino.add("20");
        horaTreino.add("21");
        horaTreino.add("22");
        horaTreino.add("23");
        horaTreino.add("00");

        horaTreinoAlterarCliente.setItems(horaTreino);

        ObservableList<String> minutoTreino = FXCollections.observableArrayList();
        minutoTreino.add("00");
        minutoTreino.add("15");
        minutoTreino.add("30");
        minutoTreino.add("45");

        minutoTreinoAlterarCliente.setItems(minutoTreino);

        ObservableList<String> diaVencimento = FXCollections.observableArrayList();
        diaVencimento.add("1");
        diaVencimento.add("2");
        diaVencimento.add("3");
        diaVencimento.add("4");
        diaVencimento.add("5");
        diaVencimento.add("6");
        diaVencimento.add("7");
        diaVencimento.add("8");
        diaVencimento.add("9");
        diaVencimento.add("10");
        diaVencimento.add("11");
        diaVencimento.add("12");
        diaVencimento.add("13");
        diaVencimento.add("14");
        diaVencimento.add("15");
        diaVencimento.add("16");
        diaVencimento.add("17");
        diaVencimento.add("18");
        diaVencimento.add("19");
        diaVencimento.add("20");
        diaVencimento.add("21");
        diaVencimento.add("22");
        diaVencimento.add("23");
        diaVencimento.add("24");
        diaVencimento.add("25");
        diaVencimento.add("26");
        diaVencimento.add("27");
        diaVencimento.add("28");
        diaVencimento.add("29");
        diaVencimento.add("30");

        diaVencimentoAlterarCliente.setItems(diaVencimento);

        FXMLbuscarCliente fXMLbuscarCliente = new FXMLbuscarCliente();
        try {
            alterarCliente();
        } catch (IOException ex) {
            Logger.getLogger(FXMLalterarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        maskCpf();
        maskTel9Dig();
    }

    @FXML
    public void alterarCliente() throws IOException {
        ParametroCliente parametroCliente = new ParametroCliente();
        Mensalidade mensalidade = new Mensalidade();
        MensalidadeDAO mensalidadeDao = new MensalidadeDAO();
        
        Cliente cliente;
        cliente = parametroCliente.getClienteParametro(); //coleta cliente usando a classe clienteparametro;
        mensalidade = mensalidadeDao.pegarMensalidadeClienteSelecionado(cliente.getId());
        idClienteAlterar = cliente.getId();
        System.err.println(idClienteAlterar);
        parametroCliente.setClienteParametro(null);
        nomeAlterarCliente.setText(cliente.getNome());
        CPFalterarCliente.setText(cliente.getCpf());
        emailAlterarCliente.setText(cliente.getEmail());
        telefoneAlterarCliente.setText(cliente.getContato());
        CEPAlterarCliente.setText(cliente.getCEP());
        observacaoAlterarCliente.setText(cliente.getObservacao());
        enderecoAlterarCliente.setText(cliente.getEndereco());
        numeroAlterarCliente.setText(cliente.getNumero());
        bairroAlterarCliente.setText(cliente.getBairro());
        cidadeAlterarCliente.setText(cliente.getCidade());
        dataCadastro = cliente.getDataNascimento();

        ufAlterarCliente.getSelectionModel().select(cliente.getEstado());
        horaTreinoAlterarCliente.getSelectionModel().select(cliente.getHoraTreino().substring(0, 2));
        minutoTreinoAlterarCliente.getSelectionModel().select(cliente.getHoraTreino().subSequence(3, 5));

        if (cliente.getStatus()) {
            statusAtivoAlterarCliente.setSelected(true);
            statusInativoAlterarCliente.setSelected(false);
        } else {
            statusInativoAlterarCliente.setSelected(true);
            statusAtivoAlterarCliente.setSelected(false);
        }

        dataNascimentoAlterarCliente.setValue(cliente.getDataNascimento());
        
        diaVencimentoAlterarCliente.getSelectionModel().select(mensalidade.getDiaVencimento() - 1);
        String valor = String.valueOf(mensalidade.getValor());
        valorMensalidadeAlterarCliente.setText(valor);
        
    }

    @FXML
    public void clicaAtivo(ActionEvent action) {
        statusInativoAlterarCliente.setSelected(false);
        statusAtivoAlterarCliente.setSelected(true);
    }

    @FXML
    public void clicaInativo(ActionEvent action) {
        statusInativoAlterarCliente.setSelected(true);
        statusAtivoAlterarCliente.setSelected(false);

    }

    @FXML
    public void alterarCadastroCliente(ActionEvent event) {
        Cliente cliente = new Cliente();
        Mensalidade mensalidade = new Mensalidade();
        
        cliente = coletarDadosCliente(); //coletar todos os dados do cliente, inserir em um cliente, e mandar para o banco.
        mensalidade = coletarDadosMensalidadeCliente();
        
        Negocio_Cliente negocioCliente = new Negocio_Cliente();
        if (validarCampos()) {
            if (negocioCliente.alterarCliente(cliente, mensalidade)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alteração Dados de Cliente");
                alert.setHeaderText("Cliente " + cliente.getNome() + " Alterado Com Sucesso!");
                alert.show();

                limparFormulario();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alteração Dados de Cliente");
                alert.setHeaderText("Erro ao Alterar o Cliente  " + cliente.getNome() + " ! \nPor Favor, verifique os dados informados.");
                alert.show();

            }
        }
    }

    @FXML
    public void cancelarAlteracaoCliente(ActionEvent event) {
        limparFormulario();
    }

    protected Cliente coletarDadosCliente() {
        if (validarCampos()) {
            String nome = (nomeAlterarCliente.getText());
            String cpf = (CPFalterarCliente.getText());
            String contato = (telefoneAlterarCliente.getText());
            String endereco = (enderecoAlterarCliente.getText());
            String bairro = (bairroAlterarCliente.getText());
            String CEP = (CEPAlterarCliente.getText());
            String observacao = observacaoAlterarCliente.getText();
            String email = (emailAlterarCliente.getText());
            String estado = (String) ufAlterarCliente.getValue();
            String numero = (numeroAlterarCliente.getText());
            String cidade = cidadeAlterarCliente.getText();
            String horarioTreino = (String) horaTreinoAlterarCliente.getValue();
            horarioTreino.concat(":");
            horarioTreino.concat((String) minutoTreinoAlterarCliente.getValue());

            System.out.println(horarioTreino);
            boolean status;
            if (statusAtivoAlterarCliente.isSelected()) {
                status = true;
            } else {
                status = false;
            }

            LocalDate dataNascimento = dataNascimentoAlterarCliente.getValue();
            Cliente cliente;
            cliente = new Cliente(nome, cpf, contato, observacao, endereco, bairro, cidade, estado,
                    status, email, CEP, numero, idClienteAlterar ,dataCadastro, dataNascimento, horarioTreino);

            System.out.println(cliente.getNome() + " " + cliente.getEstado() + " " + cliente.getDataCadastro());
            return cliente;
        }
        return null;

    }

    private Mensalidade coletarDadosMensalidadeCliente(){
        if(!valorMensalidadeAlterarCliente.getText().isEmpty()){
            float valor =  (float) Double.parseDouble(valorMensalidadeAlterarCliente.getText());
            String dia = diaVencimentoAlterarCliente.getValue().toString();
            int diaVencimento = Integer.parseInt(dia);
            Mensalidade mensalidade = new Mensalidade(valor, diaVencimento);
            return mensalidade;
        }
        return null;
    }
    
    private void maskCpf() {

        CPFalterarCliente.setOnKeyTyped((KeyEvent evento) -> {
            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }

            if (evento.getCharacter().trim().length() == 0) {

                switch (CPFalterarCliente.getText().length()) {
                    case 11:
                        CPFalterarCliente.setText(CPFalterarCliente.getText().substring(0, 9));
                        CPFalterarCliente.positionCaret(CPFalterarCliente.getText().length());
                        break;
                    case 7:
                        CPFalterarCliente.setText(CPFalterarCliente.getText().substring(0, 6));
                        CPFalterarCliente.positionCaret(CPFalterarCliente.getText().length());
                        break;
                    case 3:
                        CPFalterarCliente.setText(CPFalterarCliente.getText().substring(0, 2));
                        CPFalterarCliente.positionCaret(CPFalterarCliente.getText().length());
                        break;
                }

            } else if (CPFalterarCliente.getText().length() == 14) {
                evento.consume();
            }
            switch (CPFalterarCliente.getText().length()) {
                case 3:
                    CPFalterarCliente.setText(CPFalterarCliente.getText() + ".");
                    CPFalterarCliente.positionCaret(CPFalterarCliente.getText().length());
                    break;
                case 7:
                    CPFalterarCliente.setText(CPFalterarCliente.getText() + ".");
                    CPFalterarCliente.positionCaret(CPFalterarCliente.getText().length());
                    break;
                case 11:
                    CPFalterarCliente.setText(CPFalterarCliente.getText() + "-");
                    CPFalterarCliente.positionCaret(CPFalterarCliente.getText().length());
                    break;
            }

        });
    }

    private void maskTel9Dig() {
        telefoneAlterarCliente.setOnKeyTyped((KeyEvent evento) -> {
            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }

            if (evento.getCharacter().trim().length() == 0) {

                switch (telefoneAlterarCliente.getText().length()) {
                    case 1:
                        telefoneAlterarCliente.setText("");
                        telefoneAlterarCliente.positionCaret(telefoneAlterarCliente.getText().length());
                        break;
                    case 3:
                        telefoneAlterarCliente.setText(telefoneAlterarCliente.getText().substring(0, 2));
                        telefoneAlterarCliente.positionCaret(telefoneAlterarCliente.getText().length());
                        break;
                    case 10:
                        telefoneAlterarCliente.setText(telefoneAlterarCliente.getText().substring(0, 9));
                        telefoneAlterarCliente.positionCaret(telefoneAlterarCliente.getText().length());
                        break;
                }
            } else if (telefoneAlterarCliente.getText().length() == 15) {
                evento.consume();
            }
            switch (telefoneAlterarCliente.getText().length()) {
                case 1:
                    telefoneAlterarCliente.setText("(" + telefoneAlterarCliente.getText());
                    telefoneAlterarCliente.positionCaret(telefoneAlterarCliente.getText().length());
                    break;
                case 3:
                    telefoneAlterarCliente.setText(telefoneAlterarCliente.getText() + ") ");
                    telefoneAlterarCliente.positionCaret(telefoneAlterarCliente.getText().length());
                    break;
                case 10:
                    telefoneAlterarCliente.setText(telefoneAlterarCliente.getText() + "-");
                    telefoneAlterarCliente.positionCaret(telefoneAlterarCliente.getText().length());
                    break;
            }

        });
    }

    @FXML
    private boolean validarCampos() {
        String e = "";

        if (dataNascimentoAlterarCliente.getValue() == null || dataNascimentoAlterarCliente.getValue().isAfter(LocalDate.now())) {
            e += "Data Nascimento Inválida\n";

        }

        if (nomeAlterarCliente.getText().isEmpty()) {
            e += "Nome Informado Inválido!\n";

        }
        if (CPFalterarCliente.getText().isEmpty()) {
            e += "CPF Inválido! \n";

        }
        if (!emailAlterarCliente.getText().contains("@")) {
            e += "Email Inválido!\n";
        }

        if (telefoneAlterarCliente.getText().isEmpty()) {
            e += "Contato Inválido! \n";

        }

        if (CEPAlterarCliente.getText().isEmpty()) {
            e += "CEP Inválido! \n";

        }

        if (enderecoAlterarCliente.getText().isEmpty()) {
            e += "Endereço Inválido! \n";

        }

        if (bairroAlterarCliente.getText().isEmpty()) {
            e += "Bairro Inválido! \n";

        }

        if (ufAlterarCliente.getValue() == null) {
            e += "Estado Inválido! \n";

        }

        if (cidadeAlterarCliente.getText().isEmpty()) {
            e += "Município Inválido! \n";

        }

        if (horaTreinoAlterarCliente.getValue() == null) {
            e += "Hora Treino Inválida! \n";

        }

        if (minutoTreinoAlterarCliente.getValue() == null) {
            e += "Minuto Treino Inválido! \n";

        }
        
        if(valorMensalidadeAlterarCliente.getText().isEmpty()){
            e += "Valor Mensalidade Inválido!\n";
        }
        if (e.length() == 0) {
            return true;
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Campos Inválidos");
            alerta.setHeaderText("Por favor, corrija os campos inválidos!");
            alerta.setContentText(e);
            alerta.initStyle(StageStyle.DECORATED);
            alerta.showAndWait();
            return false;
        }
    }

    private void limparFormulario() {
        nomeAlterarCliente.setText("");
        emailAlterarCliente.setText("");
        CPFalterarCliente.setText("");
        dataNascimentoAlterarCliente.setValue(LocalDate.now());
        telefoneAlterarCliente.setText("");
        CEPAlterarCliente.setText("");
        observacaoAlterarCliente.setText("");
        enderecoAlterarCliente.setText("");
        numeroAlterarCliente.setText("");
        bairroAlterarCliente.setText("");
        cidadeAlterarCliente.setText("");
        ufAlterarCliente.getSelectionModel().select(15);
        horaTreinoAlterarCliente.getSelectionModel().select(-1);
        minutoTreinoAlterarCliente.getSelectionModel().select(-1);
        diaVencimentoAlterarCliente.getSelectionModel().select(0);
        valorMensalidadeAlterarCliente.setText("");
    }

    @FXML
    public void AcaoBotaEnter() {
        botaoSalvarAlterarCliente.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode() == KeyCode.ENTER) {
                ActionEvent event = null;
                alterarCadastroCliente(event);

            }
        });
    }

    @FXML
    public void AcaoBotaoCancelar() {
        botaoCancelarAlterarCliente.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode() == KeyCode.ENTER) {
                ActionEvent event = null;
                cancelarAlteracaoCliente(event);
            }
        });
    }
}


