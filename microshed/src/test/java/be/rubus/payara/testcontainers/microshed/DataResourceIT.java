package be.rubus.payara.testcontainers.microshed;

import org.junit.jupiter.api.Test;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.MicroProfileApplication;
import org.testcontainers.junit.jupiter.Container;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@MicroShedTest
public class DataResourceIT {

    @Container
    public static MicroProfileApplication app = new MicroProfileApplication();

    @Inject
    public static DataResource dataResource;

    @Test
    public void getData() {
        Data data = dataResource.getData();

        assertThat(data.getName()).isEqualTo("Rudy");
        assertThat(data.getAge()).isEqualTo(42);

    }

}