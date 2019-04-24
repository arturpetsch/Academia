/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Avaliacao_Fisica;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

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
        imageView.setImage(null);
        i = 0;
        if(imagens.size()>0){ 
            imagem = new Image(imagens.get(i).toURI().toString());
            imageView.setImage(imagem);
            i++;
        }
    }
    
    /**
     * Metodo que atualiza a foto que está sendo vista no momento.
     * @param action 
     */
    @FXML
    private void atualizarFoto(ActionEvent action){
        if(i<=0){
            i=1;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*"));
        File imagemParaAtualizar = fileChooser.showOpenDialog(null);
        String nome = imagens.get(i-1).getName();
        System.err.println("nome imagem atual:" + nome);
        imagemParaAtualizar.renameTo(new File("C:\\Users\\Public\\Documents\\Fotos Academia" + "\\" + nome));
        imagens.remove(i-1);
        imagens.add(i-1, imagemParaAtualizar);
        visualizarImagem();
        
    }      

    /**
     * Metodo utilizado para excluir a foto
     * @param action 
     */
    @FXML
    private void excluirFoto(ActionEvent action){
        if(i<=0){
            i=1;
        }
        if(imagens.get(i-1).delete()){
            imagens.remove(i-1);
            visualizarImagem();
            
            Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
            confirmacao.setTitle("Deletar Imagem");
            confirmacao.setHeaderText("Imagem excluída com sucesso!");
            confirmacao.showAndWait();
            
        }else{
            Alert confirmacao = new Alert(Alert.AlertType.ERROR);
            confirmacao.setTitle("Deletar Imagem");
            confirmacao.setHeaderText("Não foi possível excluir a imagem selecionada!");
            confirmacao.showAndWait();
        }
    }
    
    public void setImagem(List<File> imagens){
        this.imagens = imagens;
        if(imagens.size() == 0){
            botaoAvanca.setVisible(false);
            botaoVolta.setVisible(false);
            botaoAtualizar.setVisible(false);
            botaoExcluir.setVisible(false);
        }
        visualizarImagem();
    }
    
    
    public List<File> getImagens(){
        return this.imagens;
    }
}
