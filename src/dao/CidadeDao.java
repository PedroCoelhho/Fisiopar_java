package dao;

import engine.MysqlConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cidade;

/**
 * Data Access Object para a tabela e classe Cidade
 * @author Juan Galvão
 */
public class CidadeDao {
    public List<Cidade> listar() {
        String sql = "SELECT * FROM cidade";

        List<Cidade> cidades = new ArrayList<>();

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
                Cidade cidade = new Cidade();

                //Recupera o id do banco e atribui ele ao objeto
                cidade.setIdcidade(rset.getInt("idcidade"));
                cidade.setCidadeibge(rset.getInt("cidadeibge"));
                cidade.setIdestado(rset.getString("idestado"));
                cidade.setNomedesc(rset.getString("nomedesc"));
                
                //Adiciono o contato recuperado, a lista de contatos
                cidades.add(cidade);
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
                //e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro inexesperado.\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        return cidades;
    }
    
    public List<Cidade> listarLike(String cid) {
        String sql = "SELECT * FROM cidade WHERE nomedesc LIKE '%"+ cid + "%'";

        List<Cidade> cidades = new ArrayList<>();

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
                Cidade cidade = new Cidade();

                //Recupera o id do banco e atribui ele ao objeto
                cidade.setIdcidade(rset.getInt("idcidade"));
                cidade.setCidadeibge(rset.getInt("cidadeibge"));
                cidade.setIdestado(rset.getString("idestado"));
                cidade.setNomedesc(rset.getString("nomedesc"));
                
                //Adiciono o contato recuperado, a lista de contatos
                cidades.add(cidade);
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
                //e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro inexesperado.\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        return cidades;
    }
}
