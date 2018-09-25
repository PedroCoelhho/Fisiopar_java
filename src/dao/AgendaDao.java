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
import model.Agenda;

/**
 *
 * @author Juan
 */
public class AgendaDao {
    public List<Agenda> listarWhere(String coluna, String valor) {
        
        String sql = String.format("SELECT * FROM agenda WHERE %s = '%s'",
                coluna, valor
        );

        List<Agenda> agendas = new ArrayList<>();

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
                Agenda agenda = new Agenda();

                //Recupera o id do banco e atribui ele ao objeto
                agenda.setId_agenda(rset.getInt("id_agenda"));
                agenda.setData_ag(rset.getDate("data_ag"));
                agenda.setHora_ag(rset.getTime("hora_ag"));
                agenda.setTipo(rset.getString("tipo"));
                agenda.setFk_atendimento(rset.getInt("fk_atendimento"));
                agenda.setFk_consulta(rset.getInt("fk_consulta"));
                agenda.setDetalhes(rset.getString("detalhes"));
                
                //Adiciono o contato recuperado, a lista de contatos
                agendas.add(agenda);
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

        return agendas;
    }
}
