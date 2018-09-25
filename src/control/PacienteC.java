package control;

import dao.PacienteDao;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Paciente;

/**
 * Classe de Controle de Paciente
 * @author Juan Galvão
 */
public class PacienteC {
    /** Variável global para acessar métodos da classe */
    public static final PacienteC CONTROL = new PacienteC();
    
    // Instância do DAO
    PacienteDao pacDao = new PacienteDao();
    
    /**
     * Método para salvar no banco de dados
     * @param paciente Recebe objeto do paciente
     * @return True ou false para saber se salvou com sucesso
     */
    public boolean create(Paciente paciente) {
        if(pacDao.salvar(paciente)) {
            JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Método para retornar lista de usuários direto da tabela
     * @param like True caso deseja usar a cláusula WHERE e LIKE para buscar
     * @param param Parametro em String para o LIKE: 'LIKE %param%'
     * @return Lista com os pacientes retornados da tabela
     */
    
    public List<Paciente> read(boolean like, String param) {
        String sql;
        List<Paciente> pacientes;
        
        if(like) {
            sql = "SELECT * FROM paciente WHERE nome LIKE '%" + param + "%'";
            pacientes = pacDao.listarCustom(sql);
        } else {
            sql = "SELECT * FROM paciente";
            pacientes = pacDao.listarCustom(sql);
        }
        
        return pacientes;
    }
    
    
    /**
     * Método para alterar o paciente
     * @param paciente Objeto do paciente que será alterado
     */
    public void update(Paciente paciente) {
        if(pacDao.alterar(paciente)) {
            JOptionPane.showMessageDialog(null, "Paciente alterado com sucesso!");
            
        }
    }
    
    /**
     * Deleta paciente da tabela pelo ID
     * @param id ID do paciente que será deletado
     */
    public void delete(int id) {
        if(pacDao.deletar(id)) {
            JOptionPane.showMessageDialog(null, "Paciente deletado com sucesso!");
        }
    }
    
    /**
     * Lista pacientes para uma tabela JTable
     * @param tabela Tabela JTable que armazenará os dados
     * @param where True se você deseja buscar por ID
     * @param id Caso o where seja true, ele buscará pela ID aqui passada
     */
    public void listTable(JTable tabela, boolean where, int...id) {
        // Atualizar Tabela
        List<Paciente> listPaciente;
        
        if(where == false) {
            listPaciente = pacDao.listar(false);
        } else {
            listPaciente = pacDao.listar(true, id[0]);
        }
        
        // Clear
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        
        for(Paciente h : listPaciente) {
            //CI_Roupa rpa = new CI_Roupa(r.getNome(), r);
            model = (DefaultTableModel) tabela.getModel();
            model.addRow(new Object[]{h.getId(), h.getNome(), h.getData_nasc(), h.getSexo()});
        }
    }
    
    /**
     * Busca o registro anterior ao atual no banco de dados
     * @param id ID do paciente atual
     * @return Objeto com o paciente que precede o do ID passado pelo parâmetro
     */
    public Paciente loadPreviosPaciente(int id) {
        String sql = "SELECT * FROM paciente WHERE id = (SELECT MAX(id) FROM "
                   + "paciente WHERE id < " + id + ") LIMIT 1";
        List<Paciente> pacientes = pacDao.listarCustom(sql);
        
        if(pacientes.size() > 0) {
            return pacientes.get(0);
        } else {
            return null;
        }
    }
    
    /**
     * Busca o próximo registro ao atual no banco de dados
     * @param id ID do paciente atual
     * @return Objeto com o paciente que procede o do ID passado pelo parâmetro
     */
    public Paciente loadNextPaciente(int id) {
        String sql = "SELECT * FROM paciente WHERE id = (SELECT MIN(id) FROM "
                   + "paciente WHERE id > " + id + ") LIMIT 1";
        List<Paciente> pacientes = pacDao.listarCustom(sql);
        
        if(pacientes.size() > 0) {
            return pacientes.get(0);
        } else {
            return null;
        }
    }
    
     /**
     * Busca se ja possui Cpf Cadastrado     
     */
    public boolean search(String Cpf) throws SQLException {
        if(pacDao.BuscarCpf(Cpf)) {
            JOptionPane.showMessageDialog(null, "Cpf já Cadastrado!");
            return true;
        } else {
            return false;
        }
    }
}
