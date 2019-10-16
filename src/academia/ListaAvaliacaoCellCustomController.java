/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia;

import classes_academia.AvaliacaoFisica;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Artur
 */
public class ListaAvaliacaoCellCustomController extends ListCell<AvaliacaoFisica>{
    @Override
    public void updateItem(AvaliacaoFisica avaliacao, boolean empty){
        super.updateItem(avaliacao, empty);
        
        if(empty || avaliacao == null){
            
        } else{ 
                  setText(
                          avaliacao.getCliente().getNome() 
                  + "\nData Prevista: " + avaliacao.getData_hora().plusMonths(3).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                  setTextFill(Color.DARKBLUE);
                  //setFont(Font.font(null, FontPosture.ITALIC, USE_PREF_SIZE));
        }
    }
}
