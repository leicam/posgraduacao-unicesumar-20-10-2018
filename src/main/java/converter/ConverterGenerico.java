/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import entidades.ClassePai;
import facade.AbstractFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author UniCesumar
 */
public class ConverterGenerico implements Converter{
    
    private AbstractFacade abstractFacade;
    
    public ConverterGenerico(AbstractFacade abstractFacade){
        this.abstractFacade = abstractFacade;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        return abstractFacade.buscar(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        return ((ClassePai)value).getId().toString();
    }
    
}
