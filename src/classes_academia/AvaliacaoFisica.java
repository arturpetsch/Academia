/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes_academia;

import java.time.LocalDate;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Artur
 */
public class AvaliacaoFisica {
    
    private SimpleDoubleProperty peso;
    private SimpleDoubleProperty altura;
    private Cliente cliente;
    private SimpleDoubleProperty torax;
    private SimpleDoubleProperty torax2;
    private SimpleDoubleProperty cintura;
    private SimpleDoubleProperty abdominal;
    private SimpleDoubleProperty quadril;
    private SimpleDoubleProperty bisseps_direito;
    private SimpleDoubleProperty bisseps_esquerdo;
    private SimpleDoubleProperty coxa_direita;
    private SimpleDoubleProperty coxa_esquerda;
    private SimpleDoubleProperty perna_direita;
    private SimpleDoubleProperty perna_esquerda;
    private SimpleDoubleProperty perc_gordura;
    private SimpleDoubleProperty perc_livre_gordura;
    private SimpleDoubleProperty perc_ideal_gordura;
    private SimpleDoubleProperty perc_gord_sobra;
    private SimpleDoubleProperty imc;
    private SimpleDoubleProperty rc_q;
    private SimpleDoubleProperty peso_gordura;
    private SimpleDoubleProperty peso_magro;
    private SimpleDoubleProperty peso_excedente;
    private LocalDate data_hora;
    private Integer idade;
    private SimpleDoubleProperty pressao_arterial;
    private SimpleDoubleProperty f_c_repouso;
    private SimpleDoubleProperty f_c_max;
    private SimpleDoubleProperty f_c_res;
    private SimpleDoubleProperty fct_inf;
    private SimpleDoubleProperty fct_sup;
    private SimpleDoubleProperty perc_int_inf;
    private SimpleDoubleProperty perc_int_sup;
    private SimpleStringProperty d_c;
    private SimpleStringProperty hipertensao;
    private SimpleStringProperty diabetes;
    private SimpleStringProperty fumante;
    private SimpleStringProperty bebida_alcoolica;
    private SimpleStringProperty d_c_familiar;
    private SimpleStringProperty obesidade_familiar;
    private SimpleStringProperty hipertensao_familiar;
    private SimpleStringProperty diabetes_familiar;
    private SimpleStringProperty prat_atividade_fisica;
    private SimpleStringProperty modalidade;
    private SimpleStringProperty frequencia_semanal_atividade;
    private SimpleStringProperty medicamentos;
    private SimpleStringProperty outros;
    private SimpleStringProperty objetivos;
    private SimpleDoubleProperty duracao;
    private int idAvaliacaoFisica;

    public AvaliacaoFisica() {
       peso = new SimpleDoubleProperty();
       altura = new SimpleDoubleProperty();
        torax = new SimpleDoubleProperty();
        torax2 = new SimpleDoubleProperty();
        cintura = new SimpleDoubleProperty();
        abdominal = new SimpleDoubleProperty();
        quadril = new SimpleDoubleProperty();
        bisseps_direito = new SimpleDoubleProperty();
        bisseps_esquerdo = new SimpleDoubleProperty();
        coxa_direita = new SimpleDoubleProperty();
         coxa_esquerda = new SimpleDoubleProperty();
        perna_direita = new SimpleDoubleProperty();
        perna_esquerda = new SimpleDoubleProperty();
        perc_gordura = new SimpleDoubleProperty();
        perc_livre_gordura = new SimpleDoubleProperty();
        perc_ideal_gordura = new SimpleDoubleProperty();
        perc_gord_sobra  = new SimpleDoubleProperty();
        imc = new SimpleDoubleProperty();
        rc_q = new SimpleDoubleProperty();
        peso_gordura = new SimpleDoubleProperty();
        peso_magro = new SimpleDoubleProperty();
        peso_excedente = new SimpleDoubleProperty();
        pressao_arterial = new SimpleDoubleProperty();
        f_c_repouso = new SimpleDoubleProperty();
        f_c_max = new SimpleDoubleProperty();
        f_c_res = new SimpleDoubleProperty();
        fct_inf = new SimpleDoubleProperty();
        fct_sup = new SimpleDoubleProperty();
        perc_int_inf = new SimpleDoubleProperty();
        perc_int_sup = new SimpleDoubleProperty();
        d_c = new SimpleStringProperty();
        hipertensao = new SimpleStringProperty();
        diabetes = new SimpleStringProperty();
        fumante = new SimpleStringProperty();
        bebida_alcoolica = new SimpleStringProperty();
        d_c_familiar = new SimpleStringProperty();
        obesidade_familiar = new SimpleStringProperty();
        hipertensao_familiar = new SimpleStringProperty();
        diabetes_familiar = new SimpleStringProperty();
        prat_atividade_fisica = new SimpleStringProperty();
        modalidade = new SimpleStringProperty("");
        frequencia_semanal_atividade = new SimpleStringProperty("");
        duracao = new SimpleDoubleProperty(0.00);
        medicamentos = new SimpleStringProperty("");
        outros = new SimpleStringProperty("");
        objetivos = new SimpleStringProperty("");
    }

    
    public int getIdAvaliacaoFisica() {
        return idAvaliacaoFisica;
    }

