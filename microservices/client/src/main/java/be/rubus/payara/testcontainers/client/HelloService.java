package be.rubus.payara.testcontainers.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@RegisterRestClient
@ApplicationScoped
public interface HelloService {

    @GET
    @Path("/{name}")
    String sayHello(@PathParam("name") String name, @QueryParam("lang") String lang);

}
