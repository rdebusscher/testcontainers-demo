package be.rubus.payara.testcontainers.client;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


@Path("/integration")
@RequestScoped
public class IntegrationResource {

    @RestClient
    @Inject
    private HelloService helloService;

    @GET
    @Path("/test")
    public String test(@QueryParam("lang") String lang) {
        return helloService.sayHello("Payara", lang);
    }
}
