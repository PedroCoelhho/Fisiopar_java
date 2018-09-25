package control;

import dao.FisioterapeutaDao;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Fisioterapeuta;

/**
 * Classe de Controle de fisioterapeutas
 * @author Zampiroli
 */
public class FisioterapeutaC {
    /** Variável global para acessar métodos da classe */
    public static final FisioterapeutaC CONTROL = new FisioterapeutaC();
    
    // Instância do DAO
    FisioterapeutaDao fisioDao = new FisioterapeutaDao();
    
    /**
     * Método para salvar no banco de dados
     * @param fisioterapeuta Recebe objeto do fisioterapeuta
     * @return True ou false para saber se salvou com sucesso
     */
    public boolean create(Fisioterapeuta fisioterapeuta) {
        if(fisioDao.salvar(fisioterapeuta)) {
            JOptionPane.showMessageDialog(null, "Fisioterapeuta cadastrado com sucesso!");
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Método para retornar lista de usuários direto da tabela
     * @param like True caso deseja usar a cláusula WHERE e LIKE para buscar
     * @param param Parametro em String para o LIKE: 'LIKE %param%'
     * @return Lista com os fisioterapeuta retornados da tabela
     */
    
    public List<Fisioterapeuta> read(boolean like, String param) {
        String sql;
        List<Fisioterapeuta> fisio;
        
        if(like) {
            sql = "SELECT * FROM medico WHERE nome LIKE '%" + param + "%'";
            fisio = fisioDao.listarCustom(sql);
        } else {
            sql = "SELECT * FROM medico";
            fisio = fisioDao.listarCustom(sql);
        }
        
        return fisio;
    }
    
    
    /**
     * Método para alterar o fisioterapeuta
     * @param fisioterapeuta Objeto do fisioterapeuta que será alterado
     */
    public void update(Fisioterapeuta fisioterapeuta) {
        if(fisioDao.alterar(fisioterapeuta)) {
            JOptionPane.showMessageDialog(null, "Fisioterapeuta alterado com sucesso!");
            
        }
    }
    
    /**
     * Deleta fisioterapeuta da tabela pelo ID
     * @param id ID do fisioterapeuta que será deletado
     */
    public void delete(int id) {
        if(fisioDao.deletar(id)) {
            JOptionPane.showMessageDialog(null, "Fisioterapeuta deletado com sucesso!");
        }
    }
    
    /**
     * Lista fisioterapeutas para uma tabela JTable
     * @param tabela Tabela JTable que armazenará os dados
     * @param where True se você deseja buscar por ID
     * @param id Caso o where seja true, ele buscará pela ID aqui passada
     */
    public void listTable(JTable tabela, boolean where, int...id) {
        // Atualizar Tabela
        List<Fisioterapeuta> listFisio;
        
        if(where == false) {
            listFisio = fisioDao.listar(false);
        } else {
            listFisio = fisioDao.listar(true, id[0]);
        }
        
        // Clear
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        
        for(Fisioterapeuta h : listFisio) {
            //CI_Roupa rpa = new CI_Roupa(r.getNome(), r);
            model = (DefaultTableModel) tabela.getModel();
            model.addRow(new Object[]{h.getId(), h.getNome(), h.getTel_prim(), h.getEspecialidade()});
        }
    }
    
    /**
     * Busca o registro anterior ao atual no banco de dados
     * @param id ID do fisioterapeuta atual
     * @return Objeto com o fisioterapeuta que precede o do ID passado pelo parâmetro
     */
    public Fisioterapeuta loadPreviosFisio(int id) {
        String sql = "SELECT * FROM medico WHERE id_medico = (SELECT MAX(id_medico) FROM "
                   + "medico WHERE id_medico < " + id + ") LIMIT 1";
        List<Fisioterapeuta> fisio = fisioDao.listarCustom(sql);
        
        if(fisio.size() > 0) {
            return fisio.get(0);
        } else {
            return null;
        }
    }
    
    /**
     * Busca o próximo registro ao atual no banco de dados
     * @param id ID do fisioterapeuta atual
     * @return Objeto com o fisioterapeuta que procede o do ID passado pelo parâmetro
     */
    public Fisioterapeuta loadNextFisio(int id) {
        String sql = "SELECT * FROM medico WHERE id_medico = (SELECT MIN(id_medico) FROM "
                   + "medico WHERE id_medico > " + id + ") LIMIT 1";
        List<Fisioterapeuta> fisio = fisioDao.listarCustom(sql);
        
        if(fisio.size() > 0) {
            return fisio.get(0);
        } else {
            return null;
        }
    }
    /**
     * Busca se ja possui Cpf Cadastrado     
     * @param Cpf
     * @return 
     * @throws java.sql.SQLException
     */
    public boolean search(String Cpf) throws SQLException {
        if(fisioDao.BuscarCpf(Cpf)) {
            JOptionPane.showMessageDialog(null, "Cpf já Cadastrado!");
            return true;
        } else {
            return false;
        }
    }
}
