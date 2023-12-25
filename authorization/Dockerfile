FROM openjdk:21-jdk
LABEL authors="IceblueSakura"
COPY ./build/libs/authorization-0.0.1-SNAPSHOT.jar /app/authorization.jar
EXPOSE 8080/tcp
EXPOSE 5005/tcp
#ENTRYPOINT ["java", "-b"]
CMD java -agentlib:jdwp=transport=dt_socket,server=y,address=5005,suspend=n -jar /app/authorization.jar