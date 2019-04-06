/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Avaliacao_Fisica;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class VisualizarImagemController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    List<File> imagens = new ArrayList<>();
    
    @FXML
    private Button botaoVolta;
    
    @FXML
    private Button botaoAvanca;
    
    @FXML
    private Button botaoExcluir;
    
    @FXML
    private Button botaoAtualizar;
    
    @FXML
    private ImageView imageView;
    
    Image imagem;
    
    int i = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    
    
    @FXML
    private void proximaFoto(ActionEvent action){
        if(i < imagens.size()){
            imagem = new Image(imagens.get(i).toURI().toString());
            imageView.setImage(imagem);
            i++;
        }else{
            i = 0;
            imagem = new Image(imagens.get(i).toURI().toString());
            imageView.setImage(imagem);
            i++;
        }
    }
    
    @FXML
    private void fotoAnterior(ActionEvent action){
        if(i < imagens.size() && i >= 0){
            imagem = new Image(imagens.get(i).toURI().toString());
            imageView.setImage(imagem);
            i--;
        }else{
            i = 0;
            imagem = new Image(imagens.get(i).toURI().toString());
            imageView.setImage(imagem);
            i++;
        }
    }
    
    private void visualizarImagem(){
        if(imagens.size()>0){ 
            imagem = new Image(imagens.get(i).toURI().toString());
            imageView.setImage(imagem);
            i++;
        }
    }
    
    public void setImagem(List<File> imagens){
        this.imagens = imagens;
        visualizarImagem();
    }
}
