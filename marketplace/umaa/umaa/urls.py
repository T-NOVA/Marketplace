__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

from django.conf.urls import patterns, include, url

urlpatterns = patterns('',

    url(r'^user-management/', include('users.urls')),
    url(r'^auth/', 'rest_framework_jwt.views.obtain_jwt_token'),
)
