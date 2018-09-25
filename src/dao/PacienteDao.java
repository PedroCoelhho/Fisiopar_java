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
import model.Paciente;
import engine.MysqlConn;
import java.time.LocalDateTime;
import util.Yagami;

/**
 * Data Access Object para a tabela e classe Paciente
 * @author Juan Galvão
 */
public class PacienteDao {
    //public static HoraDao hd = new HoraDao();
    
    // Métodos Implementados
    public List<Paciente> listar(boolean where, int... id) {
        String sql;
        if (where == false) {
            sql = "SELECT * FROM paciente";
        }  else {
            sql = "SELECT * FROM paciente WHERE id = " + id[0];
        }

        List<Paciente> pacientes = new ArrayList<>();

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
                Paciente paciente = new Paciente();

                //Recupera o id do banco e atribui ele ao objeto
                paciente.setId(rset.getInt("id"));
                paciente.setNome(rset.getString("nome"));
                paciente.setData_nasc(rset.getDate("data_nasc"));
                paciente.setSexo(rset.getString("sexo"));
                paciente.setCpf(rset.getString("cpf"));
                paciente.setRg(rset.getString("rg"));
                paciente.setEst_civ(rset.getString("est_civ"));
                paciente.setEtnia(rset.getString("etnia"));
                paciente.setNome_resp(rset.getString("nome_resp"));
                paciente.setNome_mae(rset.getString("nome_mae"));
                paciente.setTel_prim(rset.getString("tel_prim"));
                paciente.setTel_sec(rset.getString("tel_sec"));
                paciente.setEmail(rset.getString("email"));
                paciente.setFk_endereco(rset.getInt("fk_endereco"));
                paciente.setConvenio(rset.getString("convenio"));
                paciente.setCns(rset.getString("cns"));
                paciente.setValid_cart(rset.getDate("valid_cart"));
                paciente.setData_hr(rset.getString("dat_hr"));
                paciente.setObservacoes(rset.getString("observacoes"));
                //....
                
                //Adiciono o contato recuperado, a lista de contatos
                pacientes.add(paciente);
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

        return pacientes;
    }

    public boolean salvar(Paciente paciente) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "INSERT INTO paciente\n" +
        "(nome, data_nasc, sexo, cpf, rg, est_civ,\n" +
        "etnia, nome_resp, nome_mae, tel_prim,\n" +
        "tel_sec, email, fk_endereco, convenio, cns,\n" +
        "valid_cart, dat_hr)\n" +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,\n" +
        "?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1,  paciente.getNome());
            pstm.setDate  (2,  paciente.getData_nasc());
            pstm.setString(3,  paciente.getSexo());
            pstm.setString(4,  paciente.getCpf());
            pstm.setString(5,  paciente.getRg());
            pstm.setString(6,  paciente.getEst_civ());
            pstm.setString(7,  paciente.getEtnia());
            pstm.setString(8,  paciente.getNome_resp());
            pstm.setString(9,  paciente.getNome_mae());
            pstm.setString(10, paciente.getTel_prim());
            pstm.setString(11, paciente.getTel_sec());
            pstm.setString(12, paciente.getEmail());
            pstm.setInt   (13, paciente.getFk_endereco());
            pstm.setString(14, paciente.getConvenio());
            pstm.setString(15, paciente.getCns());
            pstm.setDate  (16, paciente.getValid_cart());
            pstm.setString(17, LocalDateTime.now().toString());

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
        String sql = "UPDATE paciente\n" +
        "SET nome = ?, data_nasc = ?, sexo = ?, cpf = ?, rg = ?, est_civ = ?,\n" +
        "etnia = ?, nome_resp = ?, nome_mae = ?, tel_prim = ?,\n" +
        "tel_sec = ?, email = ?, fk_endereco = ?, convenio = ?, cns = ?,\n" +
        "valid_cart = ?, dat_hr = ?, observacoes = ?\n" +
        "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1,  paciente.getNome());
            pstm.setDate  (2,  paciente.getData_nasc());
            pstm.setString(3,  paciente.getSexo());
            pstm.setString(4,  paciente.getCpf());
            pstm.setString(5,  paciente.getRg());
            pstm.setString(6,  paciente.getEst_civ());
            pstm.setString(7,  paciente.getEtnia());
            pstm.setString(8,  paciente.getNome_resp());
            pstm.setString(9,  paciente.getNome_mae());
            pstm.setString(10, paciente.getTel_prim());
            pstm.setString(11, paciente.getTel_sec());
            pstm.setString(12, paciente.getEmail());
            pstm.setInt   (13, paciente.getFk_endereco());
            pstm.setString(14, paciente.getConvenio());
            pstm.setString(15, paciente.getCns());
            pstm.setDate  (16, paciente.getValid_cart());
            pstm.setString(17, LocalDateTime.now().toString());
            pstm.setString(18, paciente.getObservacoes());
            pstm.setInt   (19, paciente.getId());

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
    public List<Paciente> listarCustom(String sql) {
        List<Paciente> pacientes = new ArrayList<>();

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
                Paciente paciente = new Paciente();

                //Recupera o id do banco e atribui ele ao objeto
                paciente.setId(rset.getInt("id"));
                paciente.setNome(rset.getString("nome"));
                paciente.setData_nasc(rset.getDate("data_nasc"));
                paciente.setSexo(rset.getString("sexo"));
                paciente.setCpf(rset.getString("cpf"));
                paciente.setRg(rset.getString("rg"));
                paciente.setEst_civ(rset.getString("est_civ"));
                paciente.setEtnia(rset.getString("etnia"));
                paciente.setNome_resp(rset.getString("nome_resp"));
                paciente.setNome_mae(rset.getString("nome_mae"));
                paciente.setTel_prim(rset.getString("tel_prim"));
                paciente.setTel_sec(rset.getString("tel_sec"));
                paciente.setEmail(rset.getString("email"));
                paciente.setFk_endereco(rset.getInt("fk_endereco"));
                paciente.setConvenio(rset.getString("convenio"));
                paciente.setCns(rset.getString("cns"));
                paciente.setValid_cart(rset.getDate("valid_cart"));
                paciente.setData_hr(rset.getString("dat_hr"));
                paciente.setObservacoes(rset.getString("observacoes"));
                //....
                
                //Adiciono o contato recuperado, a lista de contatos
                pacientes.add(paciente);
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

        return pacientes;
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
        String sql = "SELECT * FROM paciente " + "WHERE cpf = " + Cpf;

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