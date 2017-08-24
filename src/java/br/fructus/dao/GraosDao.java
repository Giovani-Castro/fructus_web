package br.fructus.dao;

import br.fructus.entity.Graos;
import br.fructus.util.HibernateUtil;
import br.fructus.util.JSFUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class GraosDao {

    private Session session;
    private Transaction trans;
    private List<Graos> listgrao;

    public void salveGraos(Graos g) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            Graos grao = new Graos();
            grao.setDescrição(g.getDescrição());
            grao.setUnidade(g.getUnidade());
            session.save(grao);
            trans.commit();
            JSFUtil.adicionarMensagemSucesso("Grãos salvo com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
    public List<Graos> getMostraGrao(){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
        Criteria cri = session.createCriteria(Graos.class);
        this.listgrao = cri.list();
        return listgrao;
    }catch(RuntimeException erro){
        throw erro;
            
            }finally{
            session.close();
        }
    }
    
    public void removeGrao(Graos grao){
      session = HibernateUtil.getSessionFactory().openSession();
      Transaction trans = null;
      try{
      trans = session.beginTransaction(); 
      session.delete(grao);
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
    
       public Graos listarPorCodigo(int IdGraos){
        session = HibernateUtil.getSessionFactory().openSession();
        Graos graos = null;
       
        try{
        Criteria cri = session.createCriteria(Graos.class);
        cri.add(Restrictions.eq("IdGraos", IdGraos));
       // Criterion unidade = Restrictions.gt("Unidade", Unidade);
        //cri.setMaxResults(1);
        graos = (Graos) cri.uniqueResult();
        }catch (RuntimeException ex){
            throw ex;
        }
        finally{
            session.close();
        }
        return graos;
       }

       public void merge(Graos grao){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction trans = null;
       try{
           trans = session.beginTransaction();
           session.merge(grao);
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
    public List<Graos> getListgrao() {
        return listgrao;
    }

    public void setListgrao(List<Graos> listgrao) {
        this.listgrao = listgrao;
    }
    
}
