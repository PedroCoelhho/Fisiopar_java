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
import model.Aluno;
import engine.MysqlConn;
import java.time.LocalDateTime;
import util.Yagami;

/**
 * Data Access Object para a tabela e classe Paciente
 * @author João Sanches
 */
public class AlunoDao {
    //public static HoraDao hd = new HoraDao();
    
    // Métodos Implementados
    public List<Aluno> listar(boolean where, int... id) {
        String sql;
        if (where == false) {
            sql = "SELECT * FROM aluno";
        }  else {
            sql = "SELECT * FROM aluno WHERE id = " + id[0];
        }

        List<Aluno> alunos = new ArrayList<>();

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
                Aluno aluno = new Aluno();

                //Recupera o id do banco e atribui ele ao objeto
                aluno.setId(rset.getInt("id"));
                aluno.setNome(rset.getString("nome"));
                aluno.setData_nasc(rset.getDate("data_nasc"));
                aluno.setSexo(rset.getString("sexo"));
                aluno.setCpf(rset.getString("cpf"));
                aluno.setRg(rset.getString("rg"));
                aluno.setEst_civ(rset.getString("est_civ"));
                aluno.setEtnia(rset.getString("etnia"));
                aluno.setNome_resp(rset.getString("nome_resp"));
                aluno.setNome_mae(rset.getString("nome_mae"));
                aluno.setTel_prim(rset.getString("tel_prim"));
                aluno.setTel_sec(rset.getString("tel_sec"));
                aluno.setEmail(rset.getString("email"));
                aluno.setFk_endereco(rset.getInt("fk_endereco"));
                aluno.setRa(rset.getString("ra"));
                aluno.setCurso(rset.getString("curso"));
                aluno.setCoordenador(rset.getString("coordenador"));
                
                //....
                
                //Adiciono o contato recuperado, a lista de contatos
                alunos.add(aluno);
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

        return alunos;
    }

    public boolean salvar(Aluno aluno) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "INSERT INTO aluno\n" +
        "(nome, data_nasc, sexo, cpf, rg, est_civ,\n" +
        "etnia, nome_resp, nome_mae, tel_prim,\n" +
        "tel_sec, email, fk_endereco, ra, curso, coordenador)\n" +
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
            pstm.setString(1,  aluno.getNome());
            pstm.setDate  (2,  aluno.getData_nasc());
            pstm.setString(3,  aluno.getSexo());
            pstm.setString(4,  aluno.getCpf());
            pstm.setString(5,  aluno.getRg());
            pstm.setString(6,  aluno.getEst_civ());
            pstm.setString(7,  aluno.getEtnia());
            pstm.setString(8,  aluno.getNome_resp());
            pstm.setString(9,  aluno.getNome_mae());
            pstm.setString(10, aluno.getTel_prim());
            pstm.setString(11, aluno.getTel_sec());
            pstm.setString(12, aluno.getEmail());
            pstm.setInt   (13, aluno.getFk_endereco());
            pstm.setString(14, aluno.getRa());
            pstm.setString(15, aluno.getCurso());
            pstm.setString(16, aluno.getCoordenador());

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

    public boolean alterar(Aluno aluno) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "UPDATE aluno\n" +
        "SET nome = ?, data_nasc = ?, sexo = ?, cpf = ?, rg = ?, est_civ = ?,\n" +
        "etnia = ?, nome_resp = ?, nome_mae = ?, tel_prim = ?,\n" +
        "tel_sec = ?, email = ?, fk_endereco = ?, ra = ?, curso = ?, coordenador = ?\n" +
        "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setInt   (19, aluno.getId());
            pstm.setString(1,  aluno.getNome());
            pstm.setDate  (2,  aluno.getData_nasc());
            pstm.setString(3,  aluno.getSexo());
            pstm.setString(4,  aluno.getCpf());
            pstm.setString(5,  aluno.getRg());
            pstm.setString(6,  aluno.getEst_civ());
            pstm.setString(7,  aluno.getEtnia());
            pstm.setString(8,  aluno.getNome_resp());
            pstm.setString(9,  aluno.getNome_mae());
            pstm.setString(10, aluno.getTel_prim());
            pstm.setString(11, aluno.getTel_sec());
            pstm.setString(12, aluno.getEmail());
            pstm.setInt   (13, aluno.getFk_endereco());
            pstm.setString(14, aluno.getRa());
            pstm.setString(15, aluno.getCurso());
            pstm.setString(16, aluno.getCoordenador());
            

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
        String sql = "DELETE FROM aluno "
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
    public List<Aluno> listarCustom(String sql) {
        List<Aluno> alunos = new ArrayList<>();

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
                Aluno aluno = new Aluno();

                //Recupera o id do banco e atribui ele ao objeto
                aluno.setId(rset.getInt("id"));
                aluno.setNome(rset.getString("nome"));
                aluno.setData_nasc(rset.getDate("data_nasc"));
                aluno.setSexo(rset.getString("sexo"));
                aluno.setCpf(rset.getString("cpf"));
                aluno.setRg(rset.getString("rg"));
                aluno.setEst_civ(rset.getString("est_civ"));
                aluno.setEtnia(rset.getString("etnia"));
                aluno.setNome_resp(rset.getString("nome_resp"));
                aluno.setNome_mae(rset.getString("nome_mae"));
                aluno.setTel_prim(rset.getString("tel_prim"));
                aluno.setTel_sec(rset.getString("tel_sec"));
                aluno.setEmail(rset.getString("email"));
                aluno.setFk_endereco(rset.getInt("fk_endereco"));
                aluno.setRa(rset.getString("ra"));
                aluno.setCurso(rset.getString("curso"));
                aluno.setCoordenador(rset.getString("coordenador"));
                //....
                
                //Adiciono o contato recuperado, a lista de contatos
                alunos.add(aluno);
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

        return alunos;
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