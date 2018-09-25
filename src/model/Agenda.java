package model;
import java.sql.Date;
import java.sql.Time;

/**
 * Classe de Modelo para Agenda
 * @author Juan
 */
public class Agenda {
    private int id_agenda;
    private Date data_ag;
    private Time hora_ag;
    private String tipo;
    private int fk_atendimento;
    private int fk_consulta;
    private String detalhes;

    public int getId_agenda() {
        return id_agenda;
    }

    public void setId_agenda(int id_agenda) {
        this.id_agenda = id_agenda;
    }

    public Date getData_ag() {
        return data_ag;
    }

    public void setData_ag(Date data_ag) {
        this.data_ag = data_ag;
    }

    public Time getHora_ag() {
        return hora_ag;
    }

    public void setHora_ag(Time hora_ag) {
        this.hora_ag = hora_ag;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getFk_atendimento() {
        return fk_atendimento;
    }

    public void setFk_atendimento(int fk_atendimento) {
        this.fk_atendimento = fk_atendimento;
    }

    public int getFk_consulta() {
        return fk_consulta;
    }

    public void setFk_consulta(int fk_consulta) {
        this.fk_consulta = fk_consulta;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public Agenda() { }

    public Agenda(int id_agenda, Date data_ag, Time hora_ag, String tipo, int fk_atendimento, int fk_consulta, String detalhes) {
        this.id_agenda = id_agenda;
        this.data_ag = data_ag;
        this.hora_ag = hora_ag;
        this.tipo = tipo;
        this.fk_atendimento = fk_atendimento;
        this.fk_consulta = fk_consulta;
        this.detalhes = detalhes;
    }
    
    
    
}
