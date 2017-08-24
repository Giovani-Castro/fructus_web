package br.fructus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Filial extends GenericDomain {
    
    @Column(nullable = false, length = 50)
    private String nome;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Fazenda fazenda;
    
    @Column(length = 70)
    private String bairro;
    
    @Column(length = 70)
    private String logradouro;
    
    @Column(length = 50)
    private String complemento;
    
  
    private Long número;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Cidade cidade;
    
    @Column(length = 10)
    private String Cep;
    
    @Column(length = 14)
    private String telefone;
    
    @Column(length = 15)
    private String celular;
    
    @Column(length = 90)
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Long getNúmero() {
        return número;
    }

    public void setNúmero(Long número) {
        this.número = número;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String Cep) {
        this.Cep = Cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
    
}
