FROM anapsix/alpine-java:8_server-jre_unlimited
VOLUME /tmp
ENV LANG en_GB.UTF-8
ADD ./target/influxdb-java-v1.0.jar influxdb-java.jar
ENTRYPOINT ["java","-jar","influxdb-java.jar"]