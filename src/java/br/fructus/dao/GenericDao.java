
package br.fructus.dao;

import br.fructus.util.HibernateUtil;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


public class GenericDao<Entidade> {
    private Class<Entidade> classe;
    
    public GenericDao(){
        this.classe = (Class<Entidade>)  ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public void salvar(Entidade entidade){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction trans = null;
       try{
           trans = session.beginTransaction();
           session.save(entidade);
           trans.commit();
       }catch(RuntimeException erro){
           if(trans != null){
               trans.rollback();
           }
           throw erro;
       }finally{
           session.close();
       }
    }
    public List<Entidade> listar(){
      Session session = HibernateUtil.getSessionFactory().openSession();
      try{
         Criteria consulta = session.createCriteria(classe);
         List<Entidade> resultado = consulta.list();
         return resultado;
      }catch(RuntimeException erro){
          throw erro;
      }finally{
          session.close();
      }
    }
    public Entidade buscar(Long codigo){
      Session session = HibernateUtil.getSessionFactory().openSession();
      try{
         Criteria consulta = session.createCriteria(classe);
         consulta.add(Restrictions.idEq(codigo));
         Entidade resultado = (Entidade) consulta.uniqueResult();
         return resultado;
      }catch(RuntimeException erro){
          throw erro;
      }finally{
          session.close();
      }
    }
    public void excluir(Entidade entidade){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction trans = null;
       try{
           trans = session.beginTransaction();
           session.delete(entidade);
           trans.commit();
       }catch(RuntimeException erro){
           if(trans != null){
               trans.rollback();
           }
           throw erro;
       }finally{
           session.close();
       }
    }
   public void editar(Entidade entidade){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction trans = null;
       try{
           trans = session.beginTransaction();
           session.update(entidade);
           trans.commit();
       }catch(RuntimeException erro){
           if(trans != null){
               trans.rollback();
           }
           throw erro;
       }finally{
           session.close();
       }
    } 
   public List<Entidade> listar(String campoOrdenado){
      Session session = HibernateUtil.getSessionFactory().openSession();
      try{
         Criteria consulta = session.createCriteria(classe);
         consulta.addOrder(Order.asc(campoOrdenado));
         List<Entidade> resultado = consulta.list();
         return resultado;
      }catch(RuntimeException erro){
          throw erro;
      }finally{
          session.close();
      }
    }
   
   public void merge(Entidade entidade){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction trans = null;
       try{
           trans = session.beginTransaction();
           session.merge(entidade);
           trans.commit();
       }catch(RuntimeException erro){
           if(trans != null){
               trans.rollback();
           }
           throw erro;
       }finally{
           session.close();
       }
    }
}

