
package br.fructus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ClassePro extends GenericDomain {
    
    @Column(nullable = false , length = 70 )
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
