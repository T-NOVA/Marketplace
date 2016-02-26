__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

from django.contrib.auth.models import AbstractUser
from django_countries.fields import CountryField
from django.db import models


class User(AbstractUser):

    class Meta:
        db_table = 'auth_user'

    company_name = models.CharField(max_length=256, blank=True)
    country = CountryField(default='GR', blank=True)
    city = models.CharField(max_length=100, blank=True)
    address = models.CharField(max_length=100, blank=True)
