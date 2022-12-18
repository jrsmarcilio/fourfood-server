FROM openjdk:8-jdk-alpine

EXPOSE 8080

RUN apk add --no-cache curl tar bash
RUN apk add git 


ARG MAVEN_VERSION=3.3.9
ARG USER_HOME_DIR="/root"


RUN mkdir -p /usr/share/maven && \
curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
ln -s /usr/share/maven/bin/mvn /usr/bin/mvn


ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"


RUN mkdir -p /usr/app


WORKDIR /usr/app
COPY docker-entrypoint.sh .
RUN chmod +x docker-entrypoint.sh


RUN git clone https://github.com/jrsmarcilio/oxefood-marcilio.git
RUN mvn -f  /usr/app/oxefood-marcilio clean package -DskipTests


ENTRYPOINT ["/usr/app/docker-entrypoint.sh"]