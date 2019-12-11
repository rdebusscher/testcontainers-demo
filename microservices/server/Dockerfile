FROM payara/micro:5.193
CMD ["--deploymentDir", "/opt/payara/deployments", "--noCluster", "--contextRoot", "/"]
ADD target/server.war /opt/payara/deployments
