
package br.fructus.bean;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.format.Formatter;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.Validator;
import br.fructus.dao.CliForDao;
import br.fructus.dao.MunicipioDao;
import br.fructus.dao.PaisDao;
import br.fructus.entity.CliFor;
import br.fructus.entity.Municipio;
import br.fructus.entity.Pais;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.hibernate.validator.constraints.br.CPF;
import org.omnifaces.util.Messages;


@Named
@SessionScoped
public class CliForBean implements Serializable {

    private List<CliFor> clifor;
    private CliForDao clifordao;
    private CliFor clienteforne;
    private List<Municipio> municipios;
    private List<Pais> paises;
    private MunicipioDao municipiodao; 
    private PaisDao paisdao;
    private Long codigocli;
    private String mascara;
    CNPJValidator validatorCnpj =  new CNPJValidator();
    CPFValidator validatorCpf = new CPFValidator();
    
    public CliForBean() {
    }

    public List<CliFor> getClifor() {
        return clifor;
    }

    public void setClifor(List<CliFor> clifor) {
        this.clifor = clifor;
        
        
    }

    public CliFor getClienteforne() {
        return clienteforne;
    }

    public void setClienteforne(CliFor clienteforne) {
        this.clienteforne = clienteforne;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public Long getCodigocli() {
        return codigocli;
    }

    public void setCodigocli(Long codigocli) {
        this.codigocli = codigocli;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }
    
    
    
    @PostConstruct
    public void inciciar(){
        clifordao = new CliForDao();
        municipiodao = new MunicipioDao();
        paisdao = new PaisDao();
        
    }
    
    public void listar() {
        try {
            
            clifor = clifordao.listar("nome");
            

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao Listar Cliente/Fornecedor");
            erro.printStackTrace();
        }

    }
    
    public void carregarEdicao(){
        try {
            
            municipios = municipiodao.listar("nomemunicipio");
            paises = paisdao.listar("nomepais");
            if (codigocli!=0){
               clienteforne = clifordao.buscar(codigocli);
               System.out.println(codigocli); 
            } else {
                clienteforne = new CliFor();
            }
            
            
              // clienteforne = new CliFor();  
                    
              
            
            

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao Carregar Cliente/Fornecedor");
            erro.printStackTrace();
        }

    }
    
    public void salvar(){
        try{
            
           // Formatter formatter = new CNPJFormatter();
           // String fotmatedValue = clienteforne.getCpfcnpj();
           // String unformatedValue = formatter.unformat(fotmatedValue);
            //clienteforne.setCpfcnpj(unformatedValue);
            
            Character tipoP = clienteforne.getTipopessoa();
            String cnpjFormatado = clienteforne.getCpfcnpj().replaceAll("\\D","");
            String cepFormatado = clienteforne.getCep().replaceAll("\\D","");
            clienteforne.setCpfcnpj(cnpjFormatado);
            clienteforne.setCep(cepFormatado);
            
           if("F".equals(tipoP.toString())){
               
               validatorCpf.assertValid(clienteforne.getCpfcnpj());
               
           }else{
               validatorCnpj.assertValid(clienteforne.getCpfcnpj());
               
           }
           
            
            if (clifordao.cnpj(cnpjFormatado)!=null){
                if (codigocli!=0){
                 clifordao.editar(clienteforne);  
                 clienteforne = new CliFor();
             Messages.addFlashGlobalInfo("Cliente/Fornecedor salvo com sucesso");    
                }else{
                   Messages.addFlashGlobalInfo("Fornecedor já cadastrado"); 
                }
                
                    
                
                                 
           }else{
            
            clifordao.merge(clienteforne);
            clienteforne = new CliFor();
            
               
                     
            
            
           Messages.addFlashGlobalInfo("Cliente/Fornecedor salvo com sucesso");
            }
            
        }catch (InvalidStateException e)    {
            System.out.println(e.getInvalidMessages());
            Messages.addFlashGlobalError("CPF ou CNPJ inválido");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar um Cliente/Fornecedor");
            erro.printStackTrace();
        }
    }
    
    
    public void novo(){
        
        clienteforne = new CliFor();
        codigocli = clienteforne.getCodigo();
        municipios = municipiodao.listar("nomemunicipio");
        paises = paisdao.listar("nomepais");
        
               
        
    }
    
     public void excluir(ActionEvent evento){
        try{ 
            clienteforne = (CliFor) evento.getComponent().getAttributes().get("fornecedorselecionado");
            clifordao.excluir(clienteforne);
            clifor = clifordao.listar("nome");
            
            Messages.addFlashGlobalInfo("registro removido com sucesso");
            
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao excluir um registro");
            erro.printStackTrace();
    }
    }
     
    
}

