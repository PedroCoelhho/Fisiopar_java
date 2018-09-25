package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import engine.MysqlConn;
import model.Usuario;
import util.Yagami;

/**
 * Data Access Object para a tabela e classe Usuario
 * @author Juan Galvão
 */
public class UsuarioDao {
    //public static HoraDao hd = new HoraDao();
    
    // Métodos Implementados
    public List<Usuario> listar(boolean where, int... id) {
        String sql;
        if (where == false) {
            sql = "SELECT * FROM usuario";
        }  else {
            sql = "SELECT * FROM usuario WHERE id = " + id[0];
        }

        List<Usuario> usuarios = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = MysqlConn.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                Usuario usuario = new Usuario();

                //Recupera o id do banco e atribui ele ao objeto
                usuario.setId(rset.getInt("id"));
                usuario.setNome(rset.getString("nome"));
                usuario.setUsuario(rset.getString("usuario"));
                usuario.setSenha(rset.getString("senha"));
                usuario.setNivelAcesso(rset.getInt("nivelAcesso"));
                
                //Adiciono o contato recuperado, a lista de contatos
                usuarios.add(usuario);
            }
            
        } catch (Exception e) {
            Yagami.mensagemErro(e);
            
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                Yagami.mensagemErro(e);
            }
        }

        return usuarios;
    }

    public boolean salvar(Usuario usuario) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "INSERT INTO usuario\n" +
        "(nome, usuario, senha, nivelAcesso)\n" +
        "VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Converte a senha para MD5
            String md5Senha = Yagami.md5(usuario.getSenha());
            
            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1,  usuario.getNome());
            pstm.setString(2,  usuario.getUsuario());
            pstm.setString(3,  md5Senha);
            pstm.setInt   (4,  usuario.getNivelAcesso());
            

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            Yagami.mensagemErro(e);
            retorno = false;
        
        } finally {
            //Fecha as conexões
            try{
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }

            } catch(SQLException e){
                Yagami.mensagemErro(e);
                retorno = false;
            }
        }
        return retorno;
    }

    
    public boolean alterar(Usuario usuario) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "UPDATE usuario\n" +
        "SET nome = ?, usuario = ?, senha = ?, nivelAcesso = ?\n" +
        "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            
            // Converte a senha para MD5
            String md5Senha = Yagami.md5(usuario.getSenha());

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1,  usuario.getNome());
            pstm.setString(2,  usuario.getUsuario());
            pstm.setString(3,  md5Senha);
            pstm.setInt   (4,  usuario.getNivelAcesso());
            pstm.setInt   (5,  usuario.getId());

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            //e.printStackTrace();
            Yagami.mensagemErro(e);
            retorno = false;
        
        } finally {
            //Fecha as conexões
            try{
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }

            } catch(SQLException e){
                Yagami.mensagemErro(e);
                retorno = false;
            }
        }
        return retorno;
    }

    public boolean deletar(int id) {
        boolean retorno = true;
        /*
        * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
        * na base de dados
        */
        String sql = "DELETE FROM usuario "
                   + "WHERE id = " + id;

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            Yagami.mensagemErro(e);
            retorno = false;
        
        } finally {
            //Fecha as conexões
            try{
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }

            } catch(SQLException e){
                Yagami.mensagemErro(e);
                retorno = false;
            }
        }
        return retorno;
    }
    
    // CUSTOM
    public List<Usuario> listarLogin(String userP, String senhaP) {
        String sql = String.format(
                "SELECT * " +
                "FROM usuario " +
                "WHERE usuario = '%s' " +
                "AND senha = '%s'",
                userP, senhaP
        );

        List<Usuario> usuarios = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = MysqlConn.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                Usuario usuario = new Usuario();

                //Recupera o id do banco e atribui ele ao objeto
                usuario.setId(rset.getInt("id"));
                usuario.setNome(rset.getString("nome"));
                usuario.setUsuario(rset.getString("usuario"));
                usuario.setSenha(rset.getString("senha"));
                usuario.setNivelAcesso(rset.getInt("nivelAcesso"));
                //Adiciono o contato recuperado, a lista de contatos
                usuarios.add(usuario);
            }
            
        } catch (Exception e) {
            Yagami.mensagemErro(e);
            
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                Yagami.mensagemErro(e);
            }
        }

        return usuarios;
    }
    
    public List<Usuario> listarCadastro(String userP) {
        String sql = "SELECT * FROM usuario WHERE usuario = '" + userP + "'";

        List<Usuario> usuarios = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = MysqlConn.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                Usuario usuario = new Usuario();

                //Recupera o id do banco e atribui ele ao objeto
                usuario.setId(rset.getInt("id"));
                usuario.setNome(rset.getString("nome"));
                usuario.setUsuario(rset.getString("usuario"));
                usuario.setSenha(rset.getString("senha"));
                usuario.setNivelAcesso(rset.getInt("nivelAcesso"));
                
                //Adiciono o contato recuperado, a lista de contatos
                usuarios.add(usuario);
            }
            
        } catch (Exception e) {
            Yagami.mensagemErro(e);
            
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                Yagami.mensagemErro(e);
            }
        }

        return usuarios;
    }

}