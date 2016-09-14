from django.conf.urls import include, url

urlpatterns = [
    url(r'^', include('account.urls')),
    #url(r'^docs/', include('rest_framework_swagger.urls')),
]

