/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fructus.bean;

import br.fructus.dao.GraosDao;
import br.fructus.dao.UniMedDao;
import br.fructus.entity.Graos;
import br.fructus.entity.UniMed;
import br.fructus.util.JSFUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;


/**
 *
 * @author Giovani
 */
@Named
@SessionScoped
public class GraosBean implements Serializable {

    private Graos grao = new Graos();
    private GraosDao graodao = new GraosDao();
    private List<Graos> listagrao;
    private List<UniMed> unida;
    private Graos gr;

    public GraosBean() {
    }

    public void adicionarGraos() {
        try {
            graodao.merge(grao);
            grao = new Graos();
            listagrao = graodao.getMostraGrao();
            //grao.setDescrição("");
           // grao.setUnidade(null);

        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionarMensagemErro(e.getMessage());
        }
    }
    @PostConstruct
    public void listarGrao() {
        try{
        listagrao = graodao.getMostraGrao();
        
    }catch(RuntimeException erro){
            Messages.addFlashGlobalError("Ocorreu um erro ao listar Grãos");
            erro.printStackTrace();
    }
    }
    
    //listar no selectOneMenu
    
    public void listarUnida(){
        try{
        grao = new Graos();    
        UniMedDao unimeddao = new UniMedDao();
        unida =unimeddao.getList();
    }
    catch (RuntimeException erro){
        Messages.addFlashGlobalError("Ocorreu um erro ao listar Unidade");
        erro.printStackTrace();
}
}
public void exxcluirGrao(ActionEvent eventograo) {
        try {
            grao = (Graos) eventograo.getComponent().getAttributes().get("graoselecionado");
            graodao.removeGrao(grao);
            listagrao = graodao.getMostraGrao();
        } catch (RuntimeException erro) {
            erro.printStackTrace();
        }
    }
public void editar(ActionEvent evento){
    try {
            grao = (Graos) evento.getComponent().getAttributes().get("graoeditado");
            UniMedDao unimeddao = new UniMedDao();
            unida = unimeddao.getList();
            
        } catch (RuntimeException erro) {
            erro.printStackTrace();
        }
    }

    public Graos getGrao() {
        return grao;
    }

    public void setGrao(Graos grao) {
        this.grao = grao;
    }

    public GraosDao getGraodao() {
        return graodao;
    }

    public void setGraodao(GraosDao graodao) {
        this.graodao = graodao;
    }

    public List<Graos> getListagrao() {
        return listagrao;
    }

    public void setListagrao(List<Graos> listagrao) {
        this.listagrao = listagrao;
    }

    public List<UniMed> getUnida() {
        return unida;
    }

    public void setUnida(List<UniMed> unida) {
        this.unida = unida;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.grao);
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
        final GraosBean other = (GraosBean) obj;
        if (!Objects.equals(this.grao, other.grao)) {
            return false;
        }
        return true;
    }

}
