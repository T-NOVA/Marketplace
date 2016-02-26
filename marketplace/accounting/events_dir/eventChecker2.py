from datetime import datetime, timedelta
from django.utils import timezone
import dateutil.parser
import json
import requests
import urllib2, urllib



url_slaPenalties = 'http://localhost:8080/sla-service/penalties' 
url_slaAgreement = 'http://localhost:8080/sla-service/enforcements/' 
url_account = 'http://localhost:8000/accounts/' 
url_events = 'http://localhost:8000/events/' 
username_sla = "user" 
password_sla = "password" 


def getServices(url):
   response = urllib2.urlopen(url)
   json_str = response.read()
   arrayObj = json.loads(json_str)
   return arrayObj['results'] 

def putService(url, obj):
   url = '%s%d/' % (url, obj['id'])
   headers = {"content-type": "application/json"}#, "Authorization": "<auth-key>" }
   r = requests.put(url, data=json.dumps(obj), headers=headers)
   print r.text

def postEvent(url, obj):
   #url = '%s%d/' % (url, obj['id'])
   headers = {"content-type": "application/json"}#, "Authorization": "<auth-key>" }
   r = requests.post(url, data=json.dumps(vars(obj)), headers=headers)
   print r.text

def getTimePeriod(period):
   import re

   #Period format: P2W --> Period of 2 Weeks
   try:
      m = re.findall('P(\d+)(\w+)', period)
      if m[0][1] in ['h', 'H']:
         return timedelta(hours=int(m[0][0]))
      if m[0][1] in ['d', 'D']:
         return timedelta(days=int(m[0][0]))
      if m[0][1] in ['m', 'M']:
         return timedelta(weeks=int(m[0][0]))
      if m[0][1] in ['m', 'M']:
         return timedelta(months=int(m[0][0]))
      if m[0][1] in ['y', 'Y']:
         return timedelta(years=int(m[0][0]))
   except:
      print ('The format of the period string is not correct')
   return None



def getPenalties(url, username, password, service, beginDate, endDate): 
   headers = {"Content-Type", "application/json"}
   data = None
   req = urllib2.Request(url, data, headers) 
   response = urllib2.urlopen(req) 
   return str(response.read()) 


def serviceSLASTOP(url, username, password, service): 
   url = '%s%s/stop' % (url, service)
   r = requests.put(url, None, auth=(username, password))
   print r.text




class eventEncoder:
   def __init__(self, obj, reason): 
      self.agreementId = obj['agreementId']
      self.productId = obj['productId']
      self.productType = obj['productType']
      self.eventType = reason
      self.clientId = obj['clientId']
      self.providerId = obj['providerId']
      self.date = obj['lastBillDate']


class eventDetection:

   def __init__(self): 
      servicesArrayObj = getServices(url_account)
      currentDate = datetime.utcnow()
      #print ('hoy: %s' % (str(currentDate)))
      for service in servicesArrayObj:
         #print ('Service: %s, LastBill: %s, Period: %s' % (service['productId'], service['lastBillDate'], service['period']))
         period = getTimePeriod(service['period'])
         if (currentDate - period >= dateutil.parser.parse(service['lastBillDate'])):
            eventType = 'EndOfPeriod'
            #it's time to issue a bill 
            print ('servicio: %d' % service['id'])
            #we update the lastBill field 
            service['lastBillDate'] = str(dateutil.parser.parse(service['lastBillDate']) + period)
            #print json.dumps(vars(eventObj),sort_keys=True, indent=4)
            if (service['renew'] == FALSE):
               serviceSLASTOP (url_slaAgreement, username_sla, password_sla, 's1vnf2_4') 
               eventType = 'EndOfService'
            putService(url_account, service)
            #creation of the event object and POST it into the Billing intermediate module
            eventObj = eventEncoder(service, eventType)
            postEvent(url_events, eventObj)
