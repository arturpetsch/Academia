/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Relatorios;

import classes_academia.AvaliacaoFisica;
import classes_academia.ContaReceber;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Alert;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import academia_DAO.AvaliacaoFisicaDAO;
import java.sql.ResultSet;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
/**
 *
 * @author Artur
 */
public class ReciboAvaliacaoFisica {
 private final String path; //Caminho base

    private String pathToReportPackage;
    private String caminhoFinal;
    
    public ReciboAvaliacaoFisica() {
        this.path = System.getProperty("user.dir");
        int lenght = this.path.length();
        int sizeFinal = lenght - 5;
        caminhoFinal = this.path.substring(0, sizeFinal); //executar no pc do user; retirar isso para funcionar no pc desenvolvimento;
        
        //alterar tudo de "caminhoFinal" para this.path para funcionar no momento do desenv. 
        System.out.println("PATH: " +this.path + "\\src\\relatorios\\avaliacaoFisica.jrxml");
        //"/pastadoProjeto/relatorios/
    }

    public void gerarReciboAvaliacaoFisica(Collection<AvaliacaoFisica> avaliacaoFisica) throws JRException {
        try{
            AvaliacaoFisicaDAO avaliacaoFisicaDAO = new AvaliacaoFisicaDAO();
            Map<String, Object> params = new HashMap();
        
        
        ArrayList<AvaliacaoFisica> avaliacaoF = new ArrayList();
        avaliacaoF = (ArrayList<AvaliacaoFisica>) avaliacaoFisica;
        String nome = avaliacaoF.get(0).getCliente().getNome();
        java.awt.Image logo = new ImageIcon(getClass().getResource("/academia/icon/logo.jpg")).getImage();
        String idade = avaliacaoF.get(0).getIdade().toString();
        
        java.lang.Integer idAvaliacaoFisica = avaliacaoF.get(0).getIdAvaliacaoFisica();
        params.put("logo", logo);
        Integer idCliente = avaliacaoF.get(0).getCliente().getId();
       params.put("cliente.id", idCliente.toString());
       params.put("cliente.nome", avaliacaoF.get(0).getCliente().getNome()); 
       
        JasperReport report = JasperCompileManager.compileReport(this.path + "\\src\\relatorios\\avaliacaoFisica.jrxml");
        
        ArrayList<Double> torax = new ArrayList<Double>();
       torax = avaliacaoFisicaDAO.buscarAvaliacoesPorCliente(avaliacaoF.get(0).getCliente().getId());
       
        ArrayList<Double> torax2 = new ArrayList<Double>();
        torax2 = avaliacaoFisicaDAO.buscarTorax2PorCliente(avaliacaoF.get(0).getCliente().getId());
       
        ArrayList<AvaliacaoFisica> avaliacoes = avaliacaoFisicaDAO.consultarAvaliacaoPorCliente(avaliacaoF.get(0).getCliente());
       JRBeanArrayDataSource jrRs = new JRBeanArrayDataSource(avaliacoes.toArray());
       
       String mes =  avaliacaoF.get(0).getData_hora().toString();
        //params.put("torax", torax);
        //params.put("torax2", torax2);
       params.put("mes", mes);
        params.put("idade", avaliacaoF.get(0).getIdade());
        params.put("peso", avaliacaoF.get(0).getPeso());
        params.put("altura", avaliacaoF.get(0).getAltura());
        JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(avaliacaoFisica,false));
       
       //JasperPrint print = JasperFillManager.fillReport(report, params, jrRs);
        System.err.println("compilou ");
        JasperViewer.viewReport(print, false);
        }catch(Exception e){
            e.printStackTrace();
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro no PDF");
            alerta.setHeaderText("Erro ao gerar dados avaliação física!\n" + this.path + "\\src\\relatorios\\recibo.jrxml");
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
    

