# syntax = docker/dockerfile:1.1-experimental
FROM maven:3.5-jdk-11-slim AS build-env
ADD . /work
WORKDIR /work
RUN --mount=type=cache,target=/root/.m2 mvn clean package

FROM openjdk:11-jre-slim
WORKDIR  /usr/local/ktor-oom/
COPY --from=build-env /work/target/ktor-oom-0.0.1-jar-with-dependencies.jar .
ADD https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.13.0/jmx_prometheus_javaagent-0.13.0.jar .
RUN touch config.yaml
CMD ["java", "-Xmx1g", "-XX:+ExitOnOutOfMemoryError", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/dump", "-javaagent:./jmx_prometheus_javaagent-0.13.0.jar=9100:config.yaml", "-jar", "/usr/local/ktor-oom/ktor-oom-0.0.1-jar-with-dependencies.jar"]
