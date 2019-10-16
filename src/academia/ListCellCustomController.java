package academia;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import classes_academia.ContaReceber;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import academia.ListCellCustomController;
import java.time.format.DateTimeFormatter;
import javafx.scene.paint.Color;
/**
 * FXML Controller class
 *
 * @author Artur
 */
public class ListCellCustomController extends ListCell<ContaReceber> {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label descricao;
    
    @FXML
    private Label dataVencimento;
    
    @FXML
    private Label valor;
    
    private FXMLLoader mLoader;
        
    @FXML
    private AnchorPane grid;
    
    
    @Override
    public void updateItem(ContaReceber cR, boolean empty){
        super.updateItem(cR, empty);
        
        if(empty || cR == null){
            
        } else{
            if(cR.getDataVencimento().isBefore(LocalDate.now())){
                  setText("**Vencida\n" 
                          + cR.getCliente().getNome() + "\n"+cR.getDescricao() + "\n"
                          + cR.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) 
                          + "  \t\t     R$" + cR.getValor() + "0");
                  setTextFill(Color.RED);
            }else{
                  setText("**A Vencer\n" 
                          + cR.getCliente().getNome() + "\n"+cR.getDescricao() + "\n"
                          + cR.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) 
                          + "  \t\t     R$" + cR.getValor() + "0");
                  setTextFill(Color.GREEN);
            }
        }
    }

}
