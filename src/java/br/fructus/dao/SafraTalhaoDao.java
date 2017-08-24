
package br.fructus.dao;

import br.fructus.entity.SafraTalhao;
import br.fructus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


public class SafraTalhaoDao extends GenericDao<SafraTalhao> {
    public List<SafraTalhao> buscarPorGrao(Integer graosidgraos){
        Session session = HibernateUtil.getSessionFactory().openSession();
      try{
         Criteria consulta = session.createCriteria(SafraTalhao.class);
         consulta.createAlias("variedade","v");
         consulta.createAlias("v.IdGraos","g");
         consulta.add(Restrictions.eq("g.IdGraos",graosidgraos ));
         consulta.addOrder(Order.asc("safra"));
         List<SafraTalhao> resultado = consulta.list();
         return resultado;
      }catch(RuntimeException erro){
          throw erro;
      }finally{
          session.close();
      }
    }
}
