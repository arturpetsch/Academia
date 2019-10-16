/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class BarraDeProgressoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ProgressBar barraProgresso;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        run();
    }    
    
    @FXML
    protected void run(){
        
    }
    
    @FXML
    protected void close(boolean fechar){
        if(fechar){
            Stage stage = (Stage) barraProgresso.getScene().getWindow();
        stage.close();
        }
    }
}
