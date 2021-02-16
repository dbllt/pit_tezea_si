FROM openjdk:11 AS build

COPY ./pom.xml ./pom.xml
COPY ./mvnw ./mvnw
COPY ./.mvn ./.mvn
RUN ./mvnw dependency:resolve

COPY ./src ./src
RUN ./mvnw install -Dmaven.test.skip=true


FROM openjdk:11-jre-slim AS runtime

RUN apt-get -q update && apt-get -qy install netcat
COPY ./wait-for ./wait-for
RUN chmod +x ./wait-for

COPY --from=build target/si-0.0.1-SNAPSHOT.jar ./target/si-0.0.1-SNAPSHOT.jar

COPY ./entrypoint.sh ./entrypoint.sh
RUN chmod +x ./entrypoint.sh

CMD ["./entrypoint.sh"]
