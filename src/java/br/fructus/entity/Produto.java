
package br.fructus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Produto extends GenericDomain {
    
    @Column(nullable = false, length = 70)
    private String nome;
    
    @Column(length = 8)
    private int NCM;
    
    @Column(length = 14)
    private String EAN;
    
    @Column(length = 6)
    private String NVE;
    
    @Column (length = 3)
    private int EXTIPI;
        
    
    @ManyToOne
    @JoinColumn (nullable = false)
    private UniMed unimed;
    
    @ManyToOne
    @JoinColumn (nullable = false)
    private FamiliaPro familiaPro;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNCM() {
        return NCM;
    }

    public void setNCM(int NCM) {
        this.NCM = NCM;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public String getNVE() {
        return NVE;
    }

    public void setNVE(String NVE) {
        this.NVE = NVE;
    }

    public int getEXTIPI() {
        return EXTIPI;
    }

    public void setEXTIPI(int EXTIPI) {
        this.EXTIPI = EXTIPI;
    }

    public UniMed getUnimed() {
        return unimed;
    }

    public void setUnimed(UniMed unimed) {
        this.unimed = unimed;
    }

    public FamiliaPro getFamiliaPro() {
        return familiaPro;
    }

    public void setFamiliaPro(FamiliaPro familiaPro) {
        this.familiaPro = familiaPro;
    }
    
    
    
}
