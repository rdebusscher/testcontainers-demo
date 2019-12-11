package be.rubus.payara.testcontainers.microshed;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/data")
@ApplicationScoped
public class DataResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Data getData() {
        return new Data("Rudy", 42);
    }
}
