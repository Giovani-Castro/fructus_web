
package br.fructus.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@Named
@SessionScoped
public class ImportaNfeBean implements Serializable {

    private UploadedFile nfe;
    
    public ImportaNfeBean() {
    }

    public UploadedFile getNfe() {
        return nfe;
    }

    public void setNfe(UploadedFile nfe) {
        this.nfe = nfe;
    }
    
    public void upload(FileUploadEvent event) {
    nfe = event.getFile();
    if (nfe!= null){
        File file = new File("C:/projetos java/NetBeansProjects/FructusWeb", nfe.getFileName());
        System.out.println(file.getPath());
        try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(event.getFile().getContents());
                fos.close();

                FacesContext instance = FacesContext.getCurrentInstance();
                instance.addMessage("mensagens", new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        nfe.getFileName() + " anexado com sucesso", null));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 

    }
 
}
}
 