package control;

import dao.UbsDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Ubs;

/**
 * Classe para controle de Agenda
 * @author Juan Galvão
 */
public class UbsC {
    /** Variável global para acessar métodos da classe */
    public static final UbsC CONTROL = new UbsC();
    
    // Instância do DAO
    UbsDao ubsDao = new UbsDao();
    
    public void listComboBox(JComboBox comboBox) {
        List<Ubs> listUbs = ubsDao.listar();
        List<String> nameUbs = new ArrayList<>();
        
        for(Ubs u : listUbs) {
            nameUbs.add(u.getNomeUbs());
        }
        
        comboBox.setModel(new DefaultComboBoxModel(nameUbs.toArray()));
    }
    
    public void listTable(JTable tabela) {
        // Atualizar Tabela
        List<Ubs> listUbs = ubsDao.listar();
        
        // Clear
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        
        for(Ubs h : listUbs) {
            //CI_Roupa rpa = new CI_Roupa(r.getNome(), r);
            model = (DefaultTableModel) tabela.getModel();
            model.addRow(new Object[]{h.getCodUbs(), h.getNomeUbs()});
        }
    }
}
