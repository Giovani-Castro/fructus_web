
package br.fructus.bean;

import br.fructus.dao.UsuarioDao;
import br.fructus.entity.Usuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;


@Named
@SessionScoped
public class AutenticacaoBean implements Serializable {
    
    private Usuario usuario;
    private Usuario usuariologado;

    
    public AutenticacaoBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuariologado() {
        return usuariologado;
    }

    public void setUsuariologado(Usuario usuariologado) {
        this.usuariologado = usuariologado;
    }
    
    
    
    @PostConstruct
        public void iniciar(){
            usuario = new Usuario();
        }
        
        public void autentica(){
            
        try {
            UsuarioDao usuariodao = new UsuarioDao();
            usuariologado = usuariodao.autenticar(usuario.getCpf(), usuario.getSenha());
            if(usuariologado == null){
                Messages.addGlobalError("CPF e/ou senha icorreto");
                return;
            }
            Faces.redirect("./index.xhtml");
        } catch (IOException erro) {
            Messages.addGlobalError(erro.getMessage());
        }
        }
}
            
