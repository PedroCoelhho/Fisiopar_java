/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Fisioterapeuta;
import engine.MysqlConn;
import util.Yagami;

/**
 * Data Access Object para a tabela e classe Fisioterapeuta
 * @author Juan Galvão
 */
public class FisioterapeutaDao {
    //public static HoraDao hd = new HoraDao();
    
    // Métodos Implementados
    public List<Fisioterapeuta> listar(boolean where, int... id) {
        String sql;
        if (where == false) {
            sql = "SELECT * FROM medico";
        }  else {
            sql = "SELECT * FROM medico WHERE id = " + id[0];
        }

        List<Fisioterapeuta> fisioterapeutas = new ArrayList<>();

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
                Fisioterapeuta fisioterapeuta = new Fisioterapeuta();

                //Recupera o id do banco e atribui ele ao objeto
                fisioterapeuta.setId(rset.getInt("id"));
                fisioterapeuta.setNome(rset.getString("nome"));
                fisioterapeuta.setCpf(rset.getString("cpf"));
                fisioterapeuta.setRg(rset.getString("rg"));
                fisioterapeuta.setTel_prim(rset.getString("tel_prim"));
                fisioterapeuta.setTel_sec(rset.getString("tel_sec"));
                fisioterapeuta.setEmail(rset.getString("email"));
                fisioterapeuta.setEspecialidade(rset.getString("especialidade"));
                fisioterapeuta.setFk_endereco(rset.getInt("fk_endereco"));
                
                //....
                
                //Adiciono o contato recuperado, a lista de contatos
                fisioterapeutas.add(fisioterapeuta);
            }
            
        } catch (Exception e) {
            //e.printStackTrace();
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
                //e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro inexesperado.\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        return fisioterapeutas;
    }

    public boolean salvar(Fisioterapeuta fisioterapeuta) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "INSERT INTO medico\n" +
        "(nome, cpf, rg, \n" +
        "tel_prim,\n" +
        "tel_sec, email, especialidade, fk_endereco)\n" +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1,  fisioterapeuta.getNome());
            
            pstm.setString(2,  fisioterapeuta.getCpf());
            pstm.setString(3,  fisioterapeuta.getRg());
            
            pstm.setString(4, fisioterapeuta.getTel_prim());
            pstm.setString(5, fisioterapeuta.getTel_sec());
            pstm.setString(6, fisioterapeuta.getEmail());
            pstm.setString(7, fisioterapeuta.getEspecialidade());
            pstm.setInt(8, fisioterapeuta.getFk_endereco());

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

    public boolean alterar(Fisioterapeuta fisioterapeuta) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "UPDATE medico\n" +
        "SET nome = ?,cpf = ?, rg = ?,\n" +
        " tel_prim = ?,\n" +
        "tel_sec = ?, email = ?, especialidade = ?, fk_endereco = ?\n" +
        "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setInt   (19, fisioterapeuta.getId());
            pstm.setString(1,  fisioterapeuta.getNome());
            pstm.setString(2,  fisioterapeuta.getCpf());
            pstm.setString(3,  fisioterapeuta.getRg());
            pstm.setString(4, fisioterapeuta.getTel_prim());
            pstm.setString(5, fisioterapeuta.getTel_sec());
            pstm.setString(6, fisioterapeuta.getEmail());
            pstm.setString(7, fisioterapeuta.getEspecialidade());
            pstm.setInt(8, fisioterapeuta.getFk_endereco());
            

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
                //e.printStackTrace();
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
        String sql = "DELETE FROM medico "
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
    public List<Fisioterapeuta> listarCustom(String sql) {
        List<Fisioterapeuta> fisioterapeutas = new ArrayList<>();

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
                Fisioterapeuta fisioterapeuta = new Fisioterapeuta();

                //Recupera o id do banco e atribui ele ao objeto
                fisioterapeuta.setId(rset.getInt("id"));
                fisioterapeuta.setNome(rset.getString("nome"));
                fisioterapeuta.setCpf(rset.getString("cpf"));
                fisioterapeuta.setRg(rset.getString("rg"));
                fisioterapeuta.setTel_prim(rset.getString("tel_prim"));
                fisioterapeuta.setTel_sec(rset.getString("tel_sec"));
                fisioterapeuta.setEmail(rset.getString("email"));
                fisioterapeuta.setEspecialidade(rset.getString("especialidade"));
                fisioterapeuta.setFk_endereco(rset.getInt("fk_endereco"));
                //....
                
                //Adiciono o contato recuperado, a lista de contatos
                fisioterapeutas.add(fisioterapeuta);
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

        return fisioterapeutas;
    }
    
 /**
     * Metodo usado para verificacao de Cpf cadastrado
     * @param CPF
     * @return 
     */
    public boolean BuscarCpf(String Cpf) throws SQLException {
        boolean retorno = false;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
         */
        String sql = "SELECT * FROM medico " + "WHERE cpf = " + Cpf;

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Executa a sql para inserção dos dados
            pstm.execute();
            //Se a sua consulta obter algum registro retorne true
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                retorno = true;
            }

        } catch (Exception e) {
            Yagami.mensagemErro(e);
            retorno = false;

        } finally {
            //Fecha as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                Yagami.mensagemErro(e);
                retorno = false;
            }
        }
        return retorno;
    }

}