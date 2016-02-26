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

from account.models  import Account, Monitor, BillingEvent, SLAInfo, SlaViolation, SlaPenalty
from rest_framework import serializers


class AccountSerializer(serializers.ModelSerializer):
   class Meta:
      model = Account
      fields = ('id', 'instanceId', 'productId', 'agreementId', 'relatives', 'productType', 'flavour', 'startDate', 'lastBillDate', 'providerId', 'clientId', 'status', 'billingModel', 'period', 'priceUnit', 'periodCost', 'setupCost', 'renew', 'dateCreated', 'dateModified')
      read_only_fields = ('dateCreated', )

class AccountBillSerializer(serializers.ModelSerializer):
   class Meta:
      model = Account
      fields = ('startDate', 'lastBillDate', 'billingModel', 'period', 'priceUnit', 'periodCost', 'setupCost')
      read_only_fields = ('dateCreated', )


class AccountInstanceListSerializer(serializers.ModelSerializer):
   class Meta:
      model = Account
      fields = ('instanceId', 'productType')


class BillingEventSerializer(serializers.ModelSerializer):
   class Meta:
      model = BillingEvent
      fields = ('id', 'agreementId', 'productId', 'productType', 'eventType', 'clientId', 'providerId', 'date')
      #read_only_fields = ('date', )

class SLAInfoSerializer(serializers.ModelSerializer):
   class Meta:
      model = SLAInfo
      fields = ('id', 'productId', 'productType', 'clientId', 'providerId', 'SLAPenalties', 'agreementId', 'dateCreated', 'dateTerminated')
      #read_only_fields = ('date', )

class SlaViolationSerializer(serializers.ModelSerializer):
   class Meta:
      model = SlaViolation
      fields = ('uuid', 'actualValue', 'kpiName', 'datetime', 'contractUuid')

class SlaPenaltySerializer(serializers.ModelSerializer):
   class Meta:
      model = SlaPenalty
      violation = SlaViolationSerializer(many=False)
      fields = ('uuid', 'datetime', 'agreementId', 'violation')


class MonitorSerializer(serializers.ModelSerializer):
   class Meta:
      model = Monitor
      fields = ('id', 'serviceId', 'metricName', 'value', 'date')
      read_only_fields = ('date', )

