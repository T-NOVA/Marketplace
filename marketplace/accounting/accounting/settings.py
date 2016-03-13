"""
Copyright 2015 Atos
Contact: Atos <javier.melian@atos.net>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
"""

"""
Django settings for accounting project.

For more information on this file, see
https://docs.djangoproject.com/en/1.7/topics/settings/

For the full list of settings and their values, see
https://docs.djangoproject.com/en/1.7/ref/settings/
"""

# Build paths inside the project like this: os.path.join(BASE_DIR, ...)
import os
BASE_DIR = os.path.dirname(os.path.dirname(__file__))


# Quick-start development settings - unsuitable for production
# See https://docs.djangoproject.com/en/1.7/howto/deployment/checklist/

# SECURITY WARNING: keep the secret key used in production secret!
SECRET_KEY = '#(j^o+(dmr*ec*a6mh28pr%$ifcpzu*)_1$u3(22=^zmd7tk2p'

# SECURITY WARNING: don't run with debug turned on in production!
DEBUG = True

TEMPLATE_DEBUG = True

ALLOWED_HOSTS = []


# Application definition

INSTALLED_APPS = (
   'django.contrib.admin',
   'django.contrib.auth',
   'django.contrib.contenttypes',
   'django.contrib.sessions',
   'django.contrib.messages',
   'django.contrib.staticfiles',
   'rest_framework',
   #'django_crontab',
   'rest_framework_swagger',
   'django_extensions',
   'account',
)

REST_FRAMEWORK = {
   'DEFAULT_PERMISSON_CLASSES': ('rest_framework.premissions.IsAdminUser',),
   'DEFAULT_FILTER_BACKENDS': ('rest_framework.filters.DjangoFilterBackend',),
   'PAGINATE_BY': 10
}

MIDDLEWARE_CLASSES = (
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.common.CommonMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.auth.middleware.SessionAuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'django.middleware.clickjacking.XFrameOptionsMiddleware',
)

ROOT_URLCONF = 'accounting.urls'

WSGI_APPLICATION = 'accounting.wsgi.application'


# Database
# https://docs.djangoproject.com/en/1.7/ref/settings/#databases

DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': 'accounting',
        'USER': 'accounting_usr',
        'PASSWORD': 'accounting_usr',
        #'HOST': 'localhost',   # Or an IP Address that your DB is hosted on
        'HOST': 'mysql',   # Or an IP Address that your DB is hosted on
        'PORT': '3306',
    }
}

# Internationalization
# https://docs.djangoproject.com/en/1.7/topics/i18n/

LANGUAGE_CODE = 'en-us'

TIME_ZONE = 'UTC'

USE_I18N = True

USE_L10N = True

USE_TZ = True


# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/1.7/howto/static-files/

STATIC_URL = '/static/'

CRONJOBS = [
   ('* * * * *', 'accounting.account.eventChecker2.eventDetection', {'verbose': 1})
]

CORS_ORIGIN_ALLOW_ALL = True

#RabbitMQ connection params
AMQP_HOST = 'cyclops.sisyphus.mnl'
AMQP_PORT = 5672
AMQP_USER = 'guest'
AMQP_PASSWORD = 'guest'
AMQP_EXCHANGE_NAME = 'tnova'
AMQP_ROUTING_KEY = 'event'
AMQP_VHOST = '/'

SLA_URL = 'http://sla.docker:9040'
#SLA_URL = 'http://localhost:9040'

#constant to define a service or a VNF
NETWORK_SERVICE = 'ns'
VNF = 'vnf'

#Possible status of an instance
STATUS_RUNNING = 'running'
STATUS_STOPPED = 'stopped'



TEMPLATE_LOADERS = (
    'django.template.loaders.eggs.Loader',
)


