
package br.fructus.bean;

import br.fructus.dao.GraosDao;
import br.fructus.dao.VariedadeDao;
import br.fructus.entity.Graos;
import br.fructus.entity.Variedade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;


@Named
@SessionScoped
public class VariedadeBean implements Serializable {

   private Variedade variedade;
   private List<Variedade> variedades;
   private Graos grao;
   private List<Graos> graos;
   
    public VariedadeBean() {
    }

    public Variedade getVariedade() {
        return variedade;
    }

    public void setVariedade(Variedade variedade) {
        this.variedade = variedade;
    }

    public List<Variedade> getVariedades() {
        return variedades;
    }

    public void setVariedades(List<Variedade> variedades) {
        this.variedades = variedades;
    }

    public Graos getGrao() {
        return grao;
    }

    public void setGrao(Graos grao) {
        this.grao = grao;
    }

    public List<Graos> getGraos() {
        return graos;
    }

    public void setGraos(List<Graos> graos) {
        this.graos = graos;
    }
    
    @PostConstruct
    public void listar() {
        try {
            VariedadeDao variedadedao = new VariedadeDao();
            variedades = variedadedao.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao Listar Variedades");
            erro.printStackTrace();
        }

    }

    public void novo() {
        try {
            variedade = new Variedade();
            GraosDao graosdao = new GraosDao();
            graos = graosdao.getMostraGrao();
            
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar gerar uma Variedade");
            erro.printStackTrace();
        }
    }
    
    public void salvar(){
        try{
           VariedadeDao variedadedao = new VariedadeDao();
            variedadedao.merge(variedade);
            variedade = new Variedade();
            GraosDao graosdao = new GraosDao();
            graos = graosdao.getMostraGrao();
            variedades = variedadedao.listar();
            Messages.addFlashGlobalInfo("Variedade salva com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar uma Variedade");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento){
        try{
            variedade = (Variedade) evento.getComponent().getAttributes().get("variedadeselecionada");
            VariedadeDao variedadedao = new VariedadeDao();
            variedadedao.excluir(variedade);
            variedades = variedadedao.listar("descricao");
            Messages.addFlashGlobalInfo("Variedade removida com sucesso");
            
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao excluir uma Variedade");
            erro.printStackTrace();
    }
    }
    public void editar(ActionEvent evento){
         try {
            variedade = (Variedade) evento.getComponent().getAttributes().get("variedadeselecionada");
            GraosDao graosdao = new GraosDao();
            graosdao.getMostraGrao();
            
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar uma Variedade");
            erro.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.variedade);
        hash = 23 * hash + Objects.hashCode(this.variedades);
        hash = 23 * hash + Objects.hashCode(this.grao);
        hash = 23 * hash + Objects.hashCode(this.graos);
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
        final VariedadeBean other = (VariedadeBean) obj;
        if (!Objects.equals(this.variedade, other.variedade)) {
            return false;
        }
        if (!Objects.equals(this.variedades, other.variedades)) {
            return false;
        }
        if (!Objects.equals(this.grao, other.grao)) {
            return false;
        }
        if (!Objects.equals(this.graos, other.graos)) {
            return false;
        }
        return true;
    }
    
    
}
