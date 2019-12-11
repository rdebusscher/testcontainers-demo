# Testcontainers demo

This repository contains the demos of for the Testcontainers presentation.

### Requirement

You need to have a Docker client installation which points to a valid Docker Environment. For more information, have a look at [Testcontainers Docker prerequisite page](https://www.testcontainers.org/supported_docker_environment/).

## demos

### Plain

This example shows how you can test an application endpoint which reads from a database.

Steps

- Run `mvn clean package` to build the Docker Image with the test application.
- Run the test method within `be.rubus.payara.testcontainers.plain.PersonResourceTest`.

### MicroShed

This examples shows you the basic setup with MicroShed testing using the Payara Micro plugin.

Steps

- Run `mvn clean package` to build the Docker Image with the test application.
- Run the test method within `be.rubus.payara.testcontainers.microshed.HelloWorldResourceIT`.
- Run the test method within `be.rubus.payara.testcontainers.microshed.DataResourceIT`. (uses JSON mapping)

### MicroServices

This examples shows you how you can test a micro-service (client) which depends on another micro-service (server)

Steps

- From within the `microservices/server`, run `mvn clean package` to build the Docker Image for this app.
- From within the `microservices/client`, run `mvn clean package` to build the Docker Image for app under test.
- Run the test method within `be.rubus.payara.client.testcontainers.IntegrationResourceIT`.
