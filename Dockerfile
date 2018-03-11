FROM openjdk:8-jdk-alpine


ADD target/blog-sample-0.0.1-SNAPSHOT.jar blog-sample-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/blog-sample-0.0.1-SNAPSHOT.jar"]