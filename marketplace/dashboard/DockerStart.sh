#!/bin/sh

#python manage.py bower install -- --allow-root
#python manage.py collectstatic

#python manage.py bower_install -- --allow-root --no-input
python manage.py runserver 0.0.0.0:8000

