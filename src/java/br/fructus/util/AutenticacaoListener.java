
package br.fructus.util;

import br.fructus.bean.AutenticacaoBean;
import br.fructus.entity.Usuario;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.omnifaces.util.Faces;


public class AutenticacaoListener implements PhaseListener{
   @Inject
    private AutenticacaoBean autenticacaobean; 
    @Override
    public void afterPhase(PhaseEvent event) {
      /* String paginaAtual = Faces.getViewId(); 
        
       boolean ehPaginaAutenticacao = paginaAtual.contains("autenticacao.xhtml");
       
       if(!ehPaginaAutenticacao){
           // verifica se tenho usuário logado*/
                    
          AutenticacaoBean autenticacaobean =   Faces.getSessionAttribute("autenticacaoBean");
          
           System.out.println("tem usuario: " + autenticacaobean);
         /*  if(autenticacaobean == null){
             Faces.navigate("/autenticacao.xhtml");
               return;
           }
           Usuario usuario = autenticacaobean.getUsuariologado();
           if(usuario == null){
              Faces.navigate("/autenticacao.xhtml"); 
              return;
           }
           
       }*/
    }
    
  /*  private FacesContext facesContext;

    @Override
    public void afterPhase(PhaseEvent event) {
        facesContext = event.getFacesContext();
        String viewId = facesContext.getViewRoot().getViewId();

        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
        boolean paginaLogin = viewId.lastIndexOf("autenticacao") > -1;
        System.out.println(paginaLogin);
        
        if (existeUsuarioLogado() && paginaLogin) {
            System.out.println();
            nh.handleNavigation(facesContext, null, "/index.xhtml-redirect=true");
        } else if (!existeUsuarioLogado() && !paginaLogin) {
            nh.handleNavigation(facesContext, null, "/autenticacao?faces-redirect=true");
        }
    }

    public boolean existeUsuarioLogado() {
        return (((Usuario) getAtributoSessao("usuario")) != null);
        
    }

    public Object getAtributoSessao(String attributeName) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            return session.getAttribute(attributeName);
        }
        return null;
    }*/

    @Override
    public void beforePhase(PhaseEvent event) {
       
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
