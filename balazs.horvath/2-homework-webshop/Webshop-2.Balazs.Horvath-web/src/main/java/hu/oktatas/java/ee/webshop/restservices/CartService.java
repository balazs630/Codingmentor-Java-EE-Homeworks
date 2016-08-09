package hu.oktatas.java.ee.webshop.restservices;

import hu.oktatas.java.ee.webshop.beans.MobileType;
import hu.oktatas.java.ee.webshop.shoppingcart.ShoppingCart;
import hu.oktatas.java.ee.webshop.shoppingcart.exceptions.MobileNotExistInTheCartException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/shoppingcart")
@SessionScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class CartService implements Serializable {

    @Inject
    private ShoppingCart cart;

    @POST
    @Path("/add")
    public void add(MobileType type, @Context HttpServletRequest request) {
        cart.addItem(type, 1);
    }

    @DELETE
    @Path("/remove")
    public void remove(MobileType type, @Context HttpServletRequest request) 
            throws MobileNotExistInTheCartException {
        cart.removeItem(type, 1);
    }

    @POST
    @Path("/checkout")
    public void checkout(@Context HttpServletRequest request) {
        cart.checkout();
        request.getSession().invalidate();
    }
}
