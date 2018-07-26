
package br.fructus.dao;

import br.fructus.entity.Produtor;
import br.fructus.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class ProdutorDao extends GenericDao<Produtor> {
    
    public Produtor procuraprodutor (String Cpf){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try{
           Criteria consulta = session.createCriteria(Produtor.class); 
           consulta.add(Restrictions.eq("Cpf",Cpf ));
           Produtor resultado = (Produtor)consulta.uniqueResult();
           return resultado;
        }catch(RuntimeException erro){
             throw erro;
        }finally{
          session.close();
        }
    
            
    }
    
}
