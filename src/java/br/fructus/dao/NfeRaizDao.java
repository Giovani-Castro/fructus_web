
package br.fructus.dao;

import br.fructus.entity.NfeRaiz;
import br.fructus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class NfeRaizDao {
    
    private Session session;
    private Transaction trans;
    
    public void salvarNfeRaiz(NfeRaiz nferaiz){
        
         try {
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            
            session.save(nferaiz);
            trans.commit();
            
            
         }catch(Exception e){
             e.printStackTrace();
        } finally {
            session.flush();
            session.close();
         }
    }
    
    public List <NfeRaiz> pesquisarIdNfe(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Criteria consulta = session.createCriteria(NfeRaiz.class);
            consulta.add(Restrictions.eq("IdNfe", id));
            List<NfeRaiz> resultado = consulta.list();
            return resultado;
        }catch(RuntimeException erro){
            throw erro;
        }
           
    }
}
