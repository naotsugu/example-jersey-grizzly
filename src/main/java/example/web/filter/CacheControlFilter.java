package example.web.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

// No @Provider
@Provider
public class CacheControlFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        if (requestContext.getMethod().equals("GET")) {

            CacheControl cacheControl = new CacheControl();
            cacheControl.setMaxAge(100);
            responseContext.getHeaders().add("Cache-Control", cacheControl);

        }

    }
}
