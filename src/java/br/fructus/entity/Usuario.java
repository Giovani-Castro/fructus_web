
package br.fructus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Usuario extends GenericDomain{
    
    @Column (nullable = false, length = 32)
    private String senha;
    
    @Transient
    private String senhasemcriptografia;
    
    @Column (nullable = false, length = 70)
    private String nome;
    
    
    @CPF
    @Column ( nullable = false, length = 14)
    private String cpf;
    
    @Column (nullable = false)
    private Boolean ativo;
    
    @Column (nullable = false)
    private Character tipo;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public String getSenhasemcriptografia() {
        return senhasemcriptografia;
    }

    public void setSenhasemcriptografia(String senhasemcriptografia) {
        this.senhasemcriptografia = senhasemcriptografia;
    }
    
    
    
}
