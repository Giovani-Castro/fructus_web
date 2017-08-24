
import br.fructus.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giovani
 */
public class HibernateUtilTeste {
   @Test
    public void conectar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.close();
        HibernateUtil.getSessionFactory().close();
    }
    
}
