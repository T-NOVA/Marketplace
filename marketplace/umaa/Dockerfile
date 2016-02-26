FROM python:2.7
MAINTAINER George Alexiou (TEIC)

ENV PYTHONUNBUFFERED 1
WORKDIR /umaa/
ADD requirements.txt /umaa/
RUN pip install -r requirements.txt
ADD . /umaa/

CMD ["/bin/sh","DockerStart.sh"]
