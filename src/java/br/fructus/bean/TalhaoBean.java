
package br.fructus.bean;

import br.fructus.dao.GlebaTerraDao;
import br.fructus.dao.TalhaoDao;
import br.fructus.entity.Gleba_Terra;
import br.fructus.entity.Talhao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;


@Named
@SessionScoped
public class TalhaoBean implements Serializable {

private Talhao talhao;
private List<Talhao> talhoes;
private Gleba_Terra  glebaterra;
private List<Gleba_Terra> glebaterras;

    public TalhaoBean() {
    }

    public Talhao getTalhao() {
        return talhao;
    }

    public void setTalhao(Talhao talhao) {
        this.talhao = talhao;
    }

    public List<Talhao> getTalhoes() {
        return talhoes;
    }

    public void setTalhoes(List<Talhao> talhoes) {
        this.talhoes = talhoes;
    }

    public Gleba_Terra getGlebaterra() {
        return glebaterra;
    }

    public void setGlebaterra(Gleba_Terra glebaterra) {
        this.glebaterra = glebaterra;
    }

    public List<Gleba_Terra> getGlebaterras() {
        return glebaterras;
    }

    public void setGlebaterras(List<Gleba_Terra> glebaterras) {
        this.glebaterras = glebaterras;
    }
    
     @PostConstruct
    public void listar() {
        try {
            TalhaoDao talhaodao = new TalhaoDao();
            talhoes = talhaodao.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao Listar Talhões");
            erro.printStackTrace();
        }

    }

    public void novo() {
        try {
            talhao = new Talhao();
            GlebaTerraDao glebaterradao = new GlebaTerraDao();
            glebaterras = glebaterradao.listar("nome");
            
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar gerar um Talhão");
            erro.printStackTrace();
        }
    }
    
    public void salvar(){
        try{
            TalhaoDao talhaodao = new TalhaoDao();
            talhaodao.merge(talhao);
            talhao = new Talhao();
            GlebaTerraDao glebaterradao = new GlebaTerraDao();
            glebaterras = glebaterradao.listar();
            talhoes = talhaodao.listar();
            Messages.addFlashGlobalInfo("Talhão salvo com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar um Talhão");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento){
        try{
            talhao = (Talhao) evento.getComponent().getAttributes().get("talhaoselecionado");
            TalhaoDao talhaodao = new TalhaoDao();
            talhaodao.excluir(talhao);
            talhoes = talhaodao.listar("descricao");
            Messages.addFlashGlobalInfo("Talhao removido com sucesso");
            
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao excluir um Talhão");
            erro.printStackTrace();
    }
    }
    public void editar(ActionEvent evento){
         try {
            talhao = (Talhao) evento.getComponent().getAttributes().get("talhaoselecionado");
            GlebaTerraDao glebaterradao = new GlebaTerraDao();
            glebaterradao.listar("nome");
            
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar um Talhão");
            erro.printStackTrace();
        }
    }
}
