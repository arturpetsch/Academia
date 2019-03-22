/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes_academia;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Artur
 */
public class AvaliacaoReabilitacao {
    
    private Cliente cliente;
    private Integer idade;
    private int idAvaliacaoReabilitacao;
    private LocalDate data_hora;
    private SimpleStringProperty descricao;
    private SimpleStringProperty tratamento_anterior;
    private SimpleStringProperty exercicios;
    private SimpleStringProperty medicamentos;

    public AvaliacaoReabilitacao() {
        descricao = new SimpleStringProperty();
        tratamento_anterior = new SimpleStringProperty();
        exercicios = new SimpleStringProperty();
        medicamentos = new SimpleStringProperty();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public int getIdAvaliacaoReabilitacao() {
        return idAvaliacaoReabilitacao;
    }

    public void setIdAvaliacaoReabilitacao(int idAvaliacaoReabilitacao) {
        this.idAvaliacaoReabilitacao = idAvaliacaoReabilitacao;
    }

    public LocalDate getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDate data_hora) {
        this.data_hora = data_hora;
    }

    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public String getTratamento_anterior() {
        return tratamento_anterior.get();
    }

    public void setTratamento_anterior(String tratamento_anterior) {
        this.tratamento_anterior.set(tratamento_anterior);
    }

    public String getExercicios() {
        return exercicios.get();
    }

    public void setExercicios(String exercicios) {
        this.exercicios.set(exercicios);
    }

    public String getMedicamentos() {
        return medicamentos.get();
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos.set(medicamentos);
    }
    
    
    
    
}
