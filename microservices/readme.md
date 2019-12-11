# Example of testing integration between Microservices

## Overview

This demo contains 2 projects, a `server` project containing an endpoint, hello World style, and a `client` project which contains a service calling the endpoint in the server project.

## Server

This project doesn't contain any tests, but of course it can be added. The main points of interest are

- the `pom.xml` contains only MicroProfile dependency
- `pom.xml` contains the fabric8 docker-maven-plugin to build a docker image from this project.
- `Dockerfile` contains the Docker script to generate the Docker image for the _server_ project (used by fabric8 docker-maven-plugin)

perform a `mvn clean package` to build the project and generate the Docker Image.

## Client

This project is using JUnit 5, MicroProfile testing and Testcontainers.

The goals of the frameworks are

- Testcontainers: easily starting multiple programs by using Docker
- MicroProfile Testing: Easily test JAX-RS endpoints (and MicroProfile Functionality)
- JUnit 5: Easier to build extensions for JUnit.

The microservice itself just has an endpoint (_/integration_) which calls the other endpoint within _server_ project and returns the result.
It uses MP Rest client to call the other Service (but that is not a requirement to make this demo work) and the URL of the endpoint is configured in `microprofile-config.properties` (see the especially the hostname which is defined there).

### Requirements

#### pom.xml

- MicroProfile dependency
- MicroProfile testing dependency (including the support for Payara Micro) which is under the _MP microshed_ umbrella 
- JUnit5 dependency and assertJ for fluent assertions
- Failsafe maven plugin to run the integration test

#### Test class

- Indicate execution with the Microshed extension (`@MicroShedTest`)
- Define the containers with the applications. In this case a generic Container running the Docker image of the _server_ project and a container with our application under test.
- Important here is to define the host name we expect for the service we are calling. (`.withNetworkAliases("otherService")`)
- Within the test method (correctly annotated with the JUnit @Test variant), you can write the code to perform your testing. Here we use te MicroProfile testing feature of 'injecting' the JAX-RS resource and call it (the call is actually remote and not local !)

The use of the Injected JAX-RS is not required, just as an example for easy testing. The test method can perform code you like.

  
You can perform a `mvn clean verify` to build the project and execute the test.