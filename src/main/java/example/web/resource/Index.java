package example.web.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class Index {

    @GET
    public Response hello() {
        return Response.ok("Hello jersey-grizzly!!").build();
    }

}
