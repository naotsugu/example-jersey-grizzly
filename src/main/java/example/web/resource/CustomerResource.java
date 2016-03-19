package example.web.resource;

import com.avaje.ebean.Ebean;
import example.domain.model.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;


@Path("customers")
public class CustomerResource {

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Customer get(@PathParam("id") Long id) {
        return Ebean.find(Customer.class, id);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createCustomer(Customer customer) {
        Ebean.save(customer);
        return Response.created(
                URI.create("/customers/" + customer.getId())).build();
    }


}
