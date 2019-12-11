package be.rubus.payara.testcontainers.microshed;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.net.InetAddress;
import java.util.Optional;

@Path("/hello")
@ApplicationScoped
public class HelloWorldResource {

    @GET
    public String sayHello() {
        return "Hello World from " + getName();
    }

    private String getName() {
        String instanceName = null;
        try {
            instanceName = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(instanceName).orElse("<unknown>>");
    }
}
