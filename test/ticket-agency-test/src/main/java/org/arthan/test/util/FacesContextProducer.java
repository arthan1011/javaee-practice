package org.arthan.test.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;

/**
 * Created by ashamsiev on 16.12.2015
 */
public class FacesContextProducer {

    @Produces
    public FacesContext produceFacesContext(InjectionPoint injectionPoint) {
        return FacesContext.getCurrentInstance();
    }
}
