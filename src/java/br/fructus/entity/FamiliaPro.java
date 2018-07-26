
package br.fructus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class FamiliaPro extends GenericDomain {
   
    @Column (nullable = false, length = 70)
    private String nome;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private ClassePro classePro;
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ClassePro getClassePro() {
        return classePro;
    }

    public void setClassePro(ClassePro classePro) {
        this.classePro = classePro;
    }
    
    
}
