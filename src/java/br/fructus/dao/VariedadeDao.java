
package br.fructus.dao;

import br.fructus.entity.Variedade;
import br.fructus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


public class VariedadeDao extends GenericDao<Variedade> {
    public List<Variedade> buscarPorGrao(Integer graosidgraos){
        Session session = HibernateUtil.getSessionFactory().openSession();
      try{
         Criteria consulta = session.createCriteria(Variedade.class);
         consulta.add(Restrictions.eq("IdGraos.IdGraos",graosidgraos ));
         consulta.addOrder(Order.asc("descricao"));
         List<Variedade> resultado = consulta.list();
         return resultado;
      }catch(RuntimeException erro){
          throw erro;
      }finally{
          session.close();
      }
    }
}
