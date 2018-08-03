/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes_academia;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Artur
 */
public class Usuario {
    
    private SimpleStringProperty nome;
    private SimpleStringProperty senha;
    private String tipoUser;
    private SimpleStringProperty respostaSecreta;
    private int id;
    
    public Usuario(){
        this.nome = new SimpleStringProperty();
        this.senha =  new SimpleStringProperty();
        this.respostaSecreta = new SimpleStringProperty();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getSenha() {
        return senha.get();
    }

    public void setSenha(String senha) {
        this.senha.set(senha);
    }

    public String getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    public String getRespostaSecreta() {
        return respostaSecreta.get();
    }

    public void setRespostaSecreta(String respostaSecreta) {
        this.respostaSecreta.set(respostaSecreta);
    }
    
    
}
