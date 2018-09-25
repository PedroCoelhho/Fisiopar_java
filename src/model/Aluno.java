package model;

import java.sql.Date;

/**
 * Classe Model do Paciente
 * @author Juan Galvão
 */
public class Aluno {
    private int id;
    private String nome;
    private Date data_nasc;
    private String sexo;
    private String cpf;
    private String rg;
    private String est_civ;
    private String etnia;
    private String nome_resp;
    private String nome_mae;
    private String tel_prim;
    private String tel_sec;
    private String email;
    private int fk_endereco;   
    private String ra;
    private String curso;

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }
    private String coordenador;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEst_civ() {
        return est_civ;
    }

    public void setEst_civ(String est_civ) {
        this.est_civ = est_civ;
    }

    public String getEtnia() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    public String getNome_resp() {
        return nome_resp;
    }

    public void setNome_resp(String nome_resp) {
        this.nome_resp = nome_resp;
    }

    public String getNome_mae() {
        return nome_mae;
    }

    public void setNome_mae(String nome_mae) {
        this.nome_mae = nome_mae;
    }

    public String getTel_prim() {
        return tel_prim;
    }

    public void setTel_prim(String tel_prim) {
        this.tel_prim = tel_prim;
    }

    public String getTel_sec() {
        return tel_sec;
    }

    public void setTel_sec(String tel_sec) {
        this.tel_sec = tel_sec;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFk_endereco() {
        return fk_endereco;
    }

    public void setFk_endereco(int fk_endereco) {
        this.fk_endereco = fk_endereco;
    }

 

    public Aluno() { }

    public Aluno(int id, String nome, Date data_nasc, String sexo, String cpf, String rg, String est_civ, String etnia, String nome_resp, String nome_mae, String tel_prim, String tel_sec, String email, int fk_endereco, String convenio, String cns, Date valid_cart, String data_hr, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.sexo = sexo;
        this.cpf = cpf;
        this.rg = rg;
        this.est_civ = est_civ;
        this.etnia = etnia;
        this.nome_resp = nome_resp;
        this.nome_mae = nome_mae;
        this.tel_prim = tel_prim;
        this.tel_sec = tel_sec;
        this.email = email;
        this.fk_endereco = fk_endereco;
        this.ra = ra;
        this.curso = curso;
        this.coordenador = coordenador;
    
    
    }
}
