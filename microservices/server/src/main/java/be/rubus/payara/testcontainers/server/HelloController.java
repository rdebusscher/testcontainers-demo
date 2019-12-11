package be.rubus.payara.testcontainers.server;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 *
 */
@Path("/hello")
@Singleton
public class HelloController {

    @Inject
    private TranslateBoundary translateBoundary;

    @GET
    @Path("{name}")
    public String sayHello(@PathParam("name") String name, @QueryParam("lang") String lang) {
        return String.format(translateBoundary.getPattern(lang), name);
    }
}
