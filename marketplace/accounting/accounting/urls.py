from django.conf.urls import patterns, include, url

urlpatterns = patterns('',
    url(r'^', include('account.urls')),
    url(r'^docs/', include('rest_framework_swagger.urls')),
)

