
package br.fructus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Fazenda extends GenericDomain{
    @Column (nullable = false, length = 50)
    private String nome;
    
    @Column(length = 50)
    private String nome_fantasia;
    
    @Column(length = 19)
    private String Cnpj;
    
    @Column(length = 14)
    private String Cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String Cnpj_Cpf) {
        this.Cnpj = Cnpj;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }
    
    
    
}
