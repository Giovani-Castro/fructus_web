
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
public class Colheita extends GenericDomain {
    
    @Temporal (TemporalType.DATE)
    private Date data;
    
    @Column (nullable = false, length = 7)
    private String placa;
    
    @Column (nullable = false, precision = 8, scale = 2)
    private BigDecimal pesob;
    
    @Column (nullable = false, precision = 8, scale = 2)
    private BigDecimal tara;
    
    @Column (precision = 8, scale = 2)
    private BigDecimal impureza;
    
    @Column (precision = 8, scale = 2)
    private BigDecimal umidade;
    
    @Column (precision = 8, scale = 2)
    private BigDecimal outdesc;
    
    @Column ( nullable = false, precision = 8, scale = 2)
    private BigDecimal pesoliq;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private SafraTalhao safra;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Deposito deposito;
    
    @Column( length = 10)
    private String documento;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public BigDecimal getPesob() {
        return pesob;
    }

    public void setPesob(BigDecimal pesob) {
        this.pesob = pesob;
    }

    public BigDecimal getTara() {
        return tara;
    }

    public void setTara(BigDecimal tara) {
        this.tara = tara;
    }

    public BigDecimal getImpureza() {
        return impureza;
    }

    public void setImpureza(BigDecimal impureza) {
        this.impureza = impureza;
    }

    public BigDecimal getUmidade() {
        return umidade;
    }

    public void setUmidade(BigDecimal umidade) {
        this.umidade = umidade;
    }

    public BigDecimal getOutdesc() {
        return outdesc;
    }

    public void setOutdesc(BigDecimal outdesc) {
        this.outdesc = outdesc;
    }

    public BigDecimal getPesoliq() {
        return pesoliq;
    }

    public void setPesoliq(BigDecimal pesoliq) {
        this.pesoliq = pesoliq;
    }

    public SafraTalhao getSafra() {
        return safra;
    }

    public void setSafra(SafraTalhao safra) {
        this.safra = safra;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Colheita{" + "data=" + data + ", placa=" + placa + ", pesob=" + pesob + ", tara=" + tara + ", impureza=" + impureza + ", umidade=" + umidade + ", outdesc=" + outdesc + ", pesoliq=" + pesoliq + ", safra=" + safra + ", deposito=" + deposito + ", documento=" + documento + '}';
    }
    
    
}
