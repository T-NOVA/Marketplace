#!/usr/bin/python
# -*- coding: utf-8 -*-

import pika
import unicodedata

def send_msg(exch, key, msg):
    '''
        This is the rabbitmq sender module.
        Arguments: exchange name, routing key, and message payload.
    '''
    connection = None
    try:
        credentials = pika.PlainCredentials('acc_module', 'j7yunyBQ')
        connection = pika.BlockingConnection(pika.ConnectionParameters(host='messaging.demonstrator.info', port=5672, virtual_host='/', credentials=credentials,socket_timeout=1))
        channel = connection.channel()
        channel.basic_publish(exchange=exch, routing_key=key, body=msg)
        print " [x] Sent: %s" % msg
    except KeyboardInterrupt:
        print "  [CTRL+C] Interrupt received. Will terminate the sending process now."
    except Exception:
        print "  [err] Caught exception in the code"
        raise
    finally:
        if connection != None:
            connection.close()

if __name__ == '__main__':
    exchange_name = 'tnova'
    routing_key = 'event'
    send_msg(exchange_name, routing_key, 'TNOVA hello world')
    
