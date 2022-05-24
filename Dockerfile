FROM openjdk:17
COPY target/alatoo-news-0.0.1-SNAPSHOT.jar alatoo-news.jar
ENTRYPOINT ["java", "-jar", "/alatoo-news.jar"]