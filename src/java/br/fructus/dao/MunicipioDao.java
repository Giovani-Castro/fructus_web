
package br.fructus.dao;


import br.fructus.entity.Municipio;
import br.fructus.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class MunicipioDao extends GenericDao <Municipio> {
    
    public Municipio codMunicipio(int codmunicipio){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try{
            Criteria consulta = session.createCriteria(Municipio.class);
            consulta.add(Restrictions.eq("codmunicipio" , codmunicipio ));
                                                
            Municipio resultado = (Municipio)consulta.uniqueResult();
            return resultado;
        
        }catch(RuntimeException erro){
          throw erro;
        }finally{
          session.close();
        }
     }
}
