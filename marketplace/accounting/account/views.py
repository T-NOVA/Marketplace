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

#from django.shortcuts import render
from account.models import Account, Monitor, BillingEvent, SLAInfo, SlaViolation
from account.serializers import AccountSerializer, AccountBillSerializer, AccountInstanceListSerializer, MonitorSerializer, BillingEventSerializer, SLAInfoSerializer, SlaViolationSerializer
import datetime, collections
from django.conf import settings
from rest_framework.views import APIView
from django.http import Http404
from rest_framework.response import Response
from rest_framework import status
#import eventChecker2
from slaclient import restclient 
import json, time

#for the RabbitMQ handling
import pika

# Create your views here.


AMQP_EXCHANGE_NAME = 'tnova'
AMQP_ROUTING_KEY = 'event'
AMQP_USER = 'acc_module'
AMQP_PASSWORD = 'j7yunyBQ'
AMQP_HOST = 'messaging.demonstrator.info'
PORT = 5672
AMQP_VHOST = '/'
SLA_URL = "http://localhost:9040"
#SLA_URL = "http://sla:9040"

class send_msg(object):
    '''
    This is the rabbitmq sender module.
    Arguments: exchange name, routing key, and message payload.
    '''

    def __init__(self, msg):
        connection = None
        try:
            credentials = pika.PlainCredentials(settings.AMQP_USER, settings.AMQP_PASSWORD)
            connection = pika.BlockingConnection(pika.ConnectionParameters(host=settings.AMQP_HOST, port=PORT, virtual_host=settings.AMQP_VHOST, credentials=credentials))
            channel = connection.channel()
            channel.basic_publish(exchange=settings.AMQP_EXCHANGE_NAME, routing_key=settings.AMQP_ROUTING_KEY, body=msg)
            print " [x] Sent: %s" % msg
        except KeyboardInterrupt:
            print "  [CTRL+C] Interrupt received. Will terminate the sending process now."
        except Exception:
            print "  [ERROR] Caught exception in the code"
            raise ReferenceError
        finally:
            if connection != None:
                connection.close()

class createAgreement(object):
    '''
    Arguments:
        templateId: Id of the template where the new agreement is going to be based on.
        clientId: Id of the customer to update the agreementInitiator.
        agreementId: Id of the new agreement. 
    '''

    def readTemplate(object, templateId):
        f = restclient.Factory(settings.SLA_URL)
        a_template = f.templates()
        return a_template.getbyid(templateId)[0] 

    def postAgreement(object, agreement):
        f = restclient.Factory(settings.SLA_URL)
        a_agreement = f.agreements()
        a_agreement.create(json.dumps(agreement)) 

    def __init__(self, templateId, clientId, agreementId):
        agreement = self.readTemplate(templateId)
        agreement['context']['agreementInitiator'] = clientId
        #agreement['context']['agreementResponder'] = clientId
        agreement['agreementId'] = agreementId
        #eliminate fields that are in the Template but not in the Agreement
        try: 
            del agreement['templateId']
            for el1 in agreement['terms']['allTerms']['guaranteeTerms']:
                for el2 in el1['businessValueList']['customBusinessValue']:
                    del el2['duration']
        except Exception as e:
            print "DEBUG: ", e
        #print "AGREEMENT: ", json.dumps(agreement)
        self.postAgreement(agreement)