    public void setIdAvaliacaoFisica(int idAvaliacaoFisica) {
        this.idAvaliacaoFisica = idAvaliacaoFisica;
    }
    
    
    public Double getPeso() {
        return peso.get();
    }

    public void setPeso(Double peso) {
        this.peso.set(peso);
    }

    public Double getAltura() {
        return altura.get();
    }

    public void setAltura(Double altura) {
        this.altura.set(altura);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getTorax() {
        return torax.get();
    }

    public void setTorax(Double torax) {
        this.torax.set(torax);
    }

    public Double getTorax2() {
        return torax2.get();
    }

    public void setTorax2(Double torax2) {
        this.torax2.set(torax2);
    }

    public Double getCintura() {
        return cintura.get();
    }

    public void setCintura(Double cintura) {
        this.cintura.set(cintura);
    }

    public Double getAbdominal() {
        return abdominal.get();
    }

    public void setAbdominal(Double abdominal) {
        this.abdominal.set(abdominal);
    }

    public Double getQuadril() {
        return quadril.get();
    }

    public void setQuadril(Double quadril) {
        this.quadril.set(quadril);
    }

    public Double getBisseps_direito() {
        return bisseps_direito.get();
    }

    public void setBisseps_direito(Double bisseps_direito) {
        this.bisseps_direito.set(bisseps_direito);
    }

    public Double getBisseps_esquerdo() {
        return bisseps_esquerdo.get();
    }

    public void setBisseps_esquerdo(Double bisseps_esquerdo) {
        this.bisseps_esquerdo.set(bisseps_esquerdo);
    }

    public Double getCoxa_direita() {
        return coxa_direita.get();
    }

    public void setCoxa_direita(Double coxa_direita) {
        this.coxa_direita.set(coxa_direita);
    }

    public Double getCoxa_esquerda() {
        return coxa_esquerda.get();
    }

    public void setCoxa_esquerda(Double coxa_esquerda) {
        this.coxa_esquerda.set(coxa_esquerda);
    }

    public Double getPerna_direita() {
        return perna_direita.get();
    }

    public void setPerna_direita(Double perna_direita) {
        this.perna_direita.set(perna_direita);
    }

    public Double getPerna_esquerda() {
        return perna_esquerda.get();
    }

    public void setPerna_esquerda(Double perna_esquerda) {
        this.perna_esquerda.set(perna_esquerda);
    }

    public Double getPerc_gordura() {
        return perc_gordura.get();
    }

    public void setPerc_gordura(Double perc_gordura) {
        this.perc_gordura.set(perc_gordura);
    }

    public Double getPerc_livre_gordura() {
        return perc_livre_gordura.get();
    }

    public void setPerc_livre_gordura(Double perc_livre_gordura) {
        this.perc_livre_gordura.set(perc_livre_gordura);
    }

    public Double getPerc_ideal_gordura() {
        return perc_ideal_gordura.get();
    }

    public void setPerc_ideal_gordura(Double perc_ideal_gordura) {
        this.perc_ideal_gordura.set(perc_ideal_gordura);
    }

    public Double getPerc_gord_sobra() {
        return perc_gord_sobra.get();
    }

    public void setPerc_gord_sobra(Double perc_gord_sobra) {
        this.perc_gord_sobra.set(perc_gord_sobra);
    }

    public Double getImc() {
        return imc.get();
    }

    public void setImc(Double imc) {
        this.imc.set(imc);
    }

    public Double getRc_q() {
        return rc_q.get();
    }

    public void setRc_q(Double rc_q) {
        this.rc_q.set(rc_q);
    }

    public Double getPeso_gordura() {
        return peso_gordura.get();
    }

    public void setPeso_gordura(Double peso_gordura) {
        this.peso_gordura.set(peso_gordura);
    }

    public Double getPeso_magro() {
        return peso_magro.get();
    }

    public void setPeso_magro(Double peso_magro) {
        this.peso_magro.set(peso_magro);
    }

    public Double getPeso_excedente() {
        return peso_excedente.get();
    }

    public void setPeso_excedente(Double peso_excedente) {
        this.peso_excedente.set(peso_excedente);
    }

    public LocalDate getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDate data_hora) {
        this.data_hora = data_hora;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPressao_arterial() {
        return pressao_arterial.get();
    }

    public void setPressao_arterial(Double pressao_arterial) {
        this.pressao_arterial.set(pressao_arterial);
    }

    public Double getF_c_repouso() {
        return f_c_repouso.get();
    }

    public void setF_c_repouso(Double f_c_repouso) {
        this.f_c_repouso.set(f_c_repouso);
    }

    public Double getF_c_max() {
        return f_c_max.get();
    }

    public void setF_c_max(Double f_c_max) {
        this.f_c_max.set(f_c_max);
    }

    public Double getF_c_res() {
        return f_c_res.get();
    }

    public void setF_c_res(Double f_c_res) {
        this.f_c_res.set(f_c_res);
    }

    public Double getFct_inf() {
        return fct_inf.get();
    }

    public void setFct_inf(Double fct_inf) {
        this.fct_inf.set(fct_inf);
    }

    public Double getFct_sup() {
        return fct_sup.get();
    }

    public void setFct_sup(Double fct_sup) {
        this.fct_sup.set(fct_sup);
    }

    public Double getPerc_int_inf() {
        return perc_int_inf.get();
    }

    public void setPerc_int_inf(Double perc_int_inf) {
        this.perc_int_inf.set(perc_int_inf);
    }

    public Double getPerc_int_sup() {
        return perc_int_sup.get();
    }

    public void setPerc_int_sup(Double perc_int_sup) {
        this.perc_int_sup.set(perc_int_sup);
    }

    public String getD_c() {
        return d_c.get();
    }

    public void setD_c(String d_c) {
        this.d_c.set(d_c);
    }

    public String getHipertensao() {
        return hipertensao.get();
    }

    public void setHipertensao(String hipertensao) {
        this.hipertensao.set(hipertensao);
    }

    public String getDiabetes() {
        return diabetes.get();
    }

    public void setDiabetes(String diabetes) {
        this.diabetes.set(diabetes);
    }

    public String getFumante() {
        return fumante.get();
    }

    public void setFumante(String fumante) {
        this.fumante.set(fumante);
    }

    public String getBebida_alcoolica() {
        return bebida_alcoolica.get();
    }

    public void setBebida_alcoolica(String bebida_alcoolica) {
        this.bebida_alcoolica.set(bebida_alcoolica);
    }

    public String getD_c_familiar() {
        return d_c_familiar.get();
    }

    public void setD_c_familiar(String d_c_familiar) {
        this.d_c_familiar.set(d_c_familiar);
    }

    public String getObesidade_familiar() {
        return obesidade_familiar.get();
    }

    public void setObesidade_familiar(String obesidade_familiar) {
        this.obesidade_familiar.set(obesidade_familiar);
    }

    public String getHipertensao_familiar() {
        return hipertensao_familiar.get();
    }

    public void setHipertensao_familiar(String hipertensao_familiar) {
        this.hipertensao_familiar.set(hipertensao_familiar);
    }

    public String getDiabetes_familiar() {
        return diabetes_familiar.get();
    }

    public void setDiabetes_familiar(String diabetes_familiar) {
        this.diabetes_familiar.set(diabetes_familiar);
    }

    public String getPrat_atividade_fisica() {
        return prat_atividade_fisica.get();
    }

    public void setPrat_atividade_fisica(String prat_atividade_fisica) {
        this.prat_atividade_fisica.set(prat_atividade_fisica);
    }

    public String getModalidade() {
        return modalidade.get();
    }

    public void setModalidade(String modalidade) {
        this.modalidade.set(modalidade);
    }

    public String getFrequencia_semanal_atividade() {
        return frequencia_semanal_atividade.get();
    }

    public void setFrequencia_semanal_atividade(String frequencia_semanal_atividade) {
        this.frequencia_semanal_atividade.set(frequencia_semanal_atividade);
    }

    public String getMedicamentos() {
        return medicamentos.get();
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos.set(medicamentos);
    }

    public String getOutros() {
        return outros.get();
    }

    public void setOutros(String outros) {
        this.outros.set(outros);
    }

    public String getObjetivos() {
        return objetivos.get();
    }

    public void setObjetivos(String objetivos) {
        this.objetivos.set(objetivos);
    }

    public Double getDuracao() {
        return duracao.get();
    }

    public void setDuracao(Double duracao) {
        this.duracao.set(duracao);
    }
    
    
    
    
    
}
