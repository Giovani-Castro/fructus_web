
package br.fructus.bean;

import br.fructus.dao.CidadeDao;
import br.fructus.dao.EstadoDao;
import br.fructus.dao.ProdutorDao;
import br.fructus.entity.Cidade;
import br.fructus.entity.Estado;
import br.fructus.entity.Produtor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;


@Named
@SessionScoped
public class ProdutorBean implements Serializable {

    private Produtor produtor;
    private List<Produtor> produtores;
    private List<Cidade> cidades;
    private Estado estado;
    private List<Estado> estados;
    
    
    public ProdutorBean() {
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

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
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
    
    
    
    @PostConstruct
    public void listar() {
        try {
            ProdutorDao produtordao = new ProdutorDao();
            produtores = produtordao.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao Listar Produtor");
            erro.printStackTrace();
        }

    }

    public void novo() {
        try {
            produtor = new Produtor();
            EstadoDao estadodao = new EstadoDao();
            estados = estadodao.listar("descricao");
            cidades = new ArrayList<Cidade>();
            estado = new Estado();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar gerar um produtor");
            erro.printStackTrace();
        }
    }
    
    public void salvar(){
        try{
            ProdutorDao produtordao = new ProdutorDao();
            String cpfFormatado = produtor.getCpf().replaceAll("\\D","");
            produtor.setCpf(cpfFormatado);
            produtordao.merge(produtor);
            produtor = new Produtor();
            EstadoDao estadodao = new EstadoDao();
            estados = estadodao.listar("descricao");
            produtores = produtordao.listar();
            cidades = new ArrayList<>();
            estado = new Estado();
           Messages.addFlashGlobalInfo("Produtor salvo com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar um Produtor");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento){
        try{
            produtor = (Produtor) evento.getComponent().getAttributes().get("produtorselecionado");
            ProdutorDao produtordao = new ProdutorDao();
            produtordao.excluir(produtor);
            produtores = produtordao.listar();
            Messages.addFlashGlobalInfo("Produtor removido com sucesso");
            
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao excluir Produtor");
            erro.printStackTrace();
    }
    }
    public void editar(ActionEvent evento){
         try {
            produtor = (Produtor) evento.getComponent().getAttributes().get("produtorselecionado");
            estado = produtor.getCidade().getEstado();
            EstadoDao estadodao = new EstadoDao();
            estados = estadodao.listar("descricao");
            CidadeDao cidadedao = new CidadeDao();
            cidades = cidadedao.buscarPorEstado(estado.getCodigo());
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar um Produtor");
            erro.printStackTrace();
        }
    }
    
    public void popular(){
       try{
        if (estado != null){
           CidadeDao cidadedao = new CidadeDao();
           cidades = cidadedao.buscarPorEstado(estado.getCodigo());
        }else {
            cidades = new ArrayList<>();
        }
    }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar uma cidade");
            erro.printStackTrace();
        }
    }
}
