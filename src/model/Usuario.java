package model;

/**
 * Classe Model do Usuário
 * @author Juan Galvão
 */
public class Usuario {
    private int id;
    private String usuario, nome, senha;
    private int nivelAcesso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
    

    public Usuario() { }

    public Usuario(int id, String usuario, String nome, String senha, int nivelAcesso) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    } 
}
