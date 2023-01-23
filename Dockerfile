FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/oxefood-marcilio-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENV JDBC_DATABASE_PASSWORD_PROD "341eb0808bf15ace2a673660e24cabf4799041978afba367a9088158333db42f"
ENV JDBC_DATABASE_URL_PROD "jdbc:postgresql://ec2-52-73-155-171.compute-1.amazonaws.com:5432/d851creg3i6tjr"
ENV JDBC_DATABASE_USERNAME_PROD "jqcnozwfkdegdp"

ENV JDBC_DATABASE_URL "jdbc:postgresql://172.24.0.2:5432/oxefood"
ENV JDBC_DATABASE_PASSWORD "oxefood"
ENV JDBC_DATABASE_USERNAME "postgres"

ENV EMAIL_USERNAME "mgsj@discente.ifpe.edu.br"
ENV EMAIL_PASSWORD "aglfpjhwerqkvgeq"
ENV JPA_HIBERNATE_DLL "create-drop"

ENTRYPOINT ["java","-jar","app.jar"]


# RUN apk add --no-cache curl tar bash
# RUN apk add git 


# ARG MAVEN_VERSION=3.6.3
# ARG USER_HOME_DIR="/root"


# RUN mkdir -p /usr/share/maven && \
# curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
# ln -s /usr/share/maven/bin/mvn /usr/bin/mvn


# ENV MAVEN_HOME /usr/share/maven
# ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
# ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"


# RUN mkdir -p /usr/app


# WORKDIR /usr/app
# COPY docker-entrypoint.sh .
# RUN chmod +x docker-entrypoint.sh


# RUN git clone https://github.com/jrsmarcilio/oxefood-marcilio.git
# RUN mvn -f  /usr/app/oxefood-marcilio clean package -DskipTests


# ENTRYPOINT ["/usr/app/docker-entrypoint.sh"]