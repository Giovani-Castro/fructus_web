
package br.fructus.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SafraTalhao extends GenericDomain {
    
    @ManyToOne
    @JoinColumn (nullable = false)
    private Safra safra;
    
    @ManyToOne
    @JoinColumn (nullable = false)
    private Talhao talhao;
    
    @ManyToOne
    @JoinColumn (nullable = false)
    private Variedade variedade;

    public Safra getSafra() {
        return safra;
    }

    public void setSafra(Safra safra) {
        this.safra = safra;
    }

    public Talhao getTalhao() {
        return talhao;
    }

    public void setTalhao(Talhao talhao) {
        this.talhao = talhao;
    }

    public Variedade getVariedade() {
        return variedade;
    }

    public void setVariedade(Variedade variedade) {
        this.variedade = variedade;
    }

    @Override
    public String toString() {
        return "SafraTalhao{" + "safra=" + safra + ", talhao=" + talhao + ", variedade=" + variedade + '}';
    }
    
    
}
