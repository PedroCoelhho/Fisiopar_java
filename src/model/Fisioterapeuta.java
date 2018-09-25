/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author UNOPAR
 */
public class Fisioterapeuta {
    private int id;
    private String nome;
    private String cpf;
    private String rg;
    private String tel_prim;
    private String tel_sec;
    private String email;
    private String especialidade;
    private int fk_endereco;

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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public int getFk_endereco() {
        return fk_endereco;
    }

    public void setFk_endereco(int fk_endereco) {
        this.fk_endereco = fk_endereco;
    }
    
     public Fisioterapeuta() { }

    public Fisioterapeuta(int id, String nome, String cpf, String rg, String tel_prim, String tel_sec, String email, String especialidade, int fk_endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.tel_prim = tel_prim;
        this.tel_sec = tel_sec;
        this.email = email;
        this.especialidade = especialidade;
        this.fk_endereco = fk_endereco;
    }
}
