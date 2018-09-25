package dao;

import engine.MysqlConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.*;
import util.Yagami;

/**
 * Data Access Object para a tabela e classe Endereco
 * @author Juan Galvão
 */
public class EnderecoDao {
    public List<Endereco> listar() {
        String sql = "SELECT * FROM endereco";

        List<Endereco> enderecos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = MysqlConn.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                Endereco endereco = new Endereco();

                //Recupera o id do banco e atribui ele ao objeto
                endereco.setId(rset.getInt("id"));
                endereco.setCep(rset.getString("cep"));
                endereco.setFk_cidade(rset.getInt("fk_cidade"));
                endereco.setLogradouro(rset.getString("logradouro"));
                endereco.setNumero(rset.getInt("numero"));
                endereco.setBairro(rset.getString("bairro"));
                endereco.setComplemento(rset.getString("complemento"));
                
                //Adiciono o contato recuperado, a lista de contatos
                enderecos.add(endereco);
            }
            
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro inexesperado.\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
            
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                Yagami.mensagemErro(e);
            }
        }

        return enderecos;
    }

    public boolean salvar(Endereco endereco) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "INSERT INTO endereco\n" +
        "(cep, fk_cidade, logradouro, numero, bairro, complemento)\n" +
        "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1,  endereco.getCep());
            pstm.setInt   (2,  endereco.getFk_cidade());
            pstm.setString(3,  endereco.getLogradouro());
            pstm.setInt   (4,  endereco.getNumero());
            pstm.setString(5,  endereco.getBairro());
            pstm.setString(6,  endereco.getComplemento());
           

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            Yagami.mensagemErro(e);
            retorno = false;
        
        } finally {
            //Fecha as conexões
            try{
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }

            } catch(SQLException e){
                Yagami.mensagemErro(e);
                retorno = false;
            }
        }
        return retorno;
    }

    public boolean alterar(Paciente paciente) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = String.format("UPDATE paciente "
                   + "SET nome = '%s', data_nasc = '%s', sexo = '%s' "
                   + "WHERE id = %s", paciente.getNome(), paciente.getData_nasc(), paciente.getSexo(), paciente.getId());

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            /*
            pstm.setString(1, paciente.getNome());
            pstm.setDate(2, paciente.getData_nasc());
            pstm.setString(3, paciente.getSexo());
            pstm.setInt(4, paciente.getId());
            */
            //Executa a sql para inserção dos dados
            
            pstm.execute();

        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Algo deu errado...\n" + e,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            
            retorno = false;
        
        } finally {
            //Fecha as conexões
            try{
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }

            } catch(SQLException e){
                Yagami.mensagemErro(e);
                retorno = false;
            }
        }
        return retorno;
    }

    public boolean deletar(int id) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "DELETE FROM paciente "
                   + "WHERE id = " + id;

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            Yagami.mensagemErro(e);
            
            retorno = false;
        
        } finally {
            //Fecha as conexões
            try{
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }

            } catch(SQLException e){
                Yagami.mensagemErro(e);
                retorno = false;
            }
        }
        return retorno;
    }
    
    // CUSTOM
    public List<Endereco> listarCidade() {
        String sql = "SELECT id, cep, cidade.nomedesc, logradouro, numero, bairro, complemento\n" +
                     "FROM endereco\n" +
                     "INNER JOIN cidade ON endereco.fk_cidade=cidade.idcidade";
        
        List<Endereco> enderecos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = MysqlConn.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                Endereco endereco = new Endereco();

                //Recupera o id do banco e atribui ele ao objeto
                endereco.setId(rset.getInt("id"));
                endereco.setCep(rset.getString("cep"));
                endereco.setCidade(rset.getString("cidade.nomedesc"));
                endereco.setLogradouro(rset.getString("logradouro"));
                endereco.setNumero(rset.getInt("numero"));
                endereco.setBairro(rset.getString("bairro"));
                endereco.setComplemento(rset.getString("complemento"));
                
                //Adiciono o contato recuperado, a lista de contatos
                enderecos.add(endereco);
            }
            
        } catch (Exception e) {
            Yagami.mensagemErro(e);
            
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                Yagami.mensagemErro(e);
            }
        }

        return enderecos;
    }
    
    public List<Endereco> listarCidadeWhere(int cep) {
        String sql = "SELECT id, cep, cidade.nomedesc, logradouro, numero, bairro, complemento\n" +
                     "FROM endereco\n" +
                     "INNER JOIN cidade ON endereco.fk_cidade=cidade.idcidade\n" +
                     "WHERE cep = " + cep;
        
        List<Endereco> enderecos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = MysqlConn.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                Endereco endereco = new Endereco();

                //Recupera o id do banco e atribui ele ao objeto
                endereco.setId(rset.getInt("id"));
                endereco.setCep(rset.getString("cep"));
                endereco.setCidade(rset.getString("cidade.nomedesc"));
                endereco.setLogradouro(rset.getString("logradouro"));
                endereco.setNumero(rset.getInt("numero"));
                endereco.setBairro(rset.getString("bairro"));
                endereco.setComplemento(rset.getString("complemento"));
                
                //Adiciono o contato recuperado, a lista de contatos
                enderecos.add(endereco);
            }
            
        } catch (Exception e) {
            Yagami.mensagemErro(e);
            
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                Yagami.mensagemErro(e);
            }
        }

        return enderecos;
    }
}
