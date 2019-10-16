/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia;

import classes_academia.ContaPagar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;

/**
 *
 * @author Artur
 */
public class ListaContasCustomController extends ListCell<ContaPagar> {
    @Override
    public void updateItem(ContaPagar cP, boolean empty){
        super.updateItem(cP, empty);
        
        if(empty || cP == null){
            
        } else{
            if(cP.getDataVencimento().isBefore(LocalDate.now())){
                  setText("**Vencida\n" 
                          + cP.getFornecedor() + "\n"+cP.getDescricao() + "\n"
                          + cP.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) 
                          + "  \t\t     R$" + cP.getValor() + "0");
                  setTextFill(Color.RED);
            }else{
                  setText("**A Vencer\n" 
                          + cP.getFornecedor() + "\n"+cP.getDescricao() + "\n"
                          + cP.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) 
                          + "  \t\t     R$" + cP.getValor() + "0");
                  setTextFill(Color.GREEN);
            }
        }
    }

}
