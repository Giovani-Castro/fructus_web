
package br.fructus.converter;

import br.fructus.bean.GraosBean;
import br.fructus.dao.UniMedDao;
import br.fructus.entity.Graos;
import br.fructus.entity.UniMed;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("graosConverter")
public class ConverterGraos implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try{
          String unidade = value;  
          UniMedDao unimeddao = new UniMedDao();
          UniMed unimed = unimeddao.porcodigo(unidade);
          return unimed;
       }catch (RuntimeException ex){
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object objeto) {
      try{
         UniMed unimed = (UniMed) objeto;
         String unidade = unimed.getUnidade();
         return unidade;
        }catch(RuntimeException ex){
            return null;
        }   
    }  
}
