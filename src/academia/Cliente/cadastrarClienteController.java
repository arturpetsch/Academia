/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Cliente;

import classes_academia.Cliente;
import classes_academia.Mensalidade;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.StageStyle;
import javax.swing.text.MaskFormatter;
import negocio_academia.Negocio_Cliente;

/**
 * Classe responsável pelo cadastro de cliente, coleta de todos os dados e envio
 * para classe de negocio;
 *
 * @author Artur
 */
public class cadastrarClienteController implements Initializable {

    /**
     * Reponsável pela AnchorPane direito, onde são mostrados os campos para
     * cadastro de um cliente.
     */
    @FXML
    public TextField CPFcadastrarCliente;

    @FXML
    private TextField nomeCadastrarCliente;

    @FXML
    private TextField emailCadastrarCliente;

    @FXML
    private TextField telefoneCadastrarCliente;

    @FXML
    private TextField observacaoCadastrarCliente;

    @FXML
    private TextField CEPcadastrarCliente;

    @FXML
    private TextField enderecoCadastrarCliente;

    @FXML
    private TextField numeroCadastrarCliente;

    @FXML
    private TextField bairroCadastrarCliente;

    @FXML
    private TextField cidadeCadastrarCliente;

    @FXML
    private RadioButton statusAtivoCadastrarCliente;

    @FXML
    private RadioButton statusInativoCadastrarCliente;

    @FXML
    private RadioButton sexoMascCadastrarCliente;
    
    @FXML
    private RadioButton sexoFemCadastrarCliente;
    
    @FXML
    private ComboBox ufCadastrarCliente;

    @FXML
    private Button botaoCancelarCadastrarCliente;

    @FXML
    private Button botaoSalvarCadastrarCliente;

    @FXML
    private DatePicker dataNascimentoCadastrarCliente;

    @FXML
    private ComboBox horaTreinoCadastrarCliente;

    @FXML
    private ComboBox minutoTreinoCadastrarCliente;

    @FXML
    private ComboBox diaVencimentoCadastrarCliente;

    @FXML
    private TextField valorMensalidadeCadastrarCliente;

    @FXML
    private CheckBox segundaCadastrarCliente;
    
    @FXML
    private CheckBox tercaCadastrarCliente;
    
    @FXML
    private CheckBox quartaCadastrarCliente;
    
    @FXML
    private CheckBox quintaCadastrarCliente;
    
    @FXML
    private CheckBox sextaCadastrarCliente;
    
    @FXML
    private CheckBox sabadoCadastrarCliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dataNascimentoCadastrarCliente.setPromptText("  /  /");

        maskCpf();
        maskTel9Dig();

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

        ufCadastrarCliente.setItems(estados);
        ufCadastrarCliente.getSelectionModel().select(15);

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

        horaTreinoCadastrarCliente.setItems(horaTreino);

        ObservableList<String> minutoTreino = FXCollections.observableArrayList();
        minutoTreino.add("00");
        minutoTreino.add("15");
        minutoTreino.add("30");
        minutoTreino.add("45");

        minutoTreinoCadastrarCliente.setItems(minutoTreino);

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

