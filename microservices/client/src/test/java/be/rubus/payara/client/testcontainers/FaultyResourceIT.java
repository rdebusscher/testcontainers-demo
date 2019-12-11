package be.rubus.payara.client.testcontainers;

import be.rubus.payara.testcontainers.client.IntegrationResource;
import org.junit.jupiter.api.Test;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.MicroProfileApplication;
import org.testcontainers.junit.jupiter.Container;

import javax.inject.Inject;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@MicroShedTest
public class FaultyResourceIT {

    @Container
    public static MicroProfileApplication app = new MicroProfileApplication();

    @Inject
    public static IntegrationResource integrationResource;

    @Test
    public void sayHello() throws IOException {

        String data = integrationResource.test("nl");

        assertThat(data).isEqualTo("Hallo Payara");
        assertThat(app.getMappedPort(8080)).isNotEqualTo(8080);
        //app.getMappedPort(8080) return the port on which application is accessible from localhost/docker host IP address


    }

}