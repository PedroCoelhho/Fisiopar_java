/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import engine.MysqlConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Ubs;

/**
 *
 * @author Juan
 */
public class UbsDao {
    public List<Ubs> listar() {
        
        String sql = "SELECT * FROM ubs";

        List<Ubs> lUbs = new ArrayList<>();

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
                Ubs ubs = new Ubs();

                //Recupera o id do banco e atribui ele ao objeto
                ubs.setId(rset.getInt("id"));
                ubs.setCodUbs(rset.getInt("codUbs"));
                ubs.setNomeUbs(rset.getString("nomeUbs"));
                
                
                //Adiciono o contato recuperado, a lista de contatos
                lUbs.add(ubs);
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

        return lUbs;
    }
    
    public List<Ubs> listarWhere(String coluna, String valor) {
        
        String sql = String.format("SELECT * FROM ubs WHERE %s = '%s'",
                coluna, valor
        );

        List<Ubs> lUbs = new ArrayList<>();

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
                Ubs ubs = new Ubs();

                //Recupera o id do banco e atribui ele ao objeto
                ubs.setId(rset.getInt("id"));
                ubs.setCodUbs(rset.getInt("codUbs"));
                ubs.setNomeUbs(rset.getString("nomeUbs"));
                
                
                //Adiciono o contato recuperado, a lista de contatos
                lUbs.add(ubs);
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

        return lUbs;
    }
}
