package control;

import dao.AgendaDao;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Agenda;

/**
 * Classe para controle de Agenda
 * @author Juan Galvão
 */
public class AgendaC {
    /** Variável global para acessar métodos da classe */
    public static final AgendaC CONTROL = new AgendaC();
    
    // Instância do DAO
    AgendaDao ageDao = new AgendaDao();
    
    public void listTable(JTable tabela, String coluna, String valor) {
        // Atualizar Tabela
        List<Agenda> listAgenda = ageDao.listarWhere(coluna, valor);
        
        // Clear
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        
        for(Agenda h : listAgenda) {
            //CI_Roupa rpa = new CI_Roupa(r.getNome(), r);
            model = (DefaultTableModel) tabela.getModel();
            model.addRow(new Object[]{h.getHora_ag(), h.getTipo(), h.getFk_atendimento(), "EOQ"});
        }
    }
}
