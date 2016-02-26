FROM python:2.7
MAINTAINER George Alexiou (TEIC)

ENV PYTHONUNBUFFERED 1
ADD . /code
WORKDIR /code
RUN pip install -r requirements.txt
CMD python app.py
