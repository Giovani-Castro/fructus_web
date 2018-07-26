
package br.fructus.dao;

import br.fructus.entity.Usuario;
import br.fructus.util.HibernateUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Giovani
 */
public class UsuarioDao extends GenericDao<Usuario> {
    
    public Usuario autenticar(String cpf, String senha){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try{
            Criteria consulta = session.createCriteria(Usuario.class);
            consulta.add(Restrictions.eq("cpf" , cpf ));
            
            SimpleHash hash = new SimpleHash("md5", senha);
            
            consulta.add(Restrictions.eq("senha", hash.toHex() ));
                        
            Usuario resultado = (Usuario) consulta.uniqueResult();
            return resultado;
        
        }catch(RuntimeException erro){
          throw erro;
        }finally{
          session.close();
        } 

    }
    
}
    


