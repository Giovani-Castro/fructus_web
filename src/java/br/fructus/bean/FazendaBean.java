
package br.fructus.bean;


import br.fructus.dao.FazendaDao;
import br.fructus.entity.Fazenda;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;


@Named(value = "fazendaBean")
@SessionScoped
public class FazendaBean implements Serializable {

   private Fazenda fazenda;
   private List<Fazenda> fazendas;
   
    public FazendaBean() {
    }

    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }

    public List<Fazenda> getFazendas() {
        return fazendas;
    }

    public void setFazendas(List<Fazenda> fazendas) {
        this.fazendas = fazendas;
    }
    
    public void novo(){
        fazenda = new Fazenda();
    }
    
    @PostConstruct
    public void listar(){
        try{
          FazendaDao fazendadao = new FazendaDao();
          fazendas = fazendadao.listar();
        }catch(RuntimeException erro){
          Messages.addFlashGlobalError("Ocorreu um erro ao listar a fazenda");
          erro.printStackTrace();
        }
    }
    
    public void salvar(){
        try{
        FazendaDao fazendadao = new FazendaDao();
        fazendadao.merge(fazenda);
        novo();
        fazendas = fazendadao.listar();
        Messages.addGlobalInfo("Fazenda salva com sucesso");
    }catch(RuntimeException erro){
       Messages.addGlobalInfo("Ocorreu um erro ao tentar salvar fazenda");
       erro.printStackTrace();
    }
        
    }
    
    public void excluir(ActionEvent evento){
        try{
        fazenda = (Fazenda) evento.getComponent().getAttributes().get("fazendaselecionada");
        FazendaDao fazendadao = new FazendaDao();
        fazendadao.excluir(fazenda);
        fazendas = fazendadao.listar();
        Messages.addGlobalInfo("Fazenda Excluida com Sucesso");
        }catch(RuntimeException erro){
         Messages.addGlobalInfo("Erro ao excluir Fazenda");
         erro.printStackTrace();
                }
                
                
    }
    
    public void editar(ActionEvent evento){
        fazenda = (Fazenda) evento.getComponent().getAttributes().get("fazendaselecionada");
        
    }
}
