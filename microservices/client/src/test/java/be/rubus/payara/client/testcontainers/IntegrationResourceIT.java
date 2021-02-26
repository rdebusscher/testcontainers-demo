package be.rubus.payara.client.testcontainers;

import be.rubus.payara.testcontainers.client.IntegrationResource;
import org.junit.jupiter.api.Test;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@MicroShedTest
public class IntegrationResourceIT {

    @Container
    public static ApplicationContainer app = new ApplicationContainer();

    @Container
    public static GenericContainer<?> otherService = new GenericContainer<>("demo-server:1.0")
            .withNetworkAliases("otherService");

    @RESTClient
    public static IntegrationResource integrationResource;

    @Test
    public void sayHello() throws IOException {
        String data = integrationResource.test("en");

        assertThat(data).isEqualTo("Hello Payara");

        data = integrationResource.test("fr");

        assertThat(data).isEqualTo("Bonjour Payara");

    }

}