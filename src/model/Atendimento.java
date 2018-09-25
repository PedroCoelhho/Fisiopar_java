package model;

import java.sql.Date;
import java.sql.Time;

/**
 * Classe Modelo de Atendimento
 * @author Juan Galvão
 */
public class Atendimento {
    private int id_atendimento;
    private String tipo_atendimento;
    private int fk_medico;
    private String bloco_inicial;
    private String bloco_atual;
    private Time horario;
    private Date data_at;
    private String observacoes;
    private int fk_patendimento;
    private int fk_ubs;

    public int getId_atendimento() {
        return id_atendimento;
    }

    public void setId_atendimento(int id_atendimento) {
        this.id_atendimento = id_atendimento;
    }

    public String getTipo_atendimento() {
        return tipo_atendimento;
    }

    public void setTipo_atendimento(String tipo_atendimento) {
        this.tipo_atendimento = tipo_atendimento;
    }

    public int getFk_medico() {
        return fk_medico;
    }

    public void setFk_medico(int fk_medico) {
        this.fk_medico = fk_medico;
    }

    public String getBloco_inicial() {
        return bloco_inicial;
    }

    public void setBloco_inicial(String bloco_inicial) {
        this.bloco_inicial = bloco_inicial;
    }

    public String getBloco_atual() {
        return bloco_atual;
    }

    public void setBloco_atual(String bloco_atual) {
        this.bloco_atual = bloco_atual;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public Date getData_at() {
        return data_at;
    }

    public void setData_at(Date data_at) {
        this.data_at = data_at;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getFk_patendimento() {
        return fk_patendimento;
    }

    public void setFk_patendimento(int fk_patendimento) {
        this.fk_patendimento = fk_patendimento;
    }

    public int getFk_ubs() {
        return fk_ubs;
    }

    public void setFk_ubs(int fk_ubs) {
        this.fk_ubs = fk_ubs;
    }
    
    
}
