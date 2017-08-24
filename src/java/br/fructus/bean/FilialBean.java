
package br.fructus.bean;

import br.fructus.dao.CidadeDao;
import br.fructus.dao.EstadoDao;
import br.fructus.dao.FazendaDao;
import br.fructus.dao.FilialDao;
import br.fructus.entity.Cidade;
import br.fructus.entity.Estado;
import br.fructus.entity.Fazenda;
import br.fructus.entity.Filial;
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
public class FilialBean implements Serializable {
private List<Cidade> cidades;
private List<Estado> estados;
private Filial filial;
private List<Filial> filiais;
private Estado estado;
private Fazenda fazenda;
private List<Fazenda> fazendas;

    public FilialBean() {
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
    
    
    
     @PostConstruct
    public void listar() {
        try {
            FilialDao filialdao = new FilialDao();
            filiais = filialdao.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao Listar filiais");
            erro.printStackTrace();
        }

    }

    public void novo() {
        try {
            filial = new Filial();
            EstadoDao estadodao = new EstadoDao();
            estados = estadodao.listar("descricao");
            FazendaDao fazendadao = new FazendaDao();
            fazendas = fazendadao.listar();
            cidades = new ArrayList<Cidade>();
            estado = new Estado();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar gerar uma filial");
            erro.printStackTrace();
        }
    }
    
    public void salvar(){
        try{
            FilialDao filialdao = new FilialDao();
            filialdao.merge(filial);
            filial = new Filial();
            EstadoDao estadodao = new EstadoDao();
            estados = estadodao.listar("descricao");
            FazendaDao fazendadao = new FazendaDao();
            fazendadao.listar();
            filiais = filialdao.listar();
            cidades = new ArrayList<>();
            estado = new Estado();
           Messages.addFlashGlobalInfo("Filial salva com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar uma nova filial");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento){
        try{
            filial = (Filial) evento.getComponent().getAttributes().get("filialselecionada");
            FilialDao filialdao = new FilialDao();
            filialdao.excluir(filial);
            filiais = filialdao.listar();
            Messages.addFlashGlobalInfo("Filial removida com sucesso");
            
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao excluir Filial");
            erro.printStackTrace();
    }
    }
    public void editar(ActionEvent evento){
         try {
            filial = (Filial) evento.getComponent().getAttributes().get("filialselecionada");
            estado = filial.getCidade().getEstado();
            EstadoDao estadodao = new EstadoDao();
            estados = estadodao.listar("descricao");
            FazendaDao fazendadao = new FazendaDao();
            fazendadao.listar();
            CidadeDao cidadedao = new CidadeDao();
            cidades = cidadedao.buscarPorEstado(estado.getCodigo());
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar uma filial");
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

