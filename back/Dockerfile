FROM openjdk:11 AS build

COPY ./pom.xml ./pom.xml
COPY ./src ./src
COPY ./volume_share/META-INF ./src/main/resources/META-INF
COPY ./mvnw ./mvnw
COPY ./.mvn ./.mvn

RUN ./mvnw install


FROM openjdk:11-jre-slim AS runtime

RUN apt-get -q update && apt-get -qy install netcat
COPY ./wait-for ./wait-for
RUN chmod +x ./wait-for

COPY --from=build target/si-0.0.1-SNAPSHOT.jar ./target/si-0.0.1-SNAPSHOT.jar

COPY ./entrypoint.sh ./entrypoint.sh
RUN chmod +x ./entrypoint.sh

CMD ["./entrypoint.sh"]
