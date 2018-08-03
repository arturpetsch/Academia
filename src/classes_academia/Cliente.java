/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes_academia;

import java.time.LocalDate;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Artur
 */
public class Cliente {
    private SimpleStringProperty nome;
    private SimpleStringProperty cpf;
    private SimpleStringProperty contato;
    private SimpleStringProperty observacao;
    private SimpleStringProperty endereco;
    private SimpleStringProperty bairro;
    private SimpleStringProperty cidade;
    private SimpleStringProperty estado;
    private SimpleBooleanProperty status;
    private SimpleStringProperty email;
    private SimpleStringProperty CEP;
    private SimpleStringProperty numero;
    private int id;
    private LocalDate dataCadastro;
    private LocalDate dataNascimento;
    private SimpleStringProperty horaTreino;
    private SimpleBooleanProperty sexo;
    private SimpleBooleanProperty treinoSegunda;
    private SimpleBooleanProperty treinoTerca;
    private SimpleBooleanProperty treinoQuarta;
    private SimpleBooleanProperty treinoQuinta;
    private SimpleBooleanProperty treinoSexta;
    private SimpleBooleanProperty treinoSabado;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String contato, String observacao, String endereco, String bairro, String cidade, String estado,
            boolean status, String email, String CEP, String numero, int id, LocalDate dataCadastro, LocalDate dataNascimento, String horaTreino, boolean sexo
            , boolean treinoSegunda, boolean treinoTerca, boolean treinoQuarta, boolean treinoQuinta, boolean treinoSexta, boolean treinoSabado) {
        this.nome = new SimpleStringProperty(nome);
        this.cpf = new SimpleStringProperty(cpf);
        this.contato = new SimpleStringProperty(contato);
        this.observacao = new SimpleStringProperty(observacao);
        this.endereco = new SimpleStringProperty(endereco);
        this.bairro = new SimpleStringProperty(bairro);
        this.cidade = new SimpleStringProperty(cidade);
        this.estado = new SimpleStringProperty(estado);
        this.status = new SimpleBooleanProperty(status);
        this.email = new SimpleStringProperty(email);
        this.CEP = new SimpleStringProperty(CEP);
        this.numero = new SimpleStringProperty(numero);
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.dataNascimento = dataNascimento;
        this.horaTreino = new SimpleStringProperty(horaTreino);
        this.sexo = new SimpleBooleanProperty(sexo);
        this.treinoSegunda = new SimpleBooleanProperty(treinoSegunda);
        this.treinoTerca = new SimpleBooleanProperty(treinoTerca);
        this.treinoQuarta = new SimpleBooleanProperty(treinoQuarta);
        this.treinoQuinta = new SimpleBooleanProperty(treinoQuinta);
        this.treinoSexta = new SimpleBooleanProperty(treinoSexta);
        this.treinoSabado = new SimpleBooleanProperty(treinoSabado);
    }

    public Cliente(String nome, String cpf, String contato, String observacao, String endereco, String bairro, String cidade, String estado,
            boolean status, String email, String CEP, String numero, LocalDate dataCadastro, LocalDate dataNascimento, String horaTreino, boolean sexo,
             boolean treinoSegunda, boolean treinoTerca, boolean treinoQuarta, boolean treinoQuinta, boolean treinoSexta, boolean treinoSabado) {
        this.nome = new SimpleStringProperty(nome);
        this.cpf = new SimpleStringProperty(cpf);
        this.contato = new SimpleStringProperty(contato);
        this.observacao = new SimpleStringProperty(observacao);
        this.endereco = new SimpleStringProperty(endereco);
        this.bairro = new SimpleStringProperty(bairro);
        this.cidade = new SimpleStringProperty(cidade);
        this.estado = new SimpleStringProperty(estado);
        this.status = new SimpleBooleanProperty(status);
        this.email = new SimpleStringProperty(email);
        this.CEP = new SimpleStringProperty(CEP);
        this.numero = new SimpleStringProperty(numero);
        this.dataCadastro = dataCadastro;
        this.dataNascimento = dataNascimento;
        this.horaTreino = new SimpleStringProperty(horaTreino);
        this.sexo = new SimpleBooleanProperty(sexo);
        this.treinoSegunda = new SimpleBooleanProperty(treinoSegunda);
        this.treinoTerca = new SimpleBooleanProperty(treinoTerca);
        this.treinoQuarta = new SimpleBooleanProperty(treinoQuarta);
        this.treinoQuinta = new SimpleBooleanProperty(treinoQuinta);
        this.treinoSexta = new SimpleBooleanProperty(treinoSexta);
        this.treinoSabado = new SimpleBooleanProperty(treinoSabado);
    }

    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(SimpleStringProperty nome) {
        this.nome = nome;
    }

    public String getHoraTreino(){
        return horaTreino.get();
    }
    
    public void setHoraTreino(SimpleStringProperty horaTreino){
        this.horaTreino = horaTreino;
    }
    
    public String getCpf() {
        return cpf.get();
    }

    public void setCpf(SimpleStringProperty cpf) {
        this.cpf = cpf;
    }

    public String getContato() {
        return contato.get();
    }

    public void setContato(SimpleStringProperty contato) {
        this.contato = contato;
    }

    public String getObservacao() {
        return observacao.get();
    }

    public void setObservacao(SimpleStringProperty observacao) {
        this.observacao = observacao;
    }

    public String getEndereco() {
        return endereco.get();
    }

    public void setEndereco(SimpleStringProperty endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro.get();
    }

    public void setBairro(SimpleStringProperty bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade.get();
    }

    public void setCidade(SimpleStringProperty cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado.get();
    }

    public void setEstado(SimpleStringProperty estado) {
        this.estado = estado;
    }

    public boolean getStatus() {
        return status.get();
    }

    public void setStatus(SimpleBooleanProperty status) {
        this.status = status;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }

    public String getCEP() {
        return CEP.get();
    }

    public void setCEP(SimpleStringProperty CEP) {
        this.CEP = CEP;
    }

    public String getNumero() {
        return numero.get();
    }

    public void setNumero(SimpleStringProperty numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getSexo() {
        return sexo.get();
    }

    public void setSexo(SimpleBooleanProperty sexo) {
        this.sexo = sexo;
    }

    public boolean getTreinoSegunda() {
        return treinoSegunda.get();
    }

    public void setTreinoSegunda(SimpleBooleanProperty treinoSegunda) {
        this.treinoSegunda = treinoSegunda;
    }

    public boolean getTreinoTerca() {
        return treinoTerca.get();
    }

    public void setTreinoTerca(SimpleBooleanProperty treinoTerca) {
        this.treinoTerca = treinoTerca;
    }

    public boolean getTreinoQuarta() {
        return treinoQuarta.get();
    }

    public void setTreinoQuarta(SimpleBooleanProperty treinoQuarta) {
        this.treinoQuarta = treinoQuarta;
    }

    public boolean getTreinoQuinta() {
        return treinoQuinta.get();
    }

    public void setTreinoQuinta(SimpleBooleanProperty treinoQuinta) {
        this.treinoQuinta = treinoQuinta;
    }

    public boolean getTreinoSexta() {
        return treinoSexta.get();
    }

    public void setTreinoSexta(SimpleBooleanProperty treinoSexta) {
        this.treinoSexta = treinoSexta;
    }

    public boolean getTreinoSabado() {
        return treinoSabado.get();
    }

    public void setTreinoSabado(SimpleBooleanProperty treinoSabado) {
        this.treinoSabado = treinoSabado;
    }
    
    
}
