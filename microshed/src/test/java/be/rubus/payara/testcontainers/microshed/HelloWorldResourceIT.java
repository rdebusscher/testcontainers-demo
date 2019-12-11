package be.rubus.payara.testcontainers.microshed;

import org.junit.jupiter.api.Test;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.MicroProfileApplication;
import org.testcontainers.junit.jupiter.Container;

import javax.inject.Inject;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@MicroShedTest
public class HelloWorldResourceIT {

    @Container
    public static MicroProfileApplication app = new MicroProfileApplication();

    @Inject
    public static HelloWorldResource helloWorldResource;

    @Test
    public void sayHello() {
        String data = helloWorldResource.sayHello();

        assertThat(data).startsWith("Hello World");
        System.out.println(data);

    }

}