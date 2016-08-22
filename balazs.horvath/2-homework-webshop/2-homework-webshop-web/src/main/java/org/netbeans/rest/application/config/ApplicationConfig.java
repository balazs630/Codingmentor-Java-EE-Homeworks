package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Horváth
 */
@javax.ws.rs.ApplicationPath("app")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(hu.oktatas.java.ee.webshop.exceptionmapper.GeneralExceptionMapper.class);
        resources.add(hu.oktatas.java.ee.webshop.restservices.CartService.class);
        resources.add(hu.oktatas.java.ee.webshop.restservices.MobileDBService.class);
        resources.add(hu.oktatas.java.ee.webshop.restservices.MobileTypeService.class);
        resources.add(hu.oktatas.java.ee.webshop.restservices.UserService.class);
    }
}
