package example.web.resource;

import com.avaje.ebean.Ebean;
import example.domain.model.Customer;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;


@Path("customers")
public class CustomerResource {


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createCustomer(Customer customer) {
        Ebean.save(customer);
        return Response.created(
                URI.create("/customers/" + customer.getId())).build();
    }


    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Customer get(@PathParam("id") Long id) {

        Customer customer = Ebean.find(Customer.class, id);

        if (customer == null) {
            // handled by EntityNotFoundExceptionMapper.
            throw new EntityNotFoundException();
        }

        return customer;

    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(Customer customer) {

        Customer current = Ebean.find(Customer.class, customer.getId());

        if (current == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);

        current.setFirstName(customer.getFirstName());
        current.setLastName(customer.getLastName());
        current.setAddress(customer.getAddress());
        Ebean.update(current);

        return Response.noContent().build();
    }


    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {

        int count = Ebean.delete(Customer.class, id);

        if (count != 1)
            throw new WebApplicationException(Response.Status.NOT_FOUND);

        return Response.noContent().build();

    }


}
