import pika
import logging

logging.basicConfig(level=logging.DEBUG)

credentials = pika.PlainCredentials('acc_module', 'j7yunyBQ')
connection = pika.BlockingConnection(pika.ConnectionParameters(host='messaging.demonstrator.info', port=5672, virtual_host='/', credentials=credentials,socket_timeout=1))
channel = connection.channel()
channel.exchange_declare(exchange="tnova", exchange_type="topic")

print("Sending message to create a queue")
channel.basic_publish('tnova', 'event', 'queue:event', pika.BasicProperties(content_type='text/plain', delivery_mode=1))

connection.sleep(5)

print("Sending text message")
channel.basic_publish('tnova', 'event', 'TNOVA hello world', pika.BasicProperties(content_type='text/plain', delivery_mode=1))

connection.close()
