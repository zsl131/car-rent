FROM java:8
EXPOSE 7771

VOLUME /web/car-rent/upload/

ADD run.sh /run.sh

RUN chmod +x /run.sh

ADD car-rent-*.jar /car-rent.jar

ENTRYPOINT sh /run.sh