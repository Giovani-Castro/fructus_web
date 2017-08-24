
package br.fructus.dao;

import br.fructus.entity.Cidade;
import br.fructus.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


public class CidadeDao extends GenericDao<Cidade> {
    public List<Cidade> buscarPorEstado(Long estadocodigo){
        Session session = HibernateUtil.getSessionFactory().openSession();
      try{
         Criteria consulta = session.createCriteria(Cidade.class);
         consulta.add(Restrictions.eq("estado.codigo",estadocodigo ));
         consulta.addOrder(Order.asc("nome"));
         List<Cidade> resultado = consulta.list();
         return resultado;
      }catch(RuntimeException erro){
          throw erro;
      }finally{
          session.close();
      }
    }
    
}
