
package br.fructus.bean;

import br.fructus.dao.ColheitaDao;
import br.fructus.dao.DepositoDao;
import br.fructus.dao.GraosDao;
import br.fructus.dao.SafraTalhaoDao;
import br.fructus.entity.Colheita;
import br.fructus.entity.Deposito;
import br.fructus.entity.Graos;
import br.fructus.entity.SafraTalhao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.omnifaces.util.Messages;


@Named
@SessionScoped
public class ColheitaBean implements Serializable {

    private Colheita colheita;
    private SafraTalhao safratalhao;
    private List<SafraTalhao> safratalhoes;
    private Deposito deposito;
    private List<Deposito> depositos;
    private Graos grao;
    private List<Graos> graos;
    
    public ColheitaBean() {
    }

    public Colheita getColheita() {
        return colheita;
    }

    public void setColheita(Colheita colheita) {
        this.colheita = colheita;
    }

    public SafraTalhao getSafratalhao() {
        return safratalhao;
    }

    public void setSafratalhao(SafraTalhao safratalhao) {
        this.safratalhao = safratalhao;
    }

    public List<SafraTalhao> getSafratalhoes() {
        return safratalhoes;
    }

    public void setSafratalhoes(List<SafraTalhao> safratalhoes) {
        this.safratalhoes = safratalhoes;
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

    public Graos getGrao() {
        return grao;
    }

    public void setGrao(Graos grao) {
        this.grao = grao;
    }

    public List<Graos> getGraos() {
        return graos;
    }

    public void setGraos(List<Graos> graos) {
        this.graos = graos;
    }
    
     @PostConstruct
     public void novo() {
        try {
            
            colheita = new Colheita();
            GraosDao graodao = new GraosDao();
            graos = graodao.getMostraGrao();
            DepositoDao depositodao = new DepositoDao();
            depositos = depositodao.listar();
            safratalhoes = new ArrayList<SafraTalhao>();
            grao = new Graos();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar listar uma colheita");
            erro.printStackTrace();
        }
    }
    
    public void salvar(){
        try{
            ColheitaDao colheitadao = new ColheitaDao();
            colheitadao.merge(colheita);
            colheita = new Colheita();
            grao = new Graos();
            safratalhoes = new ArrayList<>();
            
                        
           Messages.addFlashGlobalInfo("Colheita salva com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar uma Colheita");
            erro.printStackTrace();
        }
    }
           
    public void popular(){
       try{
        if (grao != null){
           SafraTalhaoDao safratalhaodao = new SafraTalhaoDao();
           safratalhoes = safratalhaodao.buscarPorGrao(grao.getIdGraos());
        }else {
            safratalhoes = new ArrayList<>();
        }
    }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar uma safra");
            erro.printStackTrace();
        }
    }
    
}
