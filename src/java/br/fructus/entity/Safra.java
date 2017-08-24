package br.fructus.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Safra extends GenericDomain {

    @Column(nullable = false, length = 30)
    private String safra;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date Ano_inicio;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date Ano_fim;

    public String getSafra() {
        return safra;
    }

    public void setSafra(String safra) {
        this.safra = safra;
    }

    public Date getAno_inicio() {
        return Ano_inicio;
    }

    public void setAno_inicio(Date Ano_inicio) {
        this.Ano_inicio = Ano_inicio;
    }

    public Date getAno_fim() {
        return Ano_fim;
    }

    public void setAno_fim(Date Ano_fim) {
        this.Ano_fim = Ano_fim;
    }
    
    

}
