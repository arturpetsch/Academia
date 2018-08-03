/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academia_DAO;

import classes_academia.Cliente;
import classes_academia.QuadroHorario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artur
 */
public class ClienteDAO {

    private Connection connection = null;

    public boolean cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente(nome, cpf, status, telefone, endereco, bairro, cep, observacao, email, numero,  cidade, estado, dataCadastro, dataNascimento, horaTreino, sexo, segunda, terca, quarta, quinta, sexta, sabado)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.setBoolean(3, cliente.getStatus());
            preparedStatement.setString(4, cliente.getContato());
            preparedStatement.setString(5, cliente.getEndereco());
            preparedStatement.setString(6, cliente.getBairro());
            preparedStatement.setString(7, cliente.getCEP());
            preparedStatement.setString(8, cliente.getObservacao());
            preparedStatement.setString(9, cliente.getEmail());
            preparedStatement.setString(10, cliente.getNumero());
            preparedStatement.setString(11, cliente.getCidade());
            preparedStatement.setString(12, cliente.getEstado());
            preparedStatement.setDate(13, java.sql.Date.valueOf(cliente.getDataCadastro()));
            preparedStatement.setDate(14, java.sql.Date.valueOf(cliente.getDataNascimento()));
            preparedStatement.setString(15, cliente.getHoraTreino());
            preparedStatement.setBoolean(16, cliente.getSexo());
            preparedStatement.setBoolean(17, cliente.getTreinoSegunda());
            preparedStatement.setBoolean(18, cliente.getTreinoTerca());
            preparedStatement.setBoolean(19, cliente.getTreinoQuarta());
            preparedStatement.setBoolean(20, cliente.getTreinoQuinta());
            preparedStatement.setBoolean(21, cliente.getTreinoSexta());
            preparedStatement.setBoolean(22, cliente.getTreinoSabado());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Cliente> buscarCliente(String nomeCliente) {
        System.out.println(nomeCliente);
        String sql = "SELECT * FROM Cliente WHERE nome LIKE " + "'%" + nomeCliente + "%' order by nome";
        ResultSet resultSet;
        List<Cliente> clientes = new ArrayList();
        try {

            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                int id = (resultSet.getInt("idCliente"));
                String nome = (resultSet.getString("nome"));
                String cpf = (resultSet.getString("cpf"));
                boolean status = (resultSet.getBoolean("status"));
                String contato = (resultSet.getString("telefone"));
                String endereco = (resultSet.getString("endereco"));
                String bairro = (resultSet.getString("bairro"));
                String CEP = (resultSet.getString("cep"));
                String observacao = (resultSet.getString("observacao"));
                String email = (resultSet.getString("email"));
                String estado = (resultSet.getString("estado"));
                String numero = (String.valueOf(resultSet.getInt("numero")));
                String cidade = (resultSet.getString("cidade"));
                LocalDate dataCadastro = (resultSet.getDate("dataCadastro").toLocalDate());
                LocalDate dataNascimento = (resultSet.getDate("dataNascimento").toLocalDate());
                String horaTreino = (resultSet.getString("horaTreino"));
                Boolean sexo = (resultSet.getBoolean("sexo"));
                boolean segunda = (resultSet.getBoolean("segunda"));
                boolean terca = (resultSet.getBoolean("terca"));
                boolean quarta = (resultSet.getBoolean("quarta"));
                boolean quinta = (resultSet.getBoolean("quinta"));
                boolean sexta = (resultSet.getBoolean("sexta"));
                boolean sabado = (resultSet.getBoolean("sabado"));
                Cliente cliente = new Cliente(nome, cpf, contato, observacao, endereco, bairro, cidade, estado,
                        status, email, CEP, numero, id, dataCadastro, dataNascimento, horaTreino, sexo, segunda, terca, quarta, quinta,
                        sexta, sabado);
                clientes.add(cliente);
            }
        } catch (Exception e) {
            return null;
        }
        return clientes;
    }

    public Cliente buscarClientePeloID(int idCliente) {
        String sql = "SELECT * FROM Cliente WHERE idCliente = " + idCliente;
        ResultSet resultSet;
        try {

            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                int id = (resultSet.getInt("idCliente"));
                String nome = (resultSet.getString("nome"));
                String cpf = (resultSet.getString("cpf"));
                boolean status = (resultSet.getBoolean("status"));
                String contato = (resultSet.getString("telefone"));
                String endereco = (resultSet.getString("endereco"));
                String bairro = (resultSet.getString("bairro"));
                String CEP = (resultSet.getString("cep"));
                String observacao = (resultSet.getString("observacao"));
                String email = (resultSet.getString("email"));
                String estado = (resultSet.getString("estado"));
                String numero = (String.valueOf(resultSet.getInt("numero")));
                String cidade = (resultSet.getString("cidade"));
                LocalDate dataCadastro = (resultSet.getDate("dataCadastro").toLocalDate());
                LocalDate dataNascimento = (resultSet.getDate("dataNascimento").toLocalDate());
                String horaTreino = (resultSet.getString("horaTreino"));
                Boolean sexo = (resultSet.getBoolean("sexo"));
                boolean segunda = (resultSet.getBoolean("segunda"));
                boolean terca = (resultSet.getBoolean("terca"));
                boolean quarta = (resultSet.getBoolean("quarta"));
                boolean quinta = (resultSet.getBoolean("quinta"));
                boolean sexta = (resultSet.getBoolean("sexta"));
                boolean sabado = (resultSet.getBoolean("sabado"));
                Cliente cliente = new Cliente(nome, cpf, contato, observacao, endereco, bairro, cidade, estado,
                        status, email, CEP, numero, id, dataCadastro, dataNascimento, horaTreino, sexo, segunda, terca,
                        quarta, quinta, sexta, sabado);
                return cliente;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public void alterarStatusCliente(boolean status, int id) {
        String sql = "UPDATE Cliente SET status = " + status + " WHERE idCliente = " + id + ";";
        System.err.println(sql);
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public boolean alterarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nome = ?, cpf = ?, status = ?, telefone = ?, endereco = ?, bairro = ?, "
                + "cep = ?, observacao = ?, email = ?, numero = ?,  cidade = ?, estado = ?, dataCadastro = ?, "
                + "dataNascimento = ?, horaTreino = ?, sexo = ?, segunda = ?, terca = ?, quarta = ?, quinta = ?,"
                + " sexta = ?, sabado = ?"
                + " WHERE idCliente = " + cliente.getId();

        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.setBoolean(3, cliente.getStatus());
            preparedStatement.setString(4, cliente.getContato());
            preparedStatement.setString(5, cliente.getEndereco());
            preparedStatement.setString(6, cliente.getBairro());
            preparedStatement.setString(7, cliente.getCEP());
            preparedStatement.setString(8, cliente.getObservacao());
            preparedStatement.setString(9, cliente.getEmail());
            preparedStatement.setString(10, cliente.getNumero());
            preparedStatement.setString(11, cliente.getCidade());
            preparedStatement.setString(12, cliente.getEstado());
            preparedStatement.setDate(13, java.sql.Date.valueOf(cliente.getDataCadastro()));
            preparedStatement.setDate(14, java.sql.Date.valueOf(cliente.getDataNascimento()));
            preparedStatement.setString(15, cliente.getHoraTreino());
            preparedStatement.setBoolean(16, cliente.getSexo());
            preparedStatement.setBoolean(17, cliente.getTreinoSegunda());
            preparedStatement.setBoolean(18, cliente.getTreinoTerca());
            preparedStatement.setBoolean(19, cliente.getTreinoQuarta());
            preparedStatement.setBoolean(20, cliente.getTreinoQuinta());
            preparedStatement.setBoolean(21, cliente.getTreinoSexta());
            preparedStatement.setBoolean(22, cliente.getTreinoSabado());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removerCliente(Cliente cliente) {
        String sql = "DELETE FROM Cliente WHERE idCliente = " + cliente.getId() + "";
        String sql2 = "DELETE FROM Mensalidade WHERE idCliente = " + cliente.getId() + "";
        System.err.println(sql);
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql2);
            preparedStatement.executeUpdate(sql);
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean buscarClientePeloCPF(String cpfCliente) {
        System.out.println(cpfCliente);
        String sql = "SELECT * FROM Cliente WHERE cpf = '" + cpfCliente + "';";
        System.err.println(sql);
        ResultSet resultSet;

        try {

            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            if (resultSet.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    public boolean buscarClientePeloCPFparaAlterar(String cpfCliente) {
        System.out.println(cpfCliente);
        String sql = "SELECT * FROM Cliente WHERE cpf = '" + cpfCliente + "';";
        System.err.println(sql);
        ResultSet resultSet;

        try {

            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);

            int contador = 0;
            while (resultSet.next()) {
                contador++;

            }
            if (contador > 1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    public int buscarUltimoCliente() {
        String sql = "SELECT MAX(idCliente) as idCliente FROM Cliente";
        ResultSet resultSet;
        int idCliente = 0;
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            if (resultSet.next()) {
                idCliente = resultSet.getInt("idCliente");

            }
            return idCliente;
        } catch (Exception e) {
            return 0;
        }
    }

    public Cliente buscarUltimoClienteRetObjeto() {
        String sql = "SELECT * FROM Cliente WHERE idCliente = (SELECT MAX(idCliente) FROM Cliente)";
        ResultSet resultSet;
        Cliente cliente = null;
        try {
            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            if (resultSet.next()) {
                int idCliente = resultSet.getInt("idCliente");
                String nome = (resultSet.getString("nome"));
                String cpf = (resultSet.getString("cpf"));
                boolean status = (resultSet.getBoolean("status"));
                String contato = (resultSet.getString("telefone"));
                String endereco = (resultSet.getString("endereco"));
                String bairro = (resultSet.getString("bairro"));
                String CEP = (resultSet.getString("cep"));
                String observacao = (resultSet.getString("observacao"));
                String email = (resultSet.getString("email"));
                String estado = (resultSet.getString("estado"));
                String numero = (String.valueOf(resultSet.getInt("numero")));
                String cidade = (resultSet.getString("cidade"));
                LocalDate dataCadastro = (resultSet.getDate("dataCadastro").toLocalDate());
                LocalDate dataNascimento = (resultSet.getDate("dataNascimento").toLocalDate());
                String horaTreino = (resultSet.getString("horaTreino"));
                Boolean sexo = (resultSet.getBoolean("sexo"));
                boolean segunda = (resultSet.getBoolean("segunda"));
                boolean terca = (resultSet.getBoolean("terca"));
                boolean quarta = (resultSet.getBoolean("quarta"));
                boolean quinta = (resultSet.getBoolean("quinta"));
                boolean sexta = (resultSet.getBoolean("sexta"));
                boolean sabado = (resultSet.getBoolean("sabado"));
                cliente = new Cliente(nome, cpf, contato, observacao, endereco, bairro, cidade, estado,
                        status, email, CEP, numero, idCliente, dataCadastro, dataNascimento, horaTreino, sexo, segunda,
                        terca, quarta, quinta, sexta, sabado);
            }
            return cliente;
        } catch (Exception e) {
            return null;
        }

    }

    public List<Cliente> getClientesAtivos() {
        String sql = "SELECT * FROM Cliente WHERE status = 1 order by horaTreino ASC";

        ResultSet resultSet;

        List<Cliente> clientes = new ArrayList();
        try {

            connection = Conexao.conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                int id = (resultSet.getInt("idCliente"));
                String nome = (resultSet.getString("nome"));
                String cpf = (resultSet.getString("cpf"));
                boolean status = (resultSet.getBoolean("status"));
                String contato = (resultSet.getString("telefone"));
                String endereco = (resultSet.getString("endereco"));
                String bairro = (resultSet.getString("bairro"));
                String CEP = (resultSet.getString("cep"));
                String observacao = (resultSet.getString("observacao"));
                String email = (resultSet.getString("email"));
                String estado = (resultSet.getString("estado"));
                String numero = (String.valueOf(resultSet.getInt("numero")));
                String cidade = (resultSet.getString("cidade"));
                LocalDate dataCadastro = (resultSet.getDate("dataCadastro").toLocalDate());
                LocalDate dataNascimento = (resultSet.getDate("dataNascimento").toLocalDate());
                String horaTreino = (resultSet.getString("horaTreino"));
                Boolean sexo = (resultSet.getBoolean("sexo"));
                boolean segunda = (resultSet.getBoolean("segunda"));
                boolean terca = (resultSet.getBoolean("terca"));
                boolean quarta = (resultSet.getBoolean("quarta"));
                boolean quinta = (resultSet.getBoolean("quinta"));
                boolean sexta = (resultSet.getBoolean("sexta"));
                boolean sabado = (resultSet.getBoolean("sabado"));
                Cliente cliente = new Cliente(nome, cpf, contato, observacao, endereco, bairro, cidade, estado,
                        status, email, CEP, numero, id, dataCadastro, dataNascimento, horaTreino, sexo, segunda, terca,
                        quarta, quinta, sexta, sabado);
                clientes.add(cliente);
            }
        } catch (Exception e) {
            return null;

        }

        return clientes;
    }

//    public ArrayList<QuadroHorario> getAlunosPorHoraDiaTreino(String dia) throws ClassNotFoundException {
//        int hora = 00;
//        int minuto = 45;
//        ResultSet resultSet;
//        ArrayList<QuadroHorario> quadroHorarios = new ArrayList<QuadroHorario>();
//        
//        for (hora = 23; hora >= 00; hora--) {
//            minuto = 45;
//            while (minuto >= 0) {
//                String sql = "SELECT count('" + hora + ":" + minuto + "') from Cliente where " + dia + " = 1 AND horaTreino = '" + hora + ":" + minuto + "' ";
//                System.err.println(sql);
//                try {
//                    
//                    connection = Conexao.conexao();
//                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                    resultSet = preparedStatement.executeQuery(sql);
//                    if(resultSet.getRow() > 0){
//                        
//                        QuadroHorario qHora = new QuadroHorario();
//                        String horaFinal = String.valueOf(hora) + ":" + String.valueOf(minuto);
//                        qHora.setHora(horaFinal);
//                        qHora.setQtdePessoasPorHora(resultSet.getRow());
//                        quadroHorarios.add(qHora);
//                    }
//                }catch(SQLException e){
//                    e.printStackTrace();
//                }
//                minuto = minuto - 15;
//                System.err.println("hora : " + hora);
//            }
//        }
//        return quadroHorarios;
//    }
}
