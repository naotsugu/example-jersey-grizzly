package example.web.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/")
public class Index {

    private static Logger log = LoggerFactory.getLogger(Index.class);


    @GET
    public Response hello() {
        return Response.seeOther(
                UriBuilder.fromPath("/static/index.html").build()
        ).build();
    }


}
