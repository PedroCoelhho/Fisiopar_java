package control;

import dao.EnderecoDao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Endereco;

/**
 * Classe de Controle de Endereços
 * @author Juan Galvão
 */
public class EnderecoC {
    /** Variável global para acessar métodos da classe */
    public static final EnderecoC CONTROL = new EnderecoC();
    
    // Instância do DAO
    EnderecoDao endDao = new EnderecoDao();
    
    /**
     * Método para salvar no banco de dados
     * @param endereco Recebe objeto do endereço
     * @return True ou false para saber se salvou com sucesso
     */
    public boolean create(Endereco endereco) {
        if(endDao.salvar(endereco)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Endereço registrado com sucesso.",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Lista endereços para uma tabela JTable
     * @param tabela Tabela JTable que armazenará os dados
     * @param where True se você deseja buscar por CEP
     * @param cep Caso o where seja true, ele buscará pelo CEP aqui passado
     */
    public void listTable(JTable tabela, boolean where, int...cep) {
        List<Endereco> listEndereco;
        
        // Atualizar Tabela
        if(where == false) {
            listEndereco = endDao.listarCidade();
        } else {
            listEndereco = endDao.listarCidadeWhere(cep[0]);
        }
        
        
        // Clear
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        
        for(Endereco h : listEndereco) {
            //CI_Roupa rpa = new CI_Roupa(r.getNome(), r);
            model = (DefaultTableModel) tabela.getModel();
            model.addRow(new Object[]{
                h.getId(),
                h.getCep(), h.getCidade(), h.getLogradouro(),
                h.getNumero(), h.getBairro(), h.getComplemento()
            });
        }
    }
}
