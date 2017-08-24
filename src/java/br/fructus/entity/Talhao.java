
package br.fructus.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Talhao extends GenericDomain {
    @Column(nullable = false, length = 45)
    private String descricao;
    
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal hectares;
    
    @ManyToOne()
    @JoinColumn(nullable = false)
    private Gleba_Terra gleba;
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getHectares() {
        return hectares;
    }

    public void setHectares(BigDecimal hectares) {
        this.hectares = hectares;
    }

    public Gleba_Terra getGleba() {
        return gleba;
    }

    public void setGleba(Gleba_Terra gleba) {
        this.gleba = gleba;
    }
    
    
}
