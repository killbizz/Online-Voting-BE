FROM openjdk:11
EXPOSE 8080
COPY target/online-voting-0.0.1-SNAPSHOT.jar keystore.p12 ./
RUN ["apt-get", "update"]
RUN ["apt-get", "install", "-y", "nano"]
ENTRYPOINT ["java", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=72m", "-jar", "./online-voting-0.0.1-SNAPSHOT.jar"]