class AccountList(APIView):
    """
    List all account entries, or create a new one.
    """
    def get(self, request, format=None):
        """
        ---
        response_serializer: AccountSerializer
        """
        accounts = Account.objects.all()
        serializer = AccountSerializer(accounts, many=True)

        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = AccountSerializer(data=request.data)
        if serializer.is_valid():
            try:
                if Account.objects.filter(productType=request.data['productType'], instanceId=request.data['instanceId']):
                    print ("  [ERROR] There is an entry already with that InstanceId (%s) for the same ProductType (%s)" % (request.data['instanceId'], request.data['productType']))
                    return Response(status=status.HTTP_400_BAD_REQUEST)
                serializer.save()
                #prepares and sends the message to the queue
                message = {}
                message['instanceId'] = request.data['instanceId']
                message['event'] = request.data['status']
                #send_msg(str(message))
                send_msg(json.dumps(serializer.data))

                #create the SLA agreements in the SLA module based on the already created templates
                templateId = request.data['productType']+request.data['productId']+request.data['flavour']
                createAgreement(templateId, request.data['clientId'], request.data['agreementId'])
                #start the Agreement enforcement
                f = restclient.Factory(settings.SLA_URL)
                a_enforcement = f.enforcements()
                a_enforcement.start(request.data['agreementId']) 

            except ReferenceError:
                print "  [ERROR] Could not update the messages queue"
                return Response(status=status.HTTP_408_REQUEST_TIMEOUT)
            except Exception as e:
                print "DEBUG: ", e
                return Response(status=status.HTTP_400_BAD_REQUEST)
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class AccountDetail(APIView):
    """
    Retrieve, update or delete an Accounting instance.
    """
    def get_object(self, pk):
        try:
            return Account.objects.get(pk=pk)
        except Account.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        account = self.get_object(pk)
        serializer = AccountSerializer(account)
        return Response(serializer.data)

    def put(self, request, pk, format=None):    
        account = self.get_object(pk)
        serializer = AccountSerializer(account, data=request.data)
        print "request: ", request.data
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        account = self.get_object(pk)
        account.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class ServiceInstancesByClient(APIView):
    """
    2-API endpoint that list of all active services the client is using.  
        /service-instance-list/?clientId=c1
    """
    def get(self, request, format=None):
        """
        ---
        parameters:
            - name: clientId
              description: Client ID
              type: string
              paramType: form
        serializer: AccountSerializer
        """
        if self.request.query_params:
            clientId = self.request.query_params.get('clientId', None)
            if clientId:
                queryset = Account.objects.filter(status=settings.STATUS_RUNNING, productType=settings.NETWORK_SERVICE, clientId=clientId)
            else:
                return Response(status=status.HTTP_400_BAD_REQUEST)
        else:
            queryset = Account.objects.filter(status=settings.STATUS_RUNNING, productType=settings.NETWORK_SERVICE)
        serializer = AccountSerializer(queryset, many=True)
        return Response(serializer.data)


class VNFByClient(APIView):
    """
    4- API endpoint that lists all VNFs purchased by a particular provider (client)
      /vnf-list/?clientId=p1
    """
    def get(self, request, format=None):
        """
        ---
        parameters:
            - name: clientId
              description: Client ID
              type: string
              paramType: form
        serializer: AccountSerializer
        """
        clientId = self.request.query_params.get('clientId', None)
        queryset = Account.objects.filter(productType=settings.VNF, clientId=clientId)
        serializer = AccountSerializer(queryset, many=True)
        return Response(serializer.data)


class VNFBillingModelByClient(APIView):
    """
    5- API endpoint that gives details of the revenue sharing model between SP and FP for the given VNF instance
        /vnf-billing-model/?spId=p1&instanceId=vnfid1
    """
    def get(self, request, format=None):
        """
        ---
        parameters:
            - name: spId
              description: service provider ID
              type: string
              paramType: form
            - name: instanceId
              description: vnf instance ID
              type: string
              paramType: form
        serializer: AccountBillSerializer
        """
        clientId = self.request.query_params.get('spId', None)
        instanceId = self.request.query_params.get('instanceId', None)
        queryset = Account.objects.filter(productType=settings.VNF, clientId=clientId, instanceId=instanceId)
        serializer = AccountBillSerializer(queryset, many=True)
        return Response(serializer.data)


class ServiceBillingModelByClient(APIView): 
    """
    1- API endpoint that gives details about the user's chosen billing model and specs. 
    for the queried service instance id, must have billing cycle start date (for subscription model)* 
        /service-billing-model/?clientId=c1&instanceId=s1
    """
    def get(self, request, format=None):
        """
        ---
        parameters:
            - name: clientId
              description: Client ID
              type: string
              paramType: form
            - name: instanceId
              description: Service instance ID
              type: string
              paramType: form
        serializer: AccountBillSerializer
        """
        clientId = self.request.query_params.get('clientId', None)
        instanceId = self.request.query_params.get('instanceId', None)
        queryset = Account.objects.filter(productType=settings.NETWORK_SERVICE, clientId=clientId, instanceId=instanceId)
        if queryset:
            queryset = queryset[0]
        serializer = AccountBillSerializer(queryset, many=False)
        return Response(serializer.data)


