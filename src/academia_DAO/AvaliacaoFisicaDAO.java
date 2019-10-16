/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia_DAO;

import classes_academia.AvaliacaoFisica;
import classes_academia.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Artur
 */
public class AvaliacaoFisicaDAO {
    
    private Connection connection = null;
    
    public ArrayList<AvaliacaoFisica> consultarDadosClienteEmAvaliacao(Cliente cliente){
         
        String busca = "SELECT idade, peso, altura, idAvaliacaoFisica, data_hora FROM avaliacaofisica WHERE id_cliente_aval_fisica = " 
                + cliente.getId() + " ORDER BY idAvaliacaoFisica DESC LIMIT 1";
        
        System.err.println(busca);
        ResultSet resultSet;
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        ArrayList<AvaliacaoFisica> avaliacao = new ArrayList<>();
        
        try {
            
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            resultSet = preparedStatement.executeQuery(busca);
            
            if(resultSet.next()){
                avaliacaoFisica.setPeso(resultSet.getDouble("peso"));
                avaliacaoFisica.setAltura(resultSet.getDouble("altura"));
                avaliacaoFisica.setIdade(resultSet.getInt("idade"));
                avaliacaoFisica.setCliente(cliente);
                avaliacaoFisica.setIdAvaliacaoFisica(resultSet.getInt("idAvaliacaoFisica"));
                avaliacaoFisica.setData_hora(resultSet.getDate("data_hora").toLocalDate());
                
                avaliacao.add(avaliacaoFisica);
            }
            return avaliacao;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<AvaliacaoFisica> consultarAvaliacaoPorCliente(Cliente cliente){
        String busca = "SELECT * FROM avaliacaofisica WHERE id_cliente_aval_fisica = " + cliente.getId();
        
        ResultSet resultSet;
        ArrayList<AvaliacaoFisica> avaliacoes = new ArrayList<AvaliacaoFisica>();
        
        try {
            
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            resultSet = preparedStatement.executeQuery(busca);
            
            while(resultSet.next()){
                AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
                avaliacaoFisica.setIdAvaliacaoFisica(resultSet.getInt("idAvaliacaoFisica"));
                avaliacaoFisica.setPeso(resultSet.getDouble("peso"));
                avaliacaoFisica.setAltura(resultSet.getDouble("altura"));
                avaliacaoFisica.setCliente(cliente);
                avaliacaoFisica.setTorax(resultSet.getDouble("torax"));
                avaliacaoFisica.setTorax2(resultSet.getDouble("torax2"));
                avaliacaoFisica.setCintura(resultSet.getDouble("cintura"));
                avaliacaoFisica.setAbdominal(resultSet.getDouble("abdominal"));
                avaliacaoFisica.setQuadril(resultSet.getDouble("quadril"));
                avaliacaoFisica.setBisseps_direito(resultSet.getDouble("bisseps_direito"));
                avaliacaoFisica.setBisseps_esquerdo(resultSet.getDouble("bisseps_esquerdo"));
                avaliacaoFisica.setCoxa_esquerda(resultSet.getDouble("coxa_esquerda"));
                avaliacaoFisica.setCoxa_direita(resultSet.getDouble("coxa_direita"));
                avaliacaoFisica.setPerna_esquerda(resultSet.getDouble("perna_esquerda"));
                avaliacaoFisica.setPerna_direita(resultSet.getDouble("perna_direita"));
                avaliacaoFisica.setPerc_gordura(resultSet.getDouble("perc_gordura"));
                avaliacaoFisica.setPerc_livre_gordura(resultSet.getDouble("perc_livre_gordura"));
                avaliacaoFisica.setPerc_ideal_gordura(resultSet.getDouble("perc_ideal_gordura"));
                avaliacaoFisica.setPerc_gord_sobra(resultSet.getDouble("perc_gord_sobra"));
                avaliacaoFisica.setImc(resultSet.getDouble("imc"));
                avaliacaoFisica.setRc_q(resultSet.getDouble("rc_q"));
                avaliacaoFisica.setPeso_gordura(resultSet.getDouble("peso_gordura"));
                avaliacaoFisica.setPeso_magro(resultSet.getDouble("peso_magro"));
                avaliacaoFisica.setPeso_excedente(resultSet.getDouble("peso_excedente"));
                avaliacaoFisica.setData_hora(resultSet.getDate("data_hora").toLocalDate());
                avaliacaoFisica.setIdade(resultSet.getInt("idade"));
                avaliacaoFisica.setPressao_arterial(resultSet.getDouble("pressao_arterial"));
                avaliacaoFisica.setF_c_repouso(resultSet.getDouble("f_c_repouso"));
                avaliacaoFisica.setF_c_max(resultSet.getDouble("f_c_max"));
                avaliacaoFisica.setF_c_res(resultSet.getDouble("f_c_res"));
                avaliacaoFisica.setFct_inf(resultSet.getDouble("fct_inf"));
                avaliacaoFisica.setFct_sup(resultSet.getDouble("fct_sup"));
                avaliacaoFisica.setPerc_int_inf(resultSet.getDouble("perc_int_inf"));
                avaliacaoFisica.setPerc_int_sup(resultSet.getDouble("perc_int_sup"));
                avaliacaoFisica.setD_c(resultSet.getString("d_c"));
                avaliacaoFisica.setHipertensao(resultSet.getString("hipertensao"));
                avaliacaoFisica.setDiabetes(resultSet.getString("diabetes"));
                avaliacaoFisica.setFumante(resultSet.getString("fumante"));
                avaliacaoFisica.setBebida_alcoolica(resultSet.getString("bebida_alcoolica"));
                avaliacaoFisica.setD_c_familiar(resultSet.getString("d_c_familiar"));
                avaliacaoFisica.setObesidade_familiar(resultSet.getString("obesidade_familiar"));
                avaliacaoFisica.setHipertensao_familiar(resultSet.getString("hipertensao_familiar"));
                avaliacaoFisica.setDiabetes_familiar(resultSet.getString("diabetes_familiar"));
                avaliacaoFisica.setPrat_atividade_fisica(resultSet.getString("prat_atividade_fisica"));
                avaliacaoFisica.setModalidade(resultSet.getString("modalidade"));
                avaliacaoFisica.setFrequencia_semanal_atividade(resultSet.getString("frequencia_semanal_atividade"));
                avaliacaoFisica.setDuracao(resultSet.getDouble("duracao"));
                avaliacaoFisica.setMedicamentos(resultSet.getString("medicamentos"));
                avaliacaoFisica.setOutros(resultSet.getString("outros"));
                avaliacaoFisica.setObjetivos(resultSet.getString("objetivos"));
                
                avaliacoes.add(avaliacaoFisica);
            
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return avaliacoes;
    }
    
    public boolean salvarAvaliacaoFisica(AvaliacaoFisica avaliacao){
        String salvar = "INSERT INTO avaliacaofisica(peso, altura, id_cliente_aval_fisica, torax, torax2, cintura, abdominal,"
                    + " quadril, bisseps_direito, bisseps_esquerdo, coxa_esquerda, coxa_direita,"
                    + " perna_esquerda, perna_direita, perc_gordura, perc_livre_gordura, perc_ideal_gordura,"
                    + " perc_gord_sobra, imc, rc_q, peso_gordura, peso_magro, peso_excedente, data_hora,"
                    + " idade, pressao_arterial, f_c_repouso, f_c_max, f_c_res, fct_inf, fct_sup,"
                    + " perc_int_inf, perc_int_sup, d_c, hipertensao, diabetes, fumante, bebida_alcoolica,"
                    + " d_c_familiar, obesidade_familiar, hipertensao_familiar, diabetes_familiar, prat_atividade_fisica,"
                    + " modalidade, frequencia_semanal_atividade, duracao, medicamentos, outros,"
                    + " objetivos)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";   
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(salvar);
            preparedStatement.setDouble(1, avaliacao.getPeso());
            preparedStatement.setDouble(2, avaliacao.getAltura());
            preparedStatement.setInt(3, avaliacao.getCliente().getId());
            preparedStatement.setDouble(4, avaliacao.getTorax());
            preparedStatement.setDouble(5, avaliacao.getTorax2());
            preparedStatement.setDouble(6, avaliacao.getCintura());
            preparedStatement.setDouble(7, avaliacao.getAbdominal());
            preparedStatement.setDouble(8, avaliacao.getQuadril());
            preparedStatement.setDouble(9, avaliacao.getBisseps_direito());
            preparedStatement.setDouble(10, avaliacao.getBisseps_esquerdo());
            preparedStatement.setDouble(11, avaliacao.getCoxa_esquerda());
            preparedStatement.setDouble(12, avaliacao.getCoxa_direita());
            preparedStatement.setDouble(13, avaliacao.getPerna_esquerda());
            preparedStatement.setDouble(14, avaliacao.getPerna_direita());
            preparedStatement.setDouble(15, avaliacao.getPerc_gordura());
            preparedStatement.setDouble(16, avaliacao.getPerc_livre_gordura());
            preparedStatement.setDouble(17, avaliacao.getPerc_ideal_gordura());
            preparedStatement.setDouble(18, avaliacao.getPerc_gord_sobra());
            preparedStatement.setDouble(19, avaliacao.getImc());
            preparedStatement.setDouble(20, avaliacao.getRc_q());
            preparedStatement.setDouble(21, avaliacao.getPeso_gordura());
            preparedStatement.setDouble(22, avaliacao.getPeso_magro());
            preparedStatement.setDouble(23, avaliacao.getPeso_excedente());
            preparedStatement.setDate(24, java.sql.Date.valueOf(avaliacao.getData_hora()));
            preparedStatement.setInt(25, avaliacao.getIdade());
            preparedStatement.setDouble(26, avaliacao.getPressao_arterial());
            preparedStatement.setDouble(27, avaliacao.getF_c_repouso());
            preparedStatement.setDouble(28, avaliacao.getF_c_max());
            preparedStatement.setDouble(29, avaliacao.getF_c_res());
            preparedStatement.setDouble(30, avaliacao.getFct_inf());
            preparedStatement.setDouble(31, avaliacao.getFct_sup());
            preparedStatement.setDouble(32, avaliacao.getPerc_int_inf());
            preparedStatement.setDouble(33, avaliacao.getPerc_int_sup());
            preparedStatement.setString(34, avaliacao.getD_c());
            preparedStatement.setString(35, avaliacao.getHipertensao());
            preparedStatement.setString(36, avaliacao.getDiabetes());
            preparedStatement.setString(37, avaliacao.getFumante());
            preparedStatement.setString(38, avaliacao.getBebida_alcoolica());
            preparedStatement.setString(39, avaliacao.getD_c_familiar());
            preparedStatement.setString(40, avaliacao.getObesidade_familiar());
            preparedStatement.setString(41, avaliacao.getHipertensao_familiar());
            preparedStatement.setString(42, avaliacao.getDiabetes_familiar());
            preparedStatement.setString(43, avaliacao.getPrat_atividade_fisica());
            preparedStatement.setString(44, avaliacao.getModalidade());
            preparedStatement.setString(45, avaliacao.getFrequencia_semanal_atividade());
            preparedStatement.setDouble(46, avaliacao.getDuracao());
            preparedStatement.setString(47, avaliacao.getMedicamentos());
            preparedStatement.setString(48, avaliacao.getOutros());
            preparedStatement.setString(49, avaliacao.getObjetivos());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean atualizarAvaliacaoFisica(AvaliacaoFisica avaliacao){
        String salvar = "UPDATE avaliacaofisica SET peso = ?, altura = ?, torax = ?, torax2 = ?, cintura = ?, abdominal = ?,"
                    + " quadril = ?, bisseps_direito = ?, bisseps_esquerdo = ?, coxa_esquerda = ?, coxa_direita = ?,"
                    + " perna_esquerda = ?, perna_direita = ?, perc_gordura = ?, perc_livre_gordura = ?, perc_ideal_gordura = ?,"
                    + " perc_gord_sobra = ?, imc = ?, rc_q = ?, peso_gordura = ?, peso_magro = ?, peso_excedente = ?,"
                    + " pressao_arterial = ?, f_c_repouso = ?, f_c_max = ?, f_c_res = ?, fct_inf = ?, fct_sup = ?,"
                    + " perc_int_inf = ?, perc_int_sup = ?, d_c = ?, hipertensao = ?, diabetes = ?, fumante = ?, bebida_alcoolica = ?,"
                    + " d_c_familiar = ?, obesidade_familiar = ?, hipertensao_familiar = ?, diabetes_familiar = ?, prat_atividade_fisica = ?,"
                    + " modalidade = ?, frequencia_semanal_atividade = ?, duracao = ?, medicamentos = ?, outros = ?,"
                    + " objetivos = ? WHERE idAvaliacaoFisica =" + avaliacao.getIdAvaliacaoFisica();
        
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(salvar);
            preparedStatement.setDouble(1, avaliacao.getPeso());
            preparedStatement.setDouble(2, avaliacao.getAltura());
            preparedStatement.setDouble(3, avaliacao.getTorax());
            preparedStatement.setDouble(4, avaliacao.getTorax2());
            preparedStatement.setDouble(5, avaliacao.getCintura());
            preparedStatement.setDouble(6, avaliacao.getAbdominal());
            preparedStatement.setDouble(7, avaliacao.getQuadril());
            preparedStatement.setDouble(8, avaliacao.getBisseps_direito());
            preparedStatement.setDouble(9, avaliacao.getBisseps_esquerdo());
            preparedStatement.setDouble(10, avaliacao.getCoxa_esquerda());
            preparedStatement.setDouble(11, avaliacao.getCoxa_direita());
            preparedStatement.setDouble(12, avaliacao.getPerna_esquerda());
            preparedStatement.setDouble(13, avaliacao.getPerna_direita());
            preparedStatement.setDouble(14, avaliacao.getPerc_gordura());
            preparedStatement.setDouble(15, avaliacao.getPerc_livre_gordura());
            preparedStatement.setDouble(16, avaliacao.getPerc_ideal_gordura());
            preparedStatement.setDouble(17, avaliacao.getPerc_gord_sobra());
            preparedStatement.setDouble(18, avaliacao.getImc());
            preparedStatement.setDouble(19, avaliacao.getRc_q());
            preparedStatement.setDouble(20, avaliacao.getPeso_gordura());
            preparedStatement.setDouble(21, avaliacao.getPeso_magro());
            preparedStatement.setDouble(22, avaliacao.getPeso_excedente());
            preparedStatement.setDouble(23, avaliacao.getPressao_arterial());
            preparedStatement.setDouble(24, avaliacao.getF_c_repouso());
            preparedStatement.setDouble(25, avaliacao.getF_c_max());
            preparedStatement.setDouble(26, avaliacao.getF_c_res());
            preparedStatement.setDouble(27, avaliacao.getFct_inf());
            preparedStatement.setDouble(28, avaliacao.getFct_sup());
            preparedStatement.setDouble(29, avaliacao.getPerc_int_inf());
            preparedStatement.setDouble(30, avaliacao.getPerc_int_sup());
            preparedStatement.setString(31, avaliacao.getD_c());
            preparedStatement.setString(32, avaliacao.getHipertensao());
            preparedStatement.setString(33, avaliacao.getDiabetes());
            preparedStatement.setString(34, avaliacao.getFumante());
            preparedStatement.setString(35, avaliacao.getBebida_alcoolica());
            preparedStatement.setString(36, avaliacao.getD_c_familiar());
            preparedStatement.setString(37, avaliacao.getObesidade_familiar());
            preparedStatement.setString(38, avaliacao.getHipertensao_familiar());
            preparedStatement.setString(39, avaliacao.getDiabetes_familiar());
            preparedStatement.setString(40, avaliacao.getPrat_atividade_fisica());
            preparedStatement.setString(41, avaliacao.getModalidade());
            preparedStatement.setString(42, avaliacao.getFrequencia_semanal_atividade());
            preparedStatement.setDouble(43, avaliacao.getDuracao());
            preparedStatement.setString(44, avaliacao.getMedicamentos());
            preparedStatement.setString(45, avaliacao.getOutros());
            preparedStatement.setString(46, avaliacao.getObjetivos());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    public boolean deletarAvaliacaoFisica(AvaliacaoFisica avaliacao){
        String sql = "DELETE FROM avaliacaofisica WHERE idAvaliacaoFisica = " + avaliacao.getIdAvaliacaoFisica();
        
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public ArrayList<Double> buscarAvaliacoesPorCliente(int idCliente){
        String sql = "SELECT torax FROM avaliacaofisica WHERE id_cliente_aval_fisica = " + idCliente;
        
         ResultSet resultSet;
         ArrayList<Double> torax = new ArrayList<Double>();
         
        try{
            
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            resultSet = preparedStatement.executeQuery(sql);
            
            while(resultSet.next()){
                AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
                avaliacaoFisica.setTorax(resultSet.getDouble("torax"));
                torax.add(avaliacaoFisica.getTorax());
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return torax;
    }
    
    public ArrayList<Double> buscarTorax2PorCliente(int idCliente){
        String sql = "SELECT torax2 FROM avaliacaofisica WHERE id_cliente_aval_fisica = " + idCliente;
        
         ResultSet resultSet;
         ArrayList<Double> torax2 = new ArrayList<Double>();
         
        try{
            
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            resultSet = preparedStatement.executeQuery(sql);
            
            while(resultSet.next()){
                AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
                avaliacaoFisica.setTorax(resultSet.getDouble("torax2"));
                torax2.add(avaliacaoFisica.getTorax2());
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return torax2;
    }
    
    public ArrayList<AvaliacaoFisica> consultarProximasAvaliacoes(){
        LocalDate dataHoje = LocalDate.now().minusMonths(3);
        String busca = "SELECT id_cliente_aval_fisica, MAX(data_hora) FROM avaliacaofisica WHERE date(data_hora) > date('" 
                + dataHoje 
                + "') GROUP BY id_cliente_aval_fisica HAVING COUNT(id_cliente_aval_fisica) > 0";
        System.err.println(busca);
        ResultSet resultSet;
        ArrayList<AvaliacaoFisica> avaliacoes = new ArrayList<AvaliacaoFisica>();
        
        try {
            
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            resultSet = preparedStatement.executeQuery(busca);
            
            while(resultSet.next()){
                AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
                ClienteDAO clienteDAO = new ClienteDAO();
                avaliacaoFisica.setCliente(clienteDAO.buscarClientePeloID(resultSet.getInt("id_cliente_aval_fisica")));
                avaliacaoFisica.setData_hora(resultSet.getDate("MAX(data_hora)").toLocalDate());
                
                
                avaliacoes.add(avaliacaoFisica);
            
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return avaliacoes;
    }
}

