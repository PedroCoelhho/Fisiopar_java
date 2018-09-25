package control;

import dao.AlunoDao;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Aluno;

/**
 * Classe de Controle de Alunos
 * @author Zampiroli
 */
public class AlunoC {
    /** Variável global para acessar métodos da classe */
    public static final AlunoC CONTROL = new AlunoC();
    
    // Instância do DAO
    AlunoDao alunoDao = new AlunoDao();
    
    /**
     * Método para salvar no banco de dados
     * @param aluno Recebe objeto do aluno
     * @return True ou false para saber se salvou com sucesso
     */
    public boolean create(Aluno aluno) {
        if(alunoDao.salvar(aluno)) {
            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Método para retornar lista de usuários direto da tabela
     * @param like True caso deseja usar a cláusula WHERE e LIKE para buscar
     * @param param Parametro em String para o LIKE: 'LIKE %param%'
     * @return Lista com os aluno retornados da tabela
     */
    
    public List<Aluno> read(boolean like, String param) {
        String sql;
        List<Aluno> alunos;
        
        if(like) {
            sql = "SELECT * FROM aluno WHERE nome LIKE '%" + param + "%'";
            alunos = alunoDao.listarCustom(sql);
        } else {
            sql = "SELECT * FROM aluno";
            alunos = alunoDao.listarCustom(sql);
        }
        
        return alunos;
    }
    
    
    /**
     * Método para alterar o aluno
     * @param aluno Objeto do aluno que será alterado
     */
    public void update(Aluno aluno) {
        if(alunoDao.alterar(aluno)) {
            JOptionPane.showMessageDialog(null, "Aluno alterado com sucesso!");
            
        }
    }
    
    /**
     * Deleta aluno da tabela pelo ID
     * @param id ID do aluno que será deletado
     */
    public void delete(int id) {
        if(alunoDao.deletar(id)) {
            JOptionPane.showMessageDialog(null, "Aluno deletado com sucesso!");
        }
    }
    
    /**
     * Lista alunos para uma tabela JTable
     * @param tabela Tabela JTable que armazenará os dados
     * @param where True se você deseja buscar por ID
     * @param id Caso o where seja true, ele buscará pela ID aqui passada
     */
    public void listTable(JTable tabela, boolean where, int...id) {
        // Atualizar Tabela
        List<Aluno> listAluno;
        
        if(where == false) {
            listAluno = alunoDao.listar(false);
        } else {
            listAluno = alunoDao.listar(true, id[0]);
        }
        
        // Clear
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        
        for(Aluno h : listAluno) {
            //CI_Roupa rpa = new CI_Roupa(r.getNome(), r);
            model = (DefaultTableModel) tabela.getModel();
            model.addRow(new Object[]{h.getId(), h.getNome(), h.getData_nasc(), h.getSexo()});
        }
    }
    
    /**
     * Busca o registro anterior ao atual no banco de dados
     * @param id ID do aluno atual
     * @return Objeto com o aluno que precede o do ID passado pelo parâmetro
     */
    public Aluno loadPreviosAluno(int id) {
        String sql = "SELECT * FROM aluno WHERE id_aluno = (SELECT MAX(id_aluno) FROM "
                   + "aluno WHERE id_aluno < " + id + ") LIMIT 1";
        List<Aluno> aluno = alunoDao.listarCustom(sql);
        
        if(aluno.size() > 0) {
            return aluno.get(0);
        } else {
            return null;
        }
    }
    
    /**
     * Busca o próximo registro ao atual no banco de dados
     * @param id ID do aluno atual
     * @return Objeto com o aluno que procede o do ID passado pelo parâmetro
     */
    public Aluno loadNextAluno(int id) {
        String sql = "SELECT * FROM aluno WHERE id_aluno = (SELECT MIN(id_aluno) FROM "
                   + "aluno WHERE id_aluno > " + id + ") LIMIT 1";
        List<Aluno> aluno = alunoDao.listarCustom(sql);
        
        if(aluno.size() > 0) {
            return aluno.get(0);
        } else {
            return null;
        }
    }
    /**
     * Busca se ja possui Cpf Cadastrado     
     */
    public boolean search(String Cpf) throws SQLException {
        if(alunoDao.BuscarCpf(Cpf)) {
            JOptionPane.showMessageDialog(null, "Cpf já Cadastrado!");
            return true;
        } else {
            return false;
        }
    }
}