class SlaVNFViolations(APIView):
    """
    7- API endpoint that returns the list of all SLA violations for a given VNF instance for the queried time window
        /sla/vnf-violation/{?instanceId=id02&metric=drops_per_sec}

    instanceId -- VNF instance ID
    metric -- Metric name
    """

    def get(self, request, format=None):
        """
        ---
        parameters:
            - name: instanceId
              description: VNF instance ID
              type: string
              paramType: form
            - name: metric
              description: Metric name
              type: string
              paramType: form
        """
        f = restclient.Factory(settings.SLA_URL)
        instanceId = self.request.query_params.get('instanceId', None)
        agreementId = 'unknown'
        if instanceId is not None:
            print(instanceId)
            #find all the service running instances with the given Service instance ID
            queryset = Account.objects.filter(instanceId=instanceId, productType=settings.VNF)#, status='running')
            if queryset:
                for obj in queryset:
                    agreementId = obj.agreementId
        metric = self.request.query_params.get('metric', None)
        start = self.request.query_params.get('start', None)
        end = self.request.query_params.get('end', None)
         
        print ('agreementID: %s, metric: %s, start: %s, end: %s' % (agreementId, metric, start, end))
        a_client = f.penalties()
        penalties = a_client.getbyagreementTermAndDates(agreementId, metric, start, end)[0]
        return Response(penalties)


class SlaServiceViolations(APIView):
    """
    6- API endpoint that returns the list of all SLA violations for a given servicie instance for the queried time window
        /sla/service-violation/{?instanceId=id02&metric=drops_per_sec}
    """

    def get(self, request, format=None):
        """
        ---
        parameters:
            - name: instanceId
              description: Service instance ID
              type: string
              paramType: form
            - name: metric
              description: Metric name
              type: string
              paramType: form
        """
        print "debug: ", settings.SLA_URL
        f = restclient.Factory(settings.SLA_URL)
        instanceId = self.request.query_params.get('instanceId', None)
        agreementId = 'unknown'
        if instanceId is not None:
            print(instanceId)
            #find all the service running instances with the given Service instance ID
            queryset = Account.objects.filter(instanceId=instanceId, productType=settings.NETWORK_SERVICE)#, status='running')
            if queryset:
                for obj in queryset:
                    agreementId = obj.agreementId
        metric = self.request.query_params.get('metric', None)
        start = self.request.query_params.get('start', None)
        end = self.request.query_params.get('end', None)
         
        print ('agreementID: %s, metric: %s, start: %s, end: %s' % (agreementId, metric, start, end))
        a_client = f.penalties()
        penalties = a_client.getbyagreementTermAndDates(agreementId, metric, start, end)[0]
        #print (violations)
        return Response(penalties)


class ServiceList(APIView):
    """
    8- API endpoint that returns the list of running services that use an specified VNF
        /service-list/?vnfId=vnf1
    """

    def get(self, request, format=None):
        """
        ---
        parameters:
            - name: vnfId
              description: VNF ID
              type: string
              paramType: form
        serializer: AccountSerializer
        """
        if self.request.query_params:
            vnfId = self.request.query_params.get('vnfId', None)
            if vnfId is not None:
                #find all the VNF running instances with the given VNF ID
                queryset = Account.objects.filter(productId=vnfId, productType=settings.VNF, status=settings.STATUS_RUNNING)
                serviceListing = list()
                try:
                    for obj in queryset:
                        if (obj.relatives) is not None:
                            #find all the running services that use that VNF
                            serviceListing.append(Account.objects.filter(status=settings.STATUS_RUNNING, productType=settings.NETWORK_SERVICE, instanceId=obj.relatives)[0])
                    #remove duplicates
                    serviceListing= sorted(set(serviceListing))
                    serializer = AccountSerializer(serviceListing, many=True)
                    print "vnfid: ", vnfId
                    return Response(serializer.data)
                except Exception:
                    print " [ERROR] Database malformed: the service does not exist."
                    #raise
                    return Response(status=status.HTTP_400_BAD_REQUEST)
            else:
                return Response(status=status.HTTP_400_BAD_REQUEST)
                
        serializer = AccountSerializer(Account.objects.filter(productType=settings.VNF, status=settings.STATUS_RUNNING), many=True)
        return Response(serializer.data)#, status=status.HTTP_400_BAD_REQUEST)


