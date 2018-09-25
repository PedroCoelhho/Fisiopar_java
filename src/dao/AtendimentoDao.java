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
import model.Atendimento;
import util.Yagami;

/**
 * Data Access Object para a tabela e classe Paciente
 * @author Juan Galvão
 */
public class AtendimentoDao {
    //public static HoraDao hd = new HoraDao();
    
    // Métodos Implementados
    public List<Atendimento> listar(boolean where, int... id) {
        String sql;
        if (where == false) {
            sql = "SELECT * FROM atendimento";
        }  else {
            sql = "SELECT * FROM atendimento WHERE id_atendimento = " + id[0];
        }

        List<Atendimento> atendimentos = new ArrayList<>();

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
                Atendimento atendimento = new Atendimento();

                //Recupera o id do banco e atribui ele ao objeto
                atendimento.setId_atendimento(rset.getInt("id_atendimento"));
                atendimento.setTipo_atendimento(rset.getString("tipo_atendimento"));
                atendimento.setFk_medico(rset.getInt("fk_medico"));
                atendimento.setBloco_inicial(rset.getString("bloco_inicial"));
                atendimento.setBloco_atual(rset.getString("bloco_atual"));
                atendimento.setHorario(rset.getTime("horario"));
                atendimento.setData_at(rset.getDate("data_at"));
                atendimento.setObservacoes(rset.getString("observacoes"));
                atendimento.setFk_patendimento(rset.getInt("fk_patendimento"));
                atendimento.setFk_ubs(rset.getInt("fk_ubs"));
                
                //....
                
                //Adiciono o contato recuperado, a lista de contatos
                atendimentos.add(atendimento);
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

        return atendimentos;
    }

    public boolean salvar(Atendimento atendimento) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "INSERT INTO atendimento\n" +
        "(tipo_atendimento, fk_medico,\n" +
        "bloco_inicial, bloco_atual, horario, data_at,\n" +
        "observacoes, fk_patendimento, fk_ubs)\n" +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1,  atendimento.getTipo_atendimento());
            pstm.setInt   (2,  atendimento.getFk_medico());
            pstm.setString(3,  atendimento.getBloco_inicial());
            pstm.setString(4,  atendimento.getBloco_atual());
            pstm.setTime  (5,  atendimento.getHorario());
            pstm.setDate  (6,  atendimento.getData_at());
            pstm.setString(7,  atendimento.getObservacoes());
            pstm.setInt   (8,  atendimento.getFk_patendimento());
            pstm.setInt   (9,  atendimento.getFk_ubs());

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
    public int getMaxId() {
        String sql = "SELECT MAX(id) as id FROM atendimento";
        int id = 0;
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
                //Recupera o id do banco e atribui ele ao objeto
                id = rset.getInt("id");
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

        return id;
    }
    

}