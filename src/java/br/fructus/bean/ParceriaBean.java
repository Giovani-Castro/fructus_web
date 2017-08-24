
package br.fructus.bean;

import br.fructus.dao.GlebaTerraDao;
import br.fructus.dao.ParceriaDao;
import br.fructus.dao.ProdutorDao;
import br.fructus.entity.Gleba_Terra;
import br.fructus.entity.Parceria;
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
public class ParceriaBean implements Serializable {

    private Parceria parceria;
    private List<Parceria> parcerias;
    private Gleba_Terra glebaterra;
    private List<Gleba_Terra> glebaterras;
    private Produtor produtor;
    private List<Produtor> produtores;
    
    
    public ParceriaBean() {
    }

    public Parceria getParceria() {
        return parceria;
    }

    public void setParceria(Parceria parceria) {
        this.parceria = parceria;
    }

    public List<Parceria> getParcerias() {
        return parcerias;
    }

    public void setParcerias(List<Parceria> parcerias) {
        this.parcerias = parcerias;
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
            ParceriaDao parceriadao = new ParceriaDao();
            parcerias = parceriadao.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao Listar Parcerias");
            erro.printStackTrace();
        }

    }

    public void novo() {
        try {
            parceria = new Parceria();
            GlebaTerraDao glebaterradao = new GlebaTerraDao();
            glebaterras = glebaterradao.listar("nome");
            ProdutorDao produtordao = new ProdutorDao();
            produtores = produtordao.listar("nome");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar gerar uma parceria");
            erro.printStackTrace();
        }
    }
    
    public void salvar(){
        try{
            ParceriaDao parceriadao = new ParceriaDao();
            parceriadao.merge(parceria);
            parceria = new Parceria();
            ProdutorDao produtordao = new ProdutorDao();
            produtores = produtordao.listar();
            GlebaTerraDao glebaterradao = new GlebaTerraDao();
            glebaterras = glebaterradao.listar();
            parcerias = parceriadao.listar();
            Messages.addFlashGlobalInfo("Parceria salva com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar uma nova Parceria");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento){
        try{
            parceria = (Parceria) evento.getComponent().getAttributes().get("parceriaselecionada");
            ParceriaDao parceriadao = new ParceriaDao();
            parceriadao.excluir(parceria);
            parcerias = parceriadao.listar();
            Messages.addFlashGlobalInfo("Parceria removida com sucesso");
            
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao excluir uma Parceria");
            erro.printStackTrace();
    }
    }
    public void editar(ActionEvent evento){
         try {
            parceria = (Parceria) evento.getComponent().getAttributes().get("parceriaselecionada");
            ProdutorDao produtordao = new ProdutorDao();
            produtordao.listar();
            GlebaTerraDao glebaterradao = new GlebaTerraDao();
            glebaterradao.listar();
            } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar uma Parceria");
            erro.printStackTrace();
        }
    }
    
   
}