class VnfInstanceList(APIView):
    """
    9- API endpoint that returns the list of VNF instance IDs that belong to a given service instanceID
        /vnf-instance-list/?sInstanceId=s1
    """

    def get(self, request, format=None):
        """
        ---
        parameters:
            - name: sInstanceId
              description: Service instance ID
              type: string
              paramType: form
        serializer: AccountInstanceListSerializer
        """
        sInstanceId = self.request.query_params.get('sInstanceId', None)
        if sInstanceId is not None:
            #get the entry that corresponds to that service instance ID
            queryset = Account.objects.filter(instanceId=sInstanceId, productType=settings.NETWORK_SERVICE)
            if queryset:
                resultsList = list()
                try:
                    vnfList = queryset[0].relatives.replace(" ", "").split(",")
                    for vnf in vnfList:
                        #find all the VNFs in the list
                        resultsList.append(Account.objects.filter(productType=settings.VNF, instanceId=vnf)[0])
                except Exception as e:
                    #print "DEBUG: ", e
                    print " [ERROR] Database malformed: the service does not contain any functions."
                    raise
                finally:
                    serializer = AccountInstanceListSerializer(resultsList, many=True)
                    return Response(serializer.data)
            serializer = AccountInstanceListSerializer(queryset, many=True)
            return Response(serializer.data)#, status=status.HTTP_400_BAD_REQUEST)
        return Response(status=status.HTTP_400_BAD_REQUEST)




class updateServiceStatus(APIView): 
    """
    API endpoint to update the status of a Service given the instanceID and the new status
        /servicestatus/<instance-n>/<new_status>
    """
    def startEnforcement(self, agreementId):
        f = restclient.Factory(settings.SLA_URL)
        a_enforcement= f.enforcements()
        a_enforcement.start(agreementId) 

    def stopEnforcement(self, agreementId):
        f = restclient.Factory(settings.SLA_URL)
        a_enforcement= f.enforcements()
        a_enforcement.stop(agreementId) 


    def post(self, request, ns_instance, new_status, format=None):
        """
        ---
        response_serializer: AccountSerializer
        """
        queryset = Account.objects.filter(productType=settings.NETWORK_SERVICE, instanceId=ns_instance)
        if queryset.exists():
            jsonStatus='{"status":"' + new_status + '"}' 
            service = queryset[0]
            serializer = AccountSerializer(service, data=json.loads(jsonStatus))
            try:
                if serializer.is_valid():
                    #saves the new status
                    serializer.save()
                    print "status changed "
                    #start/stop the SLA enforcement accordingly
                    if (new_status == settings.STATUS_RUNNING):
                        self.startEnforcement(service.agreementId)
                    if (new_status == settings.STATUS_STOPPED):
                        self.stopEnforcement(service.agreementId)

                    #prepares and sends the message to the queue
                    message = {}
                    message['instanceId'] = ns_instance
                    message['event'] = new_status
                    #print (json.dumps(serializer.data))
                    send_msg(json.dumps(serializer.data))

                    #update the status of the participant VNFs
                    vnf_list = service.relatives.replace(" ", "").split(",")
                    print "VNF list: ", vnf_list
                    for vnfInstance in vnf_list:
                        time.sleep(1)
                        print "VNF: ", vnfInstance
                        vnfquery = Account.objects.filter(productType=settings.VNF, instanceId=vnfInstance)    
                        vnf = vnfquery[0]
                        vnfserializer = AccountSerializer(vnf, many=False, data=json.loads(jsonStatus))
                        if vnfserializer.is_valid():
                            #print "VNF2: ", json.dumps(vnfserializer.data)
                            vnfserializer.save()
                            #start/stop the SLA enforcement accordingly
                            if (new_status == settings.STATUS_RUNNING):
                                self.startEnforcement(vnf.agreementId)
                            if (new_status == settings.STATUS_STOPPED):
                                self.stopEnforcement(vnf.agreementId)
                    return Response(serializer.data, status=status.HTTP_201_CREATED)
            except ReferenceError:
                print "  [ERROR] Could not update the messages queue"
                return Response(status=status.HTTP_408_REQUEST_TIMEOUT)
            except Exception as e:
                print "  [ERROR] ", e
                return Response(status=status.HTTP_400_BAD_REQUEST)
        return Response(queryset, status=status.HTTP_404_NOT_FOUND)


