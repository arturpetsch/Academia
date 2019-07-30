/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.Relatorios;

import academia_DAO.AvaliacaoFisicaDAO;
import classes_academia.AvaliacaoFisica;
import classes_academia.AvaliacaoReabilitacao;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Artur
 */
public class RelatorioAvaliacaoReabilitacao {
     private final String path; //Caminho base

    private String pathToReportPackage;
    private String caminhoFinal;
    
    public RelatorioAvaliacaoReabilitacao() {
        this.path = System.getProperty("user.dir");
        int lenght = this.path.length();
        int sizeFinal = lenght - 5;
        caminhoFinal = this.path.substring(0, sizeFinal); //executar no pc do user; retirar isso para funcionar no pc desenvolvimento;
        
        //alterar tudo de "caminhoFinal" para this.path para funcionar no momento do desenv. 
        System.out.println("PATH: " +this.path + "\\src\\relatorios\\relatorioAvalicaoReabilitacao.jrxml");
        //"/pastadoProjeto/relatorios/
    }

    public void gerarRelatorioAvaliacaoReabilitacao(Collection<AvaliacaoReabilitacao> avaliacaoReabilitacao, List<File> imagens) throws JRException {
        try{
            Map<String, Object> params = new HashMap();
        
        
        ArrayList<AvaliacaoReabilitacao> avaliacaoF = new ArrayList();
        avaliacaoF = (ArrayList<AvaliacaoReabilitacao>) avaliacaoReabilitacao;
        String nome = avaliacaoF.get(0).getCliente().getNome();
        java.awt.Image logo = new ImageIcon(getClass().getResource("/academia/icon/logo.jpg")).getImage();
        String idade = avaliacaoF.get(0).getIdade().toString();
        
        java.lang.Integer idAvaliacaoFisica = avaliacaoF.get(0).getIdAvaliacaoReabilitacao();
        params.put("logo", logo);
        Integer idCliente = avaliacaoF.get(0).getCliente().getId();
       params.put("cliente.id", idCliente.toString());
       params.put("cliente.nome", avaliacaoF.get(0).getCliente().getNome()); 
       params.put("idAvaliacaoReabilitacao", idAvaliacaoFisica);
       
        if( imagens.size()>0 && imagens.get(0)!=null){
            
            BufferedImage imagem = ImageIO.read(imagens.get(0));
            params.put("img1", imagem);
        }
       
        if( imagens.size()>1 && imagens.get(1)!=null){
            BufferedImage imagem = ImageIO.read(imagens.get(1));
            params.put("img2", imagem);
        }
        
        if(imagens.size()>2 && imagens.get(2)!=null){
            BufferedImage imagem = ImageIO.read(imagens.get(2));
            params.put("img3", imagem);
        }
        
        if(imagens.size()>3 && imagens.get(3)!=null ){
            BufferedImage imagem = ImageIO.read(imagens.get(3));
            params.put("img4", imagem);
        }
        
        if( imagens.size()>4 && imagens.get(4)!=null){
            BufferedImage imagem = ImageIO.read(imagens.get(4));
            params.put("img5", imagem);
        }
        
        if(imagens.size()>5 && imagens.get(5)!=null){
            BufferedImage imagem = ImageIO.read(imagens.get(5));
            params.put("img6", imagem);
        }
        
        if(imagens.size()>6 && imagens.get(6)!=null){
            BufferedImage imagem = ImageIO.read(imagens.get(6));
            params.put("img7", imagem);
        }
        
        JasperReport report = JasperCompileManager.compileReport(this.path + "\\src\\relatorios\\relatorioAvalicaoReabilitacao.jrxml");
        
       
//       String mes =  avaliacaoF.get(0).getData_hora().toString();
        //params.put("torax", torax);
        //params.put("torax2", torax2);
   //    params.put("mes", mes);
        params.put("idade", avaliacaoF.get(0).getIdade());
        JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(avaliacaoReabilitacao,false));
       
       //JasperPrint print = JasperFillManager.fillReport(report, params, jrRs);
        System.err.println("compilou ");
        JasperViewer.viewReport(print, false);
        }catch(Exception e){
            e.printStackTrace();
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro no PDF");
            alerta.setHeaderText("Erro ao gerar dados avaliação reabilitação!\n" + this.path + "\\src\\relatorios\\recibo.jrxml");
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
    



