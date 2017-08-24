package br.fructus.dao;

import br.fructus.entity.UniMed;
import br.fructus.util.HibernateUtil;
import br.fructus.util.JSFUtil;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

/**
 *
 * @author Giovani
 */
public class UniMedDao {

    private Session session;
    private Transaction trans;
    private List<UniMed> listuni;
    private UniMed unid;

    public List<UniMed> getList() {
        session = HibernateUtil.getSessionFactory().openSession();
        trans = session.beginTransaction();

        Criteria cri = session.createCriteria(UniMed.class);
        this.listuni = cri.list();
        session.close();
        return listuni;
    }

    public UniMed buscarPorCodigo(String Unidade) {
        session = HibernateUtil.getSessionFactory().openSession();
        UniMed unimed = null;
        try {
            Query consulta = (Query) session.getNamedQuery("UniMed.buscarporunidade");
            consulta.setParameter("Unidade", Unidade);
            unimed = (UniMed) consulta.getSingleResult();
        } catch (RuntimeException ex) {
            throw ex;
        }
        finally{
            session.close();
        }
    return unimed;
    }
    
    public UniMed porcodigo(String Unidade) {
        session = HibernateUtil.getSessionFactory().openSession();
        UniMed unimed = null;
        try{
        Criteria cri = session.createCriteria(UniMed.class);
        cri.add(Restrictions.eq("Unidade", Unidade));
       // Criterion unidade = Restrictions.gt("Unidade", Unidade);
        //cri.setMaxResults(1);
        unimed = (UniMed) cri.uniqueResult();
        }catch (RuntimeException ex){
            throw ex;
        }
        finally{
            session.close();
        }
        return unimed;
    }

    
    public void salveUniMed(UniMed u) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            UniMed unimed = new UniMed();
            unimed.setUnidade(u.getUnidade());
            unimed.setDescricao(u.getDescricao());
            session.save(unimed);
            trans.commit();
            JSFUtil.adicionarMensagemSucesso("Unidade Salva com Sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void removeUniMed(UniMed u) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            session.delete(u);
            trans.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    public void excluir(UniMed unimed){
      session = HibernateUtil.getSessionFactory().openSession();
      Transaction trans = null;
      try{
      trans = session.beginTransaction(); 
      session.delete(unimed);
      trans.commit();
    }catch(RuntimeException ex){
       if(trans != null) {
           trans.rollback();
       }
       throw ex;
    }finally {
          session.close();
      }
      
      
    }
    
    public void excluirporcodigo(String Unidade){
      session = HibernateUtil.getSessionFactory().openSession();
      Transaction trans = null;
      try{
      trans = session.beginTransaction(); 
      UniMed unimed = porcodigo(Unidade);
      session.delete(unimed);
      trans.commit();
    }catch(RuntimeException ex){
       if(trans != null) {
           trans.rollback();
       }
       throw ex;
    }finally {
          session.close();
      }
      
      
    }
    
    public void editarUnidade(UniMed unimed){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction trans = null;
       try{
           trans = session.beginTransaction();
           session.update(unimed);
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
    public List<UniMed> getListuni() {
        return listuni;
    }

    public void setListuni(List<UniMed> listuni) {
        this.listuni = listuni;
    }

}
