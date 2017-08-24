
import br.fructus.dao.UniMedDao;
import br.fructus.entity.UniMed;
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
public class GerarTeste {
    @Test
   public void buscarPorUnidade(){
       UniMedDao dao = new UniMedDao();
       UniMed F1 = dao.porcodigo("Kg");
       UniMed F2 = dao.porcodigo("Cx");
       System.out.println(F1);
       System.out.println(F2);
   } 
}
