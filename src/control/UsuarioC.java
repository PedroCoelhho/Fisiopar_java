package control;

import dao.UsuarioDao;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.swing.JOptionPane;
import model.Usuario;
import util.Yagami;

/**
 * Classe de Controle do Usuário
 * @author Juan Galvão
 */
public class UsuarioC {
    /** Variável global para acessar métodos da classe */
    public static final UsuarioC CONTROL = new UsuarioC();
    
    // Instância do DAO
    UsuarioDao userDao = new UsuarioDao();
    
    /**
     * Método para salvar no banco de dados
     * @param usuario Recebe objeto do usuário
     * @return True ou false para saber se salvou com sucesso
     */
    public boolean create(Usuario usuario) {
        if(userDao.salvar(usuario)) {
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Método para procurar um usuário no banco de acordo com o login e senha
     * Usado para teste de login
     * @param user String com o valor do Usuário
     * @param pass String com a senha criptografada
     * @return Objeto do usuario, ou nulo caso não ache nada
     * @throws NoSuchAlgorithmException 
     */
    public Usuario readLogin(String user, String pass) throws NoSuchAlgorithmException {
        String md5Pass = Yagami.md5(pass);
        md5Pass = Yagami.md5(md5Pass);
        List<Usuario> lUsuario = userDao.listarLogin(user, md5Pass);
        Usuario usuario;
        if(lUsuario.size() > 0) {
            usuario = lUsuario.get(0);
        } else {
            usuario = null;
        }
        return usuario;
    }
    
    /**
     * Busca e verifica um usuário na tabela de acordo com a coluna usuario
     * @param user String com o valor do usuario
     * @return Objeto do usuário ou nulo caso não encontre nada
     */
    public Usuario readVerify(String user) {
        List<Usuario> lUsuario = userDao.listarCadastro(user);
        Usuario usuario;
        if(lUsuario.size() > 0) {
            usuario = lUsuario.get(0);
        } else {
            usuario = null;
        }
        return usuario;
    }
    
    /**
     * Método para alterar usuário
     * @param usuario Objeto contendo o usuário que será alterado
     */
    public void update(Usuario usuario) {
        if(userDao.alterar(usuario)) {
            JOptionPane.showMessageDialog(null, "Paciente alterado com sucesso!");
            
        }
    }
    
    /**
     * Deleta usuário pela id
     * @param id Id do usuário que será deletado
     */
    public void delete(int id) {
        if(userDao.deletar(id)) {
            JOptionPane.showMessageDialog(null, "Paciente deletado com sucesso!");
        }
    }
    
   
}