        diaVencimentoCadastrarCliente.setItems(diaVencimento);
        diaVencimentoCadastrarCliente.getSelectionModel().select(0);

    }

    @FXML
    private void salvarCadastroCliente(ActionEvent action) {
        Cliente cliente = new Cliente();
        Mensalidade mensalidade = new Mensalidade();
        
        cliente = coletarDadosCliente(); //coletar todos os dados do cliente, inserir em um cliente, e mandar para o banco.
        mensalidade = coletarDadosMensalidadeCliente();
        Negocio_Cliente negocioCliente = new Negocio_Cliente();
        if (negocioCliente.cadastrarCliente(cliente, mensalidade)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Cliente");
            alert.setHeaderText("Cliente " + cliente.getNome() + " Cadastrado Com Sucesso!\nMensalidade Gerada!");
            alert.show();
            limparFormulario();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cadastro de Cliente");
            alert.setHeaderText("Erro ao Cadastrar o Cliente  " + cliente.getNome() + " ! \nPor Favor, verifique os dados informados.");
            alert.show();

        }
    }

    @FXML
    public void cancelarCadastroCliente(ActionEvent event) {
        limparFormulario();
    }

    protected Cliente coletarDadosCliente() {
        LocalDate dataCadastro = LocalDate.now();
        //ufCadastrarCliente = new ComboBox();
        if (validarCampos()) {
            String nome = (nomeCadastrarCliente.getText());
            String cpf = (CPFcadastrarCliente.getText());
            String contato = (telefoneCadastrarCliente.getText());
            String endereco = (enderecoCadastrarCliente.getText());
            String bairro = (bairroCadastrarCliente.getText());
            String CEP = (CEPcadastrarCliente.getText());
            String observacao = observacaoCadastrarCliente.getText();
            String email = (emailCadastrarCliente.getText());
            String estado = (String) ufCadastrarCliente.getValue();
            String numero = (numeroCadastrarCliente.getText());
            String cidade = cidadeCadastrarCliente.getText();

            String horarioTreino = (String) horaTreinoCadastrarCliente.getValue();
            horarioTreino = horarioTreino.concat(":");
            horarioTreino = horarioTreino.concat((String) minutoTreinoCadastrarCliente.getValue());

            System.out.println(horarioTreino);
            boolean status;
            if (statusAtivoCadastrarCliente.isSelected()) {
                status = true;
            } else {
                status = false;
            }
            
            boolean sexo;
            if(sexoMascCadastrarCliente.isSelected()){
                sexo = true;
            }else{
                sexo = false;
            }
            
            boolean segunda, terca, quarta, quinta, sexta, sabado;
            
            if(segundaCadastrarCliente.isSelected()){
                segunda = true;
            }else{
                segunda = false;
            }
            
            if(tercaCadastrarCliente.isSelected()){
                terca = true;
            }else{
                terca = false;
            }
            
            if(quartaCadastrarCliente.isSelected()){
                quarta = true;
            }else{
                quarta = false;
            }
            
            if(quintaCadastrarCliente.isSelected()){
                quinta = true;
            }else{
                quinta = false;
            }
            
            if(sextaCadastrarCliente.isSelected()){
                sexta = true;
            }else{
                sexta = false;
            }
            
            if(sabadoCadastrarCliente.isSelected()){
                sabado = true;
            }else{
                sabado = false;
            }
            
            
            LocalDate dataNascimento = dataNascimentoCadastrarCliente.getValue();
            Cliente cliente;
            cliente = new Cliente(nome, cpf, contato, observacao, endereco, bairro, cidade, estado,
                    status, email, CEP, numero, dataCadastro, dataNascimento, horarioTreino, sexo,
            segunda, terca, quarta, quinta, sexta, sabado);

            System.out.println(cliente.getNome() + " " + cliente.getEstado() + " " + cliente.getDataCadastro());
            return cliente;
        }
        return null;
    }

    private Mensalidade coletarDadosMensalidadeCliente(){
        if(!valorMensalidadeCadastrarCliente.getText().isEmpty()){
            double valor =   Double.parseDouble((valorMensalidadeCadastrarCliente.getText().replace(',', '.')));
            String dia = diaVencimentoCadastrarCliente.getValue().toString();
            int diaVencimento = Integer.parseInt(dia);
            Mensalidade mensalidade = new Mensalidade(valor, diaVencimento);
            return mensalidade;
        }
        return null;
    }
    
    @FXML
    public void clicaAtivo(ActionEvent action) {
        statusInativoCadastrarCliente.setSelected(false);
        statusAtivoCadastrarCliente.setSelected(true);
    }

    @FXML
    public void clicaInativo(ActionEvent action) {
        statusInativoCadastrarCliente.setSelected(true);
        statusAtivoCadastrarCliente.setSelected(false);

    }

    @FXML
    public void clicaMasculino(ActionEvent action){
        sexoMascCadastrarCliente.setSelected(true);
        sexoFemCadastrarCliente.setSelected(false);
    }
    
    @FXML
    public void clicaFeminino(ActionEvent action){
        sexoMascCadastrarCliente.setSelected(false);
        sexoFemCadastrarCliente.setSelected(true);
    }
    
    private void maskCpf() {

        CPFcadastrarCliente.setOnKeyTyped((KeyEvent evento) -> {
            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }

            if (evento.getCharacter().trim().length() == 0) {

                switch (CPFcadastrarCliente.getText().length()) {
                    case 11:
                        CPFcadastrarCliente.setText(CPFcadastrarCliente.getText().substring(0, 9));
                        CPFcadastrarCliente.positionCaret(CPFcadastrarCliente.getText().length());
                        break;
                    case 7:
                        CPFcadastrarCliente.setText(CPFcadastrarCliente.getText().substring(0, 6));
                        CPFcadastrarCliente.positionCaret(CPFcadastrarCliente.getText().length());
                        break;
                    case 3:
                        CPFcadastrarCliente.setText(CPFcadastrarCliente.getText().substring(0, 2));
                        CPFcadastrarCliente.positionCaret(CPFcadastrarCliente.getText().length());
                        break;
                }

            } else if (CPFcadastrarCliente.getText().length() == 14) {
                evento.consume();
            }
            switch (CPFcadastrarCliente.getText().length()) {
                case 3:
                    CPFcadastrarCliente.setText(CPFcadastrarCliente.getText() + ".");
                    CPFcadastrarCliente.positionCaret(CPFcadastrarCliente.getText().length());
                    break;
                case 7:
                    CPFcadastrarCliente.setText(CPFcadastrarCliente.getText() + ".");
                    CPFcadastrarCliente.positionCaret(CPFcadastrarCliente.getText().length());
                    break;
                case 11:
                    CPFcadastrarCliente.setText(CPFcadastrarCliente.getText() + "-");
                    CPFcadastrarCliente.positionCaret(CPFcadastrarCliente.getText().length());
                    break;
            }

        });
    }

    private void maskTel9Dig() {
        telefoneCadastrarCliente.setOnKeyTyped((KeyEvent evento) -> {
            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }

            if (evento.getCharacter().trim().length() == 0) {

                switch (telefoneCadastrarCliente.getText().length()) {
                    case 1:
                        telefoneCadastrarCliente.setText("");
                        telefoneCadastrarCliente.positionCaret(telefoneCadastrarCliente.getText().length());
                        break;
                    case 3:
                        telefoneCadastrarCliente.setText(telefoneCadastrarCliente.getText().substring(0, 2));
                        telefoneCadastrarCliente.positionCaret(telefoneCadastrarCliente.getText().length());
                        break;
                    case 10:
                        telefoneCadastrarCliente.setText(telefoneCadastrarCliente.getText().substring(0, 9));
                        telefoneCadastrarCliente.positionCaret(telefoneCadastrarCliente.getText().length());
                        break;
                }
            } else if (telefoneCadastrarCliente.getText().length() == 15) {
                evento.consume();
            }
            switch (telefoneCadastrarCliente.getText().length()) {
                case 1:
                    telefoneCadastrarCliente.setText("(" + telefoneCadastrarCliente.getText());
                    telefoneCadastrarCliente.positionCaret(telefoneCadastrarCliente.getText().length());
                    break;
                case 3:
                    telefoneCadastrarCliente.setText(telefoneCadastrarCliente.getText() + ") ");
                    telefoneCadastrarCliente.positionCaret(telefoneCadastrarCliente.getText().length());
                    break;
                case 10:
                    telefoneCadastrarCliente.setText(telefoneCadastrarCliente.getText() + "-");
                    telefoneCadastrarCliente.positionCaret(telefoneCadastrarCliente.getText().length());
                    break;
            }

        });
    }

    @FXML
    private boolean validarCampos() {
        String e = "";

        if (dataNascimentoCadastrarCliente.getValue() == null || dataNascimentoCadastrarCliente.getValue().isAfter(LocalDate.now())) {
            e += "Data Nascimento Inválida\n";

        }

        if (nomeCadastrarCliente.getText().isEmpty()) {
            e += "Nome Informado Inválido!\n";

        }
        if (CPFcadastrarCliente.getText().isEmpty()) {
            e += "CPF Inválido! \n";

        }
        if (!emailCadastrarCliente.getText().contains("@")) {
            e += "Email Inválido!\n";
        }

        if (telefoneCadastrarCliente.getText().isEmpty()) {
            e += "Contato Inválido! \n";

        }

        if (CEPcadastrarCliente.getText().isEmpty()) {
            e += "CEP Inválido! \n";

        }

        if (enderecoCadastrarCliente.getText().isEmpty()) {
            e += "Endereço Inválido! \n";

        }

        if (bairroCadastrarCliente.getText().isEmpty()) {
            e += "Bairro Inválido! \n";

        }

        if (ufCadastrarCliente.getValue() == null) {
            e += "Estado Inválido! \n";

        }

        if (cidadeCadastrarCliente.getText().isEmpty()) {
            e += "Município Inválido! \n";

        }

        if (horaTreinoCadastrarCliente.getValue() == null) {
            e += "Hora Treino Inválida! \n";

        }

        if (minutoTreinoCadastrarCliente.getValue() == null) {
            e += "Minuto Treino Inválido! \n";

        }
        
        if(valorMensalidadeCadastrarCliente.getText().isEmpty()){
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
        nomeCadastrarCliente.setText("");
        emailCadastrarCliente.setText("");
        CPFcadastrarCliente.setText("");
        dataNascimentoCadastrarCliente.setValue(LocalDate.now());
        telefoneCadastrarCliente.setText("");
        CEPcadastrarCliente.setText("");
        observacaoCadastrarCliente.setText("");
        enderecoCadastrarCliente.setText("");
        numeroCadastrarCliente.setText("");
        bairroCadastrarCliente.setText("");
        cidadeCadastrarCliente.setText("");
        ufCadastrarCliente.getSelectionModel().select(15);
        horaTreinoCadastrarCliente.getSelectionModel().select(-1);
        minutoTreinoCadastrarCliente.getSelectionModel().select(-1);
        diaVencimentoCadastrarCliente.getSelectionModel().select(0);
        valorMensalidadeCadastrarCliente.setText("");
        segundaCadastrarCliente.setSelected(false);
        tercaCadastrarCliente.setSelected(false);
        quartaCadastrarCliente.setSelected(false);
        quintaCadastrarCliente.setSelected(false);
        sextaCadastrarCliente.setSelected(false);
        sabadoCadastrarCliente.setSelected(false);
    }

    @FXML
    public void AcaoBotaEnter() {
        botaoSalvarCadastrarCliente.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode() == KeyCode.ENTER) {
                ActionEvent event = null;
                salvarCadastroCliente(event);
                System.out.println("AQUI NO ENTER");
            }
        });
    }

    @FXML
    public void AcaoBotaoCancelar() {
        botaoCancelarCadastrarCliente.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode() == KeyCode.ENTER) {
                ActionEvent event = null;
                cancelarCadastroCliente(event);
            }
        });
    }

}
