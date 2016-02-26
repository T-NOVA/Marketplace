__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

from django.conf.urls import patterns, url

urlpatterns = patterns('dashboard_app.views',
    url(r'^$', 'index'),
    url(r'^vnf-diagram/(?P<vnfd_id>[0-9]+)/$', 'vnf_diagram'),
                       #url(r'^users/(?P<pk>[0-9]+)/$', UserDetails.as_view(), name='user_details'),
)