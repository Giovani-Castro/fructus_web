/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fructus.bean;

import br.fructus.dao.UniMedDao;
import br.fructus.entity.UniMed;
import br.fructus.util.JSFUtil;
import com.mysql.jdbc.Messages;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Giovani
 */
@Named
@SessionScoped
public class NovoUniMedBean implements Serializable {

    UniMed unimed = new UniMed();
    private UniMedDao unimeddao = new UniMedDao();
    private List<UniMed> listaUniMed;
    private UniMed unidade;
    public NovoUniMedBean() {
    }

    public void adicionarUniMed() {
        try {
            unimeddao.salveUniMed(unimed);
            unimed.setUnidade("");
            unimed.setDescricao("");
            
            listaUniMed = unimeddao.getList();
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionarMensagemErro(e.getMessage());
        }
    }

    public String removerUniMed(UniMed u) {
        this.unimed = u;
        unimeddao.removeUniMed(this.unimed);
        this.unimed.setUnidade(null);
        this.unimed.setDescricao(null);
        return "index";
    }

    public void excluir(ActionEvent evento) {
        try {
            unimed = (UniMed) evento.getComponent().getAttributes().get("unidadeselecionada");
           // UniMedDao unimeddao = new UniMedDao();
            unimeddao.excluir(unimed);
            listaUniMed = unimeddao.getList();
        } catch (RuntimeException erro) {
            erro.printStackTrace();
        }
    }
    //captura a linha para edição
    public void editar(ActionEvent evento){
        unidade = (UniMed) evento.getComponent().getAttributes().get("unidadeselecionada");
       
        org.omnifaces.util.Messages.addGlobalInfo("unidade " + unidade.getUnidade() + "decrição: " + unidade.getDescricao() );
    }
    
    public void editarUni(){
        try{
        unimeddao.editarUnidade(unidade);
        unidade = new UniMed(); 
        listaUniMed = unimeddao.getList();
            org.omnifaces.util.Messages.addGlobalInfo("Registro alterado com sucesso");
        }catch(RuntimeException erro){
            org.omnifaces.util.Messages.addGlobalError("Ocorreu um erro ao tentar editar o registro");
            erro.printStackTrace();
        }
    }
@PostConstruct
    public void listarUniMed() {
        
        listaUniMed = unimeddao.getList();
        
    }

    public UniMed getUnimed() {
        return unimed;
    }

    public void setUnimed(UniMed unimed) {
        this.unimed = unimed;
    }

    public List<UniMed> getListaUniMed() {
        return listaUniMed;
    }

    public void setListaUniMed(List<UniMed> listaUniMed) {
        this.listaUniMed = listaUniMed;
    }

    public UniMedDao getUnimeddao() {
        return unimeddao;
    }

    public void setUnimeddao(UniMedDao unimeddao) {
        this.unimeddao = unimeddao;
    }

    public UniMed getUnidade() {
        return unidade;
    }

    public void setUnidade(UniMed unidade) {
        this.unidade = unidade;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.unimed);
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
        final NovoUniMedBean other = (NovoUniMedBean) obj;
        if (!Objects.equals(this.unimed, other.unimed)) {
            return false;
        }
        return true;
    }

}
