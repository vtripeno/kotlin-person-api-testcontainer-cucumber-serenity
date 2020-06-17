FROM mongo
EXPOSE 27017

WORKDIR /usr/src/data
COPY data.js /docker-entrypoint-initdb.d/