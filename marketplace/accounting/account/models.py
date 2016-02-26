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

from django.db import models
from django.utils import timezone
from datetime import datetime


# Create your models here.


class Account(models.Model):

   #Id of the VNF or the Service in the system
   productId = models.CharField(max_length=256, default=None, blank=False) 
   #Id of the VNF or the Service instance in the Orchestrator
   instanceId = models.CharField(max_length=256, default=None, blank=False) 
   #Id of the agreement in the SLA module
   agreementId = models.CharField(max_length=256, null=False, default=None, blank=False) 
   #Id of the parent service (null if it's a Service parent already)
   relatives = models.CharField(max_length=256, null=True, default=None, blank=False) 
   #vnf|service
   productType = models.CharField(max_length=10, default=None, blank=False) 
   #Name of the flavour of the product
   flavour = models.CharField(max_length=256, default=None, blank=False) 
   #Date of the service start
   #startDate = models.DateTimeField(default=datetime.utcnow(), blank=False) 
   startDate = models.DateTimeField(auto_now_add=True)
   #date of the last issued bill
   #lastBillDate = models.DateTimeField(default=datetime.utcnow(), blank=False)
   lastBillDate = models.DateTimeField(auto_now_add=True)
   #Id of the seller
   providerId = models.CharField(max_length=256, null=False, default=None, blank=False) 
   #Id of the buyer
   clientId = models.CharField(max_length=256, null=False, default=None, blank=False) 
   #status of the service: running, paused, stopped...
   status = models.CharField(max_length=10, default=None) 
   billingModel = models.CharField(max_length=10, default=None) 
   period = models.CharField(max_length=10, blank=True, default=None) 
   priceUnit = models.CharField(max_length=3, blank=True, default=None) 
   periodCost = models.FloatField(blank=False, default=None) 
   setupCost = models.FloatField(blank=False, default=None) 
   renew = models.BooleanField(default=True) 
   
   dateCreated = models.DateTimeField(auto_now_add=True)
   dateModified = models.DateTimeField(auto_now=True)
   
   def __unicode__(self): 
      return 'Service %s (%s)' % (self.productId, self.period) 



class Monitor(models.Model):

   serviceId = models.CharField(max_length=256, null=False) 
   metricName = models.CharField(max_length=256, null=False) 
   value = models.FloatField(null=False) 
   date = models.DateTimeField(default=timezone.now) 
   
   def __unicode__(self): 
      return 'Service %s: Monitoring %s (%d on %s)' % (self.serviceId, self.metricName, self.value, str(self.date)) 


class BillingEvent(models.Model):

   agreementId = models.CharField(max_length=26, null=False) 
   productId = models.CharField(max_length=26, null=False) 
   productType = models.CharField(max_length=26, null=False) 
   eventType = models.CharField(max_length=26, null=False) 
   clientId = models.CharField(max_length=26, null=False) 
   providerId = models.CharField(max_length=26, null=False) 
   date = models.DateTimeField(default=None) 
   
   def __unicode__(self): 
      return 's% %s: (Client: %s - Provider: %s) Event: %s, date: %s' % (self.productType, self.productId, self.clientId, self.providerId, self.eventType, str(self.date)) 


class SLAInfo(models.Model):

   productId = models.CharField(max_length=26, null=False) 
   agreementId = models.CharField(max_length=26, null=False) 
   productType = models.CharField(max_length=26, null=False) 
   clientId = models.CharField(max_length=26, null=False) 
   providerId = models.CharField(max_length=26, null=False) 
   SLAPenalties = models.IntegerField(null=False, default=0)
   dateCreated = models.DateTimeField(default=None) 
   dateTerminated= models.DateTimeField(default=None) 
   
   def __unicode__(self): 
      return 's% %s: (Client: %s - Provider: %s) Penalties: %d, date: %s' % (self.productType, self.productId, self.clientId, self.providerId, self.SLAPenalties, str(self.dateCreated)) 


class SlaViolation(models.Model):

   uuid = models.CharField(max_length=256, null=False) 
   #datetime = models.CharField(max_length=256, null=True) 
   #datetime = models.DateTimeField(default=None, null=True) 
   actualValue = models.FloatField(null=False) 
   kpiName = models.CharField(max_length=26, null=False) 
   #contractUuid = models.CharField(max_length=26, null=False) 
   

class SlaPenalty(models.Model):

   uuid = models.CharField(max_length=256, null=False) 
   datetime = models.CharField(max_length=256, null=True) 
   #datetime = models.DateTimeField(default=None, null=True) 
   agreementId = models.CharField(max_length=26, null=False) 
