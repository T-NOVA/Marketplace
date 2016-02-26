#!/usr/bin/env python
import pika

credentials = pika.PlainCredentials('acc_module', 'j7yunyBQ')
parameters = pika.ConnectionParameters('messaging.demonstrator.info', 5672, '/', credentials)
connection = pika.BlockingConnection(parameters)
channel = connection.channel()
#channel.queue_declare(queue='event', type='topic')
#channel.exchange_declare(exchange='accounting', type='topic')
channel.basic_publish(exchange='tnova', routing_key='event', body='{"instanceId": "id06", "event": "START"}')
connection.close()
