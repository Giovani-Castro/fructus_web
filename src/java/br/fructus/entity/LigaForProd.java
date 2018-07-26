
package br.fructus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LigaForProd extends GenericDomain {

@Column (nullable = false)    
private String prodForn;

@Column (nullable = false)
private float indexadorUnid;

@Column (nullable = false)
private String unidadeForn;

@ManyToOne
@JoinColumn(nullable = false)
private CliFor fornecedor;

@ManyToOne
@JoinColumn(nullable = false)
private Produto produto;

    public String getProdForn() {
        return prodForn;
    }

    public void setProdForn(String prodForn) {
        this.prodForn = prodForn;
    }

    public float getIndexadorUnid() {
        return indexadorUnid;
    }

    public void setIndexadorUnid(float indexadorUnid) {
        this.indexadorUnid = indexadorUnid;
    }

    public String getUnidadeForn() {
        return unidadeForn;
    }

    public void setUnidadeForn(String unidadeForn) {
        this.unidadeForn = unidadeForn;
    }

    public CliFor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(CliFor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }


    
}
