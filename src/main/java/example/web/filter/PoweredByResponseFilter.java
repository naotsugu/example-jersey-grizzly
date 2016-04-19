package example.web.filter;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

@Priority(Priorities.HEADER_DECORATOR)
public class PoweredByResponseFilter implements ContainerResponseFilter {

    private final String version;

    public PoweredByResponseFilter(String version) {
        this.version = version;
    }

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("X-Powered-By", "Jersey :" + version);
    }

}