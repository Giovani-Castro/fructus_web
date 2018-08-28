
package br.fructus.dao;

import br.fructus.entity.Pais;
import br.fructus.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class PaisDao extends GenericDao <Pais> {
    
     public Pais codPais(int codpais){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try{
            Criteria consulta = session.createCriteria(Pais.class);
            consulta.add(Restrictions.eq("codpais" , codpais ));
                                                
            Pais resultado = (Pais)consulta.uniqueResult();
            return resultado;
        
        }catch(RuntimeException erro){
          throw erro;
        }finally{
          session.close();
        }
     }
}
