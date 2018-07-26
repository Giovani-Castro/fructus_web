
package br.fructus.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "municipio")
public class Municipio implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(nullable = false)
    private  int codmunicipio;
    
    @Column (nullable = false, length = 60)
    private String nomemunicipio;
    
    @Column (nullable = false, length = 60)
    private String nomeuf;

    public int getCodmunicipio() {
        return codmunicipio;
    }

    public void setCodmunicipio(int codmunicipio) {
        this.codmunicipio = codmunicipio;
    }

    public String getNomemunicipio() {
        return nomemunicipio;
    }

    public void setNomemunicipio(String nomemunicipio) {
        this.nomemunicipio = nomemunicipio;
    }

    public String getNomeuf() {
        return nomeuf;
    }

    public void setNomeuf(String nomeuf) {
        this.nomeuf = nomeuf;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.codmunicipio;
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
        final Municipio other = (Municipio) obj;
        if (this.codmunicipio != other.codmunicipio) {
            return false;
        }
        return true;
    }
    
    
    
}
