FROM  maven:3.3.9-jdk-7

MAINTAINER  Anthony K GROSS

COPY src /src
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
CMD ["run"]