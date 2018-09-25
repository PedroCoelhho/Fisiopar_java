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
import model.Funcionario;
import engine.MysqlConn;
import java.time.LocalDateTime;
import util.Yagami;

/**
 * Data Access Object para a tabela e classe funcionario
 * @author Juan Galvão
 */
public class FuncionarioDao {
    //public static HoraDao hd = new HoraDao();
    
    // Métodos Implementados
    public List<Funcionario> listar(boolean where, int... id) {
        String sql;
        if (where == false) {
            sql = "SELECT * FROM funcionario";
        }  else {
            sql = "SELECT * FROM funcionario WHERE id = " + id[0];
        }

        List<Funcionario> funcionarios = new ArrayList<>();

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
                Funcionario funcionario = new Funcionario();

                //Recupera o id do banco e atribui ele ao objeto
                funcionario.setId(rset.getInt("id"));
                funcionario.setNome(rset.getString("nome"));
                funcionario.setData_nasc(rset.getDate("data_nasc"));
                funcionario.setSexo(rset.getString("sexo"));
                funcionario.setCpf(rset.getString("cpf"));
                funcionario.setRg(rset.getString("rg"));
                funcionario.setEst_civ(rset.getString("est_civ"));
                funcionario.setEtnia(rset.getString("etnia"));
                funcionario.setNome_resp(rset.getString("nome_resp"));
                funcionario.setNome_mae(rset.getString("nome_mae"));
                funcionario.setTel_prim(rset.getString("tel_prim"));
                funcionario.setTel_sec(rset.getString("tel_sec"));
                funcionario.setEmail(rset.getString("email"));
                funcionario.setFk_endereco(rset.getInt("fk_endereco"));
                funcionario.setCargo(rset.getString("cargo"));
                funcionario.setSetor(rset.getString("setor"));
                funcionario.setData_adm(rset.getDate("data_adm"));
                //....
                
                //Adiciono o contato recuperado, a lista de contatos
                funcionarios.add(funcionario);
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

        return funcionarios;
    }

    public boolean salvar(Funcionario funcionario) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "INSERT INTO funcionario\n" +
        "(nome, data_nasc, sexo, cpf, rg, est_civ,\n" +
        "etnia, nome_resp, nome_mae, tel_prim,\n" +
        "tel_sec, email, fk_endereco, cargo, setor, data_adm \n" +
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
            pstm.setString(1,  funcionario.getNome());
            pstm.setDate  (2,  funcionario.getData_nasc());
            pstm.setString(3,  funcionario.getSexo());
            pstm.setString(4,  funcionario.getCpf());
            pstm.setString(5,  funcionario.getRg());
            pstm.setString(6,  funcionario.getEst_civ());
            pstm.setString(7,  funcionario.getEtnia());
            pstm.setString(8,  funcionario.getNome_resp());
            pstm.setString(9,  funcionario.getNome_mae());
            pstm.setString(10, funcionario.getTel_prim());
            pstm.setString(11, funcionario.getTel_sec());
            pstm.setString(12, funcionario.getEmail());
            pstm.setInt   (13, funcionario.getFk_endereco());
            pstm.setString(14, funcionario.getCargo());
            pstm.setString(15, funcionario.getSetor());
            pstm.setDate  (16, funcionario.getData_adm());
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

    public boolean alterar(Funcionario funcionario) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "UPDATE funcionario\n" +
        "SET nome = ?, data_nasc = ?, sexo = ?, cpf = ?, rg = ?, est_civ = ?,\n" +
        "etnia = ?, nome_resp = ?, nome_mae = ?, tel_prim = ?,\n" +
        "tel_sec = ?, email = ?, fk_endereco = ?, cargo = ?, setor = ?, data_adm = ?\n" +
        "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1,  funcionario.getNome());
            pstm.setDate  (2,  funcionario.getData_nasc());
            pstm.setString(3,  funcionario.getSexo());
            pstm.setString(4,  funcionario.getCpf());
            pstm.setString(5,  funcionario.getRg());
            pstm.setString(6,  funcionario.getEst_civ());
            pstm.setString(7,  funcionario.getEtnia());
            pstm.setString(8,  funcionario.getNome_resp());
            pstm.setString(9,  funcionario.getNome_mae());
            pstm.setString(10, funcionario.getTel_prim());
            pstm.setString(11, funcionario.getTel_sec());
            pstm.setString(12, funcionario.getEmail());
            pstm.setInt   (13, funcionario.getFk_endereco());
            pstm.setString(12, funcionario.getCargo());
            pstm.setString(12, funcionario.getSetor());
            pstm.setDate (12, funcionario.getData_adm());

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
        String sql = "DELETE FROM funcionario "
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
    public List<Funcionario> listarCustom(String sql) {
        List<Funcionario> funcionarios = new ArrayList<>();

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
                Funcionario funcionario = new Funcionario();

                //Recupera o id do banco e atribui ele ao objeto
                funcionario.setId(rset.getInt("id"));
                funcionario.setNome(rset.getString("nome"));
                funcionario.setData_nasc(rset.getDate("data_nasc"));
                funcionario.setSexo(rset.getString("sexo"));
                funcionario.setCpf(rset.getString("cpf"));
                funcionario.setRg(rset.getString("rg"));
                funcionario.setEst_civ(rset.getString("est_civ"));
                funcionario.setEtnia(rset.getString("etnia"));
                funcionario.setNome_resp(rset.getString("nome_resp"));
                funcionario.setNome_mae(rset.getString("nome_mae"));
                funcionario.setTel_prim(rset.getString("tel_prim"));
                funcionario.setTel_sec(rset.getString("tel_sec"));
                funcionario.setEmail(rset.getString("email"));
                funcionario.setFk_endereco(rset.getInt("fk_endereco"));
                funcionario.setCargo(rset.getString("cargo"));
                funcionario.setSetor(rset.getString("setor"));
                funcionario.setData_adm(rset.getDate("data_adm"));
                //....
                
                //Adiciono o contato recuperado, a lista de contatos
                funcionarios.add(funcionario);
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

        return funcionarios;
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