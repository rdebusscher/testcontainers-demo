FROM payara/micro:5.2021.1
CMD ["--deploymentDir", "/opt/payara/deployments", "--noCluster", "--contextRoot", "/"]
ADD target/server.war /opt/payara/deployments
