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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;
import model.Cid10;
import model.Paciente;
import util.Yagami;
import view.telas.FiltrarCid10;

/**
 *
 * @author david
 */
public class Cid10Dao {
    
    public List<Cid10> listar() {
        String sql = "SELECT * FROM cid10";

        List<Cid10> cid10 = new ArrayList<>();

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
                Cid10 cids10 = new Cid10();

                //Recupera o id do banco e atribui ele ao objeto
                
                cids10.setNome(rset.getString("nome"));
                
                //Adiciono o contato recuperado, a lista de contatos
                cid10.add(cids10);
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

        return cid10;
    }
    
   public List<Cid10> listarLike(String cid) {
        String sql = "SELECT * FROM cid10 WHERE nome LIKE '%"+ cid + "%'";

        List<Cid10> cid10 = new ArrayList<>();

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
                Cid10 cids10 = new Cid10();

                //Recupera o id do banco e atribui ele ao objeto
                cids10.setNome(rset.getString("nome"));
                
                //Adiciono o contato recuperado, a lista de contatos
                cid10.add(cids10);
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

        return cid10;
    }
     
    
}
