package br.fructus.bean;

import br.fructus.dao.CliForDao;
import br.fructus.dao.MunicipioDao;
import br.fructus.dao.NfeRaizDao;
import br.fructus.dao.PaisDao;
import br.fructus.dao.ProdutorDao;
import br.fructus.entity.CliFor;
import br.fructus.entity.Municipio;
import br.fructus.entity.NfeRaiz;
import br.fructus.entity.Produtor;
import br.inf.portalfiscal.nfe.TNfeProc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@SessionScoped
public class ImportaNfeBean implements Serializable {

    private UploadedFile nfe;
    private NfeRaiz nfeRaiz;
    private CliFor cliFor;
    private List<NfeRaiz> nfeRaizes;
    private Produtor produtor;
    private CliFor fornecedor;

    public ImportaNfeBean() {
    }

    public UploadedFile getNfe() {
        return nfe;
    }

    public void setNfe(UploadedFile nfe) {
        this.nfe = nfe;
    }

    public NfeRaiz getNfeRaiz() {
        return nfeRaiz;
    }

    public void setNfeRaiz(NfeRaiz nfeRaiz) {
        this.nfeRaiz = nfeRaiz;
    }

    public List<NfeRaiz> getNfeRaizes() {
        return nfeRaizes;
    }

    public void setNfeRaizes(List<NfeRaiz> nfeRaizes) {
        this.nfeRaizes = nfeRaizes;
    }

    public CliFor getCliFor() {
        return cliFor;
    }

    public void setCliFor(CliFor cliFor) {
        this.cliFor = cliFor;
    }

    public Produtor getProdutor() {
        return produtor;
    }

    public void setProdutor(Produtor produtor) {
        this.produtor = produtor;
    }

    public CliFor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(CliFor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    

    public void upload(FileUploadEvent event) throws JAXBException {
        nfe = event.getFile();
        if (nfe != null) {
            File file = new File("C:/projetos java/NetBeansProjects/fructus_web", nfe.getFileName());
            System.out.println(file.getPath());
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(event.getFile().getContents());
                fos.close();

                JAXBContext jc = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
                Unmarshaller un = jc.createUnmarshaller();
                TNfeProc nfeProc = un.unmarshal(new StreamSource(new File(file.getPath())), TNfeProc.class).getValue();

                // importar raiz nfe
                nfeRaiz = new NfeRaiz();
                produtor = new Produtor();
                fornecedor = new CliFor();
                nfeRaiz.setIdNfe(nfeProc.getNFe().getInfNFe().getId());
                nfeRaiz.setVersao(nfeProc.getNFe().getInfNFe().getVersao());
                NfeRaizDao nfeRaizDao = new NfeRaizDao();
                String idNfe = nfeProc.getNFe().getInfNFe().getId();
                nfeRaizes = nfeRaizDao.pesquisarIdNfe(idNfe);

                //Verificar produtor
                ProdutorDao produtorDao = new ProdutorDao();
                PaisDao paisDao = new PaisDao();
                MunicipioDao municipioDao = new MunicipioDao();
                String cpfProdutor = nfeProc.getNFe().getInfNFe().getDest().getCPF();
                Produtor cpfPro = produtorDao.procuraprodutor(cpfProdutor);

                //Verificar fornecedor
                CliForDao cliForDao = new CliForDao();
                String CnpjForne = nfeProc.getNFe().getInfNFe().getEmit().getCNPJ();
                cliFor = cliForDao.cnpj(CnpjForne);

                System.out.println(nfeRaizes);
                if (cpfPro != null) {
                    
                    //se ainda não existir nfe raiz verifica demais requisitos
                    if (nfeRaizes.isEmpty()) {
                        //erro entidade nula
                        if (cliFor ==null){
                            
                            
                            org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('cadfornecedores').show();");
                            fornecedor.setNome(nfeProc.getNFe().getInfNFe().getEmit().getXNome());
                            fornecedor.setFantasia(nfeProc.getNFe().getInfNFe().getEmit().getXFant());
                            fornecedor.setLogradouro(nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getXLgr());
                            fornecedor.setBairro(nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getXBairro());
                            fornecedor.setComplemento(nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getXCpl());
                            fornecedor.setNumero(nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getNro());
                            fornecedor.setCep(nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getCEP());
                            if ((nfeProc.getNFe().getInfNFe().getEmit().getCNPJ())!=null){fornecedor.setCpfcnpj(nfeProc.getNFe().getInfNFe().getEmit().getCNPJ());}
                            if ((nfeProc.getNFe().getInfNFe().getEmit().getCPF())!=null) {fornecedor.setCpfcnpj(nfeProc.getNFe().getInfNFe().getEmit().getCPF());}
                            fornecedor.setIe(nfeProc.getNFe().getInfNFe().getEmit().getIE());
                            fornecedor.setImunicipal(nfeProc.getNFe().getInfNFe().getEmit().getIM());
                            fornecedor.setPais(paisDao.codPais(Integer.parseInt(nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getCPais())));
                            fornecedor.setMunicipio(municipioDao.codMunicipio(Integer.parseInt(nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getCMun())));
                            if ((nfeProc.getNFe().getInfNFe().getEmit().getCRT())!= null) {fornecedor.setCodregime(Integer.parseInt(nfeProc.getNFe().getInfNFe().getEmit().getCRT()));}
                            if ((nfeProc.getNFe().getInfNFe().getEmit().getCNAE())!=null) {fornecedor.setCnae(Integer.parseInt(nfeProc.getNFe().getInfNFe().getEmit().getCNAE()));}
                            fornecedor.setTelefone(nfeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getFone());
                            
                            
                            
                        }//falta dizer oque vai fazer se não for igual a nulo

                        nfeRaizDao.salvarNfeRaiz(nfeRaiz);
                       // cliForDao.merge(cliFor);

                        FacesContext instance = FacesContext.getCurrentInstance();
                        instance.addMessage("mensagens", new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                nfe.getFileName() + " anexado com sucesso", null));
                        

                    } else {
                        Messages.addFlashGlobalInfo("Nota Fiscal Já Cadastrada");
                    }
                } else {
                    Messages.addFlashGlobalInfo("Nota Fiscal não pertence a esses produtores");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
    
     public void salvar(){
        try{
            CliForDao cliForDao = new CliForDao();
            cliForDao.merge(fornecedor);
            fornecedor = new CliFor();
          
            Messages.addFlashGlobalInfo("registro salvo com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar um registro");
            erro.printStackTrace();
        }
        
    }
}
