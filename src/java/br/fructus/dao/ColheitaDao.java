
package br.fructus.dao;

import br.fructus.entity.Colheita;
import br.fructus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Giovani
 */
public class ColheitaDao extends GenericDao<Colheita> {
     
      public List<Colheita> pesquisarC( Integer graosidgraos, String safra, String descricao, String nome){
        Session session = HibernateUtil.getSessionFactory().openSession();
      try{
         Criteria consulta = session.createCriteria(Colheita.class);
         consulta.createAlias("deposito","d");
         consulta.createAlias("safra","s");
         consulta.createAlias("s.variedade","v");
         consulta.createAlias("v.IdGraos","g");
         consulta.createAlias("s.talhao","t");
         consulta.createAlias("s.safra","a");
         
         if (descricao == null & nome == null) {
         consulta.add(Restrictions.eq("g.IdGraos",graosidgraos ));
         consulta.add(Restrictions.eq("a.safra", safra));
         consulta.addOrder(Order.asc("data"));
         List<Colheita> resultado = consulta.list();
         return resultado;
         }
         
         else if(descricao == null){
         consulta.add(Restrictions.eq("g.IdGraos",graosidgraos ));
         consulta.add(Restrictions.eq("a.safra", safra));
         consulta.add(Restrictions.eq("d.nome", nome));
         consulta.addOrder(Order.asc("data"));
         List<Colheita> resultado = consulta.list();
         return resultado;
      }
         
        else if(nome == null){
         consulta.add(Restrictions.eq("g.IdGraos",graosidgraos ));
         consulta.add(Restrictions.eq("a.safra", safra));
         consulta.add(Restrictions.eq("t.descricao", descricao));
         consulta.addOrder(Order.asc("data"));
         List<Colheita> resultado = consulta.list();
         return resultado;
      } 
        else{
         consulta.add(Restrictions.eq("g.IdGraos",graosidgraos ));
         consulta.add(Restrictions.eq("a.safra", safra));
         consulta.add(Restrictions.eq("t.descricao", descricao));
         consulta.add(Restrictions.eq("d.nome", nome));
         consulta.addOrder(Order.asc("data"));
         List<Colheita> resultado = consulta.list();
         return resultado;  
        }
      }catch(RuntimeException erro){
          throw erro;
      }finally{
          session.close();
      }
    
      }  

}
