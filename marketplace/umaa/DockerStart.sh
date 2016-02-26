#!/bin/sh

# Wait for database to get available
sleep 55
python manage.py syncdb --noinput
python manage.py update_initial_data
python manage.py runserver 0.0.0.0:8000

