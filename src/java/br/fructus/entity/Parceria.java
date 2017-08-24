
package br.fructus.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Parceria extends GenericDomain{
    @ManyToOne()
    @JoinColumn(nullable = false)
    private Gleba_Terra gleba;
    
    @ManyToOne()
    @JoinColumn(nullable = false)
    private Produtor produtor;
    
    @Temporal(TemporalType.DATE)
    private Date data_inic;
    
    @Temporal(TemporalType.DATE)
    private Date data_final;
    
    
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal part_desp;
    
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal part_receita;
    
    @Column(nullable = false)
    private Boolean ativo;

    public Gleba_Terra getGleba() {
        return gleba;
    }

    public void setGleba(Gleba_Terra gleba) {
        this.gleba = gleba;
    }

    public Produtor getProdutor() {
        return produtor;
    }

    public void setProdutor(Produtor produtor) {
        this.produtor = produtor;
    }

    public Date getData_inic() {
        return data_inic;
    }

    public void setData_inic(Date data_inic) {
        this.data_inic = data_inic;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public BigDecimal getPart_desp() {
        return part_desp;
    }

    public void setPart_desp(BigDecimal part_desp) {
        this.part_desp = part_desp;
    }

    public BigDecimal getPart_receita() {
        return part_receita;
    }

    public void setPart_receita(BigDecimal part_receita) {
        this.part_receita = part_receita;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    
}
