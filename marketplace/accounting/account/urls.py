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

from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns
from account import views

urlpatterns = [
    #service selection
    url(r'^accounts/$', views.AccountList.as_view()),
    url(r'^accounts/(?P<pk>[0-9]+)/$', views.AccountDetail.as_view()),
    #billing
    url(r'service-instance-list/$', views.ServiceInstancesByClient.as_view()),
    url(r'vnf-list/$', views.VNFByClient.as_view()),
    url(r'service-billing-model/$', views.ServiceBillingModelByClient.as_view()),
    url(r'vnf-billing-model/$', views.VNFBillingModelByClient.as_view()),
    url(r'^sla/service-violation/$', views.SlaServiceViolations.as_view()),
    url(r'^sla/vnf-violation/$', views.SlaVNFViolations.as_view()),
    url(r'^service-list/$', views.ServiceList.as_view()),
    url(r'^vnf-instance-list/$', views.VnfInstanceList.as_view()),
    #orchestrator
    url(r'^servicestatus/(?P<ns_instance>\w+)/(?P<new_status>\w+)/', views.updateServiceStatus.as_view()),
    #dashboard
    url(r'^sla-info/$', views.SLAInformation.as_view()),
    url(r'^servicelist/(?P<userId>\w+)/', views.DashboardServiceList.as_view()),
    url(r'^vnflist/(?P<userId>\w+)/', views.DashboardVNFList.as_view()),
    #url(r'^vnfstatus/(?P<ns_instance>\w+)/(?P<new_status>\w+)/', views.updateVNFStatus.as_view()),

]

urlpatterns = format_suffix_patterns(urlpatterns)

'''

router.register(r'monitor', views.MonitorViewSet)
router.register(r'client', views.AccountClientIdFiltered)

    url(r'^event', views.BillingEventViewSet.as_view()),
    url(r'^', include(router.urls)),
    #url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework'))
'''
