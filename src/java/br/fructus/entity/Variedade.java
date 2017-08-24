
package br.fructus.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Variedade extends GenericDomain {
    
    @Column(nullable = false, length = 30)
    private String descricao;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Graos IdGraos;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Graos getIdGraos() {
        return IdGraos;
    }

    public void setIdGraos(Graos IdGraos) {
        this.IdGraos = IdGraos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.descricao);
        hash = 23 * hash + Objects.hashCode(this.IdGraos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Variedade other = (Variedade) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.IdGraos, other.IdGraos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Variedade{" + "descricao=" + descricao + ", IdGraos=" + IdGraos + '}';
    }
    
    
    
}
