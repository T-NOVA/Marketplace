__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

"""
Django settings for dashboard project.

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
SECRET_KEY = 'z@_#=cglk9@(aqz+u_)(!&82^v*ag2+^uxdgpx7ncu#8i2mp3b'

# SECURITY WARNING: don't run with debug turned on in production!
DEBUG = True
TEMPLATE_DEBUG = True

ALLOWED_HOSTS = []


# Application definition

INSTALLED_APPS = (
    'django.contrib.staticfiles',
    'djangobower',
    'dashboard_app',
)

MIDDLEWARE_CLASSES = (
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.common.CommonMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.auth.middleware.SessionAuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'django.middleware.clickjacking.XFrameOptionsMiddleware',
)

STATICFILES_FINDERS = (
    'django.contrib.staticfiles.finders.FileSystemFinder',
    'django.contrib.staticfiles.finders.AppDirectoriesFinder',
    'djangobower.finders.BowerFinder'
)

ROOT_URLCONF = 'dashboard.urls'
WSGI_APPLICATION = 'dashboard.wsgi.application'

# Internationalization
# https://docs.djangoproject.com/en/1.7/topics/i18n/

LANGUAGE_CODE = 'en-us'
TIME_ZONE = 'UTC'
USE_I18N = True
USE_L10N = True
USE_TZ = True


# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/1.7/howto/static-files/
STATIC_ROOT = os.path.join(BASE_DIR,  'static')
STATIC_URL = '/static/'

TEMPLATE_DIRS = (
    os.path.join(BASE_DIR,  'templates'),
)

BOWER_COMPONENTS_ROOT = os.path.join(BASE_DIR,  'components')
BOWER_PATH = '/usr/bin/bower'

BOWER_INSTALLED_APPS = (
    'angular#1.4',
    'angular-ui-router',
    'angular-cookies',
    'restangular',
    'lodash#3.10.1',
    'angular-animate',
    #'angular-strap',
    'angular-bootstrap',
    #'angular-dialog-service',
    'angular-modal-service',
    'angular-sanitize',
    'bootstrap',
    'angular-bootstrap-checkbox',
    'angular-motion',
    'angular-ui-select',
    'angular-rangeslider',
    'angular-ui-codemirror',
    'ng-file-upload',
    'roboto-fontface',
    'font-awesome',
    'jsplumb',
    'highcharts',
    'highcharts-ng',
    'normalize-css'
)