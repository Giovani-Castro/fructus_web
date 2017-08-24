package br.fructus.bean;

import br.fructus.dao.CidadeDao;
import br.fructus.dao.EstadoDao;
import br.fructus.entity.Cidade;
import br.fructus.entity.Estado;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

@Named
@SessionScoped
public class CidadeBean implements Serializable {

    private List<Cidade> cidades;
    private Cidade cidade;
    private List<Estado> estados;

    public CidadeBean() {
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    @PostConstruct
    public void listar() {
        try {
            CidadeDao cidadedao = new CidadeDao();
            cidades = cidadedao.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao Listar Cidades");
            erro.printStackTrace();
        }

    }

    public void novo() {
        try {
            cidade = new Cidade();
            EstadoDao estadodao = new EstadoDao();
            estados = estadodao.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar gerar uma nova cidade");
            erro.printStackTrace();
        }
    }
    
    public void salvar(){
        try{
            CidadeDao cidadedao = new CidadeDao();
            cidadedao.merge(cidade);
            cidade = new Cidade();
            EstadoDao estadodao = new EstadoDao();
            estados = estadodao.listar();
            cidades = cidadedao.listar();
            
           Messages.addFlashGlobalInfo("Cidade salva com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar uma nova cidade");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento){
        try{
            cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeselecionada");
            CidadeDao cidadedao = new CidadeDao();
            cidadedao.excluir(cidade);
            cidades = cidadedao.listar();
            Messages.addFlashGlobalInfo("Cidade removida com sucesso");
            
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao excluir cidade");
            erro.printStackTrace();
    }
    }
    public void editar(ActionEvent evento){
         try {
            cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeeditada");
            EstadoDao estadodao = new EstadoDao();
            estados = estadodao.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar uma cidade");
            erro.printStackTrace();
        }
    }
        
        
    
}

