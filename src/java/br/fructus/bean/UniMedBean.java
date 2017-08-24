package br.fructus.bean;

import br.fructus.dao.UniMedDao;
import br.fructus.entity.UniMed;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;


@Named
@SessionScoped
public class UniMedBean implements Serializable {

    private UniMed unimed = new UniMed();
    private UniMedDao unimeddao = new UniMedDao();
    private List<UniMed> listaUniMed;

    public UniMedBean() {
    }

    public String adicionarUniMed() {
        unimeddao.salveUniMed(unimed);
        //unimed.setUnidade("");
       // unimed.setDescricao("");
        return "index";
    }

    public List listarUniMed() {
        listaUniMed = unimeddao.getList();
        return this.listaUniMed;
    }

    public UniMed getUnimed() {
        return unimed;
    }

    public void setUnimed(UniMed unimed) {
        this.unimed = unimed;
    }

    public UniMedDao getUnimeddao() {
        return unimeddao;
    }

    public void setUnimeddao(UniMedDao unimeddao) {
        this.unimeddao = unimeddao;
    }

    public List<UniMed> getListaUniMed() {
        return listaUniMed;
    }

    public void setListaUniMed(List<UniMed> listaUniMed) {
        this.listaUniMed = listaUniMed;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.unimed);
        hash = 47 * hash + Objects.hashCode(this.listaUniMed);
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
        final UniMedBean other = (UniMedBean) obj;
        if (!Objects.equals(this.unimed, other.unimed)) {
            return false;
        }
        if (!Objects.equals(this.listaUniMed, other.listaUniMed)) {
            return false;
        }
        return true;
    }
    
    
}
