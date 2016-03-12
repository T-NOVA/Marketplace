__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

from django.conf.urls import patterns, url

urlpatterns = patterns('dashboard_app.views',
    url(r'^$', 'index'),
    url(r'^vnf-diagram/(?P<vnfd_id>\d+)/$', 'vnf_diagram'),
    url(r'^ns-diagram/(?P<ns_id>\w+)/$', 'ns_diagram'),
                       #url(r'^users/(?P<pk>[0-9]+)/$', UserDetails.as_view(), name='user_details'),
)