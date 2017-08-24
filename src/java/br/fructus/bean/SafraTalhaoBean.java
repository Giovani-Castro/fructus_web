
package br.fructus.bean;

import br.fructus.dao.GraosDao;
import br.fructus.dao.SafraDao;
import br.fructus.dao.SafraTalhaoDao;
import br.fructus.dao.TalhaoDao;
import br.fructus.dao.VariedadeDao;
import br.fructus.entity.Graos;
import br.fructus.entity.Safra;
import br.fructus.entity.SafraTalhao;
import br.fructus.entity.Talhao;
import br.fructus.entity.Variedade;
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
public class SafraTalhaoBean implements Serializable {

    private SafraTalhao safratalhao;
    private List<SafraTalhao> safratalhoes;
    private List<Graos> graos;
    private Graos grao;
    private List<Variedade> variedades;
    private List<Safra> safras;
    private List<Talhao> talhoes;
    
    
    public SafraTalhaoBean() {
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

    public List<Graos> getGraos() {
        return graos;
    }

    public void setGraos(List<Graos> graos) {
        this.graos = graos;
    }

    public Graos getGrao() {
        return grao;
    }

    public void setGrao(Graos grao) {
        this.grao = grao;
    }

    public List<Variedade> getVariedades() {
        return variedades;
    }

    public void setVariedades(List<Variedade> variedades) {
        this.variedades = variedades;
    }

    public List<Safra> getSafras() {
        return safras;
    }

    public void setSafras(List<Safra> safras) {
        this.safras = safras;
    }

    public List<Talhao> getTalhoes() {
        return talhoes;
    }

    public void setTalhoes(List<Talhao> talhoes) {
        this.talhoes = talhoes;
    }
    
    
    
     @PostConstruct
    public void listar() {
        try {
            SafraTalhaoDao safratalhaodao = new SafraTalhaoDao();
            safratalhoes = safratalhaodao.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao Listar as safras e talhões");
            erro.printStackTrace();
        }

    }

    public void novo() {
        try {
            safratalhao = new SafraTalhao();
            GraosDao graodao = new GraosDao();
            graos = graodao.getMostraGrao();
            TalhaoDao talhaodao = new TalhaoDao();
            talhoes = talhaodao.listar("descricao");
            SafraDao safradao = new SafraDao();
            safras = safradao.listar();
            variedades = new ArrayList<Variedade>();
            grao = new Graos();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar gerar uma Safra/talhao");
            erro.printStackTrace();
        }
    }
    
    public void salvar(){
        try{
            SafraTalhaoDao safratalhaodao = new SafraTalhaoDao();
            safratalhaodao.merge(safratalhao);
            safratalhao = new SafraTalhao();
            GraosDao graosdao = new GraosDao();
            graos = graosdao.getMostraGrao();
            TalhaoDao talhaodao = new TalhaoDao();
            talhaodao.listar();
            safratalhoes = safratalhaodao.listar();
            variedades = new ArrayList<>();
            grao = new Graos();
           Messages.addFlashGlobalInfo("Safra/Talhao salvo com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar uma nova Safra/Talhao");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento){
        try{
            safratalhao = (SafraTalhao) evento.getComponent().getAttributes().get("safratalhaoselecionado");
            SafraTalhaoDao safratalhaodao = new SafraTalhaoDao();
            safratalhaodao.excluir(safratalhao);
            safratalhoes = safratalhaodao.listar();
            Messages.addFlashGlobalInfo("Safra/Talhao removido com sucesso");
            
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao excluir Safra/Talhao");
            erro.printStackTrace();
    }
    }
    public void editar(ActionEvent evento){
         try {
            safratalhao = (SafraTalhao) evento.getComponent().getAttributes().get("safratalhaoselecionado");
            grao = safratalhao.getVariedade().getIdGraos();
            GraosDao graosdao = new GraosDao();
            graos = graosdao.getMostraGrao();
            TalhaoDao talhaodao = new TalhaoDao();
            talhaodao.listar();
            VariedadeDao variedadedao = new VariedadeDao();
            variedades = variedadedao.buscarPorGrao(grao.getIdGraos());
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar uma Safra/Talhao");
            erro.printStackTrace();
        }
    }
    
    public void popular(){
       try{
        if (grao != null){
           VariedadeDao variedadedao = new VariedadeDao();
           variedades = variedadedao.buscarPorGrao(grao.getIdGraos());
        }else {
            variedades = new ArrayList<>();
        }
    }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar uma variedade");
            erro.printStackTrace();
        }
    }
}
