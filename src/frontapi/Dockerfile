FROM debian:stable-slim AS builder

FROM gcr.io/distroless/base-debian10

EXPOSE 8080
WORKDIR /app
COPY build/image /app
COPY --from=builder /lib/x86_64-linux-gnu/libz.so.1 /lib/x86_64-linux-gnu/libz.so.1
ENTRYPOINT ["/app/bin/java"]
CMD ["-Xmx128M", "-XX:+FlightRecorder", "-XX:+PrintFlagsFinal", "-classpath", "/app/lib/frontapi-0.0.1-SNAPSHOT.jar", "org.springframework.boot.loader.JarLauncher"]