FROM gradle:jdk17-alpine as build

ARG VERSION=0.0.1-SNAPSHOT

COPY src/ src
COPY gradle/ gradle
COPY gradlew .
COPY settings.gradle .
COPY build.gradle .

RUN ./gradlew clean assemble test -PprojVersion=$VERSION && \
    mv build/libs/$(./gradlew properties -q | grep '^name:' | awk '{print $2}')-$VERSION.jar build/libs/app.jar

RUN cd build/libs && java -Djarmode=layertools -jar app.jar extract


# ---


FROM openjdk:17

COPY --from=build /home/gradle/build/libs/dependencies/ ./
COPY --from=build /home/gradle/build/libs/spring-boot-loader/ ./
COPY --from=build /home/gradle/build/libs/application/ ./

ENV JAVA_TOOL_OPTIONS=""

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
CMD []
