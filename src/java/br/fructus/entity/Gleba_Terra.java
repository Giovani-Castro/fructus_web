
package br.fructus.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
@Entity
public class Gleba_Terra extends GenericDomain{
    
    @Column(nullable = false, length = 50)
    private String nome;
    
    @Column(length = 14)
    private String cod_incra;
    
    @Column(length =11)
    private String cod_receita;
    
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal area;
    
    @Column(nullable = false)
    private Boolean ativo;
    
    @Column (nullable = false)
    private Character condicao;
    
    
    @Temporal(TemporalType.DATE)
    private Date data_aquis;
    
    
    @Temporal(TemporalType.DATE)
    private Date data_venda;
    
    @ManyToOne()
    @JoinColumn(nullable = false)
    private Filial filial;
    
    @ManyToOne()
    @JoinColumn(nullable = false)
    private Produtor responsavel;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCod_incra() {
        return cod_incra;
    }

    public void setCod_incra(String cod_incra) {
        this.cod_incra = cod_incra;
    }

    public String getCod_receita() {
        return cod_receita;
    }

    public void setCod_receita(String cod_receita) {
        this.cod_receita = cod_receita;
    }

   

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Character getCondicao() {
        return condicao;
    }
    
    @Transient
    public String getCondicaoFormatado(){
        String condicaoFormatado = null;
        if (condicao == 'A'){
            condicaoFormatado = "Arrendado";
        }else if (condicao =='P'){
            condicaoFormatado = "Próprio";
        }
        return condicaoFormatado;
    }

    public void setCondicao(Character condicao) {
        this.condicao = condicao;
    }

    public Date getData_aquis() {
        return data_aquis;
    }

    public void setData_aquis(Date data_aquis) {
        this.data_aquis = data_aquis;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public Produtor getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Produtor responsavel) {
        this.responsavel = responsavel;
    }
    
    
}
