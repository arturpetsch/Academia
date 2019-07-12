/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Relatorios;

import classes_academia.ContaReceber;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Alert;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Artur
 */
public class ReciboPagamento {

    private final String path; //Caminho base

    private String pathToReportPackage;
    private String caminhoFinal;
    
    public ReciboPagamento() {
        this.path = System.getProperty("user.dir");
        int lenght = this.path.length();
        int sizeFinal = lenght - 5;
        caminhoFinal = this.path.substring(0, sizeFinal); //executar no pc do user; retirar isso para funcionar no pc desenvolvimento;
        
        //alterar tudo de "caminhoFinal" para this.path para funcionar no momento do desenv. 
        System.out.println("PATH: " +this.path + "\\src\\relatorios\\reciboMatricula.jrxml");
        //"/pastadoProjeto/relatorios/
    }

    public void gerarRecibo(Collection<ContaReceber> mensalidade) throws JRException {
        try{
            Map<String, Object> params = new HashMap();
        
        
        ArrayList<ContaReceber> cReceber = new ArrayList();
        cReceber = (ArrayList<ContaReceber>) mensalidade;
        String valorFinal = String.valueOf(cReceber.get(0).getValor()).replace('.', ',');
        java.awt.Image logo = new ImageIcon(getClass().getResource("/academia/icon/logo.jpg")).getImage();
        System.err.println("caminho logo: " + this.path + "\\src\\icon\\logo.jpg");
        params.put("logo", logo);
        params.put("valorFinal", valorFinal);
        //String caminho = "/academia/relatorios/reciboMatricula.jrxml";
        
       JasperReport report = JasperCompileManager.compileReport(this.path + "\\src\\relatorios\\recibo.jrxml");
        
        //JasperReport report = JasperCompileManager.compileReport(this.path + "\\src\\relatorios\\recibo.jrxml"); // modo desenv.
        
        JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(mensalidade));
        
        //JasperExportManager.exportReportToPdfFile(print, System.getProperty("user.dir") + "print.pdf");
        
        /*JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\arquivo.pdf");
            Runtime.getRuntime().exec("cmd /c start C:\\Users\\arquivo.pdf");
           File file = new File("C:\\Users\\arquivo.pdf");
           file.deleteOnExit();*/
        //JasperViewer.viewReport(print);
        JasperViewer.viewReport(print, false);
        }catch(Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro no Recibo");
            alerta.setHeaderText("Erro ao gerar o Recibo!\n" + this.path + "\\src\\relatorios\\recibo.jrxml");
            alerta.showAndWait();
        }
    }

    public String getPathToReportPackage() {
        return this.pathToReportPackage;
    }

    public String getPath() {
        return this.path;
    }
}