class updateVNFStatus(APIView): 
    """
    API endpoint to update the status of a VNF given the instanceID and the new status
        /vnfstatus/<instance-n>/<new_status>
    """
    def get(self, request, vnf_instance, new_status, format=None):
        queryset = Account.objects.filter(productType=settings.VNF, instanceId=vnf_instance)
        if queryset.exists():
            jsonStatus='{"status":"' + new_status + '"}' 
            for account in queryset:
                serializer = AccountSerializer(account, data=json.loads(jsonStatus))
                try:
                    if serializer.is_valid():
                        #saves the new status
                        serializer.save()
                        print "status changed "
                        #prepares and sends the message to the queue
                        message = {}
                        message['instanceId'] = vnf_instance
                        message['event'] = new_status
                        #send_msg(str(message))
                        send_msg(json.dumps(serializer.data))
                        return Response(serializer.data, status=status.HTTP_201_CREATED)
                except ReferenceError:
                    print "  [ERROR] Could not update the messages queue"
                    return Response(status=status.HTTP_408_REQUEST_TIMEOUT)
                except Exception:
                    return Response(status=status.HTTP_400_BAD_REQUEST)
        return Response(queryset, status=status.HTTP_404_NOT_FOUND)


class SLAInformation(APIView): 
    """
    API endpoint that gives details about the services purchased by a certain user. 
        /sla-info/?clientId=c1&kind=service
    """
    def get(self, request, format=None):
        """
        ---
        parameters:
            - name: clientId
              description: Client ID
              kind: string
              paramType: form
            - name: type
              description: Type of the SLA information to be returned: service (for the customer) or vnf (for the SP)
              type: string
              paramType: form
        serializer: SLAInfoSerializer
        """
        clientId = self.request.query_params.get('clientId', None)
        kind = self.request.query_params.get('kind', None)
        if (clientId is not None) and (kind is not None):
            f = restclient.Factory(settings.SLA_URL)
            queryset = Account.objects.filter(productType=kind, clientId=clientId)
            if queryset:
                resultsList = list()
                try:
                    for service in queryset:
                        a_client = f.violations()
                        violations = a_client.getbyagreement(service.agreementId)[0]
                        element = {}
                        element['productId'] = service.productId
                        element['productType'] = service.productType
                        element['clientId'] = service.clientId
                        element['providerId'] = service.providerId
                        element['SLAPenalties'] = len(violations)
                        element['agreementId'] = service.agreementId
                        element['dateCreated'] = service.dateCreated
                        if service.status == settings.STATUS_RUNNING:
                            element['dateTerminated'] = None
                        else:
                            element['dateTerminated'] = datetime.datetime.now()
                            
                        resultsList.append(element)
                except Exception as e:
                    print "DEBUG: ", e
                    raise
                finally:
                    serializer = SLAInfoSerializer(resultsList, many=True)
                    return Response(serializer.data)
            serializer = SLAInfoSerializer(queryset, many=True)
            return Response(serializer.data)#, status=status.HTTP_400_BAD_REQUEST)
        return Response(status=status.HTTP_400_BAD_REQUEST)


'''
================ DASHBOARD API ====================
'''

class DashboardServiceList(APIView):
    """
    API endpoint that returns the list of all active services the user is using.  
        /servicelist/<userId>
    """
    def get(self, request, userId, format=None):
        """
        ---
        parameters:
            - name: userId
              description: User ID
              type: string
              paramType: form
        serializer: AccountSerializer
        """
        queryset = Account.objects.filter(status=settings.STATUS_RUNNING, productType=settings.NETWORK_SERVICE, clientId=userId)
        serializer = AccountSerializer(queryset, many=True)
        return Response(serializer.data)


class DashboardVNFList(APIView):
    """
    API endpoint that returns the list of all active VNFs the user is using.  
        /vnflist/<userId>
    """
    def get(self, request, userId, format=None):
        """
        ---
        parameters:
            - name: userId
              description: User ID
              type: string
              paramType: form
        serializer: AccountSerializer
        """
        queryset = Account.objects.filter(status=settings.STATUS_RUNNING, productType=settings.VNF, clientId=userId)
        serializer = AccountSerializer(queryset, many=True)
        return Response(serializer.data)




'''
class MonitorViewSet(viewsets.ModelViewSet):
   #eventChecker2.eventDetection()
   queryset = Monitor.objects.all()
   serializer_class = MonitorSerializer
'''
