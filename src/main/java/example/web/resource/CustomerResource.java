package example.web.resource;

import example.domain.model.Address;
import example.domain.model.Customer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("customers")
public class CustomerResource {

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Customer get(@PathParam("id") Long id) {
        return new Customer(id, "Michael", " Stipe",
                new Address("1016", "Hollywood", "CA", "90038", "US"));
    }

}
