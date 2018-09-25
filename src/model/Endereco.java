package model;

/**
 * Classe Model do Endereço
 * @author Juan Galvão
 */
public class Endereco {
    private int id, fk_cidade, numero;
    private String cep, logradouro, bairro, complemento, cidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getFk_cidade() {
        return fk_cidade;
    }

    public void setFk_cidade(int fk_cidade) {
        this.fk_cidade = fk_cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    

    public Endereco() { }

    public Endereco(int id, String cep, int fk_cidade, String logradouro, int numero, String bairro, String complemento) {
        this.id = id;
        this.cep = cep;
        this.fk_cidade = fk_cidade;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
    }
    
    
}
