
package br.fructus.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pais")
public class Pais implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(nullable = false)
    private  int codpais;
    
    @Column(length = 80, nullable = false)
    private String nomepais;
    
   

    public int getCodpais() {
        return codpais;
    }

    public void setCodpais(int codpais) {
        this.codpais = codpais;
    }

    public String getNomepais() {
        return nomepais;
    }

    public void setNomepais(String nomepais) {
        this.nomepais = nomepais;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.codpais;
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
        final Pais other = (Pais) obj;
        if (this.codpais != other.codpais) {
            return false;
        }
        return true;
    }

   

      
    
}
