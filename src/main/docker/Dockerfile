FROM java:8
EXPOSE 7771

RUN mkdir -p /usr/upload/

VOLUME /usr/upload/

ADD run.sh /run.sh

RUN chmod +x /run.sh

ADD car-rent-*.jar /car-rent.jar

ENTRYPOINT sh /run.sh