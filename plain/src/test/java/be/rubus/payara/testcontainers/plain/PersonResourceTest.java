package be.rubus.payara.testcontainers.plain;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.ContainerNetwork;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class PersonResourceTest {

    private static final Logger logger = LoggerFactory.getLogger(PersonResourceTest.class);

    @Container
    private static MySQLContainer mysqlServer = new MySQLContainer()
            .withDatabaseName("rubus")
            .withUsername("rubus")
            .withPassword("rubus");

    @Container
    private GenericContainer appContainer = new GenericContainer<>("demo-plain:1.0")
            .withExposedPorts(8080)
            .withLogConsumer(new Slf4jLogConsumer(logger))
            .waitingFor(Wait.forHttp("/health"))
            //.withEnv("MYSQL_CONN", "jdbc:tc:mysql:///rubus?TC_INITSCRIPT=init_mysql.sql");
            .withEnv("MYSQL_CONN", "jdbc:mysql://" + getDockerContainerIP(mysqlServer.getDockerClient(), mysqlServer.getContainerId()) + ":3306/rubus");


    @BeforeEach
    public void setup() throws DatabaseUnitException, SQLException, URISyntaxException, IOException {
        // Initialize Mysql Database and rights
        Connection conn = DriverManager.
                getConnection(mysqlServer.getJdbcUrl() , "root", "rubus");
        conn.createStatement().execute("CREATE DATABASE RUBUS");
        conn.createStatement().execute("GRANT ALL PRIVILEGES ON *.* TO 'rubus'@'%';");
        conn.close();

        // Create empty tables
        ScriptUtils.runInitScript(new JdbcDatabaseDelegate(mysqlServer, ""), "init_mysql.sql");

        // Fill with test data
        Connection dbUnitConnection = DriverManager.
                getConnection(mysqlServer.getJdbcUrl(), "rubus", "rubus");
        IDatabaseConnection connection = new MySqlConnection(dbUnitConnection, "rubus");

        URL testDataFile = PersonResourceTest.class.getClassLoader().getResource("test.xls");
        IDataSet dataSet = new XlsDataSet(Paths.get(testDataFile.toURI()).toFile());


        try {
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        } finally {
            connection.close();
        }


    }

    @Test
    public void personAverage() throws IOException {

        String url = String.format("http://%s:%s/rest/person/average", appContainer.getContainerIpAddress(), appContainer.getMappedPort(8080));
        // We can use Rest client for it, or just plain Java
        String data;

        try (InputStream inputStream = new URL(url).openStream()) {
            data = new Scanner(inputStream).useDelimiter("\\A").next();
        }

        assertThat(data).isEqualTo("{\"age\":41.0}");

    }

    public static String getDockerContainerIP(DockerClient dockerClient, String containerId) {

        InspectContainerResponse resp = dockerClient.inspectContainerCmd(containerId).exec();
        Map<String, ContainerNetwork> networks = resp.getNetworkSettings().getNetworks();
        ContainerNetwork containerNetwork = networks.values().iterator().next();
        return containerNetwork.getIpAddress();

    }
}