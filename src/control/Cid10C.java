/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.Cid10Dao;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cid10;
import view.telas.FiltrarCid10;

/**
 *
 * @author david
 */
public class Cid10C {
    public static final Cid10C CONTROL = new Cid10C();
    
    // Instância do DAO
    Cid10Dao cid10Dao = new Cid10Dao();
    
    /**
     * Lista os resultados da consulta para uma Lista
     * @param like True se você quer buscar usando o comando LIKE % %
     * @param param Caso like seja true, a query será executada com esse parametro no Like
     * @return Uma lista com os resultados encontrados da tabela Cidade
     */
    public List<Cid10> listar(boolean like, String...param) {
        List<Cid10>cid10;
        
        if(like) {
            cid10 = cid10Dao.listarLike(param[0]);
        } else {
            cid10 = cid10Dao.listar();
        }
        
        return cid10;
    }
    
    /**
     * Lista cidades para uma tabela JTable
     * @param tabela Tabela JTable que armazenará os dados
     * @param like True se você quer buscar usando o comando LIKE % %
     * @param param Caso like seja true, a query será executada com esse parametro no Like
     */
    public void listTable(JTable tabela, boolean like, String...param) {
        List<Cid10> listCid10;
        
        // Atualizar Tabela
        if(like) {
            listCid10 = cid10Dao.listarLike(param[0]);
        } else {
            listCid10 = cid10Dao.listar();
        }
        
        // Clear
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        
        for(Cid10 h : listCid10) {
            //CI_Roupa rpa = new CI_Roupa(r.getNome(), r);
            model = (DefaultTableModel) tabela.getModel();
            model.addRow(new Object[]{
                h.getNome()
            });
        }
    }
   
    
}
