FROM openjdk:11-jre-slim
RUN useradd -ms /bin/bash springuser
USER springuser
WORKDIR /home/springuser
ADD ./target/blog-0.0.1-SNAPSHOT.jar blog-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "blog-0.0.1-SNAPSHOT.jar"]
