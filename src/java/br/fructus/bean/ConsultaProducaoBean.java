

package br.fructus.bean;

import br.fructus.dao.ColheitaDao;
import br.fructus.dao.DepositoDao;
import br.fructus.dao.GraosDao;
import br.fructus.dao.SafraDao;
import br.fructus.dao.SafraTalhaoDao;
import br.fructus.dao.TalhaoDao;
import br.fructus.entity.Colheita;
import br.fructus.entity.Deposito;
import br.fructus.entity.Graos;
import br.fructus.entity.Safra;
import br.fructus.entity.SafraTalhao;
import br.fructus.entity.Talhao;
import br.fructus.entity.Variedade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;


@Named
@SessionScoped
public class ConsultaProducaoBean implements Serializable {

    private Colheita colheita;
    private Safra safra;
    private Talhao talhao;
    private List<Colheita> colheitas;
    private SafraTalhao safratalhao;
    private List<SafraTalhao> safratalhoes;
    private List<Safra> safras;
    private Deposito deposito;
    private List<Deposito> depositos;
    private Graos grao;
    private List<Graos> graos;
    private List<Talhao> talhoes;
    
    public ConsultaProducaoBean() {
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

    public List<Safra> getSafras() {
        return safras;
    }

    public void setSafras(List<Safra> safras) {
        this.safras = safras;
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

    public List<Talhao> getTalhoes() {
        return talhoes;
    }

    public void setTalhoes(List<Talhao> talhoes) {
        this.talhoes = talhoes;
    }

    public List<Colheita> getColheitas() {
        return colheitas;
    }

    public void setColheitas(List<Colheita> colheitas) {
        this.colheitas = colheitas;
    }

    public Safra getSafra() {
        return safra;
    }

    public void setSafra(Safra safra) {
        this.safra = safra;
    }

    public Talhao getTalhao() {
        return talhao;
    }

    public void setTalhao(Talhao talhao) {
        this.talhao = talhao;
    }

    public List<SafraTalhao> getSafratalhoes() {
        return safratalhoes;
    }

    public void setSafratalhoes(List<SafraTalhao> safratalhoes) {
        this.safratalhoes = safratalhoes;
    }
    
    
    
    
    
    @PostConstruct
     public void novo() {
        try {
            
            colheita = new Colheita();
            colheita.setPesob(new BigDecimal("0.00"));
            colheita.setPesoliq(new BigDecimal("0.00"));
            colheita.setSafra(new SafraTalhao());
            colheita.getSafra().setSafra(new Safra());
            colheita.getSafra().setTalhao(new Talhao());
            colheita.getSafra().setVariedade(new Variedade());
            colheita.getSafra().getVariedade().setIdGraos(new Graos());
            colheita.setDeposito(new Deposito());
            safra = new Safra();
            talhao = new Talhao();
            deposito = new Deposito();
            grao = new Graos();
            
           
            GraosDao graodao = new GraosDao();
            graos = graodao.getMostraGrao();
            DepositoDao depositodao = new DepositoDao();
            depositos = depositodao.listar();
            SafraDao safradao = new SafraDao();
            safras = safradao.listar();
            TalhaoDao talhaodao = new TalhaoDao();
            talhoes = talhaodao.listar();
            safratalhoes = new ArrayList<>();
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
           /* grao = new Graos();*/
            Messages.addFlashGlobalInfo("registro salvo com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar um registro");
            erro.printStackTrace();
        }
        
    }
    
    public void excluir(ActionEvent evento){
        try{ 
            colheita = (Colheita) evento.getComponent().getAttributes().get("colheitaselecionada");
            ColheitaDao colheitadao = new ColheitaDao();
            colheitadao.excluir(colheita);
            
            filtrar();
            Messages.addFlashGlobalInfo("registro removido com sucesso");
            
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao excluir um registro");
            erro.printStackTrace();
    }
    }
    public void editar(ActionEvent evento){
         try {
            colheita = (Colheita) evento.getComponent().getAttributes().get("colheitaselecionada");
            grao = colheita.getSafra().getVariedade().getIdGraos();
            GraosDao graosdao = new GraosDao();
            SafraTalhaoDao safratalhaodao = new SafraTalhaoDao();
            safratalhoes = safratalhaodao.buscarPorGrao(grao.getIdGraos());
            DepositoDao depositodao = new DepositoDao();
            depositos = depositodao.listar();
            
            
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar um registro");
            erro.printStackTrace();
        }
    }
    public void filtrar() {
        try {
            ColheitaDao colheitadao = new ColheitaDao();
            List<Colheita> resultado = colheitadao.pesquisarC(colheita.getSafra().getVariedade().getIdGraos().getIdGraos(),colheita.getSafra().getSafra().getSafra(),colheita.getSafra().getTalhao().getDescricao(),colheita.getDeposito().getNome());
            
            if (resultado.isEmpty()) {
            colheitas.clear();
                Messages.addFlashGlobalInfo("Não existem dados para listar");
            } else {
                
           colheitas = resultado;
           calcular();
            
            }
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao pesquisar a colheita");
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
    
   public void calcular(){
       colheita.setPesob(new BigDecimal("0.00"));
       colheita.setPesoliq(new BigDecimal("0.00"));
       for(int posicao = 0; posicao < colheitas.size(); posicao++){
          Colheita colh =  colheitas.get(posicao);
          colheita.setPesob( colheita.getPesob().add( colh.getPesob()));
          colheita.setPesoliq(colheita.getPesoliq().add(colh.getPesoliq()));
       }
   }
}


