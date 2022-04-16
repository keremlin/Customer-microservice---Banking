FROM openjdk:latest
COPY target/customer-0.0.1-SNAPSHOT.jar customer.jar
ENTRYPOINT [ "java","-jar", "/customer.jar" ]
