
package br.fructus.bean;

import br.fructus.dao.CidadeDao;
import br.fructus.dao.DepositoDao;
import br.fructus.dao.EstadoDao;
import br.fructus.entity.Cidade;
import br.fructus.entity.Deposito;
import br.fructus.entity.Estado;
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
public class DepositoBean implements Serializable {

    private Deposito deposito;
    private List<Deposito> depositos;
    private Estado estado;
    private List<Estado> estados;
    private List<Cidade> cidades;
    
    
    
    public DepositoBean() {
        
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public List<Deposito> getDepositos() {
        return depositos;
    }

    public void setDepositos(List<Deposito> depositos) {
        this.depositos = depositos;
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

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
    
     @PostConstruct
    public void listar() {
        try {
            DepositoDao depositodao = new DepositoDao();
            depositos = depositodao.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao Listar filiais");
            erro.printStackTrace();
        }

    }

    public void novo() {
        try {
            deposito = new Deposito();
            EstadoDao estadodao = new EstadoDao();
            estados = estadodao.listar("descricao");
            cidades = new ArrayList<Cidade>();
            estado = new Estado();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar gerar uma filial");
            erro.printStackTrace();
        }
    }
    
    public void salvar(){
        try{
            DepositoDao depositodao = new DepositoDao();
            depositodao.merge(deposito);
            deposito = new Deposito();
            EstadoDao estadodao = new EstadoDao();
            estados = estadodao.listar("descricao");
            depositos = depositodao.listar();
            cidades = new ArrayList<>();
            estado = new Estado();
           Messages.addFlashGlobalInfo("Depósito salvo com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar um novo depósito");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento){
        try{
            deposito = (Deposito) evento.getComponent().getAttributes().get("depositoselecionado");
            DepositoDao depositodao = new DepositoDao();
            depositodao.excluir(deposito);
            depositos = depositodao.listar();
            Messages.addFlashGlobalInfo("Deposito removido com sucesso");
            
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao excluir um depósito");
            erro.printStackTrace();
    }
    }
    public void editar(ActionEvent evento){
         try {
            deposito = (Deposito) evento.getComponent().getAttributes().get("depositoselecionado");
            estado = deposito.getCidade().getEstado();
            EstadoDao estadodao = new EstadoDao();
            estados = estadodao.listar("descricao");
            CidadeDao cidadedao = new CidadeDao();
            cidades = cidadedao.buscarPorEstado(estado.getCodigo());
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar um estado");
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
