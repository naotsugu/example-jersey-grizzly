package example.web.filter;

import example.web.resource.CustomerResource;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class PoweredByFeature implements DynamicFeature {

    @Override
    public void configure(ResourceInfo ri, FeatureContext ctx) {

        if (CustomerResource.class.equals(ri.getResourceClass())
                && ri.getResourceMethod().getName().equals("createCustomer")) {
            ctx.register(new PoweredByResponseFilter("2.22"));
        }

    }
}