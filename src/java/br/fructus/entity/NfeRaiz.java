
package br.fructus.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NfeRaiz")
public class NfeRaiz {
    @Id
    @Column(nullable = false)
    private String IdNfe;
    
    @Column 
    private String versao;

    public String getIdNfe() {
        return IdNfe;
    }

    public void setIdNfe(String IdNfe) {
        this.IdNfe = IdNfe;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.IdNfe);
        hash = 29 * hash + Objects.hashCode(this.versao);
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
        final NfeRaiz other = (NfeRaiz) obj;
        if (!Objects.equals(this.IdNfe, other.IdNfe)) {
            return false;
        }
        if (!Objects.equals(this.versao, other.versao)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
