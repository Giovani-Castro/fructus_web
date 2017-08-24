
package br.fructus.bean;

import br.fructus.dao.FilialDao;
import br.fructus.dao.GlebaTerraDao;
import br.fructus.dao.ProdutorDao;
import br.fructus.entity.Filial;
import br.fructus.entity.Gleba_Terra;
import br.fructus.entity.Produtor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;


@Named
@SessionScoped
public class GlebaTerraBean implements Serializable {

    private Gleba_Terra glebaterra;
    private List<Gleba_Terra> glebaterras;
    private Filial filial;
    private List<Filial> filiais;
    private Produtor produtor;
    private List<Produtor>produtores;
    
    public GlebaTerraBean() {
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

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public List<Filial> getFiliais() {
        return filiais;
    }

    public void setFiliais(List<Filial> filiais) {
        this.filiais = filiais;
    }

    public Produtor getProdutor() {
        return produtor;
    }

    public void setProdutor(Produtor produtor) {
        this.produtor = produtor;
    }

    public List<Produtor> getProdutores() {
        return produtores;
    }

    public void setProdutores(List<Produtor> produtores) {
        this.produtores = produtores;
    }
    
     @PostConstruct
    public void listar() {
        try {
            GlebaTerraDao glebaterradao = new GlebaTerraDao();
            glebaterras = glebaterradao.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao Listar Glebas de Terra");
            erro.printStackTrace();
        }

    }

    public void novo() {
        try {
            glebaterra = new Gleba_Terra();
            FilialDao filialdao = new FilialDao();
            filiais = filialdao.listar("nome");
            ProdutorDao produtordao = new ProdutorDao();
            produtores = produtordao.listar("nome");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar gerar uma gleba de terra");
            erro.printStackTrace();
        }
    }
    
    public void salvar(){
        try{
            GlebaTerraDao glebaterradao = new GlebaTerraDao();
            glebaterradao.merge(glebaterra);
            glebaterra = new Gleba_Terra();
            ProdutorDao produtordao = new ProdutorDao();
            produtores = produtordao.listar();
            FilialDao filialdao = new FilialDao();
            filiais = filialdao.listar();
            glebaterras = glebaterradao.listar();
            Messages.addFlashGlobalInfo("Gleba de terra salva com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar uma nova gleba de terra");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento){
        try{
            glebaterra = (Gleba_Terra) evento.getComponent().getAttributes().get("glebaselecionada");
            GlebaTerraDao glebaterradao = new GlebaTerraDao();
            glebaterradao.excluir(glebaterra);
            glebaterras = glebaterradao.listar();
            Messages.addFlashGlobalInfo("Gleba removida com sucesso");
            
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao excluir uma Gleba de terra");
            erro.printStackTrace();
    }
    }
    public void editar(ActionEvent evento){
         try {
            glebaterra = (Gleba_Terra) evento.getComponent().getAttributes().get("glebaselecionada");
            ProdutorDao produtordao = new ProdutorDao();
            produtordao.listar();
            FilialDao filialdao = new FilialDao();
            filialdao.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar uma Gleba");
            erro.printStackTrace();
        }
    }
    
   
}
