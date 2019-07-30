/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Financeiro;

import academia_DAO.ContaPagarDAO;
import academia_DAO.ContaReceberDAO;
import classes_academia.ContaPagar;
import classes_academia.ContaReceber;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Artur
 */
public class GraficoMovimentacaoFinanceiroController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private String ano;
    
    @FXML
    private Label titulo;
    
    @FXML
    private LineChart grafico = new LineChart<>(new CategoryAxis(), new NumberAxis());
    
    int mes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    
    public void setAno(String ano){
        this.ano = ano;
        
        titulo.setText("Gráfico Movimentação Financeira Ref. ao Ano de " + this.ano);
        System.err.println(titulo.getText());
         buscarValoresAReceberPorAno();
         buscarValoresAPagarPorAno();
    }
    
    @FXML
    private void buscarValoresAReceberPorAno(){
        ContaReceberDAO contaReceberDAO = new ContaReceberDAO();
        ArrayList<ContaReceber> contasAReceber = new ArrayList<ContaReceber>();
        
        contasAReceber = contaReceberDAO.buscarMensalidadesPorAno(Integer.parseInt(ano));
        
        double janeiro = 0, fevereiro = 0, marco = 0, abril = 0, 
                maio = 0, junho = 0, julho = 0, agosto = 0, setembro = 0, 
                outubro = 0, novembro = 0, dezembro = 0;
        
        for (ContaReceber contaReceber : contasAReceber) {
            int mes = contaReceber.getDataVencimento().getDayOfMonth();
            switch(mes){
                case 1:
                    janeiro = janeiro + contaReceber.getValor();
                    break;
                case 2:
                    fevereiro = fevereiro + contaReceber.getValor();
                    break;
                case 3:
                    marco = marco + contaReceber.getValor();
                    break;
                case 4:
                    abril = abril + contaReceber.getValor();
                    break;
                case 5:
                    maio = maio + contaReceber.getValor();
                    break;    
                case 6:
                    junho = junho + contaReceber.getValor();
                    break;
                case 7:
                    julho = julho + contaReceber.getValor();
                    break;
                case 8:
                    agosto = agosto + contaReceber.getValor();
                    break; 
                case 9:
                    setembro = setembro + contaReceber.getValor();
                    break;
                case 10:
                    outubro = outubro + contaReceber.getValor();
                    break;
                case 11:
                    novembro = novembro + contaReceber.getValor();
                    break;
                case 12:
                    dezembro = dezembro + contaReceber.getValor();
                    break;    
            }
        }
        
        
        XYChart.Series contasR = new XYChart.Series<>();
        contasR.setName("Contas a Receber");
        
        contasR.getData().add(new XYChart.Data("Janeiro", janeiro));
        contasR.getData().add(new XYChart.Data("Fevereiro", fevereiro));
        contasR.getData().add(new XYChart.Data("Março", marco));
        contasR.getData().add(new XYChart.Data("Abril", abril));
        contasR.getData().add(new XYChart.Data("Maio", maio));
        contasR.getData().add(new XYChart.Data("Junho", junho));
        contasR.getData().add(new XYChart.Data("Julho", julho));
        contasR.getData().add(new XYChart.Data("Agosto", agosto));
        contasR.getData().add(new XYChart.Data("Setembro", setembro));
        contasR.getData().add(new XYChart.Data("Outubro", outubro));
        contasR.getData().add(new XYChart.Data("Novembro", novembro));
        contasR.getData().add(new XYChart.Data("Dezembro", dezembro));
        grafico.getData().addAll(contasR);
    }
    
    @FXML
    private void buscarValoresAPagarPorAno(){
        ContaPagarDAO contaPagarDAO = new ContaPagarDAO();
        ArrayList<ContaPagar> contasAPagar = new ArrayList<ContaPagar>();
        
        contasAPagar = contaPagarDAO.buscarContasAPagarPorAno(Integer.parseInt(ano));
        
        double janeiro = 0, fevereiro = 0, marco = 0, abril = 0, 
                maio = 0, junho = 0, julho = 0, agosto = 0, setembro = 0, 
                outubro = 0, novembro = 0, dezembro = 0;
        
        for (ContaPagar contaPagar : contasAPagar) {
            int mes = contaPagar.getDataVencimento().getDayOfMonth();
            Double valor = new Double(contaPagar.getValor());
            switch(mes){
                case 1:
                    janeiro = janeiro + valor;
                    break;
                case 2:
                    fevereiro = fevereiro + valor;
                    break;
                case 3:
                    marco = marco + valor;
                    break;
                case 4:
                    abril = abril + valor;
                    break;
                case 5:
                    maio = maio + valor;
                    break;    
                case 6:
                    junho = junho + valor;
                    break;
                case 7:
                    julho = julho + valor;
                    break;
                case 8:
                    agosto = agosto + valor;
                    break; 
                case 9:
                    setembro = setembro + valor;
                    break;
                case 10:
                    outubro = outubro + valor;
                    break;
                case 11:
                    novembro = novembro + valor;
                    break;
                case 12:
                    dezembro = dezembro + valor;
                    break;    
            }
        }
        
        
        XYChart.Series contasP = new XYChart.Series<>();
        contasP.setName("Contas a Pagar");
        
        contasP.getData().add(new XYChart.Data("Janeiro", janeiro));
        contasP.getData().add(new XYChart.Data("Fevereiro", fevereiro));
        contasP.getData().add(new XYChart.Data("Março", marco));
        contasP.getData().add(new XYChart.Data("Abril", abril));
        contasP.getData().add(new XYChart.Data("Maio", maio));
        contasP.getData().add(new XYChart.Data("Junho", junho));
        contasP.getData().add(new XYChart.Data("Julho", julho));
        contasP.getData().add(new XYChart.Data("Agosto", agosto));
        contasP.getData().add(new XYChart.Data("Setembro", setembro));
        contasP.getData().add(new XYChart.Data("Outubro", outubro));
        contasP.getData().add(new XYChart.Data("Novembro", novembro));
        contasP.getData().add(new XYChart.Data("Dezembro", dezembro));
        grafico.getData().addAll(contasP);
        
    }
}
