/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author Artur
 */
public class Conexao {
    private static Connection connection;
    
    public static Connection conexao() throws ClassNotFoundException, SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","root","academia54321");
            System.out.println("Conectado");
            return connection;
        }catch(Exception e){
             Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro no Recibo");
            alerta.setHeaderText("Erro ao acessar o banco de dados");
            alerta.showAndWait();
            return null;
        }
    }
}
