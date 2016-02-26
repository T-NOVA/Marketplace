__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

from django.conf.urls import patterns, include, url


urlpatterns = patterns('',
    url(r'^dashboard/', include('dashboard_app.urls')),
)
