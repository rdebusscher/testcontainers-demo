FROM payara/micro:5.2021.1
ARG MYSQL_CONN
ENV MYSQL_CONN=$MYSQL_CONN
CMD ["--deploymentDir", "/opt/payara/deployments", "--postbootcommandfile", "/opt/payara/postboot", "--addLibs", "/opt/payara/mysql-connector-java-5.1.39.jar","--noCluster", "--contextRoot", "/"]
ADD src/main/docker/mysql-connector-java-5.1.39.jar /opt/payara
ADD src/main/docker/postboot /opt/payara
ADD target/plain.war /opt/payara/deployments
