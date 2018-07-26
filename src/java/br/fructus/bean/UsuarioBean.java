
package br.fructus.bean;

import br.fructus.dao.UsuarioDao;
import br.fructus.entity.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;


@Named
@SessionScoped
public class UsuarioBean implements Serializable {

   private Usuario usuario;
   private List<Usuario> usuarios;
   
    public UsuarioBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

@PostConstruct
public void listar() {
        try {
            UsuarioDao usuariodao = new UsuarioDao();
            usuarios = usuariodao.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao Listar usuarios");
            erro.printStackTrace();
        }

    }
    public void novo() {
        try {
            usuario = new Usuario();
            
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar gerar um usuário");
            erro.printStackTrace();
        }
    }
public void salvar(){
        try{
            UsuarioDao usuariodao = new UsuarioDao();
            SimpleHash hash = new SimpleHash("md5", usuario.getSenhasemcriptografia());
            usuario.setSenha(hash.toHex());
            usuariodao.merge(usuario);
            usuario = new Usuario();
            usuarios = usuariodao.listar();
            Messages.addFlashGlobalInfo("Usuário salvo com sucesso");
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao tentar salvar um novo Usuario");
            erro.printStackTrace();
        }
}

public void excluir(ActionEvent evento){
        try{
            usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioselecionado");
            UsuarioDao usuariodao = new UsuarioDao();
            usuariodao.excluir(usuario);
            usuarios = usuariodao.listar();
            Messages.addFlashGlobalInfo("Usuário removido com sucesso");
            
        }catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um Erro ao excluir um usuário");
            erro.printStackTrace();
    }
    }


public void editar(ActionEvent evento){
         try {
            usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioselecionado");
            
            } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao selecionar um Usuario");
            erro.printStackTrace();
        }
    }
    


}



