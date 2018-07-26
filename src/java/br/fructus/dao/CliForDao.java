
package br.fructus.dao;

import br.fructus.entity.CliFor;
import br.fructus.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class CliForDao extends GenericDao <CliFor> {
    
    public CliFor cnpj(String cpfcnpj){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try{
            Criteria consulta = session.createCriteria(CliFor.class);
            consulta.add(Restrictions.eq("cpfcnpj" , cpfcnpj ));
                                                
            CliFor resultado = (CliFor)consulta.uniqueResult();
            return resultado;
        
        }catch(RuntimeException erro){
          throw erro;
        }finally{
          session.close();
        } 

    }
        
    
    
}
