
package br.fructus.bean;

import br.fructus.dao.SafraDao;
import br.fructus.entity.Safra;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;


@Named
@SessionScoped
public class SafraBean implements Serializable {

    private Safra safra;
    private List<Safra> safras;
    
    public SafraBean() {
    }

    public Safra getSafra() {
        return safra;
    }

    public void setSafra(Safra safra) {
        this.safra = safra;
    }

    public List<Safra> getSafras() {
        return safras;
    }

    public void setSafras(List<Safra> safras) {
        this.safras = safras;
    }
    
    
    
    @PostConstruct
    public void listar() {
        try {
            SafraDao safradao = new SafraDao();
            safras = safradao.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao Listar Safras");
            erro.printStackTrace();
        }

    }

    public void novo() {
        try {
            safra = new Safra();
                        
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar gerar uma Safra");
            erro.printStackTrace();
        }
    }
    
    public void salvar(){
        try{
            SafraDao safradao = new SafraDao();
            safradao.merge(safra);
            safra = new Safra();
            safras = safradao.listar();
            Messages.addFlashGlobalInfo("Safra salva com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar uma Safra");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento){
        try{
            safra = (Safra) evento.getComponent().getAttributes().get("safraselecionada");
            SafraDao safradao = new SafraDao();
            safradao.excluir(safra);
            safras = safradao.listar("safra");
            Messages.addFlashGlobalInfo("Safra removida com sucesso");
            
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao excluir uma Safra");
            erro.printStackTrace();
    }
    }
    public void editar(ActionEvent evento){
         try {
            safra = (Safra) evento.getComponent().getAttributes().get("safraselecionada");
            
            
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar uma Safra");
            erro.printStackTrace();
        }
    }
    
}
