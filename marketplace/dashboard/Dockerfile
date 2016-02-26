#FROM nodesource/node:trusty
FROM nodesource/trusty:0.12.0
MAINTAINER George Alexiou (TEIC)

ENV PYTHONUNBUFFERED 1
ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update
RUN apt-get install -y python-pip

RUN npm install -g bower

WORKDIR /dashboard/
ADD requirements.txt /dashboard/
RUN pip install -r requirements.txt
ADD . /dashboard/

#RUN python manage.py bower_install -- --allow-root --no-input
CMD ["/bin/sh","DockerStart.sh"]