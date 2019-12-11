package be.rubus.payara.testcontainers.plain;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("/person")
@ApplicationScoped
public class PersonResource {

    @Inject
    private PersonService personService;

    @GET
    @Path("/average")
    public Response getAverage() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("age", personService.getAverageAge());
        return Response.ok().entity(builder.build()).build();
    }
}
