
package br.fructus.bean;

import br.fructus.dao.EstadoDao;
import br.fructus.entity.Estado;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;
import org.omnifaces.util.Messages;
import org.omnifaces.util.Messages.Message;

@Named
@SessionScoped
public class EstadoBeans implements Serializable {
    private Estado estado;
    private List<Estado> estados;
    
    public void listar(){
        try{
          EstadoDao estadodao = new EstadoDao();
          estados = estadodao.listar();
        }catch(RuntimeException erro){
          Messages.addFlashGlobalError("Ocorreu um erro ao listar o estado");
          erro.printStackTrace();
        }
    }
    public void novo (){
    estado = new Estado();
}
    public void salvar(){
        try{
        EstadoDao estadodao = new EstadoDao();
        estadodao.merge(estado);
        
        
    }catch(RuntimeException erro){
        
    }
    }
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
        
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
    
}
