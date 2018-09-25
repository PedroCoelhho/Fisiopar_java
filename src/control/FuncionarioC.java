package control;

import dao.FuncionarioDao;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Funcionario;

/**
 * Classe de Controle de funcionarios
 * @author Zampiroli
 */
public class FuncionarioC {
    /** Variável global para acessar métodos da classe */
    public static final FuncionarioC CONTROL = new FuncionarioC();
    
    // Instância do DAO
    FuncionarioDao funcDao = new FuncionarioDao();
    
    /**
     * Método para salvar no banco de dados
     * @param funcionario Recebe objeto do funcionarios
     * @return True ou false para saber se salvou com sucesso
     */
    public boolean create(Funcionario funcionario) {
        if(funcDao.salvar(funcionario)) {
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Método para retornar lista de usuários direto da tabela
     * @param like True caso deseja usar a cláusula WHERE e LIKE para buscar
     * @param param Parametro em String para o LIKE: 'LIKE %param%'
     * @return Lista com os funcionario retornados da tabela
     */
    
    public List<Funcionario> read(boolean like, String param) {
        String sql;
        List<Funcionario> fisio;
        
        if(like) {
            sql = "SELECT * FROM funcionario WHERE nome LIKE '%" + param + "%'";
            fisio = funcDao.listarCustom(sql);
        } else {
            sql = "SELECT * FROM funcionario";
            fisio = funcDao.listarCustom(sql);
        }
        
        return fisio;
    }
    
    
    /**
     * Método para alterar o funcionario
     * @param funcionario Objeto do funcionario que será alterado
     */
    public void update(Funcionario funcionario) {
        if(funcDao.alterar(funcionario)) {
            JOptionPane.showMessageDialog(null, "Funcionário alterado com sucesso!");
            
        }
    }
    
    /**
     * Deleta funcionario da tabela pelo ID
     * @param id ID do funcionario que será deletado
     */
    public void delete(int id) {
        if(funcDao.deletar(id)) {
            JOptionPane.showMessageDialog(null, "Funcionário deletado com sucesso!");
        }
    }
    
    /**
     * Lista funcionarios para uma tabela JTable
     * @param tabela Tabela JTable que armazenará os dados
     * @param where True se você deseja buscar por ID
     * @param id Caso o where seja true, ele buscará pela ID aqui passada
     */
    public void listTable(JTable tabela, boolean where, int...id) {
        // Atualizar Tabela
        List<Funcionario> listFunc;
        
        if(where == false) {
            listFunc = funcDao.listar(false);
        } else {
            listFunc = funcDao.listar(true, id[0]);
        }
        
        // Clear
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        
        for(Funcionario h : listFunc) {
            //CI_Roupa rpa = new CI_Roupa(r.getNome(), r);
            model = (DefaultTableModel) tabela.getModel();
            model.addRow(new Object[]{h.getId(), h.getNome(), h.getTel_prim(), h.getCargo()});
        }
    }
    
    /**
     * Busca o registro anterior ao atual no banco de dados
     * @param id ID do funcionario atual
     * @return Objeto com o funcionario que precede o do ID passado pelo parâmetro
     */
    public Funcionario loadPreviosFunc(int id) {
        String sql = "SELECT * FROM funcionario WHERE id_funcionario = (SELECT MAX(id_funcionario) FROM "
                   + "funcionario WHERE id_funcionario < " + id + ") LIMIT 1";
        List<Funcionario> func = funcDao.listarCustom(sql);
        
        if(func.size() > 0) {
            return func.get(0);
        } else {
            return null;
        }
    }
    
    /**
     * Busca o próximo registro ao atual no banco de dados
     * @param id ID do funcionario atual
     * @return Objeto com o funcionario que procede o do ID passado pelo parâmetro
     */
    public Funcionario loadNextFunc(int id) {
        String sql = "SELECT * FROM funcionario WHERE id_funcionario = (SELECT MIN(id_funcionario) FROM "
                   + "funcionario WHERE id_funcionario > " + id + ") LIMIT 1";
        List<Funcionario> func = funcDao.listarCustom(sql);
        
        if(func.size() > 0) {
            return func.get(0);
        } else {
            return null;
        }
    }
    /**
     * Busca se ja possui Cpf Cadastrado     
     */
    public boolean search(String Cpf) throws SQLException {
        if(funcDao.BuscarCpf(Cpf)) {
            JOptionPane.showMessageDialog(null, "Cpf já Cadastrado!");
            return true;
        } else {
            return false;
        }
    }
}
