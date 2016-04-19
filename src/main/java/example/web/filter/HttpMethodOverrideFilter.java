package example.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class HttpMethodOverrideFilter implements ContainerRequestFilter {

    private static Logger log = LoggerFactory.getLogger(HttpMethodOverrideFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String methodOverride = requestContext.getHeaderString("X-Http-Method-Override");

        log.info("HttpMethodOverrideFilter:" + methodOverride);

        if (methodOverride != null) {
            requestContext.setMethod(methodOverride);
        }

    }
}
