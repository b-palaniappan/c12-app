# build application using gradle docker image
FROM gradle:jdk15 as builder
# Create and set work directory
WORKDIR /app
# Copy project to work directory
COPY . /app
# Build application
RUN gradle --no-daemon --parallel clean build

FROM adoptopenjdk:15-jre-hotspot as runner
# Create and set work directory
WORKDIR /app
# add local application user
RUN groupadd -r appuser && useradd --no-log-init -m -r -u 1001 -g appuser appuser
# switch user to appuser
USER appuser
# Copy application from gradle builder
COPY --chown=appuser:appuser --from=builder /app/build/libs/c12-app-0.0.1.jar /app/application.jar
# run application
CMD ["java", "-jar", "application.jar"]