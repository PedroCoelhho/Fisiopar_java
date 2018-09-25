package control;

import dao.CidadeDao;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cidade;

/**
 * Classe de Controle de Cidades
 * @author Juan Galvão
 */
public class CidadeC {
    /** Variável global para acessar métodos da classe */
    public static final CidadeC CONTROL = new CidadeC();
    
    // Instância do DAO
    CidadeDao cidDao = new CidadeDao();
    
    /**
     * Lista os resultados da consulta para uma Lista
     * @param like True se você quer buscar usando o comando LIKE % %
     * @param param Caso like seja true, a query será executada com esse parametro no Like
     * @return Uma lista com os resultados encontrados da tabela Cidade
     */
    public List<Cidade> listar(boolean like, String...param) {
        List<Cidade> cidades;
        
        if(like) {
            cidades = cidDao.listarLike(param[0]);
        } else {
            cidades = cidDao.listar();
        }
        
        return cidades;
    }
    
    /**
     * Lista cidades para uma tabela JTable
     * @param tabela Tabela JTable que armazenará os dados
     * @param like True se você quer buscar usando o comando LIKE % %
     * @param param Caso like seja true, a query será executada com esse parametro no Like
     */
    public void listTable(JTable tabela, boolean like, String...param) {
        List<Cidade> listCidade;
        
        // Atualizar Tabela
        if(like) {
            listCidade = cidDao.listarLike(param[0]);
        } else {
            listCidade = cidDao.listar();
        }
        
        // Clear
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        
        for(Cidade h : listCidade) {
            //CI_Roupa rpa = new CI_Roupa(r.getNome(), r);
            model = (DefaultTableModel) tabela.getModel();
            model.addRow(new Object[]{
                h.getIdcidade(), h.getNomedesc()
            });
        }
    }
}
