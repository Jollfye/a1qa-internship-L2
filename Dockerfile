FROM mysql:debian
RUN apt-get update \
 && apt-get install -y git
RUN git clone https://github.com/a1qatraineeship/db_task_dump /docker-entrypoint-initdb.d/